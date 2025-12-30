package com.duoec.video.builder;

import com.duoec.video.project.VideoProject;
import com.duoec.video.project.VideoScript;
import com.duoec.video.project.VideoTimeRange;

import java.util.ArrayList;
import java.util.List;

public class VideoProjectBuilder {
    private final VideoProject project;

    private VideoProjectBuilder(long projectId, String projectName) {
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

    public static VideoProjectBuilder createBuilder(long projectId, String projectName, int width, int height) {
        VideoProjectBuilder videoProjectBuilder = new VideoProjectBuilder(projectId, projectName);
        videoProjectBuilder.project.setWidth(width);
        videoProjectBuilder.project.setHeight(height);
        return videoProjectBuilder;
    }

    /**
     * 获取创建文本模板的Builder
     * @param scriptIndex 添加到哪个分镜。系统初始化后，默认会创建一个分镜，索引号为：0
     */
    public VideoProjectTextTemplateBuilder getTextTemplateBuilder(int scriptIndex) {
        return VideoProjectTextTemplateBuilder.getBuilder(this, scriptIndex);
    }

    /**
     * 获取创建文本的Builder
     * @param scriptIndex 添加到哪个分镜。系统初始化后，默认会创建一个分镜，索引号为：0
     */
    public VideoProjectTextBuilder getTextBuilder(int scriptIndex) {
        return VideoProjectTextBuilder.getBuilder(this, scriptIndex);
    }

    /**
     * 新建一个分镜
     */
    public VideoProjectBuilder createScript() {
        VideoScript videoScript = new VideoScript();
        videoScript.setSegments(new ArrayList<>());
        project.getScripts().add(videoScript);
        return this;
    }

    /**
     * 新建一个分镜，限定分镜时间
     * @param start 开始时间，单位：毫秒
     * @param duration 持续时间，单位：毫秒
     */
    public VideoProjectBuilder createScript(long start, long duration) {
        VideoScript videoScript = new VideoScript();
        videoScript.setSegments(new ArrayList<>());
        videoScript.setTime(new VideoTimeRange(start, duration));
        project.getScripts().add(videoScript);
        return this;
    }

    /**
     * 设置测试模式
     */
    public VideoProjectBuilder setTest(boolean test) {
        this.project.setTest(test);
        return this;
    }

    public VideoProject getProject() {
        return project;
    }
}
