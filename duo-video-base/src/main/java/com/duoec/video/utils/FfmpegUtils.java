package com.duoec.video.utils;

import com.duoec.base.core.DuoServerConsts;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
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

    public static MediaExif getMediaExif(File file) {
        try {
            if (file == null) {
                return null;
            }

            // 使用 ffprobe 获取媒体信息
            List<String> info = ProcessBuilderUtils.exec(
                    10,
                    "ffprobe",
                    "-v", "quiet",           // 静默模式，不输出处理信息
                    "-print_format", "json",  // 输出格式为JSON
                    "-show_format",          // 显示格式信息
                    "-select_streams", "v:0", // 选择第一个视频流
                    "-show_streams",         // 显示流信息
                    file.getAbsolutePath()
            );

            String jsonString = String.join("\n", info);
            return parseMediaExifFromFfprobe(jsonString);
        } catch (Exception e) {
            logger.error("getMediaExifByFfmpeg error", e);
            return null;
        }
    }

    private static MediaExif parseMediaExifFromFfprobe(String jsonString) {
        try {
            // 使用 Jackson ObjectMapper 解析 JSON
            com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();
            java.util.Map<String, Object> rootMap = mapper.readValue(jsonString, java.util.Map.class);

            MediaExif exif = new MediaExif();

            // 提取 streams 信息
            Object streamsObj = rootMap.get("streams");
            if (streamsObj instanceof java.util.List) {
                java.util.List<?> streams = (java.util.List<?>) streamsObj;

                // 遍历流，查找视频流
                for (Object streamObj : streams) {
                    if (streamObj instanceof java.util.Map) {
                        java.util.Map<?, ?> stream = (java.util.Map<?, ?>) streamObj;

                        String codecType = (String) stream.get("codec_type");
                        if ("video".equals(codecType)) {
                            // 获取视频尺寸信息
                            // 尝试从多个可能的来源获取尺寸信息
                            Object widthObj = null;
                            Object heightObj = null;

                            // 首先尝试从 tags 中获取 DisplayWidth 和 DisplayHeight
                            Object tagsObj = stream.get("tags");
                            if (tagsObj instanceof java.util.Map) {
                                java.util.Map<?, ?> tags = (java.util.Map<?, ?>) tagsObj;

                                widthObj = tags.get("DisplayWidth");
                                heightObj = tags.get("DisplayHeight");

                                // 如果标签中没有显示尺寸，则使用编码尺寸
                                if (widthObj == null || heightObj == null) {
                                    widthObj = stream.get("coded_width");
                                    if (widthObj == null) {
                                        widthObj = stream.get("width");
                                    }

                                    heightObj = stream.get("coded_height");
                                    if (heightObj == null) {
                                        heightObj = stream.get("height");
                                    }
                                }
                            } else {
                                // 如果没有 tags，直接使用编码尺寸
                                widthObj = stream.get("coded_width");
                                if (widthObj == null) {
                                    widthObj = stream.get("width");
                                }

                                heightObj = stream.get("coded_height");
                                if (heightObj == null) {
                                    heightObj = stream.get("height");
                                }
                            }

                            // 检查是否有旋转信息，影响宽高的展示
                            Object sideDataList = stream.get("side_data_list");
                            if (sideDataList instanceof java.util.List) {
                                java.util.List<?> sideDataArray = (java.util.List<?>) sideDataList;
                                for (Object sideDataObj : sideDataArray) {
                                    if (sideDataObj instanceof java.util.Map) {
                                        java.util.Map<?, ?> sideData = (java.util.Map<?, ?>) sideDataObj;
                                        String rotation = (String) sideData.get("rotation");
                                        if (rotation != null && (rotation.equals("90") || rotation.equals("-270"))) {
                                            // 如果视频旋转了90度，则交换宽高
                                            Object temp = widthObj;
                                            widthObj = heightObj;
                                            heightObj = temp;
                                            break;
                                        }
                                    }
                                }
                            }

                            if (widthObj != null) {
                                exif.setWidth(Integer.parseInt(widthObj.toString()));
                            }
                            if (heightObj != null) {
                                exif.setHeight(Integer.parseInt(heightObj.toString()));
                            }

                            // 找到视频流后跳出循环
                            break;
                        }
                    }
                }
            }

            // 提取 format 信息中的持续时间
            Object formatObj = rootMap.get("format");
            if (formatObj instanceof java.util.Map) {
                java.util.Map<?, ?> format = (java.util.Map<?, ?>) formatObj;

                Object durationObj = format.get("duration");
                if (durationObj != null) {
                    try {
                        // 将秒转换为毫秒
                        double durationSecs = Double.parseDouble(durationObj.toString());
                        exif.setDuration(String.valueOf(durationSecs));
                    } catch (NumberFormatException e) {
                        // 如果解析失败，设置为 0
                        exif.duration = 0L;
                    }
                }
            }

            return exif;
        } catch (Exception e) {
            logger.error("parseMediaExifFromFfprobe error", e);
            return null;
        }
    }



    @Data
    public static class MediaExif {
        @JsonProperty("ImageWidth")
        private Integer width;

        @JsonProperty("ImageHeight")
        private Integer height;

        /**
         * 时长，单位：毫秒
         */
        private Long duration;

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
    }
}
