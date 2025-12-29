
package com.duoec.video.jy.dto.font;

import java.io.Serializable;
import java.util.List;

public class Solid implements Serializable {

    public Double alpha = 1.0;

    public List<Double> color;

    public Double getAlpha() {
        return alpha;
    }

    public Solid setAlpha(Double alpha) {
        this.alpha = alpha;
        return this;
    }

    public List<Double> getColor() {
        return color;
    }

    public Solid setColor(List<Double> color) {
        this.color = color;
        return this;
    }
}
