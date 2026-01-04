package com.duoec.video.builder;

import com.duoec.base.core.util.SnowflakeIdUtils;
import com.duoec.video.project.VideoSegment;
import com.duoec.video.project.VideoTimeRange;
import com.duoec.video.project.material.StickerMaterial;

public class ProjectStickerBuilder extends BaseMaterialBuilder<StickerMaterial, ProjectStickerBuilder> {
    private ProjectStickerBuilder(ProjectBuilder projectBuilder, ProjectScriptBuilder scriptBuilder) {
        this.projectBuilder = projectBuilder;
        this.scriptBuilder = scriptBuilder;
        this.script = scriptBuilder.getScript();
    }

    public static ProjectStickerBuilder getBuilder(ProjectBuilder projectBuilder, ProjectScriptBuilder scriptBuilder) {
        return new ProjectStickerBuilder(projectBuilder, scriptBuilder);
    }

    /**
     * 添加贴纸
     * @param stickerResourceId 贴纸ID
     * @param start 展示起始时间（在整个视频中的时间），单位：毫秒
     * @param duration 展示时长，单位：毫秒
     */
    public ProjectStickerBuilder add(long stickerResourceId, long start, long duration) {
        material = new StickerMaterial();
        material.setId(SnowflakeIdUtils.nextTmpId());
        material.setResourceId(stickerResourceId);

        videoTime = new VideoTimeRange(start, duration);

        segment = new VideoSegment();
        segment.setId(SnowflakeIdUtils.nextTmpId());
        segment.setMaterialId(material.getId());
        segment.setType("sticker");
        segment.setLayoutIndex(1000);

        return this;
    }
}
