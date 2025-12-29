package com.duoec.video.jy.dto.info;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

public class ShapeParam implements Serializable {
    private List<Object> points;

    @JsonProperty("shape_type")
    private Integer shapeType;

    public List<Object> getPoints() {
        return points;
    }

    public ShapeParam setPoints(List<Object> points) {
        this.points = points;
        return this;
    }

    public Integer getShapeType() {
        return shapeType;
    }

    public ShapeParam setShapeType(Integer shapeType) {
        this.shapeType = shapeType;
        return this;
    }
}
