package com.duoec.video.jy;

import com.duoec.base.core.util.FileUtils;
import com.duoec.base.core.util.JsonUtils;
import com.duoec.base.exceptions.DuoServiceException;
import com.duoec.video.jy.dto.info.JianYingProjectInfo;
import com.duoec.video.jy.dto.info.Track;
import com.duoec.video.jy.dto.meta.JianYingProjectMeta;
import com.duoec.video.jy.utils.JianyingResourceUtils;
import com.duoec.video.jy.utils.JianyingUtils;
import com.duoec.video.jy.utils.UuidUtils;
import com.duoec.video.project.VideoProject;
import com.duoec.video.project.material.BaseMaterial;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class JianyingProjectBuildState {
    private static final Logger logger = LoggerFactory.getLogger(JianyingProjectBuildState.class);

    /**
     * 测试模式下，直接写入剪映草稿目录
     */
    public static String DEBUG_JY_DRAFT_DIR;

    private final VideoProject videoProject;

    private final JianYingProjectInfo jianYingProject;
    private final JianYingProjectMeta jianYingProjectMeta;

    private final Map<Long, BaseMaterial> materialMap;

    private final File projectDir;
    private final File projectLocalResourceDir;

    public JianyingProjectBuildState(VideoProject videoProject) {
        if (videoProject == null) {
            throw new DuoServiceException("工程不能为空！");
        }
        Long projectId = videoProject.getId();
        if (projectId == null || projectId <= 0L) {
            throw new DuoServiceException("工程ID不能为：" + projectId);
        }
        this.videoProject = videoProject;

        jianYingProjectMeta = JianyingUtils.getDefaultProjectMeta()
                .setDraftId(UuidUtils.next());
        jianYingProject = JianyingUtils.getDefaultProjectInfo();

        if (this.jianYingProject == null) {
            throw new DuoServiceException("初始化剪映工程失败！");
        }

        jianYingProject.setId(UuidUtils.next())
                .setName(projectId.toString())
                .setDuration(videoProject.getDuration() * JianyingUtils.LONG_1000);
        jianYingProject.getCanvasConfig()
                .setHeight(videoProject.getHeight())
                .setWidth(videoProject.getWidth());

        boolean test = Optional.ofNullable(videoProject.getTest()).orElse(false);

        String projectDirName = StringUtils.hasLength(videoProject.getProjectName()) ? videoProject.getProjectName() : videoProject.getId().toString();
        this.projectDir = test ?
                new File(DEBUG_JY_DRAFT_DIR + projectDirName) : // 测试时，直接写入剪映草稿目录
                new File(JianyingResourceUtils.JY_PROJECT_DIR, projectId.toString());

        if (test && this.projectDir.exists()) {
            // 测试环境下，先删除历史目录
            logger.info("测试条件-删除剪映目录：{}", this.projectDir);
            FileUtils.deletes(this.projectDir);
        }

        projectLocalResourceDir = new File(this.projectDir, JianyingResourceUtils.JY_RESOURCE_LOCAL);
        FileUtils.mkdirs(projectLocalResourceDir);

        if (this.videoProject.getMaterials() != null) {
            this.materialMap = this.videoProject.getMaterials()
                    .stream()
                    .filter(material -> material.getId() != null && material.getId() > 0)
                    .collect(Collectors.toMap(BaseMaterial::getId, Function.identity()));
        } else {
            this.materialMap = new HashMap<>();
        }
    }

    @SuppressWarnings("unchecked")
    public <T extends BaseMaterial> T getMaterial(long materialId) {
        return (T) this.materialMap.get(materialId);
    }

    public JianYingProjectInfo getJianyingProject() {
        return this.jianYingProject;
    }

    public VideoProject getVideoProject() {
        return videoProject;
    }

    public File getProjectDir() {
        return projectDir;
    }

    public File getProjectLocalResourceDir() {
        return projectLocalResourceDir;
    }

    public JianYingProjectMeta getJianYingProjectMeta() {
        return jianYingProjectMeta;
    }

    public void save() {
        // 重新排序
        List<Track> tracks = jianYingProject.getTracks();
        tracks.sort(Comparator.comparing(Track::getDuoLayoutIndex));

        tracks.stream().collect(Collectors.groupingBy(Track::getType)).forEach((type, ts) -> {
            for (int i = 0; i < ts.size(); i++) {
                ts.get(i).setFlag(i == 0 ? 0 : 2);
            }
        });

        FileUtils.writeFile(
                JsonUtils.toJsonString(jianYingProject).getBytes(StandardCharsets.UTF_8),
                new File(this.projectDir, "draft_info.json")
        );
        FileUtils.writeFile(
                JsonUtils.toJsonString(jianYingProjectMeta).getBytes(StandardCharsets.UTF_8),
                new File(this.projectDir, "draft_meta_info.json")
        );
    }
}
