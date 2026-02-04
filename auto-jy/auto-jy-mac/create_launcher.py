#!/usr/bin/env python3
"""
创建macOS应用启动器
此脚本将创建一个完整的可发布的应用包
"""

import os
import shutil
from pathlib import Path

def create_app_bundle():
    """创建完整的应用包"""
    # 源应用位置
    source_app = Path("dist/自动化视频处理系统.app")
    
    if not source_app.exists():
        print("错误：找不到源应用包")
        return False
    
    # 目标发布目录
    release_dir = Path("release")
    release_dir.mkdir(exist_ok=True)
    
    # 复制应用到发布目录
    target_app = release_dir / "自动化视频处理系统.app"
    if target_app.exists():
        shutil.rmtree(target_app)
    
    shutil.copytree(source_app, target_app)
    
    # 复制配置文件和说明文档
    config_file = Path("app_config.ini")
    readme_file = Path("README.md")
    
    if config_file.exists():
        shutil.copy2(config_file, release_dir / "app_config.ini")
        
    if readme_file.exists():
        shutil.copy2(readme_file, release_dir / "README.md")
    
    print(f"应用已创建在: {target_app}")
    print(f"发布包位置: {release_dir.absolute()}")
    print("\n使用说明:")
    print("1. 将 '自动化视频处理系统.app' 拖拽到 'Applications' 文件夹")
    print("2. 首次运行时可能需要在 '系统偏好设置' > '安全性与隐私' 中允许运行")
    print("3. 运行应用前请确保已配置好 'app_config.ini' 文件")
    
    return True

if __name__ == "__main__":
    create_app_bundle()