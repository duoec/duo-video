package com.duoec.video.project.material;

import lombok.Data;

@Data
public class SoundMaterial extends BaseEffectMaterial {
    @Override
    public String getType() {
        return MaterialTypeEnum.MATERIAL_TYPE_SOUND;
    }
}
