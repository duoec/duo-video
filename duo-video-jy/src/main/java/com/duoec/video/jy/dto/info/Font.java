
package com.duoec.video.jy.dto.info;

import com.duoec.video.jy.dto.BaseResource;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Font extends BaseResource {
    @JsonProperty("category_id")
    public String categoryId;

    @JsonProperty("category_name")
    public String categoryName;

    @JsonProperty("effect_id")
    public String effectId;

    @JsonProperty("file_uri")
    public String fileUri;

    @JsonProperty("request_id")
    public String requestId;

    @JsonProperty("resource_id")
    public String resourceId;

    @JsonProperty("source_platform")
    public Integer sourcePlatform;

    @JsonProperty("team_id")
    public String teamId;

    public String title;

    public String getCategoryId() {
        return categoryId;
    }

    public Font setCategoryId(String categoryId) {
        this.categoryId = categoryId;
        return this;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public Font setCategoryName(String categoryName) {
        this.categoryName = categoryName;
        return this;
    }

    public String getEffectId() {
        return effectId;
    }

    public Font setEffectId(String effectId) {
        this.effectId = effectId;
        return this;
    }

    public String getFileUri() {
        return fileUri;
    }

    public Font setFileUri(String fileUri) {
        this.fileUri = fileUri;
        return this;
    }

    @Override
    public Font setId(String id) {
        super.setId(id);
        return this;
    }

    @Override
    public Font setPath(String path) {
        super.setPath(path);
        return this;
    }

    public String getRequestId() {
        return requestId;
    }

    public Font setRequestId(String requestId) {
        this.requestId = requestId;
        return this;
    }

    public String getResourceId() {
        return resourceId;
    }

    public Font setResourceId(String resourceId) {
        this.resourceId = resourceId;
        return this;
    }

    public Integer getSourcePlatform() {
        return sourcePlatform;
    }

    public Font setSourcePlatform(Integer sourcePlatform) {
        this.sourcePlatform = sourcePlatform;
        return this;
    }

    public String getTeamId() {
        return teamId;
    }

    public Font setTeamId(String teamId) {
        this.teamId = teamId;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Font setTitle(String title) {
        this.title = title;
        return this;
    }
}
