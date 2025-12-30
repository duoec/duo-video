package com.duoec.video.builder;

import com.duoec.base.core.util.SnowflakeIdUtils;
import com.duoec.base.exceptions.DuoServiceException;
import com.duoec.video.project.VideoPoint;
import com.duoec.video.project.VideoSegment;
import com.duoec.video.project.VideoTimeRange;
import com.duoec.video.project.material.BaseVisibleMediaMaterial;
import com.duoec.video.project.material.VideoMaterial;
import org.springframework.util.StringUtils;

public class ProjectVideoBuilder extends BaseMaterialBuilder<VideoMaterial, ProjectVideoBuilder> {
    private BaseVisibleMediaMaterial greenBackgroundMaterial;

    private ProjectVideoBuilder(ProjectBuilder projectBuilder, ProjectScriptBuilder scriptBuilder) {
        this.projectBuilder = projectBuilder;
        this.scriptBuilder = scriptBuilder;
        this.script = scriptBuilder.getScript();
    }

    public static ProjectVideoBuilder getBuilder(ProjectBuilder projectBuilder, ProjectScriptBuilder scriptBuilder) {
        return new ProjectVideoBuilder(projectBuilder, scriptBuilder);
    }

    public ProjectVideoBuilder add(long videoMaterialId, String videoUrl, long start, long duration) {
        material = new VideoMaterial();
        material.setId(videoMaterialId);
        material.setUrl(videoUrl);

        videoTime = new VideoTimeRange(start, duration);

        segment = new VideoSegment();
        segment.setId(SnowflakeIdUtils.nextTmpId());
        segment.setMaterialId(videoMaterialId);
        segment.setType("video");
        segment.setLayoutIndex(1000);

        videoPoint = new VideoPoint(0, 0);

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
        material.setLut(
                new BaseVisibleMediaMaterial.Lut()
                        .setUrl(cubeUrl)
                        .setStrength(strength)
                        .setSkinToneCorrection(skinToneCorrection)
        );
        return this;
    }

    public ProjectGreenBackgroundBuilder addGreenBackgroundAndGetBuilder(long backgroundId, String backgroundUrl) {
        return new ProjectGreenBackgroundBuilder(projectBuilder, scriptBuilder, this)
                .add(backgroundId, backgroundUrl);
    }

    public BaseVisibleMediaMaterial getGreenBackgroundMaterial() {
        return greenBackgroundMaterial;
    }

    public ProjectScriptBuilder back() {
        if (greenBackgroundMaterial != null) {
            projectBuilder.getProject().getMaterials().add(greenBackgroundMaterial);
        }
        return super.back();
    }

    VideoMaterial getVideoMaterial() {
        return material;
    }
}
