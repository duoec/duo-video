package com.duoec.video.project.material;

import com.duoec.video.project.VideoTimeRange;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AudioMaterial extends BaseMaterial {
    /**
     * 限定时长，未指定的时为整个视频
     */
    private VideoTimeRange time;

    @Override
    public String getType() {
        return MaterialTypeEnum.MATERIAL_TYPE_AUDIO;
    }
}

