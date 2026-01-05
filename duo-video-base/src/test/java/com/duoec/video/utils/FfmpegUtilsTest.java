package com.duoec.video.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class FfmpegUtilsTest {

    @Test
    void upend() {
        File srcFile = new File("/Users/xuwenzhen/Downloads/duo-video-export.mp4");
        File targetFile = new File("/Users/xuwenzhen/Downloads/u.mp4");
        FfmpegUtils.upend(srcFile, targetFile);
        Assertions.assertTrue(targetFile.exists());
    }
}