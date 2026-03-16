package com.duoec.video.project.material;

import lombok.Data;

import java.util.List;

@Data
public class TextMaterial extends BaseTextMaterial {
    /**
     * 文本
     */
    private String text;

    /**
     * 公共样式
     */
    private TextStyle style;

    /**
     * 预设样式ID。[type='style', id=${styleId}]
     * 如果同时设置 styleId 和 style时，会优先加载 styleId 的样式，再使用 style 覆盖！
     */
    private Long styleId;

    /**
     * 特殊设置文本样式
     */
    private List<TextWord> words;

    /**
     * 文本类型：subtitle=字幕 其它为普通文本
     */
    private String textType;

    @Override
    public String getType() {
        return MaterialTypeEnum.MATERIAL_TYPE_TEXT;
    }
}
