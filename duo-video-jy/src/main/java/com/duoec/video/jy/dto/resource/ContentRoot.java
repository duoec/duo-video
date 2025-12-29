package com.duoec.video.jy.dto.resource;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

public class ContentRoot implements Serializable {
    private List<Integer> size;

    private String guid;

    private Double duration;

    private String name;

    @JsonProperty("preview_time")
    private Double previewTime;

    private List<ContentItem> children;

    public Double getDuration() {
        return duration;
    }

    public ContentRoot setDuration(Double duration) {
        this.duration = duration;
        return this;
    }

    public String getName() {
        return name;
    }

    public ContentRoot setName(String name) {
        this.name = name;
        return this;
    }

    public Double getPreviewTime() {
        return previewTime;
    }

    public ContentRoot setPreviewTime(Double previewTime) {
        this.previewTime = previewTime;
        return this;
    }

    public List<Integer> getSize() {
        return size;
    }

    public ContentRoot setSize(List<Integer> size) {
        this.size = size;
        return this;
    }

    public String getGuid() {
        return guid;
    }

    public ContentRoot setGuid(String guid) {
        this.guid = guid;
        return this;
    }

    public List<ContentItem> getChildren() {
        return children;
    }

    public ContentRoot setChildren(List<ContentItem> children) {
        this.children = children;
        return this;
    }
}
