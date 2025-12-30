package com.duoec.video.builder;

import com.duoec.video.project.VideoPoint;
import com.duoec.video.project.VideoScript;
import com.duoec.video.project.VideoSegment;
import com.duoec.video.project.VideoTimeRange;
import com.duoec.video.project.material.BaseMaterial;

public class BaseMaterialBuilder<T extends BaseMaterial, E extends BaseMaterialBuilder> {
    protected ProjectBuilder projectBuilder;
    protected ProjectScriptBuilder scriptBuilder;
    protected VideoScript script;

    protected T material;
    protected VideoSegment segment;
    protected VideoTimeRange videoTime;
    protected VideoPoint videoPoint;
    protected int rotate;
    protected Long materialStart;
    protected Integer layoutIndex;
    protected Integer speed;

    /**
     * 设置位置坐标
     * @param x 横坐标，中心为0，左负右正
     * @param y 纵坐标，中心为0，上正下负
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
     * 设置展示层级
     * @param layoutIndex 层级，越大越前
     */
    public E setLayoutIndex(Integer layoutIndex) {
        this.layoutIndex = layoutIndex;
        return (E) this;
    }

    /**
     * 设置播放速度
     * @param speed 速度，单位：百分之一。100表示正常1倍速
     */
    public E setSpeed(Integer speed) {
        this.speed = speed;
        return (E) this;
    }

    /**
     * 返回到ProjectScriptBuilder
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
        script.getSegments().add(segment);

        projectBuilder.getProject().getMaterials().add(material);
        return scriptBuilder;
    }
}
