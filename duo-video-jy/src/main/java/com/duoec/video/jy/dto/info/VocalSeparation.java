
package com.duoec.video.jy.dto.info;

import com.duoec.video.jy.dto.TimeRange;
import com.fasterxml.jackson.annotation.JsonProperty;

public class VocalSeparation extends BaseMaterial {

    public Integer choice;

    @JsonProperty("production_path")
    public String productionPath;

    @JsonProperty("time_range")
    public TimeRange timeRange;


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
}
