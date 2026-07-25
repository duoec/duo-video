package com.duoec.video.project;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 关键帧控制点
 * 用于贝塞尔曲线的控制点坐标
 */
@Data
@Accessors(chain = true)
public class KeyframeControlPoint implements Serializable {

    /**
     * X 坐标
     */
    @JsonProperty("x")
    private Double x;

    /**
     * Y 坐标
     */
    @JsonProperty("y")
    private Double y;

    public KeyframeControlPoint() {
    }

    public KeyframeControlPoint(Double x, Double y) {
        this.x = x;
        this.y = y;
    }
}
