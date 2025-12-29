package com.duoec.video.jy.dto.info;

import java.io.Serializable;

public class BaseMaterial implements Serializable {

    public String id;

    public String type;

    public String getId() {
        return id;
    }

    public BaseMaterial setId(String id) {
        this.id = id;
        return this;
    }

    public String getType() {
        return type;
    }

    public BaseMaterial setType(String type) {
        this.type = type;
        return this;
    }
}
