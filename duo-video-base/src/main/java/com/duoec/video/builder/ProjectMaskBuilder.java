package com.duoec.video.builder;

import com.duoec.base.core.util.SnowflakeIdUtils;
import com.duoec.video.project.material.MaskMaterial;

/**
 * 蒙板项目构建器
 */
public class ProjectMaskBuilder extends BaseMaterialBuilder<MaskMaterial> {
    private ProjectMaskBuilder(ProjectBuilder projectBuilder, long maskResourceId) {
        this.projectBuilder = projectBuilder;
        this.material = new MaskMaterial();
        material.setId(SnowflakeIdUtils.nextTmpId());
        material.setResourceId(maskResourceId);
        material.setConfig(new MaskMaterial.MaskConfig());
    }

    public static ProjectMaskBuilder getBuilder(ProjectBuilder projectBuilder, long maskResourceId) {
        return new ProjectMaskBuilder(projectBuilder, maskResourceId);
    }

    /**
     * 设置蒙板扩展值
     *
     * @param expansion 扩展值
     * @return 当前构建器实例
     */
    public ProjectMaskBuilder setExpansion(Integer expansion) {
        material.getConfig().setExpansion(expansion);
        return this;
    }

    /**
     * 设置蒙板羽化值
     *
     * @param feather 羽化值
     * @return 当前构建器实例
     */
    public ProjectMaskBuilder setFeather(Integer feather) {
        material.getConfig().setFeather(feather);
        return this;
    }

    /**
     * 设置蒙板圆角值
     *
     * @param roundCorner 圆角值
     * @return 当前构建器实例
     */
    public ProjectMaskBuilder setRoundCorner(Integer roundCorner) {
        material.getConfig().setRoundCorner(roundCorner);
        return this;
    }

    /**
     * 设置蒙板旋转角度
     *
     * @param rotation 旋转角度
     * @return 当前构建器实例
     */
    public ProjectMaskBuilder setRotation(Integer rotation) {
        material.getConfig().setRotation(rotation);
        return this;
    }

    /**
     * 设置蒙板宽高比
     *
     * @param aspectRatio 宽高比
     * @return 当前构建器实例
     */
    public ProjectMaskBuilder setAspectRatio(Integer aspectRatio) {
        material.getConfig().setAspectRatio(aspectRatio);
        return this;
    }

    /**
     * 设置蒙板是否反转
     *
     * @param invert 是否反转（1为是，0为否）
     * @return 当前构建器实例
     */
    public ProjectMaskBuilder setInvert(Integer invert) {
        material.getConfig().setInvert(invert);
        return this;
    }

    /**
     * 设置蒙板宽度比例（与视频宽的比值）
     *
     * @param width 宽度比例
     * @return 当前构建器实例
     */
    public ProjectMaskBuilder setWidth(Double width) {
        material.getConfig().setWidth(width);
        return this;
    }

    /**
     * 设置蒙板高度比例（与视频高的比值）
     *
     * @param height 高度比例
     * @return 当前构建器实例
     */
    public ProjectMaskBuilder setHeight(Double height) {
        material.getConfig().setHeight(height);
        return this;
    }

    /**
     * 设置蒙板中心位置X轴（与视频宽的比值）
     *
     * @param centerX 中心位置X轴坐标
     * @return 当前构建器实例
     */
    public ProjectMaskBuilder setCenterX(Double centerX) {
        material.getConfig().setCenterX(centerX);
        return this;
    }

    /**
     * 设置蒙板中心位置Y轴（与视频高的比值）
     *
     * @param centerY 中心位置Y轴坐标
     * @return 当前构建器实例
     */
    public ProjectMaskBuilder setCenterY(Double centerY) {
        material.getConfig().setCenterY(centerY);
        return this;
    }

    /**
     * 设置蒙板视频的位移X轴，与剪映界面值一致
     *
     * @param pointX X轴位移
     * @return 当前构建器实例
     */
    public ProjectMaskBuilder setPointX(Integer pointX) {
        material.getConfig().setPointX(pointX);
        return this;
    }

    /**
     * 设置蒙板视频的位移Y轴，与剪映界面值一致
     *
     * @param pointY Y轴位移
     * @return 当前构建器实例
     */
    public ProjectMaskBuilder setPointY(Integer pointY) {
        material.getConfig().setPointY(pointY);
        return this;
    }

    public MaskMaterial build() {
        return this.material;
    }
}
