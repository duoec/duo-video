
package com.duoec.video.jy.dto.info;

import java.io.Serializable;

public class CanvasConfig implements Serializable {
    public Integer height;

    public String ratio;

    public Integer width;

    public Integer getHeight() {
        return height;
    }

    public CanvasConfig setHeight(Integer height) {
        this.height = height;
        return this;
    }

    public String getRatio() {
        return ratio;
    }

    public CanvasConfig setRatio(String ratio) {
        this.ratio = ratio;
        return this;
    }

    public Integer getWidth() {
        return width;
    }

    public CanvasConfig setWidth(Integer width) {
        this.width = width;
        return this;
    }
}
