package com.duoec.video.project.material;

import lombok.Data;

@Data
public class FaceEffectMaterial extends BaseEffectMaterial {

    @Override
    public String getType() {
        return MaterialTypeEnum.MATERIAL_TYPE_FACE_EFFECT;
    }
}
