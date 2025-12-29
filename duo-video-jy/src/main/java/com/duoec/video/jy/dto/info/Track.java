
package com.duoec.video.jy.dto.info;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

public class Track implements Serializable {
    public static final String TYPE_TEXT = "text";
    public static final String TYPE_VIDEO = "video";
    public static final String TYPE_AUDIO = "audio";

    /**
     * 0=显示 2=隐藏
     */
    public Integer attribute;

    public Integer flag;

    public String id;

    @JsonProperty("is_default_name")
    public Boolean isDefaultName;

    public String name;

    public List<Segment> segments;

    public String type;

    /**
     * 轨道顺序，值越大，展示在越前面
     * 非剪映属性
     */
    private Integer duoLayoutIndex;

    public Integer getAttribute() {
        return attribute;
    }

    public Track setAttribute(Integer attribute) {
        this.attribute = attribute;
        return this;
    }

    public Integer getFlag() {
        return flag;
    }

    public Track setFlag(Integer flag) {
        this.flag = flag;
        return this;
    }

    public String getId() {
        return id;
    }

    public Track setId(String id) {
        this.id = id;
        return this;
    }

    public Boolean getDefaultName() {
        return isDefaultName;
    }

    public Track setDefaultName(Boolean defaultName) {
        isDefaultName = defaultName;
        return this;
    }

    public String getName() {
        return name;
    }

    public Track setName(String name) {
        this.name = name;
        return this;
    }

    public List<Segment> getSegments() {
        return segments;
    }

    public Track setSegments(List<Segment> segments) {
        this.segments = segments;
        return this;
    }

    public String getType() {
        return type;
    }

    public Track setType(String type) {
        this.type = type;
        return this;
    }

    public Integer getDuoLayoutIndex() {
        return duoLayoutIndex;
    }

    public Track setDuoLayoutIndex(Integer duoLayoutIndex) {
        this.duoLayoutIndex = duoLayoutIndex;
        return this;
    }
}
