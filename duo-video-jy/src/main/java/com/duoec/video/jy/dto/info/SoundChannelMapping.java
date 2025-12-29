
package com.duoec.video.jy.dto.info;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class SoundChannelMapping implements Serializable {

    @JsonProperty("audio_channel_mapping")
    public Integer audioChannelMapping;

    public String id;

    @JsonProperty("is_config_open")
    public Boolean isConfigOpen;

    public String type;

    public Integer getAudioChannelMapping() {
        return audioChannelMapping;
    }

    public SoundChannelMapping setAudioChannelMapping(Integer audioChannelMapping) {
        this.audioChannelMapping = audioChannelMapping;
        return this;
    }

    public String getId() {
        return id;
    }

    public SoundChannelMapping setId(String id) {
        this.id = id;
        return this;
    }

    public Boolean getConfigOpen() {
        return isConfigOpen;
    }

    public SoundChannelMapping setConfigOpen(Boolean configOpen) {
        isConfigOpen = configOpen;
        return this;
    }

    public String getType() {
        return type;
    }

    public SoundChannelMapping setType(String type) {
        this.type = type;
        return this;
    }
}
