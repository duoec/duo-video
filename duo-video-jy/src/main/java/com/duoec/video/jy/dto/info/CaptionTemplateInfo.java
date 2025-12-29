
package com.duoec.video.jy.dto.info;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class CaptionTemplateInfo implements Serializable {
    @JsonProperty("category_id")
    private String categoryId;

    @JsonProperty("category_name")
    private String categoryName;

    @JsonProperty("effect_id")
    private String effectId;

    @JsonProperty("resource_id")
    private String resourceId;

    @JsonProperty("resource_name")
    private String resourceName;

    @JsonProperty("request_id")
    private String requestId;

    @JsonProperty("is_new")
    private Boolean isNew;

    @JsonProperty("source_platform")
    private Integer sourcePlatform;

    private String path;

    public String getCategoryId() {
        return categoryId;
    }

    public CaptionTemplateInfo setCategoryId(String categoryId) {
        this.categoryId = categoryId;
        return this;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public CaptionTemplateInfo setCategoryName(String categoryName) {
        this.categoryName = categoryName;
        return this;
    }

    public String getEffectId() {
        return effectId;
    }

    public CaptionTemplateInfo setEffectId(String effectId) {
        this.effectId = effectId;
        return this;
    }

    public String getResourceId() {
        return resourceId;
    }

    public CaptionTemplateInfo setResourceId(String resourceId) {
        this.resourceId = resourceId;
        return this;
    }

    public String getResourceName() {
        return resourceName;
    }

    public CaptionTemplateInfo setResourceName(String resourceName) {
        this.resourceName = resourceName;
        return this;
    }

    public Boolean getNew() {
        return isNew;
    }

    public CaptionTemplateInfo setNew(Boolean aNew) {
        isNew = aNew;
        return this;
    }

    public String getPath() {
        return path;
    }

    public CaptionTemplateInfo setPath(String path) {
        this.path = path;
        return this;
    }

    public String getRequestId() {
        return requestId;
    }

    public CaptionTemplateInfo setRequestId(String requestId) {
        this.requestId = requestId;
        return this;
    }

    public Integer getSourcePlatform() {
        return sourcePlatform;
    }

    public CaptionTemplateInfo setSourcePlatform(Integer sourcePlatform) {
        this.sourcePlatform = sourcePlatform;
        return this;
    }
}
