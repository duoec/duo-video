#!/bin/bash
# duo-video 应用程序打包脚本

echo "开始打包 duo-video 应用程序..."

# 获取脚本所在目录
SCRIPT_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" &> /dev/null && pwd )"

# 进入项目目录
cd "$SCRIPT_DIR"

# 清理之前的构建文件
echo "清理之前的构建文件..."
rm -rf dist build *.spec

# 执行打包命令
echo "开始打包应用程序..."
pyinstaller --onefile --windowed --add-data "app_config.ini:." --add-data "README.md:." --add-data "logo_192.png:." --icon="logo_192.png" --name "duo-video" main_gui.py

# 检查打包是否成功
if [ $? -eq 0 ]; then
    echo "应用程序打包成功！"
    
    # 创建部署目录
    DEPLOY_DIR="$SCRIPT_DIR/deploy"
    mkdir -p "$DEPLOY_DIR"
    
    # 复制应用程序到部署目录
    cp -r "dist/duo-video.app" "$DEPLOY_DIR/"
    cp "app_config.ini" "$DEPLOY_DIR/"
    
    # 创建使用说明
    cat > "$DEPLOY_DIR/使用说明.txt" << EOF
duo-video 应用程序使用说明
========================

1. 应用程序结构
----------------
- duo-video.app - 主应用程序
- app_config.ini - 配置文件（需要与应用程序放在同一目录下）

2. 首次使用
------------
- 将 app_config.ini 配置文件复制到与 duo-video.app 相同的目录下
- 双击 duo-video.app 启动应用程序

3. 配置文件说明
----------------
app_config.ini 包含以下配置项：
- [api] - API服务器配置
- [jianying] - 剪映软件路径配置
- [upload] - 上传方式配置
- [oss/cos] - 云存储配置

4. 注意事项
------------
- 确保配置文件路径正确
- 应用程序需要访问网络和文件系统权限
- 首次运行可能需要在系统偏好设置中允许应用运行

5. 故障排除
------------
- 如果应用程序无法启动，请检查配置文件格式
- 确保有足够的磁盘空间
- 检查网络连接是否正常
EOF
    
    echo "部署包已创建在: $DEPLOY_DIR"
    echo "打包完成！"
else
    echo "打包失败，请检查错误信息。"
    exit 1
fi