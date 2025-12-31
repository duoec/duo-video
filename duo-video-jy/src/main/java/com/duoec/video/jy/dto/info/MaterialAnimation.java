
package com.duoec.video.jy.dto.info;

import java.util.List;

public class MaterialAnimation extends BaseMaterial {

    public List<Animation> animations;

    public List<Animation> getAnimations() {
        return animations;
    }

    public MaterialAnimation setAnimations(List<Animation> animations) {
        this.animations = animations;
        return this;
    }

    public MaterialAnimation setId(String id) {
        this.id = id;
        return this;
    }

    public MaterialAnimation setType(String type) {
        this.type = type;
        return this;
    }
}
