package com.duoec.video.project.material;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 基础的可视多媒体素材，包含：视频、图片
 */
@Data
public class BaseVisibleMediaMaterial extends BaseMaterial {
    /**
     * 宽
     */
    private Integer width;

    /**
     * 高
     */
    private Integer height;

    /**
     * 绿幕背景
     */
    private GreenBackground greenBackground;

    /**
     * Lut文件
     */
    private Lut lut;

    @Override
    public String getType() {
        return MaterialTypeEnum.MATERIAL_TYPE_VIDEO;
    }

    @Data
    @Accessors(chain = true)
    public static class GreenBackground {
        /**
         * 背景素材ID，支持视频、图片
         */
        private Long materialId;

        /**
         * 背景基础颜色
         */
        private String baseBackgroundColor;

        /**
         * 强度
         */
        private Integer strength;

        /**
         * 边缘羽化
         */
        private Integer edgeFeather;

        /**
         * 边缘清理
         */
        private Integer edgeCleanup;
    }

    @Data
    @Accessors(chain = true)
    public static class Lut {
        /**
         * LUT文件地址
         */
        private String url;

        /**
         * 强度 [0, 100]
         */
        private Integer strength;

        /**
         * 肤色保护 [0, 100]
         */
        private Integer skinToneCorrection;

    }
}
