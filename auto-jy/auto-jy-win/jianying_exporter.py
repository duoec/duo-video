#!/usr/bin/env python3
"""
Automated video export tool for Jianying (CapCut) - Windows Version
"""

import os
import time
import subprocess
import psutil
import pyautogui
import pygetwindow as gw
from PIL import ImageDraw

# Windows API imports
try:
    import win32gui
    import win32api
    import win32con
    import win32process
    import win32ui
    from ctypes import windll, byref, c_ulong, c_char_p, c_wchar_p, Structure, POINTER, WINFUNCTYPE
    WINDOWS_API_AVAILABLE = True
except ImportError:
    WINDOWS_API_AVAILABLE = False
    warning("警告: Windows API模块未安装，请运行: pip install pywin32")

# 导入日志模块
from logger import logger, info, error, warning, debug
from api_client import APIClient


class JianyingAutoExporter:
    """
    自动化剪映视频导出工具
    """

    def __init__(self, video_id: str, config: dict, task_id: str = None):
        """
        初始化导出器

        :param video_id: 视频ID，用于检测导出完成状态
        :param config: 配置字典（必须提供）
        :param task_id: 任务ID，用于向服务器报告进度
        """
        if config is None:
            raise ValueError("配置字典不能为空，请提供有效的配置")

        # 直接使用传入的配置
        self.config = config

        # 存储video_id和task_id
        self.video_id = video_id
        self.task_id = task_id

        # 初始化API客户端
        api_base_url = self.config.get("api_base_url")
        api_headers = self.config.get("api_headers", {})
        self.api_client = APIClient(base_url=api_base_url, headers=api_headers)

        # 在初始化时进行应用路径检查
        self._validate_app_paths()

        # 设置pyautogui参数
        pyautogui.FAILSAFE = True
        pyautogui.PAUSE = 0.5

        self._system_initialization_task()
    
    def _find_image_with_confidence(self, image_list, confidence_levels=[0.9, 0.8, 0.7], debug=False, retries=10, retry_delay=1):
        """
        通用图像识别方法：先循环置信度，再循环各个图片

        :param image_list: 图片路径列表
        :param confidence_levels: 置信度列表，按从高到低排序
        :param debug: 是否启用调试模式，如果启用则会在找到图像时截图并画红框
        :param retries: 重试次数，默认为3次
        :param retry_delay: 每次重试间隔时间（秒），默认为1秒
        :return: (location, image_path) 如果找到图像，否则返回 (None, None)
        """
        for attempt in range(retries + 1):  # 总共尝试 retries+1 次（包括第一次尝试）
            # 在开始搜索前激活窗口
            self._activate_jianying_window()

            for confidence in confidence_levels:
                for img_path in image_list:
                    try:
                        # 在全屏搜索图像
                        location = pyautogui.locateOnScreen(img_path, confidence=confidence)

                        if location is not None:
                            # 获取中心点坐标
                            center = pyautogui.center(location)
                            # info(f"找到图像 {img_path}，置信度 {confidence}，位置: ({center.x}, {center.y})")

                            if debug:
                                # 启用调试模式，截图并画红框
                                # 再次激活窗口确保截图时窗口在最前面
                                self._activate_jianying_window()

                                screenshot_name = os.path.splitext(os.path.basename(img_path))[0]
                                self._debug_screenshot_with_box(location, img_path, screenshot_name)
                            return center, img_path
                    except pyautogui.ImageNotFoundException:
                        # 图像未找到，继续尝试下一个
                        continue
                    except Exception as e:
                        error(f"尝试检测图像 {img_path} 时发生错误: {e}")
                        continue

            # 如果是最后一次尝试，直接返回None
            if attempt == retries:
                break

            # 否则等待一段时间后重试
            debug(f"第 {attempt + 1} 次尝试未找到图像，等待 {retry_delay} 秒后重试...")
            time.sleep(retry_delay)

        return None, None

    def _detect_and_select_draft(self) -> bool:
        """
        检测是否存在项目，如果存在，则点击第一个草稿（Windows版本）

        :return: 是否成功选择草稿
        """

        # 激活剪映窗口并置顶
        self._activate_jianying_window()

        # 尝试通过图像识别查找草稿
        draft_images = [
            "images/draft_1.jpg",
            "images/draft_2.png",
            "images/draft_3.jpg"
        ]

        # 使用通用图像识别方法，在指定区域内搜索
        center, found_img_path = self._find_image_with_confidence(draft_images, debug=False, retries=3, retry_delay=1)

        if center is not None:
            # 找到图像，获取中心坐标
            # 再次激活剪映窗口并置顶
            self._activate_jianying_window()

            # 对于Windows DPI缩放，需要转换坐标
            adjusted_center = self._adjust_coordinates_for_dpi(center)

            # 点击之前找到的图像中心位置
            pyautogui.click(adjusted_center)
            return True
        else:
            # 图像识别失败，直接返回失败
            error("草稿图像识别失败，操作终止")
            return False

    def _adjust_coordinates_for_dpi(self, point):
        """
        调整坐标以适应Windows DPI缩放
        :param point: 包含x,y坐标的点对象
        :return: 调整后的坐标点
        """
        try:
            # Windows DPI感知
            import ctypes
            user32 = ctypes.windll.user32
            user32.SetProcessDPIAware()

            # 获取DPI缩放因子
            screen_w, screen_h = pyautogui.size()
            img = pyautogui.screenshot()
            img_w, img_h = img.size

            # 计算缩放比例
            scale_x = img_w / screen_w
            scale_y = img_h / screen_h

            # 调整坐标
            adjusted_x = int(point.x / scale_x)
            adjusted_y = int(point.y / scale_y)

            # 创建一个新的Point对象返回
            import collections
            Point = collections.namedtuple('Point', ['x', 'y'])
            return Point(x=adjusted_x, y=adjusted_y)
        except Exception as e:
            debug(f"DPI调整失败，使用原始坐标: {e}")
            # 如果DPI调整失败，返回原始坐标
            import collections
            Point = collections.namedtuple('Point', ['x', 'y'])
            return Point(x=point.x, y=point.y)

    def _fix_dpi_point(self, x, y):
        """
        Windows DPI调整方法（兼容性保留）
        """
        try:
            # Windows DPI感知
            import ctypes
            user32 = ctypes.windll.user32
            user32.SetProcessDPIAware()

            screen_w, screen_h = pyautogui.size()
            img = pyautogui.screenshot()
            img_w, img_h = img.size

            scale_x = img_w / screen_w
            scale_y = img_h / screen_h

            return int(x / scale_x), int(y / scale_y)
        except Exception as e:
            debug(f"DPI调整失败，使用原始坐标: {e}")
            return x, y

    def _check_screen_recording_permission(self):
        """
        检查是否具有屏幕录制权限（Windows版本）
        """
        try:
            # Windows版本：尝试执行一个简单的屏幕截图操作来检测权限
            screenshot = pyautogui.screenshot()
            if screenshot:
                info("屏幕录制权限检查: 已授权")
                return True
            else:
                warning("屏幕录制权限检查: 权限不可用")
                return False
        except Exception as e:
            error(f"屏幕录制权限检查: 权限错误 - {e}")
            return False

    def _request_screen_recording_permission(self):
        """
        提示用户授予屏幕录制权限（Windows版本）
        """
        info("\n" + "="*60)
        info("⚠️  重要提示：需要屏幕录制权限")
        info("="*60)
        info("为了能够捕获剪映窗口内容并进行图像识别，")
        info("请按照以下步骤授予权限：")
        info("")
        info("1. 打开 'Windows设置' > '隐私' > '屏幕捕获'")
        info("2. 允许应用访问您的屏幕录制")
        info("3. 允许桌面应用访问您的屏幕（如果需要）")
        info("")
        info("注意：这是Windows的安全要求，程序无法自动获取此权限")
        info("="*60 + "\n")


    def _debug_screenshot_with_box(self, location, image_path, screenshot_name="debug_screenshot"):
        """
        截图并在匹配位置画红框

        :param location: 匹配到的图像位置 (包含left, top, width, height属性)
        :param image_path: 匹配到的图像路径
        :param screenshot_name: 截图保存的文件名
        """
        try:
            # 检查屏幕录制权限
            has_permission = self._check_screen_recording_permission()
            if not has_permission:
                warning("警告：当前没有屏幕录制权限，截图可能无法包含应用窗口内容")
                self._request_screen_recording_permission()

            # 为了确保与图像检测使用相同的方法，始终使用pyautogui.screenshot()
            # 先激活剪映窗口，确保它在最前面
            self._activate_jianying_window()

            # 添加短暂延迟，确保窗口已激活
            time.sleep(0.5)

            # 使用pyautogui截取全屏 - 这与locateOnScreen使用相同的方法
            screenshot = pyautogui.screenshot()

            # 创建可绘制的对象
            draw = ImageDraw.Draw(screenshot)

            # 使用原始坐标，因为截图是全屏的
            left = location.left
            top = location.top
            right = location.left + location.width
            bottom = location.top + location.height

            # 画红色边框
            draw.rectangle([left, top, right, bottom], outline="red", width=3)

            # 生成带时间戳的文件名
            import datetime
            timestamp = datetime.datetime.now().strftime("%Y%m%d_%H%M%S")
            filename = f"debug_{screenshot_name}_{timestamp}.png"

            # 保存截图
            screenshot.save(filename)
            info(f"调试截图已保存: {filename}")

        except Exception as e:
            error(f"创建调试截图时出错: {e}")

    def _activate_jianying_window(self) -> bool:
        """
        激活剪映窗口并置顶（Windows版本）

        :return: 是否成功激活窗口
        """
        if not WINDOWS_API_AVAILABLE:
            warning("Windows API未可用，尝试使用pygetwindow")
            return self._activate_with_pygetwindow()

        # Windows版本：使用win32gui查找并激活窗口
        def enum_window_callback(hwnd, windows):
            if win32gui.IsWindowVisible(hwnd):
                window_text = win32gui.GetWindowText(hwnd)
                if window_text:
                    # 检查窗口标题是否包含剪映相关关键词
                    if any(name.lower() in window_text.lower() for name in ['jianying', 'capcut', 'videofusion', '剪映', '剪辑', 'videofusion']):
                        windows.append((hwnd, window_text))
            return True

        try:
            windows = []
            win32gui.EnumWindows(enum_window_callback, windows)

            if windows:
                # 激活找到的第一个窗口
                hwnd, window_text = windows[0]
                # 将窗口置于前台
                win32gui.SetForegroundWindow(hwnd)
                win32gui.ShowWindow(hwnd, win32con.SW_RESTORE)
                win32gui.ShowWindow(hwnd, win32con.SW_MAXIMIZE)
                win32gui.ShowWindow(hwnd, win32con.SW_SHOW)
                info(f"成功激活剪映窗口: {window_text}")
                time.sleep(0.5)  # 额外等待时间确保窗口激活
                return True
            else:
                warning("未找到剪映窗口，请确保应用已启动")
                return False

        except Exception as e:
            error(f"使用Windows API激活窗口失败: {e}")
            # 备用方案：使用pygetwindow
            return self._activate_with_pygetwindow()

    def _activate_with_pygetwindow(self) -> bool:
        """
        使用pygetwindow激活窗口（备用方案）
        """
        try:
            if hasattr(gw, 'getAllWindows'):
                all_windows = gw.getAllWindows()
                for window in all_windows:
                    window_title = window.title.lower()
                    if any(name.lower() in window_title for name in ['jianying', 'capcut', 'videofusion', '剪映', '剪辑']):
                        try:
                            window.activate()
                            info(f"通过窗口标题激活: {window.title}")
                            time.sleep(0.5)
                            return True
                        except:
                            continue
            elif hasattr(gw, 'getWindowsWithTitle'):
                for app_name in ['Jianying', 'CapCut', 'VideoFusion', '剪映', '剪辑']:
                    windows = gw.getWindowsWithTitle(app_name)
                    if windows:
                        try:
                            windows[0].activate()
                            info(f"通过窗口标题激活: {windows[0].title}")
                            time.sleep(0.5)
                            return True
                        except:
                            continue
        except Exception as e:
            debug(f"使用pygetwindow激活失败: {e}")

        error("未能激活剪映窗口")
        return False
    
    def export_video(self) -> bool:
        """
        检测导出按钮图片并点击（Windows版本）
        直接检测 images/exportImg.jpg 图片，发现后点击其中心位置

        :return: 是否成功点击导出按钮
        """
        # 激活剪映窗口并置顶
        self._activate_jianying_window()

        # 检测导出按钮图片
        export_img_path = ["images/exportImg.jpg"]
        location, found_img_path = self._find_image_with_confidence(export_img_path, retries=30, retry_delay=1)

        if location is not None:
            try:
                # 检查location对象类型，如果是Point类型，直接使用；如果是Box类型，计算中心
                if hasattr(location, 'left'):  # Box类型 (包含left, top, width, height)
                    center_x = location.left + location.width // 2
                    center_y = location.top + location.height // 2
                    # 创建一个Point对象用于后续处理
                    center_point = type('Point', (), {'x': center_x, 'y': center_y})()
                else:  # Point类型 (包含x, y)
                    center_point = location

                # 对于Windows DPI缩放，需要转换坐标
                adjusted_center = self._adjust_coordinates_for_dpi(center_point)

                pyautogui.click(adjusted_center.x, adjusted_center.y)

                return True
            except Exception as e:
                error(f"处理检测到的图像时发生错误: {e}")
                return False
        else:
            error("未检测到导出按钮图像")
            return False
    
    def _click_export_button(self) -> bool:
        """
        等待导出窗口打开后，点击"导出"按钮（Windows版本）

        :return: 是否成功点击导出按钮
        """

        # 激活剪映窗口并置顶
        self._activate_jianying_window()

        # 尝试通过图像识别导出按钮
        export_button_images = [
            "images/export_confirm_btn.jpg",
            "images/confirmExport.png"
        ]

        # 使用通用图像识别方法，在指定区域内搜索
        button_location, found_img_path = self._find_image_with_confidence(export_button_images, retries=30, retry_delay=1)

        if button_location:
            # 找到图像，获取中心坐标
            # 对于Windows DPI缩放，需要转换坐标
            adjusted_center = self._adjust_coordinates_for_dpi(button_location)

            # 再次激活剪映窗口并置顶
            self._activate_jianying_window()

            # 点击之前找到的图像中心位置
            pyautogui.click(adjusted_center)

            return True
        else:
            # 图像识别失败，直接返回失败
            error("导出按钮图像识别失败，操作终止")
            return False
    
    def _wait_for_export(self) -> bool:
        """
        等待导出完成，然后关闭剪映

        :return: 是否成功完成导出并关闭
        """
        timeout = 300  # 硬编码时间配置 timing_for_export_completion
        info(f"正在等待导出完成（最多{timeout}秒）...")

        start_time = time.time()

        # 获取导出目录路径
        exports_path = os.path.expanduser(self.config["exports_path"])

        success = False

        # 检查是否有video_id
        if self.video_id:
            # 基于video_id的检测逻辑
            while time.time() - start_time < timeout:
                # 定时1秒检查一次导出目录
                time.sleep(1)

                # 检查导出目录是否存在 {videoId}.{mp4|mov}的文件且文件大小必须大于100K
                for ext in ['.mp4', '.mov']:
                    video_file_path = os.path.join(exports_path, f"{self.video_id}{ext}")
                    if os.path.exists(video_file_path):
                        file_size = os.path.getsize(video_file_path)
                        if file_size > 100 * 1024:  # 100KB
                            info(f"检测到导出完成: {video_file_path}, 大小: {file_size} 字节")
                            success = True
                            break

                if success:
                    info("导出完成，正在关闭剪映...")
                    break

                # 输出等待信息
                elapsed = int(time.time() - start_time)
                debug(f"已等待 {elapsed} 秒...")
        else:
            info("使用图像检测导出完成")
            # 基于图像的检测逻辑
            while time.time() - start_time < timeout:
                # 每秒检查一次
                time.sleep(1)

                # 检测 images/export_success.jpg 是否存在
                success_img_path = "images/export_success.jpg"
                if os.path.exists(success_img_path):
                    # 尝试使用图像识别检测成功标志
                    location, found_img = self._find_image_with_confidence([success_img_path], retries=1)
                    if location is not None:
                        info("检测到导出成功图像，导出完成")
                        success = True
                        break

                # 输出等待信息
                elapsed = int(time.time() - start_time)
                debug(f"已等待 {elapsed} 秒...")

        return success
    
    
    def _system_initialization_task(self) -> bool:
        """
        系统启动初始化任务
        1. 检测如果剪映如果启动，则先关闭

        :return: 是否成功完成初始化任务
        """

        self._close_jianying()

        return True
    
    def _validate_app_paths(self):
        """
        验证应用路径是否存在
        """
        self.valid_app_path = self.config["app_path"]
        if not os.path.exists(self.valid_app_path):
            warning("警告：未找到剪映应用程序，请确认是否已安装")

    def _open_jianying(self) -> bool:
        """
        打开剪映应用程序（Windows版本）

        :return: 是否成功打开
        """

        # 尝试通过命令行打开剪映
        try:
            # Windows版本：使用start命令或直接调用可执行文件
            app_path = self.valid_app_path
            if os.path.exists(app_path):
                # 方案1：直接启动可执行文件
                subprocess.Popen(app_path, shell=True)
                info(f"剪映应用已启动: {app_path}")
                time.sleep(2)  # 等待应用启动
                return True
            else:
                # 方案2：使用start命令
                subprocess.Popen(f'start "" "{app_path}"', shell=True)
                info(f"剪映应用已启动 (start命令): {app_path}")
                time.sleep(2)
                return True

        except Exception as e:
            error(f"打开剪映时发生错误: {e}")
            return False

    def _close_jianying(self) -> bool:
        """
        关闭剪映应用程序（Windows版本）

        :return: 是否成功关闭应用
        """
        try:
            # 首先尝试通过Windows API关闭窗口
            if WINDOWS_API_AVAILABLE:
                try:
                    def enum_window_callback(hwnd, windows):
                        if win32gui.IsWindowVisible(hwnd):
                            window_text = win32gui.GetWindowText(hwnd)
                            if window_text:
                                # 检查窗口标题是否包含剪映相关关键词
                                if any(name.lower() in window_text.lower() for name in ['jianying', 'capcut', 'videofusion', '剪映', '剪辑']):
                                    windows.append((hwnd, window_text))
                        return True

                    windows = []
                    win32gui.EnumWindows(enum_window_callback, windows)

                    if windows:
                        for hwnd, window_text in windows:
                            # 尝试发送WM_CLOSE消息优雅关闭
                            win32gui.PostMessage(hwnd, win32con.WM_CLOSE, 0, 0)
                            info(f"已发送关闭消息到窗口: {window_text}")

                        # 等待窗口关闭
                        time.sleep(1)
                except Exception as e:
                    debug(f"使用Windows API关闭窗口失败: {e}")

            # 使用psutil强制终止所有相关的剪映进程
            closed_processes = []
            for proc in psutil.process_iter(['pid', 'name']):
                try:
                    process_name = proc.info['name'].lower()
                    if any(name.lower() in process_name for name in ['jianying', 'capcut', 'videofusion', 'jianyingpro']):
                        pid = proc.info['pid']
                        try:
                            # 先尝试优雅终止
                            proc.terminate()
                            closed_processes.append((pid, proc.info['name'], 'terminated'))
                        except (psutil.NoSuchProcess, psutil.AccessDenied, psutil.ZombieProcess):
                            continue
                except (psutil.NoSuchProcess, psutil.AccessDenied, psutil.ZombieProcess):
                    continue

            # 等待进程结束
            time.sleep(1)

            # 检查是否还有相关进程在运行，如果有则强制杀死
            for proc in psutil.process_iter(['pid', 'name']):
                try:
                    process_name = proc.info['name'].lower()
                    if any(name.lower() in process_name for name in ['jianying', 'capcut', 'videofusion', 'jianyingpro']):
                        pid = proc.info['name']
                        try:
                            proc.kill()  # 强制杀死进程
                            closed_processes.append((pid, proc.info['name'], 'killed'))
                        except (psutil.NoSuchProcess, psutil.AccessDenied, psutil.ZombieProcess):
                            continue
                except (psutil.NoSuchProcess, psutil.AccessDenied, psutil.ZombieProcess):
                    continue

            # 等待进程完全终止
            time.sleep(1)

            if closed_processes:
                for info_tuple in closed_processes:
                    if len(info_tuple) == 3:
                        pid, name, action = info_tuple
                        info(f"已{action}进程 PID:{pid} {name}")
                return True
            else:
                info("未找到运行中的剪映进程")
                return True

        except Exception as e:
            error(f"关闭剪映时发生错误: {e}")
            return False
        
    def _export_finish(self) -> bool:
        """
        导出完成后的清理工作
        1. 关闭剪映

        :return: 是否成功完成清理工作
        """

        # 1. 关闭剪映
        close_success = self._close_jianying()
        if not close_success:
            warning("警告：关闭剪映时出现问题")

        return True
    
    def run_full_process(self) -> bool:
        """
        运行完整的自动化流程

        :return: 整个流程是否成功完成
        """

        steps = [
            ("打开剪映", self._open_jianying),
            ("打开草稿", self._detect_and_select_draft),
            ("打开导出窗口", self.export_video),
            ("点击导出按钮", self._click_export_button),
            ("等待导出完成", self._wait_for_export),
            ("关闭剪映", self._export_finish)
        ]

        for i, (step_name, step_func) in enumerate(steps):
            success = step_func()

            # 根据步骤推送相应状态
            if success:
                if step_name == "打开草稿":
                    # 13=打开工程 - 选择草稿后
                    self.api_client.update_task_status(self.task_id, 13, {"info": "打开工程"})
                elif step_name == "等待导出完成":
                    # 100=导出完成 - 导出完成后
                    self.api_client.update_task_status(self.task_id, 14, {"info": "生成视频"})

                info(f"步骤 '{step_name}' 完成")

            else:
                error(f"步骤 '{step_name}' 失败，停止执行")

                # 推送状态：-11=任务失败
                if self.task_id:
                    self.api_client.update_task_status(self.task_id, -11, {"error": f"步骤 '{step_name}' 失败"})

                return False
            
        return True
        

    def cancel_task(self):
        """
        主动取消任务并上报状态
        """
        info("收到取消任务请求")

        # 推送状态：-10=主动取消
        if self.task_id:
            self.api_client.update_task_status(self.task_id, -10, {"info": "任务被主动取消"})

        # 关闭剪映应用
        self._close_jianying()


if __name__ == '__main__':
    config = {
        # 剪映目录 (Windows版本)
        "app_path": r"C:\Program Files\VideoFusion\VideoFusion.exe",

        # 剪映草稿目录 (Windows版本)
        "drafts_path": r"C:\Users\xuwenzhen\Videos\JianyingPro\User Data\Projects\com.lveditor.draft",

        # 剪映视频导出目录 (Windows版本)
        "exports_path": r"C:\Users\xuwenzhen\Downloads\video",

        # API服务器配置
        "api_base_url": "http://localhost:17026",
        "api_headers": {"Authorization": "Bearer token"}
    }

    # 示例：使用任务ID为"12345"的任务
    exporter = JianyingAutoExporter(video_id="1月31日", config=config, task_id="12345")
    exporter.run_full_process()
    # exporter._activate_jianying_window()