package com.duoec.video.dto.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class VideoTaskResponse implements Serializable {
    /**
     * 任务ID
     */
    private Long taskId;

    /**
     * 视频ID
     */
    private Long videoId;

    /**
     * 剪映工程zip地址
     */
    private String jyZipUrl;
}
