package com.duoec.video.dto.request;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 关键帧数据
 */
@Data
public class KeyframeParam implements Serializable {

    /**
     * 属性类型
     * - "position": 位置，values: [x, y]（像素）
     * - "scale": 缩放，values: [scaleX, scaleY]（万分比）
     * - "rotation": 旋转，values: [rotation]（角度）
     * - "opacity": 透明度，values: [opacity]（0-100）
     * - "volume": 音量，values: [volume]
     * - "textColor": 文字颜色，values: [r, g, b, a]（0-1）
     */
    private String propertyType;

    /**
     * 关键帧项列表
     */
    private List<KeyframeItemParam> items;

    /**
     * 关键帧项参数
     */
    @Data
    public static class KeyframeItemParam implements Serializable {

        /**
         * 时间偏移量（毫秒）
         */
        private Integer timeOffset;

        /**
         * 曲线类型
         * - "linear": 线性
         * - "bezier": 贝塞尔曲线
         * - "ease_in": 缓入
         * - "ease_out": 缓出
         * - "ease_in_out": 缓入缓出
         */
        private String curveType;

        /**
         * 属性值列表
         * - position: [x, y]（像素）
         * - scale: [scaleX, scaleY]（万分比）
         * - rotation: [rotation]（角度）
         * - opacity: [opacity]（0-100）
         * - volume: [volume]
         * - textColor: [r, g, b, a]（0-1）
         */
        private List<Double> values;

        /**
         * 左侧控制点 X（贝塞尔曲线）
         */
        private Double leftControlX;

        /**
         * 左侧控制点 Y（贝塞尔曲线）
         */
        private Double leftControlY;

        /**
         * 右侧控制点 X（贝塞尔曲线）
         */
        private Double rightControlX;

        /**
         * 右侧控制点 Y（贝塞尔曲线）
         */
        private Double rightControlY;
    }
}
