package com.duoec.video.project.material;

import lombok.Data;

import java.util.List;

@Data
public class TextMaterial extends BaseMaterial {
    /**
     * 文本
     */
    private String text;

    /**
     * 公共样式
     */
    private TextStyle style;

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
