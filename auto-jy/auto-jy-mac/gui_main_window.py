#!/usr/bin/env python3
"""
主GUI窗口
提供自动化管道管理系统的图形界面
"""

import sys
import threading
from datetime import datetime
from PyQt6.QtWidgets import (QApplication, QMainWindow, QWidget, QVBoxLayout,
                              QHBoxLayout, QLabel, QPushButton, QTextEdit,
                              QProgressBar, QFrame, QGridLayout)
from PyQt6.QtCore import QTimer, Qt, pyqtSignal
from PyQt6.QtGui import QFont, QPalette, QColor

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


class LogDisplay(QTextEdit):
    """自定义日志显示组件"""

    def __init__(self):
        super().__init__()
        self.setReadOnly(True)
        # 注意：PyQt6 中 QTextEdit 没有 setMaximumBlockCount 方法
        # 我们设置最大块数属性，后续在 append_log 中手动限制
        self.max_blocks = 1000
        # 使用PyQt6默认样式，仅设置基本属性
        self.setStyleSheet("""
            QTextEdit {
                font-family: 'Consolas', 'Monaco', 'Courier New', monospace;
                font-size: 12px;
            }
        """)

    def append_log(self, message: str):
        """添加日志消息"""
        # 格式化时间戳
        timestamp = datetime.now().strftime("%H:%M:%S")
        formatted_message = f"[{timestamp}] {message}"

        # 添加到文本框
        self.append(formatted_message)

        # 限制最大块数（手动实现类似 setMaximumBlockCount 的功能）
        while self.document().blockCount() > self.max_blocks:
            cursor = self.textCursor()
            cursor.movePosition(cursor.MoveOperation.Start)
            cursor.movePosition(cursor.MoveOperation.EndOfBlock, cursor.MoveMode.KeepAnchor)
            cursor.removeSelectedText()


