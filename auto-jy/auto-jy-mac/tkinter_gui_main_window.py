#!/usr/bin/env python3
"""
主GUI窗口 - Tkinter版本
提供自动化管道管理系统的图形界面
"""

import sys
import os
import threading
import tkinter as tk
from tkinter import ttk, messagebox, scrolledtext
from datetime import datetime
import time

# 导入相关模块
from pipeline_manager import PipelineManager
from gui_logger import setup_gui_logging, get_gui_logger
from port_singleton import is_instance_running


class StatusMapping:
    """状态码映射类"""

    STATUS_MAP = {
        -11: "任务失败",
        -10: "主动取消",
        0: "等待处理",
        10: "任务已领取",
        11: "下载完成",
        12: "解压完成",
        13: "打开工程",
        14: "生成视频",
        100: "导出完成"
    }

    @staticmethod
    def get_status_text(status_code: int) -> str:
        """获取状态描述文本"""
        return StatusMapping.STATUS_MAP.get(status_code, f"未知状态({status_code})")


class LogDisplay(scrolledtext.ScrolledText):
    """自定义日志显示组件"""

    def __init__(self, parent=None):
        super().__init__(parent)
        self.config(state=tk.DISABLED)
        # 设置字体
        self.config(font=('Consolas', 10))
        
        # 最大日志条目数
        self.max_lines = 1000

    def append_log(self, message: str):
        """添加日志消息"""
        # 格式化时间戳
        timestamp = datetime.now().strftime("%H:%M:%S")
        formatted_message = f"[{timestamp}] {message}\n"

        # 启用编辑
        self.config(state=tk.NORMAL)
        
        # 添加到文本框
        self.insert(tk.END, formatted_message)
        
        # 限制最大行数
        lines = self.get(1.0, tk.END).split('\n')
        if len(lines) > self.max_lines:
            # 删除多余的行
            excess = len(lines) - self.max_lines
            self.delete(1.0, f"{excess}.0")
        
        # 滚动到底部
        self.see(tk.END)
        
        # 禁用编辑
        self.config(state=tk.DISABLED)


