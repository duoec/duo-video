package com.duoec.video.project;

import com.duoec.video.project.material.BaseMaterial;
import lombok.Data;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.List;

@Data
public class VideoProject implements Serializable {
    /**
     * 工程ID
     */
    private Long id;

    /**
     * 工程名称
     */
    private String projectName;

    /**
     * 分镜
     */
    private List<VideoScript> scripts;

    /**
     * 素材
     */
    private List<BaseMaterial> materials;

    /**
     * 宽
     */
    private Integer width;

    /**
     * 高
     */
    private Integer height;

    /**
     * 帧率
     */
    private Integer fps;

    /**
     * 创建时间。时间戳，粒度：秒
     */
    private Integer createTime;

    /**
     * 修改时间。时间戳，粒度：秒
     */
    private Integer updateTime;

    /**
     * 是否测试环境
     */
    private Boolean test;

    /**
     * 工程视频时长，以 scripts 占位为准
     */
    public Long getDuration() {
        if (CollectionUtils.isEmpty(scripts)) {
            return 0L;
        }

        return scripts.stream().mapToLong(VideoScript::getEndTime).max().orElse(0L);
    }
}