class MainWindow(QMainWindow):
    """主窗口类"""

    log_received = pyqtSignal(str)  # 日志接收信号

    def __init__(self, config_path: str = "app_config.ini"):
        super().__init__()
        self.config_path = config_path
        self.pipeline_manager = None
        self.is_running = False
        self.update_timer = QTimer()

        # 设置窗口属性
        self.setWindowTitle("自动化视频处理系统")
        self.setMinimumSize(1000, 700)

        # 创建UI
        self.create_ui()

        # 连接信号
        self.log_received.connect(self.log_display.append_log)

        # 初始化管道管理器
        self.init_pipeline_manager()

        # 设置定时器
        self.update_timer.timeout.connect(self.update_ui)
        self.update_timer.start(1000)  # 每秒更新一次UI

        # 自动启动服务
        self.start_pipeline_service()

    def create_ui(self):
        """创建用户界面"""
        # 创建中央部件
        central_widget = QWidget()
        self.setCentralWidget(central_widget)

        # 使用PyQt6默认样式
        pass

        # 创建主布局（水平两栏）
        main_layout = QHBoxLayout(central_widget)
        main_layout.setContentsMargins(10, 10, 10, 10)
        main_layout.setSpacing(10)

        # ==================== 左栏：操作界面 ====================
        left_panel = self.create_left_panel()
        left_panel.setMaximumWidth(400)
        left_panel.setMinimumWidth(350)
        main_layout.addWidget(left_panel)

        # 分隔线
        separator = QFrame()
        separator.setFrameShape(QFrame.Shape.VLine)
        separator.setFrameShadow(QFrame.Shadow.Sunken)
        pass  # 使用默认样式
        main_layout.addWidget(separator)

        # ==================== 右栏：日志显示 ====================
        right_panel = self.create_right_panel()
        main_layout.addWidget(right_panel)

    def create_left_panel(self) -> QWidget:
        """创建左栏操作面板"""
        panel = QWidget()
        layout = QVBoxLayout(panel)

        # 设置面板样式 - 使用PyQt6默认样式
        pass

        # 1. 服务器信息
        server_frame = QFrame()
        server_frame.setFrameStyle(QFrame.Shape.StyledPanel)
        server_layout = QGridLayout(server_frame)

        server_layout.addWidget(QLabel("服务器地址:"), 0, 0)
        self.server_url_label = QLabel("未配置")
        pass  # 使用默认样式
        server_layout.addWidget(self.server_url_label, 0, 1)

        layout.addWidget(server_frame)
        layout.addSpacing(10)

        # 2. 操作按钮
        button_frame = QFrame()
        button_frame.setFrameStyle(QFrame.Shape.StyledPanel)
        button_layout = QVBoxLayout(button_frame)

        self.toggle_button = QPushButton("启动")
        self.toggle_button.setMinimumHeight(50)
        self.toggle_button.setStyleSheet("""
            QPushButton {
                background-color: #4CAF50;
                color: white;
                border: none;
                border-radius: 5px;
                font-size: 16px;
                font-weight: bold;
            }
            QPushButton:hover {
                background-color: #45a049;
            }
            QPushButton:pressed {
                background-color: #3d8b40;
            }
        """)
        self.toggle_button.clicked.connect(self.toggle_pipeline)

        button_layout.addWidget(self.toggle_button)
        layout.addWidget(button_frame)
        layout.addSpacing(10)

        # 3. 任务状态
        task_frame = QFrame()
        task_frame.setFrameStyle(QFrame.Shape.StyledPanel)
        task_layout = QGridLayout(task_frame)

        task_layout.addWidget(QLabel("当前任务:"), 0, 0)
        self.current_task_label = QLabel("空闲")
        pass  # 使用默认样式
        task_layout.addWidget(self.current_task_label, 0, 1)

        task_layout.addWidget(QLabel("任务状态:"), 1, 0)
        self.task_status_label = QLabel("空闲")
        pass  # 使用默认样式
        task_layout.addWidget(self.task_status_label, 1, 1)

        layout.addWidget(task_frame)
        layout.addSpacing(10)

        # 4. 下载进度
        download_frame = QFrame()
        download_frame.setFrameStyle(QFrame.Shape.StyledPanel)
        download_layout = QGridLayout(download_frame)

        download_layout.addWidget(QLabel("下载文件:"), 0, 0)
        self.download_filename_label = QLabel("无")
        pass  # 使用默认样式
        download_layout.addWidget(self.download_filename_label, 0, 1)

        download_layout.addWidget(QLabel("下载进度:"), 1, 0)
        self.download_progress = QProgressBar()
        self.download_progress.setMinimumHeight(25)
        download_layout.addWidget(self.download_progress, 1, 1)

        download_layout.addWidget(QLabel("下载速度:"), 2, 0)
        self.download_speed_label = QLabel("0 B/s")
        pass  # 使用默认样式
        download_layout.addWidget(self.download_speed_label, 2, 1)

        layout.addWidget(download_frame)
        layout.addSpacing(10)

        # 5. 上传进度
        upload_frame = QFrame()
        upload_frame.setFrameStyle(QFrame.Shape.StyledPanel)
        upload_layout = QGridLayout(upload_frame)

        upload_layout.addWidget(QLabel("上传文件:"), 0, 0)
        self.upload_filename_label = QLabel("无")
        pass  # 使用默认样式
        upload_layout.addWidget(self.upload_filename_label, 0, 1)

        upload_layout.addWidget(QLabel("上传进度:"), 1, 0)
        self.upload_progress = QProgressBar()
        self.upload_progress.setMinimumHeight(25)
        upload_layout.addWidget(self.upload_progress, 1, 1)

        upload_layout.addWidget(QLabel("上传速度:"), 2, 0)
        self.upload_speed_label = QLabel("0 B/s")
        pass  # 使用默认样式
        upload_layout.addWidget(self.upload_speed_label, 2, 1)

        layout.addWidget(upload_frame)

        # 添加弹性空间
        layout.addStretch()

        return panel

    def create_right_panel(self) -> QWidget:
        """创建右栏日志面板"""
        panel = QWidget()
        layout = QVBoxLayout(panel)

        # 标题
        title_label = QLabel("系统日志")
        title_label.setStyleSheet("""
            font-size: 18px;
            font-weight: bold;
            padding: 5px;
        """)
        layout.addWidget(title_label)

        # 日志显示区域
        self.log_display = LogDisplay()
        layout.addWidget(self.log_display)

        return panel

    def init_pipeline_manager(self):
        """初始化管道管理器"""
        try:
            self.pipeline_manager = PipelineManager(self.config_path)

            # 从配置中获取服务器地址
            server_url = self.pipeline_manager.config.get('api_base_url', '未配置')
            self.server_url_label.setText(server_url)

            # 设置日志回调
            from gui_logger import setup_gui_logging, setup_logger
            setup_gui_logging(self.log_received.emit)
            setup_logger()

        except Exception as e:
            self.log_received.emit(f"初始化失败: {e}")

    def toggle_pipeline(self):
        """切换管道运行状态"""
        if not self.pipeline_manager:
            return

        if self.is_running:
            # 当前是运行状态，点击后暂停
            self.pipeline_manager.paused = True
            self.is_running = False
            self.toggle_button.setText("启动")
            self.toggle_button.setStyleSheet("""
                QPushButton {
                    background-color: #4CAF50;
                    color: white;
                    border: none;
                    border-radius: 5px;
                    font-size: 16px;
                    font-weight: bold;
                }
                QPushButton:hover {
                    background-color: #45a049;
                }
            """)
            self.log_received.emit("系统已暂停")
        else:
            # 当前是暂停状态，点击后启动
            self.pipeline_manager.paused = False
            self.is_running = True
            self.toggle_button.setText("暂停")
            self.toggle_button.setStyleSheet("""
                QPushButton {
                    background-color: #f44336;
                    color: white;
                    border: none;
                    border-radius: 5px;
                    font-size: 16px;
                    font-weight: bold;
                }
                QPushButton:hover {
                    background-color: #da190b;
                }
            """)
            self.log_received.emit("系统已启动")

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
            self.toggle_button.setText("暂停")
            self.toggle_button.setStyleSheet("""
                QPushButton {
                    background-color: #f44336;
                    color: white;
                    border: none;
                    border-radius: 5px;
                    font-size: 16px;
                    font-weight: bold;
                }
                QPushButton:hover {
                    background-color: #da190b;
                }
            """)
            self.log_received.emit("系统已启动")

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

    def update_ui(self):
        """更新UI显示"""
        if not self.pipeline_manager:
            return

        try:
            # 更新任务状态
            if hasattr(self.pipeline_manager, 'task_id') and self.pipeline_manager.task_id:
                self.current_task_label.setText(str(self.pipeline_manager.task_id))
            else:
                self.current_task_label.setText("空闲")

            # 更新下载进度
            if hasattr(self.pipeline_manager, 'last_download_filename'):
                if self.pipeline_manager.last_download_filename:
                    self.download_filename_label.setText(self.pipeline_manager.last_download_filename)
                    self.download_progress.setValue(int(self.pipeline_manager.last_download_percent))

                    # 格式化速度
                    speed = self.format_speed(self.pipeline_manager.last_download_speed)
                    self.download_speed_label.setText(speed)

            # 更新上传进度
            if hasattr(self.pipeline_manager, 'last_upload_filename'):
                if self.pipeline_manager.last_upload_filename:
                    self.upload_filename_label.setText(self.pipeline_manager.last_upload_filename)
                    self.upload_progress.setValue(int(self.pipeline_manager.last_upload_percent))

                    # 格式化速度
                    speed = self.format_speed(self.pipeline_manager.last_upload_speed)
                    self.upload_speed_label.setText(speed)

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

    def closeEvent(self, event):
        """窗口关闭事件"""
        # 停止管道
        if self.pipeline_manager:
            self.pipeline_manager.running = False

        # 停止日志处理
        gui_logger = get_gui_logger()
        gui_logger.stop_processing()

        event.accept()


