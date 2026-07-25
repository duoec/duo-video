package com.duoec.video.project;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 视频关键帧项
 * 表示单个时间点的关键帧数据
 */
@Data
@Accessors(chain = true)
public class VideoKeyframeItem implements Serializable {

    /**
     * 关键帧项 ID（可选，为空时自动生成）
     */
    private String id;

    /**
     * 曲线类型
     * 常见值：
     * - "linear": 线性
     * - "bezier": 贝塞尔曲线
     * - "step": 阶梯
     * - "ease_in": 缓入
     * - "ease_out": 缓出
     * - "ease_in_out": 缓入缓出
     */
    @JsonProperty("curveType")
    private String curveType;

    /**
     * 图表 ID（用于贝塞尔曲线控制）
     */
    @JsonProperty("graphId")
    private String graphId;

    /**
     * 左侧控制点（贝塞尔曲线）
     */
    @JsonProperty("leftControl")
    private KeyframeControlPoint leftControl;

    /**
     * 右侧控制点（贝塞尔曲线）
     */
    @JsonProperty("rightControl")
    private KeyframeControlPoint rightControl;

    /**
     * 时间偏移量（毫秒）
     * 相对于片段开始时间的偏移
     */
    @JsonProperty("timeOffset")
    private Integer timeOffset;

    /**
     * 属性值列表
     * 不同属性类型的值含义：
     * - position: [x, y] 位置坐标
     * - scale: [scaleX, scaleY] 缩放比例
     * - rotation: [rotation] 旋转角度
     * - opacity: [opacity] 透明度 (0-100)
     * - volume: [volume] 音量
     */
    @JsonProperty("values")
    private List<Double> values;

    public VideoKeyframeItem() {
    }

    /**
     * 快速创建位置关键帧
     * @param timeOffset 时间偏移（毫秒）
     * @param x X 轴位置
     * @param y Y 轴位置
     */
    public static VideoKeyframeItem createPositionKeyframe(Integer timeOffset, Double x, Double y) {
        return new VideoKeyframeItem()
                .setTimeOffset(timeOffset)
                .setValues(List.of(x, y));
    }

    /**
     * 快速创建缩放关键帧
     * @param timeOffset 时间偏移（毫秒）
     * @param scaleX X 轴缩放（万分比）
     * @param scaleY Y 轴缩放（万分比）
     */
    public static VideoKeyframeItem createScaleKeyframe(Integer timeOffset, Double scaleX, Double scaleY) {
        return new VideoKeyframeItem()
                .setTimeOffset(timeOffset)
                .setValues(List.of(scaleX, scaleY));
    }

    /**
     * 快速创建旋转关键帧
     * @param timeOffset 时间偏移（毫秒）
     * @param rotation 旋转角度
     */
    public static VideoKeyframeItem createRotationKeyframe(Integer timeOffset, Double rotation) {
        return new VideoKeyframeItem()
                .setTimeOffset(timeOffset)
                .setValues(List.of(rotation));
    }

    /**
     * 快速创建透明度关键帧
     * @param timeOffset 时间偏移（毫秒）
     * @param opacity 透明度（0-100）
     */
    public static VideoKeyframeItem createOpacityKeyframe(Integer timeOffset, Double opacity) {
        return new VideoKeyframeItem()
                .setTimeOffset(timeOffset)
                .setValues(List.of(opacity));
    }

    /**
     * 快速创建音量关键帧
     * @param timeOffset 时间偏移（毫秒）
     * @param volume 音量
     */
    public static VideoKeyframeItem createVolumeKeyframe(Integer timeOffset, Double volume) {
        return new VideoKeyframeItem()
                .setTimeOffset(timeOffset)
                .setValues(List.of(volume));
    }

    /**
     * 快速创建文字颜色关键帧
     * @param timeOffset 时间偏移（毫秒）
     * @param r 红色 (0-1)
     * @param g 绿色 (0-1)
     * @param b 蓝色 (0-1)
     * @param a 透明度 (0-1)
     */
    public static VideoKeyframeItem createTextColorKeyframe(Integer timeOffset, Double r, Double g, Double b, Double a) {
        return new VideoKeyframeItem()
                .setTimeOffset(timeOffset)
                .setValues(List.of(r, g, b, a));
    }

    /**
     * 快速创建文字颜色关键帧（十六进制颜色）
     * @param timeOffset 时间偏移（毫秒）
     * @param hexColor 十六进制颜色，如 "#FF0000" 或 "#FF0000FF"（含透明度）
     */
    public static VideoKeyframeItem createTextColorKeyframe(Integer timeOffset, String hexColor) {
        String hex = hexColor.startsWith("#") ? hexColor.substring(1) : hexColor;
        double r = Integer.parseInt(hex.substring(0, 2), 16) / 255.0;
        double g = Integer.parseInt(hex.substring(2, 4), 16) / 255.0;
        double b = Integer.parseInt(hex.substring(4, 6), 16) / 255.0;
        double a = hex.length() >= 8 ? Integer.parseInt(hex.substring(6, 8), 16) / 255.0 : 1.0;
        return createTextColorKeyframe(timeOffset, r, g, b, a);
    }
}
