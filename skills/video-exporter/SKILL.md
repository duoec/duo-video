---
name: duo-video 导出器
description: 帮助用户使用 duo-video-jy 模块将视频工程导出为剪映工程，并使用 auto-jy 工具自动导出视频文件。当需要将 VideoProject 转换为实际视频文件时使用此技能。
compatibility: [java, python, video-export, jianying]
---

# duo-video 导出器

## 功能
- 将 VideoProject 转换为剪映工程格式
- 生成完整的剪映草稿文件
- 使用 auto-jy 自动化导出视频
- 管理导出任务和进度跟踪

## duo-video-jy 模块

### 核心组件
- **JianyingBuilder**: 主要构建器，将 VideoProject 转换为剪映工程
- **JianyingMaterialBuilder**: 构建素材部分
- **JianyingScriptBuilder**: 构建分镜部分
- **JianYingProjectInfo**: 剪映工程信息对象
- **JianyingProjectBuildState**: 构建状态管理

### 使用方法

#### 1. 基础导出
```java
// 从 JSON 加载项目
VideoProject project = FileUtils.readJson("project.json", VideoProject.class);

// 构建剪映工程
JianyingBuilder builder = new JianyingBuilder();
JianYingProjectInfo jyProject = builder.build(project);
```

#### 2. 从代码创建并导出
```java
VideoProject project = ProjectBuilder.createBuilder(
    SnowflakeIdUtils.nextTmpId(), 
    "测试项目", 
    1080, 1920
)
.setTest(true)
.getScriptBuilder(0)
.addTextTemplateAndGetBuilder(270464050694389761L, "太好了", 0, 3000)
.setPosition(0, -400)
.back()
.back()
.getProject();

JianyingBuilder builder = new JianyingBuilder();
JianYingProjectInfo jyProject = builder.build(project);
```

### 构建流程
1. **初始化构建状态**: 创建 `JianyingProjectBuildState`
2. **构建素材**: 使用 `JianyingMaterialBuilder` 处理所有素材
3. **构建分镜**: 使用 `JianyingScriptBuilder` 处理每个分镜
4. **保存工程**: 生成完整的剪映工程文件

## auto-jy 模块

### 功能
- **自动化管道管理**: 自动检测API接口任务，执行完整的视频处理流程
- **多阶段处理**: 支持下载、解压、导出、上传等多个处理阶段
- **实时监控**: 提供图形界面，实时显示任务状态、下载/上传进度
- **跨平台支持**: 支持 macOS 和 Windows 系统

### 系统流程
1. **任务检测**: 定时轮询API接口，获取待处理任务
2. **资源下载**: 下载任务相关的剪映工程压缩包
3. **工程处理**: 解压并使用剪映软件导出视频
4. **视频上传**: 将导出的视频上传至指定云存储
5. **状态上报**: 向API接口上报任务处理结果
6. **资源清理**: 清理临时文件和剪映草稿

### 配置文件 (app_config.ini)
```ini
[api]
base_url = https://your-api-endpoint.com/api/task
interval = 30

[jianying]
app_path = /Applications/JianyingPro.app
drafts_path = ~/Movies/JianyingPro/Drafts
exports_path = ~/Downloads

[upload]
storage = oss
video_key_prefix = videos

[oss]  # 或 [cos] 腾讯云COS
bucket_name = your-bucket-name
access_key_id = your-access-key-id
access_key_secret = your-access-key-secret
endpoint = https://oss-cn-hangzhou.aliyuncs.com
region = cn-hangzhou
```

### 启动方法
```bash
# GUI模式
python main_gui.py [config_path]

# 无界面模式
python pipeline_manager.py [config_path]
```

### 状态码说明
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

## 完整导出流程

### 1. 创建视频工程
使用 duo-video-base 创建 VideoProject 对象

### 2. 转换为剪映工程
使用 duo-video-jy 将 VideoProject 转换为剪映工程

### 3. 自动导出视频
使用 auto-jy 自动化工具将剪映工程导出为视频文件

### 4. 上传和清理
将导出的视频上传到云存储并清理临时文件

## 注意事项
- 确保已安装剪映专业版 (验证版本：剪映 v9.6.0)
- 需要安装 FFmpeg 以支持视频倒放等功能
- 配置正确的剪映草稿目录路径
- 确保 auto-jy 配置文件中的路径和凭据正确
- 检查系统是否有足够的磁盘空间存储临时文件和最终视频