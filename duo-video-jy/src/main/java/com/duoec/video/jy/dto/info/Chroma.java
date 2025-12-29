package com.duoec.video.jy.dto.info;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Chroma extends BaseMaterial {
    private String color;

    private String path;

    @JsonProperty("edge_smooth_value")
    private Double edgeSmoothValue;

    @JsonProperty("intensity_value")
    private Double intensityValue;

    @JsonProperty("resource_id")
    private String resourceId;

    @JsonProperty("shadow_value")
    private Double shadowValue;

    @JsonProperty("should_transfer_color")
    private Boolean shouldTransferColor;

    @JsonProperty("spill_value")
    private Double spillValue;

    private String version;

    @Override
    public Chroma setId(String id) {
        super.setId(id);
        return this;
    }

    public String getPath() {
        return path;
    }

    public Chroma setPath(String path) {
        this.path = path;
        return this;
    }

    public String getColor() {
        return color;
    }

    public Chroma setColor(String color) {
        this.color = color;
        return this;
    }

    public Double getEdgeSmoothValue() {
        return edgeSmoothValue;
    }

    public Chroma setEdgeSmoothValue(Double edgeSmoothValue) {
        this.edgeSmoothValue = edgeSmoothValue;
        return this;
    }

    public Double getIntensityValue() {
        return intensityValue;
    }

    public Chroma setIntensityValue(Double intensityValue) {
        this.intensityValue = intensityValue;
        return this;
    }

    public String getResourceId() {
        return resourceId;
    }

    public Chroma setResourceId(String resourceId) {
        this.resourceId = resourceId;
        return this;
    }

    public Double getShadowValue() {
        return shadowValue;
    }

    public Chroma setShadowValue(Double shadowValue) {
        this.shadowValue = shadowValue;
        return this;
    }

    public Boolean getShouldTransferColor() {
        return shouldTransferColor;
    }

    public Chroma setShouldTransferColor(Boolean shouldTransferColor) {
        this.shouldTransferColor = shouldTransferColor;
        return this;
    }

    public Double getSpillValue() {
        return spillValue;
    }

    public Chroma setSpillValue(Double spillValue) {
        this.spillValue = spillValue;
        return this;
    }

    public Chroma setType(String type) {
        this.type = type;
        return this;
    }

    public String getVersion() {
        return version;
    }

    public Chroma setVersion(String version) {
        this.version = version;
        return this;
    }
}
