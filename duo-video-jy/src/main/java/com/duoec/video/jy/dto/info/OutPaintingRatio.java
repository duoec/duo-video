package com.duoec.video.jy.dto.info;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class OutPaintingRatio implements Serializable {
    @JsonProperty("bottom_ratio")
    private Double bottomRatio;

    @JsonProperty("left_ratio")
    private Double leftRatio;

    @JsonProperty("right_ratio")
    private Double rightRatio;

    @JsonProperty("top_ratio")
    private Double topRatio;

    public Double getBottomRatio() {
        return bottomRatio;
    }

    public OutPaintingRatio setBottomRatio(Double bottomRatio) {
        this.bottomRatio = bottomRatio;
        return this;
    }

    public Double getLeftRatio() {
        return leftRatio;
    }

    public OutPaintingRatio setLeftRatio(Double leftRatio) {
        this.leftRatio = leftRatio;
        return this;
    }

    public Double getRightRatio() {
        return rightRatio;
    }

    public OutPaintingRatio setRightRatio(Double rightRatio) {
        this.rightRatio = rightRatio;
        return this;
    }

    public Double getTopRatio() {
        return topRatio;
    }

    public OutPaintingRatio setTopRatio(Double topRatio) {
        this.topRatio = topRatio;
        return this;
    }
}
