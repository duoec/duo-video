package com.duoec.video.jy.builder.segment;

import com.duoec.video.jy.JianyingProjectBuildState;
import com.duoec.video.jy.builder.JianyingTrackBuilder;
import com.duoec.video.jy.dto.TextTemplateDto;
import com.duoec.video.jy.dto.info.*;
import com.duoec.video.jy.utils.JianyingResourceUtils;
import com.duoec.video.jy.utils.TextTemplateAdapter;
import com.duoec.video.project.VideoScript;
import com.duoec.video.project.VideoSegment;
import com.duoec.video.project.VideoTimeRange;
import com.duoec.video.project.material.MaterialTypeEnum;
import com.duoec.video.project.material.TextTemplateMaterial;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class TextTemplateSegmentBuilder extends BaseSegmentBuilder<TextTemplateMaterial> {
    private static final Logger logger = LoggerFactory.getLogger(TextTemplateSegmentBuilder.class);

    @Override
    public MaterialTypeEnum getMaterialType() {
        return MaterialTypeEnum.TEXT_TEMPLATE_MATERIAL;
    }

    @Override
    public Segment build(JianyingProjectBuildState state, VideoScript videoScript, VideoSegment videoSegment, TextTemplateMaterial material) {
        long resourceId = Optional.ofNullable(material.getResourceId()).orElse(0L);
        if (resourceId <= 0) {
            logger.warn("未指定文本模板，resourceId={}", resourceId);
            return null;
        }

        // 使用 jy_text_template_adapter 计算文本模板尺寸
        TextTemplateDto dto = TextTemplateAdapter.getTextTemplate(
                resourceId,
                material.getTexts(),
                JianyingResourceUtils.JY_RS_DIR.getAbsolutePath(),
                state.getProjectLocalResourceDir().getAbsolutePath(),
                videoSegment.getTime().getDuration()
        );
        if (dto == null) {
            return null;
        }
        TextTemplate textTemplate = dto.getTextTemplate();
        Text text = dto.getText();
        Effect effect = dto.getEffect();

        Materials materials = state.getJianyingProject().getMaterials();
        materials.getTexts().add(text);
        materials.getTextTemplates().add(textTemplate);
        if (effect != null) {
            materials.getEffects().add(effect);
        }
        VideoTimeRange segmentTime = videoSegment.getTime();
        Track textTrack = JianyingTrackBuilder.getOrCreateTrack(state, Track.TYPE_TEXT, "文本模板", segmentTime.getStart(), segmentTime.getEndTime());

        Segment segment = getSegment(videoSegment, textTemplate)
                .setExtraMaterialRefs(Lists.newArrayList())
                .setTrackRenderIndex(0);
        textTrack.getSegments().add(segment);

        return segment;
    }


}
