package com.duoec.video.jy.builder.segment;

import com.duoec.base.core.DuoServerConsts;
import com.duoec.base.core.util.JsonUtils;
import com.duoec.video.jy.JianyingProjectBuildState;
import com.duoec.video.jy.builder.JianyingTrackBuilder;
import com.duoec.video.jy.dto.BaseResource;
import com.duoec.video.jy.dto.JyResource;
import com.duoec.video.jy.dto.font.*;
import com.duoec.video.jy.dto.font.Font;
import com.duoec.video.jy.dto.info.*;
import com.duoec.video.jy.utils.JianyingResourceUtils;
import com.duoec.video.jy.utils.JianyingUtils;
import com.duoec.video.jy.utils.UuidUtils;
import com.duoec.video.project.VideoScript;
import com.duoec.video.project.VideoSegment;
import com.duoec.video.project.VideoTimeRange;
import com.duoec.video.project.material.MaterialTypeEnum;
import com.duoec.video.project.material.TextMaterial;
import com.duoec.video.project.material.TextStyle;
import com.duoec.video.project.material.TextWord;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.File;
import java.util.*;

public class TextSegmentBuilder extends BaseSegmentBuilder<TextMaterial> {
    private static final Logger logger = LoggerFactory.getLogger(TextSegmentBuilder.class);
    private static final String STR_SUBTITLE = "subtitle";
    private static final String STR_TEXT = "text";
    private static final String TRACK_NAME_SUBTITLE = "字幕";
    private static final String TRACK_NAME_TEXT = "文本";
    private static final TextStyle DEFAULT_STYLE = new TextStyle()
            .setFontName(JianyingResourceUtils.DEFAULT_FONT_NAME)
            .setFontSize(5)
            .setFillColor(JianyingResourceUtils.DEFAULT_FILL_COLOR);

    @Override
    public MaterialTypeEnum getMaterialType() {
        return MaterialTypeEnum.TEXT_MATERIAL;
    }

    @Override
    public Segment build(JianyingProjectBuildState state, VideoScript videoScript, VideoSegment videoSegment, TextMaterial material) {
        boolean isSubtitle = MaterialTypeEnum.MATERIAL_TYPE_SUBTITLE.equalsIgnoreCase(material.getTextType());
        Text text = JianyingUtils.getDefaultText()
                .setId(UuidUtils.next())
                .setType(isSubtitle ? STR_SUBTITLE : STR_TEXT);

        List<TextWord> words = Optional.ofNullable(material.getWords()).orElse(List.of());

        List<Style> styles = new ArrayList<>();
        FontConfig content = JianyingUtils.getDefaultFontConfig()
                .setText(material.getText())
                .setStyles(styles);

        TextStyle globalStyle = Optional.ofNullable(material.getStyle()).orElse(DEFAULT_STYLE);
        if (CollectionUtils.isEmpty(words)) {
            TextWord word = globalStyle.toTextWidgetWord(0, material.getText().length());
            Style style = addText(state, material, word);
            if (style != null) {
                styles.add(style);
            }
        } else {
            List<TextWord> mergedWords = mergeWords(material);
            mergedWords.forEach(word -> {
                Style style = addText(state, material, word);
                if (style != null) {
                    styles.add(style);
                }
            });
        }

        if (styles.isEmpty()) {
            return null;
        }

        text
                .setId(UuidUtils.next())
                .setAlignment(Optional.ofNullable(globalStyle.getTextAlign()).orElse(1))
                .setContent(JsonUtils.toJsonString(content))
                .setFontSize(globalStyle.getFontSize() * 1.0)
                .setTextColor(globalStyle.getFillColor())
                .setLineSpacing(Optional.ofNullable(globalStyle.getTextLineWidth()).orElse(0) / 20.0)
                .setLetterSpacing(Optional.ofNullable(globalStyle.getTextWordWidth()).orElse(0) / 20.0)
//                .setGlobalAlpha(Optional.ofNullable(globalStyle.getTextAlign()).orElse(100) / 100.0)
//                .setLineMaxWidth(0.8 / zoom)
//                .setFixedWidth(canvasConfig.getWidth() / 1.5 / zoom) //占满整行
                .setCheckFlag(15);

        if (videoSegment.getOpacity() != null) {
            text.setGlobalAlpha(videoSegment.getOpacity() / 100.0);
        }
        // 描边
        if (globalStyle.getStrokeWidth() != null && globalStyle.getStrokeWidth() > 0 && StringUtils.hasLength(globalStyle.getStrokeColor())) {
            text.setBoldWidth(JianyingUtils.pxToJianyingNonlinear(globalStyle.getStrokeWidth()))
                    .setBorderColor(globalStyle.getStrokeColor());
        }

        // 背景
        if (StringUtils.hasLength(globalStyle.getBackgroundColor())) {
            text
                    .setBackgroundColor(globalStyle.getBackgroundColor())
                    .setBackgroundAlpha(Optional.ofNullable(globalStyle.getBackgroundOpacity()).orElse(100) / 100.0)
                    .setBackgroundStyle(1)
                    .setBackgroundHeight(Optional.ofNullable(globalStyle.getBackgroundHeight()).orElse(14) / 100.0)
                    .setBackgroundWidth(Optional.ofNullable(globalStyle.getBackgroundWidth()).orElse(14) / 100.0)
                    .setBackgroundHorizontalOffset(Optional.ofNullable(globalStyle.getBackgroundOffsetY()).orElse(50) * 0.02 - 1)
                    .setBackgroundVerticalOffset(Optional.ofNullable(globalStyle.getBackgroundOffsetY()).orElse(50) * 0.02 - 1)
                    .setBackgroundRoundRadius(Optional.ofNullable(globalStyle.getBackgroundRadius()).orElse(6) / 100.0)
                    .setCheckFlag(31)
            ;
        }

        // 阴影
        if (StringUtils.hasLength(globalStyle.getShadowColor())) {
            text.setShadowAlpha(Optional.ofNullable(globalStyle.getShadowAngle()).orElse(80) / 100.0);
            text.setShadowColor(globalStyle.getShadowColor());
            text.setShadowAngle(Optional.ofNullable(globalStyle.getShadowAngle()).orElse(33) / 100.0);
            text.setShadowDistance(Optional.ofNullable(globalStyle.getShadowWidth()).orElse(8) / 100.0);
        }

        Materials materials = state.getJianyingProject().getMaterials();
        materials.getTexts().add(text);

        //添加轨道
        String trackName = isSubtitle ? TRACK_NAME_SUBTITLE : TRACK_NAME_TEXT;
        VideoTimeRange segmentTime = videoSegment.getTime();
        Track textTrack = JianyingTrackBuilder.getOrCreateTrack(state, Track.TYPE_TEXT, trackName, segmentTime.getStart(), segmentTime.getEndTime());

        Segment segment = getSegment(videoSegment, text);
        textTrack.getSegments().add(segment);

        return segment;
    }

