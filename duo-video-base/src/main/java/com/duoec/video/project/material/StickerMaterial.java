package com.duoec.video.project.material;

import lombok.Data;

@Data
public class StickerMaterial extends BaseMaterial {
    /**
     * 资源ID
     */
    private Long resourceId;

    @Override
    public String getType() {
        return MaterialTypeEnum.MATERIAL_TYPE_STICKER;
    }
}
