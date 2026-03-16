package com.duoec.video.builder;

import com.duoec.base.core.util.SnowflakeIdUtils;
import com.duoec.video.project.VideoPoint;
import com.duoec.video.project.VideoSegment;
import com.duoec.video.project.VideoTimeRange;
import com.duoec.video.project.material.BaseTextMaterial;
import com.duoec.video.project.material.TextMaterial;
import com.google.common.collect.Lists;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.function.Consumer;

import static com.duoec.video.builder.ProjectGlobalTextStyleBuilder.addAndGetBuilder;

public class ProjectTextBuilder extends BaseSegmentBuilder<TextMaterial, ProjectTextBuilder> {
    private BaseTextMaterial.TextStyle style;

    private Long styleId;
    private List<BaseTextMaterial.TextWord> words;

    private ProjectTextBuilder(ProjectBuilder projectBuilder, ProjectScriptBuilder scriptBuilder) {
        this.projectBuilder = projectBuilder;
        this.scriptBuilder = scriptBuilder;
        this.script = scriptBuilder.getScript();
    }

    public static ProjectTextBuilder getBuilder(ProjectBuilder projectBuilder, ProjectScriptBuilder scriptBuilder, String text, long start, long duration) {
        return new ProjectTextBuilder(projectBuilder, scriptBuilder).add(text, start, duration);
    }

    /**
     * 设置为字幕
     */
    public ProjectTextBuilder setAsSubtitle(boolean isSubtitle) {
        material.setType(isSubtitle ? "subtitle" : "text");
        return this;
    }

    /**
     * 设置公共样式
     */
    public ProjectTextBuilder setStyle(BaseTextMaterial.TextStyle style) {
        this.style = style;
        return this;
    }

    /**
     * 设置预设样式
     */
    public ProjectTextBuilder setStyleId(long styleId) {
        this.styleId = styleId;
        return this;
    }

    /**
     * 添加一个文本
     * @param text 文本内容
     * @param start 展示起始时间（在整个视频中的时间），单位：毫秒
     * @param duration 展示时长，单位：毫秒
     */
    private ProjectTextBuilder add(String text, long start, long duration) {
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

    /**
     * 设置文本片段样式，会直接覆盖之前设置的所有 words
     */
    public ProjectTextBuilder setWords(List<BaseTextMaterial.TextWord> words) {
        this.words = words;
        return this;
    }

    public ProjectTextBuilder addWord(int index, int length, Consumer<ProjectTextStyleBuilder<BaseTextMaterial.TextWord>> textWordBuilderConsumer) {
        if (this.words == null) {
            this.words = Lists.newArrayList();
        }
        BaseTextMaterial.TextWord textWord = new BaseTextMaterial.TextWord()
                .setIndex(index)
                .setLength(length);
        words.add(textWord);

        ProjectTextStyleBuilder<BaseTextMaterial.TextWord> textWordBuilder = ProjectTextStyleBuilder.build(textWord);
        if (textWordBuilderConsumer != null) {
            textWordBuilderConsumer.accept(textWordBuilder);
        }
        return this;
    }

    @Override
    protected void beforeBack() {
        if (styleId != null) {
            material.setStyleId(styleId);
        }
        if (!CollectionUtils.isEmpty(words)) {
            material.setWords(words);
        }
    }
}
