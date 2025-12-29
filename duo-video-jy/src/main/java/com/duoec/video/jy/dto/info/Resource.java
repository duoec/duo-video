
package com.duoec.video.jy.dto.info;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Resource implements Serializable {

    public String panel;

    public String path;

    @JsonProperty("resource_id")
    public String resourceId;

    @JsonProperty("source_platform")
    public Integer sourcePlatform;

    public String getPanel() {
        return panel;
    }

    public Resource setPanel(String panel) {
        this.panel = panel;
        return this;
    }

    public String getPath() {
        return path;
    }

    public Resource setPath(String path) {
        this.path = path;
        return this;
    }

    public String getResourceId() {
        return resourceId;
    }

    public Resource setResourceId(String resourceId) {
        this.resourceId = resourceId;
        return this;
    }

    public Integer getSourcePlatform() {
        return sourcePlatform;
    }

    public Resource setSourcePlatform(Integer sourcePlatform) {
        this.sourcePlatform = sourcePlatform;
        return this;
    }
}
