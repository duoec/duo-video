package com.duoec.video.jy.dto.info;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AudioFade extends BaseMaterial {
    @JsonProperty("fade_in_duration")
    private Integer fadeInDuration;

    @JsonProperty("fade_out_duration")
    private Integer fadeOutDuration;

    @JsonProperty("fade_type")
    private Integer fadeType;

    public Integer getFadeInDuration() {
        return fadeInDuration;
    }

    public AudioFade setFadeInDuration(Integer fadeInDuration) {
        this.fadeInDuration = fadeInDuration;
        return this;
    }

    public Integer getFadeOutDuration() {
        return fadeOutDuration;
    }

    public AudioFade setFadeOutDuration(Integer fadeOutDuration) {
        this.fadeOutDuration = fadeOutDuration;
        return this;
    }

    public Integer getFadeType() {
        return fadeType;
    }

    public AudioFade setFadeType(Integer fadeType) {
        this.fadeType = fadeType;
        return this;
    }

    public AudioFade setId(String id) {
        this.id = id;
        return this;
    }

    public AudioFade setType(String type) {
        this.type = type;
        return this;
    }
}
