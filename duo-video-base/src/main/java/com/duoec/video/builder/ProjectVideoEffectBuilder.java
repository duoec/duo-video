package com.duoec.video.builder;

import com.duoec.base.core.util.SnowflakeIdUtils;
import com.duoec.video.project.VideoSegment;
import com.duoec.video.project.VideoTimeRange;
import com.duoec.video.project.material.MaterialTypeEnum;
import com.duoec.video.project.material.VideoEffectMaterial;

public class ProjectVideoEffectBuilder extends BaseMaterialBuilder<VideoEffectMaterial, ProjectVideoEffectBuilder> {
    private ProjectVideoEffectBuilder(ProjectBuilder projectBuilder, ProjectScriptBuilder scriptBuilder) {
        this.projectBuilder = projectBuilder;
        this.scriptBuilder = scriptBuilder;
        this.script = scriptBuilder.getScript();
    }

    public static ProjectVideoEffectBuilder getBuilder(ProjectBuilder projectBuilder, ProjectScriptBuilder scriptBuilder) {
        return new ProjectVideoEffectBuilder(projectBuilder, scriptBuilder);
    }

    /**
     * 添加画面特效
     * @param videoEffectResourceId 画面特效ID
     * @param start 展示起始时间（在整个视频中的时间），单位：毫秒
     * @param duration 展示时长，单位：毫秒
     */
    public ProjectVideoEffectBuilder addVideoEffect(long videoEffectResourceId, long start, long duration) {
        addProjectVideoEffectBuilder(MaterialTypeEnum.MATERIAL_TYPE_VIDEO_EFFECT, videoEffectResourceId, start, duration);
        return this;
    }

    /**
     * 添加脸部特效
     * @param faceEffectResourceId 脸部特效ID
     * @param start 展示起始时间（在整个视频中的时间），单位：毫秒
     * @param duration 展示时长，单位：毫秒
     */
    public ProjectVideoEffectBuilder addFaceEffect(long faceEffectResourceId, long start, long duration) {
        addProjectVideoEffectBuilder(MaterialTypeEnum.MATERIAL_TYPE_FACE_EFFECT, faceEffectResourceId, start, duration);
        return this;
    }

    private void addProjectVideoEffectBuilder(String type, long videoEffectResourceId, long start, long duration) {
        material = new VideoEffectMaterial();
        material.setId(SnowflakeIdUtils.nextTmpId());
        material.setResourceId(videoEffectResourceId);

        videoTime = new VideoTimeRange(start, duration);

        segment = new VideoSegment();
        segment.setId(SnowflakeIdUtils.nextTmpId());
        segment.setMaterialId(material.getId());
        segment.setType(type);
        segment.setLayoutIndex(1000);
    }
}
