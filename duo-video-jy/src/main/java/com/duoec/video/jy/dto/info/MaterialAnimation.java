
package com.duoec.video.jy.dto.info;

import java.io.Serializable;
import java.util.List;

public class MaterialAnimation implements Serializable {

    public List<Animation> animations;

    public String id;

    public String type;

    public List<Animation> getAnimations() {
        return animations;
    }

    public MaterialAnimation setAnimations(List<Animation> animations) {
        this.animations = animations;
        return this;
    }

    public String getId() {
        return id;
    }

    public MaterialAnimation setId(String id) {
        this.id = id;
        return this;
    }

    public String getType() {
        return type;
    }

    public MaterialAnimation setType(String type) {
        this.type = type;
        return this;
    }
}
