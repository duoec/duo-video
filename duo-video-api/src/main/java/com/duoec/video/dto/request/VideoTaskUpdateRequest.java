package com.duoec.video.dto.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class VideoTaskUpdateRequest implements Serializable {
    /**
     * 任务ID
     */
    private Long taskId;

    /**
     * 任务执行服务器名称
     */
    private String serverName;

    /**
     * 任务信息
     */
    private String info;

    /**
     * 任务状态：-11=任务失败 -10=主动取消 0=等待处理 10=任务已领取 11=下载完成 12=解压完成 13=打开工程 14=生成视频 100=导出完成
     */
    private Integer status;

    /**
     * 如果创作成功，则会返回上传成功后的视频URL
     */
    private String videoUrl;
}
