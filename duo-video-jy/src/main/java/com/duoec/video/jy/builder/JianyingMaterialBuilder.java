package com.duoec.video.jy.builder;

import com.duoec.base.core.DuoServerConsts;
import com.duoec.base.core.util.FileUtils;
import com.duoec.base.exceptions.DuoServiceException;
import com.duoec.video.jy.JianyingBuilder;
import com.duoec.video.jy.JianyingProjectBuildState;
import com.duoec.video.jy.dto.TimeRange;
import com.duoec.video.jy.dto.meta.JianYingProjectMeta;
import com.duoec.video.jy.dto.meta.Value;
import com.duoec.video.jy.utils.JianyingResourceUtils;
import com.duoec.video.jy.utils.JianyingUtils;
import com.duoec.video.jy.utils.UuidUtils;
import com.duoec.video.project.VideoSegment;
import com.duoec.video.project.VideoTimeRange;
import com.duoec.video.project.material.*;
import com.duoec.video.utils.ExiftoolUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.assertj.core.util.Sets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class JianyingMaterialBuilder {
    private static final Logger logger = LoggerFactory.getLogger(JianyingMaterialBuilder.class);

    public static void build(JianyingProjectBuildState state) {
        Long taskId = state.getVideoProject().getId();

        Map<BaseMaterial, File> materialFileMap = Maps.newHashMap();
        Optional.ofNullable(state.getVideoProject().getScripts()).orElse(Lists.newArrayList())
                .stream()
                .filter(script -> !CollectionUtils.isEmpty(script.getSegments()))
                .forEach(script -> {
                    script.getSegments()
                            .stream()
                            .filter(segment -> Optional.ofNullable(segment.getVisible()).orElse(true))
                            .forEach(segment -> {
                                downloadMaterials(state, segment.getMaterialId(), materialFileMap);
                            });
                });

        JianyingBuilder.storageService.waitAsyncDownloadTask(taskId);
        materialFileMap.forEach((material, file) -> {
            if (!file.exists()) {
                throw new DuoServiceException("[" + material.getId() + "]素材文件下载失败！");
            }
            material.setLocalFile(file);

            if (material instanceof BaseVisibleMediaMaterial || material instanceof AudioMaterial) {
                material.setLocalFile(file);

                // 设置视频元
                setMaterialMeta(material, file);

                // 添加到剪映本地
                addJianYingLocal(state, material, file);
            }
        });
    }

    private static void downloadMaterials(JianyingProjectBuildState state, Long materialId, Map<BaseMaterial, File> materialFileMap) {
        BaseMaterial material = state.getMaterial(materialId);
        if (material == null) {
            return;
        }

        String url = material.getUrl();
        if (StringUtils.hasLength(url)) {
            // 下载主素材
            Long taskId = state.getVideoProject().getId();

            String fileSuffix = FileUtils.getFileSuffix(url);
            File file = new File(JianyingResourceUtils.JY_RS_DIR, material.getType() + DuoServerConsts.OBLIQUE_LINE_STR + material.getId() + fileSuffix);
            if (!file.exists()) {
                JianyingBuilder.storageService.asyncDownload(taskId, url, file);
            }
            materialFileMap.put(material, file);
        }

        if (material instanceof VideoMaterial videoMaterial) {
            VideoMaterial.GreenBackground greenBackground = videoMaterial.getGreenBackground();
            if (greenBackground != null && greenBackground.getMaterialId() != null) {
                downloadMaterials(state, greenBackground.getMaterialId(), materialFileMap);
            }
        }
    }

    private static void addJianYingLocal(JianyingProjectBuildState state, BaseMaterial material, File file) {
        JianYingProjectMeta projectMeta = state.getJianYingProjectMeta();
        List<Value> values = projectMeta.getDraftMaterials()
                .stream()
                .filter(draftMaterial -> draftMaterial.getType() == 0).findFirst()
                .get()
                .getValue();

        long now = System.currentTimeMillis();
        Value materialValue = JianyingUtils.getDefaultProjectMetaMaterialValue()
                .setId(UuidUtils.next())
                .setExtraInfo(file.getName())
                .setFilePath("./Resources/local/" + material.getType() + DuoServerConsts.OBLIQUE_LINE_STR + file.getName())
                .setImportTime(now / 1000)
                .setImportTimeMs(now)
                .setCreateTime(now / 1000);

        setMeteInfos(material, materialValue);

        values.add(materialValue);
    }

    private static void setMeteInfos(BaseMaterial material, Value value) {
        if (material instanceof VideoMaterial videoMaterial) {
            value.setMetetype(Value.METE_TYPE_VIDEO);
            value.setWidth(videoMaterial.getWidth());
            value.setHeight(videoMaterial.getHeight());
            setValueTimes(value, videoMaterial.getTime());
        } else if (material instanceof AudioMaterial audioMaterial) {
            value.setMetetype(Value.METE_TYPE_MUSIC);
            setValueTimes(value, audioMaterial.getTime());
        } else if (material instanceof ImageMaterial imageMaterial) {
            value.setMetetype(Value.METE_TYPE_PHOTO);
            value.setWidth(imageMaterial.getWidth());
            value.setHeight(imageMaterial.getHeight());
        }
    }

    private static void setValueTimes(Value value, VideoTimeRange time) {
        long duration = time.getDuration() * JianyingUtils.LONG_1000;
        value.setDuration(duration);
        value.setRoughcutTimeRange(
                new TimeRange()
                        .setStart(time.getStart() * JianyingUtils.LONG_1000)
                        .setDuration(duration)
        );
        value.setDuration(duration);
    }

    private static void setMaterialMeta(BaseMaterial material, File file) {
        ExiftoolUtils.MediaExif exif = ExiftoolUtils.getMediaExif(file);
        if (exif == null) {
            logger.warn("无法解析元数据：{}", file.getAbsolutePath());
            return;
        }

        if (material instanceof BaseVisibleMediaMaterial visibleMediaMaterial) {
            visibleMediaMaterial.setWidth(exif.getWidth());
            visibleMediaMaterial.setHeight(exif.getHeight());
        }

        if (material instanceof VideoMaterial videoMaterial) {
            videoMaterial.setDuration(exif.getDuration());
            VideoTimeRange materialTime = videoMaterial.getTime();
            if (materialTime == null) {
                materialTime = new VideoTimeRange();
                videoMaterial.setTime(materialTime);
            }
            if (materialTime.getStart() == null) {
                materialTime.setStart(0L);
            }
            if (materialTime.getDuration() == null || materialTime.getDuration() <= 0) {
                materialTime.setDuration(exif.getDuration());
            }
        }
    }
}
