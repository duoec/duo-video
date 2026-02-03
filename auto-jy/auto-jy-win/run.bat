@echo off
chcp 65001 >nul
setlocal enabledelayedexpansion

:: 剪映自动导出工具启动脚本 (Windows版本)

:: 检查是否提供了配置文件参数
if "%~1"=="" (
    echo 使用方法: %~nx0 [config_file] [target_directory_to_delete]
    echo 示例: %~nx0 app_config.ini "C:\path\to\directory\to\delete"
    echo 使用默认配置...
    set "CONFIG_FILE=app_config.ini"
) else (
    set "CONFIG_FILE=%~1"
)

set "TARGET_DIR=%~2"

:: 检查配置文件是否存在
if not exist "%CONFIG_FILE%" (
    echo 错误: 配置文件 '%CONFIG_FILE%' 不存在
    exit /b 1
)

echo 使用配置文件: %CONFIG_FILE%
echo 目标删除目录: %TARGET_DIR%

:: 尝试使用python命令
python --version >nul 2>&1
if %errorlevel% equ 0 (
    set "PYTHON_CMD=python"
    goto :run
)

:: 尝试使用py命令
py --version >nul 2>&1
if %errorlevel% equ 0 (
    set "PYTHON_CMD=py"
    goto :run
)

echo 错误: 未找到Python解释器，请确保已安装Python并添加到PATH
exit /b 1

:run
:: 运行主程序
echo 启动剪映自动导出工具...
%PYTHON_CMD% pipeline_manager.py "%CONFIG_FILE%" "%TARGET_DIR%"

set EXIT_CODE=%errorlevel%

if %EXIT_CODE% equ 0 (
    echo 剪映自动导出工具执行成功！
) else (
    echo 剪映自动导出工具执行失败，退出码: !EXIT_CODE!
)

exit /b %EXIT_CODE%
