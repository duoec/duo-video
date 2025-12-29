package com.duoec.video.jy.dto.info;

import com.duoec.video.jy.dto.TimeRange;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class TimeMarkItem implements Serializable {
    private String color;
    private String id;
    @JsonProperty("time_range")
    private TimeRange timeRange;
    private String title;

    public String getColor() {
        return color;
    }

    public TimeMarkItem setColor(String color) {
        this.color = color;
        return this;
    }

    public String getId() {
        return id;
    }

    public TimeMarkItem setId(String id) {
        this.id = id;
        return this;
    }

    public TimeRange getTimeRange() {
        return timeRange;
    }

    public TimeMarkItem setTimeRange(TimeRange timeRange) {
        this.timeRange = timeRange;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public TimeMarkItem setTitle(String title) {
        this.title = title;
        return this;
    }
}
