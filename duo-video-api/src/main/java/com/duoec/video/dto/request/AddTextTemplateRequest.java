package com.duoec.video.dto.request;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 添加文本模板请求
 */
@Data
public class AddTextTemplateRequest implements Serializable {
    /**
     * 项目ID
     */
    private Long projectId;

    /**
     * 分镜索引
     */
    private Integer scriptIndex;

    /**
     * 文本模板ID
     */
    private Long templateId;

    /**
     * 文本内容列表
     */
    private List<String> texts;

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
}
