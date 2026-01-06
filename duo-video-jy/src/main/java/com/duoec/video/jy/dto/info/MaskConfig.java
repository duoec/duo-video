package com.duoec.video.jy.dto.info;

import java.io.Serializable;

public class MaskConfig implements Serializable {
    private Double aspectRatio;

    private Double centerX;

    private Double centerY;

    private Double expansion;

    private Double feather;

    private Double height;

    private Boolean invert;

    private Double rotation;

    private Double roundCorner;

    private Double width;

    public Double getAspectRatio() {
        return aspectRatio;
    }

    public MaskConfig setAspectRatio(Double aspectRatio) {
        this.aspectRatio = aspectRatio;
        return this;
    }

    public Double getCenterX() {
        return centerX;
    }

    public MaskConfig setCenterX(Double centerX) {
        this.centerX = centerX;
        return this;
    }

    public Double getCenterY() {
        return centerY;
    }

    public MaskConfig setCenterY(Double centerY) {
        this.centerY = centerY;
        return this;
    }

    public Double getExpansion() {
        return expansion;
    }

    public MaskConfig setExpansion(Double expansion) {
        this.expansion = expansion;
        return this;
    }

    public Double getFeather() {
        return feather;
    }

    public MaskConfig setFeather(Double feather) {
        this.feather = feather;
        return this;
    }

    public Double getHeight() {
        return height;
    }

    public MaskConfig setHeight(Double height) {
        this.height = height;
        return this;
    }

    public Boolean getInvert() {
        return invert;
    }

    public MaskConfig setInvert(Boolean invert) {
        this.invert = invert;
        return this;
    }

    public Double getRotation() {
        return rotation;
    }

    public MaskConfig setRotation(Double rotation) {
        this.rotation = rotation;
        return this;
    }

    public Double getRoundCorner() {
        return roundCorner;
    }

    public MaskConfig setRoundCorner(Double roundCorner) {
        this.roundCorner = roundCorner;
        return this;
    }

    public Double getWidth() {
        return width;
    }

    public MaskConfig setWidth(Double width) {
        this.width = width;
        return this;
    }
}
