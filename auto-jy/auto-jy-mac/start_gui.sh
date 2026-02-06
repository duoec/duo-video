#!/bin/bash
# 自动化视频处理系统 GUI 启动脚本

# 获取脚本所在目录
DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" &> /dev/null && pwd )"

# 切换到脚本目录
cd "$DIR"

# Conda环境的Python路径
CONDA_PYTHON="/opt/anaconda3/bin/python3"

# 检查conda Python是否可用
if [ ! -f "$CONDA_PYTHON" ]; then
    echo "错误：未找到conda Python3环境"
    echo "请确保已安装anaconda并配置了Python环境"
    exit 1
fi

# 检查依赖是否安装
echo "检查依赖包..."
$CONDA_PYTHON -c "import tkinter" 2>/dev/null
if [ $? -ne 0 ]; then
    echo "错误：tkinter未安装或不可用"
    exit 1
fi

# 启动GUI程序
echo "启动自动化视频处理系统 GUI..."
$CONDA_PYTHON main_gui.py "$@"
