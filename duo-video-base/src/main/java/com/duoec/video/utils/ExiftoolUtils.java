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

    public static MediaExif getMediaExif(File file) {
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
            List<MediaExif> exif = JsonUtils.toObjectList(String.join(DuoServerConsts.TURN_LINE, info), MediaExif.class);
            return CollectionUtils.isEmpty(exif) ? null : exif.getFirst();
        } catch (Exception e) {
            logger.error("getMediaExif error", e);
            return null;
        }
    }

    @Data
    public static class MediaExif {
        @JsonProperty("ImageWidth")
        private Integer width;

        @JsonProperty("ImageHeight")
        private Integer height;

        private Long duration; // 存储为毫秒

        @JsonProperty("Duration")
        private void setDuration(String durationStr) {
            if (durationStr != null) {
                try {
                    // 将秒转换为毫秒（保留小数部分，转换为整数毫秒）
                    double durationSecs = Double.parseDouble(durationStr);
                    this.duration = Math.round(durationSecs * 1000);
                } catch (NumberFormatException e) {
                    // 如果解析失败，设置为 0
                    this.duration = 0L;
                }
            }
        }

        @JsonProperty("Model")
        private String model;

        @JsonProperty("Model-zho")
        private String modelZho;

        @JsonProperty("GPSPosition")
        private String gpsPosition;
    }
}
