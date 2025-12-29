package com.duoec.video.jy.dto.info;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class VideoTrackingConfig implements Serializable {
    @JsonProperty("center_x")
    private Double centerX;

    @JsonProperty("center_y")
    private Double centerY;

    private Double height;

    private Double width;

    private Double rotation;

    public Double getCenterX() {
        return centerX;
    }

    public VideoTrackingConfig setCenterX(Double centerX) {
        this.centerX = centerX;
        return this;
    }

    public Double getCenterY() {
        return centerY;
    }

    public VideoTrackingConfig setCenterY(Double centerY) {
        this.centerY = centerY;
        return this;
    }

    public Double getHeight() {
        return height;
    }

    public VideoTrackingConfig setHeight(Double height) {
        this.height = height;
        return this;
    }

    public Double getWidth() {
        return width;
    }

    public VideoTrackingConfig setWidth(Double width) {
        this.width = width;
        return this;
    }

    public Double getRotation() {
        return rotation;
    }

    public VideoTrackingConfig setRotation(Double rotation) {
        this.rotation = rotation;
        return this;
    }
}
