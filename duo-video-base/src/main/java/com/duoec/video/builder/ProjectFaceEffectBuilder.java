package com.duoec.video.builder;

import com.duoec.base.core.util.SnowflakeIdUtils;
import com.duoec.video.project.VideoSegment;
import com.duoec.video.project.VideoTimeRange;
import com.duoec.video.project.material.MaterialTypeEnum;
import com.duoec.video.project.material.VideoEffectMaterial;

public class ProjectFaceEffectBuilder extends BaseSegmentBuilder<VideoEffectMaterial, ProjectFaceEffectBuilder> {
    private ProjectFaceEffectBuilder(ProjectBuilder projectBuilder, ProjectScriptBuilder scriptBuilder) {
        this.projectBuilder = projectBuilder;
        this.scriptBuilder = scriptBuilder;
        this.script = scriptBuilder.getScript();
    }

    public static ProjectFaceEffectBuilder getBuilder(ProjectBuilder projectBuilder, ProjectScriptBuilder scriptBuilder, long faceEffectResourceId, long start, long duration) {
        return new ProjectFaceEffectBuilder(projectBuilder, scriptBuilder).add(faceEffectResourceId, start, duration);
    }

    /**
     * 添加脸部特效
     * @param faceEffectResourceId 脸部特效ID
     * @param start 展示起始时间（在整个视频中的时间），单位：毫秒
     * @param duration 展示时长，单位：毫秒
     */
    private ProjectFaceEffectBuilder add(long faceEffectResourceId, long start, long duration) {
        material = new VideoEffectMaterial();
        material.setId(SnowflakeIdUtils.nextTmpId());
        material.setResourceId(faceEffectResourceId);

        videoTime = new VideoTimeRange(start, duration);

        segment = new VideoSegment();
        segment.setId(SnowflakeIdUtils.nextTmpId());
        segment.setMaterialId(material.getId());
        segment.setType(MaterialTypeEnum.MATERIAL_TYPE_FACE_EFFECT);
        segment.setLayoutIndex(1000);
        return this;
    }
}
