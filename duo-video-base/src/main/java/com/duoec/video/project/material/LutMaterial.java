package com.duoec.video.project.material;

import lombok.Data;

@Data
public class LutMaterial extends BaseMaterial {
    @Override
    public String getType() {
        return MaterialTypeEnum.MATERIAL_TYPE_LUT;
    }
}
