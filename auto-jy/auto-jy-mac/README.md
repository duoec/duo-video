# duo-video - 自动化视频处理系统

一个基于Python的自动化视频处理系统，能够自动检测API任务、下载资源、导出视频并上传至云端。

## 功能特性

- **自动化管道管理**：自动检测API接口任务，执行完整的视频处理流程
- **多阶段处理**：支持下载、解压、导出、上传等多个处理阶段
- **实时监控**：提供图形界面，实时显示任务状态、下载/上传进度
- **跨平台支持**：基于Python开发，支持macOS系统
- **单实例控制**：防止重复启动，确保系统稳定运行

## 技术架构

- **GUI框架**：tkinter（轻量级Python GUI库）
- **核心语言**：Python 3.12+
- **系统交互**：AppleScript（macOS应用控制）、psutil（进程管理）
- **网络通信**：requests（HTTP客户端）
- **云存储**：阿里云OSS、腾讯云COS

## 安装依赖

```bash
pip install -r requirements.txt
```

## 配置说明

系统使用 `app_config.ini` 配置文件，包含以下配置项：

### API配置
```ini
[api]
base_url = https://your-api-endpoint.com/api/task
interval = 30
```

### 剪映配置
```ini
[jianying]
app_path = /Applications/JianyingPro.app
drafts_path = ~/Movies/JianyingPro/Drafts
exports_path = ~/Downloads
```

### 上传配置
```ini
[upload]
storage = oss
video_key_prefix = videos
```

### 云存储配置（根据选择的存储类型配置）
```ini
# 阿里云OSS
[oss]
bucket_name = your-bucket-name
access_key_id = your-access-key-id
access_key_secret = your-access-key-secret
endpoint = https://oss-cn-hangzhou.aliyuncs.com
region = cn-hangzhou

# 或者腾讯云COS
[cos]
bucket_name = your-bucket-name.cos.ap-shanghai.myqcloud.com
secret_id = your-secret-id
secret_key = your-secret-key
region = ap-shanghai
```

## 使用方法

### 启动GUI界面
```bash
python main_gui.py [config_path]
```

### 直接运行（无界面）
```bash
python pipeline_manager.py [config_path]
```

## 系统流程

1. **任务检测**：定时轮询API接口，获取待处理任务
2. **资源下载**：下载任务相关的剪映工程压缩包
3. **工程处理**：解压并使用剪映软件导出视频
4. **视频上传**：将导出的视频上传至指定云存储
5. **状态上报**：向API接口上报任务处理结果
6. **资源清理**：清理临时文件和剪映草稿

## 界面说明

- **服务器地址**：显示当前连接的API服务器地址
- **启动/暂停按钮**：控制自动化流程的启停
- **当前任务**：显示当前正在处理的任务ID
- **任务状态**：显示当前任务的处理状态
- **下载进度**：显示资源下载的进度和速度
- **上传进度**：显示视频上传的进度和速度
- **系统日志**：实时显示系统运行日志

## 状态码说明

| 状态码 | 说明 |
|--------|------|
| -11 | 任务失败 |
| -10 | 主动取消 |
| 0 | 等待处理 |
| 10 | 任务已领取 |
| 11 | 下载完成 |
| 12 | 解压完成 |
| 13 | 打开工程 |
| 14 | 生成视频 |
| 100 | 导出完成 |

## 开发说明

### 项目结构
```
auto-jy-mac/
├── main_gui.py          # GUI主入口
├── tkinter_gui_main_window.py  # GUI界面实现
├── pipeline_manager.py  # 核心管道管理器
├── api_client.py        # API客户端
├── jianying_exporter.py # 剪映导出器
├── download_extractor.py # 下载解压器
├── upload_handler.py    # 上传处理器
├── gui_logger.py        # GUI日志系统
├── logger.py            # 日志记录器
├── port_singleton.py    # 单实例控制器
├── app_config.ini       # 配置文件模板
├── requirements.txt     # 依赖包列表
└── README.md           # 项目说明
```

### 扩展开发

如需扩展功能，请参考以下模块：
- `pipeline_manager.py`：核心业务逻辑
- `api_client.py`：API接口交互
- `upload_handler.py`：上传逻辑扩展

## 故障排除

1. **无法启动剪映**：检查 `app_path` 配置是否正确
2. **下载失败**：检查网络连接和API配置
3. **上传失败**：验证云存储配置信息
4. **权限问题**：确保应用有辅助功能权限

## 许可证

本项目仅供学习和内部使用。