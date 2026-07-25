package com.duoec.video.builder;

import com.duoec.video.project.KeyframeControlPoint;
import com.duoec.video.project.VideoKeyframe;
import com.duoec.video.project.VideoKeyframeItem;
import com.duoec.video.project.VideoSegment;

import java.util.ArrayList;
import java.util.List;

/**
 * 关键帧 Builder
 * 用于构建视频片段的关键帧动画
 */
public class KeyframeBuilder {

    private VideoSegment segment;
    private List<VideoKeyframe> keyframes;
    private VideoKeyframe currentKeyframe;

    public KeyframeBuilder(VideoSegment segment) {
        this.segment = segment;
        this.keyframes = new ArrayList<>();
    }

    /**
     * 创建位置关键帧
     * @param timeOffset 时间偏移（毫秒）
     * @param x X 轴位置
     * @param y Y 轴位置
     */
    public KeyframeBuilder addPositionKeyframe(Integer timeOffset, Double x, Double y) {
        return addKeyframe("position", VideoKeyframeItem.createPositionKeyframe(timeOffset, x, y));
    }

    /**
     * 创建缩放关键帧
     * @param timeOffset 时间偏移（毫秒）
     * @param scaleX X 轴缩放（万分比）
     * @param scaleY Y 轴缩放（万分比）
     */
    public KeyframeBuilder addScaleKeyframe(Integer timeOffset, Double scaleX, Double scaleY) {
        return addKeyframe("scale", VideoKeyframeItem.createScaleKeyframe(timeOffset, scaleX, scaleY));
    }

    /**
     * 创建旋转关键帧
     * @param timeOffset 时间偏移（毫秒）
     * @param rotation 旋转角度
     */
    public KeyframeBuilder addRotationKeyframe(Integer timeOffset, Double rotation) {
        return addKeyframe("rotation", VideoKeyframeItem.createRotationKeyframe(timeOffset, rotation));
    }

    /**
     * 创建透明度关键帧
     * @param timeOffset 时间偏移（毫秒）
     * @param opacity 透明度（0-100）
     */
    public KeyframeBuilder addOpacityKeyframe(Integer timeOffset, Double opacity) {
        return addKeyframe("opacity", VideoKeyframeItem.createOpacityKeyframe(timeOffset, opacity));
    }

    /**
     * 创建音量关键帧
     * @param timeOffset 时间偏移（毫秒）
     * @param volume 音量
     */
    public KeyframeBuilder addVolumeKeyframe(Integer timeOffset, Double volume) {
        return addKeyframe("volume", VideoKeyframeItem.createVolumeKeyframe(timeOffset, volume));
    }

    /**
     * 创建文字颜色关键帧
     * @param timeOffset 时间偏移（毫秒）
     * @param hexColor 十六进制颜色，如 "#FF0000"
     */
    public KeyframeBuilder addTextColorKeyframe(Integer timeOffset, String hexColor) {
        return addKeyframe("textColor", VideoKeyframeItem.createTextColorKeyframe(timeOffset, hexColor));
    }

    /**
     * 创建文字颜色关键帧
     * @param timeOffset 时间偏移（毫秒）
     * @param r 红色 (0-1)
     * @param g 绿色 (0-1)
     * @param b 蓝色 (0-1)
     * @param a 透明度 (0-1)
     */
    public KeyframeBuilder addTextColorKeyframe(Integer timeOffset, Double r, Double g, Double b, Double a) {
        return addKeyframe("textColor", VideoKeyframeItem.createTextColorKeyframe(timeOffset, r, g, b, a));
    }

    /**
     * 添加关键帧到指定属性类型
     * @param propertyType 属性类型
     * @param keyframeItem 关键帧项
     */
    public KeyframeBuilder addKeyframe(String propertyType, VideoKeyframeItem keyframeItem) {
        // 查找是否已存在相同属性类型的关键帧
        VideoKeyframe keyframe = findKeyframeByPropertyType(propertyType);
        if (keyframe == null) {
            keyframe = new VideoKeyframe(propertyType);
            keyframe.setKeyframeList(new ArrayList<>());
            keyframes.add(keyframe);
        }

        keyframe.getKeyframeList().add(keyframeItem);
        return this;
    }

