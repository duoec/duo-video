#!/usr/bin/env python3
"""
GUI日志模块
扩展原有的logger模块，支持将日志推送到GUI界面
"""

import logging
import queue
import threading
from typing import Optional, Callable


class GUILogger:
    """
    GUI日志控制器
    负责收集日志并推送到GUI界面
    """

    _instance = None
    _lock = threading.Lock()

    def __new__(cls):
        """单例模式"""
        with cls._lock:
            if cls._instance is None:
                cls._instance = super().__new__(cls)
        return cls._instance

    def __init__(self):
        """初始化GUI日志器"""
        if not hasattr(self, 'initialized'):
            self.log_queue = queue.Queue()
            self.gui_callback = None
            self.running = False
            self.initialized = True

    def set_gui_callback(self, callback: Callable[[str], None]):
        """
        设置GUI回调函数

        :param callback: 回调函数，接收日志消息作为参数
        """
        self.gui_callback = callback

    def add_log(self, message: str):
        """
        添加日志到队列

        :param message: 日志消息
        """
        self.log_queue.put(message)

    def start_processing(self):
        """开始处理日志队列"""
        if not self.running:
            self.running = True
            self.process_thread = threading.Thread(target=self._process_logs, daemon=True)
            self.process_thread.start()

    def stop_processing(self):
        """停止处理日志队列"""
        self.running = False

    def _process_logs(self):
        """处理日志队列的线程函数"""
        while self.running:
            try:
                # 从队列获取日志（阻塞1秒）
                log_message = self.log_queue.get(timeout=1)
                if self.gui_callback:
                    self.gui_callback(log_message)
            except queue.Empty:
                # 队列为空，继续循环
                continue
            except Exception as e:
                # 处理异常，避免线程崩溃
                print(f"处理日志时发生错误: {e}")


# 创建全局GUI日志器实例
gui_logger = GUILogger()


def setup_gui_logging(callback: Callable[[str], None]):
    """
    设置GUI日志回调

    :param callback: GUI日志回调函数
    """
    gui_logger.set_gui_callback(callback)
    gui_logger.start_processing()


# 修改原有的logger配置，使其同时输出到控制台和GUI
def setup_logger():
    """设置日志器"""
    from logger import logger, set_gui_callback

    # 设置GUI回调函数
    def gui_callback(message):
        gui_logger.add_log(message)

    set_gui_callback(gui_callback)

    return logger


def get_gui_logger() -> GUILogger:
    """获取GUI日志器实例"""
    return gui_logger


if __name__ == '__main__':
    # 测试代码
    def test_callback(message):
        print(f"GUI Log: {message}")

    setup_gui_logging(test_callback)
    logger = setup_logger()

    import time
    for i in range(5):
        logger.info(f"测试日志 {i}")
        time.sleep(1)

    gui_logger.stop_processing()
