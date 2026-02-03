import os
import threading
from datetime import datetime
from pathlib import Path


class Logger:
    """
    日志管理类，支持按天生成日志文件和GUI日志输出
    """
    def __init__(self):
        self.log_dir = Path("logs")
        self.log_dir.mkdir(exist_ok=True)
        
        # GUI日志回调函数，用于将日志输出到GUI界面
        self.gui_log_callback = None
        
        # 线程锁，确保日志写入线程安全
        self._lock = threading.Lock()
    
    def set_gui_callback(self, callback):
        """
        设置GUI日志回调函数
        :param callback: 接收日志消息的回调函数
        """
        self.gui_log_callback = callback
    
    def _get_log_filename(self):
        """获取当日日志文件名"""
        today = datetime.now().strftime("%Y%m%d")
        return self.log_dir / f"{today}.log"
    
    def _format_message(self, level, message):
        """格式化日志消息"""
        timestamp = datetime.now().strftime("%Y-%m-%d %H:%M:%S.%f")[:-3]  # 包含毫秒
        return f"[{timestamp}] {level}: {message}"
    
    def _write_log(self, level, message):
        """写入日志到文件"""
        formatted_msg = self._format_message(level, message)
        
        # 写入日志文件
        with self._lock:
            log_file = self._get_log_filename()
            with open(log_file, "a", encoding="utf-8") as f:
                f.write(formatted_msg + "\n")
        
        # 如果设置了GUI回调，则同时输出到GUI
        if self.gui_log_callback:
            self.gui_log_callback(formatted_msg)
    
    def info(self, message):
        """信息级别日志"""
        self._write_log("INFO", message)
    
    def debug(self, message):
        """调试级别日志"""
        self._write_log("DEBUG", message)
    
    def warning(self, message):
        """警告级别日志"""
        self._write_log("WARNING", message)
    
    def error(self, message):
        """错误级别日志"""
        self._write_log("ERROR", message)
    
    def critical(self, message):
        """严重错误级别日志"""
        self._write_log("CRITICAL", message)


# 创建全局日志实例
logger = Logger()

# 提供便捷的全局函数接口
def info(message):
    logger.info(message)

def debug(message):
    logger.debug(message)

def warning(message):
    logger.warning(message)

def error(message):
    logger.error(message)

def critical(message):
    logger.critical(message)

def set_gui_callback(callback):
    """设置GUI日志回调"""
    logger.set_gui_callback(callback)