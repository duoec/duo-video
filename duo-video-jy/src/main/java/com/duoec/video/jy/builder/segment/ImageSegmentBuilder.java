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
import com.duoec.video.project.material.ImageMaterial;
import com.duoec.video.project.material.MaterialTypeEnum;

public class ImageSegmentBuilder extends BaseVisibleMediaMaterialBuilder<ImageMaterial> {
    @Override
    public MaterialTypeEnum getMaterialType() {
        return MaterialTypeEnum.IMAGE_MATERIAL;
    }

    @Override
    public Segment build(JianyingProjectBuildState state, VideoScript videoScript, VideoSegment videoSegment, ImageMaterial material) {
        VideoTimeRange segmentTime = videoSegment.getTime();

        // 复制到本地目录
        String path = JianyingResourceUtils.copyToLocalResources(state, material.getLocalFile(), JianyingResourceUtils.RS_NAME_IMAGE);
        Video video = JianyingUtils.getDefaultVideo()
                .setId(UuidUtils.next())
                .setPath(path)
                .setMaterialName(material.getLocalFile().getName())
                .setType("photo")
                .setWidth(material.getWidth())
                .setHeight(material.getHeight())
                .setDuration(segmentTime.getDuration() * JianyingUtils.LONG_1000);
        state.getJianyingProject().getMaterials().getVideos().add(video);

        Track imageTrack = JianyingTrackBuilder.getOrCreateTrack(state.getJianyingProject(), Track.TYPE_VIDEO, "图片", segmentTime.getStart(), segmentTime.getEndTime())
                .setFlag(2);
        Segment segment = getSegment(state.getJianyingProject(), videoSegment, video);
        imageTrack.getSegments().add(segment);

        return segment;
    }
}
