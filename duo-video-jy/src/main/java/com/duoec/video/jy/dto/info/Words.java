
package com.duoec.video.jy.dto.info;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

public class Words implements Serializable {

    @JsonProperty("end_time")
    public List<Integer> endTime;

    @JsonProperty("start_time")
    public List<Integer> startTime;

    /**
     * 文本
     */
    public List<String> text;

    public List<Integer> getEndTime() {
        return endTime;
    }

    public Words setEndTime(List<Integer> endTime) {
        this.endTime = endTime;
        return this;
    }

    public List<Integer> getStartTime() {
        return startTime;
    }

    public Words setStartTime(List<Integer> startTime) {
        this.startTime = startTime;
        return this;
    }

    public List<String> getText() {
        return text;
    }

    public Words setText(List<String> text) {
        this.text = text;
        return this;
    }
}
