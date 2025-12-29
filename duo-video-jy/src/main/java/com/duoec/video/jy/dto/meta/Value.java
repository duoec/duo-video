
package com.duoec.video.jy.dto.meta;

import com.duoec.video.jy.dto.TimeRange;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Value implements Serializable {
    public static final String METE_TYPE_VIDEO = "video";
    public static final String METE_TYPE_MUSIC = "music";
    public static final String METE_TYPE_PHOTO = "photo";

    @JsonProperty("create_time")
    public Long createTime;
    
    public Long duration;

    @JsonProperty("extra_info")
    public String extraInfo;

    @JsonProperty("file_Path")
    public String filePath;

    public Integer height;

    public String id;

    @JsonProperty("import_time")
    public Long importTime;

    @JsonProperty("import_time_ms")
    public Long importTimeMs;

    @JsonProperty("item_source")
    public Integer itemSource;

    public String md5;

    public String metetype;

    @JsonProperty("roughcut_time_range")
    public TimeRange roughcutTimeRange;

    @JsonProperty("sub_time_range")
    public TimeRange subTimeRange;

    public Integer type;

    public Integer width;

    public Long getCreateTime() {
        return createTime;
    }

    public Value setCreateTime(Long createTime) {
        this.createTime = createTime;
        return this;
    }

    public Long getDuration() {
        return duration;
    }

    public Value setDuration(Long duration) {
        this.duration = duration;
        return this;
    }

    public String getExtraInfo() {
        return extraInfo;
    }

    public Value setExtraInfo(String extraInfo) {
        this.extraInfo = extraInfo;
        return this;
    }

    public String getFilePath() {
        return filePath;
    }

    public Value setFilePath(String filePath) {
        this.filePath = filePath;
        return this;
    }

    public Integer getHeight() {
        return height;
    }

    public Value setHeight(Integer height) {
        this.height = height;
        return this;
    }

    public String getId() {
        return id;
    }

    public Value setId(String id) {
        this.id = id;
        return this;
    }

    public Long getImportTime() {
        return importTime;
    }

    public Value setImportTime(Long importTime) {
        this.importTime = importTime;
        return this;
    }

    public Long getImportTimeMs() {
        return importTimeMs;
    }

    public Value setImportTimeMs(Long importTimeMs) {
        this.importTimeMs = importTimeMs;
        return this;
    }

    public Integer getItemSource() {
        return itemSource;
    }

    public Value setItemSource(Integer itemSource) {
        this.itemSource = itemSource;
        return this;
    }

    public String getMd5() {
        return md5;
    }

    public Value setMd5(String md5) {
        this.md5 = md5;
        return this;
    }

    public String getMetetype() {
        return metetype;
    }

    public Value setMetetype(String metetype) {
        this.metetype = metetype;
        return this;
    }

    public TimeRange getRoughcutTimeRange() {
        return roughcutTimeRange;
    }

    public Value setRoughcutTimeRange(TimeRange roughcutTimeRange) {
        this.roughcutTimeRange = roughcutTimeRange;
        return this;
    }

    public TimeRange getSubTimeRange() {
        return subTimeRange;
    }

    public Value setSubTimeRange(TimeRange subTimeRange) {
        this.subTimeRange = subTimeRange;
        return this;
    }

    public Integer getType() {
        return type;
    }

    public Value setType(Integer type) {
        this.type = type;
        return this;
    }

    public Integer getWidth() {
        return width;
    }

    public Value setWidth(Integer width) {
        this.width = width;
        return this;
    }
}
