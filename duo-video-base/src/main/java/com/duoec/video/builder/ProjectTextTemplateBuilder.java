package com.duoec.video.builder;

import com.duoec.base.core.util.SnowflakeIdUtils;
import com.duoec.video.project.VideoPoint;
import com.duoec.video.project.VideoSegment;
import com.duoec.video.project.VideoTimeRange;
import com.duoec.video.project.material.TextTemplateMaterial;

import java.util.List;

public class ProjectTextTemplateBuilder extends BaseSegmentBuilder<TextTemplateMaterial, ProjectTextTemplateBuilder> {
    private ProjectTextTemplateBuilder(ProjectBuilder projectBuilder, ProjectScriptBuilder scriptBuilder, long textTemplateResourceId, List<String> texts, long start, long duration) {
        this.projectBuilder = projectBuilder;
        this.scriptBuilder = scriptBuilder;
        this.script = scriptBuilder.getScript();
        add(textTemplateResourceId, texts, start, duration);
    }

    public static ProjectTextTemplateBuilder getBuilder(ProjectBuilder projectBuilder, ProjectScriptBuilder scriptBuilder, long textTemplateResourceId, List<String> texts, long start, long duration) {
        return new ProjectTextTemplateBuilder(projectBuilder, scriptBuilder, textTemplateResourceId, texts, start, duration);
    }

    /**
     * 添加文本模板
     * @param textTemplateResourceId 文本模板ID
     * @param texts 文本模板的内容，支持多块文本内容
     * @param start 展示起始时间（在整个视频中的时间），单位：毫秒
     * @param duration 展示时长，单位：毫秒
     */
    private ProjectTextTemplateBuilder add(long textTemplateResourceId, List<String> texts, long start, long duration) {
        material = new TextTemplateMaterial();
        material.setId(SnowflakeIdUtils.nextTmpId());
        material.setTexts(texts);
        material.setResourceId(textTemplateResourceId);

        videoTime = new VideoTimeRange(start, duration);

        segment = new VideoSegment();
        segment.setId(SnowflakeIdUtils.nextTmpId());
        segment.setMaterialId(material.getId());
        segment.setType("text");
        segment.setLayoutIndex(1000);

        videoPoint = new VideoPoint(0, 0);

        return this;
    }
}
