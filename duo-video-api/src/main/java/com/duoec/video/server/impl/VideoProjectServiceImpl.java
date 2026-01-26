package com.duoec.video.server.impl;

import com.duoec.base.core.util.SnowflakeIdUtils;
import com.duoec.video.builder.ProjectBuilder;
import com.duoec.video.dto.request.*;
import com.duoec.video.project.VideoProject;
import com.duoec.video.project.material.TextStyle;
import com.duoec.video.server.VideoProjectService;
import com.duoec.video.server.VideoProjectStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * 视频工程服务实现类
 */
@Service
@RequiredArgsConstructor
public class VideoProjectServiceImpl implements VideoProjectService {
    private final VideoProjectStorageService videoProjectStorageService;

    @Override
    public VideoProject createProject(CreateProjectRequest request) {
        long projectId = Optional.ofNullable(request.getProjectId()).orElse(SnowflakeIdUtils.nextTmpId());
        ProjectBuilder projectBuilder = ProjectBuilder.createProject(
                projectId,
                Optional.ofNullable(request.getProjectName()).orElse(String.valueOf(projectId)),
                Optional.ofNullable(request.getWidth()).orElse(1080),
                Optional.ofNullable(request.getHeight()).orElse(1920)
        );

        // 设置测试模式
        if (request.getTest() != null && request.getTest()) {
            projectBuilder.setTest(true);
        }

        VideoProject project = projectBuilder.getProject();
        videoProjectStorageService.save(project);
        return project;
    }

    @Override
    public VideoProject setGlobalStyle(SetGlobalStyleRequest request) {
        VideoProject project = getProjectBuilder(request.getProjectId())
                .buildGlobalStyle(
                        request.getStyleId(),
                        Optional.ofNullable(request.getTextStyle()).orElse(new TextStyle()),
                        styleBuilder -> {
                            if (request.getGlobalKeywordStyle() != null) {
                                styleBuilder.setGlobalKeywordStyle(request.getGlobalKeywordStyle());
                            }
                        }
                ).getProject();
        videoProjectStorageService.save(project);
        return project;
    }

    @Override
    public VideoProject addImage(AddImageRequest request) {
        int scriptIndex = Optional.ofNullable(request.getScriptIndex()).orElse(0);
        VideoProject project = getProjectBuilder(request.getProjectId())
                .buildScript(
                        scriptIndex,
                        scriptBuilder -> {
                            scriptBuilder.buildNewImage(
                                    request.getImageId(),
                                    request.getImageUrl(),
                                    Optional.ofNullable(request.getStartTime()).orElse(0L),
                                    Optional.ofNullable(request.getDuration()).orElse(3000L),
                                    imageBuilder -> {
                                        if (request.getLayoutIndex() != null) {
                                            imageBuilder.setLayoutIndex(request.getLayoutIndex());
                                        }
                                        if (request.getZoomX() != null && request.getZoomY() != null) {
                                            imageBuilder.setZoom(request.getZoomX(), request.getZoomY());
                                        }
                                        if (request.getPositionX() != null && request.getPositionY() != null) {
                                            imageBuilder.setPosition(request.getPositionX(), request.getPositionY());
                                        }
                                        if (request.getRotate() != null) {
                                            imageBuilder.setRotate(request.getRotate());
                                        }
                                        if (request.getVisible() != null) {
                                            imageBuilder.setVisible(request.getVisible());
                                        }
                                        if (request.getHorizontal() != null) {
                                            imageBuilder.setHorizontal(request.getHorizontal());
                                        }
                                        if (request.getVertical() != null) {
                                            imageBuilder.setVertical(request.getVertical());
                                        }
                                    }
                            );
                        }
                ).getProject();
        videoProjectStorageService.save(project);
        return project;
    }

