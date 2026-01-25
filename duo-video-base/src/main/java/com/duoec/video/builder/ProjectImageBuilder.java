package com.duoec.video.builder;

import com.duoec.base.core.util.SnowflakeIdUtils;
import com.duoec.base.exceptions.DuoServiceException;
import com.duoec.video.project.VideoPoint;
import com.duoec.video.project.VideoSegment;
import com.duoec.video.project.VideoTimeRange;
import com.duoec.video.project.material.*;
import org.springframework.util.StringUtils;

public class ProjectImageBuilder extends BaseSegmentBuilder<ImageMaterial, ProjectImageBuilder> {
    private BaseVisibleMediaMaterial greenBackgroundMaterial;

    private ProjectImageBuilder(ProjectBuilder projectBuilder, ProjectScriptBuilder scriptBuilder) {
        this.projectBuilder = projectBuilder;
        this.scriptBuilder = scriptBuilder;
        this.script = scriptBuilder.getScript();
    }

    public static ProjectImageBuilder getBuilder(ProjectBuilder projectBuilder, ProjectScriptBuilder scriptBuilder, long videoMaterialId, String videoUrl, long start, long duration) {
        return new ProjectImageBuilder(projectBuilder, scriptBuilder).add(videoMaterialId, videoUrl, start, duration);
    }

    private ProjectImageBuilder add(long imageMaterialId, String imageUrl, long start, long duration) {
        BaseMaterial existsMaterial = getMaterialById(imageMaterialId);
        if (existsMaterial instanceof ImageMaterial imageMaterial) {
            material = imageMaterial;
        } else {
            material = new ImageMaterial();
            material.setId(imageMaterialId);
            material.setUrl(imageUrl);
        }

        videoTime = new VideoTimeRange(start, duration);

        segment = new VideoSegment();
        segment.setId(SnowflakeIdUtils.nextTmpId());
        segment.setMaterialId(imageMaterialId);
        segment.setType(MaterialTypeEnum.MATERIAL_TYPE_IMAGE);
        segment.setLayoutIndex(1000);

        videoPoint = new VideoPoint(0, 0);

        return this;
    }

    /**
     * 添加转场特效（视频末尾），使用默认转场时长
     * @param transitionId 转场资源ID
     */
    public ProjectImageBuilder addTransition(long transitionId) {
        return addTransition(transitionId, null);
    }

    /**
     * 添加转场特效（视频末尾）
     * @param transitionId 转场资源ID
     * @param duration 转场时长（为null 时，表示使用此转场特效的默认时长）
     */
    public ProjectImageBuilder addTransition(long transitionId, Long duration) {
        TransitionMaterial transitionMaterial = new TransitionMaterial();
        transitionMaterial.setId(SnowflakeIdUtils.nextTmpId());
        transitionMaterial.setResourceId(transitionId);
        if (duration != null) {
            transitionMaterial.setDuration(duration);
        }

        segment.getRefs().put(transitionMaterial.getId(), MaterialTypeEnum.MATERIAL_TYPE_TRANSITION);
        return this;
    }

    /**
     * 为视频素材添加LUT文件
     * @param cubeUrl lut文件的URL地址
     * @param strength 强度 [0, 100]
     * @param skinToneCorrection 肤色保护 [0, 100]，为空时表示不设置
     */
    public ProjectImageBuilder setLut(String cubeUrl, int strength, Integer skinToneCorrection) {
        if (!StringUtils.hasLength(cubeUrl)) {
            throw new DuoServiceException("CUBE 文件路径不能为空");
        }
        material.setLut(new BaseVisibleMediaMaterial.Lut().setUrl(cubeUrl).setStrength(strength).setSkinToneCorrection(skinToneCorrection));
        return this;
    }
}
