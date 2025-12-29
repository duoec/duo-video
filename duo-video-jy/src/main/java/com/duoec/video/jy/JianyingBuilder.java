package com.duoec.video.jy;

import com.duoec.video.jy.builder.JianyingMaterialBuilder;
import com.duoec.video.jy.builder.JianyingScriptBuilder;
import com.duoec.video.jy.dto.info.JianYingProjectInfo;
import com.duoec.video.jy.service.StorageService;
import com.duoec.video.project.VideoProject;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class JianyingBuilder {
    public static StorageService storageService;

    /**
     * 构建剪映工程
     */
    public JianYingProjectInfo build(VideoProject project) {
        JianyingProjectBuildState state = new JianyingProjectBuildState(project);

        // 初始化素材
        JianyingMaterialBuilder.build(state);

        // 构建每一个分镜
        Optional.ofNullable(project.getScripts()).orElse(List.of()).forEach(videoScript -> {
            JianyingScriptBuilder.build(state, videoScript);
        });

        state.save();
        return state.getJianyingProject();
    }
}
