package com.duoec.video.builder;

import com.duoec.video.project.material.BaseMaterial;
import com.duoec.video.project.material.MaterialTypeEnum;
import com.duoec.video.project.material.StyleMaterial;
import com.duoec.video.project.material.TextStyle;

import java.util.Optional;

public class ProjectTextStyleBuilder extends BaseMaterialBuilder<StyleMaterial> {
    private final TextStyle style;

    private final long styleId;

    private Boolean globalKeywordStyle = false;

    private ProjectTextStyleBuilder(ProjectBuilder projectBuilder, long styleId, TextStyle style) {
        this.projectBuilder = projectBuilder;
        this.styleId = styleId;
        this.style = Optional.ofNullable(style).orElse(new TextStyle());
        this.material = new StyleMaterial();
    }

    public static ProjectTextStyleBuilder addAndGetTextStyleBuilder(ProjectBuilder projectBuilder, long styleId, TextStyle style) {
        return new ProjectTextStyleBuilder(projectBuilder, styleId, style);
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
    public ProjectTextStyleBuilder setGlobalKeywordStyle(Boolean globalKeywordStyle) {
        this.globalKeywordStyle = globalKeywordStyle;
        return this;
    }

    /**
     * 设置字体大小
     */
    public ProjectTextStyleBuilder setFontSize(Integer fontSize) {
        this.style.setFontSize(fontSize);
        return this;
    }

    /**
     * 设置字体加粗
     */
    public ProjectTextStyleBuilder setBold(Boolean bold) {
        this.style.setBold(bold);
        return this;
    }

    /**
     * 设置斜体
     */
    public ProjectTextStyleBuilder setItalic(Boolean italic) {
        this.style.setItalic(italic);
        return this;
    }

    /**
     * 设置下划线
     */
    public ProjectTextStyleBuilder setUnderline(Boolean underline) {
        this.style.setUnderline(underline);
        return this;
    }

    /**
     * 设置文本对齐 0=左对齐 1=水平居中 2=右对齐 3=顶对齐 4=底对齐 5=水平分布 6=垂直分布
     */
    public ProjectTextStyleBuilder setTextAlign(Integer textAlign) {
        this.style.setTextAlign(textAlign);
        return this;
    }

    /**
     * 设置字体名称
     */
    public ProjectTextStyleBuilder setFontName(String fontName) {
        this.style.setFontName(fontName);
        return this;
    }

    /**
     * 设置填充颜色
     */
    public ProjectTextStyleBuilder setFillColor(String fillColor) {
        this.style.setFillColor(fillColor);
        return this;
    }

    /**
     * 设置行间距
     */
    public ProjectTextStyleBuilder setTextLineWidth(Integer textLineWidth) {
        this.style.setTextLineWidth(textLineWidth);
        return this;
    }

    /**
     * 设置字间距
     */
    public ProjectTextStyleBuilder setTextWordWidth(Integer textWordWidth) {
        this.style.setTextWordWidth(textWordWidth);
        return this;
    }

    /**
     * 设置描边颜色
     */
    public ProjectTextStyleBuilder setStrokeColor(String strokeColor) {
        this.style.setStrokeColor(strokeColor);
        return this;
    }

    /**
     * 设置描边宽度
     */
    public ProjectTextStyleBuilder setStrokeWidth(Integer strokeWidth) {
        this.style.setStrokeWidth(strokeWidth);
        return this;
    }

    /**
     * 设置背景颜色
     */
    public ProjectTextStyleBuilder setBackgroundColor(String backgroundColor) {
        this.style.setBackgroundColor(backgroundColor);
        return this;
    }

    /**
     * 设置背景透明度，100表示不透明，0表示全透明，取值范围[0, 100]
     */
    public ProjectTextStyleBuilder setBackgroundOpacity(Integer backgroundOpacity) {
        this.style.setBackgroundOpacity(backgroundOpacity);
        return this;
    }

    /**
     * 设置背景圆角，取值范围[0, 100]，单位：百分比
     */
    public ProjectTextStyleBuilder setBackgroundRadius(Integer backgroundRadius) {
        this.style.setBackgroundRadius(backgroundRadius);
        return this;
    }

    /**
     * 设置背景高度，取值范围[0, 100]，单位：百分比
     */
    public ProjectTextStyleBuilder setBackgroundHeight(Integer backgroundHeight) {
        this.style.setBackgroundHeight(backgroundHeight);
        return this;
    }

    /**
     * 设置背景宽度，取值范围[0, 100]，单位：百分比
     */
    public ProjectTextStyleBuilder setBackgroundWidth(Integer backgroundWidth) {
        this.style.setBackgroundWidth(backgroundWidth);
        return this;
    }

    /**
     * 设置左右偏移，取值范围[0, 100]，单位：百分比
     */
    public ProjectTextStyleBuilder setBackgroundOffsetX(Integer backgroundOffsetX) {
        this.style.setBackgroundOffsetX(backgroundOffsetX);
        return this;
    }

    /**
     * 设置上下偏移，取值范围[0, 100]，单位：百分比
     */
    public ProjectTextStyleBuilder setBackgroundOffsetY(Integer backgroundOffsetY) {
        this.style.setBackgroundOffsetY(backgroundOffsetY);
        return this;
    }

    /**
     * 设置发光颜色
     */
    public ProjectTextStyleBuilder setShineColor(String shineColor) {
        this.style.setShineColor(shineColor);
        return this;
    }

    /**
     * 设置发光强度，取值范围[10, 100]
     */
    public ProjectTextStyleBuilder setShineStrength(Integer shineStrength) {
        this.style.setShineStrength(shineStrength);
        return this;
    }

    /**
     * 设置发光范围，取值范围[10, 100]
     */
    public ProjectTextStyleBuilder setShineWidth(Integer shineWidth) {
        this.style.setShineWidth(shineWidth);
        return this;
    }

    /**
     * 设置发光水平角度，取值范围[-50, 50]
     */
    public ProjectTextStyleBuilder setShineAngleHorizon(Integer shineAngleHorizon) {
        this.style.setShineAngleHorizon(shineAngleHorizon);
        return this;
    }

    /**
     * 设置发光垂直角度，取值范围[-50, 50]
     */
    public ProjectTextStyleBuilder setShineAngleVertical(Integer shineAngleVertical) {
        this.style.setShineAngleVertical(shineAngleVertical);
        return this;
    }

    /**
     * 设置阴影颜色
     */
    public ProjectTextStyleBuilder setShadowColor(String shadowColor) {
        this.style.setShadowColor(shadowColor);
        return this;
    }

    /**
     * 设置阴影透明度，100表示不透明，0表示全透明，取值范围[0, 100]
     */
    public ProjectTextStyleBuilder setShadowOpacity(Integer shadowOpacity) {
        this.style.setShadowOpacity(shadowOpacity);
        return this;
    }

    /**
     * 设置阴影模糊度，100表示不透明，0表示全透明，取值范围[0, 100]
     */
    public ProjectTextStyleBuilder setShadowVague(Integer shadowVague) {
        this.style.setShadowVague(shadowVague);
        return this;
    }

    /**
     * 设置阴影距离，100表示不透明，0表示全透明，取值范围[0, 100]
     */
    public ProjectTextStyleBuilder setShadowWidth(Integer shadowWidth) {
        this.style.setShadowWidth(shadowWidth);
        return this;
    }

    /**
     * 设置阴影角度，取值范围[-180, 180]
     */
    public ProjectTextStyleBuilder setShadowAngle(Integer shadowAngle) {
        this.style.setShadowAngle(shadowAngle);
        return this;
    }

    /**
     * 设置弯曲程度，取值范围[-360, 360]
     */
    public ProjectTextStyleBuilder setBend(Integer bend) {
        this.style.setBend(bend);
        return this;
    }

    /**
     * 设置花字ID
     */
    public ProjectTextStyleBuilder setFlowerId(Long flowerId) {
        this.style.setFlowerId(flowerId);
        return this;
    }
}
