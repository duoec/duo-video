package com.duoec.video.jy.dto.info;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Placeholder extends BaseMaterial {
    @JsonProperty("material_resource_id")
    public String materialResourceId;

    public String name;

    @JsonProperty("res_path")
    public String resPath;

    @JsonProperty("error_path")
    public String errorPath;

    @JsonProperty("meta_type")
    public String metaType;

    @JsonProperty("res_text")
    public String resText;

    @JsonProperty("error_text")
    public String errorText;

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

    public Placeholder setType(String type) {
        this.type = type;
        return this;
    }

    public String getResPath() {
        return resPath;
    }

    public Placeholder setResPath(String resPath) {
        this.resPath = resPath;
        return this;
    }

    public String getErrorPath() {
        return errorPath;
    }

    public Placeholder setErrorPath(String errorPath) {
        this.errorPath = errorPath;
        return this;
    }

    public String getMetaType() {
        return metaType;
    }

    public Placeholder setMetaType(String metaType) {
        this.metaType = metaType;
        return this;
    }

    public String getResText() {
        return resText;
    }

    public Placeholder setResText(String resText) {
        this.resText = resText;
        return this;
    }

    public String getErrorText() {
        return errorText;
    }

    public Placeholder setErrorText(String errorText) {
        this.errorText = errorText;
        return this;
    }
}
