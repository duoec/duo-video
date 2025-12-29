
package com.duoec.video.jy.dto.info;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LyricsTemplate {

    private String panel;

    @JsonProperty("resource_id")
    private String resourceId;

    @JsonProperty("effect_id")
    private String effectId;

    @JsonProperty("request_id")
    private String requestId;

    @JsonProperty("category_name")
    private String categoryName;

    private String path;

    @JsonProperty("category_id")
    private String categoryId;

    @JsonProperty("resource_name")
    private String resourceName;

    public String getPanel() {
        return panel;
    }

    public LyricsTemplate setPanel(String panel) {
        this.panel = panel;
        return this;
    }

    public String getResourceId() {
        return resourceId;
    }

    public LyricsTemplate setResourceId(String resourceId) {
        this.resourceId = resourceId;
        return this;
    }

    public String getEffectId() {
        return effectId;
    }

    public LyricsTemplate setEffectId(String effectId) {
        this.effectId = effectId;
        return this;
    }

    public String getRequestId() {
        return requestId;
    }

    public LyricsTemplate setRequestId(String requestId) {
        this.requestId = requestId;
        return this;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public LyricsTemplate setCategoryName(String categoryName) {
        this.categoryName = categoryName;
        return this;
    }

    public String getPath() {
        return path;
    }

    public LyricsTemplate setPath(String path) {
        this.path = path;
        return this;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public LyricsTemplate setCategoryId(String categoryId) {
        this.categoryId = categoryId;
        return this;
    }

    public String getResourceName() {
        return resourceName;
    }

    public LyricsTemplate setResourceName(String resourceName) {
        this.resourceName = resourceName;
        return this;
    }
}
