package com.duoec.video.utils;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class ExiftoolUtilsTest {

    @Test
    void getMediaExif() {
        String mp4VideoUrl = "https://api.duoec.com/public/video/535010997887571021.mp4";
        String movVideoUrl = "https://api.duoec.com/public/a287119d7b36dcc.mp4";
        String pngVideoUrl = "https://api.duoec.com/public/greenScreen/d8a0e31b50166b6219b1df1dbb90e284.png";
        String jpgVideoUrl = "https://api.duoec.com/public/www/jy_resource_list.jpg";
        String mp3VideoUrl = "https://api.duoec.com/public/audio/535010997887571025.mp3";

        testMediaExif(mp4VideoUrl);
        testMediaExif(movVideoUrl);
        testMediaExif(pngVideoUrl);
        testMediaExif(jpgVideoUrl);
        testMediaExif(mp3VideoUrl);
    }

    private void testMediaExif(String url) {
        File tmpDir = new File("tmp");
        FileUtils.mkdirs(tmpDir);

        String fileName = FileUtils.getFileName(url);

        File mediaFile = new File(tmpDir, fileName);
        DownloadUtils.download(url, mediaFile);
        assertTrue(mediaFile.exists());

        FfmpegUtils.MediaExif exif = ExiftoolUtils.getMediaExif(mediaFile);
        assertNotNull(exif);

        FfmpegUtils.MediaExif exifFromFfmpeg = FfmpegUtils.getMediaExif(mediaFile);
        assertNotNull(exifFromFfmpeg);

        // 打印对比表格
        System.out.println("\n文件: " + fileName);
        System.out.println("+------------+----------+--------------+");
        System.out.println("| 属性       | exiftool | ffmpeg       |");
        System.out.println("+------------+----------+--------------+");
        System.out.printf("| 宽度       | %-8s | %-12s |\n",
            exif.getWidth() != null ? exif.getWidth().toString() : "null",
            exifFromFfmpeg.getWidth() != null ? exifFromFfmpeg.getWidth().toString() : "null");
        System.out.printf("| 高度       | %-8s | %-12s |\n",
            exif.getHeight() != null ? exif.getHeight().toString() : "null",
            exifFromFfmpeg.getHeight() != null ? exifFromFfmpeg.getHeight().toString() : "null");
        System.out.printf("| 持续时间   | %-8s | %-12s |\n",
            exif.getDuration() != null ? exif.getDuration().toString() : "null",
            exifFromFfmpeg.getDuration() != null ? exifFromFfmpeg.getDuration().toString() : "null");
        System.out.println("+------------+----------+--------------+");

//        if (exif.getDuration() != null) {
//            assertEquals(exif.getDuration(), exifFromFfmpeg.getDuration());
//        }
//        if (exif.getWidth() != null) {
//            assertEquals(exif.getWidth(), exifFromFfmpeg.getWidth());
//        }
//        if (exif.getHeight() != null) {
//            assertEquals(exif.getHeight(), exifFromFfmpeg.getHeight());
//        }
    }
}