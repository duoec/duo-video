package com.duoec.video.jy.builder.segment;

import com.duoec.base.core.DuoServerConsts;
import com.duoec.base.core.util.FileUtils;
import com.duoec.base.core.util.JsonUtils;
import com.duoec.video.jy.JianyingProjectBuildState;
import com.duoec.video.jy.builder.JianyingSegmentBuilder;
import com.duoec.video.jy.builder.JianyingTrackBuilder;
import com.duoec.video.jy.dto.JyResource;
import com.duoec.video.jy.dto.TimeRange;
import com.duoec.video.jy.dto.info.*;
import com.duoec.video.jy.utils.JianyingResourceUtils;
import com.duoec.video.jy.utils.JianyingUtils;
import com.duoec.video.jy.utils.UuidUtils;
import com.duoec.video.project.VideoScript;
import com.duoec.video.project.VideoSegment;
import com.duoec.video.project.VideoTimeRange;
import com.duoec.video.project.material.*;
import com.duoec.video.project.material.BaseMaterial;
import lombok.AllArgsConstructor;
import org.assertj.core.util.Lists;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.util.List;
import java.util.Map;

public class VideoSegmentBuilder extends BaseVisibleMediaMaterialBuilder<VideoMaterial> {
    @Override
    public MaterialTypeEnum getMaterialType() {
        return MaterialTypeEnum.VIDEO_MATERIAL;
    }

    @Override
    public Segment build(JianyingProjectBuildState state, VideoScript videoScript, VideoSegment videoSegment, VideoMaterial material) {
        VideoTimeRange segmentTime = videoSegment.getTime();
        VideoTimeRange materialTime = material.getTime();
        JianYingProjectInfo jianyingProject = state.getJianyingProject();

        // 复制到本地目录
        String path = JianyingResourceUtils.copyToLocalResources(state, material.getLocalFile(), JianyingResourceUtils.RS_NAME_VIDEO);
        Video video = JianyingUtils.getDefaultVideo()
                .setId(UuidUtils.next())
                .setPath(path)
                .setMaterialName(material.getLocalFile().getName())
                .setWidth(material.getWidth())
                .setHeight(material.getHeight())
                .setDuration(materialTime.getDuration() * JianyingUtils.LONG_1000);
        jianyingProject.getMaterials().getVideos().add(video);

        Track videoTrack = JianyingTrackBuilder.getOrCreateTrack(jianyingProject, Track.TYPE_VIDEO, "视频", segmentTime.getStart(), segmentTime.getEndTime());
        Segment segment = getSegment(jianyingProject, videoSegment, video);
        TimeRange targetTimeRange = segment.getTargetTimeRange();
        TimeRange sourceTimeRange = segment.getSourceTimeRange();
        JianyingSegmentBuilder.setSegmentCommonAttributes(state, videoSegment, segment, material);
        videoTrack.getSegments().add(segment);

        // 绿幕背景
        GreenBackgroundDto backgroundDto = getGreenBackgroundSegment(state, videoScript, videoSegment, material, segment);
        if (backgroundDto != null) {
            // 合并成 复合片段
            // 绿幕视频需要将主视频的时间拉到整个视频
            TimeRange timeRange = new TimeRange(0L, material.getTime().getDuration() * JianyingUtils.LONG_1000);
            segment.setTargetTimeRange(timeRange)
                    .setSourceTimeRange(timeRange);

            String combinationId = material.getId() + DuoServerConsts.UNDERLINE_STR + backgroundDto.material.getId();
            Segment finalSegment = segment;
            // 重置 Segment 时间
            segment = state.getCombinationSegment(
                            combinationId,
                            cid -> JianyingUtils.combine(jianyingProject, Lists.newArrayList(finalSegment, backgroundDto.segment))
                    )
                    // 重置 Segment 时间
                    .setTargetTimeRange(targetTimeRange)
                    .setSourceTimeRange(sourceTimeRange);
        }

        // LUT
        List<Effect> lutEffectList = buildLut(state, videoScript, videoSegment, material);
        if (!CollectionUtils.isEmpty(lutEffectList)) {
            List<Effect> effects = jianyingProject.getMaterials().getEffects();
            for (Effect effect : lutEffectList) {
                segment.getExtraMaterialRefs().add(effect.getId());
                effects.add(effect);
            }
        }

        // 处理Refs
        setRefs(state, videoScript, videoSegment, material, segment);

        if (backgroundDto != null) {
            // 如果是复合片段时，不需要后续的统一处理，这里直接返回null了，但这逻辑不好，后续需要优化
            return null;
        }
        return segment;
    }

