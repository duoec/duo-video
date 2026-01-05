package com.duoec.video.project.material;

import lombok.Data;

@Data
public class TransitionMaterial extends BaseMaterial {
    /**
     * 资源ID
     */
    private Long resourceId;

    /**
     * 转场时长，未设置时使用此转场资源默认时长，单位：毫秒
     */
    private Long duration;

    @Override
    public String getType() {
        return MaterialTypeEnum.MATERIAL_TYPE_TRANSITION;
    }
}
