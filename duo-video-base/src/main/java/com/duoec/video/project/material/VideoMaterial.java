package com.duoec.video.project.material;

import com.duoec.video.project.VideoTimeRange;
import lombok.Data;

@Data
public class VideoMaterial extends BaseVisibleMediaMaterial {
    /**
     * 素材使用时长，未指定的时为整个视频
     */
    private VideoTimeRange time;

    /**
     * 素材总时长，单位：毫秒
     */
    private Long duration;
}
