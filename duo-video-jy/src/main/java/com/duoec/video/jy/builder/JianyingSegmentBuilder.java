package com.duoec.video.jy.builder;

import com.duoec.video.jy.JianyingProjectBuildState;
import com.duoec.video.jy.builder.segment.*;
import com.duoec.video.jy.dto.info.Clip;
import com.duoec.video.jy.dto.info.Flip;
import com.duoec.video.jy.dto.info.Keyframe;
import com.duoec.video.jy.dto.info.KeyframeItem;
import com.duoec.video.jy.dto.info.Point;
import com.duoec.video.jy.dto.info.Segment;
import com.duoec.video.jy.utils.JianyingUtils;
import com.duoec.video.jy.utils.UuidUtils;
import com.duoec.video.project.VideoKeyframe;
import com.duoec.video.project.VideoKeyframeItem;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class JianyingSegmentBuilder {
    private static final Logger logger = LoggerFactory.getLogger(JianyingSegmentBuilder.class);

    private static final Map<String, SegmentBuilder> SEGMENT_BUILDER_MAP = new HashMap<>();

    static {
        // 注册 SegmentBuilder
        registrySegmentBuilder(VideoSegmentBuilder.class);
        registrySegmentBuilder(ImageSegmentBuilder.class);
        registrySegmentBuilder(TextSegmentBuilder.class);
        registrySegmentBuilder(AudioSegmentBuilder.class);
        registrySegmentBuilder(TextTemplateSegmentBuilder.class);
        registrySegmentBuilder(SoundSegmentBuilder.class);
        registrySegmentBuilder(StickerSegmentBuilder.class);
        registrySegmentBuilder(VideoEffectSegmentBuilder.class);
        registrySegmentBuilder(FaceEffectSegmentBuilder.class);
    }

    public static void build(JianyingProjectBuildState state, VideoScript videoScript, VideoSegment videoSegment) {
        var material = state.getMaterial(videoSegment.getMaterialId());
        if (material == null) {
            logger.warn("没有找到素材：materialId={}", videoSegment.getMaterialId());
            return;
        }

        SegmentBuilder builder = JianyingSegmentBuilder.getSegmentBuilder(material.getType());
        if (builder == null) {
            logger.warn("未实现的 SegmentBuilder: {}", material.getType());
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

        // 处理关键帧
        buildKeyframes(state, videoSegment, segment, material);
    }

    /**
     * 构建关键帧数据
     * 将基础模型的 VideoKeyframe 转换为剪映的 common_keyframes 格式
     * <p>
     * 剪映关键帧格式要点：
     * - material_id 为空字符串
     * - time_offset 单位为微秒
     * - 位置关键帧拆分为 KFTypePositionX 和 KFTypePositionY 两个独立轨道
     * - 位置值为归一化坐标（像素 / 画布尺寸）
     * - 控制点默认为 (0, 0)
     */
    private static void buildKeyframes(JianyingProjectBuildState state, VideoSegment videoSegment, Segment segment, BaseMaterial material) {
        List<VideoKeyframe> keyframes = videoSegment.getKeyframes();
        if (CollectionUtils.isEmpty(keyframes)) {
            return;
        }

        VideoProject videoProject = state.getVideoProject();
        int canvasWidth = videoProject.getWidth();
        int canvasHeight = videoProject.getHeight();

        List<Keyframe> jianyingKeyframes = new ArrayList<>();
        for (VideoKeyframe videoKeyframe : keyframes) {
            String propertyType = videoKeyframe.getPropertyType();
            List<VideoKeyframeItem> items = videoKeyframe.getKeyframeList();
            if (items == null || items.isEmpty()) {
                continue;
            }

            if ("position".equals(propertyType)) {
                // 位置关键帧：拆分为 X 和 Y 两个独立轨道
                jianyingKeyframes.add(buildPositionKeyframe(videoKeyframe, items, canvasWidth, true));
                jianyingKeyframes.add(buildPositionKeyframe(videoKeyframe, items, canvasHeight, false));
            } else if ("scale".equals(propertyType)) {
                // 缩放关键帧：拆分为 X 和 Y 两个独立轨道，万分比转小数
                jianyingKeyframes.add(buildScaleKeyframe(videoKeyframe, items, true));
                jianyingKeyframes.add(buildScaleKeyframe(videoKeyframe, items, false));
            } else {
                // 其它类型直接映射
                String jyPropertyType = mapPropertyType(propertyType);
                Keyframe keyframe = new Keyframe()
                        .setId(UuidUtils.next())
                        .setMaterialId("")
                        .setPropertyType(jyPropertyType)
                        .setKeyframeList(buildKeyframeItems(items, null, null));
                jianyingKeyframes.add(keyframe);
            }
        }

        if (!jianyingKeyframes.isEmpty()) {
            segment.setCommonKeyframes(jianyingKeyframes);
        }
    }

    /**
     * 构建位置关键帧轨道（X 或 Y）
     *
     * @param isX true 表示 X 轴，false 表示 Y 轴
     */
    private static Keyframe buildPositionKeyframe(VideoKeyframe videoKeyframe, List<VideoKeyframeItem> items, int canvasSize, boolean isX) {
        String jyPropertyType = isX ? "KFTypePositionX" : "KFTypePositionY";
        List<KeyframeItem> keyframeItems = new ArrayList<>();
        for (VideoKeyframeItem item : items) {
            List<Double> values = item.getValues();
            if (values == null || values.size() < 2) {
                continue;
            }
            // 取 X 或 Y 值，像素转归一化坐标
            double pixelValue = isX ? values.get(0) : values.get(1);
            double normalizedValue = pixelValue / canvasSize;

            keyframeItems.add(buildKeyframeItem(item, List.of(normalizedValue)));
        }
        return new Keyframe()
                .setId(UuidUtils.next())
                .setMaterialId("")
                .setPropertyType(jyPropertyType)
                .setKeyframeList(keyframeItems);
    }

    /**
     * 构建缩放关键帧轨道（X 或 Y）
     *
     * @param isX true 表示 X 轴，false 表示 Y 轴
     */
    private static Keyframe buildScaleKeyframe(VideoKeyframe videoKeyframe, List<VideoKeyframeItem> items, boolean isX) {
        String jyPropertyType = isX ? "KFTypeScaleX" : "KFTypeScaleY";
        List<KeyframeItem> keyframeItems = new ArrayList<>();
        for (VideoKeyframeItem item : items) {
            List<Double> values = item.getValues();
            if (values == null || values.size() < 2) {
                continue;
            }
            // 取 X 或 Y 值，万分比转小数
            double rawValue = isX ? values.get(0) : values.get(1);
            double scaleValue = rawValue / 10000.0;

            keyframeItems.add(buildKeyframeItem(item, List.of(scaleValue)));
        }
        return new Keyframe()
                .setId(UuidUtils.next())
                .setMaterialId("")
                .setPropertyType(jyPropertyType)
                .setKeyframeList(keyframeItems);
    }

    /**
     * 构建单个关键帧项
     */
    private static KeyframeItem buildKeyframeItem(VideoKeyframeItem videoItem, List<Double> values) {
        KeyframeItem item = new KeyframeItem()
                .setId(Optional.ofNullable(videoItem.getId()).orElse(UuidUtils.next()))
                // 毫秒转微秒
                .setTimeOffset(videoItem.getTimeOffset() * JianyingUtils.LONG_1000)
                .setValues(values)
                // 控制点默认 (0, 0)
                .setLeftControl(new Point(0.0, 0.0))
                .setRightControl(new Point(0.0, 0.0));

        // 仅在显式指定时设置曲线类型
        if (videoItem.getCurveType() != null) {
            item.setCurveType(videoItem.getCurveType());
        }
        if (videoItem.getGraphId() != null) {
            item.setGraphID(videoItem.getGraphId());
        }
        // 如果提供了自定义控制点，覆盖默认值
        if (videoItem.getLeftControl() != null) {
            item.setLeftControl(new Point(videoItem.getLeftControl().getX(), videoItem.getLeftControl().getY()));
        }
        if (videoItem.getRightControl() != null) {
            item.setRightControl(new Point(videoItem.getRightControl().getX(), videoItem.getRightControl().getY()));
        }
        return item;
    }

    /**
     * 批量构建关键帧项（用于不需要拆分的属性类型）
     */
    private static List<KeyframeItem> buildKeyframeItems(List<VideoKeyframeItem> items, Integer canvasWidth, Integer canvasHeight) {
        List<KeyframeItem> keyframeItems = new ArrayList<>();
        for (VideoKeyframeItem item : items) {
            keyframeItems.add(buildKeyframeItem(item, item.getValues()));
        }
        return keyframeItems;
    }

    /**
     * 属性类型映射：基础模型 → 剪映 KFType
     */
    private static String mapPropertyType(String propertyType) {
        if (propertyType.startsWith("KFType")) {
            return propertyType;
        }
        return switch (propertyType) {
            case "rotation" -> "KFTypeRotationZ";
            case "opacity" -> "KFTypeAlpha";
            case "volume" -> "KFTypeVolume";
            case "textColor" -> "KFTypeTextColor";
            default -> propertyType;
        };
    }

    private static void registrySegmentBuilder(Class<? extends SegmentBuilder> clazz) {
        SegmentBuilder builder = BeanUtils.instantiateClass(clazz);
        SEGMENT_BUILDER_MAP.put(builder.getMaterialType().getType(), builder);
    }

    private static SegmentBuilder getSegmentBuilder(String materialType) {
        return SEGMENT_BUILDER_MAP.get(materialType);
    }
}
