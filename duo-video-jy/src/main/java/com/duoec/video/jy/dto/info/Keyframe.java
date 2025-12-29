package com.duoec.video.jy.dto.info;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

public class Keyframe implements Serializable {
    private String id;

    @JsonProperty("material_id")
    private String materialId;

    @JsonProperty("keyframe_list")
    private List<KeyframeItem> keyframeList;

    @JsonProperty("property_type")
    private String propertyType;

    public String getId() {
        return id;
    }

    public Keyframe setId(String id) {
        this.id = id;
        return this;
    }

    public String getMaterialId() {
        return materialId;
    }

    public Keyframe setMaterialId(String materialId) {
        this.materialId = materialId;
        return this;
    }

    public List<KeyframeItem> getKeyframeList() {
        return keyframeList;
    }

    public Keyframe setKeyframeList(List<KeyframeItem> keyframeList) {
        this.keyframeList = keyframeList;
        return this;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public Keyframe setPropertyType(String propertyType) {
        this.propertyType = propertyType;
        return this;
    }
}
