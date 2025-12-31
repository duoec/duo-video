
package com.duoec.video.jy.dto.info;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Speed extends BaseMaterial {

    @JsonProperty("curve_speed")
    public CurveSpeed curveSpeed;

    public Integer mode;

    public Double speed;

    public CurveSpeed getCurveSpeed() {
        return curveSpeed;
    }

    public Speed setCurveSpeed(CurveSpeed curveSpeed) {
        this.curveSpeed = curveSpeed;
        return this;
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

    public Speed setType(String type) {
        this.type = type;
        return this;
    }
}
