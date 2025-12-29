package com.duoec.video.jy.dto.info;

import com.duoec.video.jy.dto.TimeRange;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class AiTranslate implements Serializable {
    @JsonProperty("actual_time_range")
    private TimeRange actualTimeRange;

    private Boolean enable;

    private String id;

    @JsonProperty("is_from_ai_translate_tool")
    private Boolean isFromAiTranslateTool;

    @JsonProperty("production_path")
    private String productionPath;

    @JsonProperty("source_language")
    private String sourceLanguage;

    @JsonProperty("target_language")
    private String targetLanguage;

    @JsonProperty("time_range")
    private TimeRange timeRange;

    private String type;

    public TimeRange getActualTimeRange() {
        return actualTimeRange;
    }

    public AiTranslate setActualTimeRange(TimeRange actualTimeRange) {
        this.actualTimeRange = actualTimeRange;
        return this;
    }

    public Boolean getEnable() {
        return enable;
    }

    public AiTranslate setEnable(Boolean enable) {
        this.enable = enable;
        return this;
    }

    public String getId() {
        return id;
    }

    public AiTranslate setId(String id) {
        this.id = id;
        return this;
    }

    public Boolean getFromAiTranslateTool() {
        return isFromAiTranslateTool;
    }

    public AiTranslate setFromAiTranslateTool(Boolean fromAiTranslateTool) {
        isFromAiTranslateTool = fromAiTranslateTool;
        return this;
    }

    public String getProductionPath() {
        return productionPath;
    }

    public AiTranslate setProductionPath(String productionPath) {
        this.productionPath = productionPath;
        return this;
    }

    public String getSourceLanguage() {
        return sourceLanguage;
    }

    public AiTranslate setSourceLanguage(String sourceLanguage) {
        this.sourceLanguage = sourceLanguage;
        return this;
    }

    public String getTargetLanguage() {
        return targetLanguage;
    }

    public AiTranslate setTargetLanguage(String targetLanguage) {
        this.targetLanguage = targetLanguage;
        return this;
    }

    public TimeRange getTimeRange() {
        return timeRange;
    }

    public AiTranslate setTimeRange(TimeRange timeRange) {
        this.timeRange = timeRange;
        return this;
    }

    public String getType() {
        return type;
    }

    public AiTranslate setType(String type) {
        this.type = type;
        return this;
    }
}
