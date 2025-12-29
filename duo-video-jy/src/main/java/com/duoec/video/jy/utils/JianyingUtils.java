package com.duoec.video.jy.utils;

import com.duoec.base.core.util.FileUtils;
import com.duoec.video.jy.dto.font.FontConfig;
import com.duoec.video.jy.dto.info.*;
import com.duoec.video.jy.dto.meta.JianYingProjectMeta;
import com.duoec.video.jy.dto.meta.Value;

import java.util.List;

public class JianyingUtils {
    public static long LONG_1000 = 1000;

    public static double pxToJianyingNonlinear(int px) {
        return 0.00196 * Math.pow(px, 1.013);
    }

    public static Video getDefaultVideo() {
        return FileUtils.readJson("tpl/empty_jy_material_video.json", Video.class);
    }

    public static Audio getDefaultAudio() {
        return FileUtils.readJson("tpl/empty_yj_material_audio.json", Audio.class);
    }

    public static FontConfig getDefaultFontConfig() {
        return FileUtils.readJson("tpl/empty_jy_text_styles.json", FontConfig.class);
    }

    public static Text getDefaultText() {
        return FileUtils.readJson("tpl/empty_yj_material_text.json", Text.class);
    }

    public static JianYingProjectInfo getDefaultProjectInfo() {
        return FileUtils.readJson("tpl/empty_jy_draft_info.json", JianYingProjectInfo.class);
    }

    public static Segment getDefaultSegment() {
        return FileUtils.readJson("tpl/empty_jy_segment.json", Segment.class);
    }

    public static JianYingProjectMeta getDefaultProjectMeta() {
        return FileUtils.readJson("tpl/empty_draft_meta_info.json", JianYingProjectMeta.class);
    }

    public static Value getDefaultProjectMetaMaterialValue() {
        return FileUtils.readJson("tpl/empty_jy_meta_material_value.json", Value.class);
    }

    public static List<Double> hexToNormalizedRGB(String hexColor) {
        if (hexColor == null) {
            hexColor = "#FFFFFF";
        }
        // 去除可能的前缀 "#"（如 "#000000" → "000000"）
        String hex = hexColor.startsWith("#") ? hexColor.substring(1) : hexColor;

        // 确保是6位十六进制颜色（如 "000000"）
        if (hex.length() != 6) {
            throw new IllegalArgumentException("Invalid hex color format. Expected #RRGGBB or RRGGBB.");
        }

        // 解析 R, G, B 分量
        int r = Integer.parseInt(hex.substring(0, 2), 16);
        int g = Integer.parseInt(hex.substring(2, 4), 16);
        int b = Integer.parseInt(hex.substring(4, 6), 16);

        // 归一化到 [0.0, 1.0]
        double[] normalized = new double[3];
        normalized[0] = r / 255.0f;
        normalized[1] = g / 255.0f;
        normalized[2] = b / 255.0f;

        return List.of(normalized[0], normalized[1], normalized[2]);
    }

    /**
     * 计算音量线性增益，如果 dB < -100，则直接返回0
     *
     * @param dB 分贝
     */
    public static double amplitudeGain(Double dB) {
        if (dB == null) {
            return 1.0;
        }
        if (dB <= -1.0) {
            return 0.0;
        }
        return Math.pow(10, dB / 20);
    }
}
