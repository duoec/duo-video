package com.duoec.video.jy.builder.ref;

import com.duoec.base.core.util.JsonUtils;
import com.duoec.video.jy.JianyingProjectBuildState;
import com.duoec.video.jy.builder.JianyingTrackBuilder;
import com.duoec.video.jy.dto.JyResource;
import com.duoec.video.jy.dto.TimeRange;
import com.duoec.video.jy.dto.info.*;
import com.duoec.video.jy.utils.JianyingResourceUtils;
import com.duoec.video.jy.utils.JianyingUtils;
import com.duoec.video.jy.utils.UuidUtils;
import com.duoec.video.project.material.MaskMaterial;

import java.util.Optional;

public class MaskRefBuilder extends BaseRefBuilder {
    public static void build(JianyingProjectBuildState state, Segment segment, MaskMaterial maskMaterial) {
        JyResource maskResource = JianyingResourceUtils.getJyResource(maskMaterial.getType(), maskMaterial.getResourceId());
        if (maskResource == null) {
            return;
        }

        // 复制资源文件到剪映本地素材库
        JianyingResourceUtils.downloadResources(state.getProjectLocalResourceDir(), maskResource.getResources());
        CommonMask mask = JsonUtils.toObject(maskResource.getMainConfig(), CommonMask.class)
                .setId(UuidUtils.next());

        MaskMaterial.MaskConfig maskConfig = maskMaterial.getConfig();
        mask.getConfig()
                .setCenterX(maskConfig.getCenterX())
                .setCenterY(maskConfig.getCenterY())
                .setWidth(maskConfig.getWidth())
                .setHeight(maskConfig.getHeight());

        JianYingProjectInfo jianyingProject = state.getJianyingProject();
        Materials materials = jianyingProject.getMaterials();
        materials.getMasks().add(mask); //兼容旧版本
        materials.getCommonMask().add(mask);

        CanvasConfig canvasConfig = jianyingProject.getCanvasConfig();
        segment.getExtraMaterialRefs().add(mask.getId());
        segment.getClip().setTransform(
                new Point()
                        .setX(1.0 * Optional.ofNullable(maskConfig.getPointX()).orElse(0) / canvasConfig.getWidth())
                        .setY(1.0 * Optional.ofNullable(maskConfig.getPointY()).orElse(0) / canvasConfig.getHeight())
        );

        // 移动到 蒙板
        Track oldTrack = jianyingProject.getTracks().stream().filter(track -> track.getSegments().contains(segment)).findFirst().orElse(null);
        if (oldTrack != null) {
            oldTrack.getSegments().remove(segment);

            TimeRange segmentTime = segment.getTargetTimeRange();
            long start = segmentTime.getStart() / JianyingUtils.LONG_1000;
            long end = start + segmentTime.getDuration() / JianyingUtils.LONG_1000;
            Track maskTrack = JianyingTrackBuilder.getOrCreateTrack(jianyingProject, Track.TYPE_VIDEO, "蒙板", start, end);
            maskTrack.getSegments().add(segment);
        }
    }
}
