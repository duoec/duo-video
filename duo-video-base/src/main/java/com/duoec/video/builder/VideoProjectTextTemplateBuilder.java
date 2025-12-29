package com.duoec.video.builder;

import com.duoec.base.core.util.SnowflakeIdUtils;
import com.duoec.base.exceptions.DuoServiceException;
import com.duoec.video.project.VideoPoint;
import com.duoec.video.project.VideoScript;
import com.duoec.video.project.VideoSegment;
import com.duoec.video.project.VideoTimeRange;
import com.duoec.video.project.material.TextTemplateMaterial;

import java.util.ArrayList;
import java.util.List;

public class VideoProjectTextTemplateBuilder extends BaseMaterialBuilder<TextTemplateMaterial, VideoProjectTextTemplateBuilder> {
    private VideoProjectTextTemplateBuilder(VideoProjectBuilder videoProjectBuilder, int scriptIndex) {
        this.videoProjectBuilder = videoProjectBuilder;

        List<VideoScript> scripts = videoProjectBuilder.getProject().getScripts();
        if (scripts.size() <= scriptIndex) {
            throw new DuoServiceException("分镜索引号错误：scriptIndex=" + scriptIndex);
        }

        script = scripts.get(scriptIndex);
    }

    public static VideoProjectTextTemplateBuilder getBuilder(VideoProjectBuilder videoProjectBuilder, int scriptIndex) {
        return new VideoProjectTextTemplateBuilder(videoProjectBuilder, scriptIndex);
    }

    /**
     * 添加文本模板
     * @param textTemplateResourceId 文本模板ID
     * @param text 文本模板的内容(单块文本）
     * @param start 展示起始时间（在整个视频中的时间），单位：毫秒
     * @param duration 展示时长，单位：毫秒
     */
    public VideoProjectTextTemplateBuilder add(long textTemplateResourceId, String text, long start, long duration) {
        List<String> texts = new ArrayList<>();
        texts.add(text);
        return add(textTemplateResourceId, texts, start, duration);
    }

    /**
     * 添加文本模板
     * @param textTemplateResourceId 文本模板ID
     * @param texts 文本模板的内容，支持多块文本内容
     * @param start 展示起始时间（在整个视频中的时间），单位：毫秒
     * @param duration 展示时长，单位：毫秒
     */
    public VideoProjectTextTemplateBuilder add(long textTemplateResourceId, List<String> texts, long start, long duration) {
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
