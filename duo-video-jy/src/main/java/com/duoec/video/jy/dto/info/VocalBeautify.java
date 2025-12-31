package com.duoec.video.jy.dto.info;

import com.duoec.video.jy.dto.TimeRange;
import com.fasterxml.jackson.annotation.JsonProperty;

public class VocalBeautify extends BaseMaterial {
    @JsonProperty("ambient_sound_level")
    private Integer ambientSoundLevel;

    private Boolean enable;

    @JsonProperty("production_path")
    private String productionPath;

    @JsonProperty("time_range")
    private TimeRange timeRange;

    @JsonProperty("voice_change_mode")
    private Object voiceChangeMode;

    public Integer getAmbientSoundLevel() {
        return ambientSoundLevel;
    }

    public VocalBeautify setAmbientSoundLevel(Integer ambientSoundLevel) {
        this.ambientSoundLevel = ambientSoundLevel;
        return this;
    }

    public Boolean getEnable() {
        return enable;
    }

    public VocalBeautify setEnable(Boolean enable) {
        this.enable = enable;
        return this;
    }

    public VocalBeautify setId(String id) {
        this.id = id;
        return this;
    }

    public String getProductionPath() {
        return productionPath;
    }

    public VocalBeautify setProductionPath(String productionPath) {
        this.productionPath = productionPath;
        return this;
    }

    public TimeRange getTimeRange() {
        return timeRange;
    }

    public VocalBeautify setTimeRange(TimeRange timeRange) {
        this.timeRange = timeRange;
        return this;
    }

    public VocalBeautify setType(String type) {
        this.type = type;
        return this;
    }

    public Object getVoiceChangeMode() {
        return voiceChangeMode;
    }

    public VocalBeautify setVoiceChangeMode(Object voiceChangeMode) {
        this.voiceChangeMode = voiceChangeMode;
        return this;
    }
}