    private List<TextWord> mergeWords(TextMaterial widget) {
        //全局样式
        TextStyle globalStyle = widget.getStyle();

        String text = widget.getText();

        // 重新排序
        List<TextWord> words = widget.getWords();
        words.sort(Comparator.comparingInt(TextWord::getIndex));

        Set<Integer> indexSet = new HashSet<>();
        words.forEach(textWidgetWord -> {
            indexSet.add(textWidgetWord.getIndex());
            indexSet.add(textWidgetWord.getIndex() + textWidgetWord.getLength());
        });
        //添加结束位置
        indexSet.add(text.length());

        //重新排序
        List<Integer> indexList = Lists.newArrayList(indexSet);
        indexList.sort(Integer::compare);

        int indexStart = 0;
        List<TextWord> wordList = Lists.newArrayList();
        TextWord word;
        for (Integer start : indexList) {
            if (start > indexStart) {
                word = globalStyle.toTextWidgetWord(indexStart, start - indexStart);
                wordList.add(word);
                indexStart = start;
            }
        }

        //追加样式
        wordList.forEach(textWidgetWord -> {
            int s = textWidgetWord.getIndex();
            int e = s + textWidgetWord.getLength();
            words.stream().filter(w -> !(w.getIndex() + w.getLength() < e || w.getIndex() > s)).forEach(textWidgetWord::mergeStyle);
        });

        return wordList;
    }

