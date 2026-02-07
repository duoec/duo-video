---
name: duo-video 工程创建器
description: 帮助用户使用 duo-video-base 模块创建视频工程。当需要创建新的视频项目、添加素材或构建视频结构时使用此技能。
compatibility: [java, video-editing, duo-video]
---

# duo-video 工程创建器

## 功能
- 创建新的视频工程 (VideoProject)
- 添加各种类型的素材 (视频、图片、文本、音频等)
- 配置视频参数 (分辨率、帧率、时长等)
- 构建完整的视频项目结构

## 核心数据模型

### VideoProject (视频项目)
- `id`: 工程ID
- `projectName`: 工程名称
- `width/height`: 分辨率 (默认 1080x1920)
- `fps`: 帧率 (默认 30)
- `scripts`: 分镜列表
- `materials`: 素材库

### VideoScript (分镜)
- `time`: 时间范围
- `segments`: 片段列表

### VideoSegment (片段)
- `id`: 片段ID
- `time`: 显示时间范围
- `materialId`: 关联的素材ID
- `type`: 素材类型
- `speed`: 变速
- `zoom`: 缩放
- `point`: 位置
- `rotate`: 旋转
- `opacity`: 透明度
- `volume`: 音量

## 使用方法

### 1. 创建基础视频工程
```java
VideoProject project = ProjectBuilder.createBuilder(
    SnowflakeIdUtils.nextTmpId(), 
    "我的视频项目", 
    1080, 1920
)
.setTest(true) // 设置为测试模式
.getProject();
```

### 2. 添加视频素材
```java
project.getScripts().get(0).getSegments().add(
    new VideoSegment()
        .setId(296653948753219561L)
        .setType("video")
        .setMaterialId(535010997887571046L)
        .setTime(new VideoTimeRange(3000L, 5000L))
        .setMaterialStart(10000L)
);
```

### 3. 添加素材到素材库
```java
project.getMaterials().add(
    new VideoMaterial()
        .setId(535010997887571046L)
        .setUrl("https://api.duoec.com/public/video/535010997887571046.mov")
        .setType("video")
);
```

### 4. 使用链式调用构建复杂项目
```java
VideoProject project = ProjectBuilder.createBuilder(
    SnowflakeIdUtils.nextTmpId(), 
    "测试项目", 
    1080, 1920
)
.setTest(true)
.getScriptBuilder(0) // 进入第一个分镜
.addTextTemplateAndGetBuilder(270464050694389761L, "太好了", 0, 3000) // 添加文本模板
.setPosition(0, -400) // 设置位置
.back()
.addTextAndGetBuilder("测试文本", 0, 3000)
.setStyle(new TextStyle()
    .setFontSize(14)
    .setFillColor("#FFFFFF"))
.setPosition(0, 1866)
.back()
.back()
.getProject();
```

## 素材类型

### 基础素材
- **视频 (video)**: 支持常见视频格式，时间裁剪、变速、倒放
- **图片 (image)**: 支持常见图片格式，自定义显示时长、缩放
- **文本 (text)**: 富文本编辑，多样式、花字、描边、阴影、背景
- **字幕 (subtitle)**: 基于文本系统，继承全部文本样式能力
- **音频 (audio)**: 背景音乐、配音，时间范围、音量控制
- **文本模板 (text_template)**: 官方动画模板，多文本块、动态效果

### 特效素材
- **特效音 (sound)**: 短音效资源，转场音、点击音、环境音
- **贴纸 (sticker)**: 动态或静态贴纸，表情、标签、装饰
- **转场 (transition)**: 场景过渡动画，淡入淡出、擦除、翻转等
- **画面特效 (video_effect)**: 全屏视觉效果，粒子、扭曲、色彩调整
- **脸部特效 (face_effect)**: AI 人脸特效，美颜、搞怪、风格化

## 高级功能
- **绿幕抠图**: 智能 Chroma Key，支持自定义取色和容差
- **复合片段**: 绿幕视频与背景自动合成为 Group
- **视频倒放**: FFmpeg 驱动的高质量倒放
- **文本模板**: 全网唯一完整支持剪映文本模板，支持多段落文本
- **逐字样式**: 单个字符独立样式（颜色、大小、特效）
- **蒙板**: 可精确控制人脸位置、大小、浮动位置

## 时间单位
所有时间参数使用 **毫秒（ms）** 为单位：
- 1 秒 = 1,000 毫秒
- 3 秒 = 3,000 毫秒

## 坐标系统
- **原点 (0, 0)**: 视频画面中心
- **X 轴**: 左负右正
- **Y 轴**: 上正下负

## 轨道层级顺序
轨道自下而上的渲染顺序（数字越小越在底层）：
1. 特效音
2. 音频
3. 绿幕背景
4. 视频
5. 图片
6. 蒙板
7. 画面特效
8. 贴纸
9. 字幕
10. 文本
11. 文本模板