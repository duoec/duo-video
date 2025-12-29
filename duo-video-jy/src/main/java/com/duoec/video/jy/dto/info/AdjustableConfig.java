package com.duoec.video.jy.dto.info;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class AdjustableConfig implements Serializable {
    @JsonProperty("beat_gear")
    private Integer beatGear;

    @JsonProperty("beat_source")
    private String beatSource;

    @JsonProperty("lightwave_repeat_duration")
    private Integer lightWaveRepeatDuration;

    @JsonProperty("outpainting_ratio")
    private OutPaintingRatio outPaintingRatio;

    private String prompt;

    private Double speed;

    @JsonProperty("stopmotion_first_render_time")
    private Integer stopmotionFirstRenderTime;

    @JsonProperty("stopmotion_gap_duration")
    private Integer stopmotionGapDuration;

    private Double strength;

    public Integer getBeatGear() {
        return beatGear;
    }

    public AdjustableConfig setBeatGear(Integer beatGear) {
        this.beatGear = beatGear;
        return this;
    }

    public String getBeatSource() {
        return beatSource;
    }

    public AdjustableConfig setBeatSource(String beatSource) {
        this.beatSource = beatSource;
        return this;
    }

    public Integer getLightWaveRepeatDuration() {
        return lightWaveRepeatDuration;
    }

    public AdjustableConfig setLightWaveRepeatDuration(Integer lightWaveRepeatDuration) {
        this.lightWaveRepeatDuration = lightWaveRepeatDuration;
        return this;
    }

    public OutPaintingRatio getOutPaintingRatio() {
        return outPaintingRatio;
    }

    public AdjustableConfig setOutPaintingRatio(OutPaintingRatio outPaintingRatio) {
        this.outPaintingRatio = outPaintingRatio;
        return this;
    }

    public String getPrompt() {
        return prompt;
    }

    public AdjustableConfig setPrompt(String prompt) {
        this.prompt = prompt;
        return this;
    }

    public Double getSpeed() {
        return speed;
    }

    public AdjustableConfig setSpeed(Double speed) {
        this.speed = speed;
        return this;
    }

    public Integer getStopmotionFirstRenderTime() {
        return stopmotionFirstRenderTime;
    }

    public AdjustableConfig setStopmotionFirstRenderTime(Integer stopmotionFirstRenderTime) {
        this.stopmotionFirstRenderTime = stopmotionFirstRenderTime;
        return this;
    }

    public Integer getStopmotionGapDuration() {
        return stopmotionGapDuration;
    }

    public AdjustableConfig setStopmotionGapDuration(Integer stopmotionGapDuration) {
        this.stopmotionGapDuration = stopmotionGapDuration;
        return this;
    }

    public Double getStrength() {
        return strength;
    }

    public AdjustableConfig setStrength(Double strength) {
        this.strength = strength;
        return this;
    }
}
