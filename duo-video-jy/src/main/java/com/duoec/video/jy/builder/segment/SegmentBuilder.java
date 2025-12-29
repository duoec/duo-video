package com.duoec.video.jy.builder.segment;

import com.duoec.video.jy.JianyingProjectBuildState;
import com.duoec.video.jy.dto.info.Segment;
import com.duoec.video.project.VideoScript;
import com.duoec.video.project.VideoSegment;
import com.duoec.video.project.material.BaseMaterial;
import com.duoec.video.project.material.MaterialTypeEnum;

public interface SegmentBuilder<T extends BaseMaterial> {
    MaterialTypeEnum getMaterialType();

    Segment build(JianyingProjectBuildState state, VideoScript videoScript, VideoSegment segment, T material);
}
