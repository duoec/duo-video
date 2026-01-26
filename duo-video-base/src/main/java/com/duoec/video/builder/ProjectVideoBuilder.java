package com.duoec.video.builder;

import com.duoec.base.core.util.SnowflakeIdUtils;
import com.duoec.base.exceptions.DuoServiceException;
import com.duoec.video.project.VideoPoint;
import com.duoec.video.project.VideoSegment;
import com.duoec.video.project.VideoTimeRange;
import com.duoec.video.project.material.*;
import org.springframework.util.StringUtils;

import java.util.function.Consumer;

public class ProjectVideoBuilder extends BaseSegmentBuilder<VideoMaterial, ProjectVideoBuilder> {
    private BaseVisibleMediaMaterial greenBackgroundMaterial;

    private Integer volume;
    private VideoTimeRange materialTime;

    private ProjectVideoBuilder(ProjectBuilder projectBuilder, ProjectScriptBuilder scriptBuilder) {
        this.projectBuilder = projectBuilder;
        this.scriptBuilder = scriptBuilder;
        this.script = scriptBuilder.getScript();
    }

    public static ProjectVideoBuilder getBuilder(ProjectBuilder projectBuilder, ProjectScriptBuilder scriptBuilder, long videoMaterialId, String videoUrl, long start, long duration) {
        return new ProjectVideoBuilder(projectBuilder, scriptBuilder).add(videoMaterialId, videoUrl, start, duration);
    }

    /**
     * 设置倒放，null为顺序播放
     * @param upend true=倒放 false=顺放
     */
    public ProjectVideoBuilder setUpend(Boolean upend) {
        this.upend = upend;
        return this;
    }

    private ProjectVideoBuilder add(long videoMaterialId, String videoUrl, long start, long duration) {
        BaseMaterial existsMaterial = getMaterialById(videoMaterialId);
        if (existsMaterial instanceof VideoMaterial videoMaterial) {
            material = videoMaterial;
        } else {
            material = new VideoMaterial();
            material.setId(videoMaterialId);
            material.setUrl(videoUrl);
        }

        videoTime = new VideoTimeRange(start, duration);

        segment = new VideoSegment();
        segment.setId(SnowflakeIdUtils.nextTmpId());
        segment.setMaterialId(videoMaterialId);
        segment.setType(MaterialTypeEnum.MATERIAL_TYPE_VIDEO);
        segment.setLayoutIndex(1000);

        videoPoint = new VideoPoint(0, 0);

        return this;
    }

    /**
     * 添加转场特效（视频末尾），使用默认转场时长
     * @param transitionId 转场资源ID
     */
    public ProjectVideoBuilder addTransition(long transitionId) {
        return addTransition(transitionId, null);
    }

    /**
     * 添加转场特效（视频末尾）
     * @param transitionId 转场资源ID
     * @param duration 转场时长（为null 时，表示使用此转场特效的默认时长）
     */
    public ProjectVideoBuilder addTransition(long transitionId, Long duration) {
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
    public ProjectVideoBuilder setLut(String cubeUrl, int strength, Integer skinToneCorrection) {
        if (!StringUtils.hasLength(cubeUrl)) {
            throw new DuoServiceException("CUBE 文件路径不能为空");
        }
        material.setLut(new BaseVisibleMediaMaterial.Lut().setUrl(cubeUrl).setStrength(strength).setSkinToneCorrection(skinToneCorrection));
        return this;
    }

    public ProjectGreenBackgroundBuilder addGreenBackgroundAndGetBuilder(long backgroundId, String backgroundUrl) {
        return new ProjectGreenBackgroundBuilder(projectBuilder, scriptBuilder, this).add(backgroundId, backgroundUrl);
    }

    /**
     * 设置当前视频为绿幕视频，并添加背景
     * @param backgroundId 背景ID
     * @param backgroundUrl 背景素材链接
     * @param greenBackgroundBuilderConsumer 新添加的 ProjectGreenBackgroundBuilder 上下文编辑器
     */
    public ProjectVideoBuilder buildGreenBackground(long backgroundId, String backgroundUrl, Consumer<ProjectGreenBackgroundBuilder> greenBackgroundBuilderConsumer) {
        ProjectGreenBackgroundBuilder greenBackgroundBuilder = new ProjectGreenBackgroundBuilder(projectBuilder, scriptBuilder, this).add(backgroundId, backgroundUrl);
        if (greenBackgroundBuilderConsumer != null) {
            greenBackgroundBuilderConsumer.accept(greenBackgroundBuilder);
        }
        greenBackgroundBuilder.back();
        return this;
    }

    public ProjectVideoBuilder setMaterialTime(long start, long duration) {
        this.materialTime = new VideoTimeRange(start, duration);
        return this;
    }

    public ProjectVideoBuilder setVolume(int volume) {
        this.volume = volume;
        return this;
    }

    public BaseVisibleMediaMaterial getGreenBackgroundMaterial() {
        return greenBackgroundMaterial;
    }

    public ProjectScriptBuilder back() {
        if (greenBackgroundMaterial != null) {
            projectBuilder.getProject().getMaterials().add(greenBackgroundMaterial);
        }
        if (volume != null) {
            segment.setVolume(volume);
        }
        if (materialTime != null) {
            material.setTime(materialTime);
        }
        return super.back();
    }

    VideoMaterial getVideoMaterial() {
        return material;
    }
}
