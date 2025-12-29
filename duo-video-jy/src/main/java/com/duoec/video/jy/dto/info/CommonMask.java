package com.duoec.video.jy.dto.info;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 新版本剪映通用蒙板
 */
public class CommonMask extends Mask {

    @JsonProperty("category_name")
    private String categoryName;

    @JsonProperty("category_id")
    private String categoryId;

    @JsonProperty("constant_material_id")
    private String constantMaterialId;

    @JsonProperty("text_config")
    private TextConfig textConfig;

    private String category;

    @JsonProperty("is_old_version")
    private Boolean isOldVersion;

    @JsonProperty("source_platform")
    private Integer sourcePlatform;

    @JsonProperty("track_segment")
    private String trackSegment;

    private String panel;

    @JsonProperty("loader_work_space")
    private String loaderWorkSpace;

    // 继承自Mask类的属性：
    // - name (继承)
    // - platform (继承)
    // - positionInfo (继承)
    // - resourceId (继承)
    // - resourceType (继承)
    // - type (继承)
    // - config (继承)
    // - id (继承)
    // - path (继承)

    public String getCategoryName() {
        return categoryName;
    }

    public CommonMask setCategoryName(String categoryName) {
        this.categoryName = categoryName;
        return this;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public CommonMask setCategoryId(String categoryId) {
        this.categoryId = categoryId;
        return this;
    }

    public String getConstantMaterialId() {
        return constantMaterialId;
    }

    public CommonMask setConstantMaterialId(String constantMaterialId) {
        this.constantMaterialId = constantMaterialId;
        return this;
    }

    public TextConfig getTextConfig() {
        return textConfig;
    }

    public CommonMask setTextConfig(TextConfig textConfig) {
        this.textConfig = textConfig;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public CommonMask setCategory(String category) {
        this.category = category;
        return this;
    }

    public Boolean getIsOldVersion() {
        return isOldVersion;
    }

    public CommonMask setIsOldVersion(Boolean isOldVersion) {
        this.isOldVersion = isOldVersion;
        return this;
    }

    public Integer getSourcePlatform() {
        return sourcePlatform;
    }

    public CommonMask setSourcePlatform(Integer sourcePlatform) {
        this.sourcePlatform = sourcePlatform;
        return this;
    }

    public String getTrackSegment() {
        return trackSegment;
    }

    public CommonMask setTrackSegment(String trackSegment) {
        this.trackSegment = trackSegment;
        return this;
    }

    public String getPanel() {
        return panel;
    }

    public CommonMask setPanel(String panel) {
        this.panel = panel;
        return this;
    }

    public String getLoaderWorkSpace() {
        return loaderWorkSpace;
    }

    public CommonMask setLoaderWorkSpace(String loaderWorkSpace) {
        this.loaderWorkSpace = loaderWorkSpace;
        return this;
    }

    @Override
    public CommonMask setId(String id) {
        super.setId(id);
        return this;
    }

    @Override
    public CommonMask setPath(String path) {
        super.setPath(path);
        return this;
    }

    /**
     * 内部文本配置类
     */
    public static class TextConfig {
        @JsonProperty("bold_width")
        private Integer boldWidth;

        private Double scale;

        @JsonProperty("char_spacing")
        private Integer charSpacing;

        @JsonProperty("align_type")
        private Integer alignType;

        @JsonProperty("font_size")
        private Integer fontSize;

        @JsonProperty("line_gap")
        private Integer lineGap;

        @JsonProperty("font_resource_id")
        private String fontResourceId;

        @JsonProperty("italic_degree")
        private Integer italicDegree;

        @JsonProperty("has_underline")
        private Boolean hasUnderline;

        @JsonProperty("font_name")
        private String fontName;

        private String content;

        @JsonProperty("font_path")
        private String fontPath;

        public Integer getBoldWidth() {
            return boldWidth;
        }

        public TextConfig setBoldWidth(Integer boldWidth) {
            this.boldWidth = boldWidth;
            return this;
        }

        public Double getScale() {
            return scale;
        }

        public TextConfig setScale(Double scale) {
            this.scale = scale;
            return this;
        }

        public Integer getCharSpacing() {
            return charSpacing;
        }

        public TextConfig setCharSpacing(Integer charSpacing) {
            this.charSpacing = charSpacing;
            return this;
        }

        public Integer getAlignType() {
            return alignType;
        }

        public TextConfig setAlignType(Integer alignType) {
            this.alignType = alignType;
            return this;
        }

        public Integer getFontSize() {
            return fontSize;
        }

        public TextConfig setFontSize(Integer fontSize) {
            this.fontSize = fontSize;
            return this;
        }

        public Integer getLineGap() {
            return lineGap;
        }

        public TextConfig setLineGap(Integer lineGap) {
            this.lineGap = lineGap;
            return this;
        }

        public String getFontResourceId() {
            return fontResourceId;
        }

        public TextConfig setFontResourceId(String fontResourceId) {
            this.fontResourceId = fontResourceId;
            return this;
        }

        public Integer getItalicDegree() {
            return italicDegree;
        }

        public TextConfig setItalicDegree(Integer italicDegree) {
            this.italicDegree = italicDegree;
            return this;
        }

        public Boolean getHasUnderline() {
            return hasUnderline;
        }

        public TextConfig setHasUnderline(Boolean hasUnderline) {
            this.hasUnderline = hasUnderline;
            return this;
        }

        public String getFontName() {
            return fontName;
        }

        public TextConfig setFontName(String fontName) {
            this.fontName = fontName;
            return this;
        }

        public String getContent() {
            return content;
        }

        public TextConfig setContent(String content) {
            this.content = content;
            return this;
        }

        public String getFontPath() {
            return fontPath;
        }

        public TextConfig setFontPath(String fontPath) {
            this.fontPath = fontPath;
            return this;
        }
    }
}