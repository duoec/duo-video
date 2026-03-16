package com.duoec.video.builder;

import com.duoec.base.exceptions.DuoServiceException;
import com.duoec.video.project.material.BaseMaterial;
import com.duoec.video.project.material.BaseTextMaterial;
import com.duoec.video.project.material.MaterialTypeEnum;
import com.duoec.video.project.material.StyleMaterial;

import java.util.Optional;
import java.util.function.Consumer;

public class ProjectGlobalTextStyleBuilder extends BaseMaterialBuilder<StyleMaterial> {
    private final BaseTextMaterial.TextStyle style;

    private final long styleId;

    private Boolean globalKeywordStyle = false;

    private ProjectGlobalTextStyleBuilder(ProjectBuilder projectBuilder, long styleId, BaseTextMaterial.TextStyle style) {
        if (style == null) {
            throw new DuoServiceException("样式不能为null！");
        }
        this.projectBuilder = projectBuilder;
        this.styleId = styleId;
        this.style = style;
        this.material = new StyleMaterial();
    }

    public static ProjectGlobalTextStyleBuilder addAndGetBuilder(ProjectBuilder projectBuilder, long styleId, BaseTextMaterial.TextStyle style) {
        return new ProjectGlobalTextStyleBuilder(projectBuilder, styleId, style);
    }

    public ProjectGlobalTextStyleBuilder getGlobalStyleBuilder(Consumer<ProjectTextStyleBuilder<BaseTextMaterial.TextStyle>> textStyleBuilderConsumer) {
        ProjectTextStyleBuilder<BaseTextMaterial.TextStyle> textStyleBuilder = ProjectTextStyleBuilder.build(style);
        if (textStyleBuilderConsumer != null) {
            textStyleBuilderConsumer.accept(textStyleBuilder);
        }
        return this;
    }

    public ProjectBuilder back() {
        BaseMaterial existsMaterial = getMaterialById(styleId);
        if (existsMaterial == null || !MaterialTypeEnum.MATERIAL_TYPE_STYLE.equals(existsMaterial.getType())) {
            // 不存在
            material.setId(styleId);
            material.setStyle(style);
            material.setGlobalKeywordStyle(Optional.ofNullable(globalKeywordStyle).orElse(false));
            addMaterial(material);
        }
        beforeBack();
        return projectBuilder;
    }

    /**
     * 设置是否是全局 关键词默认样式
     */
    public ProjectGlobalTextStyleBuilder setGlobalKeywordStyle(Boolean globalKeywordStyle) {
        this.globalKeywordStyle = globalKeywordStyle;
        return this;
    }

}
