package com.duoec.video.jy.builder.segment;

import com.duoec.video.jy.JianyingProjectBuildState;
import com.duoec.video.jy.builder.JianyingTrackBuilder;
import com.duoec.video.jy.dto.info.Segment;
import com.duoec.video.jy.dto.info.Track;
import com.duoec.video.jy.dto.info.Video;
import com.duoec.video.jy.utils.JianyingResourceUtils;
import com.duoec.video.jy.utils.JianyingUtils;
import com.duoec.video.jy.utils.UuidUtils;
import com.duoec.video.project.VideoScript;
import com.duoec.video.project.VideoSegment;
import com.duoec.video.project.VideoTimeRange;
import com.duoec.video.project.material.MaterialTypeEnum;
import com.duoec.video.project.material.VideoMaterial;

public class VideoSegmentBuilder extends BaseSegmentBuilder<VideoMaterial> {
    @Override
    public MaterialTypeEnum getMaterialType() {
        return MaterialTypeEnum.VIDEO_MATERIAL;
    }

    @Override
    public Segment build(JianyingProjectBuildState state, VideoScript videoScript, VideoSegment videoSegment, VideoMaterial material) {
        VideoTimeRange segmentTime = videoSegment.getTime();
        VideoTimeRange materialTime = material.getTime();

        // 复制到本地目录
        String path = JianyingResourceUtils.copyToLocalResources(state, material.getLocalFile(), JianyingResourceUtils.RS_NAME_VIDEO);
        Video video = JianyingUtils.getDefaultVideo()
                .setId(UuidUtils.next())
                .setPath(path)
                .setMaterialName(material.getLocalFile().getName())
                .setWidth(material.getWidth())
                .setHeight(material.getHeight())
                .setDuration(materialTime.getDuration() * JianyingUtils.LONG_1000);
        state.getJianyingProject().getMaterials().getVideos().add(video);

        Track videoTrack = JianyingTrackBuilder.getOrCreateTrack(state, Track.TYPE_VIDEO, "视频", segmentTime.getStart(), segmentTime.getEndTime());
        Segment segment = getSegment(videoSegment, video);
        videoTrack.getSegments().add(segment);

        return segment;
    }
}
