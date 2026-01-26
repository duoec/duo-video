package com.duoec.video.dto.request;

import lombok.Data;

import java.io.Serializable;

/**
 * 创建视频工程请求
 */
@Data
public class CreateProjectRequest implements Serializable {
    /**
     * 视频ID
     */
    private Long projectId;

    /**
     * 视频名称
     */
    private String projectName;

    /**
     * 视频宽度
     */
    private Integer width;

    /**
     * 视频高度
     */
    private Integer height;

    /**
     * 是否为测试模式
     */
    private Boolean test;
}
