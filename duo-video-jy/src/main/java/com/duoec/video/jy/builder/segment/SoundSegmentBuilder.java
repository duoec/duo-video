package com.duoec.video.jy.builder.segment;

import com.duoec.base.core.util.JsonUtils;
import com.duoec.base.exceptions.DuoServiceException;
import com.duoec.video.jy.JianyingProjectBuildState;
import com.duoec.video.jy.builder.JianyingTrackBuilder;
import com.duoec.video.jy.dto.JyResource;
import com.duoec.video.jy.dto.info.Audio;
import com.duoec.video.jy.dto.info.Segment;
import com.duoec.video.jy.dto.info.Track;
import com.duoec.video.jy.utils.JianyingResourceUtils;
import com.duoec.video.jy.utils.UuidUtils;
import com.duoec.video.project.VideoScript;
import com.duoec.video.project.VideoSegment;
import com.duoec.video.project.VideoTimeRange;
import com.duoec.video.project.material.MaterialTypeEnum;
import com.duoec.video.project.material.SoundMaterial;
import com.google.common.collect.Lists;

public class SoundSegmentBuilder extends BaseSegmentBuilder<SoundMaterial> {
    @Override
    public MaterialTypeEnum getMaterialType() {
        return MaterialTypeEnum.SOUND_MATERIAL;
    }

    @Override
    public Segment build(JianyingProjectBuildState state, VideoScript videoScript, VideoSegment videoSegment, SoundMaterial material) {
        Long resourceId = material.getResourceId();
        JyResource resource = JianyingResourceUtils.getJyResource(videoSegment.getType(), resourceId);
        if (resource == null) {
            throw new DuoServiceException("无法找到特效音资源：resourceId=" + resourceId);
        }

        Audio audio = JsonUtils.toObject(resource.getMainConfig(), Audio.class)
                .setId(UuidUtils.next());
        state.getJianyingProject().getMaterials().getAudios().add(audio);

        Segment segment = getSegment(videoSegment, audio)
                .setExtraMaterialRefs(Lists.newArrayList())
                .setTrackRenderIndex(0);

        VideoTimeRange segmentTime = videoSegment.getTime();
        Track audioTrack = JianyingTrackBuilder.getOrCreateTrack(state.getJianyingProject(), Track.TYPE_AUDIO, "特效音", segmentTime.getStart(), segmentTime.getEndTime());
        audioTrack.getSegments().add(segment);

        return segment;
    }
}
