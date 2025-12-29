
package com.duoec.video.jy.dto.info;

import com.duoec.video.jy.dto.TimeRange;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Stable implements Serializable {

    @JsonProperty("matrix_path")
    public String matrixPath;

    @JsonProperty("stable_level")
    public Integer stableLevel;

    @JsonProperty("time_range")
    public TimeRange timeRange;

    public String getMatrixPath() {
        return matrixPath;
    }

    public Stable setMatrixPath(String matrixPath) {
        this.matrixPath = matrixPath;
        return this;
    }

    public Integer getStableLevel() {
        return stableLevel;
    }

    public Stable setStableLevel(Integer stableLevel) {
        this.stableLevel = stableLevel;
        return this;
    }

    public TimeRange getTimeRange() {
        return timeRange;
    }

    public Stable setTimeRange(TimeRange timeRange) {
        this.timeRange = timeRange;
        return this;
    }
}
