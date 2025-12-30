package com.duoec.video.builder;

import com.duoec.base.core.util.FileUtils;
import com.duoec.base.exceptions.DuoServiceException;
import com.duoec.video.project.material.BaseVisibleMediaMaterial;
import com.duoec.video.project.material.ImageMaterial;
import com.duoec.video.project.material.VideoMaterial;
import org.springframework.util.StringUtils;

public class ProjectGreenBackgroundBuilder {
    private final ProjectBuilder projectBuilder;
    private final ProjectScriptBuilder scriptBuilder;
    private final ProjectVideoBuilder materialBuilder;
    private BaseVisibleMediaMaterial material;
    private BaseVisibleMediaMaterial.GreenBackground greenBackground;

    ProjectGreenBackgroundBuilder(ProjectBuilder projectBuilder, ProjectScriptBuilder scriptBuilder, ProjectVideoBuilder materialBuilder) {
        this.projectBuilder = projectBuilder;
        this.scriptBuilder = scriptBuilder;
        this.materialBuilder = materialBuilder;
    }

    public ProjectGreenBackgroundBuilder add(long materialId, String materialUrl) {
        if (!StringUtils.hasLength(materialUrl)) {
            throw new DuoServiceException("无效素材链接");
        }
        String fileName = FileUtils.getFileName(materialUrl);
        if (greenBackground == null) {
            greenBackground = new BaseVisibleMediaMaterial.GreenBackground();
        }
        greenBackground.setMaterialId(materialId);

        material = FileUtils.isImageFile(fileName) ? new ImageMaterial() : new VideoMaterial();
        material.setId(materialId);
        material.setUrl(materialUrl);

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
            projectBuilder.getProject().getMaterials().add(material);
        }
        return materialBuilder;
    }
}
