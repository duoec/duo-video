package com.duoec.video.jy.dto.info;

import com.duoec.video.jy.dto.TimeRange;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Loudness extends BaseMaterial {
    private Boolean enable;

    @JsonProperty("file_id")
    private String fileId;

    @JsonProperty("loudness_param")
    private LoudnessParam loudnessParam;

    @JsonProperty("target_loudness")
    private Double targetLoudness;

    @JsonProperty("time_range")
    private TimeRange timeRange;

    public Boolean getEnable() {
        return enable;
    }

    public Loudness setEnable(Boolean enable) {
        this.enable = enable;
        return this;
    }

    public String getFileId() {
        return fileId;
    }

    public Loudness setFileId(String fileId) {
        this.fileId = fileId;
        return this;
    }

    public Loudness setId(String id) {
        this.id = id;
        return this;
    }

    public LoudnessParam getLoudnessParam() {
        return loudnessParam;
    }

    public Loudness setLoudnessParam(LoudnessParam loudnessParam) {
        this.loudnessParam = loudnessParam;
        return this;
    }

    public Double getTargetLoudness() {
        return targetLoudness;
    }

    public Loudness setTargetLoudness(Double targetLoudness) {
        this.targetLoudness = targetLoudness;
        return this;
    }

    public TimeRange getTimeRange() {
        return timeRange;
    }

    public Loudness setTimeRange(TimeRange timeRange) {
        this.timeRange = timeRange;
        return this;
    }
}
