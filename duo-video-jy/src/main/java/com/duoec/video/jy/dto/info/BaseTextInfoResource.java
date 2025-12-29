package com.duoec.video.jy.dto.info;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class BaseTextInfoResource implements Serializable {
    @JsonProperty("attach_info")
    private AttachInfo attachInfo;

    public AttachInfo getAttachInfo() {
        return attachInfo;
    }

    public BaseTextInfoResource setAttachInfo(AttachInfo attachInfo) {
        this.attachInfo = attachInfo;
        return this;
    }
}
