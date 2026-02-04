#!/bin/bash
# 自动化视频处理系统 GUI 启动脚本

# 获取脚本所在目录
DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" &> /dev/null && pwd )"

# 切换到脚本目录
cd "$DIR"

# Conda环境的Python路径
CONDA_PYTHON="/opt/anaconda3/envs/python3_12/bin/python"
PIP_PATH="/opt/anaconda3/envs/python3_12/bin/pip"

# 检查conda Python是否可用
if [ ! -f "$CONDA_PYTHON" ]; then
    echo "错误：未找到conda Python3.12环境"
    echo "请确保已创建conda环境：conda create -n python3_12 python=3.12"
    exit 1
fi

# 检查PyQt6是否安装
$CONDA_PYTHON -c "from PyQt6.QtWidgets import QApplication" 2>/dev/null
if [ $? -ne 0 ]; then
    echo "正在安装PyQt6..."
    $PIP_PATH install PyQt6
    if [ $? -ne 0 ]; then
        echo "错误：PyQt6安装失败"
        exit 1
    fi
fi

# 启动GUI程序
echo "启动自动化视频处理系统 GUI..."
$CONDA_PYTHON main_gui.py "$@"
