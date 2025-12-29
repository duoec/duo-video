package com.duoec.video.project.material;

import lombok.Data;

@Data
public class ImageMaterial extends BaseVisibleMediaMaterial {

    @Override
    public String getType() {
        return MaterialTypeEnum.MATERIAL_TYPE_IMAGE;
    }
}
