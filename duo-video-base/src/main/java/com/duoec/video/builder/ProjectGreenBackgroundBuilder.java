package com.duoec.video.builder;

import com.duoec.base.core.util.FileUtils;
import com.duoec.base.exceptions.DuoServiceException;
import com.duoec.video.project.material.BaseVisibleMediaMaterial;
import com.duoec.video.project.material.ImageMaterial;
import com.duoec.video.project.material.VideoMaterial;
import org.springframework.util.StringUtils;

public class ProjectGreenBackgroundBuilder extends BaseMaterialBuilder<BaseVisibleMediaMaterial> {
    private final ProjectVideoBuilder materialBuilder;
    private BaseVisibleMediaMaterial.GreenBackground greenBackground;
    private boolean existsMaterial = false;

    ProjectGreenBackgroundBuilder(ProjectBuilder projectBuilder, ProjectVideoBuilder materialBuilder) {
        this.projectBuilder = projectBuilder;
        this.materialBuilder = materialBuilder;
    }

    public ProjectGreenBackgroundBuilder add(long materialId, String materialUrl) {
        if (!StringUtils.hasLength(materialUrl)) {
            throw new DuoServiceException("无效素材链接");
        }
        String fileName = FileUtils.getFileName(materialUrl);
        boolean imageFile = FileUtils.isImageFile(fileName);

        BaseVisibleMediaMaterial existsBgMaterial = getMaterialById(materialId);
        if (existsBgMaterial != null) {
            material = existsBgMaterial;
            existsMaterial = true;
        } else {
            material = imageFile ? new ImageMaterial() : new VideoMaterial();
            material.setId(materialId);
            material.setUrl(materialUrl);
        }

        if (greenBackground == null) {
            greenBackground = new BaseVisibleMediaMaterial.GreenBackground();
        }
        greenBackground.setMaterialId(materialId);

        return this;
    }

    public ProjectGreenBackgroundBuilder setChroma(String color, int strength, int edgeFeather, int edgeCleanup) {
        if (greenBackground == null) {
            greenBackground = new BaseVisibleMediaMaterial.GreenBackground();
        }
        greenBackground.setBaseBackgroundColor(color);
        greenBackground.setEdgeCleanup(edgeCleanup);
        greenBackground.setStrength(strength);
        greenBackground.setEdgeFeather(edgeFeather);
        return this;
    }

    public ProjectVideoBuilder back() {
        if (greenBackground != null) {
            materialBuilder.getVideoMaterial().setGreenBackground(greenBackground);
            if (!existsMaterial) {
                projectBuilder.getProject().getMaterials().add(material);
            }
        }
        return materialBuilder;
    }
}
