#!/usr/bin/env python3
"""
主程序入口
启动GUI界面的自动化视频处理系统
"""

import sys
import os

# 添加当前目录到Python路径  
sys.path.insert(0, os.path.dirname(os.path.abspath(__file__)))

from tkinter_gui_main_window import main
from port_singleton import check_single_instance


def main_entry():
    """程序入口点"""
    # 从命令行参数获取配置文件路径
    config_path = sys.argv[1] if len(sys.argv) > 1 else "app_config.ini"

    # 启动GUI（GUI内部会处理单例逻辑）
    main(config_path)


if __name__ == '__main__':
    main_entry()
