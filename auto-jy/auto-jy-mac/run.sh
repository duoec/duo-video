#!/bin/bash

# 剪映自动导出工具启动脚本

# 检查是否提供了配置文件参数
if [ $# -eq 0 ]; then
    echo "使用方法: $0 [config_file] [target_directory_to_delete]"
    echo "示例: $0 app_config.ini \"/path/to/directory/to/delete\""
    echo "使用默认配置..."
    CONFIG_FILE="app_config.ini"
else
    CONFIG_FILE=$1
fi

TARGET_DIR=$2

# 检查配置文件是否存在
if [ ! -f "$CONFIG_FILE" ]; then
    echo "错误: 配置文件 '$CONFIG_FILE' 不存在"
    exit 1
fi

echo "使用配置文件: $CONFIG_FILE"
echo "目标删除目录: ${TARGET_DIR:-未指定}"

# 运行主程序
echo "启动剪映自动导出工具..."
/opt/anaconda3/envs/python3_12/bin/python main.py "$CONFIG_FILE" "$TARGET_DIR"

EXIT_CODE=$?

if [ $EXIT_CODE -eq 0 ]; then
    echo "剪映自动导出工具执行成功！"
else
    echo "剪映自动导出工具执行失败，退出码: $EXIT_CODE"
fi

exit $EXIT_CODE