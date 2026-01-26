package com.duoec.video.controller;

import com.duoec.base.dto.response.BaseResponse;
import com.duoec.video.dto.request.*;
import com.duoec.video.project.VideoProject;
import com.duoec.video.server.VideoProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/video")
@RequiredArgsConstructor
public class VideoApiController {
    private final VideoProjectService videoProjectService;

    /**
     * 初始化一个视频工程
     * @param request 创建请求
     * @return 视频工程
     */
    @PostMapping
    public BaseResponse<VideoProject> createProject(@RequestBody CreateProjectRequest request) {
        VideoProject videoProject = videoProjectService.createProject(request);
        return BaseResponse.success(videoProject);
    }

    /**
     * 设置全局样式
     * @param request 设置全局样式请求
     * @return 视频工程
     */
    @PostMapping("/global-style")
    public BaseResponse<VideoProject> setGlobalStyle(@RequestBody SetGlobalStyleRequest request) {
        if (request.getProjectId() == null) {
            return BaseResponse.error("项目ID不能为空");
        }
        if (request.getStyleId() == null) {
            return BaseResponse.error("样式ID不能为空");
        }

        VideoProject videoProject = videoProjectService.setGlobalStyle(request);
        return BaseResponse.success(videoProject);
    }

    /**
     * 添加图片素材
     * @param request 添加图片请求
     * @return 视频工程
     */
    @PostMapping("/image")
    public BaseResponse<VideoProject> addImage(@RequestBody AddImageRequest request) {
        if (request.getProjectId() == null) {
            return BaseResponse.error("项目ID不能为空");
        }
        if (request.getImageId() == null) {
            return BaseResponse.error("图片ID不能为空");
        }
        if (request.getImageUrl() == null) {
            return BaseResponse.error("图片URL不能为空");
        }

        VideoProject videoProject = videoProjectService.addImage(request);
        return BaseResponse.success(videoProject);
    }

    /**
     * 添加视频素材
     * @param request 添加视频请求
     * @return 视频工程
     */
    @PostMapping("/video")
    public BaseResponse<VideoProject> addVideo(@RequestBody AddVideoRequest request) {
        if (request.getProjectId() == null) {
            return BaseResponse.error("项目ID不能为空");
        }
        if (request.getVideoId() == null) {
            return BaseResponse.error("视频ID不能为空");
        }
        if (request.getVideoUrl() == null) {
            return BaseResponse.error("视频URL不能为空");
        }

        VideoProject videoProject = videoProjectService.addVideo(request);
        return BaseResponse.success(videoProject);
    }

    /**
     * 添加音频素材
     * @param request 添加音频请求
     * @return 视频工程
     */
    @PostMapping("/audio")
    public BaseResponse<VideoProject> addAudio(@RequestBody AddAudioRequest request) {
        if (request.getProjectId() == null) {
            return BaseResponse.error("项目ID不能为空");
        }
        if (request.getAudioId() == null) {
            return BaseResponse.error("音频ID不能为空");
        }
        if (request.getAudioUrl() == null) {
            return BaseResponse.error("音频URL不能为空");
        }

        VideoProject videoProject = videoProjectService.addAudio(request);
        return BaseResponse.success(videoProject);
    }

    /**
     * 添加文本素材
     * @param request 添加文本请求
     * @return 视频工程
     */
    @PostMapping("/text")
    public BaseResponse<VideoProject> addText(@RequestBody AddTextRequest request) {
        if (request.getProjectId() == null) {
            return BaseResponse.error("项目ID不能为空");
        }
        if (request.getText() == null) {
            return BaseResponse.error("文本内容不能为空");
        }

        VideoProject videoProject = videoProjectService.addText(request);
        return BaseResponse.success(videoProject);
    }

    /**
     * 添加文本模板素材
     * @param request 添加文本模板请求
     * @return 视频工程
     */
    @PostMapping("/text-template")
    public BaseResponse<VideoProject> addTextTemplate(@RequestBody AddTextTemplateRequest request) {
        if (request.getProjectId() == null) {
            return BaseResponse.error("项目ID不能为空");
        }
        if (request.getTemplateId() == null) {
            return BaseResponse.error("模板ID不能为空");
        }
        if (request.getTexts() == null || request.getTexts().isEmpty()) {
            return BaseResponse.error("文本内容不能为空");
        }

        VideoProject videoProject = videoProjectService.addTextTemplate(request);
        return BaseResponse.success(videoProject);
    }

    /**
     * 添加贴纸素材
     * @param request 添加贴纸请求
     * @return 视频工程
     */
    @PostMapping("/sticker")
    public BaseResponse<VideoProject> addSticker(@RequestBody AddStickerRequest request) {
        if (request.getProjectId() == null) {
            return BaseResponse.error("项目ID不能为空");
        }
        if (request.getStickerId() == null) {
            return BaseResponse.error("贴纸ID不能为空");
        }

        VideoProject videoProject = videoProjectService.addSticker(request);
        return BaseResponse.success(videoProject);
    }

    /**
     * 添加视频特效
     * @param request 添加视频特效请求
     * @return 视频工程
     */
    @PostMapping("/video-effect")
    public BaseResponse<VideoProject> addVideoEffect(@RequestBody AddVideoEffectRequest request) {
        if (request.getProjectId() == null) {
            return BaseResponse.error("项目ID不能为空");
        }
        if (request.getEffectId() == null) {
            return BaseResponse.error("特效ID不能为空");
        }

        VideoProject videoProject = videoProjectService.addVideoEffect(request);
        return BaseResponse.success(videoProject);
    }

    /**
     * 添加人脸特效
     * @param request 添加人脸特效请求
     * @return 视频工程
     */
    @PostMapping("/face-effect")
    public BaseResponse<VideoProject> addFaceEffect(@RequestBody AddFaceEffectRequest request) {
        if (request.getProjectId() == null) {
            return BaseResponse.error("项目ID不能为空");
        }
        if (request.getEffectId() == null) {
            return BaseResponse.error("特效ID不能为空");
        }

        VideoProject videoProject = videoProjectService.addFaceEffect(request);
        return BaseResponse.success(videoProject);
    }

    /**
     * 添加音效
     * @param request 添加音效请求
     * @return 视频工程
     */
    @PostMapping("/sound")
    public BaseResponse<VideoProject> addSound(@RequestBody AddSoundRequest request) {
        if (request.getProjectId() == null) {
            return BaseResponse.error("项目ID不能为空");
        }
        if (request.getSoundId() == null) {
            return BaseResponse.error("音效ID不能为空");
        }

        VideoProject videoProject = videoProjectService.addSound(request);
        return BaseResponse.success(videoProject);
    }
}
