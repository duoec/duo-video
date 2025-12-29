package com.duoec.video.builder;

import com.duoec.base.core.util.SnowflakeIdUtils;
import com.duoec.base.exceptions.DuoServiceException;
import com.duoec.video.project.VideoPoint;
import com.duoec.video.project.VideoScript;
import com.duoec.video.project.VideoSegment;
import com.duoec.video.project.VideoTimeRange;
import com.duoec.video.project.material.TextMaterial;
import com.duoec.video.project.material.TextStyle;

import java.util.List;

public class VideoProjectTextBuilder extends BaseMaterialBuilder<TextMaterial, VideoProjectTextBuilder> {
    private TextStyle style;

    private VideoProjectTextBuilder(VideoProjectBuilder videoProjectBuilder, int scriptIndex) {
        this.videoProjectBuilder = videoProjectBuilder;

        List<VideoScript> scripts = videoProjectBuilder.getProject().getScripts();
        if (scripts.size() <= scriptIndex) {
            throw new DuoServiceException("分镜索引号错误：scriptIndex=" + scriptIndex);
        }

        script = scripts.get(scriptIndex);
    }

    public static VideoProjectTextBuilder getBuilder(VideoProjectBuilder videoProjectBuilder, int scriptIndex) {
        return new VideoProjectTextBuilder(videoProjectBuilder, scriptIndex);
    }

    /**
     * 设置为字幕
     */
    public VideoProjectTextBuilder setAsSubtitle(boolean isSubtitle) {
        material.setType(isSubtitle ? "subtitle" : "text");
        return this;
    }

    /**
     * 设置公共样式
     */
    public VideoProjectTextBuilder setStyle(TextStyle style) {
        this.style = style;
        return this;
    }

    /**
     * 添加一个文本
     * @param text 文本内容
     * @param start 展示起始时间（在整个视频中的时间），单位：毫秒
     * @param duration 展示时长，单位：毫秒
     */
    public VideoProjectTextBuilder add(String text, long start, long duration) {
        material = new TextMaterial();
        material.setId(SnowflakeIdUtils.nextTmpId());
        material.setText(text);
        if (style != null) {
            material.setStyle(style);
        }

        videoTime = new VideoTimeRange(start, duration);
        videoPoint = new VideoPoint(0, 0);

        segment = new VideoSegment();
        segment.setId(SnowflakeIdUtils.nextTmpId());
        segment.setMaterialId(material.getId());
        segment.setType("text");
        segment.setLayoutIndex(1000);

        return this;
    }
}
