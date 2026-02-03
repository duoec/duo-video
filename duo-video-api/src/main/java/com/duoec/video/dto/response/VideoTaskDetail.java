package com.duoec.video.dto.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class VideoTaskDetail implements Serializable {
    /**
     * 任务ID
     */
    private Long taskId;

    /**
     * 视频ID
     */
    private Long videoId;

    /**
     * 优先级，越小越优先，默认1000
     */
    private Integer priority;

    /**
     * 剪印工程zip地址
     */
    private String jyZipUrl;

    /**
     * 视频URL
     */
    private String videoUrl;

    /**
     * 任务状态
     */
    private Integer status;

    /**
     * 剪映服务器名称
     */
    private String serverName;

    /**
     * 任务领取时间
     */
    private Long taskFetchTime;

    /**
     * 任务状态更新时间
     */
    private Long taskUpdateTime;

    /**
     * 任务完成时间
     */
    private Long taskFinishTime;

    /**
     * 当前任务信息
     */
    private String taskInfo;

    /**
     * 重试次数
     */
    private Integer retryCount = 0;
}
