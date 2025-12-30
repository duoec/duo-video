package com.duoec.video.project;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;

@Data
public class VideoSegment implements Serializable {
    /**
     * ID
     */
    private Long id;

    /**
     * Segment时间（开始、时长）
     */
    private VideoTimeRange time;

    /**
     * 主素材ID
     */
    private Long materialId;

    /**
     * Segment绑定的素材开始时间，时间戳，单位：毫秒
     * 素材需要时长，通过 segment时间.duration * speed / 100 确实
     */
    private Long materialStart;

    /**
     * 类型。在剪映里会按此type聚合展示轨道
     */
    private String type;

    /**
     * 渲染层级，越小越底层(底层会被上层覆盖）
     */
    private Integer layoutIndex;

    /**
     * 关联：materialId => 类型
     */
    private Map<Long, String> refs;

    /**
     * 变速，单位：百分之一。100表示1倍速
     */
    private Integer speed;

    /**
     * 缩放，单位：万分之一
     */
    private VideoPoint zoom;

    /**
     * 移动，单位：px
     */
    private VideoPoint point;

    /**
     * 旋转角度，单位：度
     */
    private Integer rotate;

    /**
     * 透明度，100表示不透明，0表示全透明，取值范围[0, 100]
     */
    private Integer opacity;

    /**
     * 是否可见，不可见的不占用时长
     */
    private Boolean visible;

    /**
     * 水平镜像 (仅视频、图片有效)
     */
    private Boolean horizontal;

    /**
     * 线性增益音量 <=-100表示静音 100表示原音量 >100表示增音 （volume /100.0即为剪映界面上的值）
     * 默认：100
     */
    private Integer volume;

    /**
     * 垂直镜像
     */
    private Boolean vertical;

    public long getEndTime() {
        return time == null ? 0L : time.getEndTime();
    }
}
