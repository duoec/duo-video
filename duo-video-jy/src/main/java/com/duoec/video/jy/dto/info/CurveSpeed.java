package com.duoec.video.jy.dto.info;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

public class CurveSpeed implements Serializable {
    private String id;

    private String name;

    @JsonProperty("speed_points")
    private List<Point> speedPoints;

    public String getId() {
        return id;
    }

    public CurveSpeed setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public CurveSpeed setName(String name) {
        this.name = name;
        return this;
    }

    public List<Point> getSpeedPoints() {
        return speedPoints;
    }

    public CurveSpeed setSpeedPoints(List<Point> speedPoints) {
        this.speedPoints = speedPoints;
        return this;
    }
}
