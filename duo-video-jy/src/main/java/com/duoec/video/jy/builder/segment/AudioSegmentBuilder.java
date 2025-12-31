package com.duoec.video.jy.builder.segment;

import com.duoec.video.jy.JianyingProjectBuildState;
import com.duoec.video.jy.builder.JianyingTrackBuilder;
import com.duoec.video.jy.dto.info.Audio;
import com.duoec.video.jy.dto.info.Segment;
import com.duoec.video.jy.dto.info.Track;
import com.duoec.video.jy.utils.JianyingResourceUtils;
import com.duoec.video.jy.utils.JianyingUtils;
import com.duoec.video.jy.utils.UuidUtils;
import com.duoec.video.project.VideoScript;
import com.duoec.video.project.VideoSegment;
import com.duoec.video.project.VideoTimeRange;
import com.duoec.video.project.material.AudioMaterial;
import com.duoec.video.project.material.MaterialTypeEnum;

public class AudioSegmentBuilder extends BaseSegmentBuilder<AudioMaterial> {
    @Override
    public MaterialTypeEnum getMaterialType() {
        return MaterialTypeEnum.AUDIO_MATERIAL;
    }

    @Override
    public Segment build(JianyingProjectBuildState state, VideoScript videoScript, VideoSegment videoSegment, AudioMaterial material) {
        VideoTimeRange segmentTime = videoSegment.getTime();
        VideoTimeRange materialTime = material.getTime();

        // 复制到本地目录
        String path = JianyingResourceUtils.copyToLocalResources(state, material.getLocalFile(), JianyingResourceUtils.RS_NAME_AUDIO);
        Audio audio = JianyingUtils.getDefaultAudio()
                .setId(UuidUtils.next())
                .setPath(path)
                .setDuration(materialTime.getDuration() * JianyingUtils.LONG_1000);
        state.getJianyingProject().getMaterials().getAudios().add(audio);

        Track audioTrack = JianyingTrackBuilder.getOrCreateTrack(state.getJianyingProject(), Track.TYPE_AUDIO, "音频", segmentTime.getStart(), segmentTime.getEndTime());
        Segment segment = getSegment(videoSegment, audio);
        audioTrack.getSegments().add(segment);

        return segment;
    }
}
