package com.duoec.video.jy.builder.segment;

import com.duoec.video.jy.dto.TimeRange;
import com.duoec.video.jy.dto.info.Segment;
import com.duoec.video.jy.utils.JianyingUtils;
import com.duoec.video.jy.utils.UuidUtils;
import com.duoec.video.project.VideoSegment;
import com.duoec.video.project.VideoTimeRange;
import com.duoec.video.project.material.BaseMaterial;

import java.util.Optional;

public abstract class BaseSegmentBuilder<T extends BaseMaterial> implements SegmentBuilder<T> {
    protected static Segment getSegment(VideoSegment segment, com.duoec.video.jy.dto.info.BaseMaterial material) {
        VideoTimeRange segmentTime = segment.getTime();
        int speed = Optional.ofNullable(segment.getSpeed()).orElse(100);
        long materialDuration = segmentTime.getDuration() * speed / 100;
        long materialStart = Optional.ofNullable(segment.getMaterialStart()).orElse(0L);
        return JianyingUtils.getDefaultSegment()
                .setId(UuidUtils.next())
                .setMaterialId(material.getId())
                .setRenderIndex(2)
                .setTrackRenderIndex(2)
                .setSourceTimeRange(
                        new TimeRange()
                                .setStart(materialStart * JianyingUtils.LONG_1000)
                                .setDuration(materialDuration * JianyingUtils.LONG_1000)
                )
                .setTargetTimeRange(
                        new TimeRange()
                                .setStart(segmentTime.getStart() * JianyingUtils.LONG_1000)
                                .setDuration(segmentTime.getDuration() * JianyingUtils.LONG_1000)
                );
    }
}
