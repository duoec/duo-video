package com.duoec.video.dto.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class VideoTaskCreateRequest implements Serializable {
    /**
     * 视频ID
     */
    private Long videoId;

    /**
     * 优先级，越小越优先，默认1000
     */
    private Integer priority;

    /**
     * 剪映工程zip地址
     */
    private String jyZipUrl;
}
