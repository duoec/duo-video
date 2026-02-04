#!/bin/bash

# 自动化视频处理系统 - 打包脚本
# 用于将Python项目打包为独立的macOS应用程序

set -e  # 遇到错误时退出

echo "==========================================="
echo "自动化视频处理系统 - 打包脚本"
echo "==========================================="

# 检查必要文件是否存在
REQUIRED_FILES=("main_gui.py" "app_config.ini" "README.md")
for file in "${REQUIRED_FILES[@]}"; do
    if [[ ! -f "$file" ]]; then
        echo "错误: 找不到必要文件 $file"
        exit 1
    fi
done

echo "必要文件检查通过"

# 检查Python环境
PYTHON_PATH="/opt/anaconda3/envs/python3_12/bin/python"
if [[ ! -x "$PYTHON_PATH" ]]; then
    echo "错误: 找不到Python解释器 $PYTHON_PATH"
    exit 1
fi

echo "Python环境检查通过: $PYTHON_PATH"

# 检查PyInstaller是否已安装
if ! $PYTHON_PATH -c "import PyInstaller" &>/dev/null; then
    echo "PyInstaller未安装，正在安装..."
    $PYTHON_PATH -m pip install pyinstaller
fi

echo "PyInstaller检查通过"

# 清理旧的构建文件
echo "清理旧的构建文件..."
rm -rf build/ dist/

# 执行构建
echo "开始构建应用程序..."
$PYTHON_PATH -m PyInstaller \
  --onefile \
  --windowed \
  --add-data "app_config.ini:." \
  --add-data "README.md:." \
  --hidden-import=PIL \
  --hidden-import=keyboard \
  --hidden-import=appscript \
  --hidden-import=pyobjc_framework_Quartz \
  --exclude-module=PyQt5 \
  --exclude-module=PySide2 \
  --exclude-module=PySide6 \
  main_gui.py

# 检查构建是否成功
if [[ $? -ne 0 ]]; then
    echo "错误: 构建失败！"
    exit 1
fi

echo "构建成功！"

# 创建发布目录
echo "创建发布目录..."
rm -rf release/
mkdir -p release/

# 移动并重命名应用
SOURCE_APP="dist/main_gui.app"
TARGET_APP="release/自动化视频处理系统.app"

if [[ -d "$SOURCE_APP" ]]; then
    mv "$SOURCE_APP" "$TARGET_APP"
    echo "应用已移动到: $TARGET_APP"
else
    echo "错误: 找不到构建输出的应用程序"
    exit 1
fi

# 复制配置文件和文档
cp app_config.ini README.md 发布说明.md release/

echo "配置文件和文档已复制到发布目录"

# 验证发布目录
RELEASE_CONTENTS=$(ls -la release/)
echo ""
echo "发布目录内容:"
echo "$RELEASE_CONTENTS"

echo ""
echo "==========================================="
echo "构建完成！"
echo "发布包位置: $(pwd)/release/"
echo ""
echo "使用说明:"
echo "1. 将 '自动化视频处理系统.app' 拖拽到 'Applications' 文件夹"
echo "2. 首次运行时可能需要在 '系统偏好设置' > '安全性与隐私' 中允许运行"
echo "3. 运行应用前请确保已配置好 'app_config.ini' 文件"
echo "==========================================="