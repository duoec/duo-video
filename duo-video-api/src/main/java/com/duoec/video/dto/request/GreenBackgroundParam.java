package com.duoec.video.dto.request;

import lombok.Data;

import java.io.Serializable;

/**
 * 绿幕背景参数
 */
@Data
public class GreenBackgroundParam implements Serializable {
    /**
     * 绿幕资源ID
     */
    private Long greenScreenId;

    /**
     * 绿幕图片URL
     */
    private String greenScreenUrl;

    /**
     * 色度键颜色（如：#4e8a1fff）
     */
    private String chromaColor;

    /**
     * 色度键强度
     */
    private Integer chromaStrength;

    /**
     * 色度键阴影
     */
    private Integer chromaShadow;

    /**
     * 色度键高光
     */
    private Integer chromaHighlight;
}