class MainWindow:
    """主窗口类"""

    def __init__(self, config_path: str = "app_config.ini"):
        self.config_path = config_path
        self.pipeline_manager = None
        self.is_running = False
        
        # 创建UI
        self.root = tk.Tk()
        self.root.title("duo-video - 自动化视频处理系统")
        self.root.geometry("1000x700")
        self.root.minsize(1000, 700)

        # 设置窗口图标
        try:
            icon_path = "logo_192.png"
            if os.path.exists(icon_path):
                self.root.iconphoto(False, tk.PhotoImage(file=icon_path))
        except Exception as e:
            print(f"加载图标失败: {e}")
        
        # 创建UI组件
        self.create_ui()
        
        # 初始化管道管理器
        self.init_pipeline_manager()
        
        # 设置定时器更新UI
        self.update_ui_periodically()
        
        # 自动启动服务
        self.start_pipeline_service()

    def create_ui(self):
        """创建用户界面"""
        # 创建主框架
        main_frame = ttk.Frame(self.root, padding="10")
        main_frame.grid(row=0, column=0, sticky=(tk.W, tk.E, tk.N, tk.S))
        
        # 配置网格权重
        self.root.columnconfigure(0, weight=1)
        self.root.rowconfigure(0, weight=1)
        main_frame.columnconfigure(1, weight=1)  # 右侧日志面板
        main_frame.rowconfigure(0, weight=1)

        # ==================== 左栏：操作界面 ====================
        left_panel = self.create_left_panel(main_frame)
        left_panel.grid(row=0, column=0, sticky=(tk.W, tk.E, tk.N, tk.S), padx=(0, 5))
        left_panel.config(width=350)
        main_frame.columnconfigure(0, weight=0)  # 左侧固定宽度

        # ==================== 右栏：日志显示 ====================
        right_panel = self.create_right_panel(main_frame)
        right_panel.grid(row=0, column=1, sticky=(tk.W, tk.E, tk.N, tk.S))

    def create_left_panel(self, parent) -> ttk.Frame:
        """创建左栏操作面板"""
        panel = ttk.Frame(parent, padding="10")

        # 服务器信息
        server_frame = ttk.Frame(panel, padding="5")
        server_frame.grid(row=0, column=0, sticky=(tk.W, tk.E), pady=(0, 10))
        server_frame.columnconfigure(1, weight=1)

        ttk.Label(server_frame, text="服务器地址:").grid(row=0, column=0, sticky=tk.W)
        self.server_url_label = ttk.Label(server_frame, text="未配置")
        self.server_url_label.grid(row=0, column=1, sticky=(tk.W, tk.E), padx=(5, 0))

        # 操作按钮
        button_frame = ttk.Frame(panel, padding="5")
        button_frame.grid(row=1, column=0, sticky=(tk.W, tk.E), pady=(0, 10))
        button_frame.columnconfigure(0, weight=1)

        self.toggle_button = tk.Button(button_frame, text="启 动", command=self.toggle_pipeline,
                                       bg="#90EE90", fg="black", relief=tk.FLAT, height=2,
                                       font=("Arial", 12, "bold"))  # 浅绿色
        self.toggle_button.grid(row=0, column=0, sticky=(tk.W, tk.E), padx=5, pady=5)

        # 任务状态
        task_frame = ttk.Frame(panel, padding="5", relief="groove", borderwidth=1)
        task_frame.grid(row=2, column=0, sticky=(tk.W, tk.E), pady=(0, 10))
        task_frame.columnconfigure(1, weight=1)

        # 添加标题标签
        task_title = ttk.Label(task_frame, text="当前任务", font=("Arial", 10, "bold"))
        task_title.grid(row=0, column=0, columnspan=2, sticky=tk.W, pady=(0, 5))

        ttk.Label(task_frame, text="当前任务:").grid(row=1, column=0, sticky=tk.W)
        self.current_task_label = ttk.Label(task_frame, text="空闲")
        self.current_task_label.grid(row=1, column=1, sticky=(tk.W, tk.E), padx=(5, 0))

        ttk.Label(task_frame, text="任务状态:").grid(row=2, column=0, sticky=tk.W)
        self.task_status_label = ttk.Label(task_frame, text="空闲")
        self.task_status_label.grid(row=2, column=1, sticky=(tk.W, tk.E), padx=(5, 0))

        # 下载进度
        download_frame = ttk.Frame(panel, padding="5", relief="groove", borderwidth=1)
        download_frame.grid(row=3, column=0, sticky=(tk.W, tk.E), pady=(0, 10))
        download_frame.columnconfigure(1, weight=1)

        # 添加标题标签
        download_title = ttk.Label(download_frame, text="正在下载", font=("Arial", 10, "bold"))
        download_title.grid(row=0, column=0, columnspan=2, sticky=tk.W, pady=(0, 5))

        ttk.Label(download_frame, text="下载文件:").grid(row=1, column=0, sticky=tk.W)
        self.download_filename_label = ttk.Label(download_frame, text="无")
        self.download_filename_label.grid(row=1, column=1, sticky=(tk.W, tk.E), padx=(5, 0))

        ttk.Label(download_frame, text="下载进度:").grid(row=2, column=0, sticky=tk.W)
        self.download_progress = ttk.Progressbar(download_frame, mode='determinate')
        self.download_progress.grid(row=2, column=1, sticky=(tk.W, tk.E), padx=(5, 0), pady=(5, 5))

        ttk.Label(download_frame, text="下载速度:").grid(row=3, column=0, sticky=tk.W)
        self.download_speed_label = ttk.Label(download_frame, text="0 B/s")
        self.download_speed_label.grid(row=3, column=1, sticky=(tk.W, tk.E), padx=(5, 0))

        # 上传进度
        upload_frame = ttk.Frame(panel, padding="5", relief="groove", borderwidth=1)
        upload_frame.grid(row=4, column=0, sticky=(tk.W, tk.E), pady=(0, 10))
        upload_frame.columnconfigure(1, weight=1)

        # 添加标题标签
        upload_title = ttk.Label(upload_frame, text="正在上传", font=("Arial", 10, "bold"))
        upload_title.grid(row=0, column=0, columnspan=2, sticky=tk.W, pady=(0, 5))

        ttk.Label(upload_frame, text="上传文件:").grid(row=1, column=0, sticky=tk.W)
        self.upload_filename_label = ttk.Label(upload_frame, text="无")
        self.upload_filename_label.grid(row=1, column=1, sticky=(tk.W, tk.E), padx=(5, 0))

        ttk.Label(upload_frame, text="上传进度:").grid(row=2, column=0, sticky=tk.W)
        self.upload_progress = ttk.Progressbar(upload_frame, mode='determinate')
        self.upload_progress.grid(row=2, column=1, sticky=(tk.W, tk.E), padx=(5, 0), pady=(5, 5))

        ttk.Label(upload_frame, text="上传速度:").grid(row=3, column=0, sticky=tk.W)
        self.upload_speed_label = ttk.Label(upload_frame, text="0 B/s")
        self.upload_speed_label.grid(row=3, column=1, sticky=(tk.W, tk.E), padx=(5, 0))

        # 添加弹性空间
        panel.rowconfigure(5, weight=1)
        
        return panel

    def create_right_panel(self, parent) -> ttk.Frame:
        """创建右栏日志面板"""
        panel = ttk.Frame(parent, padding="5")

        # 日志显示区域
        self.log_display = LogDisplay(panel)
        self.log_display.grid(row=0, column=0, sticky=(tk.W, tk.E, tk.N, tk.S))

        # 配置权重使日志区域可扩展
        panel.columnconfigure(0, weight=1)
        panel.rowconfigure(0, weight=1)

        return panel

    def init_pipeline_manager(self):
        """初始化管道管理器"""
        try:
            self.pipeline_manager = PipelineManager(self.config_path)

            # 从配置中获取服务器地址
            server_url = self.pipeline_manager.config.get('api_base_url', '未配置')
            self.server_url_label.config(text=server_url)

            # 设置日志回调
            from gui_logger import setup_gui_logging, setup_logger
            setup_gui_logging(self.log_received)
            setup_logger()

        except Exception as e:
            self.log_received(f"初始化失败: {e}")

    def log_received(self, message: str):
        """日志接收回调"""
        # 在主线程中更新UI
        self.root.after(0, lambda: self.log_display.append_log(message))

    def toggle_pipeline(self):
        """切换管道运行状态"""
        if not self.pipeline_manager:
            return

        if self.is_running:
            # 当前是运行状态，点击后暂停
            self.pipeline_manager.paused = True
            self.is_running = False
            self.toggle_button.config(text="启 动", bg="#90EE90")  # 浅绿色
            self.log_received("系统已暂停")
        else:
            # 当前是暂停状态，点击后启动
            self.pipeline_manager.paused = False
            self.is_running = True
            self.toggle_button.config(text="暂 停", bg="#FFB6C1")  # 浅红色
            self.log_received("系统已启动")

            # 在新线程中运行管道循环
            self.pipeline_thread = threading.Thread(
                target=self.run_pipeline_loop,
                daemon=True
            )
            self.pipeline_thread.start()

    def start_pipeline_service(self):
        """启动管道服务"""
        if not self.pipeline_manager:
            return

        # 如果当前是暂停状态，启动服务
        if not self.is_running:
            self.pipeline_manager.paused = False
            self.is_running = True

            # 更新按钮状态为"暂停"
            self.toggle_button.config(text="暂 停", bg="#FFB6C1")  # 浅红色
            self.log_received("系统已启动")

            # 在新线程中运行管道循环
            self.pipeline_thread = threading.Thread(
                target=self.run_pipeline_loop,
                daemon=True
            )
            self.pipeline_thread.start()

    def run_pipeline_loop(self):
        """在单独线程中运行管道循环"""
        if self.pipeline_manager:
            self.pipeline_manager.run_pipeline_loop()

    def update_ui_periodically(self):
        """定期更新UI显示"""
        self.update_ui()
        # 每秒更新一次UI
        self.root.after(1000, self.update_ui_periodically)

    def update_ui(self):
        """更新UI显示"""
        if not self.pipeline_manager:
            return

        try:
            # 更新任务状态
            if hasattr(self.pipeline_manager, 'task_id') and self.pipeline_manager.task_id:
                self.current_task_label.config(text=str(self.pipeline_manager.task_id))

                # 更新任务状态文本
                if hasattr(self.pipeline_manager, 'current_task_status'):
                    status_text = StatusMapping.get_status_text(self.pipeline_manager.current_task_status)
                    self.task_status_label.config(text=status_text)
                else:
                    self.task_status_label.config(text="未知状态")
            else:
                self.current_task_label.config(text="空闲")
                self.task_status_label.config(text="空闲")

            # 更新下载进度
            if hasattr(self.pipeline_manager, 'last_download_filename'):
                if self.pipeline_manager.last_download_filename:
                    self.download_filename_label.config(text=self.pipeline_manager.last_download_filename)
                    self.download_progress['value'] = int(self.pipeline_manager.last_download_percent)

                    # 格式化速度
                    speed = self.format_speed(self.pipeline_manager.last_download_speed)
                    self.download_speed_label.config(text=speed)
                else:
                    # 如果没有下载任务，清空下载信息
                    self.download_filename_label.config(text="无")
                    self.download_progress['value'] = 0
                    self.download_speed_label.config(text="0 B/s")

            # 更新上传进度
            if hasattr(self.pipeline_manager, 'last_upload_filename'):
                if self.pipeline_manager.last_upload_filename:
                    self.upload_filename_label.config(text=self.pipeline_manager.last_upload_filename)
                    self.upload_progress['value'] = int(self.pipeline_manager.last_upload_percent)

                    # 格式化速度
                    speed = self.format_speed(self.pipeline_manager.last_upload_speed)
                    self.upload_speed_label.config(text=speed)
                else:
                    # 如果没有上传任务，清空上传信息
                    self.upload_filename_label.config(text="无")
                    self.upload_progress['value'] = 0
                    self.upload_speed_label.config(text="0 B/s")

            # 检查任务是否已完成（状态为100）或失败（状态为-11），如果是则清空相关信息
            if (hasattr(self.pipeline_manager, 'current_task_status') and
                self.pipeline_manager.current_task_status in [-11, 100] and
                hasattr(self.pipeline_manager, 'last_task_clearance') and
                self.pipeline_manager.last_task_clearance != self.pipeline_manager.current_task_status):

                # 记录已处理过此状态，避免重复清理
                self.pipeline_manager.last_task_clearance = self.pipeline_manager.current_task_status

                # 清空任务相关信息
                self.current_task_label.config(text="空闲")
                self.task_status_label.config(text="空闲")

                # 清空下载信息
                self.download_filename_label.config(text="无")
                self.download_progress['value'] = 0
                self.download_speed_label.config(text="0 B/s")

                # 清空上传信息
                self.upload_filename_label.config(text="无")
                self.upload_progress['value'] = 0
                self.upload_speed_label.config(text="0 B/s")

                # 重置pipeline_manager中的相关变量
                if hasattr(self.pipeline_manager, 'task_id'):
                    self.pipeline_manager.task_id = None
                if hasattr(self.pipeline_manager, 'current_task_status'):
                    self.pipeline_manager.current_task_status = 0  # 等待处理状态
                if hasattr(self.pipeline_manager, 'last_download_filename'):
                    self.pipeline_manager.last_download_filename = None
                if hasattr(self.pipeline_manager, 'last_upload_filename'):
                    self.pipeline_manager.last_upload_filename = None

        except Exception as e:
            # 静默更新UI错误
            pass

    @staticmethod
    def format_speed(bytes_per_second: float) -> str:
        """格式化速度显示"""
        if bytes_per_second < 1024:
            return f"{bytes_per_second:.2f} B/s"
        elif bytes_per_second < 1024 * 1024:
            return f"{bytes_per_second / 1024:.2f} KB/s"
        elif bytes_per_second < 1024 * 1024 * 1024:
            return f"{bytes_per_second / (1024 * 1024):.2f} MB/s"
        else:
            return f"{bytes_per_second / (1024 * 1024 * 1024):.2f} GB/s"

    def show(self):
        """显示窗口"""
        self.root.mainloop()

    def destroy(self):
        """销毁窗口"""
        # 停止管道
        if self.pipeline_manager:
            self.pipeline_manager.running = False

        # 停止日志处理
        gui_logger = get_gui_logger()
        gui_logger.stop_processing()
        
        self.root.destroy()


