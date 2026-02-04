# -*- mode: python ; coding: utf-8 -*-
"""
PyInstaller spec file for auto-jy-mac application
"""

import sys
import os
from PyInstaller.utils.hooks import collect_data_files, collect_submodules

# 收集数据文件
datas = []
datas += collect_data_files('PyQt6')
# 检查文件是否存在后再添加
import os
if os.path.exists('./app_config.ini'):
    datas += [('./app_config.ini', '.')]  # 包含配置文件
if os.path.exists('./README.md'):
    datas += [('./README.md', '.')]       # 包含说明文档

# 收集所有子模块
hiddenimports = []
hiddenimports += collect_submodules('PyQt6')
hiddenimports += [
    'api_client',
    'download_extractor', 
    'gui_logger',
    'jianying_exporter',
    'logger',
    'pipeline_manager',
    'port_singleton',
    'upload_handler',
    'configparser',
    'requests',
    'oss2',
    'qcloud_cos_v5',
    'Pillow',
    'pyautogui',
    'pygetwindow',
    'keyboard',
    'psutil',
    'appscript',
    'pyobjc_framework_Quartz'
]

block_cipher = None

a = Analysis(
    ['main_gui.py'],
    pathex=['.'],  # 当前目录
    binaries=[],
    datas=datas,
    hiddenimports=hiddenimports,
    hookspath=[],
    hooksconfig={},
    runtime_hooks=[],
    excludes=['PyQt5', 'PySide2', 'PySide6'],  # 排除其他Qt绑定包
    win_no_prefer_redirects=False,
    win_private_assemblies=False,
    cipher=block_cipher,
    noarchive=False,
)

pyz = PYZ(a.pure, a.zipped_data, cipher=block_cipher)

exe = EXE(
    pyz,
    a.scripts,
    a.binaries,
    a.zipfiles,
    a.datas,
    [],
    name='auto-jy-mac',
    debug=False,
    bootloader_ignore_signals=False,
    strip=False,
    upx=True,
    upx_exclude=[],
    runtime_tmpdir=None,
    console=False,  # 设置为False以隐藏控制台窗口
    disable_windowed_traceback=False,
    argv_emulation=True,  # macOS特定选项，启用命令行参数模拟
    target_arch=None,
    codesign_identity=None,
    entitlements_file=None,
    icon=None,  # 可以在这里指定图标文件路径
)

coll = COLLECT(
    exe,
    a.binaries,
    a.zipfiles,
    a.datas,
    strip=False,
    upx=True,
    upx_exclude=[],
    name='auto-jy-mac',
)