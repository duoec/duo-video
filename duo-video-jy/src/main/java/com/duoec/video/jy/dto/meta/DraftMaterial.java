
package com.duoec.video.jy.dto.meta;

import java.io.Serializable;
import java.util.List;

public class DraftMaterial implements Serializable {

    public Integer type;

    public List<Value> value;

    public Integer getType() {
        return type;
    }

    public DraftMaterial setType(Integer type) {
        this.type = type;
        return this;
    }

    public List<Value> getValue() {
        return value;
    }

    public DraftMaterial setValue(List<Value> value) {
        this.value = value;
        return this;
    }
}