def show_restart_dialog():
    """显示重新启动对话框"""
    from port_singleton import PortBasedSingleton, is_instance_running
    import time

    result = messagebox.askyesno("程序已在运行", "检测到程序已在运行中！\n是否要关闭原有程序并重新启动？")

    if result:
        print("正在关闭原有程序...")

        # 检查是否仍有实例在运行，如果有则尝试终止
        if is_instance_running():
            # 创建单实例控制器并尝试终止已有进程
            instance = PortBasedSingleton()
            if instance.terminate_existing():
                print("原有程序已关闭")
                # 等待一下确保进程完全关闭和端口释放
                time.sleep(5)
                return True
            else:
                print("无法关闭原有程序")
                return False
        else:
            print("没有检测到运行中的实例，可能已经关闭")
            return True
    return False


def find_config_file(default_config_path: str = "app_config.ini"):
    """查找配置文件，尝试多个可能的位置"""
    import os

    # 首先尝试默认路径
    if os.path.exists(default_config_path):
        return default_config_path

    # 尝试在当前工作目录的父目录中查找
    parent_dir_config = os.path.join("..", default_config_path)
    if os.path.exists(parent_dir_config):
        return parent_dir_config

    # 尝试在脚本所在目录查找
    script_dir = os.path.dirname(os.path.abspath(__file__))
    script_dir_config = os.path.join(script_dir, default_config_path)
    if os.path.exists(script_dir_config):
        return script_dir_config

    # 尝试在脚本所在目录的父目录查找
    script_parent_config = os.path.join(script_dir, "..", default_config_path)
    if os.path.exists(script_parent_config):
        return script_parent_config

    # 如果在.app包中运行，尝试在.app包同级目录查找
    import sys
    if getattr(sys, 'frozen', False):
        # 运行在打包环境中
        app_dir = os.path.dirname(sys.executable)
        app_parent_dir = os.path.dirname(app_dir)
        app_sibling_config = os.path.join(app_parent_dir, default_config_path)
        if os.path.exists(app_sibling_config):
            return app_sibling_config

    # 如果都找不到，返回默认路径，让后续代码处理
    return default_config_path


