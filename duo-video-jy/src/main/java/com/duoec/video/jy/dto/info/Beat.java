package com.duoec.video.jy.dto.info;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Beat extends BaseMaterial
{

    @JsonProperty("ai_beats")
    public AiBeats aiBeats;

    @JsonProperty("enable_ai_beats")
    public Boolean enableAiBeats;

    public Integer gear;

    @JsonProperty("gear_count")
    public Integer gearCount;

    public Integer mode;

    @JsonProperty("user_beats")
    public List<Object> userBeats;

    @JsonProperty("user_delete_ai_beats")
    public UserDeleteAiBeats userDeleteAiBeats;

    public AiBeats getAiBeats() {
        return aiBeats;
    }

    public Beat setAiBeats(AiBeats aiBeats) {
        this.aiBeats = aiBeats;
        return this;
    }

    public Boolean getEnableAiBeats() {
        return enableAiBeats;
    }

    public Beat setEnableAiBeats(Boolean enableAiBeats) {
        this.enableAiBeats = enableAiBeats;
        return this;
    }

    public Integer getGear() {
        return gear;
    }

    public Beat setGear(Integer gear) {
        this.gear = gear;
        return this;
    }

    public Integer getGearCount() {
        return gearCount;
    }

    public Beat setGearCount(Integer gearCount) {
        this.gearCount = gearCount;
        return this;
    }

    public Beat setId(String id) {
        this.id = id;
        return this;
    }

    public Integer getMode() {
        return mode;
    }

    public Beat setMode(Integer mode) {
        this.mode = mode;
        return this;
    }

    public Beat setType(String type) {
        this.type = type;
        return this;
    }

    public List<Object> getUserBeats() {
        return userBeats;
    }

    public Beat setUserBeats(List<Object> userBeats) {
        this.userBeats = userBeats;
        return this;
    }

    public UserDeleteAiBeats getUserDeleteAiBeats() {
        return userDeleteAiBeats;
    }

    public Beat setUserDeleteAiBeats(UserDeleteAiBeats userDeleteAiBeats) {
        this.userDeleteAiBeats = userDeleteAiBeats;
        return this;
    }
}
