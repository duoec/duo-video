package com.duoec.video.jy.dto.info;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NonTextInfoResource extends BaseTextInfoResource {
    private String name;

    @JsonProperty("shape_param")
    private ShapeParam shapeParam;

    private String type;

    @Override
    public NonTextInfoResource setAttachInfo(AttachInfo attachInfo) {
        super.setAttachInfo(attachInfo);
        return this;
    }

    public String getName() {
        return name;
    }

    public NonTextInfoResource setName(String name) {
        this.name = name;
        return this;
    }

    public ShapeParam getShapeParam() {
        return shapeParam;
    }

    public NonTextInfoResource setShapeParam(ShapeParam shapeParam) {
        this.shapeParam = shapeParam;
        return this;
    }

    public String getType() {
        return type;
    }

    public NonTextInfoResource setType(String type) {
        this.type = type;
        return this;
    }
}
