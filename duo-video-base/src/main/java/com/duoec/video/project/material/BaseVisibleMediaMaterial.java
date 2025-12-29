package com.duoec.video.project.material;

import lombok.Data;

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
}
