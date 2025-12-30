# Duo Video

[![Java Version](https://img.shields.io/badge/Java-21-orange.svg)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.8-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Maven](https://img.shields.io/badge/Maven-Project-blue.svg)](https://maven.apache.org/)

Duo Video 是一个用于以编程方式生成剪映（JianYing）视频项目文件的 Java 库。通过简洁的 API，你可以创建复杂的视频项目，包括视频片段、文本、字幕、特效等，而无需手动操作剪映应用。

## 特性

- 编程式创建剪映视频项目
- 支持多种素材类型：视频、文本、文本模板、音频等
- 灵活的分镜（VideoScript）和片段（VideoSegment）管理
- 支持文本样式自定义（字体、颜色、大小、对齐等）
- 支持 LUT（色彩查找表）
- 支持位置、时间范围等精确控制
- 基于 Builder 模式的流畅 API

## 项目结构

```
duo-video/
├── duo-server-base/      # 服务器基础模块
├── duo-video-base/       # 视频项目基础模块（核心数据模型）
└── duo-video-jy/         # 剪映项目生成器（转换为剪映格式）
```

## 环境要求

- Java 21+
- Maven 3.6+
- Spring Boot 3.5.8

## 快速开始

### 1. 添加依赖

在你的 `pom.xml` 中添加依赖：

```xml
<dependency>
    <groupId>com.duoec.video</groupId>
    <artifactId>duo-video-jy</artifactId>
    <version>1.0.0-SNAPSHOT</version>
</dependency>
```

### 2. 创建视频项目

使用 `VideoProjectBuilder` 创建视频项目：

```java
import com.duoec.video.builder.ProjectBuilder;
import com.duoec.video.jy.JianyingBuilder;
import com.duoec.video.jy.dto.info.JianYingProjectInfo;
import com.duoec.video.project.VideoProject;
import com.duoec.video.project.material.TextStyle;

// 创建视频项目
VideoProject videoProject = VideoProjectBuilder
    .createBuilder(projectId, "我的视频项目", 1080, 1920)
    .setTest(true) // 设置为测试模式

    // 第一个分镜：添加文本模板
    .getTextTemplateBuilder(0)
    .add(textTemplateResourceId, "标题文本", 0, 3000)
    .setPosition(0, -400) // 位置：中央偏上
    .back()

    // 添加普通文本
    .getTextBuilder(0)
    .add("文本ID", 0, 3000)
    .setStyle(
        new TextStyle()
            .setFontSize(5)
            .setTextAlign(1)
            .setFillColor("#FFFFFF")
            .setFontName("默认字体")
    )
    .setPosition(0, 1866) // 位置：底部
    .back()

    .getProject(); // 导出项目

// 构建剪映项目文件
JianyingBuilder jianyingBuilder = new JianyingBuilder();
JianYingProjectInfo jyProject = jianyingBuilder.build(videoProject);
```

### 3. 使用 JSON 配置

你也可以从 JSON 文件加载项目配置：

```java
VideoProject videoProject = FileUtils.readJson("project.json", VideoProject.class);
videoProject.setTest(true);

JianYingProjectInfo jyProject = jianyingBuilder.build(videoProject);
```

## 核心概念

### VideoProject（视频项目）

视频项目是最顶层的容器，包含：
- 项目名称、尺寸（宽高）、帧率
- 分镜列表（scripts）
- 素材列表（materials）

### VideoScript（分镜）

分镜代表视频的一个时间段，包含：
- 时间范围（可选，未指定则自动扩容）
- 片段列表（segments）

### VideoSegment（片段）

片段是最小的视频元素单位，可以是：
- 视频片段
- 文本
- 文本模板
- 音频
- 特效等

### 坐标系统

位置坐标以视频中心为原点 (0, 0)：
- X 轴：左负右正
- Y 轴：上正下负

### 时间单位

所有时间相关的参数使用微秒（μs）为单位。

## API 示例

### 设置视频尺寸和帧率

```java
VideoProject project = VideoProjectBuilder
    .createBuilder(id, "项目名称", 1920, 1080) // 横屏 16:9
    .setFps(30) // 设置帧率为 30fps
    .getProject();
```

### 添加多个分镜

```java
VideoProjectBuilder builder = VideoProjectBuilder.createBuilder(id, "项目名称", 1080, 1920);

// 第一个分镜
builder.getTextBuilder(0)
    .add("scene1_text", 0, 3000)
    .back();

// 第二个分镜
builder.getTextBuilder(1)
    .add("scene2_text", 0, 5000)
    .back();

VideoProject project = builder.getProject();
```

### 自定义文本样式

```java
TextStyle style = new TextStyle()
    .setFontSize(10)           // 字体大小
    .setTextAlign(1)           // 对齐方式：0=左对齐，1=居中，2=右对齐
    .setFillColor("#FF0000")   // 文字颜色（红色）
    .setFontName("微软雅黑");   // 字体名称

builder.getTextBuilder(0)
    .add("text_id", 0, 3000)
    .setStyle(style)
    .back();
```

## 构建和测试

### 编译项目

```bash
mvn clean install
```

### 运行测试

```bash
mvn test
```

### 使用指定 Profile

开发环境（默认）：
```bash
mvn clean install -Pdev
```

生产环境：
```bash
mvn clean install -Pprod
```

## 项目配置

### Maven Profiles

项目提供两个 Maven Profile：

- `dev`（默认）：使用 SNAPSHOT 版本
- `prod`：使用正式版本

## 开发指南

### 添加新的素材类型

1. 在 `duo-video-base` 模块的 `com.duoec.video.project.material` 包下创建新的素材类
2. 继承 `BaseMaterial` 类
3. 在 `duo-video-jy` 模块中实现对应的转换逻辑

### 扩展剪映功能

在 `duo-video-jy` 模块的 `com.duoec.video.jy.builder` 包下添加新的 Builder 类。

## 技术栈

- Java 21
- Spring Boot 3.5.8
- Lombok
- JUnit 5
- Maven

## 许可证

本项目采用开源许可证（具体许可证类型待定）。

## 贡献

欢迎贡献代码！请遵循以下步骤：

1. Fork 本仓库
2. 创建你的特性分支 (`git checkout -b feature/AmazingFeature`)
3. 提交你的更改 (`git commit -m 'Add some AmazingFeature'`)
4. 推送到分支 (`git push origin feature/AmazingFeature`)
5. 开启一个 Pull Request

## 联系方式

如有问题或建议，请提交 Issue。

## 更新日志

### v1.0.0-SNAPSHOT

- 初始版本
- 支持基础的剪映项目生成
- 支持文本、文本模板、视频等素材
- 支持 LUT 色彩查找表
- 提供 Builder 模式的流畅 API
