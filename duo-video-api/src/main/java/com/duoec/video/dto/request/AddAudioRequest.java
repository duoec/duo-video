package com.duoec.video.dto.request;

import lombok.Data;

import java.io.Serializable;

/**
 * 添加音频请求
 */
@Data
public class AddAudioRequest implements Serializable {
    /**
     * 项目ID
     */
    private Long projectId;

    /**
     * 分镜索引
     */
    private Integer scriptIndex;

    /**
     * 音频资源ID
     */
    private Long audioId;

    /**
     * 音频URL
     */
    private String audioUrl;

    /**
     * 开始时间（毫秒）
     */
    private Long startTime;

    /**
     * 持续时间（毫秒）
     */
    private Long duration;

    /**
     * 素材时间范围开始（毫秒）
     */
    private Long materialTimeStart;

    /**
     * 素材时间范围结束（毫秒）
     */
    private Long materialTimeEnd;

    /**
     * 素材开始时间（毫秒）
     */
    private Long materialStart;

    /**
     * 布局索引
     */
    private Integer layoutIndex;

    /**
     * 播放速度（百分比，100表示正常速度）
     */
    private Integer speed;

    /**
     * 是否可见
     */
    private Boolean visible;

    /**
     * 音量（-100到100，0表示原音量）
     */
    private Integer volume;
}