def show_restart_dialog():
    """显示重新启动对话框"""
    from PyQt6.QtWidgets import QMessageBox
    from port_singleton import PortBasedSingleton, is_instance_running
    import time

    app = QApplication.instance()

    msg_box = QMessageBox()
    msg_box.setIcon(QMessageBox.Icon.Warning)
    msg_box.setWindowTitle("程序已在运行")
    msg_box.setText("检测到程序已在运行中！")
    msg_box.setInformativeText("是否要关闭原有程序并重新启动？")

    # 添加按钮
    restart_button = msg_box.addButton("重新启动", QMessageBox.ButtonRole.AcceptRole)
    cancel_button = msg_box.addButton("取消", QMessageBox.ButtonRole.RejectRole)
    msg_box.setDefaultButton(restart_button)

    # 显示对话框
    msg_box.exec()

    if msg_box.clickedButton() == restart_button:
        print("正在关闭原有程序...")

        # 检查是否仍有实例在运行，如果有则尝试终止
        if is_instance_running():
            # 创建单实例控制器并尝试终止已有进程
            instance = PortBasedSingleton()
            if instance.terminate_existing():
                print("原有程序已关闭")
                # 等待一下确保进程完全关闭
                time.sleep(3)
                return True
            else:
                print("无法关闭原有程序")
                return False
        else:
            print("没有检测到运行中的实例，可能已经关闭")
            return True
    return False


def main(config_path: str = "app_config.ini"):
    """主函数"""
    # 创建QApplication
    app = QApplication(sys.argv)
    app.setStyle('Fusion')  # 使用现代样式

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
                        window = MainWindow(config_path)
                        window.show()

                        # 运行应用程序
                        sys.exit(app.exec())
                    finally:
                        instance.release()
                    break  # 成功启动后退出循环
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
        window = MainWindow(config_path)
        window.show()

        # 运行应用程序
        sys.exit(app.exec())


if __name__ == '__main__':
    main()
