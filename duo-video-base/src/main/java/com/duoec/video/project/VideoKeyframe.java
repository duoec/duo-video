package com.duoec.video.project;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 视频关键帧
 * 用于定义属性随时间变化的动画效果
 */
@Data
@Accessors(chain = true)
public class VideoKeyframe implements Serializable {

    /**
     * 关键帧 ID（可选，为空时自动生成）
     */
    private String id;

    /**
     * 关联的素材 ID
     */
    private String materialId;

    /**
     * 关键帧列表
     */
    @JsonProperty("items")
    private List<VideoKeyframeItem> keyframeList;

    /**
     * 属性类型
     * 常见值：
     * - "position": 位置（像素值，构建时拆分为 KFTypePositionX / KFTypePositionY）
     * - "scale": 缩放（万分比，构建时拆分为 KFTypeScaleX / KFTypeScaleY）
     * - "rotation": 旋转（角度，映射为 KFTypeRotationZ）
     * - "opacity": 透明度（0-100，映射为 KFTypeAlpha）
     * - "volume": 音量（映射为 KFTypeVolume）
     * - "textColor": 文字颜色（RGBA 0-1，映射为 KFTypeTextColor）
     * 也可直接使用剪映原生类型如 "KFTypePositionX"
     */
    @JsonProperty("propertyType")
    private String propertyType;

    public VideoKeyframe() {
    }

    public VideoKeyframe(String propertyType) {
        this.propertyType = propertyType;
    }
}
