
package com.duoec.video.jy.dto.info;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Speed implements Serializable {

    @JsonProperty("curve_speed")
    public CurveSpeed curveSpeed;

    public String id;

    public Integer mode;

    public Double speed;

    public String type;

    public CurveSpeed getCurveSpeed() {
        return curveSpeed;
    }

    public Speed setCurveSpeed(CurveSpeed curveSpeed) {
        this.curveSpeed = curveSpeed;
        return this;
    }

    public String getId() {
        return id;
    }

    public Speed setId(String id) {
        this.id = id;
        return this;
    }

    public Integer getMode() {
        return mode;
    }

    public Speed setMode(Integer mode) {
        this.mode = mode;
        return this;
    }

    public Double getSpeed() {
        return speed;
    }

    public Speed setSpeed(Double speed) {
        this.speed = speed;
        return this;
    }

    public String getType() {
        return type;
    }

    public Speed setType(String type) {
        this.type = type;
        return this;
    }
}
