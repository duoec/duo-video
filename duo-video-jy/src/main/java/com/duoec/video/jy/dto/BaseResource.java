package com.duoec.video.jy.dto;

import com.duoec.video.jy.dto.info.BaseMaterial;

public class BaseResource extends BaseMaterial {

    private String path;

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
