package com.duoec.video.dto.request;

import lombok.Data;

import java.io.Serializable;

/**
 * 添加音效请求
 */
@Data
public class AddSoundRequest implements Serializable {
    /**
     * 项目ID
     */
    private Long projectId;

    /**
     * 分镜索引
     */
    private Integer scriptIndex;

    /**
     * 音效ID
     */
    private Long soundId;

    /**
     * 开始时间（毫秒）
     */
    private Long startTime;

    /**
     * 持续时间（毫秒）
     */
    private Long duration;
}
