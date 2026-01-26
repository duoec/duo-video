package com.duoec.video.dto.request;

import lombok.Data;

import java.io.Serializable;

/**
 * 添加图片请求
 */
@Data
public class AddImageRequest implements Serializable {
    /**
     * 项目ID
     */
    private Long projectId;

    /**
     * 分镜索引
     */
    private Integer scriptIndex;

    /**
     * 图片资源ID
     */
    private Long imageId;

    /**
     * 图片URL
     */
    private String imageUrl;

    /**
     * 开始时间（毫秒）
     */
    private Long startTime;

    /**
     * 持续时间（毫秒）
     */
    private Long duration;

    /**
     * 布局索引
     */
    private Integer layoutIndex;

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

    /**
     * 是否可见
     */
    private Boolean visible;

    /**
     * 是否水平翻转
     */
    private Boolean horizontal;

    /**
     * 是否垂直翻转
     */
    private Boolean vertical;
}
