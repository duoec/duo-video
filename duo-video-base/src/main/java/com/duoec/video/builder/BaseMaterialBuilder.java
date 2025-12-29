package com.duoec.video.builder;

import com.duoec.video.project.VideoPoint;
import com.duoec.video.project.VideoScript;
import com.duoec.video.project.VideoSegment;
import com.duoec.video.project.VideoTimeRange;
import com.duoec.video.project.material.BaseMaterial;

public class BaseMaterialBuilder<T extends BaseMaterial, E extends BaseMaterialBuilder> {
    protected VideoProjectBuilder videoProjectBuilder;
    protected VideoScript script;

    protected T material;
    protected VideoSegment segment;
    protected VideoTimeRange videoTime;
    protected VideoPoint videoPoint;
    protected int rotate;
    protected Long materialStart;

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
     * 返回到VideoProjectBuilder
     */
    public VideoProjectBuilder back() {
        segment.setTime(videoTime);
        segment.setPoint(videoPoint);
        segment.setRotate(rotate);
        if (materialStart != null) {
            segment.setMaterialStart(materialStart);
        }
        script.getSegments().add(segment);

        videoProjectBuilder.getProject().getMaterials().add(material);
        return videoProjectBuilder;
    }
}
