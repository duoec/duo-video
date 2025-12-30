package com.duoec.video.builder;

import com.duoec.video.project.VideoProject;
import com.duoec.video.project.VideoScript;

import java.util.ArrayList;
import java.util.List;

public class ProjectBuilder {
    private final VideoProject project;

    private ProjectBuilder(long projectId, String projectName) {
        VideoScript videoScript = new VideoScript();
        videoScript.setSegments(new ArrayList<>());

        project = new VideoProject();
        project.setId(projectId);
        project.setProjectName(projectName);
        int now = (int) (System.currentTimeMillis() / 1000);
        project.setCreateTime(now);
        project.setUpdateTime(now);
        List<VideoScript> scripts = new ArrayList<>();
        scripts.add(videoScript);

        project.setScripts(scripts);
        project.setMaterials(new ArrayList<>());
    }

    public static ProjectBuilder createBuilder(long projectId, String projectName, int width, int height) {
        ProjectBuilder projectBuilder = new ProjectBuilder(projectId, projectName);
        projectBuilder.project.setWidth(width);
        projectBuilder.project.setHeight(height);
        return projectBuilder;
    }

    /**
     * 获取分镜Builder
     * @param scriptIndex 添加到哪个分镜。系统初始化后，默认会创建一个分镜，索引号为：0
     */
    public ProjectScriptBuilder getScriptBuilder(int scriptIndex) {
        return ProjectScriptBuilder.getBuilder(this, scriptIndex);
    }

    /**
     * 创建一个分镜，并返回新建分镜的Builder
     */
    public ProjectScriptBuilder addAndGetScriptBuilder() {
        return ProjectScriptBuilder.addAndGetScriptBuilder(this);
    }

    /**
     * 新建一个分镜，限定分镜时间
     * @param start 开始时间，单位：毫秒
     * @param duration 持续时间，单位：毫秒
     */
    public ProjectScriptBuilder addAndGetScriptBuilder(long start, long duration) {
        return ProjectScriptBuilder.addAndGetScriptBuilder(this)
                .setTime(start, duration);
    }

    /**
     * 设置测试模式
     */
    public ProjectBuilder setTest(boolean test) {
        this.project.setTest(test);
        return this;
    }

    public VideoProject getProject() {
        return project;
    }
}