    private Style addText(JianyingProjectBuildState state, TextMaterial material, TextWord word) {
        WordStyleResult result = getStyle(state, word);
        Style style = result.style;

        if (word.getBold() != null && word.getBold()) {
            style.setBold(true);
        }
        if (word.getItalic() != null && word.getItalic()) {
            style.setItalic(true);
        }
        if (word.getUnderline() != null && word.getUnderline()) {
            style.setUnderline(true);
        }

        if (!result.hasFlower) {
            // 描边
            if (word.getStrokeWidth() != null && word.getStrokeWidth() > 0 && StringUtils.hasLength(word.getStrokeColor())) {
                style.setStrokes(Lists.newArrayList(
                        new FontStyle()
                                .setWidth(JianyingUtils.pxToJianyingNonlinear(word.getStrokeWidth()))
                                .setContent(
                                        new Content()
                                                .setSolid(
                                                        new Solid()
                                                                .setColor(JianyingUtils.hexToNormalizedRGB(word.getStrokeColor()))
                                                )
                                )
                ));
            }

            // 阴影
            if (StringUtils.hasLength(word.getShadowColor())) {
                style.setShadows(Lists.newArrayList(
                        new FontStyle()
                                .setAlpha(Optional.ofNullable(word.getShadowOpacity()).orElse(80) / 100.0)
                                .setAngle(Optional.ofNullable(word.getShadowAngle()).orElse(-45))
                                .setDistance(Optional.ofNullable(word.getShadowWidth()).orElse(8))
                                .setFeather(Optional.ofNullable(word.getShadowVague()).orElse(33) / 600.0)
                                .setContent(
                                        new Content()
                                                .setRenderType(Content.RENDER_TYPE_SOLID)
                                                .setSolid(
                                                        new Solid()
                                                                .setColor(JianyingUtils.hexToNormalizedRGB(word.getShadowColor()))
                                                )
                                )
                ));
            }
        }

        return style;
    }

    private static WordStyleResult getStyle(JianyingProjectBuildState state, TextWord word) {
        String fontName = word.getFontName();
        if (!StringUtils.hasLength(fontName)) {
            fontName = JianyingResourceUtils.DEFAULT_FONT_NAME;
        }
        File fontFile = JianyingResourceUtils.getFontFile(fontName);
        String fontPath = JianyingResourceUtils.copyToLocalResources(state, fontFile, JianyingResourceUtils.RS_NAME_FONTS);
        FontStyle fontStyle = new FontStyle()
                .setContent(
                        new Content()
                                .setSolid(
                                        new Solid()
                                                .setColor(JianyingUtils.hexToNormalizedRGB(word.getFillColor()))
                                )
                );

        long flowerId = Optional.ofNullable(word.getFlowerId()).orElse(0L);
        Style style;
        if (flowerId > 0) {
            // 花字
            JyResource resource = JianyingResourceUtils.getJyResource(JianyingResourceUtils.RS_NAME_FLOWER, flowerId);
            style = getFlowerStyle(state, word, resource);
            if (style != null) {
                return new WordStyleResult(true, style);
            }
        }

        // 没有花字时
        style = new Style()
                .setFill(fontStyle)
                .setFont(
                        new Font()
                                .setPath(fontPath)
                                .setId(DuoServerConsts.EMPTY_STR)
                )
                .setRange(Lists.newArrayList(word.getIndex(), word.getIndex() + word.getLength()))
                .setSize(word.getFontSize() * 1.0)
                .setStrokes(null)
                .setEffectStyle(null)
                .setUseLetterColor(true);
        return new WordStyleResult(false, style);
    }

    private static Style getFlowerStyle(JianyingProjectBuildState state, TextWord word, JyResource resource) {
        if (resource == null) {
            logger.warn("花字资源[{}]不存在", word.getFlowerId());
            return null;
        }
        if (!JianyingResourceUtils.RS_NAME_FLOWER.equals(resource.getType())) {
            logger.warn("花字资源[{}]类型不匹配，期望：flower，实际：{}", word.getFlowerId(), resource.getType());
            return null;
        }
        Style style = JsonUtils.toObject(resource.getMainConfig(), Style.class);
        if (style == null) {
            logger.warn("花字资源[{}]样式转换失败!", resource.getMainConfig());
            return null;
        }

        // 加载资源包
        Set<String> resources = resource.getResources();
        JianyingResourceUtils.downloadResources(state.getProjectLocalResourceDir(), resources);

        style
                .setRange(Lists.newArrayList(word.getIndex(), word.getIndex() + word.getLength()))
                .setSize(word.getFontSize() * 1.0)
                .setUseLetterColor(true)
        ;

        // 添加特效
        String effectJson = resource.getEffect();
        if (StringUtils.hasLength(effectJson)) {
            Effect effectDto = JsonUtils.toObject(effectJson, Effect.class);
            style.setEffectStyle(
                    new BaseResource()
                            .setId(effectDto.getResourceId())
                            .setPath(effectDto.getPath())
            );

            //添加 materials > effects
            state.getJianyingProject().getMaterials().getEffects().add(effectDto);
        }

        return style;
    }

    static class WordStyleResult {
        boolean hasFlower;

        Style style;

        public WordStyleResult(boolean hasFlower, Style style) {
            this.hasFlower = hasFlower;
            this.style = style;
        }
    }
}
