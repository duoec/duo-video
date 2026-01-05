
package com.duoec.video.jy.dto.info;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Transition extends BaseMaterial {
    private String path;

    @JsonProperty("is_ai_transition")
    public Boolean isAiTransition;

    @JsonProperty("category_id")
    public String categoryId;

    @JsonProperty("category_name")
    public String categoryName;

    public Long duration;

    @JsonProperty("effect_id")
    public String effectId;

    @JsonProperty("is_overlap")
    public Boolean isOverlap;

    public String name;

    public String platform = "all";

    @JsonProperty("video_path")
    public String videoPath;

    @JsonProperty("third_resource_id")
    public String thirdResourceId;

    @JsonProperty("request_id")
    public String requestId;

    @JsonProperty("resource_id")
    public String resourceId;

    @JsonProperty("task_id")
    public String taskId;

    @JsonProperty("source_platform")
    public Integer sourcePlatform;

    public String getCategoryId() {
        return categoryId;
    }

    public Transition setCategoryId(String categoryId) {
        this.categoryId = categoryId;
        return this;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public Transition setCategoryName(String categoryName) {
        this.categoryName = categoryName;
        return this;
    }

    public Long getDuration() {
        return duration;
    }

    public Transition setDuration(Long duration) {
        this.duration = duration;
        return this;
    }

    public String getEffectId() {
        return effectId;
    }

    public Transition setEffectId(String effectId) {
        this.effectId = effectId;
        return this;
    }

    @Override
    public Transition setId(String id) {
        super.setId(id);
        return this;
    }

    public Boolean getOverlap() {
        return isOverlap;
    }

    public Transition setOverlap(Boolean overlap) {
        isOverlap = overlap;
        return this;
    }

    public String getName() {
        return name;
    }

    public Transition setName(String name) {
        this.name = name;
        return this;
    }

    public String getPath() {
        return path;
    }

    public Transition setPath(String path) {
        this.path = path;
        return this;
    }

    public String getPlatform() {
        return platform;
    }

    public Transition setPlatform(String platform) {
        this.platform = platform;
        return this;
    }

    public String getRequestId() {
        return requestId;
    }

    public Transition setRequestId(String requestId) {
        this.requestId = requestId;
        return this;
    }

    public String getResourceId() {
        return resourceId;
    }

    public Transition setResourceId(String resourceId) {
        this.resourceId = resourceId;
        return this;
    }

    public Transition setType(String type) {
        this.type = type;
        return this;
    }

    public Boolean getAiTransition() {
        return isAiTransition;
    }

    public Transition setAiTransition(Boolean aiTransition) {
        isAiTransition = aiTransition;
        return this;
    }

    public String getVideoPath() {
        return videoPath;
    }

    public Transition setVideoPath(String videoPath) {
        this.videoPath = videoPath;
        return this;
    }

    public String getThirdResourceId() {
        return thirdResourceId;
    }

    public Transition setThirdResourceId(String thirdResourceId) {
        this.thirdResourceId = thirdResourceId;
        return this;
    }

    public String getTaskId() {
        return taskId;
    }

    public Transition setTaskId(String taskId) {
        this.taskId = taskId;
        return this;
    }

    public Integer getSourcePlatform() {
        return sourcePlatform;
    }

    public Transition setSourcePlatform(Integer sourcePlatform) {
        this.sourcePlatform = sourcePlatform;
        return this;
    }
}
