package com.duoec.video.dto.request;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 添加视频请求
 */
@Data
public class AddVideoRequest implements Serializable {
    /**
     * 项目ID
     */
    private Long projectId;

    /**
     * 分镜索引
     */
    private Integer scriptIndex;

    /**
     * 视频资源ID
     */
    private Long videoId;

    /**
     * 视频URL
     */
    private String videoUrl;

    /**
     * 开始时间（毫秒）
     */
    private Long startTime;

    /**
     * 持续时间（毫秒）
     */
    private Long duration;

    /**
     * 素材开始时间（毫秒）
     */
    private Long materialStart;

    /**
     * 素材时间范围开始（毫秒）
     */
    private Long materialTimeStart;

    /**
     * 素材时间范围结束（毫秒）
     */
    private Long materialTimeEnd;

    /**
     * 布局索引
     */
    private Integer layoutIndex;

    /**
     * 播放速度（百分比，100表示正常速度）
     */
    private Integer speed;

    /**
     * 缩放比例X（万分比，10000表示100%）
     */
    private Integer zoomX;

    /**
     * 缩放比例Y（万分比，10000表示100%）
     */
    private Integer zoomY;

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
     * 音量（-100到100，0表示原音量）
     */
    private Integer volume;

    /**
     * 转场特效ID
     */
    private Long transitionId;

    /**
     * 转场持续时间（毫秒）
     */
    private Long transitionDuration;

    /**
     * 绿幕背景参数（如果指定，表示添加绿幕背景）
     */
    private GreenBackgroundParam greenBackground;

    /**
     * 蒙版参数（如果指定，表示添加蒙版）
     */
    private MaskParam mask;

    /**
     * 关键帧列表（用于动画效果）
     */
    private List<KeyframeParam> keyframes;
}
