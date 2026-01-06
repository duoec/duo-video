package com.duoec.video.jy.builder;

import com.duoec.base.core.DuoServerConsts;
import com.duoec.base.core.util.NumberUtils;
import com.duoec.video.jy.JianyingProjectBuildState;
import com.duoec.video.jy.dto.TimeRange;
import com.duoec.video.jy.dto.info.JianYingProjectInfo;
import com.duoec.video.jy.dto.info.Segment;
import com.duoec.video.jy.dto.info.Track;
import com.duoec.video.jy.utils.JianyingUtils;
import com.duoec.video.jy.utils.UuidUtils;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JianyingTrackBuilder {
    private static final Logger logger = LoggerFactory.getLogger(JianyingTrackBuilder.class);
    private static final Pattern TRACK_NAME_NO_PATTERN = Pattern.compile("^(.*-)(\\d+)$");

    /**
     * 各轨道的排序值，越大越上层
     */
    private static final String[] TRACK_LAYOUTS = new String[]{
            "特效音",
            "音频",

            "绿幕背景",
            "视频",
            "图片",
            "蒙板",

            "画面特效",

            "贴纸",

            "字幕",
            "文本",
            "文本模板",
    };
    private static final Map<String, Integer> TRACK_LAYOUT_INDEX = new HashMap<>();

    static {
        for (int i = 0; i < TRACK_LAYOUTS.length; i++) {
            TRACK_LAYOUT_INDEX.put(TRACK_LAYOUTS[i], 10000 * (i + 1));
        }
    }

    /**
     * 获取或创建一个不层叠的track，如果有层叠则重新创建一个以 {trackName}-{序号}的track
     */
    public static Track getOrCreateTrack(JianYingProjectInfo projectInfo, String type, String trackName, long start, long end) {
        List<Track> tracks = Optional.ofNullable(projectInfo.getTracks()).orElse(Lists.newArrayList());
        Track track = tracks.stream()
                .filter(t -> type.equals(t.getType()) && trackName.equals(t.getName()))
                .findFirst()
                .orElse(null);

        if (track == null) {
            // 新建
            Track newTrack = getDefaultTrack(type, trackName)
                    .setDuoLayoutIndex(getTraceLayout(projectInfo, trackName));
            tracks.add(newTrack);
            logger.debug("创建轨道：{}", trackName);
            return newTrack;
        }

        // 检查是否有层叠
        boolean free = true;
        for (Segment segment : track.getSegments()) {
            TimeRange targetTimeRange = segment.getTargetTimeRange();
            if (between(targetTimeRange, start) || between(targetTimeRange, end)) {
                free = false;
                break;
            }
        }
        if (free) {
            return track;
        }

        // 有层叠，则在现有的名称后面添加序号
        Matcher matcher = TRACK_NAME_NO_PATTERN.matcher(trackName);
        String newTrackName;
        int num;
        if (matcher.find()) {
            num = Integer.parseInt(matcher.group(2)) + 1;
            newTrackName = matcher.group(1) + (num + 1);
        } else {
            num = 1;
            newTrackName = trackName + DuoServerConsts.MIDDLE_LINE_STR + num;
        }
        return getOrCreateTrack(projectInfo, type, newTrackName, start, end);
    }

    public static int getTraceLayout(JianYingProjectInfo projectInfo, String trackName) {
        int index = trackName.lastIndexOf(DuoServerConsts.MIDDLE_LINE_STR);
        String groupName = trackName;
        int num = 0;
        if (index > -1) {
            String numStr = trackName.substring(index + 1);
            if (NumberUtils.isNatural(numStr)) {
                groupName = trackName.substring(0, index);
                num = Integer.parseInt(numStr);
            }
        }

        return TRACK_LAYOUT_INDEX.computeIfAbsent(groupName, name -> {
            logger.warn("未配置轨道：{}", name);
            return projectInfo.getTracks().stream().mapToInt(Track::getDuoLayoutIndex).max().orElse(0) + 10;
        }) + num;
    }

    public static Track getDefaultTrack(String type, String name) {
        return new Track()
                .setAttribute(0)
                .setFlag(0)
                .setId(UuidUtils.next())
                .setDefaultName(true)
                .setName(name)
                .setSegments(new ArrayList<>())
                .setType(type);
    }

    private static boolean between(TimeRange timeRange, long time) {
        time *= JianyingUtils.LONG_1000;
        return timeRange.getStart() < time && timeRange.getStart() + timeRange.getDuration() > time;
    }
}
