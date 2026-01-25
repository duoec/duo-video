package com.duoec.video.builder;

import com.duoec.base.exceptions.DuoServiceException;
import com.duoec.video.project.VideoScript;
import com.duoec.video.project.VideoTimeRange;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.function.Consumer;

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
        createNewScript(projectBuilder);
        return new ProjectScriptBuilder(projectBuilder, projectBuilder.getProject().getScripts().size() - 1);
    }

    public static ProjectScriptBuilder addAndGetScriptBuilder(ProjectBuilder projectBuilder, VideoTimeRange time) {
        createNewScript(projectBuilder, time);
        return new ProjectScriptBuilder(projectBuilder, projectBuilder.getProject().getScripts().size() - 1);
    }

    public ProjectScriptBuilder build(Consumer<ProjectScriptBuilder> projectScriptBuilderConsumer) {
        if (projectScriptBuilderConsumer != null) {
            projectScriptBuilderConsumer.accept(this);
        }
        return this;
    }

    /**
     * 创建一个视频片段，并获得 VideoBuilder
     * @param videoMaterialId 视频素材ID。注意，素材ID在系统层面应该是唯一的，比如它就是数据库里的一个ID。在后续的创作中，会以此ID为名称，缓存到本地。如果ID重复，会导致文件错乱！！
     */
    public ProjectVideoBuilder addVideoAndGetBuilder(long videoMaterialId, String videoUrl, long start, long duration) {
        return ProjectVideoBuilder.getBuilder(projectBuilder, this, videoMaterialId, videoUrl, start, duration);
    }

    /**
     * 创建一个视频片段，并获取 VideoBuilder 的上下文，以进一步编辑
     */
    public ProjectScriptBuilder buildNewVideo(long videoMaterialId, String videoUrl, long start, long duration, Consumer<ProjectVideoBuilder> projectVideoBuilderConsumer) {
        ProjectVideoBuilder videoBuilder = addVideoAndGetBuilder(videoMaterialId, videoUrl, start, duration);
        if (videoBuilder != null) {
            projectVideoBuilderConsumer.accept(videoBuilder);
        }
        videoBuilder.back();
        return this;
    }

    /**
     * 添加文本模板
     * @param textTemplateResourceId 文本模板ID
     * @param text 文本模板的内容(单块文本），需要根据文本模板确定是单行还是多行
     * @param start 展示起始时间（在整个视频中的时间），单位：毫秒
     * @param duration 展示时长，单位：毫秒
     */
    public ProjectTextTemplateBuilder addTextTemplateAndGetBuilder(long textTemplateResourceId, String text, long start, long duration) {
        return ProjectTextTemplateBuilder.getBuilder(projectBuilder, this, textTemplateResourceId, Lists.newArrayList(text), start, duration);
    }

    /**
     * 添加文本模板
     * @param textTemplateResourceId 文本模板ID
     * @param texts 文本模板的内容(多块文本），需要根据文本模板确定是单行还是多行
     * @param start 展示起始时间（在整个视频中的时间），单位：毫秒
     * @param duration 展示时长，单位：毫秒
     */
    public ProjectTextTemplateBuilder addTextTemplateAndGetBuilder(long textTemplateResourceId, List<String> texts, long start, long duration) {
        return ProjectTextTemplateBuilder.getBuilder(projectBuilder, this, textTemplateResourceId, texts, start, duration);
    }

    /**
     * 添加文本模板，并获得 ProjectScriptBuilder 上下文
     * @param textTemplateResourceId 文本模板ID
     * @param texts 文本模板的内容(多块文本），需要根据文本模板确定是单行还是多行
     * @param start 展示起始时间（在整个视频中的时间），单位：毫秒
     * @param duration 展示时长，单位：毫秒
     * @param textTemplateBuilderConsumer ProjectScriptBuilder 上下文构建器
     */
    public ProjectScriptBuilder buildNewTextTemplate(long textTemplateResourceId, List<String> texts, long start, long duration, Consumer<ProjectTextTemplateBuilder> textTemplateBuilderConsumer) {
        ProjectTextTemplateBuilder textTemplateBuilder = ProjectTextTemplateBuilder.getBuilder(projectBuilder, this, textTemplateResourceId, texts, start, duration);
        if (textTemplateBuilderConsumer != null) {
            textTemplateBuilderConsumer.accept(textTemplateBuilder);
        }
        textTemplateBuilder.back();
        return this;
    }

    /**
     * 添加贴纸
     * @param stickerResourceId 贴纸ID
     * @param start 展示起始时间（在整个视频中的时间），单位：毫秒
     * @param duration 展示时长，单位：毫秒
     */
    public ProjectStickerBuilder addStickerAndGetBuilder(long stickerResourceId, long start, long duration) {
        return ProjectStickerBuilder.getBuilder(projectBuilder, this, stickerResourceId, start, duration);
    }

    /**
     * 添加贴纸
     * @param stickerResourceId 贴纸ID
     * @param start 展示起始时间（在整个视频中的时间），单位：毫秒
     * @param duration 展示时长，单位：毫秒
     * @param stickerBuilderConsumer ProjectStickerBuilder 上下文构建器
     */
    public ProjectScriptBuilder buildNewSticker(long stickerResourceId, long start, long duration, Consumer<ProjectStickerBuilder> stickerBuilderConsumer) {
        ProjectStickerBuilder projectStickerBuilder = ProjectStickerBuilder.getBuilder(projectBuilder, this, stickerResourceId, start, duration);
        if (stickerBuilderConsumer != null) {
            stickerBuilderConsumer.accept(projectStickerBuilder);
        }
        projectStickerBuilder.back();
        return this;
    }

    /**
     * 添加画面特效
     * @param videoEffectResourceId 画面特效ID
     * @param start 展示起始时间（在整个视频中的时间），单位：毫秒
     * @param duration 展示时长，单位：毫秒
     */
    public ProjectVideoEffectBuilder addVideoEffectAndGetBuilder(long videoEffectResourceId, long start, long duration) {
        return ProjectVideoEffectBuilder.getBuilder(projectBuilder, this, videoEffectResourceId, start, duration);
    }

    /**
     * 添加脸部特效
     * @param faceEffectResourceId 脸部特效ID
     * @param start 展示起始时间（在整个视频中的时间），单位：毫秒
     * @param duration 展示时长，单位：毫秒
     */
    public ProjectFaceEffectBuilder addFaceEffectAndGetBuilder(long faceEffectResourceId, long start, long duration) {
        return ProjectFaceEffectBuilder.getBuilder(projectBuilder, this, faceEffectResourceId, start, duration);
    }

    /**
     * 添加画面特效
     * @param videoEffectResourceId 画面特效ID
     * @param start 展示起始时间（在整个视频中的时间），单位：毫秒
     * @param duration 展示时长，单位：毫秒
     */
    public ProjectScriptBuilder builderNewVideoEffect(long videoEffectResourceId, long start, long duration, Consumer<ProjectVideoEffectBuilder> videoEffectBuilderConsumer) {
        ProjectVideoEffectBuilder videoEffectBuilder = ProjectVideoEffectBuilder.getBuilder(projectBuilder, this, videoEffectResourceId, start, duration);
        if (videoEffectBuilderConsumer != null) {
            videoEffectBuilderConsumer.accept(videoEffectBuilder);
        }
        return this;
    }

    /**
     * 添加脸部特效
     * @param videoEffectResourceId 脸部特效ID
     * @param start 展示起始时间（在整个视频中的时间），单位：毫秒
     * @param duration 展示时长，单位：毫秒
     */
    public ProjectScriptBuilder builderNewFaceEffect(long videoEffectResourceId, long start, long duration, Consumer<ProjectFaceEffectBuilder> faceEffectBuilderConsumer) {
        ProjectFaceEffectBuilder faceEffectBuilder = ProjectFaceEffectBuilder.getBuilder(projectBuilder, this, videoEffectResourceId, start, duration);
        if (faceEffectBuilderConsumer != null) {
            faceEffectBuilderConsumer.accept(faceEffectBuilder);
        }
        return this;
    }

    /**
     * 添加特效音
     * @param soundResourceId 特效音ID
     * @param start 展示起始时间（在整个视频中的时间），单位：毫秒
     * @param duration 展示时长，单位：毫秒
     */
    public ProjectSoundBuilder addSoundAndGetBuilder(long soundResourceId, long start, long duration) {
        return ProjectSoundBuilder.getBuilder(projectBuilder, this, soundResourceId, start, duration);
    }

    /**
     * 添加特效音
     * @param soundResourceId 特效音ID
     * @param start 展示起始时间（在整个视频中的时间），单位：毫秒
     * @param duration 展示时长，单位：毫秒
     * @param soundBuilderConsumer ProjectSoundBuilder 上下文编辑器
     */
    public ProjectScriptBuilder buildNewSound(long soundResourceId, long start, long duration, Consumer<ProjectSoundBuilder> soundBuilderConsumer) {
        ProjectSoundBuilder soundBuilder = ProjectSoundBuilder.getBuilder(projectBuilder, this, soundResourceId, start, duration);
        if (soundBuilderConsumer != null) {
            soundBuilderConsumer.accept(soundBuilder);
        }
        soundBuilder.back();
        return this;
    }

    /**
     * 添加文本，并返回文本Builder
     * @param text 文本内容
     * @param start 展示开始时间，单位：毫秒
     * @param duration 展示持续时间，单位：毫秒
     */
    public ProjectTextBuilder addTextAndGetBuilder(String text, long start, long duration) {
        return ProjectTextBuilder.getBuilder(projectBuilder, this, text, start, duration);
    }

    /**
     * 添加文本
     * @param text 文本内容
     * @param start 展示开始时间，单位：毫秒
     * @param duration 展示持续时间，单位：毫秒
     * @param textBuilderConsumer ProjectTextBuilder 的上下文编辑器
     */
    public ProjectScriptBuilder buildNewText(String text, long start, long duration, Consumer<ProjectTextBuilder> textBuilderConsumer) {
        ProjectTextBuilder textBuilder = ProjectTextBuilder.getBuilder(projectBuilder, this, text, start, duration);
        if (textBuilderConsumer != null) {
            textBuilderConsumer.accept(textBuilder);
        }
        textBuilder.back();
        return this;
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

    private static VideoScript createNewScript(ProjectBuilder projectBuilder) {
        VideoScript videoScript = new VideoScript();

        List<VideoScript> scripts = projectBuilder.getProject().getScripts();
        scripts.add(videoScript);
        return videoScript;
    }

    private static VideoScript createNewScript(ProjectBuilder projectBuilder, VideoTimeRange time) {
        VideoScript videoScript = createNewScript(projectBuilder);
        videoScript.setTime(time);
        return videoScript;
    }
}
