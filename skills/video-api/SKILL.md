---
name: duo-video API 接口
description: 帮助用户使用 duo-video-api 模块的 HTTP 接口创建视频工程和视频创作任务。当需要通过 REST API 进行视频项目管理和任务调度时使用此技能。
compatibility: [rest-api, http, video-management]
---

# duo-video API 接口

## 功能
- 通过 HTTP 接口创建视频项目
- 添加各种类型的素材到视频项目
- 管理视频创作任务
- 查询项目和任务状态
- 构建视频并获取任务ID

## API 基础信息
- **基础URL**: http://localhost:8080 (默认)
- **内容类型**: application/json
- **响应格式**:
```json
{
  "code": 0,           // 0 表示成功，非 0 表示失败
  "msg": "success",    // 一般在 code!=0时，会有值，表示错误原因
  "data": { ... }      // 具体响应数据
}
```

## 核心接口

### 1. 创建视频项目
```
POST /api/video
```

**请求参数**:
```json
{
  "projectId": 123456789,      // 可选，不传则自动生成
  "projectName": "我的视频",    // 可选，默认使用 projectId
  "width": 1080,               // 可选，默认 1080
  "height": 1920,              // 可选，默认 1920
  "test": true                 // 可选，是否为测试模式
}
```

**响应示例**:
```json
{
  "code": 0,
  "msg": "success",
  "data": {
    "id": 123456789,
    "projectName": "我的视频",
    "width": 1080,
    "height": 1920,
    "test": true,
    "scripts": [],
    "materials": []
  }
}
```

### 2. 设置全局文本样式
```
POST /api/project/global-style
```

**请求参数**:
```json
{
  "projectId": 123456789,
  "styleId": 296653948753219540,
  "textStyle": {
    "fontSize": 28,
    "bold": true,
    "italic": true,
    "textAlign": 1,
    "fontName": "抖音美好体",
    "fillColor": "#FFFF00",
    "strokeColor": "#FF0000",
    "strokeWidth": 10
  },
  "globalKeywordStyle": true    // 是否设为全局关键词样式
}
```

### 3. 添加图片素材
```
POST /api/project/image
```

**请求参数**:
```json
{
  "projectId": 123456789,
  "scriptIndex": 0,             // 分镜索引，默认 0
  "imageId": 535010997887571096,
  "imageUrl": "https://example.com/image.png",
  "startTime": 3500,            // 开始时间（毫秒）
  "duration": 3000,             // 持续时间（毫秒）
  "layoutIndex": 1000,          // 图层索引
  "zoomX": 7500,                // X 轴缩放（万分比）
  "zoomY": 7500,                // Y 轴缩放（万分比）
  "positionX": 0,               // X 轴位置
  "positionY": -1512,           // Y 轴位置
  "rotate": -90,                // 旋转角度
  "visible": true,              // 是否可见
  "horizontal": true,           // 水平翻转
  "vertical": true              // 垂直翻转
}
```

### 4. 添加视频素材（支持绿幕和蒙版）
```
POST /api/project/video
```

**请求参数**:
```json
{
  "projectId": 123456789,
  "scriptIndex": 0,
  "videoId": 535010997887571021,
  "videoUrl": "https://example.com/video.mp4",
  "startTime": 0,
  "duration": 3000,
  "materialStart": 5000,        // 素材开始位置（毫秒）
  "materialTimeStart": 0,       // 素材时间范围开始（毫秒）
  "materialTimeEnd": 14264,     // 素材时间范围结束（毫秒）
  "layoutIndex": 1000,
  "speed": 100,                 // 播放速度（百分比）
  "zoomX": 10000,
  "zoomY": 10000,
  "rotate": 90,
  "visible": true,
  "horizontal": true,
  "volume": 0,                  // 音量（-100 到 100）
  "transitionId": 270404457990455297,  // 转场特效 ID
  "transitionDuration": 1000,          // 转场持续时间
  "greenBackground": {          // 绿幕背景（可选）
    "greenScreenId": 535010997887571022,
    "greenScreenUrl": "https://example.com/green.png",
    "chromaColor": "#4e8a1fff",
    "chromaStrength": 20,
    "chromaShadow": 10,
    "chromaHighlight": 10
  },
  "mask": {                     // 蒙版（可选）
    "maskId": 270415264124764161,
    "feather": 5,
    "rotation": 90,
    "width": 0.5,
    "height": 0.28,
    "centerX": 0.07,
    "centerY": 0.25,
    "pointX": 400,
    "pointY": 400
  }
}
```

### 5. 添加音频素材
```
POST /api/project/audio
```

**请求参数**:
```json
{
  "projectId": 123456789,
  "scriptIndex": 0,
  "audioId": 535010997887571025,
  "audioUrl": "https://example.com/audio.mp3",
  "startTime": 0,
  "duration": 8000,
  "materialTimeStart": 170,
  "materialTimeEnd": 126869,
  "materialStart": 10000,
  "layoutIndex": 1000,
  "speed": 100,
  "visible": true,
  "volume": -50
}
```

### 6. 添加文本/字幕
```
POST /api/project/text
```

