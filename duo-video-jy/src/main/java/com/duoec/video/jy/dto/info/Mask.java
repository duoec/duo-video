package com.duoec.video.jy.dto.info;

import com.duoec.video.jy.dto.BaseResource;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Mask extends BaseResource {
    private MaskConfig config;

    private String name;

    private String platform;

    @JsonProperty("position_info")
    private String positionInfo;

    @JsonProperty("resource_id")
    private String resourceId;

    @JsonProperty("resource_type")
    private String resourceType;

    @Override
    public Mask setId(String id) {
        super.setId(id);
        return this;
    }

    @Override
    public Mask setPath(String path) {
        super.setPath(path);
        return this;
    }

    public MaskConfig getConfig() {
        return config;
    }

    public Mask setConfig(MaskConfig config) {
        this.config = config;
        return this;
    }

    public String getName() {
        return name;
    }

    public Mask setName(String name) {
        this.name = name;
        return this;
    }

    public String getPlatform() {
        return platform;
    }

    public Mask setPlatform(String platform) {
        this.platform = platform;
        return this;
    }

    public String getPositionInfo() {
        return positionInfo;
    }

    public Mask setPositionInfo(String positionInfo) {
        this.positionInfo = positionInfo;
        return this;
    }

    public String getResourceId() {
        return resourceId;
    }

    public Mask setResourceId(String resourceId) {
        this.resourceId = resourceId;
        return this;
    }

    public String getResourceType() {
        return resourceType;
    }

    public Mask setResourceType(String resourceType) {
        this.resourceType = resourceType;
        return this;
    }

    public Mask setType(String type) {
        this.type = type;
        return this;
    }
}
