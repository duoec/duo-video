package com.duoec.video.utils;

import com.duoec.base.core.DuoServerConsts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.List;

public class FfmpegUtils {
    private static final Logger logger = LoggerFactory.getLogger(FfmpegUtils.class);

    public static String FFMPEG = "ffmpeg";

    public static void upend(File videoFile, File targetVideoFile) {
        String[] cmd = new String[]{
                FFMPEG,
                "-nostdin", // 禁止 ffmpeg 从 stdin 读取任何输入
                "-i", videoFile.getAbsolutePath(),
                "-vf", "reverse",
                "-af", "areverse",
                "-y",
                targetVideoFile.getAbsolutePath()
        };
        List<String> info = ProcessBuilderUtils.exec(60, cmd);
        logger.debug("{}", String.join(DuoServerConsts.TURN_LINE, info));
    }
}
