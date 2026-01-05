# Duo-Video

[![Java Version](https://img.shields.io/badge/Java-21-orange.svg)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.8-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Maven](https://img.shields.io/badge/Maven-Project-blue.svg)](https://maven.apache.org/)

Duo-Video 是一个强大的 Java 视频编辑 SDK，通过简洁优雅的 API 以编程方式生成专业级视频项目。项目采用分层架构设计，目前通过生成剪映工程文件实现视频创作，**最终目标是实现纯 Linux 环境下的 Headless 视频生成能力**。

![剪映工程示例](https://api.duoec.com/public/duo-video.png)
（本工程由 com.duoec.video.jy.JianyingBuilderTest.buildWithProjectJson 测试用例生成）

## 项目愿景

**duo-video-base** 定义了一套简洁而完整的视频数据模型，用最小化的结构描述复杂的视频项目。当前阶段通过 **duo-video-jy** 模块将这套模型转换为剪映工程格式，利用剪映编辑器导出最终视频。未来将直接调用底层渲染引擎，实现无需 GUI 的服务器端视频生成。

## 项目亮点

- **支持剪映最新版本** - 与剪映专业版保持同步，支持最新特性
- **文本模板** - 丰富的官方文字动画模板，一键应用动态效果
- **画面特效** - 粒子、扭曲、模糊等数百种视觉效果
- **脸部特效** - 基于 AI 的人脸识别特效（美颜、搞怪等）
- **绿幕抠图** - 智能色度键控，支持背景替换和边缘优化
- **转场效果** - 专业的场景过渡动画
- **复合片段** - 多层素材智能合成（绿幕+背景自动合并）
- **视频倒放** - 基于 FFmpeg 的高质量倒放处理。支持剪映自带的水平镜像、垂直镜像
- **LUT 滤镜** - 专业级调色，支持肤色保护（使用自定义CUBE文件）
- **花字系统** - 花字文字效果，支持逐字符样式定制
- **Fluent API** - 流畅的 Builder 模式，代码即视频脚本

## 核心功能一览

### 基础素材（6 种）

| 素材类型     | 功能描述     | 特性              |
|----------|----------|-----------------|
| **视频**   | 支持常见视频格式 | 时间裁剪、变速、倒放      |
| **图片**   | 支持常见图片格式 | 自定义显示时长、缩放      |
| **文本**   | 富文本编辑    | 多样式、花字、描边、阴影、背景 |
| **字幕**   | 基于文本系统   | 继承全部文本样式能力      |
| **音频**   | 背景音乐、配音  | 时间范围、音量控制       |
| **文本模板** | 官方动画模板   | 多文本块、动态效果       |
| -- 其它 -- | 新功能持续追加中 | ...             |

### 特效素材（5 种）

| 特效类型 | 功能描述 | 应用场景 |
|---------|---------|---------|
| **特效音** | 短音效资源 | 转场音、点击音、环境音 |
| **贴纸** | 动态或静态贴纸 | 表情、标签、装饰 |
| **转场** | 场景过渡动画 | 淡入淡出、擦除、翻转等 |
| **画面特效** | 全屏视觉效果 | 粒子、扭曲、色彩调整 |
| **脸部特效** | AI 人脸特效 | 美颜、搞怪、风格化 |

### 高级功能（14 项）

- **绿幕抠图** - 智能 Chroma Key，支持自定义取色和容差
- **复合片段** - 绿幕视频与背景自动合成为 Group
- **LUT 滤镜** - 3D LUT 调色，支持肤色保护模式
- **视频倒放** - FFmpeg 驱动的高质量倒放
- **变速控制** - 0.2x - 100x 任意倍速
- **音量调节** - 静音、原音量、增益，支持线性/对数转换
- **透明度** - 0-100 级别控制
- **旋转** - 任意角度旋转
- **缩放** - 支持 X/Y 轴独立缩放
- **位置** - 像素级精确定位，中心原点坐标系
- **镜像** - 水平/垂直镜像独立控制
- **花字** - 特殊文字样式，支持资源包加载
- **逐字样式** - 单个字符独立样式（颜色、大小、特效）
- **智能轨道** - 自动创建、防重叠、层级管理

## 项目架构

```
duo-video/
├── duo-server-base/      # 基础工具库（文件处理、JSON、工具类）
├── duo-video-base/       # 核心数据模型（VideoProject、Material、Segment）
└── duo-video-jy/         # 剪映集成（将数据模型转换为剪映工程格式）
    ├── builder/          # 17 个专业 Builder 类
    ├── dto/              # 剪映数据结构映射
    └── service/          # 素材下载、FFmpeg、Exiftool 集成
```

## 环境要求

- **Java 21+**
- **Maven 3.6+**
- **Spring Boot 3.5.8**
- **FFmpeg**（可选，用于视频倒放）
- **Exiftool**（可选，用于媒体元数据提取）

## 快速开始

### 1. 添加依赖

在你的 `pom.xml` 中添加：

```xml
<dependency>
    <groupId>com.duoec.video</groupId>
    <artifactId>duo-video-jy</artifactId>
    <version>1.0.0-SNAPSHOT</version>
</dependency>
```

### 2. 创建你的第一个视频

```java
import com.duoec.video.builder.ProjectBuilder;
import com.duoec.video.jy.JianyingBuilder;
import com.duoec.video.project.VideoProject;
import com.duoec.video.project.material.TextStyle;

// 创建 1080x1920 竖屏视频项目
VideoProject project = ProjectBuilder
    .createBuilder("my-project-001", "我的第一个视频", 1080, 1920)
    .setFps(30)
    .setTest(true) // 测试模式

    // 添加文本模板（带动画效果）
    .addTextTemplateAndGetBuilder(0, 267415006823001104L, "标题文本", 0, 3000)
    .setPosition(0, -400)  // 屏幕上方
    .back()

    // 添加普通文本
    .addTextAndGetBuilder(0, "这是一段普通文本", 0, 3000)
    .setStyle(new TextStyle()
        .setFontSize(16)
        .setFillColor("#FFFFFF")
        .setTextAlign(1)  // 居中对齐
        .setBold(true))
    .setPosition(0, 400)  // 屏幕下方
    .back()

    .getProject();

// 构建剪映工程
JianyingBuilder builder = new JianyingBuilder();
JianYingProjectInfo jyProject = builder.build(project);

// 此时剪映工程已生成，可在剪映中打开并导出视频
```

## 核心概念

### 三层数据模型

```
VideoProject (视频项目)
├── projectName: 项目名称
├── width/height: 分辨率
├── fps: 帧率
├── scripts: List<VideoScript> (分镜列表，用于组织复杂的项目)
│   └── VideoScript (分镜)
│       ├── time: 时间范围（可选，未填写时自动扩容）
│       └── segments: List<VideoSegment> (片段列表)
│           └── VideoSegment (片段)
│               ├── materialId: 关联的素材ID
│               ├── type: 素材类型
│               ├── time: 显示时间范围
│               ├── speed: 变速
│               ├── zoom: 缩放
│               ├── point: 位置
│               ├── rotate: 旋转
│               ├── opacity: 透明度
│               ├── volume: 音量
│               └── refs: 关联的其他素材（如转场）
└── materials: List<BaseMaterial> (素材库)
```

### 坐标系统

```
        Y+
        ↑
        |
X- ←--(0,0)--→ X+
        |
        ↓
        Y-
```

参考剪映的坐标系

- **原点 (0, 0)**: 视频画面中心
- **X 轴**: 左负右正
- **Y 轴**: 上正下负（注意：与常见坐标系相反）

### 时间单位

所有时间参数使用 **毫秒（ms）** 为单位：

- 1 秒 = 1,000 毫秒
- 3 秒 = 3,000 毫秒

## 实用示例

### 绿幕抠图 + 背景替换

```java
// 添加带绿幕的视频
VideoSegment greenScreenVideo = new VideoSegment()
    .setMaterialId(videoMaterialId)
    .setTime(new VideoTimeRange(0, 5000000))  // 5秒
    .setGreenBackground(
            new GreenBackground() // 目前仅实现捨色抠像
                .setBaseBackgroundColor("#4e8a1fff")  // 绿幕颜色
                .setStrength(20) // 强度。其它参数、功能可以剪映的参数。这些参数可以使用AI推荐（需要多模态支持）
    );

// 会自动生成复合片段（绿幕视频 + 背景合成）
```

### 视频倒放

```java
VideoSegment reverseVideo = new VideoSegment()
    .setMaterialId(videoMaterialId)
    .setTime(new VideoTimeRange(0, 3000000))
    .setUpend(true);  // 启用倒放（需要 FFmpeg）
```

### 添加转场

```java
// 在 refs 中关联转场素材
VideoSegment segment1 = new VideoSegment()
    .setMaterialId(video1)
    .setTime(new VideoTimeRange(0, 3000000));

// 自动应用到前一个片段的结尾
segment1.addRef(transitionMaterialId, "transition");
```

### 复杂文本样式

```java
TextStyle style = new TextStyle()
    .setFontSize(20)                    // 字体大小
    .setBold(true)                      // 粗体
    .setItalic(false)                   // 斜体
    .setUnderline(true)                 // 下划线
    .setFillColor("#FF0000")            // 填充颜色
    .setTextAlign(1)                    // 0=左，1=中，2=右
    .setLineSpacing(0.5)                // 行间距
    .setLetterSpacing(0.1)              // 字间距

    // 描边
    .setStrokeColor("#000000")
    .setStrokeWidth(2.0)

    // 阴影
    .setShadowColor("#00000080")
    .setShadowAlpha(0.5)
    .setShadowAngle(45)
    .setShadowDistance(5.0)
    .setShadowSmooth(3.0)

    // 背景
    .setBackgroundColor("#FFFFFF80")
    .setBackgroundAlpha(0.5)
    .setBackgroundCornerRadius(10.0)
    .setBackgroundWidth(1.2)
    .setBackgroundHeight(1.5);

// 花字效果
TextWord word = new TextWord()
    .setText("花")
    .setFlowerId(flowerResourceId);  // 特殊文字效果
```

### 画面特效

```java
VideoSegment withEffect = new VideoSegment()
    .setMaterialId(videoId)
    .setTime(new VideoTimeRange(0, 3000000));

// 添加画面特效（粒子、扭曲、模糊等）
VideoSegment effect = new VideoSegment()
    .setType("video_effect")
    .setMaterialId(videoEffectMaterialId)  // 特效素材ID
    .setTime(new VideoTimeRange(500000, 2000000));  // 特效时间范围
```

### 使用 LUT 滤镜

```java
VideoMaterial video = new VideoMaterial()
    .setMaterialId(videoId)
    .setUrl("https://example.com/video.mp4")
    .setLut(new Lut()
        .setUrl("https://example.com/lut.cube")
        .setSkinToneCorrection(10));  // 保护肤色，与剪映上的参数保持一致
```

### 变速和音量控制

```java
VideoSegment segment = new VideoSegment()
    .setMaterialId(videoId)
    .setTime(new VideoTimeRange(0, 3000000))
    .setSpeed(200)      // 2倍速（百分比）
    .setVolume(150);    // 音量增益 1.5 
```

### 使用 JSON 配置

```java
// 从 JSON 文件加载项目
VideoProject project = FileUtils.readJson("project.json", VideoProject.class);

// 构建剪映工程
JianyingBuilder builder = new JianyingBuilder();
JianYingProjectInfo jyProject = builder.build(project);
```

注意：本demo中，第一个视频旋转 90度，应该是倒着的人，但又配置了垂直镜像，所以人正过来了。另外，剪映5.9版本不支持垂直镜像，所以在低版本上人是倒过来的。特此说明

## 构建

### 编译项目

```bash
mvn clean install
```

### 运行测试

```bash
mvn test
```

### 使用 Maven Profile

开发环境（SNAPSHOT 版本）：
```bash
mvn clean install -Pdev
```

生产环境（正式版本）：
```bash
mvn clean install -Pprod
```

## 开发指南

### 轨道层级顺序

轨道自下而上的渲染顺序（数字越小越在底层）：

1. 特效音
2. 音频
3. 绿幕背景
4. 视频
5. 图片
6. 画面特效
7. 贴纸
8. 字幕
9. 文本
10. 文本模板

## 特别说明

**本项目仅供学习交流使用，如有侵权，请联系作者。**

![企业微信](https://api.duoec.com/public/qywx_xwz.jpg)

_本 README.md 由 claude code 生成，仅作参考，以代码实现为准_