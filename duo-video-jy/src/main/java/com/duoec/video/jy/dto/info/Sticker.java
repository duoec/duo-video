package com.duoec.video.jy.dto.info;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Sticker extends BaseMaterial {
    public static final String TYPE = "sticker";
    @JsonProperty("aigc_type")
    private String aigcType = "none";

    private String path;

    @JsonProperty("background_alpha")
    private Double backgroundAlpha = 1.0;

    @JsonProperty("background_color")
    private String backgroundColor = "";

    @JsonProperty("border_color")
    private String borderColor = "";

    @JsonProperty("border_line_style")
    private Integer borderLineStyle = 0;

    @JsonProperty("border_width")
    private Double borderWidth = 0.0;

    @JsonProperty("category_id")
    private String categoryId = "";

    @JsonProperty("category_name")
    private String categoryName = "";

    @JsonProperty("check_flag")
    private Integer checkFlag = 1;

    @JsonProperty("combo_info")
    public ComboInfo comboInfo;

    @JsonProperty("cycle_setting")
    public Boolean cycleSetting = false;

    @JsonProperty("formula_id")
    public String formulaId = "";

    @JsonProperty("global_alpha")
    private Double globalAlpha = 1.0;

    @JsonProperty("has_shadow")
    public Boolean hasShadow = false;

    @JsonProperty("icon_url")
    public String iconUrl = "";

    @JsonProperty("multi_language_current")
    public String multiLanguageCurrent = "none";

    public String name = "";

    @JsonProperty("original_size")
    public List<Object> originalSize;

    public String platform;

    @JsonProperty("preview_cover_url")
    private String previewCoverUrl;

    private Radius radius;

    @JsonProperty("request_id")
    private String requestId;

    @JsonProperty("resource_id")
    private String resourceId;

    @JsonProperty("sequence_type")
    private Boolean sequenceType = false;

    @JsonProperty("shadow_alpha")
    private Double shadowAlpha = 1.0;

    @JsonProperty("shadow_angle")
    private Double shadowAngle = 1.0;

    @JsonProperty("shadow_color")
    private String shadowColor = "";

    @JsonProperty("shadow_distance")
    private Double shadowDistance = 1.0;

    @JsonProperty("shadow_point")
    public Point shadowPoint;

    @JsonProperty("shadow_smoothing")
    public Double shadowSmoothing;

    @JsonProperty("shape_param")
    private ShapeParam shapeParam;

    @JsonProperty("source_platform")
    public Integer sourcePlatform = 1;

    @JsonProperty("sticker_id")
    public String stickerId;

    @JsonProperty("sub_type")
    public Integer subType = 0;

    @JsonProperty("team_id")
    public String teamId = "";

    public String unicode = "";

    public String getAigcType() {
        return aigcType;
    }

    public Sticker setAigcType(String aigcType) {
        this.aigcType = aigcType;
        return this;
    }

    public Double getBackgroundAlpha() {
        return backgroundAlpha;
    }

    public Sticker setBackgroundAlpha(Double backgroundAlpha) {
        this.backgroundAlpha = backgroundAlpha;
        return this;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public Sticker setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
        return this;
    }

    public String getBorderColor() {
        return borderColor;
    }

    public Sticker setBorderColor(String borderColor) {
        this.borderColor = borderColor;
        return this;
    }

    public Integer getBorderLineStyle() {
        return borderLineStyle;
    }

    public Sticker setBorderLineStyle(Integer borderLineStyle) {
        this.borderLineStyle = borderLineStyle;
        return this;
    }

    public Double getBorderWidth() {
        return borderWidth;
    }

    public Sticker setBorderWidth(Double borderWidth) {
        this.borderWidth = borderWidth;
        return this;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public Sticker setCategoryId(String categoryId) {
        this.categoryId = categoryId;
        return this;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public Sticker setCategoryName(String categoryName) {
        this.categoryName = categoryName;
        return this;
    }

    public Integer getCheckFlag() {
        return checkFlag;
    }

    public Sticker setCheckFlag(Integer checkFlag) {
        this.checkFlag = checkFlag;
        return this;
    }

    public ComboInfo getComboInfo() {
        return comboInfo;
    }

    public Sticker setComboInfo(ComboInfo comboInfo) {
        this.comboInfo = comboInfo;
        return this;
    }

    public Boolean getCycleSetting() {
        return cycleSetting;
    }

    public Sticker setCycleSetting(Boolean cycleSetting) {
        this.cycleSetting = cycleSetting;
        return this;
    }

    public String getFormulaId() {
        return formulaId;
    }

    public Sticker setFormulaId(String formulaId) {
        this.formulaId = formulaId;
        return this;
    }

    public Double getGlobalAlpha() {
        return globalAlpha;
    }

    public Sticker setGlobalAlpha(Double globalAlpha) {
        this.globalAlpha = globalAlpha;
        return this;
    }

    public Boolean getHasShadow() {
        return hasShadow;
    }

    public Sticker setHasShadow(Boolean hasShadow) {
        this.hasShadow = hasShadow;
        return this;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public Sticker setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
        return this;
    }

    public String getMultiLanguageCurrent() {
        return multiLanguageCurrent;
    }

    public Sticker setMultiLanguageCurrent(String multiLanguageCurrent) {
        this.multiLanguageCurrent = multiLanguageCurrent;
        return this;
    }

    public String getName() {
        return name;
    }

    public Sticker setName(String name) {
        this.name = name;
        return this;
    }

    public List<Object> getOriginalSize() {
        return originalSize;
    }

    public Sticker setOriginalSize(List<Object> originalSize) {
        this.originalSize = originalSize;
        return this;
    }

    public String getPlatform() {
        return platform;
    }

    public Sticker setPlatform(String platform) {
        this.platform = platform;
        return this;
    }

    public String getPreviewCoverUrl() {
        return previewCoverUrl;
    }

    public Sticker setPreviewCoverUrl(String previewCoverUrl) {
        this.previewCoverUrl = previewCoverUrl;
        return this;
    }

    public Radius getRadius() {
        return radius;
    }

    public Sticker setRadius(Radius radius) {
        this.radius = radius;
        return this;
    }

    public String getRequestId() {
        return requestId;
    }

    public Sticker setRequestId(String requestId) {
        this.requestId = requestId;
        return this;
    }

    public String getResourceId() {
        return resourceId;
    }

    public Sticker setResourceId(String resourceId) {
        this.resourceId = resourceId;
        return this;
    }

    public Boolean getSequenceType() {
        return sequenceType;
    }

    public Sticker setSequenceType(Boolean sequenceType) {
        this.sequenceType = sequenceType;
        return this;
    }

    public Double getShadowAlpha() {
        return shadowAlpha;
    }

    public Sticker setShadowAlpha(Double shadowAlpha) {
        this.shadowAlpha = shadowAlpha;
        return this;
    }

    public Double getShadowAngle() {
        return shadowAngle;
    }

    public Sticker setShadowAngle(Double shadowAngle) {
        this.shadowAngle = shadowAngle;
        return this;
    }

    public String getShadowColor() {
        return shadowColor;
    }

    public Sticker setShadowColor(String shadowColor) {
        this.shadowColor = shadowColor;
        return this;
    }

    public Double getShadowDistance() {
        return shadowDistance;
    }

    public Sticker setShadowDistance(Double shadowDistance) {
        this.shadowDistance = shadowDistance;
        return this;
    }

    public Point getShadowPoint() {
        return shadowPoint;
    }

    public Sticker setShadowPoint(Point shadowPoint) {
        this.shadowPoint = shadowPoint;
        return this;
    }

    public Double getShadowSmoothing() {
        return shadowSmoothing;
    }

    public Sticker setShadowSmoothing(Double shadowSmoothing) {
        this.shadowSmoothing = shadowSmoothing;
        return this;
    }

    public ShapeParam getShapeParam() {
        return shapeParam;
    }

    public Sticker setShapeParam(ShapeParam shapeParam) {
        this.shapeParam = shapeParam;
        return this;
    }

    public Integer getSourcePlatform() {
        return sourcePlatform;
    }

    public Sticker setSourcePlatform(Integer sourcePlatform) {
        this.sourcePlatform = sourcePlatform;
        return this;
    }

    public String getStickerId() {
        return stickerId;
    }

    public Sticker setStickerId(String stickerId) {
        this.stickerId = stickerId;
        return this;
    }

    public Integer getSubType() {
        return subType;
    }

    public Sticker setSubType(Integer subType) {
        this.subType = subType;
        return this;
    }

    public String getTeamId() {
        return teamId;
    }

    public Sticker setTeamId(String teamId) {
        this.teamId = teamId;
        return this;
    }

    public Sticker setType(String type) {
        this.type = type;
        return this;
    }

    public String getUnicode() {
        return unicode;
    }

    public Sticker setUnicode(String unicode) {
        this.unicode = unicode;
        return this;
    }

    @Override
    public Sticker setId(String id) {
        super.setId(id);
        return this;
    }

    public String getPath() {
        return path;
    }

    public Sticker setPath(String path) {
        this.path = path;
        return this;
    }
}
