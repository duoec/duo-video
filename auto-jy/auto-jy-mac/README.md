# 剪映自动导出系统 (Jianying Auto Export System)

[![Python Version](https://img.shields.io/badge/python-3.7+-blue.svg)](https://python.org)
[![Platform](https://img.shields.io/badge/platform-macOS-lightgrey.svg)](https://www.apple.com/macos/)
[![License](https://img.shields.io/badge/license-MIT-green.svg)](LICENSE)

一个功能完整的剪映（CapCut/Jianying）自动化视频处理系统，支持从任务拉取到云存储上传的全流程自动化。



## ✨ 功能特点

- 🔄 **自动任务处理**: 循环检测API接口，自动拉取和处理视频导出任务
- 📦 **资源管理**: 自动下载和解压剪映项目资源
- 🎬 **自动化导出**: 基于GUI自动化的剪映操作，无需人工干预
- ☁️ **多云存储**: 支持阿里云OSS和腾讯云COS上传
- 📊 **实时监控**: 完整的任务状态跟踪和日志记录
- 🧹 **自动清理**: 任务完成后自动清理临时文件和草稿
- 🎯 **高精度识别**: 基于图像识别的界面元素检测
- 🔧 **灵活配置**: 完整的配置文件系统，支持自定义参数

## 🏗️ 系统架构

本系统采用模块化设计，包含三个核心流水线：

```
┌─────────────┐
│  Pipeline 1 │  ← 任务检测：循环检测API接口，拉取待处理任务
└──────┬──────┘
       │
       ▼
┌─────────────┐
│  Pipeline 2 │  ← 资源处理：下载ZIP资源并解压到剪映草稿目录
└──────┬──────┘
       │
       ▼
┌─────────────┐
│  Pipeline 3 │  ← 自动化导出：通过GUI自动化操作剪映完成视频导出
└──────┬──────┘
       │
       ▼
┌─────────────┐
│  Pipeline 4 │  ← 云端上传：将导出的视频上传到云存储（OSS/COS）
└─────────────┘
```

## 📋 核心模块

| 模块 | 文件 | 功能描述 |
|------|------|----------|
| **Pipeline Manager** | `pipeline_manager.py` | 流水线管理系统，协调整个自动化流程 |
| **API Client** | `api_client.py` | HTTP API客户端，处理任务拉取和状态更新 |
| **Downloader** | `download_extractor.py` | 资源下载和解压模块 |
| **Auto Exporter** | `jianying_exporter.py` | 基于GUI自动化的剪映操作引擎 |
| **Upload Handler** | `upload_handler.py` | 多云存储上传处理器（OSS/COS） |
| **Logger** | `logger.py` | 日志管理系统，支持文件输出和GUI回调 |

## 🚀 快速开始

### 环境要求

- **操作系统**: macOS 10.15+
- **Python**: 3.7 或更高版本
- **剪映**: VideoFusion-macOS 或 CapCut for Mac
- **权限**: 屏幕录制权限（用于GUI自动化）

### 安装依赖

#### 方法一：使用pip安装（推荐）

```bash
pip install -r requirements.txt
```

#### 方法二：使用setup.py安装

```bash
pip install -e .
```

### 权限配置

**重要**: 本工具需要屏幕录制权限才能进行GUI自动化操作。

1. 打开 **系统偏好设置** → **安全性与隐私** → **隐私**
2. 在左侧列表中选择 **屏幕录制**
3. 点击锁图标进行解锁
4. 勾选您的Python应用或终端应用
5. 重启程序使权限生效



### 配置系统

复制并编辑配置文件：

```bash
cp app_config.ini my_config.ini
```

编辑 `my_config.ini` 文件：

```ini
[api]
base_url = http://your-api-server:17026
interval = 3

[jianying]
app_path = /Applications/VideoFusion-macOS.app
drafts_path = /Users/username/Movies/JianyingPro/User Data/Projects/com.lveditor.draft
exports_path = ~/Downloads/video

[upload]
storage = oss
video_key_prefix = videos/

[oss]
bucket_name = your-bucket-name
access_key_id = YOUR_ACCESS_KEY
access_key_secret = YOUR_SECRET_KEY
endpoint = oss-cn-hangzhou.aliyuncs.com
region = cn-hangzhou

[cos]
bucket_name = your-bucket-name
secret_id = YOUR_SECRET_ID
secret_key = YOUR_SECRET_KEY
region = ap-beijing
```

### 运行系统

#### 使用启动脚本（推荐）

```bash
./run.sh my_config.ini
```

#### 直接运行Python程序

```bash
python pipeline_manager.py my_config.ini
```

## 📁 项目结构

```
auto-jy-mac/
├── README.md                          # 项目说明文档
├── requirements.txt                    # Python依赖列表
├── app_config.ini                     # 配置文件示例
├── run.sh                             # 启动脚本
│
├── core modules/
│   ├── pipeline_manager.py            # 流水线管理器
│   ├── api_client.py                  # API客户端
│   ├── jianying_exporter.py           # 剪映自动化引擎
│   ├── upload_handler.py              # 上传处理器
│   ├── download_extractor.py          # 下载解压模块
│   └── logger.py                      # 日志系统
│
├── images/                            # GUI自动化所需图像资源
│   ├── draft_1.jpg
│   ├── draft_2.png
│   ├── exportImg.jpg
│   ├── export_confirm_btn.jpg
│   ├── confirmExport.png
│   └── export_success.jpg
│
└── logs/                              # 日志输出目录
```

## ⚙️ 配置说明

### API配置

```ini
[api]
base_url = http://localhost:17026    # API服务器地址 （当前项目是在Java，启动 duo-video_api）
interval = 3                         # 检测间隔（秒）
```

### 剪映配置

```ini
[jianying]
app_path = /Applications/VideoFusion-macOS.app    # 剪映应用程序路径
drafts_path = /path/to/drafts                     # 草稿存储目录
exports_path = ~/Downloads/video                  # 导出文件目录
```

### 上传配置

```ini
[upload]
storage = oss                          # 上传方式: oss | cos
video_key_prefix = videos/             # 存储key前缀
```

### 云存储配置

#### 阿里云OSS

```ini
[oss]
bucket_name = your-bucket-name
access_key_id = YOUR_ACCESS_KEY
access_key_secret = YOUR_SECRET_KEY
endpoint = oss-cn-hangzhou.aliyuncs.com
region = cn-hangzhou
```

#### 腾讯云COS

```ini
[cos]
bucket_name = your-bucket-name
secret_id = YOUR_SECRET_ID
secret_key = YOUR_SECRET_KEY
region = ap-beijing
```

## 📦 依赖项

核心依赖：

```
pyobjc-framework-Quartz>=7.0    # macOS GUI框架
pyautogui>=0.9.52              # GUI自动化
pygetwindow>=0.0.9             # 窗口管理
keyboard>=0.13.5               # 键盘模拟
psutil>=5.8.0                  # 进程管理
appscript>=1.2.2               # AppleScript桥接
requests>=2.25.1               # HTTP客户端
Pillow>=8.3.2                  # 图像处理
oss2>=2.15.0                   # 阿里云OSS SDK
qcloud-cos-v5>=2.6.70          # 腾讯云COS SDK
```

## 🎯 使用场景

- **批量视频处理**: 自动处理大量剪映视频导出任务
- **内容管理系统**: 集成到视频内容管理工作流
- **云端渲染**: 自动化视频渲染和上传流程
- **测试环境**: 用于剪映自动化测试

## 🔍 任务流程

### 完整流水线

1. **任务检测**
   - 定时向API服务器发送请求
   - 拉取待处理的视频导出任务
   - 返回任务详情（videoId、jyZipUrl等）

2. **资源下载**
   - 下载任务提供的ZIP资源文件
   - 解压到剪映草稿目录
   - 推送状态：下载完成、解压完成

3. **自动化导出**
   - 启动剪映应用程序
   - 自动选择并打开草稿项目
   - 点击导出按钮
   - 等待导出完成
   - 推送状态：打开工程、生成视频

4. **云端上传**
   - 检测导出文件（基于videoId）
   - 上传到指定云存储（OSS/COS）
   - 获取文件访问URL
   - 推送状态：任务完成（100）

5. **自动清理**
   - 关闭剪映应用程序
   - 删除草稿目录内容
   - 清空导出目录

### 状态码说明

| 状态码 | 说明 |
|--------|------|
| 0 | 等待处理 |
| 11 | 下载完成 |
| 12 | 解压完成 |
| 13 | 打开工程 |
| 14 | 生成视频 |
| 100 | 任务完成 |
| -10 | 主动取消 |
| -11 | 任务失败 |

## 🛠️ 故障排除

### 常见问题

**Q: 无法激活剪映窗口**

A: 检查剪映应用程序路径配置是否正确，确保应用程序已安装。

**Q: 图像识别失败**

A: 确保 `images/` 目录中有正确的图像文件，检查屏幕分辨率是否匹配。

**Q: 导出超时**

A: 检查导出目录路径是否正确，确保有足够磁盘空间。

**Q: 云存储上传失败**

A: 验证OSS/COS配置是否正确，检查网络连接和权限。

### 日志调试

查看详细日志：

```bash
tail -f logs/$(date +%Y%m%d).log
```

启用调试模式：

在 `jianying_exporter.py` 中设置 `debug=True` 以生成调试截图。

## 📄 许可证

本项目采用 MIT 许可证。详情请参阅 [LICENSE](LICENSE) 文件。



---

**注意**: 本工具仅供学习和研究使用，请遵守相关软件的使用协议和法律法规。
