package com.duoec.video.project.material;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class StyleMaterial extends BaseMaterial {
    /**
     * 是否设置为全局关键词样式（确保全局只有一个为true，否则可能会错乱！）
     */
    private Boolean globalKeywordStyle;

    /**
     * 文本样式
     */
    private TextStyle style;

    @Override
    public String getType() {
        return MaterialTypeEnum.MATERIAL_TYPE_STYLE;
    }
}
