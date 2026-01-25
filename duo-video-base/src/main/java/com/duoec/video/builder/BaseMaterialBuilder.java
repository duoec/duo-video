package com.duoec.video.builder;

import com.duoec.video.project.VideoPoint;
import com.duoec.video.project.VideoScript;
import com.duoec.video.project.VideoSegment;
import com.duoec.video.project.VideoTimeRange;
import com.duoec.video.project.material.BaseMaterial;

import java.util.List;

public class BaseMaterialBuilder<T extends BaseMaterial> {
    protected ProjectBuilder projectBuilder;

    protected T material;

    protected void beforeBack() {
    }

    protected T getMaterialById(long materialId) {
        return (T) projectBuilder.getProject().getMaterials()
                .stream()
                .filter(m -> m.getId().equals(materialId))
                .findFirst()
                .orElse(null);
    }

    protected void addMaterial(T material) {
        List<BaseMaterial> materials = projectBuilder.getProject().getMaterials();
        if (materials.stream().noneMatch(m -> m.getId().equals(material.getId()))) {
            materials.add(material);
        }
    }
}
