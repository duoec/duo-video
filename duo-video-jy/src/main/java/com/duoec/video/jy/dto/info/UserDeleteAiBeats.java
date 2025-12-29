package com.duoec.video.jy.dto.info;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

public class UserDeleteAiBeats implements Serializable {
    @JsonProperty("beat_0")
    private List<TimeMarkItem> beat0;
    @JsonProperty("beat_1")
    private List<TimeMarkItem> beat1;
    @JsonProperty("beat_2")
    private List<TimeMarkItem> beat2;
    @JsonProperty("beat_3")
    private List<TimeMarkItem> beat3;
    @JsonProperty("beat_4")
    private List<TimeMarkItem> beat4;
    @JsonProperty("melody_0")
    private List<TimeMarkItem> melody0;

    public List<TimeMarkItem> getBeat0() {
        return beat0;
    }

    public UserDeleteAiBeats setBeat0(List<TimeMarkItem> beat0) {
        this.beat0 = beat0;
        return this;
    }

    public List<TimeMarkItem> getBeat1() {
        return beat1;
    }

    public UserDeleteAiBeats setBeat1(List<TimeMarkItem> beat1) {
        this.beat1 = beat1;
        return this;
    }

    public List<TimeMarkItem> getBeat2() {
        return beat2;
    }

    public UserDeleteAiBeats setBeat2(List<TimeMarkItem> beat2) {
        this.beat2 = beat2;
        return this;
    }

    public List<TimeMarkItem> getBeat3() {
        return beat3;
    }

    public UserDeleteAiBeats setBeat3(List<TimeMarkItem> beat3) {
        this.beat3 = beat3;
        return this;
    }

    public List<TimeMarkItem> getBeat4() {
        return beat4;
    }

    public UserDeleteAiBeats setBeat4(List<TimeMarkItem> beat4) {
        this.beat4 = beat4;
        return this;
    }

    public List<TimeMarkItem> getMelody0() {
        return melody0;
    }

    public UserDeleteAiBeats setMelody0(List<TimeMarkItem> melody0) {
        this.melody0 = melody0;
        return this;
    }
}
