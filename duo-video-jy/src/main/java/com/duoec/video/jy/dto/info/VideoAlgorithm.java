
package com.duoec.video.jy.dto.info;

import com.duoec.video.jy.dto.TimeRange;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

public class VideoAlgorithm implements Serializable {

    public List<Algorithm> algorithms;

    public Object deflicker;

    @JsonProperty("complement_frame_config")
    public Object complementFrameConfig;

    @JsonProperty("gameplay_configs")
    private List<GameplayConfig> gameplayConfigs;

    @JsonProperty("motion_blur_config")
    public Object motionBlurConfig;

    @JsonProperty("noise_reduction")
    public NoiseReduction noiseReduction;

    public String path;

    @JsonProperty("quality_enhance")
    public Object qualityEnhance;

    @JsonProperty("time_range")
    public TimeRange timeRange;

    public List<Algorithm> getAlgorithms() {
        return algorithms;
    }

    public VideoAlgorithm setAlgorithms(List<Algorithm> algorithms) {
        this.algorithms = algorithms;
        return this;
    }

    public Object getDeflicker() {
        return deflicker;
    }

    public VideoAlgorithm setDeflicker(Object deflicker) {
        this.deflicker = deflicker;
        return this;
    }

    public Object getMotionBlurConfig() {
        return motionBlurConfig;
    }

    public VideoAlgorithm setMotionBlurConfig(Object motionBlurConfig) {
        this.motionBlurConfig = motionBlurConfig;
        return this;
    }

    public NoiseReduction getNoiseReduction() {
        return noiseReduction;
    }

    public VideoAlgorithm setNoiseReduction(NoiseReduction noiseReduction) {
        this.noiseReduction = noiseReduction;
        return this;
    }

    public String getPath() {
        return path;
    }

    public VideoAlgorithm setPath(String path) {
        this.path = path;
        return this;
    }

    public Object getQualityEnhance() {
        return qualityEnhance;
    }

    public VideoAlgorithm setQualityEnhance(Object qualityEnhance) {
        this.qualityEnhance = qualityEnhance;
        return this;
    }

    public TimeRange getTimeRange() {
        return timeRange;
    }

    public VideoAlgorithm setTimeRange(TimeRange timeRange) {
        this.timeRange = timeRange;
        return this;
    }
}
