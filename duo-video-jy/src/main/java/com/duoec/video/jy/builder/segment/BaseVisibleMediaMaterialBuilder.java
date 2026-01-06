package com.duoec.video.jy.builder.segment;

import com.duoec.base.core.DuoServerConsts;
import com.duoec.base.core.util.FileUtils;
import com.duoec.base.core.util.JsonUtils;
import com.duoec.video.jy.JianyingBuilder;
import com.duoec.video.jy.JianyingProjectBuildState;
import com.duoec.video.jy.dto.JyResource;
import com.duoec.video.jy.dto.info.Effect;
import com.duoec.video.jy.utils.JianyingResourceUtils;
import com.duoec.video.jy.utils.UuidUtils;
import com.duoec.video.project.VideoScript;
import com.duoec.video.project.VideoSegment;
import com.duoec.video.project.material.BaseVisibleMediaMaterial;
import org.assertj.core.util.Lists;
import org.springframework.util.StringUtils;

import java.io.File;
import java.util.List;
import java.util.Optional;

public abstract class BaseVisibleMediaMaterialBuilder<T extends BaseVisibleMediaMaterial> extends BaseSegmentBuilder<T> {
    protected List<Effect> buildLut(JianyingProjectBuildState state, VideoScript videoScript, VideoSegment videoSegment, T material) {
        List<Effect> effectList = Lists.newArrayList();
        BaseVisibleMediaMaterial.Lut lut = material.getLut();
        if (lut == null) {
            return effectList;
        }
        String lutUrl = lut.getUrl();
        if (!StringUtils.hasLength(lutUrl)) {
            return effectList;
        }
        String lutFileName = FileUtils.getFileName(lutUrl);
        File lutFile = new File(JianyingResourceUtils.JY_RS_DIR, "lut/" + lutFileName);
        if (!lutFile.exists()) {
            JianyingBuilder.storageService.download(lutUrl, lutFile);
        }
        if (!lutFile.exists()) {
            return null;
        }

        // 复制到草稿本地素材
        String lutPath = JianyingResourceUtils.copyToLocalResources(state, lutFile, "lut");

        JyResource lutResource = JianyingResourceUtils.getJyResource("effect", 267417583110651905L);

        // 加载资源
        JianyingResourceUtils.downloadResources(state.getProjectLocalResourceDir(), lutResource.getResources());

        Effect effect = JsonUtils.toObject(lutResource.getMainConfig(), Effect.class)
                .setId(UuidUtils.next())
                .setValue(Optional.ofNullable(lut.getStrength()).orElse(100) / 100.0)
                .setPath(lutPath);

        if (lut.getSkinToneCorrection() != null) {
            String effectPath = effect.getLumiHubPath();
            int index = effectPath.lastIndexOf(DuoServerConsts.OBLIQUE_LINE_STR);
            if (index != -1) {
                effectPath = effectPath.substring(0, index);
            }
            Effect skinToneEffect = JsonUtils.toObject(lutResource.getMainConfig(), Effect.class)
                    .setId(UuidUtils.next())
                    .setType("skin_tone_correction")
                    .setVersion("v3")
                    .setValue(Optional.ofNullable(lut.getSkinToneCorrection()).orElse(0) / 100.0)
                    .setPath(effectPath)
                    .setLumiHubPath(effectPath);
            effectList.add(skinToneEffect);
        }

        effectList.add(effect);

        return effectList;
    }
}
