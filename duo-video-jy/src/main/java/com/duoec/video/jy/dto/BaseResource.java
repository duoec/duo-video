package com.duoec.video.jy.dto;

import java.io.Serializable;

public class BaseResource implements Serializable {

    private String id;

    private String path;

    public String getId() {
        return id;
    }

    public BaseResource setId(String id) {
        this.id = id;
        return this;
    }

    public String getPath() {
        return path;
    }

    public BaseResource setPath(String path) {
        this.path = path;
        return this;
    }
}
