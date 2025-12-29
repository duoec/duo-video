package com.duoec.video.jy.dto.font;

import java.io.Serializable;

public class FontStyle implements Serializable {
    private Double alpha = 1.0;

    private Double feather;

    private Integer angle;

    private Integer distance;

    private Content content;
    
    private Double width;

    public Double getAlpha() {
        return alpha;
    }

    public FontStyle setAlpha(Double alpha) {
        this.alpha = alpha;
        return this;
    }

    public Content getContent() {
        return content;
    }

    public FontStyle setContent(Content content) {
        this.content = content;
        return this;
    }

    public Integer getAngle() {
        return angle;
    }

    public FontStyle setAngle(Integer angle) {
        this.angle = angle;
        return this;
    }

    public Double getFeather() {
        return feather;
    }

    public FontStyle setFeather(Double feather) {
        this.feather = feather;
        return this;
    }

    public Integer getDistance() {
        return distance;
    }

    public FontStyle setDistance(Integer distance) {
        this.distance = distance;
        return this;
    }

    public Double getWidth() {
        return width;
    }

    public FontStyle setWidth(Double width) {
        this.width = width;
        return this;
    }
}
