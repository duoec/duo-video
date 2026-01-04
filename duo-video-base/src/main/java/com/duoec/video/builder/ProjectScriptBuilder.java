package com.duoec.video.builder;

import com.duoec.base.exceptions.DuoServiceException;
import com.duoec.video.project.VideoScript;
import com.duoec.video.project.VideoTimeRange;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

public class ProjectScriptBuilder {
    private final ProjectBuilder projectBuilder;
    private final VideoScript script;
    private VideoTimeRange time;

    private ProjectScriptBuilder(ProjectBuilder projectBuilder, int scriptIndex) {
        this.projectBuilder = projectBuilder;
        List<VideoScript> scripts = projectBuilder.getProject().getScripts();
        if (scripts.size() <= scriptIndex) {
            throw new DuoServiceException("分镜索引号错误：scriptIndex=" + scriptIndex);
        }

        script = scripts.get(scriptIndex);
    }

    public static ProjectScriptBuilder getBuilder(ProjectBuilder projectBuilder, int scriptIndex) {
        return new ProjectScriptBuilder(projectBuilder, scriptIndex);
    }

    public static ProjectScriptBuilder addAndGetScriptBuilder(ProjectBuilder projectBuilder) {
        VideoScript videoScript = new VideoScript();
        videoScript.setSegments(new ArrayList<>());

        List<VideoScript> scripts = projectBuilder.getProject().getScripts();
        scripts.add(videoScript);
        return new ProjectScriptBuilder(projectBuilder, scripts.size() - 1);
    }

    /**
     * 获取 VideoBuilder
     */
    public ProjectVideoBuilder getVideoBuilder() {
        return ProjectVideoBuilder.getBuilder(projectBuilder, this);
    }

    public ProjectVideoBuilder addVideoAndGetBuilder(long videoMaterialId, String videoUrl, long start, long duration) {
        return getVideoBuilder()
                .add(videoMaterialId, videoUrl, start, duration);
    }

    /**
     * 获取创建文本模板的Builder
     */
    public ProjectTextTemplateBuilder getTextTemplateBuilder() {
        return ProjectTextTemplateBuilder.getBuilder(projectBuilder, this);
    }

    /**
     * 添加文本模板
     * @param textTemplateResourceId 文本模板ID
     * @param text 文本模板的内容(单块文本），需要根据文本模板确定是单行还是多行
     * @param start 展示起始时间（在整个视频中的时间），单位：毫秒
     * @param duration 展示时长，单位：毫秒
     */
    public ProjectTextTemplateBuilder addTextTemplateAndGetBuilder(long textTemplateResourceId, String text, long start, long duration) {
        return getTextTemplateBuilder()
                .add(textTemplateResourceId, Lists.newArrayList(text), start, duration);
    }

    /**
     * 添加文本模板
     * @param textTemplateResourceId 文本模板ID
     * @param texts 文本模板的内容(多块文本），需要根据文本模板确定是单行还是多行
     * @param start 展示起始时间（在整个视频中的时间），单位：毫秒
     * @param duration 展示时长，单位：毫秒
     */
    public ProjectTextTemplateBuilder addTextTemplateAndGetBuilder(long textTemplateResourceId, List<String> texts, long start, long duration) {
        return getTextTemplateBuilder()
                .add(textTemplateResourceId, texts, start, duration);
    }

    /**
     * 获取贴纸Builder
     */
    public ProjectStickerBuilder getProjectStickerBuilder() {
        return ProjectStickerBuilder.getBuilder(projectBuilder, this);
    }

    /**
     * 添加贴纸
     * @param stickerResourceId 贴纸ID
     * @param start 展示起始时间（在整个视频中的时间），单位：毫秒
     * @param duration 展示时长，单位：毫秒
     */
    public ProjectStickerBuilder addStickerAndGetBuilder(long stickerResourceId, long start, long duration) {
        return getProjectStickerBuilder()
                .add(stickerResourceId, start, duration);
    }

    /**
     * 获取特效音Builder
     */
    public ProjectSoundBuilder getProjectSoundBuilder() {
        return ProjectSoundBuilder.getBuilder(projectBuilder, this);
    }

    /**
     * 添加特效音
     * @param soundResourceId 特效音ID
     * @param start 展示起始时间（在整个视频中的时间），单位：毫秒
     * @param duration 展示时长，单位：毫秒
     */
    public ProjectSoundBuilder addSoundAndGetBuilder(long soundResourceId, long start, long duration) {
        return getProjectSoundBuilder()
                .add(soundResourceId, start, duration);
    }

    /**
     * 获取文本Builder
     */
    public ProjectTextBuilder getProjectTextBuilder() {
        return ProjectTextBuilder.getBuilder(projectBuilder, this);
    }

    /**
     * 添加文本，并返回文本Builder
     * @param text 文本内容
     * @param start 展示开始时间，单位：毫秒
     * @param duration 展示持续时间，单位：毫秒
     */
    public ProjectTextBuilder addTextAndGetBuilder(String text, long start, long duration) {
        return getProjectTextBuilder()
                .add(text, start, duration);
    }

    public ProjectScriptBuilder setTime(long start, long duration) {
        if (duration <= 0L) {
            throw new DuoServiceException("分镜持续时长不能小于0！");
        }
        if (start < 0L) {
            throw new DuoServiceException("分镜开始时间不能小于0！");
        }
        this.time = new VideoTimeRange(start, duration);
        return this;
    }

    /**
     * 返回到 ProjectBuilder
     */
    public ProjectBuilder back() {
        if (time != null) {
            script.setTime(time);
        }
        return projectBuilder;
    }

    /**
     * 获取当前分镜
     */
    public VideoScript getScript() {
        return script;
    }
}
