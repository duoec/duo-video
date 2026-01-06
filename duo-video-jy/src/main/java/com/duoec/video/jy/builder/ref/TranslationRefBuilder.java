package com.duoec.video.jy.builder.ref;

import com.duoec.base.core.util.JsonUtils;
import com.duoec.video.jy.JianyingProjectBuildState;
import com.duoec.video.jy.dto.JyResource;
import com.duoec.video.jy.dto.info.Segment;
import com.duoec.video.jy.dto.info.Transition;
import com.duoec.video.jy.utils.JianyingResourceUtils;
import com.duoec.video.jy.utils.JianyingUtils;
import com.duoec.video.jy.utils.UuidUtils;
import com.duoec.video.project.material.TransitionMaterial;

public class TranslationRefBuilder extends BaseRefBuilder {
    public static void build(JianyingProjectBuildState state, Segment segment, TransitionMaterial transitionMaterial) {
        JyResource transitionConfig = JianyingResourceUtils.getJyResource(transitionMaterial.getType(), transitionMaterial.getResourceId());
        if (transitionConfig == null) {
            return;
        }

        // 复制资源文件到剪映本地素材库
        JianyingResourceUtils.downloadResources(state.getProjectLocalResourceDir(), transitionConfig.getResources());
        Transition transition = JsonUtils.toObject(transitionConfig.getMainConfig(), Transition.class)
                .setId(UuidUtils.next());
        Long duration = transitionMaterial.getDuration();
        if (duration != null) {
            transition.setDuration(duration * JianyingUtils.LONG_1000);
        }

        state.getJianyingProject().getMaterials().getTransitions().add(transition);
        segment.getExtraMaterialRefs().add(transition.getId());
    }
}
