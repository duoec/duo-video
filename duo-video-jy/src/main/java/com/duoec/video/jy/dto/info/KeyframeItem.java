package com.duoec.video.jy.dto.info;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

public class KeyframeItem implements Serializable {
    private String id;

    private String curveType;

    private String graphID;

    @JsonProperty("left_control")
    public Point leftControl;

    @JsonProperty("right_control")
    public Point rightControl;

    @JsonProperty("time_offset")
    public Integer timeOffset;

    private List<Double> values;

    public String getId() {
        return id;
    }

    public KeyframeItem setId(String id) {
        this.id = id;
        return this;
    }

    public String getCurveType() {
        return curveType;
    }

    public KeyframeItem setCurveType(String curveType) {
        this.curveType = curveType;
        return this;
    }

    public String getGraphID() {
        return graphID;
    }

    public KeyframeItem setGraphID(String graphID) {
        this.graphID = graphID;
        return this;
    }

    public Point getLeftControl() {
        return leftControl;
    }

    public KeyframeItem setLeftControl(Point leftControl) {
        this.leftControl = leftControl;
        return this;
    }

    public Point getRightControl() {
        return rightControl;
    }

    public KeyframeItem setRightControl(Point rightControl) {
        this.rightControl = rightControl;
        return this;
    }

    public Integer getTimeOffset() {
        return timeOffset;
    }

    public KeyframeItem setTimeOffset(Integer timeOffset) {
        this.timeOffset = timeOffset;
        return this;
    }

    public List<Double> getValues() {
        return values;
    }

    public KeyframeItem setValues(List<Double> values) {
        this.values = values;
        return this;
    }
}