**请求参数**:
```json
{
  "projectId": 123456789,
  "scriptIndex": 0,
  "text": "测试中文字幕",
  "startTime": 10,
  "duration": 1990,
  "layoutIndex": 1000,
  "positionX": 0,
  "positionY": -1000,
  "rotate": 0,
  "asSubtitle": true,           // 是否作为字幕
  "styleId": 296653948753219540, // 引用全局样式 ID（可选）
  "style": {                    // 自定义样式（可选）
    "fontSize": 14,
    "bold": false,
    "italic": false,
    "textAlign": 1,
    "fontName": "微软雅黑",
    "fillColor": "#FFFFFF",
    "strokeColor": "#FF0000",
    "strokeWidth": 10
  },
  "wordStyles": [               // 逐字样式（可选）
    {
      "startIndex": 2,
      "length": 2,
      "fontSize": 16,
      "fillColor": "#00FFFF",
      "strokeWidth": 20,
      "strokeColor": "#0000FF"
    },
    {
      "startIndex": 3,
      "length": 2,
      "styleId": 296653948753219540,
      "fontSize": 18,
      "flowerId": 270413717936603137  // 花字 ID
    }
  ]
}
```

### 7. 添加文本模板
```
POST /api/project/text-template
```

**请求参数**:
```json
{
  "projectId": 123456789,
  "scriptIndex": 0,
  "templateId": 270414005699805185,
  "texts": ["非", "常", "棒", "duoec.com"],
  "startTime": 2001,
  "duration": 999,
  "layoutIndex": 1000,
  "zoomX": 5000,
  "zoomY": 5000,
  "positionX": 0,
  "positionY": 1400
}
```

### 8. 添加贴纸
```
POST /api/project/sticker
```

**请求参数**:
```json
{
  "projectId": 123456789,
  "scriptIndex": 0,
  "stickerId": 270402997699280897,
  "startTime": 1500,
  "duration": 3000,
  "zoomX": 5000,
  "zoomY": 5000,
  "positionX": 500,
  "positionY": 0,
  "rotate": -45
}
```

### 9. 添加画面特效
```
POST /api/project/video-effect
```

**请求参数**:
```json
{
  "projectId": 123456789,
  "scriptIndex": 0,
  "effectId": 270464037793497089,
  "startTime": 5000,
  "duration": 3000
}
```

### 10. 添加人脸特效
```
POST /api/project/face-effect
```

**请求参数**:
```json
{
  "projectId": 123456789,
  "scriptIndex": 0,
  "effectId": 270464033541718017,
  "startTime": 1500,
  "duration": 1000
}
```

### 11. 添加音效
```
POST /api/project/sound
```

**请求参数**:
```json
{
  "projectId": 123456789,
  "scriptIndex": 0,
  "soundId": 270464042140893185,
  "startTime": 1000,
  "duration": 3000
}
```

### 12. 构建视频
```
POST /api/project/build
```

**请求参数**:
```json
{
  "id": 123456789,
  "projectName": "我的视频",
  "width": 1080,
  "height": 1920,
  "scripts": [...],
  "materials": [...]
}
```

**响应示例**:
```json
{
  "code": 0,
  "msg": "success",
  "data": 987654321  // 视频构建任务ID
}
```

## 完整示例

使用 curl 创建一个完整的视频项目：

```bash
# 1. 创建项目
curl -X POST http://localhost:8080/api/video \
  -H "Content-Type: application/json" \
  -d '{
    "projectId": 123456789,
    "projectName": "我的第一个视频",
    "width": 1080,
    "height": 1920,
    "test": true
  }'

# 2. 添加视频素材
curl -X POST http://localhost:8080/api/project/video \
  -H "Content-Type: application/json" \
  -d '{
    "projectId": 123456789,
    "videoId": 535010997887571021,
    "videoUrl": "https://api.duoec.com/public/video/535010997887571021.mp4",
    "startTime": 0,
    "duration": 3000,
    "zoomX": 10000,
    "zoomY": 10000
  }'

# 3. 添加文本
curl -X POST http://localhost:8080/api/project/text \
  -H "Content-Type: application/json" \
  -d '{
    "projectId": 123456789,
    "text": "Hello World",
    "startTime": 0,
    "duration": 3000,
    "positionY": -800
  }'

# 4. 构建视频
curl -X POST http://localhost:8080/api/project/build \
  -H "Content-Type: application/json" \
  -d '{
    "id": 123456789,
    "projectName": "我的第一个视频",
    "width": 1080,
    "height": 1920,
    "scripts": [...],
    "materials": [...]
  }'
```

## API 特性
- **链式操作**: 每个接口返回完整的 VideoProject，可以连续调用
- **自动保存**: 每次操作后自动保存项目状态
- **参数验证**: 自动验证必填参数，返回友好的错误提示
- **灵活配置**: 所有可选参数都有合理的默认值
- **完整功能**: 支持所有 Builder 模式的功能，包括绿幕、蒙版、转场等高级特性

## 错误处理
- 检查参数完整性（如项目ID、素材ID等）
- 验证素材URL的有效性
- 确保时间范围不冲突
- 检查素材类型与接口的匹配性