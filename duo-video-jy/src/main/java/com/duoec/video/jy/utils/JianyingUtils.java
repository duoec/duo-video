package com.duoec.video.jy.utils;

import com.duoec.base.core.util.FileUtils;
import com.duoec.base.exceptions.DuoServiceException;
import com.duoec.video.jy.builder.JianyingTrackBuilder;
import com.duoec.video.jy.dto.MaterialData;
import com.duoec.video.jy.dto.TimeRange;
import com.duoec.video.jy.dto.font.FontConfig;
import com.duoec.video.jy.dto.info.*;
import com.duoec.video.jy.dto.meta.JianYingProjectMeta;
import com.duoec.video.jy.dto.meta.Value;
import org.assertj.core.util.Lists;
import org.assertj.core.util.Sets;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public class JianyingUtils {
    public static long LONG_1000 = 1000;

    public static double pxToJianyingNonlinear(int px) {
        return 0.00196 * Math.pow(px, 1.013);
    }

    public static Video getDefaultVideo() {
        return FileUtils.readJson("tpl/empty_jy_material_video.json", Video.class);
    }

    public static Audio getDefaultAudio() {
        return FileUtils.readJson("tpl/empty_yj_material_audio.json", Audio.class);
    }

    public static FontConfig getDefaultFontConfig() {
        return FileUtils.readJson("tpl/empty_jy_text_styles.json", FontConfig.class);
    }

    public static Text getDefaultText() {
        return FileUtils.readJson("tpl/empty_yj_material_text.json", Text.class);
    }

    public static JianYingProjectInfo getDefaultProjectInfo() {
        return FileUtils.readJson("tpl/empty_jy_project_info.json", JianYingProjectInfo.class);
    }

    public static Segment getDefaultSegment() {
        return FileUtils.readJson("tpl/empty_jy_segment.json", Segment.class);
    }

    public static Segment getDefaultCombinationSegment() {
        return FileUtils.readJson("tpl/empty_jy_combination_segment.json", Segment.class);
    }

    public static Video getDefaultCombinationMaterial() {
        return FileUtils.readJson("tpl/empty_jy_combination_video_material.json", Video.class);
    }

    public static JianYingProjectMeta getDefaultProjectMeta() {
        return FileUtils.readJson("tpl/empty_draft_meta_info.json", JianYingProjectMeta.class);
    }

    public static Value getDefaultProjectMetaMaterialValue() {
        return FileUtils.readJson("tpl/empty_jy_meta_material_value.json", Value.class);
    }

    public static Draft getDefaultDraft() {
        return FileUtils.readJson("tpl/empty_jy_draft.json", Draft.class);
    }

    public static List<Double> hexToNormalizedRGB(String hexColor) {
        if (hexColor == null) {
            hexColor = "#FFFFFF";
        }
        // 去除可能的前缀 "#"（如 "#000000" → "000000"）
        String hex = hexColor.startsWith("#") ? hexColor.substring(1) : hexColor;

        // 确保是6位十六进制颜色（如 "000000"）
        if (hex.length() != 6) {
            throw new IllegalArgumentException("Invalid hex color format. Expected #RRGGBB or RRGGBB.");
        }

        // 解析 R, G, B 分量
        int r = Integer.parseInt(hex.substring(0, 2), 16);
        int g = Integer.parseInt(hex.substring(2, 4), 16);
        int b = Integer.parseInt(hex.substring(4, 6), 16);

        // 归一化到 [0.0, 1.0]
        double[] normalized = new double[3];
        normalized[0] = r / 255.0f;
        normalized[1] = g / 255.0f;
        normalized[2] = b / 255.0f;

        return List.of(normalized[0], normalized[1], normalized[2]);
    }

    /**
     * 计算音量线性增益，如果 dB < -100，则直接返回0
     *
     * @param dB 分贝
     */
    public static double amplitudeGain(Double dB) {
        if (dB == null) {
            return 1.0;
        }
        if (dB <= -1.0) {
            return 0.0;
        }
        return Math.pow(10, dB / 20);
    }

    /**
     * 合并为复合片段
     * @param projectInfo 工程
     * @param segments 需要合并的segments
     */
    public static MaterialData combine(JianYingProjectInfo projectInfo, List<Segment> segments) {
        if (CollectionUtils.isEmpty(segments)) {
            // 没有需要合并的
            throw new DuoServiceException("复合片段失败：合并内容为空");
        }
        // 重新排序
        List<Track> tracks = projectInfo.getTracks();
        tracks.sort(Comparator.comparing(Track::getDuoLayoutIndex));

        Set<String> ids = Sets.newHashSet();
        long maxDuration = segments.stream().mapToLong(segment -> segment.getSourceTimeRange().getDuration()).max().getAsLong();
        segments.forEach(segment -> {
            ids.add(segment.getId());
            ids.add(segment.getMaterialId());
            if (!CollectionUtils.isEmpty(segment.getExtraMaterialRefs())) {
                ids.addAll(segment.getExtraMaterialRefs());
            }
        });

        Draft draft = getDefaultDraft()
                .setId(UuidUtils.next())
                .setCombinationId(UuidUtils.next());
        projectInfo.getMaterials().getDrafts().add(draft);

        JianYingProjectInfo draftInfo = draft.getDraft();
        draftInfo.getCanvasConfig().setWidth(projectInfo.getCanvasConfig().getWidth());
        draftInfo.getCanvasConfig().setHeight(projectInfo.getCanvasConfig().getHeight());

        tracks.forEach(track -> {
            if (track.getSegments() == null) {
                return;
            }
            List<Segment> remainSegments = Lists.newArrayList();
            List<Segment> moveSegments = Lists.newArrayList();

            track.getSegments().forEach(segment -> {
                if (ids.contains(segment.getId())) {
                    moveSegments.add(segment);
                } else {
                    remainSegments.add(segment);
                }
            });
            if (moveSegments.isEmpty()) {
                return;
            }
            track.setSegments(remainSegments);

            Track newTrack = new Track()
                    .setAttribute(track.getAttribute())
                    .setName(track.getName())
                    .setId(UuidUtils.next())
                    .setFlag(track.getFlag())
                    .setDefaultName(track.getDefaultName())
                    .setDuoLayoutIndex(track.getDuoLayoutIndex())
                    .setType(track.getType())
                    .setSegments(moveSegments);
            draftInfo.getTracks().add(newTrack);
        });

        tracks = tracks.stream().filter(track -> !CollectionUtils.isEmpty(track.getSegments())).collect(Collectors.toList());
        projectInfo.setTracks(tracks);

        // 处理其它的资源 - 使用 Lambda 表达式方式
        Materials materials = projectInfo.getMaterials();

        moveMaterials(draftInfo, materials, Materials::getTextTemplates, Materials::setTextTemplates, ids);
        moveMaterials(draftInfo, materials, Materials::getVideos, Materials::setVideos, ids);
        moveMaterials(draftInfo, materials, Materials::getAudios, Materials::setAudios, ids);
        moveMaterials(draftInfo, materials, Materials::getTexts, Materials::setTexts, ids);
        moveMaterials(draftInfo, materials, Materials::getStickers, Materials::setStickers, ids);
        moveMaterials(draftInfo, materials, Materials::getEffects, Materials::setEffects, ids);
        moveMaterials(draftInfo, materials, Materials::getTransitions, Materials::setTransitions, ids);
        moveMaterials(draftInfo, materials, Materials::getVideoEffects, Materials::setVideoEffects, ids);
        moveMaterials(draftInfo, materials, Materials::getAudioEffects, Materials::setAudioEffects, ids);
        moveMaterials(draftInfo, materials, Materials::getAudioFades, Materials::setAudioFades, ids);
        moveMaterials(draftInfo, materials, Materials::getSpeeds, Materials::setSpeeds, ids);
        moveMaterials(draftInfo, materials, Materials::getMasks, Materials::setMasks, ids);
        moveMaterials(draftInfo, materials, Materials::getMaterialAnimations, Materials::setMaterialAnimations, ids);
        moveMaterials(draftInfo, materials, Materials::getChromas, Materials::setChromas, ids);
        moveMaterials(draftInfo, materials, Materials::getCanvases, Materials::setCanvases, ids);
        moveMaterials(draftInfo, materials, Materials::getBeats, Materials::setBeats, ids);
        moveMaterials(draftInfo, materials, Materials::getLoudnesses, Materials::setLoudnesses, ids);
        moveMaterials(draftInfo, materials, Materials::getRealtimeDenoises, Materials::setRealtimeDenoises, ids);
        moveMaterials(draftInfo, materials, Materials::getSoundChannelMappings, Materials::setSoundChannelMappings, ids);
        moveMaterials(draftInfo, materials, Materials::getVideoTrackings, Materials::setVideoTrackings, ids);
        moveMaterials(draftInfo, materials, Materials::getVocalBeautifys, Materials::setVocalBeautifys, ids);
        moveMaterials(draftInfo, materials, Materials::getVocalSeparations, Materials::setVocalSeparations, ids);
        moveMaterials(draftInfo, materials, Materials::getAiTranslates, Materials::setAiTranslates, ids);
        moveMaterials(draftInfo, materials, Materials::getTimeMarks, Materials::setTimeMarks, ids);
        moveMaterials(draftInfo, materials, Materials::getCommonMask, Materials::setCommonMask, ids);
        moveMaterials(draftInfo, materials, Materials::getPlaceholders, Materials::setPlaceholders, ids);

        // 添加复合material
        Video combinationMaterial = getDefaultCombinationMaterial()
                .setId(UuidUtils.next())
                .setWidth(projectInfo.getCanvasConfig().getWidth())
                .setHeight(projectInfo.getCanvasConfig().getHeight());
        projectInfo.getMaterials().getVideos().add(combinationMaterial);

        // 添加segment
        TimeRange timeRange = new TimeRange(0L, maxDuration);
        Segment combinationSegment = getDefaultCombinationSegment()
                .setId(UuidUtils.next())
                .setMaterialId(combinationMaterial.getId())
                .setExtraMaterialRefs(Lists.newArrayList(
                        draft.getId()
                ))
                .setSourceTimeRange(timeRange)
                .setTargetTimeRange(timeRange);
        long start = timeRange.getStart() / LONG_1000;
        long end = start + timeRange.getDuration() / LONG_1000;
        Track videoTrack = JianyingTrackBuilder.getOrCreateTrack(projectInfo, Track.TYPE_VIDEO, "视频", start, end);
        videoTrack.getSegments().add(combinationSegment);

        return new MaterialData(combinationSegment, combinationMaterial, false, draft.getId());
    }

    /**
     * 移动材料到目标 Draft
     * 使用 Lambda 表达式方式，类似 MyBatis Plus 的 Materials::getXxx 语法
     *
     * @param draft 目标 draft
     * @param sourceMaterials 源材料集合
     * @param getter 获取材料列表的方法引用，如 Materials::getVideos
     * @param setter 设置材料列表的方法引用，如 Materials::setVideos
     * @param ids 需要移动的材料 ID 集合
     * @param <T> 材料类型
     */
    private static <T extends BaseMaterial> void moveMaterials(
            JianYingProjectInfo draft,
            Materials sourceMaterials,
            Function<Materials, List<T>> getter,
            BiConsumer<Materials, List<T>> setter,
            Set<String> ids) {

        // 获取源材料列表
        List<T> sourceMaterialList = getter.apply(sourceMaterials);
        if (CollectionUtils.isEmpty(sourceMaterialList) || CollectionUtils.isEmpty(ids)) {
            return;
        }

        // 找到需要移动的元素
        List<T> toMove = sourceMaterialList.stream()
                .filter(material -> ids.contains(material.getId()))
                .toList();

        if (CollectionUtils.isEmpty(toMove)) {
            return;
        }

        // 从源列表中删除
        sourceMaterialList.removeAll(toMove);

        // 确保 draft 有 materials
        if (draft.getMaterials() == null) {
            draft.setMaterials(new Materials());
        }

        // 获取目标材料列表
        Materials draftMaterials = draft.getMaterials();
        List<T> targetList = getter.apply(draftMaterials);

        // 如果目标列表为空，创建新列表
        if (targetList == null) {
            targetList = new ArrayList<>();
            setter.accept(draftMaterials, targetList);
        }

        // 添加到目标列表
        targetList.addAll(toMove);
    }

}
