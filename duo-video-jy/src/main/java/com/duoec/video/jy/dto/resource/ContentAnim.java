package com.duoec.video.jy.dto.resource;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class ContentAnim implements Serializable {
    @JsonProperty("anim_resource_id")
    private String animResourceId;

    @JsonProperty("anim_resource_path")
    private String animResourcePath;

    @JsonProperty("anim_script_type")
    private String animScriptType;

    @JsonProperty("anim_start_time")
    private Double animStartTime;

    @JsonProperty("anim_type")
    private String animType;

    private Double duration;

    @JsonProperty("loop_duration")
    private Double loopDuration;

    public String getAnimResourceId() {
        return animResourceId;
    }

    public ContentAnim setAnimResourceId(String animResourceId) {
        this.animResourceId = animResourceId;
        return this;
    }

    public String getAnimResourcePath() {
        return animResourcePath;
    }

    public ContentAnim setAnimResourcePath(String animResourcePath) {
        this.animResourcePath = animResourcePath;
        return this;
    }

    public String getAnimScriptType() {
        return animScriptType;
    }

    public ContentAnim setAnimScriptType(String animScriptType) {
        this.animScriptType = animScriptType;
        return this;
    }

    public Double getAnimStartTime() {
        return animStartTime;
    }

    public ContentAnim setAnimStartTime(Double animStartTime) {
        this.animStartTime = animStartTime;
        return this;
    }

    public String getAnimType() {
        return animType;
    }

    public ContentAnim setAnimType(String animType) {
        this.animType = animType;
        return this;
    }

    public Double getDuration() {
        return duration;
    }

    public ContentAnim setDuration(Double duration) {
        this.duration = duration;
        return this;
    }

    public Double getLoopDuration() {
        return loopDuration;
    }

    public ContentAnim setLoopDuration(Double loopDuration) {
        this.loopDuration = loopDuration;
        return this;
    }
}
