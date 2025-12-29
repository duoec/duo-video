
package com.duoec.video.jy.dto.info;

import java.io.Serializable;

public class Point implements Serializable {

    public Double x;

    public Double y;

    public Double getX() {
        return x;
    }

    public Point setX(Double x) {
        this.x = x;
        return this;
    }

    public Double getY() {
        return y;
    }

    public Point setY(Double y) {
        this.y = y;
        return this;
    }
}
