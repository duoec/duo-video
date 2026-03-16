package com.duoec.video.dto.request;

import com.duoec.video.project.material.BaseTextMaterial;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 添加文本请求
 */
@Data
public class AddTextRequest implements Serializable {
    /**
     * 项目ID
     */
    private Long projectId;

    /**
     * 分镜索引
     */
    private Integer scriptIndex;

    /**
     * 文本内容
     */
    private String text;

    /**
     * 开始时间（毫秒）
     */
    private Long startTime;

    /**
     * 持续时间（毫秒）
     */
    private Long duration;

    /**
     * 布局索引
     */
    private Integer layoutIndex;

    /**
     * X轴位置
     */
    private Integer positionX;

    /**
     * Y轴位置
     */
    private Integer positionY;

    /**
     * 旋转角度
     */
    private Integer rotate;

    /**
     * 是否作为字幕
     */
    private Boolean asSubtitle;

    /**
     * 文本样式
     */
    private BaseTextMaterial.TextStyle style;

    /**
     * 样式ID（使用全局样式）
     */
    private Long styleId;

    /**
     * 关键词样式列表
     */
    private List<WordStyleRequest> wordStyles;

    /**
     * 关键词样式
     */
    @Data
    public static class WordStyleRequest implements Serializable {
        /**
         * 关键词起始位置
         */
        private Integer startIndex;

        /**
         * 关键词长度
         */
        private Integer length;

        /**
         * 样式ID
         */
        private Long styleId;

        /**
         * 花字ID
         */
        private Long flowerId;

        /**
         * 字体大小
         */
        private Integer fontSize;

        /**
         * 填充颜色
         */
        private String fillColor;

        /**
         * 描边宽度
         */
        private Integer strokeWidth;

        /**
         * 描边颜色
         */
        private String strokeColor;
    }
}
