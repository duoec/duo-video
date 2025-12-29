package com.duoec.video.jy.dto.info;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class SubtitleTaskInfo implements Serializable {
    private String id;

    private String language = "";

    @JsonProperty("remove_invalid_task_id")
    private String removeInvalidTaskId = "";

    private Integer type = 10;

    public String getId() {
        return id;
    }

    public SubtitleTaskInfo setId(String id) {
        this.id = id;
        return this;
    }

    public String getLanguage() {
        return language;
    }

    public SubtitleTaskInfo setLanguage(String language) {
        this.language = language;
        return this;
    }

    public String getRemoveInvalidTaskId() {
        return removeInvalidTaskId;
    }

    public SubtitleTaskInfo setRemoveInvalidTaskId(String removeInvalidTaskId) {
        this.removeInvalidTaskId = removeInvalidTaskId;
        return this;
    }

    public Integer getType() {
        return type;
    }

    public SubtitleTaskInfo setType(Integer type) {
        this.type = type;
        return this;
    }
}
