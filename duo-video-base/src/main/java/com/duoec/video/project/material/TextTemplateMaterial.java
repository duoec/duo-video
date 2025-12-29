package com.duoec.video.project.material;

import lombok.Data;

import java.util.List;

@Data
public class TextTemplateMaterial extends BaseMaterial {
    /**
     * 资源ID
     */
    private Long resourceId;

    /**
     * 文本内容（支持多块文本）
     */
    private List<String> texts;

    @Override
    public String getType() {
        return MaterialTypeEnum.MATERIAL_TYPE_TEXT_TEMPLATE;
    }
}