    /**
     * 设置关键帧曲线类型
     * @param curveType 曲线类型（linear, bezier, ease_in, ease_out, ease_in_out）
     */
    public KeyframeBuilder withCurveType(String curveType) {
        if (currentKeyframe != null && !currentKeyframe.getKeyframeList().isEmpty()) {
            VideoKeyframeItem lastItem = currentKeyframe.getKeyframeList().get(
                    currentKeyframe.getKeyframeList().size() - 1
            );
            lastItem.setCurveType(curveType);
        }
        return this;
    }

    /**
     * 设置贝塞尔曲线控制点
     * @param leftX 左侧控制点 X
     * @param leftY 左侧控制点 Y
     * @param rightX 右侧控制点 X
     * @param rightY 右侧控制点 Y
     */
    public KeyframeBuilder withBezierControlPoints(Double leftX, Double leftY, Double rightX, Double rightY) {
        if (currentKeyframe != null && !currentKeyframe.getKeyframeList().isEmpty()) {
            VideoKeyframeItem lastItem = currentKeyframe.getKeyframeList().get(
                    currentKeyframe.getKeyframeList().size() - 1
            );
            lastItem.setLeftControl(new KeyframeControlPoint(leftX, leftY));
            lastItem.setRightControl(new KeyframeControlPoint(rightX, rightY));
        }
        return this;
    }

    /**
     * 应用关键帧到 Segment
     */
    public void apply() {
        if (!keyframes.isEmpty()) {
            segment.setKeyframes(keyframes);
        }
    }

    /**
     * 查找指定属性类型的关键帧
     */
    private VideoKeyframe findKeyframeByPropertyType(String propertyType) {
        for (VideoKeyframe keyframe : keyframes) {
            if (propertyType.equals(keyframe.getPropertyType())) {
                currentKeyframe = keyframe;
                return keyframe;
            }
        }
        return null;
    }

    /**
     * 创建位置关键帧动画（简化方法）
     * @param timeOffset 时间偏移（毫秒）
     * @param x X 轴位置
     * @param y Y 轴位置
     * @param curveType 曲线类型
     */
    public KeyframeBuilder position(Integer timeOffset, Double x, Double y, String curveType) {
        VideoKeyframeItem item = VideoKeyframeItem.createPositionKeyframe(timeOffset, x, y);
        item.setCurveType(curveType);
        return addKeyframe("position", item);
    }

    /**
     * 创建缩放关键帧动画（简化方法）
     * @param timeOffset 时间偏移（毫秒）
     * @param scale 缩放比例（万分比）
     * @param curveType 曲线类型
     */
    public KeyframeBuilder scale(Integer timeOffset, Double scale, String curveType) {
        return addKeyframe("scale", VideoKeyframeItem.createScaleKeyframe(timeOffset, scale, scale)
                .setCurveType(curveType));
    }

    /**
     * 创建旋转关键帧动画（简化方法）
     * @param timeOffset 时间偏移（毫秒）
     * @param rotation 旋转角度
     * @param curveType 曲线类型
     */
    public KeyframeBuilder rotation(Integer timeOffset, Double rotation, String curveType) {
        return addKeyframe("rotation", VideoKeyframeItem.createRotationKeyframe(timeOffset, rotation)
                .setCurveType(curveType));
    }

    /**
     * 创建透明度关键帧动画（简化方法）
     * @param timeOffset 时间偏移（毫秒）
     * @param opacity 透明度（0-100）
     * @param curveType 曲线类型
     */
    public KeyframeBuilder opacity(Integer timeOffset, Double opacity, String curveType) {
        return addKeyframe("opacity", VideoKeyframeItem.createOpacityKeyframe(timeOffset, opacity)
                .setCurveType(curveType));
    }
}
