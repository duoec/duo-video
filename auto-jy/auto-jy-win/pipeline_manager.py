#!/usr/bin/env python3
"""
自动化管道管理系统
包含三个主要管道：
1. pipeline1: 循环检测API接口
2. pipeline2: 执行导出流程
3. pipeline3: 上传视频
"""

import time
import configparser
import os
import sys
from typing import Optional, Dict, Any
from jianying_exporter import JianyingAutoExporter
from api_client import APIClient
from download_extractor import download_and_extract
from upload_handler import UploadHandler

# 导入日志模块
from logger import info, error, warning


class PipelineManager:
    """
    自动化管道管理系统
    """
    
    def __init__(self, config_path: str = "app_config.ini"):
        """
        初始化管道管理器

        :param config_path: 配置文件路径
        """
        self.config = self.load_config(config_path)
        self.running = True
        
    def load_config(self, config_path: str) -> dict:
        """
        加载配置文件

        :param config_path: 配置文件路径
        :return: 配置字典
        """
        try:
            config_parser = configparser.ConfigParser()
            config_parser.read(config_path, encoding='utf-8')

            # 解析配置（一层结构）
            config = {}

            # API配置
            if config_parser.has_section('api'):
                config['api_base_url'] = config_parser.get('api', 'base_url')
                config['api_interval'] = config_parser.getint('api', 'interval')
                config['api_headers'] = dict(config_parser.items('api')) if config_parser.has_option('api', 'headers') else {}

            # 剪映配置
            if config_parser.has_section('jianying'):
                config['app_path'] = config_parser.get('jianying', 'app_path')
                config['drafts_path'] = config_parser.get('jianying', 'drafts_path')
                config['exports_path'] = config_parser.get('jianying', 'exports_path')

            # 上传配置
            if config_parser.has_section('upload'):
                config['upload_storage'] = config_parser.get('upload', 'storage', fallback='oss')
                config['video_key_prefix'] = config_parser.get('upload', 'video_key_prefix', fallback='video')

            # OSS配置（仅在storage=oss时有效）
            if config_parser.has_section('oss'):
                config['oss_bucket_name'] = config_parser.get('oss', 'bucket_name', fallback='')
                config['oss_access_key_id'] = config_parser.get('oss', 'access_key_id', fallback='')
                config['oss_access_key_secret'] = config_parser.get('oss', 'access_key_secret', fallback='')
                config['oss_endpoint'] = config_parser.get('oss', 'endpoint', fallback='')
                config['oss_region'] = config_parser.get('oss', 'region', fallback='')

            # COS配置（仅在storage=cos时有效）
            if config_parser.has_section('cos'):
                config['cos_bucket_name'] = config_parser.get('cos', 'bucket_name', fallback='')
                config['cos_secret_id'] = config_parser.get('cos', 'secret_id', fallback='')
                config['cos_secret_key'] = config_parser.get('cos', 'secret_key', fallback='')
                config['cos_region'] = config_parser.get('cos', 'region', fallback='')

            return config
        except Exception as e:
            error(f"配置文件 {config_path} 读取失败: {e}")
            raise e
    
    
    def get_video_task(self) -> Optional[Dict[str, Any]]:
        """
        Pipeline1: 循环检测API接口
        接口返回标准结构：{"code": 0, "data": {"videoId": 12345678654}}
        如果 data 不为空，则表示有任务

        :return: 任务数据或None
        """
        base_url = self.config.get('api_base_url', '')
        interval = self.config.get('api_interval', 30)
        headers = self.config.get('api_headers', {})

        if not base_url:
            warning("API Base URL 未配置，跳过检测")
            time.sleep(interval)
            return None

        try:
            client = APIClient(base_url=base_url, headers=headers)
            success, task_data, message = client.fetch_task()

            if success and task_data:
                self.task_id = task_data.get('taskId', task_data.get('videoId', None))
                return task_data
            elif success and not task_data:
                # 推送状态：0=等待处理
                # 注意：只有当有任务ID时才上报状态，如果没有任务则不报状态
                time.sleep(interval)
                return None
            else:
                error(f"API请求失败: {message}")
                time.sleep(interval)
                return None
        except Exception as e:
            error(f"检测API时发生错误: {e}")
            time.sleep(interval)
            return None
    
    def export_video(self, task_data: Dict[str, Any]) -> Optional[str]:
        """
        Pipeline2: 执行导出流程
        初始化 JianyingAutoExporter，并执行导出流程

        :param task_data: 任务数据
        :return: 成功时返回视频文件地址，失败时返回 None
        """
        try:
            video_id = task_data.get('videoId')
            if not video_id:
                error("任务数据中缺少videoId")
                return None

            # 从配置中获取剪映相关配置（一层结构）
            app_path = self.config.get('app_path', '')
            drafts_path = self.config.get('drafts_path', '')
            exports_path = self.config.get('exports_path', '')

            # 验证必要配置
            if not app_path:
                error("错误：未配置剪映应用路径")
                return None

            if not drafts_path:
                error("错误：未配置剪映草稿目录")
                return None

            if not exports_path:
                error("错误：未配置剪映导出目录")
                return None

            # 初始化导出器，传入配置和video_id
            exporter = JianyingAutoExporter(
                video_id=str(video_id),
                config=self.config,
                task_id=self.task_id
            )

            success = exporter.run_full_process()
            if success:
                # 构建视频文件路径
                exports_path = os.path.expanduser(self.config['exports_path'])

                # 检查可能的视频文件扩展名
                for ext in ['.mp4', '.mov']:
                    potential_path = os.path.join(exports_path, f"{video_id}{ext}")
                    if os.path.exists(potential_path):
                        return potential_path

                # 如果找不到视频文件，返回 None
                error(f"导出成功但未找到视频文件: {video_id}")
                return None
            else:
                return None
        except Exception as e:
            error(f"执行导出流程时发生错误: {e}")
            return None
    

    def upload_video(self, task_data: Dict[str, Any]) -> bool:
        """
        Pipeline3: 上传视频
        根据配置上传导出的视频文件

        :param task_data: 任务数据
        :return: 是否成功上传
        """
        try:
            video_id = task_data.get('videoId')
            if not video_id:
                error("任务数据中缺少videoId")
                return False

            # 优先使用传入的视频文件路径
            video_file_path = task_data.get('video_file_path')

            if not video_file_path:
                error(f"未找到视频文件: {video_id}")
                return False

            # 创建上传处理器并执行上传
            video_file_name = os.path.basename(video_file_path)
            video_key_prefix = self.config.get('video_key_prefix', 'video')
            # 如果 video_key_prefix 以 / 结尾，先去掉 /
            if video_key_prefix.endswith('/'):
                video_key_prefix = video_key_prefix.rstrip('/')
            storage_key = f"{video_key_prefix}/{video_file_name}"

            uploader = UploadHandler(self.config)
            success, file_url, message = uploader.upload_video(video_file_path, storage_key)

            if success and file_url:
                # 通过API上报任务完成状态
                base_url = self.config.get('api_base_url', '')
                headers = self.config.get('api_headers', {})

                if base_url:
                    client = APIClient(base_url=base_url, headers=headers)

                    # 推送状态：100=导出完成（任务已完成）
                    # 注意：这里保留原有的 update_task_status 调用，因为 report_progress 已被删除
                    task_id = task_data.get('taskId')
                    client.update_task_status(task_id, 100, {"videoUrl": file_url, "info": "任务完成"})

                # 上传成功后，执行清理工作
                self.cleanup_pipeline(task_data)

                return True
            else:
                error(f"视频上传失败: {message}")

                # 通过API上报任务失败状态
                base_url = self.config.get('api_base_url', '')
                if base_url:
                    client = APIClient(base_url=base_url, headers=self.config.get('api_headers', {}))

                    # 推送状态：-11=任务失败
                    # 注意：这里保留原有的 update_task_status 调用，因为 report_progress 已被删除
                    client.update_task_status(str(video_id), -11, {"error": message})

                return False
        except Exception as e:
            error(f"创建上传处理器时发生错误: {e}")
            # 推送状态：-11=任务失败
            video_id = task_data.get('videoId', 'unknown')
            task_id = str(video_id)
            client = APIClient(base_url=self.config.get('api_base_url', ''), headers=self.config.get('api_headers', {}))
            # 注意：这里保留原有的 update_task_status 调用，因为 report_progress 已被删除
            client.update_task_status(task_id, -11, {"error": f"创建上传处理器时发生错误: {e}"})
            return False

    def cleanup_pipeline(self, task_data: Dict[str, Any]) -> bool:
        """
        清理管道：关闭剪映应用、删除草稿、清空导出目录

        :param task_data: 任务数据
        :return: 是否成功完成清理
        """
        try:
            # info("开始执行管道清理工作...")

            # 1. 关闭剪映应用
            self.close_jianying_app()

            # 2. 删除草稿
            # info("正在删除草稿...")
            drafts_path = self.config.get("drafts_path", "")
            if drafts_path and os.path.exists(drafts_path):
                try:
                    import shutil
                    # 删除草稿目录下的内容，但保留目录本身
                    for item in os.listdir(drafts_path):
                        item_path = os.path.join(drafts_path, item)
                        if os.path.isfile(item_path):
                            os.remove(item_path)
                            # info(f"已删除草稿文件: {item_path}")
                        elif os.path.isdir(item_path):
                            shutil.rmtree(item_path)
                            # info(f"已删除草稿目录: {item_path}")
                    info("草稿清理完成")
                except Exception as e:
                    error(f"删除草稿时发生错误: {e}")
            else:
                info("草稿路径未配置或不存在，跳过删除")

            # 3. 清空导出目录
            # info("正在清空导出目录...")
            exports_path = os.path.expanduser(self.config.get("exports_path", ""))
            if exports_path and os.path.exists(exports_path):
                try:
                    import shutil
                    # 只保留导出目录本身，删除其中的内容
                    for item in os.listdir(exports_path):
                        item_path = os.path.join(exports_path, item)
                        if os.path.isfile(item_path):
                            os.remove(item_path)
                            # info(f"已删除导出文件: {item_path}")
                        elif os.path.isdir(item_path):
                            shutil.rmtree(item_path)
                            # info(f"已删除导出目录: {item_path}")
                except Exception as e:
                    error(f"清空导出目录时发生错误: {e}")
            else:
                info("导出路径未配置或不存在，跳过清空")

            return True
        except Exception as e:
            error(f"管道清理过程中发生错误: {e}")
            return False

    def close_jianying_app(self) -> bool:
        """
        关闭剪映应用程序（Windows版本）

        :return: 是否成功关闭应用
        """
        try:
            import psutil

            # Windows版本：使用psutil终止进程
            # 首先尝试通过Windows API关闭窗口
            try:
                import win32gui
                import win32con

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
            except ImportError:
                warning("win32gui模块未安装，跳过窗口关闭")
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
                        pid = proc.info['pid']
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
    
    def run_pipeline_loop(self):
        """
        运行管道循环
        1. 检测API接口
        2. 如果有任务，下载并解压资源
        3. 解压成功后，执行导出流程
        4. 导出成功后，上传视频
        5. 继续循环
        """

        while self.running:
            try:
                # Pipeline1: 检测API接口
                task_data = self.get_video_task()

                if task_data:
                    # 提取jyZipUrl和task_id
                    jy_zip_url = task_data.get('jyZipUrl')
                    if not jy_zip_url:
                        error("任务数据中缺少jyZipUrl")
                        # 跳过此任务，继续下一次循环
                        continue

                    task_id = task_data.get('taskId')

                    # Pipeline2: 下载并解压资源
                    download_success = download_and_extract(self.config, jy_zip_url, task_id)

                    if download_success:
                        # Pipeline3: 执行导出流程
                        video_file_path = self.export_video(task_data)

                        if video_file_path:
                            # Pipeline4: 上传视频
                            # 更新task_data以包含视频文件路径信息
                            task_data_with_path = task_data.copy()
                            task_data_with_path['video_file_path'] = video_file_path
                            upload_success = self.upload_video(task_data_with_path)

                            if not upload_success:
                                warning("上传失败，但导出成功")
                        else:
                            error("导出失败，跳过上传")

                            # 推送状态：-11=任务失败
                            if task_id:
                                client = APIClient(base_url=self.config.get('api_base_url', ''),
                                                  headers=self.config.get('api_headers', {}))
                                client.update_task_status(task_id, -11, {"error": "导出失败"})
                    else:
                        error("下载或解压失败，跳过导出和上传")

                # 短暂休眠，避免过度占用CPU
                time.sleep(self.config["api_interval"])

            except KeyboardInterrupt:
                info("收到中断信号，正在停止管道系统...")
                self.running = False
                break
            except Exception as e:
                error(f"管道循环中发生错误: {e}")
                time.sleep(5)  # 出错后等待5秒再继续


def main():
    """
    主函数
    """
    # 从命令行参数获取配置文件路径
    config_path = sys.argv[1] if len(sys.argv) > 1 else "app_config.ini"
    
    # 创建管道管理器
    manager = PipelineManager(config_path=config_path)
    
    # 运行管道循环
    manager.run_pipeline_loop()


if __name__ == "__main__":
    main()