def main(config_path: str = "app_config.ini"):
    """主函数"""
    # 查找配置文件的实际位置
    actual_config_path = find_config_file(config_path)

    # 检查是否已有实例运行
    if is_instance_running():
        # 显示重新启动对话框
        if show_restart_dialog():
            # 等待一段时间确保旧进程完全退出
            import time
            time.sleep(3)

            # 重新获取锁实例
            from port_singleton import check_single_instance
            import time
            max_retries = 5
            retry_count = 0

            while retry_count < max_retries:
                instance = check_single_instance()
                if instance:
                    try:
                        # 设置GUI日志
                        gui_logger = get_gui_logger()
                        gui_logger.start_processing()

                        # 创建主窗口
                        window = MainWindow(actual_config_path)

                        # 确保在退出时释放锁
                        def on_closing():
                            instance.release()
                            window.destroy()

                        window.root.protocol("WM_DELETE_WINDOW", on_closing)

                        window.show()
                        return
                    except Exception as e:
                        print(f"启动GUI时发生错误: {e}")
                        instance.release()
                        break
                else:
                    retry_count += 1
                    print(f"无法获取单实例锁，第 {retry_count}/{max_retries} 次重试...")
                    time.sleep(1)  # 等待1秒后重试

            if retry_count >= max_retries:
                print("经过多次尝试后仍无法获取单实例锁，程序退出")
                sys.exit(1)
        else:
            print("用户取消启动")
            sys.exit(0)
    else:
        # 没有已有实例，直接启动
        # 设置GUI日志
        gui_logger = get_gui_logger()
        gui_logger.start_processing()

        # 创建主窗口
        window = MainWindow(actual_config_path)

        # 确保在退出时释放锁
        from port_singleton import check_single_instance
        instance = check_single_instance()
        if instance:
            def on_closing():
                instance.release()
                window.destroy()

            window.root.protocol("WM_DELETE_WINDOW", on_closing)

        window.show()


if __name__ == '__main__':
    main()