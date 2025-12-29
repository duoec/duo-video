package com.duoec.video.jy.dto.resource;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class ContentLayoutParams implements Serializable {
    @JsonProperty("bottom_toBottomOf")
    private String bottomToBottomOf;

    @JsonProperty("fix_dimensionRatio")
    private Boolean fixDimensionRatio;

    @JsonProperty("fix_height")
    private Boolean fixHeight;

    @JsonProperty("fix_width")
    private Boolean fixWidth;

    @JsonProperty("hCenter_toHCenterOf")
    private String hCenterToHCenterOf;

    @JsonProperty("left_toLeftOf")
    private String leftToLeftOf;

    @JsonProperty("right_toRightOf")
    private String rightToRightOf;

    @JsonProperty("top_toTopOf")
    private String topToTopOf;

    @JsonProperty("vCenter_toVCenterOf")
    private String vCenterToVCenterOf;

    public String getBottomToBottomOf() {
        return bottomToBottomOf;
    }

    public ContentLayoutParams setBottomToBottomOf(String bottomToBottomOf) {
        this.bottomToBottomOf = bottomToBottomOf;
        return this;
    }

    public Boolean getFixDimensionRatio() {
        return fixDimensionRatio;
    }

    public ContentLayoutParams setFixDimensionRatio(Boolean fixDimensionRatio) {
        this.fixDimensionRatio = fixDimensionRatio;
        return this;
    }

    public Boolean getFixHeight() {
        return fixHeight;
    }

    public ContentLayoutParams setFixHeight(Boolean fixHeight) {
        this.fixHeight = fixHeight;
        return this;
    }

    public Boolean getFixWidth() {
        return fixWidth;
    }

    public ContentLayoutParams setFixWidth(Boolean fixWidth) {
        this.fixWidth = fixWidth;
        return this;
    }

    public String gethCenterToHCenterOf() {
        return hCenterToHCenterOf;
    }

    public ContentLayoutParams sethCenterToHCenterOf(String hCenterToHCenterOf) {
        this.hCenterToHCenterOf = hCenterToHCenterOf;
        return this;
    }

    public String getLeftToLeftOf() {
        return leftToLeftOf;
    }

    public ContentLayoutParams setLeftToLeftOf(String leftToLeftOf) {
        this.leftToLeftOf = leftToLeftOf;
        return this;
    }

    public String getRightToRightOf() {
        return rightToRightOf;
    }

    public ContentLayoutParams setRightToRightOf(String rightToRightOf) {
        this.rightToRightOf = rightToRightOf;
        return this;
    }

    public String getTopToTopOf() {
        return topToTopOf;
    }

    public ContentLayoutParams setTopToTopOf(String topToTopOf) {
        this.topToTopOf = topToTopOf;
        return this;
    }

    public String getvCenterToVCenterOf() {
        return vCenterToVCenterOf;
    }

    public ContentLayoutParams setvCenterToVCenterOf(String vCenterToVCenterOf) {
        this.vCenterToVCenterOf = vCenterToVCenterOf;
        return this;
    }
}
