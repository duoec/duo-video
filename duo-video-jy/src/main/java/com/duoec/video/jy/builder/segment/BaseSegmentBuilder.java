package com.duoec.video.jy.builder.segment;

import com.duoec.video.jy.dto.TimeRange;
import com.duoec.video.jy.dto.info.JianYingProjectInfo;
import com.duoec.video.jy.dto.info.Segment;
import com.duoec.video.jy.dto.info.Speed;
import com.duoec.video.jy.utils.JianyingUtils;
import com.duoec.video.jy.utils.UuidUtils;
import com.duoec.video.project.VideoSegment;
import com.duoec.video.project.VideoTimeRange;
import com.duoec.video.project.material.BaseMaterial;

import java.util.Optional;

public abstract class BaseSegmentBuilder<T extends BaseMaterial> implements SegmentBuilder<T> {
    public static final String STR_SPEED = "speed";

    protected static Segment getSegment(JianYingProjectInfo projectInfo, VideoSegment videoSegment, com.duoec.video.jy.dto.info.BaseMaterial material) {
        VideoTimeRange segmentTime = videoSegment.getTime();
        int speed = Optional.ofNullable(videoSegment.getSpeed()).orElse(100);

        long materialDuration = segmentTime.getDuration() * speed / 100;
        long materialStart = Optional.ofNullable(videoSegment.getMaterialStart()).orElse(0L);
        Segment segment = JianyingUtils.getDefaultSegment()
                .setId(UuidUtils.next())
                .setMaterialId(material.getId())
                .setRenderIndex(2)
                .setTrackRenderIndex(2)
                .setSpeed(speed / 100.0)
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

        if (speed != 100) {
            // 有变速
            Speed speedMaterial = getDefaultSpeed(speed / 100.0);
            projectInfo.getMaterials().getSpeeds().add(speedMaterial);

            segment.getExtraMaterialRefs().add(speedMaterial.getId());
        }
        return segment;
    }


    protected static Speed getDefaultSpeed(double speedRate) {
        return new Speed()
                .setCurveSpeed(null)
                .setId(UuidUtils.next())
                .setMode(0)
                .setSpeed(speedRate)
                .setType(STR_SPEED);
    }
}
