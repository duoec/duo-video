package com.duoec.video.builder;

import com.duoec.base.core.util.SnowflakeIdUtils;
import com.duoec.video.project.VideoPoint;
import com.duoec.video.project.VideoSegment;
import com.duoec.video.project.VideoTimeRange;
import com.duoec.video.project.material.AudioMaterial;
import com.duoec.video.project.material.BaseMaterial;
import com.duoec.video.project.material.MaterialTypeEnum;
import com.duoec.video.project.material.TransitionMaterial;

public class ProjectAudioBuilder extends BaseSegmentBuilder<AudioMaterial, ProjectAudioBuilder> {
    private Integer volume;
    private VideoTimeRange materialTime;

    private ProjectAudioBuilder(ProjectBuilder projectBuilder, ProjectScriptBuilder scriptBuilder) {
        this.projectBuilder = projectBuilder;
        this.scriptBuilder = scriptBuilder;
        this.script = scriptBuilder.getScript();
    }

    public static ProjectAudioBuilder getBuilder(ProjectBuilder projectBuilder, ProjectScriptBuilder scriptBuilder, long audioMaterialId, String audioUrl, long start, long duration) {
        return new ProjectAudioBuilder(projectBuilder, scriptBuilder).add(audioMaterialId, audioUrl, start, duration);
    }

    private ProjectAudioBuilder add(long audioMaterialId, String audioUrl, long start, long duration) {
        BaseMaterial existsMaterial = getMaterialById(audioMaterialId);
        if (existsMaterial instanceof AudioMaterial audioMaterial) {
            material = audioMaterial;
        } else {
            material = new AudioMaterial();
            material.setId(audioMaterialId);
            material.setUrl(audioUrl);
        }

        videoTime = new VideoTimeRange(start, duration);

        segment = new VideoSegment();
        segment.setId(SnowflakeIdUtils.nextTmpId());
        segment.setMaterialId(audioMaterialId);
        segment.setType(MaterialTypeEnum.MATERIAL_TYPE_AUDIO);
        segment.setLayoutIndex(1000);

        videoPoint = new VideoPoint(0, 0);

        return this;
    }

    /**
     * 添加转场特效（视频末尾），使用默认转场时长
     * @param transitionId 转场资源ID
     */
    public ProjectAudioBuilder addTransition(long transitionId) {
        return addTransition(transitionId, null);
    }

    /**
     * 添加转场特效（视频末尾）
     * @param transitionId 转场资源ID
     * @param duration 转场时长（为null 时，表示使用此转场特效的默认时长）
     */
    public ProjectAudioBuilder addTransition(long transitionId, Long duration) {
        TransitionMaterial transitionMaterial = new TransitionMaterial();
        transitionMaterial.setId(SnowflakeIdUtils.nextTmpId());
        transitionMaterial.setResourceId(transitionId);
        if (duration != null) {
            transitionMaterial.setDuration(duration);
        }

        segment.getRefs().put(transitionMaterial.getId(), MaterialTypeEnum.MATERIAL_TYPE_TRANSITION);
        return this;
    }

    public ProjectAudioBuilder setVolume(int volume) {
        this.volume = volume;
        return this;
    }

    public ProjectAudioBuilder setMaterialTime(long start, long duration) {
        this.materialTime = new VideoTimeRange(start, duration);
        return this;
    }

    public ProjectScriptBuilder back() {
        if (volume != null) {
            segment.setVolume(volume);
        }
        if (materialTime != null) {
            material.setTime(materialTime);
        }
        return super.back();
    }
}
