package com.duoec.video.jy.dto.info;

import com.duoec.video.jy.dto.TimeRange;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Effect extends BaseMaterial {
    @JsonProperty("adjust_params")
    public List<AdjustParam> adjustParams;

    @JsonProperty("algorithm_artifact_path")
    public String algorithmArtifactPath;

    @JsonProperty("apply_target_type")
    public Integer applyTargetType;

    @JsonProperty("bloom_params")
    public Object bloomParams;

    @JsonProperty("category_id")
    public String categoryId;

    @JsonProperty("category_name")
    public String categoryName;

    @JsonProperty("color_match_info")
    public ColorMatchInfo colorMatchInfo;

    @JsonProperty("effect_id")
    public String effectId;

    @JsonProperty("enable_skin_tone_correction")
    public Boolean enableSkinToneCorrection;

    @JsonProperty("exclusion_group")
    public List<Object> exclusionGroup;

    @JsonProperty("face_adjust_params")
    public List<Object> faceAdjustParams;

    @JsonProperty("formula_id")
    public String formulaId;

    @JsonProperty("intensity_key")

    public String intensityKey;

    public String name;

    @JsonProperty("panel_id")
    public String panelId;

    public String path;

    public String platform;

    @JsonProperty("request_id")
    public String requestId;

    @JsonProperty("resource_id")
    public String resourceId;

    @JsonProperty("source_platform")
    public Integer sourcePlatform;

    @JsonProperty("sub_type")
    public String subType;

    @JsonProperty("time_range")
    public TimeRange timeRange;

    public Double value;

    public String version;

    public List<AdjustParam> getAdjustParams() {
        return adjustParams;
    }

    public Effect setAdjustParams(List<AdjustParam> adjustParams) {
        this.adjustParams = adjustParams;
        return this;
    }

    public String getAlgorithmArtifactPath() {
        return algorithmArtifactPath;
    }

    public Effect setAlgorithmArtifactPath(String algorithmArtifactPath) {
        this.algorithmArtifactPath = algorithmArtifactPath;
        return this;
    }

    public Integer getApplyTargetType() {
        return applyTargetType;
    }

    public Effect setApplyTargetType(Integer applyTargetType) {
        this.applyTargetType = applyTargetType;
        return this;
    }

    public Object getBloomParams() {
        return bloomParams;
    }

    public Effect setBloomParams(Object bloomParams) {
        this.bloomParams = bloomParams;
        return this;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public Effect setCategoryId(String categoryId) {
        this.categoryId = categoryId;
        return this;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public Effect setCategoryName(String categoryName) {
        this.categoryName = categoryName;
        return this;
    }

    public ColorMatchInfo getColorMatchInfo() {
        return colorMatchInfo;
    }

    public Effect setColorMatchInfo(ColorMatchInfo colorMatchInfo) {
        this.colorMatchInfo = colorMatchInfo;
        return this;
    }

    public String getEffectId() {
        return effectId;
    }

    public Effect setEffectId(String effectId) {
        this.effectId = effectId;
        return this;
    }

    public Boolean getEnableSkinToneCorrection() {
        return enableSkinToneCorrection;
    }

    public Effect setEnableSkinToneCorrection(Boolean enableSkinToneCorrection) {
        this.enableSkinToneCorrection = enableSkinToneCorrection;
        return this;
    }

    public List<Object> getExclusionGroup() {
        return exclusionGroup;
    }

    public Effect setExclusionGroup(List<Object> exclusionGroup) {
        this.exclusionGroup = exclusionGroup;
        return this;
    }

    public List<Object> getFaceAdjustParams() {
        return faceAdjustParams;
    }

    public Effect setFaceAdjustParams(List<Object> faceAdjustParams) {
        this.faceAdjustParams = faceAdjustParams;
        return this;
    }

    public String getFormulaId() {
        return formulaId;
    }

    public Effect setFormulaId(String formulaId) {
        this.formulaId = formulaId;
        return this;
    }

    public Effect setId(String id) {
        this.id = id;
        return this;
    }

    public String getIntensityKey() {
        return intensityKey;
    }

    public Effect setIntensityKey(String intensityKey) {
        this.intensityKey = intensityKey;
        return this;
    }

    public String getName() {
        return name;
    }

    public Effect setName(String name) {
        this.name = name;
        return this;
    }

    public String getPanelId() {
        return panelId;
    }

    public Effect setPanelId(String panelId) {
        this.panelId = panelId;
        return this;
    }

    public String getPath() {
        return path;
    }

    public Effect setPath(String path) {
        this.path = path;
        return this;
    }

    public String getPlatform() {
        return platform;
    }

    public Effect setPlatform(String platform) {
        this.platform = platform;
        return this;
    }

    public String getRequestId() {
        return requestId;
    }

    public Effect setRequestId(String requestId) {
        this.requestId = requestId;
        return this;
    }

    public String getResourceId() {
        return resourceId;
    }

    public Effect setResourceId(String resourceId) {
        this.resourceId = resourceId;
        return this;
    }

    public Integer getSourcePlatform() {
        return sourcePlatform;
    }

    public Effect setSourcePlatform(Integer sourcePlatform) {
        this.sourcePlatform = sourcePlatform;
        return this;
    }

    public String getSubType() {
        return subType;
    }

    public Effect setSubType(String subType) {
        this.subType = subType;
        return this;
    }

    public TimeRange getTimeRange() {
        return timeRange;
    }

    public Effect setTimeRange(TimeRange timeRange) {
        this.timeRange = timeRange;
        return this;
    }

    public Effect setType(String type) {
        this.type = type;
        return this;
    }

    public Double getValue() {
        return value;
    }

    public Effect setValue(Double value) {
        this.value = value;
        return this;
    }

    public String getVersion() {
        return version;
    }

    public Effect setVersion(String version) {
        this.version = version;
        return this;
    }
}