    @Override
    public VideoProject addVideo(AddVideoRequest request) {
        int scriptIndex = Optional.ofNullable(request.getScriptIndex()).orElse(0);
        VideoProject project = getProjectBuilder(request.getProjectId())
                .buildScript(
                        scriptIndex,
                        scriptBuilder -> {
                            scriptBuilder.buildNewVideo(
                                    request.getVideoId(),
                                    request.getVideoUrl(),
                                    Optional.ofNullable(request.getStartTime()).orElse(0L),
                                    Optional.ofNullable(request.getDuration()).orElse(3000L),
                                    videoBuilder -> {
                                        if (request.getMaterialStart() != null) {
                                            videoBuilder.setMaterialStart(request.getMaterialStart());
                                        }
                                        if (request.getMaterialTimeStart() != null && request.getMaterialTimeEnd() != null) {
                                            videoBuilder.setMaterialTime(request.getMaterialTimeStart(), request.getMaterialTimeEnd());
                                        }
                                        if (request.getLayoutIndex() != null) {
                                            videoBuilder.setLayoutIndex(request.getLayoutIndex());
                                        }
                                        if (request.getSpeed() != null) {
                                            videoBuilder.setSpeed(request.getSpeed());
                                        }
                                        if (request.getZoomX() != null && request.getZoomY() != null) {
                                            videoBuilder.setZoom(request.getZoomX(), request.getZoomY());
                                        }
                                        if (request.getRotate() != null) {
                                            videoBuilder.setRotate(request.getRotate());
                                        }
                                        if (request.getVisible() != null) {
                                            videoBuilder.setVisible(request.getVisible());
                                        }
                                        if (request.getHorizontal() != null) {
                                            videoBuilder.setHorizontal(request.getHorizontal());
                                        }
                                        if (request.getVolume() != null) {
                                            videoBuilder.setVolume(request.getVolume());
                                        }
                                        if (request.getTransitionId() != null && request.getTransitionDuration() != null) {
                                            videoBuilder.addTransition(request.getTransitionId(), request.getTransitionDuration());
                                        }
                                        // 如果指定了绿幕参数，添加绿幕背景
                                        if (request.getGreenBackground() != null
                                                && request.getGreenBackground().getGreenScreenId() != null
                                                && request.getGreenBackground().getGreenScreenUrl() != null) {
                                            GreenBackgroundParam greenBg = request.getGreenBackground();
                                            videoBuilder.buildGreenBackground(
                                                    greenBg.getGreenScreenId(),
                                                    greenBg.getGreenScreenUrl(),
                                                    backgroundBuilder -> {
                                                        if (greenBg.getChromaColor() != null) {
                                                            backgroundBuilder.setChroma(
                                                                    greenBg.getChromaColor(),
                                                                    Optional.ofNullable(greenBg.getChromaStrength()).orElse(20),
                                                                    Optional.ofNullable(greenBg.getChromaShadow()).orElse(10),
                                                                    Optional.ofNullable(greenBg.getChromaHighlight()).orElse(10)
                                                            );
                                                        }
                                                    }
                                            );
                                        }
                                        // 如果指定了蒙版参数，添加蒙版
                                        if (request.getMask() != null && request.getMask().getMaskId() != null) {
                                            MaskParam maskParam = request.getMask();
                                            videoBuilder.addMask(
                                                    maskParam.getMaskId(),
                                                    maskBuilder -> {
                                                        if (maskParam.getFeather() != null) {
                                                            maskBuilder.setFeather(maskParam.getFeather());
                                                        }
                                                        if (maskParam.getRotation() != null) {
                                                            maskBuilder.setRotation(maskParam.getRotation());
                                                        }
                                                        if (maskParam.getWidth() != null) {
                                                            maskBuilder.setWidth(maskParam.getWidth());
                                                        }
                                                        if (maskParam.getHeight() != null) {
                                                            maskBuilder.setHeight(maskParam.getHeight());
                                                        }
                                                        if (maskParam.getCenterX() != null) {
                                                            maskBuilder.setCenterX(maskParam.getCenterX());
                                                        }
                                                        if (maskParam.getCenterY() != null) {
                                                            maskBuilder.setCenterY(maskParam.getCenterY());
                                                        }
                                                        if (maskParam.getPointX() != null) {
                                                            maskBuilder.setPointX(maskParam.getPointX());
                                                        }
                                                        if (maskParam.getPointY() != null) {
                                                            maskBuilder.setPointY(maskParam.getPointY());
                                                        }
                                                    }
                                            );
                                        }
                                    }
                            );
                        }
                ).getProject();
        videoProjectStorageService.save(project);
        return project;
    }

