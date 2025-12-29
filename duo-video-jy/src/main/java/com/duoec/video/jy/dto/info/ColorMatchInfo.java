package com.duoec.video.jy.dto.info;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class ColorMatchInfo implements Serializable {
    @JsonProperty("source_feature_path")
    public String sourceFeaturePath;

    @JsonProperty("target_feature_path")
    public String targetFeaturePath;

    @JsonProperty("target_image_path")
    public String targetImagePath;

    public String getSourceFeaturePath() {
        return sourceFeaturePath;
    }

    public ColorMatchInfo setSourceFeaturePath(String sourceFeaturePath) {
        this.sourceFeaturePath = sourceFeaturePath;
        return this;
    }

    public String getTargetFeaturePath() {
        return targetFeaturePath;
    }

    public ColorMatchInfo setTargetFeaturePath(String targetFeaturePath) {
        this.targetFeaturePath = targetFeaturePath;
        return this;
    }

    public String getTargetImagePath() {
        return targetImagePath;
    }

    public ColorMatchInfo setTargetImagePath(String targetImagePath) {
        this.targetImagePath = targetImagePath;
        return this;
    }
}
