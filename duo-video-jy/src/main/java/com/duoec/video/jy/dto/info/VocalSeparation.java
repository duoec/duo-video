
package com.duoec.video.jy.dto.info;

import com.duoec.video.jy.dto.TimeRange;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class VocalSeparation extends BaseMaterial {

    public Integer choice;

    @JsonProperty("production_path")
    public String productionPath;

    @JsonProperty("time_range")
    public TimeRange timeRange;

    @JsonProperty("enter_from")
    public String enterFrom;

    @JsonProperty("final_algorithm")
    public String finalAlgorithm;

    @JsonProperty("removed_sounds")
    public List<Object> removedSounds;

    public Integer getChoice() {
        return choice;
    }

    public VocalSeparation setChoice(Integer choice) {
        this.choice = choice;
        return this;
    }

    public VocalSeparation setId(String id) {
        this.id = id;
        return this;
    }

    public String getProductionPath() {
        return productionPath;
    }

    public VocalSeparation setProductionPath(String productionPath) {
        this.productionPath = productionPath;
        return this;
    }

    public TimeRange getTimeRange() {
        return timeRange;
    }

    public VocalSeparation setTimeRange(TimeRange timeRange) {
        this.timeRange = timeRange;
        return this;
    }

    public VocalSeparation setType(String type) {
        this.type = type;
        return this;
    }

    public String getEnterFrom() {
        return enterFrom;
    }

    public VocalSeparation setEnterFrom(String enterFrom) {
        this.enterFrom = enterFrom;
        return this;
    }

    public String getFinalAlgorithm() {
        return finalAlgorithm;
    }

    public VocalSeparation setFinalAlgorithm(String finalAlgorithm) {
        this.finalAlgorithm = finalAlgorithm;
        return this;
    }

    public List<Object> getRemovedSounds() {
        return removedSounds;
    }

    public VocalSeparation setRemovedSounds(List<Object> removedSounds) {
        this.removedSounds = removedSounds;
        return this;
    }
}