    private void setRefs(JianyingProjectBuildState state, VideoScript videoScript, VideoSegment videoSegment, VideoMaterial material, Segment segment) {
        Map<Long, String> refs = videoSegment.getRefs();
        if (CollectionUtils.isEmpty(refs)) {
            return;
        }
        refs.forEach((materialId, type) -> {
            BaseMaterial refMaterial = state.getMaterial(materialId);
            if (refMaterial == null) {
                return;
            }
            if (refMaterial instanceof TransitionMaterial transitionMaterial) {
                // 添加转场
                JyResource transitionConfig = JianyingResourceUtils.getJyResource(transitionMaterial.getType(), transitionMaterial.getResourceId());
                if (transitionConfig == null) {
                    return;
                }

                // 复制资源文件到剪映本地素材库
                JianyingResourceUtils.downloadResources(state.getProjectLocalResourceDir(), transitionConfig.getResources());
                Transition transition = JsonUtils.toObject(transitionConfig.getMainConfig(), Transition.class)
                        .setId(UuidUtils.next());
                Long duration = transitionMaterial.getDuration();
                if (duration != null) {
                    transition.setDuration(duration * JianyingUtils.LONG_1000);
                }

                state.getJianyingProject().getMaterials().getTransitions().add(transition);
                segment.getExtraMaterialRefs().add(transition.getId());
            }
        });
    }

    private GreenBackgroundDto getGreenBackgroundSegment(JianyingProjectBuildState state, VideoScript videoScript, VideoSegment videoSegment, VideoMaterial material, Segment segment) {
        // 绿幕资源 resourceId=267417011880001538
        BaseVisibleMediaMaterial.GreenBackground greenBackground = material.getGreenBackground();
        if (greenBackground == null) {
            // 没有绿幕
            return null;
        }

        Long duration = material.getDuration() * JianyingUtils.LONG_1000;

        JyResource greenBackgroundResource = JianyingResourceUtils.getJyResource(JianyingResourceUtils.RS_NAME_EFFECT, 267417011880001538L);
        // 下载剪映资源
        JianyingResourceUtils.downloadResources(state.getProjectLocalResourceDir(), greenBackgroundResource.getResources());

        BaseVisibleMediaMaterial backgroundMaterial = state.getMaterial(greenBackground.getMaterialId());

        // 复制到本地目录
        File backgroundFile = backgroundMaterial.getLocalFile();
        String path = JianyingResourceUtils.copyToLocalResources(state, backgroundFile, JianyingResourceUtils.RS_NAME_IMAGE);
        String type = FileUtils.isImageFile(backgroundFile.getName()) ? "photo" : "video";
        Video video = JianyingUtils.getDefaultVideo()
                .setId(UuidUtils.next())
                .setPath(path)
                .setType(type)
                .setMaterialName(backgroundFile.getName())
                .setWidth(backgroundMaterial.getWidth())
                .setHeight(backgroundMaterial.getHeight())
                .setDuration(duration);
        state.getJianyingProject().getMaterials().getVideos().add(video);

        VideoSegment backgroundVideoSegment = new VideoSegment();
        VideoTimeRange time = material.getTime();
        backgroundVideoSegment.setTime(time);
        backgroundVideoSegment.setMaterialStart(0L);

        // 取色
        Chroma chroma = JsonUtils.toObject(greenBackgroundResource.getMainConfig(), Chroma.class)
                .setColor(greenBackground.getBaseBackgroundColor())
                .setEdgeSmoothValue(greenBackground.getEdgeFeather() / 100.0)
                .setId(UuidUtils.next())
                .setIntensityValue(greenBackground.getStrength() / 100.0)
                .setSpillValue(greenBackground.getEdgeCleanup() / 100.0);
        state.getJianyingProject().getMaterials().getChromas().add(chroma);
        segment.getExtraMaterialRefs().add(chroma.getId());

        Segment backgroundSegment = getSegment(state.getJianyingProject(), backgroundVideoSegment, video)
                .setRenderIndex(1)
                .setTrackRenderIndex(1);
        Track videoTrack = JianyingTrackBuilder.getOrCreateTrack(state.getJianyingProject(), Track.TYPE_VIDEO, "绿幕背景", time.getStart(), time.getEndTime());
        videoTrack.getSegments().add(backgroundSegment);

        return new GreenBackgroundDto(backgroundSegment, backgroundMaterial);
    }

    @AllArgsConstructor
    private static class GreenBackgroundDto {
        Segment segment;
        BaseVisibleMediaMaterial material;
    }
}
