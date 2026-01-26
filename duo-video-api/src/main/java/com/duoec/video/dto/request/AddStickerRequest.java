package com.duoec.video.dto.request;

import lombok.Data;

import java.io.Serializable;

/**
 * 添加贴纸请求
 */
@Data
public class AddStickerRequest implements Serializable {
    /**
     * 项目ID
     */
    private Long projectId;

    /**
     * 分镜索引
     */
    private Integer scriptIndex;

    /**
     * 贴纸ID
     */
    private Long stickerId;

    /**
     * 开始时间（毫秒）
     */
    private Long startTime;

    /**
     * 持续时间（毫秒）
     */
    private Long duration;

    /**
     * 缩放比例X（万分比，10000表示100%）
     */
    private Integer zoomX;

    /**
     * 缩放比例Y（万分比，10000表示100%）
     */
    private Integer zoomY;

    /**
     * X轴位置
     */
    private Integer positionX;

    /**
     * Y轴位置
     */
    private Integer positionY;

    /**
     * 旋转角度
     */
    private Integer rotate;
}
