
package com.duoec.video.jy.dto.info;

import java.io.Serializable;

public class Clip implements Serializable {
    public Double alpha;

    public Flip flip;

    public Double rotation;

    public Point scale;

    public Point transform;

    public Double getAlpha() {
        return alpha;
    }

    public Clip setAlpha(Double alpha) {
        this.alpha = alpha;
        return this;
    }

    public Flip getFlip() {
        return flip;
    }

    public Clip setFlip(Flip flip) {
        this.flip = flip;
        return this;
    }

    public Double getRotation() {
        return rotation;
    }

    public Clip setRotation(Double rotation) {
        this.rotation = rotation;
        return this;
    }

    public Point getScale() {
        return scale;
    }

    public Clip setScale(Point scale) {
        this.scale = scale;
        return this;
    }

    public Point getTransform() {
        return transform;
    }

    public Clip setTransform(Point transform) {
        this.transform = transform;
        return this;
    }
}
