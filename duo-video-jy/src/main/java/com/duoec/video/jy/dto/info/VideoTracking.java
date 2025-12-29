package com.duoec.video.jy.dto.info;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

public class VideoTracking implements Serializable {
    private VideoTrackingConfig config;

    @JsonProperty("enable_scale")
    private Boolean enableScale = true;

    @JsonProperty("enable_video_tracking")
    private Boolean enableVideoTracking = false;

    private String id;

    @JsonProperty("map_path")
    private String mapPath;

    @JsonProperty("result_path")
    private String resultPath;

    @JsonProperty("tracker_type")
    private Integer trackerType;

    private List<Object> trackers;

    @JsonProperty("tracking_time_range")
    private Integer trackingTimeRange = 0;

    private String type;

    private String version = "";

    public VideoTrackingConfig getConfig() {
        return config;
    }

    public VideoTracking setConfig(VideoTrackingConfig config) {
        this.config = config;
        return this;
    }

    public Boolean getEnableScale() {
        return enableScale;
    }

    public VideoTracking setEnableScale(Boolean enableScale) {
        this.enableScale = enableScale;
        return this;
    }

    public Boolean getEnableVideoTracking() {
        return enableVideoTracking;
    }

    public VideoTracking setEnableVideoTracking(Boolean enableVideoTracking) {
        this.enableVideoTracking = enableVideoTracking;
        return this;
    }

    public String getId() {
        return id;
    }

    public VideoTracking setId(String id) {
        this.id = id;
        return this;
    }

    public String getMapPath() {
        return mapPath;
    }

    public VideoTracking setMapPath(String mapPath) {
        this.mapPath = mapPath;
        return this;
    }

    public String getResultPath() {
        return resultPath;
    }

    public VideoTracking setResultPath(String resultPath) {
        this.resultPath = resultPath;
        return this;
    }

    public Integer getTrackerType() {
        return trackerType;
    }

    public VideoTracking setTrackerType(Integer trackerType) {
        this.trackerType = trackerType;
        return this;
    }

    public List<Object> getTrackers() {
        return trackers;
    }

    public VideoTracking setTrackers(List<Object> trackers) {
        this.trackers = trackers;
        return this;
    }

    public Integer getTrackingTimeRange() {
        return trackingTimeRange;
    }

    public VideoTracking setTrackingTimeRange(Integer trackingTimeRange) {
        this.trackingTimeRange = trackingTimeRange;
        return this;
    }

    public String getType() {
        return type;
    }

    public VideoTracking setType(String type) {
        this.type = type;
        return this;
    }

    public String getVersion() {
        return version;
    }

    public VideoTracking setVersion(String version) {
        this.version = version;
        return this;
    }
}
