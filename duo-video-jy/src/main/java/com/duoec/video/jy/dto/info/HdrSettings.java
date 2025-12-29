package com.duoec.video.jy.dto.info;

import java.io.Serializable;

public class HdrSettings implements Serializable {
    private Double intensity;

    /**
     * 转换模式
     * 4: Rec.2100 HLG To Rec.709
     * 6: Rec.2100 PQ To Rec.709
     */
    private Integer mode;

    private Integer nits;

    public Double getIntensity() {
        return intensity;
    }

    public HdrSettings setIntensity(Double intensity) {
        this.intensity = intensity;
        return this;
    }

    public Integer getMode() {
        return mode;
    }

    public HdrSettings setMode(Integer mode) {
        this.mode = mode;
        return this;
    }

    public Integer getNits() {
        return nits;
    }

    public HdrSettings setNits(Integer nits) {
        this.nits = nits;
        return this;
    }
}
