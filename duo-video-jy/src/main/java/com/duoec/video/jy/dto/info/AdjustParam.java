package com.duoec.video.jy.dto.info;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class AdjustParam implements Serializable {
    @JsonProperty("default_value")
    private Double defaultValue;

    private String name;

    private Double value;

    public Double getDefaultValue() {
        return defaultValue;
    }

    public AdjustParam setDefaultValue(Double defaultValue) {
        this.defaultValue = defaultValue;
        return this;
    }

    public String getName() {
        return name;
    }

    public AdjustParam setName(String name) {
        this.name = name;
        return this;
    }

    public Double getValue() {
        return value;
    }

    public AdjustParam setValue(Double value) {
        this.value = value;
        return this;
    }
}
