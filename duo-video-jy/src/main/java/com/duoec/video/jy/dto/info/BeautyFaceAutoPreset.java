
package com.duoec.video.jy.dto.info;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class BeautyFaceAutoPreset implements Serializable {

    @JsonProperty("preset_id")
    public String presetId;

    public String scene;

    public String name;

    @JsonProperty("rate_map")
    public String rateMap;

    public String getPresetId() {
        return presetId;
    }

    public BeautyFaceAutoPreset setPresetId(String presetId) {
        this.presetId = presetId;
        return this;
    }

    public String getScene() {
        return scene;
    }

    public BeautyFaceAutoPreset setScene(String scene) {
        this.scene = scene;
        return this;
    }

    public String getName() {
        return name;
    }

    public BeautyFaceAutoPreset setName(String name) {
        this.name = name;
        return this;
    }

    public String getRateMap() {
        return rateMap;
    }

    public BeautyFaceAutoPreset setRateMap(String rateMap) {
        this.rateMap = rateMap;
        return this;
    }
}
