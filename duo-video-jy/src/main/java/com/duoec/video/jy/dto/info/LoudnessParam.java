package com.duoec.video.jy.dto.info;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class LoudnessParam implements Serializable {
    @JsonProperty("avg_loudness")
    private Double avgLoudness;

    @JsonProperty("peak_loudness")
    private Double peakLoudness;

    public Double getAvgLoudness() {
        return avgLoudness;
    }

    public LoudnessParam setAvgLoudness(Double avgLoudness) {
        this.avgLoudness = avgLoudness;
        return this;
    }

    public Double getPeakLoudness() {
        return peakLoudness;
    }

    public LoudnessParam setPeakLoudness(Double peakLoudness) {
        this.peakLoudness = peakLoudness;
        return this;
    }
}
