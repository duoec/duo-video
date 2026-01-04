package com.duoec.video.builder;

import com.duoec.base.core.util.SnowflakeIdUtils;
import com.duoec.video.project.VideoSegment;
import com.duoec.video.project.VideoTimeRange;
import com.duoec.video.project.material.SoundMaterial;

public class ProjectSoundBuilder extends BaseMaterialBuilder<SoundMaterial, ProjectSoundBuilder> {
    private ProjectSoundBuilder(ProjectBuilder projectBuilder, ProjectScriptBuilder scriptBuilder) {
        this.projectBuilder = projectBuilder;
        this.scriptBuilder = scriptBuilder;
        this.script = scriptBuilder.getScript();
    }

    public static ProjectSoundBuilder getBuilder(ProjectBuilder projectBuilder, ProjectScriptBuilder scriptBuilder) {
        return new ProjectSoundBuilder(projectBuilder, scriptBuilder);
    }

    /**
     * 添加特效音
     * @param soundResourceId 音效ID
     * @param start 展示起始时间（在整个视频中的时间），单位：毫秒
     * @param duration 展示时长，单位：毫秒
     */
    public ProjectSoundBuilder add(long soundResourceId, long start, long duration) {
        material = new SoundMaterial();
        material.setId(SnowflakeIdUtils.nextTmpId());
        material.setResourceId(soundResourceId);

        videoTime = new VideoTimeRange(start, duration);

        segment = new VideoSegment();
        segment.setId(SnowflakeIdUtils.nextTmpId());
        segment.setMaterialId(material.getId());
        segment.setType("sound");
        segment.setLayoutIndex(1000);

        return this;
    }
}
