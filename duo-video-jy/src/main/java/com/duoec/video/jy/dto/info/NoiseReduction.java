package com.duoec.video.jy.dto.info;

import java.io.Serializable;

public class NoiseReduction implements Serializable {
    private Integer level;

    public Integer getLevel() {
        return level;
    }

    public NoiseReduction setLevel(Integer level) {
        this.level = level;
        return this;
    }
}
