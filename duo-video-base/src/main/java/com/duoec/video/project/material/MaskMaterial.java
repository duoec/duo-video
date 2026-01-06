package com.duoec.video.project.material;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class MaskMaterial extends BaseMaterial {
    /**
     * 资源ID
     */
    private Long resourceId;

    /**
     * 蒙板配置
     */
    private MaskConfig config;

    @Override
    public String getType() {
        return MaterialTypeEnum.MATERIAL_TYPE_MASK;
    }

    @Data
    public static class MaskConfig {
        // TODO 目前直接使用剪映的值，单位很奇葩，后面会进行调整！！！
        private Integer expansion;
        private Integer feather;
        private Integer roundCorner;
        private Integer rotation;
        private Integer aspectRatio;
        private Integer invert;

        /**
         * 蒙板宽度比例（与视频宽的比值）
         */
        private Double width;

        /**
         * 蒙板高度比例（与视频高的比值）
         */
        private Double height;

        /**
         * 蒙板中心位置X轴（与视频宽的比值，有点奇葩）
         */
        private Double centerX;

        /**
         * 蒙板中心位置Y轴（与视频高的比值，有点奇葩）
         */
        private Double centerY;

        /**
         * 蒙板视频的位移X轴，与剪映界面值一致
         */
        private Integer pointX;

        /**
         * 蒙板视频的位移Y轴，与剪映界面值一致
         */
        private Integer pointY;
    }
}

