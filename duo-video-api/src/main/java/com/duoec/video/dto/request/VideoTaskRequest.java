package com.duoec.video.dto.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class VideoTaskRequest implements Serializable {
    /**
     * 剪映服务器名称
     */
    private String serverName;
}
