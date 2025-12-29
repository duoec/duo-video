package com.duoec.video.jy.dto.info;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Placeholder implements Serializable {
    public String id;

    @JsonProperty("material_resource_id")
    public String materialResourceId;

    public String name;

    public String type;

    public String getId() {
        return id;
    }

    public Placeholder setId(String id) {
        this.id = id;
        return this;
    }

    public String getMaterialResourceId() {
        return materialResourceId;
    }

    public Placeholder setMaterialResourceId(String materialResourceId) {
        this.materialResourceId = materialResourceId;
        return this;
    }

    public String getName() {
        return name;
    }

    public Placeholder setName(String name) {
        this.name = name;
        return this;
    }

    public String getType() {
        return type;
    }

    public Placeholder setType(String type) {
        this.type = type;
        return this;
    }
}
