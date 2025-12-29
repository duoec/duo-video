package com.duoec.video.project.material;

import lombok.Data;

@Data
public class VideoGreenBackgroundMaterial extends VideoMaterial {
    /**
     * 背景素材ID
     */
    private Long backgroundMaterialId;

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

    @Override
    public String getType() {
        return MaterialTypeEnum.MATERIAL_TYPE_GREEN_BG_VIDEO;
    }
}
