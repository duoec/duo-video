
package com.duoec.video.jy.dto.info;

import java.io.Serializable;

public class UniformScale implements Serializable {

    public Boolean on;

    public Double value;

    public Boolean getOn() {
        return on;
    }

    public UniformScale setOn(Boolean on) {
        this.on = on;
        return this;
    }

    public Double getValue() {
        return value;
    }

    public UniformScale setValue(Double value) {
        this.value = value;
        return this;
    }
}
