package com.duoec.video.jy.dto.info;

import com.duoec.video.jy.dto.TimeRange;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class VideoEffect extends BaseMaterial {
    @JsonProperty("adjust_params")
    public List<AdjustParam> adjustParams;

    @JsonProperty("apply_target_type")
    public Integer applyTargetType;

    @JsonProperty("apply_time_range")
    public Object applyTimeRange;

    @JsonProperty("category_id")
    public String categoryId;

    @JsonProperty("category_name")
    public String categoryName;

    @JsonProperty("effect_id")
    public String effectId;

    @JsonProperty("formula_id")
    public String formulaId;

    public String path;

    public String name;

    public String platform = "all";

    @JsonProperty("render_index")
    public Integer renderIndex;

    @JsonProperty("resource_id")
    public String resourceId;

    @JsonProperty("source_platform")
    public Integer sourcePlatform = 0;

    @JsonProperty("time_range")
    public TimeRange timeRange;

    @JsonProperty("track_render_index")
    public Integer trackRenderIndex = 0;

    private Double value = 1.0;

    private String version = "";

    @JsonProperty("transparent_params")
    private String transparentParams = "";

    @JsonProperty("request_id")
    private String requestId;

    @JsonProperty("algorithm_artifact_path")
    private String algorithmArtifactPath;

    @JsonProperty("bind_segment_id")
    private String bindSegmentId = "";

    @JsonProperty("effect_mask")
    private List<Object> effectMask;

    @JsonProperty("disable_effect_faces")
    private List<Object> disableEffectFaces;

    @JsonProperty("sub_type")
    private Integer subType = 0;

    @JsonProperty("common_keyframes")
    private List<Object> commonKeyframes;

    @JsonProperty("item_effect_type")
    private Integer itemEffectType = 0;

    @JsonProperty("enable_mask")
    private Boolean enableMask = true;

    @JsonProperty("covering_relation_change")
    private Integer coveringRelationChange = 0;

    public List<AdjustParam> getAdjustParams() {
        return adjustParams;
    }

    public VideoEffect setAdjustParams(List<AdjustParam> adjustParams) {
        this.adjustParams = adjustParams;
        return this;
    }

    public Integer getApplyTargetType() {
        return applyTargetType;
    }

    public VideoEffect setApplyTargetType(Integer applyTargetType) {
        this.applyTargetType = applyTargetType;
        return this;
    }

    public Object getApplyTimeRange() {
        return applyTimeRange;
    }

    public VideoEffect setApplyTimeRange(Object applyTimeRange) {
        this.applyTimeRange = applyTimeRange;
        return this;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public VideoEffect setCategoryId(String categoryId) {
        this.categoryId = categoryId;
        return this;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public VideoEffect setCategoryName(String categoryName) {
        this.categoryName = categoryName;
        return this;
    }

    public String getEffectId() {
        return effectId;
    }

    public VideoEffect setEffectId(String effectId) {
        this.effectId = effectId;
        return this;
    }

    public String getFormulaId() {
        return formulaId;
    }

    public VideoEffect setFormulaId(String formulaId) {
        this.formulaId = formulaId;
        return this;
    }

    public VideoEffect setId(String id) {
        this.id = id;
        return this;
    }

    public String getPath() {
        return path;
    }

    public VideoEffect setPath(String path) {
        this.path = path;
        return this;
    }

    public String getName() {
        return name;
    }

    public VideoEffect setName(String name) {
        this.name = name;
        return this;
    }

    public String getPlatform() {
        return platform;
    }

    public VideoEffect setPlatform(String platform) {
        this.platform = platform;
        return this;
    }

    public Integer getRenderIndex() {
        return renderIndex;
    }

    public VideoEffect setRenderIndex(Integer renderIndex) {
        this.renderIndex = renderIndex;
        return this;
    }

    public String getResourceId() {
        return resourceId;
    }

    public VideoEffect setResourceId(String resourceId) {
        this.resourceId = resourceId;
        return this;
    }

    public Integer getSourcePlatform() {
        return sourcePlatform;
    }

    public VideoEffect setSourcePlatform(Integer sourcePlatform) {
        this.sourcePlatform = sourcePlatform;
        return this;
    }

    public TimeRange getTimeRange() {
        return timeRange;
    }

    public VideoEffect setTimeRange(TimeRange timeRange) {
        this.timeRange = timeRange;
        return this;
    }

    public Integer getTrackRenderIndex() {
        return trackRenderIndex;
    }

    public VideoEffect setTrackRenderIndex(Integer trackRenderIndex) {
        this.trackRenderIndex = trackRenderIndex;
        return this;
    }

    public VideoEffect setType(String type) {
        this.type = type;
        return this;
    }

    public Double getValue() {
        return value;
    }

    public VideoEffect setValue(Double value) {
        this.value = value;
        return this;
    }

    public String getVersion() {
        return version;
    }

    public VideoEffect setVersion(String version) {
        this.version = version;
        return this;
    }

    public String getTransparentParams() {
        return transparentParams;
    }

    public VideoEffect setTransparentParams(String transparentParams) {
        this.transparentParams = transparentParams;
        return this;
    }

    public String getRequestId() {
        return requestId;
    }

    public VideoEffect setRequestId(String requestId) {
        this.requestId = requestId;
        return this;
    }

    public String getAlgorithmArtifactPath() {
        return algorithmArtifactPath;
    }

    public VideoEffect setAlgorithmArtifactPath(String algorithmArtifactPath) {
        this.algorithmArtifactPath = algorithmArtifactPath;
        return this;
    }

    public String getBindSegmentId() {
        return bindSegmentId;
    }

    public VideoEffect setBindSegmentId(String bindSegmentId) {
        this.bindSegmentId = bindSegmentId;
        return this;
    }

    public List<Object> getEffectMask() {
        return effectMask;
    }

    public VideoEffect setEffectMask(List<Object> effectMask) {
        this.effectMask = effectMask;
        return this;
    }

    public List<Object> getDisableEffectFaces() {
        return disableEffectFaces;
    }

    public VideoEffect setDisableEffectFaces(List<Object> disableEffectFaces) {
        this.disableEffectFaces = disableEffectFaces;
        return this;
    }

    public Integer getSubType() {
        return subType;
    }

    public VideoEffect setSubType(Integer subType) {
        this.subType = subType;
        return this;
    }

    public List<Object> getCommonKeyframes() {
        return commonKeyframes;
    }

    public VideoEffect setCommonKeyframes(List<Object> commonKeyframes) {
        this.commonKeyframes = commonKeyframes;
        return this;
    }

    public Integer getItemEffectType() {
        return itemEffectType;
    }

    public VideoEffect setItemEffectType(Integer itemEffectType) {
        this.itemEffectType = itemEffectType;
        return this;
    }

    public Boolean getEnableMask() {
        return enableMask;
    }

    public VideoEffect setEnableMask(Boolean enableMask) {
        this.enableMask = enableMask;
        return this;
    }

    public Integer getCoveringRelationChange() {
        return coveringRelationChange;
    }

    public VideoEffect setCoveringRelationChange(Integer coveringRelationChange) {
        this.coveringRelationChange = coveringRelationChange;
        return this;
    }
}