    @Override
    public VideoProject addAudio(AddAudioRequest request) {
        int scriptIndex = Optional.ofNullable(request.getScriptIndex()).orElse(0);
        VideoProject project = getProjectBuilder(request.getProjectId())
                .buildScript(
                        scriptIndex,
                        scriptBuilder -> {
                            scriptBuilder.buildNewAudio(
                                    request.getAudioId(),
                                    request.getAudioUrl(),
                                    Optional.ofNullable(request.getStartTime()).orElse(0L),
                                    Optional.ofNullable(request.getDuration()).orElse(3000L),
                                    audioBuilder -> {
                                        if (request.getMaterialTimeStart() != null && request.getMaterialTimeEnd() != null) {
                                            audioBuilder.setMaterialTime(request.getMaterialTimeStart(), request.getMaterialTimeEnd());
                                        }
                                        if (request.getMaterialStart() != null) {
                                            audioBuilder.setMaterialStart(request.getMaterialStart());
                                        }
                                        if (request.getLayoutIndex() != null) {
                                            audioBuilder.setLayoutIndex(request.getLayoutIndex());
                                        }
                                        if (request.getSpeed() != null) {
                                            audioBuilder.setSpeed(request.getSpeed());
                                        }
                                        if (request.getVisible() != null) {
                                            audioBuilder.setVisible(request.getVisible());
                                        }
                                        if (request.getVolume() != null) {
                                            audioBuilder.setVolume(request.getVolume());
                                        }
                                    }
                            );
                        }
                ).getProject();
        videoProjectStorageService.save(project);
        return project;
    }

    @Override
    public VideoProject addText(AddTextRequest request) {
        int scriptIndex = Optional.ofNullable(request.getScriptIndex()).orElse(0);
        VideoProject project = getProjectBuilder(request.getProjectId())
                .buildScript(
                        scriptIndex,
                        scriptBuilder -> {
                            scriptBuilder.buildNewText(
                                    request.getText(),
                                    Optional.ofNullable(request.getStartTime()).orElse(0L),
                                    Optional.ofNullable(request.getDuration()).orElse(3000L),
                                    textBuilder -> {
                                        if (request.getLayoutIndex() != null) {
                                            textBuilder.setLayoutIndex(request.getLayoutIndex());
                                        }
                                        if (request.getPositionX() != null && request.getPositionY() != null) {
                                            textBuilder.setPosition(request.getPositionX(), request.getPositionY());
                                        }
                                        if (request.getRotate() != null) {
                                            textBuilder.setRotate(request.getRotate());
                                        }
                                        if (request.getAsSubtitle() != null) {
                                            textBuilder.setAsSubtitle(request.getAsSubtitle());
                                        }
                                        if (request.getStyle() != null) {
                                            textBuilder.setStyle(request.getStyle());
                                        }
                                        if (request.getStyleId() != null) {
                                            textBuilder.setStyleId(request.getStyleId());
                                        }
                                        if (request.getWordStyles() != null && !request.getWordStyles().isEmpty()) {
                                            for (AddTextRequest.WordStyleRequest wordStyle : request.getWordStyles()) {
                                                textBuilder.addWord(wordStyle.getStartIndex(), wordStyle.getLength(), wordTextBuilder -> {
                                                            if (wordStyle.getStyleId() != null) {
                                                                wordTextBuilder.setStyleId(wordStyle.getStyleId());
                                                            }
                                                            if (wordStyle.getFlowerId() != null) {
                                                                wordTextBuilder.setFlowerId(wordStyle.getFlowerId());
                                                            }
                                                            if (wordStyle.getFontSize() != null) {
                                                                wordTextBuilder.setFontSize(wordStyle.getFontSize());
                                                            }
                                                            if (wordStyle.getFillColor() != null) {
                                                                wordTextBuilder.setFillColor(wordStyle.getFillColor());
                                                            }
                                                            if (wordStyle.getStrokeWidth() != null) {
                                                                wordTextBuilder.setStrokeWidth(wordStyle.getStrokeWidth());
                                                            }
                                                            if (wordStyle.getStrokeColor() != null) {
                                                                wordTextBuilder.setStrokeColor(wordStyle.getStrokeColor());
                                                            }
                                                        }
                                                );
                                            }
                                        }
                                    }
                            );
                        }
                ).getProject();
        videoProjectStorageService.save(project);
        return project;
    }

