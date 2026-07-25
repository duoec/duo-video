package com.duoec.video.builder;

import com.duoec.video.project.VideoKeyframe;
import com.duoec.video.project.VideoPoint;
import com.duoec.video.project.VideoScript;
import com.duoec.video.project.VideoSegment;
import com.duoec.video.project.VideoTimeRange;
import com.duoec.video.project.material.BaseMaterial;

import java.util.List;

public class BaseSegmentBuilder<T extends BaseMaterial, E extends BaseMaterialBuilder> extends BaseMaterialBuilder<T> {
    protected ProjectScriptBuilder scriptBuilder;
    protected VideoScript script;

    protected VideoSegment segment;
    protected VideoTimeRange videoTime;
    protected VideoPoint videoPoint;
    protected int rotate;
    protected Long materialStart;
    protected Integer layoutIndex;
    protected Integer speed;
    protected Boolean upend;
    protected Boolean horizontal;
    protected Boolean vertical;
    protected Integer zoomX;
    protected Integer zoomY;
    protected Boolean visible;

    /**
     * 设置位置坐标
     * @param x 横坐标，中心为 0，左负右正
     * @param y 纵坐标，中心为 0，上正下负
     */
    public E setPosition(int x, int y) {
        videoPoint = new VideoPoint(x, y);
        return (E) this;
    }

    /**
     * 设置素材起始时间，仅在视频、音频时有效
     * @param materialStart 视频起始时间，即从这个时间开始播放，单位：毫秒
     */
    public E setMaterialStart(long materialStart) {
        this.materialStart = materialStart;
        return (E) this;
    }

    /**
     * 设置旋转角度
     * @param rotate 旋转角度
     */
    public E setRotate(int rotate) {
        this.rotate = rotate;
        return (E) this;
    }

    /**
     * 设置水平镜像，null 或 false 表示不镜像
     * @param vertical true=水平镜像
     */
    public E setVertical(Boolean vertical) {
        this.vertical = vertical;
        return (E) this;
    }

    /**
     * 设置垂直镜像，null 或 false 表示不镜像
     * @param horizontal true=垂直镜像
     */
    public E setHorizontal(Boolean horizontal) {
        this.horizontal = horizontal;
        return (E) this;
    }

    /**
     * 设置展示层级
     * @param layoutIndex 层级，越大越前
     */
    public E setLayoutIndex(Integer layoutIndex) {
        this.layoutIndex = layoutIndex;
        return (E) this;
    }

    /**
     * 设置播放速度
     * @param speed 速度，单位：百分之一。100 表示正常 1 倍速
     */
    public E setSpeed(Integer speed) {
        this.speed = speed;
        return (E) this;
    }

    /**
     * 返回到 ProjectScriptBuilder
     */
    public ProjectScriptBuilder back() {
        segment.setTime(videoTime);
        segment.setPoint(videoPoint);
        segment.setRotate(rotate);
        if (layoutIndex != null) {
            segment.setLayoutIndex(layoutIndex);
        }
        if (materialStart != null) {
            segment.setMaterialStart(materialStart);
        }
        if (speed != null) {
            segment.setSpeed(speed);
        }
        if (upend != null) {
            segment.setUpend(upend);
        }
        if (horizontal != null) {
            segment.setHorizontal(horizontal);
        }
        if (vertical != null) {
            segment.setVertical(vertical);
        }
        if (zoomX != null && zoomY != null) {
            segment.setZoom(new VideoPoint(zoomX, zoomY));
        }
        script.getSegments().add(segment);

        beforeBack();

        addMaterial(material);
        return scriptBuilder;
    }

    public E setZoom(int zoomX, int zoomY) {
        this.zoomX = zoomX;
        this.zoomY = zoomY;
        return (E) this;
    }

    public E setVisible(boolean visible) {
        this.visible = visible;
        return (E) this;
    }

    /**
     * 设置关键帧
     * @param keyframes 关键帧列表
     */
    public E setKeyframes(List<VideoKeyframe> keyframes) {
        this.segment.setKeyframes(keyframes);
        return (E) this;
    }

    /**
     * 获取关键帧 Builder，用于链式添加关键帧
     * @return KeyframeBuilder
     */
    public KeyframeBuilder getKeyframeBuilder() {
        return new KeyframeBuilder(segment);
    }

    /**
     * 添加位置关键帧
     * @param timeOffset 时间偏移（毫秒）
     * @param x X 轴位置
     * @param y Y 轴位置
     */
    public E addPositionKeyframe(Integer timeOffset, Double x, Double y) {
        getKeyframeBuilder().addPositionKeyframe(timeOffset, x, y).apply();
        return (E) this;
    }

    /**
     * 添加缩放关键帧
     * @param timeOffset 时间偏移（毫秒）
     * @param scaleX X 轴缩放（万分比）
     * @param scaleY Y 轴缩放（万分比）
     */
    public E addScaleKeyframe(Integer timeOffset, Double scaleX, Double scaleY) {
        getKeyframeBuilder().addScaleKeyframe(timeOffset, scaleX, scaleY).apply();
        return (E) this;
    }

    /**
     * 添加旋转关键帧
     * @param timeOffset 时间偏移（毫秒）
     * @param rotation 旋转角度
     */
    public E addRotationKeyframe(Integer timeOffset, Double rotation) {
        getKeyframeBuilder().addRotationKeyframe(timeOffset, rotation).apply();
        return (E) this;
    }

    /**
     * 添加透明度关键帧
     * @param timeOffset 时间偏移（毫秒）
     * @param opacity 透明度（0-100）
     */
    public E addOpacityKeyframe(Integer timeOffset, Double opacity) {
        getKeyframeBuilder().addOpacityKeyframe(timeOffset, opacity).apply();
        return (E) this;
    }

    /**
     * 添加文字颜色关键帧
     * @param timeOffset 时间偏移（毫秒）
     * @param hexColor 十六进制颜色，如 "#FF0000"
     */
    public E addTextColorKeyframe(Integer timeOffset, String hexColor) {
        getKeyframeBuilder().addTextColorKeyframe(timeOffset, hexColor).apply();
        return (E) this;
    }
}
