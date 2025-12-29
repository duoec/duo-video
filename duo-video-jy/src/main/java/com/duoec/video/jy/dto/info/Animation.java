package com.duoec.video.jy.dto.info;

import com.duoec.video.jy.dto.BaseResource;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Animation extends BaseResource {
    @JsonProperty("anim_adjust_params")
    private Object animAdjustParams;

    @JsonProperty("category_id")
    private String categoryId;

    @JsonProperty("category_name")
    private String categoryName;

    private Integer duration;

    @JsonProperty("material_type")
    private String materialType;

    private String name;

    private String panel;

    private String platform;

    @JsonProperty("request_id")
    private String requestId;

    @JsonProperty("resource_id")
    private String resourceId;

    private Integer start;

    private String type;

    public Object getAnimAdjustParams() {
        return animAdjustParams;
    }

    public Animation setAnimAdjustParams(Object animAdjustParams) {
        this.animAdjustParams = animAdjustParams;
        return this;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public Animation setCategoryId(String categoryId) {
        this.categoryId = categoryId;
        return this;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public Animation setCategoryName(String categoryName) {
        this.categoryName = categoryName;
        return this;
    }

    public Integer getDuration() {
        return duration;
    }

    public Animation setDuration(Integer duration) {
        this.duration = duration;
        return this;
    }

    public String getMaterialType() {
        return materialType;
    }

    public Animation setMaterialType(String materialType) {
        this.materialType = materialType;
        return this;
    }

    public String getName() {
        return name;
    }

    public Animation setName(String name) {
        this.name = name;
        return this;
    }

    public String getPanel() {
        return panel;
    }

    public Animation setPanel(String panel) {
        this.panel = panel;
        return this;
    }

    public String getPlatform() {
        return platform;
    }

    public Animation setPlatform(String platform) {
        this.platform = platform;
        return this;
    }

    public String getRequestId() {
        return requestId;
    }

    public Animation setRequestId(String requestId) {
        this.requestId = requestId;
        return this;
    }

    public String getResourceId() {
        return resourceId;
    }

    public Animation setResourceId(String resourceId) {
        this.resourceId = resourceId;
        return this;
    }

    public Integer getStart() {
        return start;
    }

    public Animation setStart(Integer start) {
        this.start = start;
        return this;
    }

    public String getType() {
        return type;
    }

    public Animation setType(String type) {
        this.type = type;
        return this;
    }

    @Override
    public Animation setId(String id) {
        super.setId(id);
        return this;
    }

    @Override
    public Animation setPath(String path) {
        super.setPath(path);
        return this;
    }
}
