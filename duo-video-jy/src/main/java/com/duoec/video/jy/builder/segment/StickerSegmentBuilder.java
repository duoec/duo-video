package com.duoec.video.jy.builder.segment;

import com.duoec.base.core.util.JsonUtils;
import com.duoec.base.exceptions.DuoServiceException;
import com.duoec.video.jy.JianyingProjectBuildState;
import com.duoec.video.jy.builder.JianyingTrackBuilder;
import com.duoec.video.jy.dto.JyResource;
import com.duoec.video.jy.dto.info.Segment;
import com.duoec.video.jy.dto.info.Sticker;
import com.duoec.video.jy.dto.info.Track;
import com.duoec.video.jy.utils.JianyingResourceUtils;
import com.duoec.video.jy.utils.UuidUtils;
import com.duoec.video.project.VideoScript;
import com.duoec.video.project.VideoSegment;
import com.duoec.video.project.VideoTimeRange;
import com.duoec.video.project.material.MaterialTypeEnum;
import com.duoec.video.project.material.StickerMaterial;
import com.google.common.collect.Lists;

public class StickerSegmentBuilder extends BaseSegmentBuilder<StickerMaterial> {
    @Override
    public MaterialTypeEnum getMaterialType() {
        return MaterialTypeEnum.STICKER_MATERIAL;
    }

    @Override
    public Segment build(JianyingProjectBuildState state, VideoScript videoScript, VideoSegment videoSegment, StickerMaterial material) {
        Long resourceId = material.getResourceId();
        JyResource resource = JianyingResourceUtils.getJyResource(videoSegment.getType(), resourceId);
        if (resource == null) {
            throw new DuoServiceException("无法找到贴纸资源：resourceId=" + resourceId);
        }

        // 复制资源文件到剪映本地素材库
        JianyingResourceUtils.downloadResources(state.getProjectLocalResourceDir(), resource.getResources());

        Sticker sticker = JsonUtils.toObject(resource.getMainConfig(), Sticker.class)
                .setId(UuidUtils.next());
        state.getJianyingProject().getMaterials().getStickers().add(sticker);

        VideoTimeRange segmentTime = videoSegment.getTime();
        Track audioTrack = JianyingTrackBuilder.getOrCreateTrack(state.getJianyingProject(), Track.TYPE_STICKER, "贴纸", segmentTime.getStart(), segmentTime.getEndTime());

        Segment segment = getSegment(videoSegment, sticker)
                .setExtraMaterialRefs(Lists.newArrayList())
                .setTrackRenderIndex(0);
        audioTrack.getSegments().add(segment);

        return segment;
    }
}