    @Override
    public VideoProject addTextTemplate(AddTextTemplateRequest request) {
        int scriptIndex = Optional.ofNullable(request.getScriptIndex()).orElse(0);
        VideoProject project = getProjectBuilder(request.getProjectId())
                .buildScript(
                        scriptIndex,
                        scriptBuilder -> {
                            scriptBuilder.buildNewTextTemplate(
                                    request.getTemplateId(),
                                    request.getTexts(),
                                    Optional.ofNullable(request.getStartTime()).orElse(0L),
                                    Optional.ofNullable(request.getDuration()).orElse(3000L),
                                    textBuilder -> {
                                        if (request.getLayoutIndex() != null) {
                                            textBuilder.setLayoutIndex(request.getLayoutIndex());
                                        }
                                        if (request.getZoomX() != null && request.getZoomY() != null) {
                                            textBuilder.setZoom(request.getZoomX(), request.getZoomY());
                                        }
                                        if (request.getPositionX() != null && request.getPositionY() != null) {
                                            textBuilder.setPosition(request.getPositionX(), request.getPositionY());
                                        }
                                    }
                            );
                        }
                ).getProject();
        videoProjectStorageService.save(project);
        return project;
    }

    @Override
    public VideoProject addSticker(AddStickerRequest request) {
        int scriptIndex = Optional.ofNullable(request.getScriptIndex()).orElse(0);
        VideoProject project = getProjectBuilder(request.getProjectId())
                .buildScript(
                        scriptIndex,
                        scriptBuilder -> {
                            scriptBuilder.buildNewSticker(
                                    request.getStickerId(),
                                    Optional.ofNullable(request.getStartTime()).orElse(0L),
                                    Optional.ofNullable(request.getDuration()).orElse(3000L),
                                    stickerBuilder -> {
                                        if (request.getZoomX() != null && request.getZoomY() != null) {
                                            stickerBuilder.setZoom(request.getZoomX(), request.getZoomY());
                                        }
                                        if (request.getPositionX() != null && request.getPositionY() != null) {
                                            stickerBuilder.setPosition(request.getPositionX(), request.getPositionY());
                                        }
                                        if (request.getRotate() != null) {
                                            stickerBuilder.setRotate(request.getRotate());
                                        }
                                    }
                            );
                        }
                ).getProject();
        videoProjectStorageService.save(project);
        return project;
    }

    @Override
    public VideoProject addVideoEffect(AddVideoEffectRequest request) {
        int scriptIndex = Optional.ofNullable(request.getScriptIndex()).orElse(0);
        VideoProject project = getProjectBuilder(request.getProjectId())
                .buildScript(
                        scriptIndex,
                        scriptBuilder -> {
                            scriptBuilder.builderNewVideoEffect(
                                    request.getEffectId(),
                                    Optional.ofNullable(request.getStartTime()).orElse(0L),
                                    Optional.ofNullable(request.getDuration()).orElse(3000L),
                                    videoEffectBuilder -> {
                                        // 视频特效暂无额外配置
                                    }
                            );
                        }
                ).getProject();
        videoProjectStorageService.save(project);
        return project;
    }

    @Override
    public VideoProject addFaceEffect(AddFaceEffectRequest request) {
        int scriptIndex = Optional.ofNullable(request.getScriptIndex()).orElse(0);
        VideoProject project = getProjectBuilder(request.getProjectId())
                .buildScript(
                        scriptIndex,
                        scriptBuilder -> {
                            scriptBuilder.buildNewFaceEffect(
                                    request.getEffectId(),
                                    Optional.ofNullable(request.getStartTime()).orElse(0L),
                                    Optional.ofNullable(request.getDuration()).orElse(3000L),
                                    faceEffectBuilder -> {
                                        // 人脸特效暂无额外配置
                                    }
                            );
                        }
                ).getProject();
        videoProjectStorageService.save(project);
        return project;
    }

    @Override
    public VideoProject addSound(AddSoundRequest request) {
        int scriptIndex = Optional.ofNullable(request.getScriptIndex()).orElse(0);
        VideoProject project = getProjectBuilder(request.getProjectId())
                .buildScript(
                        scriptIndex,
                        scriptBuilder -> {
                            scriptBuilder.buildNewSound(
                                    request.getSoundId(),
                                    Optional.ofNullable(request.getStartTime()).orElse(0L),
                                    Optional.ofNullable(request.getDuration()).orElse(3000L),
                                    soundBuilder -> {
                                        // 音效暂无额外配置
                                    }
                            );
                        }
                ).getProject();
        videoProjectStorageService.save(project);
        return project;
    }

    private ProjectBuilder getProjectBuilder(long videoProjectId) {
        return ProjectBuilder.createProject(videoProjectStorageService.get(videoProjectId));
    }
}

