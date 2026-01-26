package com.duoec.video.dto.request;

import lombok.Data;

import java.io.Serializable;

/**
 * 蒙版参数
 */
@Data
public class MaskParam implements Serializable {
    /**
     * 蒙版ID
     */
    private Long maskId;

    /**
     * 羽化值
     */
    private Integer feather;

    /**
     * 旋转角度
     */
    private Integer rotation;

    /**
     * 宽度（0-1之间的小数）
     */
    private Double width;

    /**
     * 高度（0-1之间的小数）
     */
    private Double height;

    /**
     * 中心点X（0-1之间的小数）
     */
    private Double centerX;

    /**
     * 中心点Y（0-1之间的小数）
     */
    private Double centerY;

    /**
     * 点X坐标
     */
    private Integer pointX;

    /**
     * 点Y坐标
     */
    private Integer pointY;
}
