package com.duoec.video.server;

import com.duoec.video.dto.request.*;
import com.duoec.video.project.VideoProject;

/**
 * 视频工程服务接口
 */
public interface VideoProjectService {
    /**
     * 创建视频工程
     * @param request 创建请求
     * @return 视频工程
     */
    VideoProject createProject(CreateProjectRequest request);

    /**
     * 设置全局样式
     * @param request 设置全局样式请求
     * @return 视频工程
     */
    VideoProject setGlobalStyle(SetGlobalStyleRequest request);

    /**
     * 添加图片素材
     * @param request 添加图片请求
     * @return 视频工程
     */
    VideoProject addImage(AddImageRequest request);

    /**
     * 添加视频素材
     * @param request 添加视频请求
     * @return 视频工程
     */
    VideoProject addVideo(AddVideoRequest request);

    /**
     * 添加音频素材
     * @param request 添加音频请求
     * @return 视频工程
     */
    VideoProject addAudio(AddAudioRequest request);

    /**
     * 添加文本素材
     * @param request 添加文本请求
     * @return 视频工程
     */
    VideoProject addText(AddTextRequest request);

    /**
     * 添加文本模板素材
     * @param request 添加文本模板请求
     * @return 视频工程
     */
    VideoProject addTextTemplate(AddTextTemplateRequest request);

    /**
     * 添加贴纸素材
     * @param request 添加贴纸请求
     * @return 视频工程
     */
    VideoProject addSticker(AddStickerRequest request);

    /**
     * 添加视频特效
     * @param request 添加视频特效请求
     * @return 视频工程
     */
    VideoProject addVideoEffect(AddVideoEffectRequest request);

    /**
     * 添加人脸特效
     * @param request 添加人脸特效请求
     * @return 视频工程
     */
    VideoProject addFaceEffect(AddFaceEffectRequest request);

    /**
     * 添加音效
     * @param request 添加音效请求
     * @return 视频工程
     */
    VideoProject addSound(AddSoundRequest request);
}
