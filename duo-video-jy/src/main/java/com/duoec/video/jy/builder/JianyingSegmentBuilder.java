package com.duoec.video.jy.builder;

import com.duoec.video.jy.JianyingProjectBuildState;
import com.duoec.video.jy.builder.segment.*;
import com.duoec.video.jy.dto.info.Clip;
import com.duoec.video.jy.dto.info.Flip;
import com.duoec.video.jy.dto.info.Point;
import com.duoec.video.jy.dto.info.Segment;
import com.duoec.video.jy.utils.JianyingUtils;
import com.duoec.video.project.VideoPoint;
import com.duoec.video.project.VideoProject;
import com.duoec.video.project.VideoScript;
import com.duoec.video.project.VideoSegment;
import com.duoec.video.project.material.BaseMaterial;
import com.duoec.video.project.material.BaseVisibleMediaMaterial;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class JianyingSegmentBuilder {
    private static final Logger logger = LoggerFactory.getLogger(JianyingSegmentBuilder.class);

    private static final Map<String, SegmentBuilder> SEGMENT_BUILDER_MAP = new HashMap<>();

    static {
        // 注册SegmentBuilder
        registrySegmentBuilder(VideoSegmentBuilder.class);
        registrySegmentBuilder(ImageSegmentBuilder.class);
        registrySegmentBuilder(TextSegmentBuilder.class);
        registrySegmentBuilder(AudioSegmentBuilder.class);
        registrySegmentBuilder(TextTemplateSegmentBuilder.class);
    }

    public static void build(JianyingProjectBuildState state, VideoScript videoScript, VideoSegment videoSegment) {
        var material = state.getMaterial(videoSegment.getMaterialId());
        if (material == null) {
            logger.warn("没有找到素材：materialId={}", videoSegment.getMaterialId());
            return;
        }

        SegmentBuilder builder = JianyingSegmentBuilder.getSegmentBuilder(material.getType());
        if (builder == null) {
            logger.warn("未实现的SegmentBuilder: {}", material.getType());
            return;
        }

        Segment segment = builder.build(state, videoScript, videoSegment, material);
        if (segment == null) {
            return;
        }

        setSegmentCommonAttributes(state, videoSegment, segment, material);
    }

    public static void setSegmentCommonAttributes(JianyingProjectBuildState state, VideoSegment videoSegment, Segment segment, BaseMaterial material) {
        VideoProject videoProject = state.getVideoProject();
        Clip segmentClip = segment.getClip();

        //透明
        double alpha = Optional.ofNullable(videoSegment.getOpacity()).orElse(100) / 100.0;
        segmentClip.setAlpha(alpha);

        //旋转
        double rotate = Optional.ofNullable(videoSegment.getRotate()).orElse(0);
        segmentClip.setRotation(rotate);

        //缩放
        VideoPoint zoomPoint = Optional.ofNullable(videoSegment.getZoom()).orElse(new VideoPoint());
        double zoomX = Optional.ofNullable(zoomPoint.getX()).orElse(10000);
        double zoomY = Optional.ofNullable(zoomPoint.getY()).orElse(10000);

        if (Math.abs(rotate % 180) == 90 && material instanceof BaseVisibleMediaMaterial visibleMediaMaterial) {
            // 旋转了，需要重新计算缩放值
            //计算放大倍数（最长边）
            int zoom = Math.max((int) (10000.0 * visibleMediaMaterial.getHeight() / visibleMediaMaterial.getWidth()), (int) (10000.0 * visibleMediaMaterial.getWidth() / visibleMediaMaterial.getHeight()));

            zoomX *= zoom / 10000.0;
            zoomY *= zoom / 10000.0;
        }
        segmentClip.setScale(new Point().setX(zoomX / 10000.0).setY(zoomY / 10000.0));

        VideoPoint point = videoSegment.getPoint();
        if (point != null) {
            segmentClip.setTransform(new Point().setX(1.0 * point.getX() / videoProject.getWidth()).setY(1.0 * point.getY() / videoProject.getHeight()));
        }

        boolean horizontal = Optional.ofNullable(videoSegment.getHorizontal()).orElse(false);
        boolean vertical = Optional.ofNullable(videoSegment.getVertical()).orElse(false);
        segmentClip.setFlip(
                new Flip()
                        .setVertical(vertical) // 垂直镜像
                        .setHorizontal(horizontal) // 水平镜像
        );

        // 增益音量
        int volume = Optional.ofNullable(videoSegment.getVolume()).orElse(100);
        segment.setVolume(JianyingUtils.amplitudeGain(volume / 100.0));

        // 处理refs
        Map<Long, String> refs = videoSegment.getRefs();
        if (!CollectionUtils.isEmpty(refs)) {

        }
    }

    private static void registrySegmentBuilder(Class<? extends SegmentBuilder> clazz) {
        SegmentBuilder builder = BeanUtils.instantiateClass(clazz);
        SEGMENT_BUILDER_MAP.put(builder.getMaterialType().getType(), builder);
    }

    private static SegmentBuilder getSegmentBuilder(String materialType) {
        return SEGMENT_BUILDER_MAP.get(materialType);
    }
}
