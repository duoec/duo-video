package com.duoec.video.jy.dto.info;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Radius implements Serializable {
    @JsonProperty("bottom_left")
    private Double bottomLeft = 0.0;

    @JsonProperty("bottom_right")
    private Double bottomRight = 0.0;

    @JsonProperty("top_left")
    private Double topLeft = 0.0;

    @JsonProperty("top_right")
    private Double topRight = 0.0;

    public Double getBottomLeft() {
        return bottomLeft;
    }

    public Radius setBottomLeft(Double bottomLeft) {
        this.bottomLeft = bottomLeft;
        return this;
    }

    public Double getBottomRight() {
        return bottomRight;
    }

    public Radius setBottomRight(Double bottomRight) {
        this.bottomRight = bottomRight;
        return this;
    }

    public Double getTopLeft() {
        return topLeft;
    }

    public Radius setTopLeft(Double topLeft) {
        this.topLeft = topLeft;
        return this;
    }

    public Double getTopRight() {
        return topRight;
    }

    public Radius setTopRight(Double topRight) {
        this.topRight = topRight;
        return this;
    }
}
