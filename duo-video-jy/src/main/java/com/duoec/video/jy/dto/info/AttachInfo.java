
package com.duoec.video.jy.dto.info;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class AttachInfo implements Serializable {
    public Clip clip;

    public Long duration;

    @JsonProperty("original_size_height")
    public Double originalSizeHeight;

    @JsonProperty("original_size_width")
    public Double originalSizeWidth;

    @JsonProperty("start_time")
    public Long startTime;

    @JsonIgnore
    public Double originalHeight;

    @JsonIgnore
    public Double originalWidth;

    public Clip getClip() {
        return clip;
    }

    public AttachInfo setClip(Clip clip) {
        this.clip = clip;
        return this;
    }

    public Long getDuration() {
        return duration;
    }

    public AttachInfo setDuration(Long duration) {
        this.duration = duration;
        return this;
    }

    public Double getOriginalSizeHeight() {
        return originalSizeHeight;
    }

    public AttachInfo setOriginalSizeHeight(Double originalSizeHeight) {
        if (originalHeight == null) {
            originalHeight = originalSizeHeight;
        }
        this.originalSizeHeight = originalSizeHeight;
        return this;
    }

    public Double getOriginalSizeWidth() {
        return originalSizeWidth;
    }

    public AttachInfo setOriginalSizeWidth(Double originalSizeWidth) {
        if (originalWidth == null) {
            originalWidth = originalSizeWidth;
        }
        this.originalSizeWidth = originalSizeWidth;
        return this;
    }

    public Long getStartTime() {
        return startTime;
    }

    public AttachInfo setStartTime(Long startTime) {
        this.startTime = startTime;
        return this;
    }

    public Double getOriginalHeight() {
        return originalHeight;
    }

    public Double getOriginalWidth() {
        return originalWidth;
    }
}
