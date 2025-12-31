
package com.duoec.video.jy.dto.info;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SoundChannelMapping extends BaseMaterial {

    @JsonProperty("audio_channel_mapping")
    public Integer audioChannelMapping;

    @JsonProperty("is_config_open")
    public Boolean isConfigOpen;

    public Integer getAudioChannelMapping() {
        return audioChannelMapping;
    }

    public SoundChannelMapping setAudioChannelMapping(Integer audioChannelMapping) {
        this.audioChannelMapping = audioChannelMapping;
        return this;
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

    public SoundChannelMapping setType(String type) {
        this.type = type;
        return this;
    }
}
