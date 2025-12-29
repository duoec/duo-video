package com.duoec.video.jy.dto;

import java.io.Serializable;
import java.util.List;

public class BeatTime implements Serializable {
    private List<Integer> time;

    private List<Integer> value;

    public List<Integer> getTime() {
        return time;
    }

    public BeatTime setTime(List<Integer> time) {
        this.time = time;
        return this;
    }

    public List<Integer> getValue() {
        return value;
    }

    public BeatTime setValue(List<Integer> value) {
        this.value = value;
        return this;
    }
}
