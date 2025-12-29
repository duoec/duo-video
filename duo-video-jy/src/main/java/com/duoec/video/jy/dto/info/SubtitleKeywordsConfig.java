package com.duoec.video.jy.dto.info;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class SubtitleKeywordsConfig implements Serializable {
    @JsonProperty("font_size_ratio")
    private Double fontSizeRatio;


    private String styles;

    @JsonProperty("subtitle_template_keywords_original_font_size")
    private Double subtitleTemplateKeywordsOriginalFontSize;

    @JsonProperty("subtitle_template_original_font_size")
    private Double subtitleTemplateOriginalFontSize;

    public Double getFontSizeRatio() {
        return fontSizeRatio;
    }

    public SubtitleKeywordsConfig setFontSizeRatio(Double fontSizeRatio) {
        this.fontSizeRatio = fontSizeRatio;
        return this;
    }

    public String getStyles() {
        return styles;
    }

    public SubtitleKeywordsConfig setStyles(String styles) {
        this.styles = styles;
        return this;
    }

    public Double getSubtitleTemplateKeywordsOriginalFontSize() {
        return subtitleTemplateKeywordsOriginalFontSize;
    }

    public SubtitleKeywordsConfig setSubtitleTemplateKeywordsOriginalFontSize(Double subtitleTemplateKeywordsOriginalFontSize) {
        this.subtitleTemplateKeywordsOriginalFontSize = subtitleTemplateKeywordsOriginalFontSize;
        return this;
    }

    public Double getSubtitleTemplateOriginalFontSize() {
        return subtitleTemplateOriginalFontSize;
    }

    public SubtitleKeywordsConfig setSubtitleTemplateOriginalFontSize(Double subtitleTemplateOriginalFontSize) {
        this.subtitleTemplateOriginalFontSize = subtitleTemplateOriginalFontSize;
        return this;
    }
}
