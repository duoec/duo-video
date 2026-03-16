package com.duoec.video.builder;

import com.duoec.base.core.util.FileUtils;
import com.duoec.base.exceptions.DuoServiceException;
import com.duoec.video.project.material.BaseVisibleMediaMaterial;
import com.duoec.video.project.material.ImageMaterial;
import com.duoec.video.project.material.VideoMaterial;
import org.springframework.util.StringUtils;

public class ProjectGreenScreenBuilder extends BaseMaterialBuilder<BaseVisibleMediaMaterial> {
    private final ProjectVideoBuilder materialBuilder;
    private boolean existsMaterial = false;
    private BaseVisibleMediaMaterial.GreenScreen greenScreen;

    ProjectGreenScreenBuilder(ProjectBuilder projectBuilder, ProjectVideoBuilder materialBuilder) {
        this.projectBuilder = projectBuilder;
        this.materialBuilder = materialBuilder;
    }

    public ProjectGreenScreenBuilder add(long materialId, String materialUrl) {
        if (!StringUtils.hasLength(materialUrl)) {
            throw new DuoServiceException("无效素材链接");
        }
        String fileName = FileUtils.getFileName(materialUrl);
        boolean imageFile = FileUtils.isImageFile(fileName);

        BaseVisibleMediaMaterial existsBgMaterial = getMaterialById(materialId);
        if (existsBgMaterial != null) {
            this.material = existsBgMaterial;
            existsMaterial = true;
        } else {
            this.material = imageFile ? new ImageMaterial() : new VideoMaterial();
            this.material.setId(materialId);
            this.material.setUrl(materialUrl);
        }

        if (greenScreen == null) {
            greenScreen = new BaseVisibleMediaMaterial.GreenScreen();
        }
        greenScreen.setMediaId(materialId);

        return this;
    }

    public ProjectGreenScreenBuilder setChroma(String color, int strength, int edgeFeather, int edgeCleanup) {
        if (greenScreen == null) {
            greenScreen = new BaseVisibleMediaMaterial.GreenScreen();
        }
        greenScreen.setColor(color);
        greenScreen.setEdgeCleanup(edgeCleanup);
        greenScreen.setStrength(strength);
        greenScreen.setEdgeFeather(edgeFeather);
        return this;
    }

    public ProjectVideoBuilder back() {
        if (greenScreen != null) {
            materialBuilder.getVideoMaterial().setGreenScreen(greenScreen);
            if (!existsMaterial) {
                projectBuilder.getProject().getMaterials().add(material);
            }
        }
        return materialBuilder;
    }
}
