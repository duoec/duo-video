package com.duoec.video.utils;

import com.duoec.base.core.DuoServerConsts;
import com.duoec.base.core.util.JsonUtils;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.util.List;

public class ExiftoolUtils {
    private static final Logger logger = LoggerFactory.getLogger(ExiftoolUtils.class);
    public static String STR_EXIFTOOL = "exiftool";

    public static FfmpegUtils.MediaExif getMediaExif(File file) {
        try {
            if (file == null) {
                return null;
            }
            // ProcessBuilder handles arguments safely without shell interpretation
            // so we can pass the absolute path directly without quotes
            List<String> info = ProcessBuilderUtils.exec(
                    10,
                    STR_EXIFTOOL,
                    "-n",
                    "-j",
                    file.getAbsolutePath()
            );
            List<FfmpegUtils.MediaExif> exif = JsonUtils.toObjectList(String.join(DuoServerConsts.TURN_LINE, info), FfmpegUtils.MediaExif.class);
            return CollectionUtils.isEmpty(exif) ? null : exif.getFirst();
        } catch (Exception e) {
            logger.error("getMediaExif error", e);
            return null;
        }
    }
}
