package com.duoec.video.jy.builder;

import com.duoec.video.jy.JianyingProjectBuildState;
import com.duoec.video.project.VideoScript;

import java.util.List;
import java.util.Optional;

public class JianyingScriptBuilder {
    public static void build(JianyingProjectBuildState state, VideoScript videoScript) {
        Optional.ofNullable(videoScript.getSegments()).orElse(List.of())
                .stream()
                .filter(segment -> Optional.ofNullable(segment.getVisible()).orElse(true))
                .forEach(segment -> JianyingSegmentBuilder.build(state, videoScript, segment));
    }
}
