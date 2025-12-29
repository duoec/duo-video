package com.duoec.video.jy.dto.info;

import com.duoec.video.jy.dto.TimeRange;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class AudioEffect extends BaseMaterial {
    @JsonProperty("audio_adjust_params")
    private List<Object> audioAdjustParams;

    private String path;

    @JsonProperty("category_id")
    private String categoryId;

    @JsonProperty("category_name")
    private String categoryName;

    @JsonProperty("constant_material_id")
    private String constantMaterialId;

    @JsonProperty("is_ugc")
    private Boolean ugc;

    private String name;

    @JsonProperty("production_path")
    private String productionPath;

    @JsonProperty("resource_id")
    private String resourceId;

    @JsonProperty("speaker_id")
    private String speakerId;

    @JsonProperty("sub_type")
    private Integer subType;

    @JsonProperty("time_range")
    private TimeRange timeRange;

    @Override
    public AudioEffect setId(String id) {
        super.setId(id);
        return this;
    }

    public String getPath() {
        return path;
    }

    public AudioEffect setPath(String path) {
        this.path = path;
        return this;
    }

    public List<Object> getAudioAdjustParams() {
        return audioAdjustParams;
    }

    public AudioEffect setAudioAdjustParams(List<Object> audioAdjustParams) {
        this.audioAdjustParams = audioAdjustParams;
        return this;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public AudioEffect setCategoryId(String categoryId) {
        this.categoryId = categoryId;
        return this;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public AudioEffect setCategoryName(String categoryName) {
        this.categoryName = categoryName;
        return this;
    }

    public String getConstantMaterialId() {
        return constantMaterialId;
    }

    public AudioEffect setConstantMaterialId(String constantMaterialId) {
        this.constantMaterialId = constantMaterialId;
        return this;
    }

    public Boolean getUgc() {
        return ugc;
    }

    public AudioEffect setUgc(Boolean ugc) {
        this.ugc = ugc;
        return this;
    }

    public String getName() {
        return name;
    }

    public AudioEffect setName(String name) {
        this.name = name;
        return this;
    }

    public String getProductionPath() {
        return productionPath;
    }

    public AudioEffect setProductionPath(String productionPath) {
        this.productionPath = productionPath;
        return this;
    }

    public String getResourceId() {
        return resourceId;
    }

    public AudioEffect setResourceId(String resourceId) {
        this.resourceId = resourceId;
        return this;
    }

    public String getSpeakerId() {
        return speakerId;
    }

    public AudioEffect setSpeakerId(String speakerId) {
        this.speakerId = speakerId;
        return this;
    }

    public Integer getSubType() {
        return subType;
    }

    public AudioEffect setSubType(Integer subType) {
        this.subType = subType;
        return this;
    }

    public TimeRange getTimeRange() {
        return timeRange;
    }

    public AudioEffect setTimeRange(TimeRange timeRange) {
        this.timeRange = timeRange;
        return this;
    }

    public AudioEffect setType(String type) {
        this.type = type;
        return this;
    }
}
