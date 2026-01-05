package com.duoec.video.jy.builder.segment;

import com.duoec.base.core.util.JsonUtils;
import com.duoec.base.exceptions.DuoServiceException;
import com.duoec.video.jy.JianyingProjectBuildState;
import com.duoec.video.jy.builder.JianyingTrackBuilder;
import com.duoec.video.jy.dto.JyResource;
import com.duoec.video.jy.dto.info.Segment;
import com.duoec.video.jy.dto.info.Track;
import com.duoec.video.jy.dto.info.VideoEffect;
import com.duoec.video.jy.utils.JianyingResourceUtils;
import com.duoec.video.jy.utils.UuidUtils;
import com.duoec.video.project.VideoScript;
import com.duoec.video.project.VideoSegment;
import com.duoec.video.project.VideoTimeRange;
import com.duoec.video.project.material.FaceEffectMaterial;
import com.duoec.video.project.material.MaterialTypeEnum;
import com.duoec.video.project.material.VideoEffectMaterial;
import com.google.common.collect.Lists;

public class FaceEffectSegmentBuilder extends BaseSegmentBuilder<FaceEffectMaterial> {
    @Override
    public MaterialTypeEnum getMaterialType() {
        return MaterialTypeEnum.FACE_EFFECT_MATERIAL;
    }

    @Override
    public Segment build(JianyingProjectBuildState state, VideoScript videoScript, VideoSegment videoSegment, FaceEffectMaterial material) {
        Long resourceId = material.getResourceId();
        JyResource resource = JianyingResourceUtils.getJyResource(videoSegment.getType(), resourceId);
        if (resource == null) {
            throw new DuoServiceException("无法找到脸部特效资源：resourceId=" + resourceId);
        }

        // 复制资源文件到剪映本地素材库
        JianyingResourceUtils.downloadResources(state.getProjectLocalResourceDir(), resource.getResources());

        VideoEffect faceEffect = JsonUtils.toObject(resource.getMainConfig(), VideoEffect.class)
                .setId(UuidUtils.next());
        state.getJianyingProject().getMaterials().getVideoEffects().add(faceEffect);

        Segment segment = getSegment(state.getJianyingProject(), videoSegment, faceEffect)
                .setExtraMaterialRefs(Lists.newArrayList())
                .setTrackRenderIndex(0);

        VideoTimeRange segmentTime = videoSegment.getTime();
        Track effectTrack = JianyingTrackBuilder.getOrCreateTrack(state.getJianyingProject(), Track.TYPE_EFFECT, "画面特效", segmentTime.getStart(), segmentTime.getEndTime());
        effectTrack.getSegments().add(segment);

        return segment;
    }
}
