package com.duoec.video.builder;

import com.duoec.video.project.VideoProject;
import com.duoec.video.project.VideoScript;
import com.duoec.video.project.VideoTimeRange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Consumer;

public class ProjectBuilder {
    private static final Logger logger = LoggerFactory.getLogger(ProjectBuilder.class);

    private final VideoProject project;

    /**
     * 创建一个新工程。未指定视频宽、高，使用默认值 1080x1920
     * @param projectId 指定视频ID
     * @param projectName 指定视频名称
     */
    private ProjectBuilder(long projectId, String projectName) {
        VideoScript videoScript = new VideoScript();

        int now = (int) (System.currentTimeMillis() / 1000);
        project = new VideoProject();
        project.setId(projectId);
        project.setProjectName(projectName);
        project.setCreateTime(now);
        project.setUpdateTime(now);
        project.getScripts().add(videoScript);
    }

    /**
     * 创建一个新工程。指定视频宽、高
     * @param projectId 指定视频ID
     * @param projectName 指定视频名称
     * @param width 视频宽
     * @param height 视频高
     */
    public static ProjectBuilder createBuilder(long projectId, String projectName, int width, int height) {
        ProjectBuilder projectBuilder = new ProjectBuilder(projectId, projectName);
        projectBuilder.project.setWidth(width);
        projectBuilder.project.setHeight(height);
        return projectBuilder;
    }

    /**
     * 构建VideoProject
     * @param projectBuilderConsumer VideoProject上下文构建器
     */
    public ProjectBuilder build(Consumer<ProjectBuilder> projectBuilderConsumer) {
        projectBuilderConsumer.accept(this);
        return this;
    }

    /**
     * 获取分镜Builder，如果指定的分镜号还不存在，则会直接创建，补全缺失的分镜
     * 比如，当前只有一个分镜，但请求 getScriptBuilder(10)时，会自动创建10个空分镜，然后返回第11个分镜
     * @param scriptIndex 添加到哪个分镜
     * @return 返回索引对应的分镜
     */
    public ProjectScriptBuilder getScriptBuilder(int scriptIndex) {
        int size = this.getProject().getScripts().size();
        if (size < scriptIndex) {
            // 已经存在了
            return ProjectScriptBuilder.getBuilder(this, scriptIndex);
        }
        int span = scriptIndex - size;
        if (span > 10) {
            // 如果超过10，会尝试提醒，不报错
            logger.warn("尝试获取分镜索引：{}，缺失分镜数量达:{}，请关注", scriptIndex, span);
        }
        // 还不存在，会自动创建缺失的分镜
        for (int i = size - 1; i < scriptIndex; i++) {
            ProjectScriptBuilder.addAndGetScriptBuilder(this);
        }

        return ProjectScriptBuilder.getBuilder(this, scriptIndex);
    }

    /**
     * 创建一个分镜，并返回新建分镜的Builder
     */
    public ProjectScriptBuilder addAndGetScriptBuilder() {
        return ProjectScriptBuilder.addAndGetScriptBuilder(this);
    }

    /**
     * 创建一个分镜，并指定分镜时间，并返回新建分镜的Builder
     */
    public ProjectScriptBuilder addAndGetScriptBuilder(VideoTimeRange time) {
        return ProjectScriptBuilder.addAndGetScriptBuilder(this, time);
    }

    /**
     * 新建一个分镜，限定分镜时间
     * @param start 开始时间，单位：毫秒
     * @param duration 持续时间，单位：毫秒
     */
    public ProjectScriptBuilder addAndGetScriptBuilder(long start, long duration) {
        return ProjectScriptBuilder.addAndGetScriptBuilder(this, new VideoTimeRange(start, duration));
    }

    /**
     * 指定分镜，构建分镜内容
     * @param scriptIndex 添加到哪个分镜。系统初始化后，默认会创建一个分镜，索引号为：0
     * @param scriptBuilderConsumer 分镜Builder上下文，可以在这里构建当前分镜的结构
     */
    public ProjectBuilder buildScript(int scriptIndex, Consumer<ProjectScriptBuilder> scriptBuilderConsumer) {
        ProjectScriptBuilder builder = getScriptBuilder(scriptIndex);
        if (scriptBuilderConsumer != null) {
            scriptBuilderConsumer.accept(builder);
        }
        return this;
    }

    /**
     * 新建分镜，构建分镜内容
     */
    public ProjectBuilder buildNewScript(Consumer<ProjectScriptBuilder> scriptBuilderConsumer) {
        ProjectScriptBuilder builder = addAndGetScriptBuilder();
        if (scriptBuilderConsumer != null) {
            scriptBuilderConsumer.accept(builder);
        }
        return this;
    }

    /**
     * 新建分镜并指定分镜时间，构建分镜内容
     */
    public ProjectBuilder buildNewScript(long start, long duration, Consumer<ProjectScriptBuilder> scriptBuilderConsumer) {
        ProjectScriptBuilder builder = addAndGetScriptBuilder(start, duration);
        if (scriptBuilderConsumer != null) {
            scriptBuilderConsumer.accept(builder);
        }
        return this;
    }

    /**
     * 新建分镜并指定分镜时间，构建分镜内容
     */
    public ProjectBuilder buildNewScript(VideoTimeRange time, Consumer<ProjectScriptBuilder> scriptBuilderConsumer) {
        ProjectScriptBuilder builder = addAndGetScriptBuilder(time);
        if (scriptBuilderConsumer != null) {
            scriptBuilderConsumer.accept(builder);
        }
        return this;
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
