package com.duoec.video.dto.request;

import com.duoec.video.project.material.BaseTextMaterial;
import lombok.Data;

import java.io.Serializable;

/**
 * 设置全局样式请求
 */
@Data
public class SetGlobalStyleRequest implements Serializable {
    /**
     * 项目ID
     */
    private Long projectId;

    /**
     * 样式ID
     */
    private Long styleId;

    /**
     * 文本样式
     */
    private BaseTextMaterial.TextStyle textStyle;

    /**
     * 是否设置为全局关键词样式
     */
    private Boolean globalKeywordStyle;
}
