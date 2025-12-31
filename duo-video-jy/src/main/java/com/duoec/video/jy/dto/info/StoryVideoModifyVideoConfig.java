
package com.duoec.video.jy.dto.info;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class StoryVideoModifyVideoConfig implements Serializable {

    @JsonProperty("is_overwrite_last_video")
    public Boolean isOverwriteLastVideo;

    @JsonProperty("tracker_task_id")
    public String trackerTaskId;

    @JsonProperty("task_id")
    public String taskId;

    public Boolean getIsOverwriteLastVideo() {
        return isOverwriteLastVideo;
    }

    public StoryVideoModifyVideoConfig setIsOverwriteLastVideo(Boolean isOverwriteLastVideo) {
        this.isOverwriteLastVideo = isOverwriteLastVideo;
        return this;
    }

    public String getTrackerTaskId() {
        return trackerTaskId;
    }

    public StoryVideoModifyVideoConfig setTrackerTaskId(String trackerTaskId) {
        this.trackerTaskId = trackerTaskId;
        return this;
    }

    public String getTaskId() {
        return taskId;
    }

    public StoryVideoModifyVideoConfig setTaskId(String taskId) {
        this.taskId = taskId;
        return this;
    }
}
