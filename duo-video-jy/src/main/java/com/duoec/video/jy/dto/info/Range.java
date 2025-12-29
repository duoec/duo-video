package com.duoec.video.jy.dto.info;

import java.io.Serializable;

public class Range implements Serializable {
    /**
     * 长度
     */
    private Integer length;

    /**
     * 起始位置，从0开始
     */
    private Integer location;

    public Integer getLength() {
        return length;
    }

    public Range setLength(Integer length) {
        this.length = length;
        return this;
    }

    public Integer getLocation() {
        return location;
    }

    public Range setLocation(Integer location) {
        this.location = location;
        return this;
    }
}
