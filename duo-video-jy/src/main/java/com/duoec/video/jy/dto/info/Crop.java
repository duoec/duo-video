
package com.duoec.video.jy.dto.info;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Crop implements Serializable {
    @JsonProperty("lower_left_x")
    public Double lowerLeftX = 0.0;

    @JsonProperty("lower_left_y")
    public Double lowerLeftY = 1.0;

    @JsonProperty("lower_right_x")
    public Double lowerRightX = 1.0;

    @JsonProperty("lower_right_y")
    public Double lowerRightY = 1.0;

    @JsonProperty("upper_left_x")
    public Double upperLeftX = 0.0;

    @JsonProperty("upper_left_y")
    public Double upperLeftY;

    @JsonProperty("upper_right_x")
    public Double upperRightX = 1.0;

    @JsonProperty("upper_right_y")
    public Double upperRightY = 0.0;

    public Double getLowerLeftX() {
        return lowerLeftX;
    }

    public Crop setLowerLeftX(Double lowerLeftX) {
        this.lowerLeftX = lowerLeftX;
        return this;
    }

    public Double getLowerLeftY() {
        return lowerLeftY;
    }

    public Crop setLowerLeftY(Double lowerLeftY) {
        this.lowerLeftY = lowerLeftY;
        return this;
    }

    public Double getLowerRightX() {
        return lowerRightX;
    }

    public Crop setLowerRightX(Double lowerRightX) {
        this.lowerRightX = lowerRightX;
        return this;
    }

    public Double getLowerRightY() {
        return lowerRightY;
    }

    public Crop setLowerRightY(Double lowerRightY) {
        this.lowerRightY = lowerRightY;
        return this;
    }

    public Double getUpperLeftX() {
        return upperLeftX;
    }

    public Crop setUpperLeftX(Double upperLeftX) {
        this.upperLeftX = upperLeftX;
        return this;
    }

    public Double getUpperLeftY() {
        return upperLeftY;
    }

    public Crop setUpperLeftY(Double upperLeftY) {
        this.upperLeftY = upperLeftY;
        return this;
    }

    public Double getUpperRightX() {
        return upperRightX;
    }

    public Crop setUpperRightX(Double upperRightX) {
        this.upperRightX = upperRightX;
        return this;
    }

    public Double getUpperRightY() {
        return upperRightY;
    }

    public Crop setUpperRightY(Double upperRightY) {
        this.upperRightY = upperRightY;
        return this;
    }
}
