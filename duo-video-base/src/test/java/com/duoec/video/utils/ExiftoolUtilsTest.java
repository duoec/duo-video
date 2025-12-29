package com.duoec.video.utils;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class ExiftoolUtilsTest {

    @Test
    public void getMediaExif() {
        String filePath = "/Users/xuwenzhen/Projects/duoec/duo-www/duo-api/duo-video/duo-video-jy/tmp/rs/video/535010997887571021.mp4";
        File file = new File(filePath);

        // Verify the file exists before proceeding
        assertTrue(file.exists(), "Test file does not exist at expected location: " + file.getAbsolutePath());

        // Call the actual method which might return null if exiftool fails
        ExiftoolUtils.MediaExif exif = ExiftoolUtils.getMediaExif(file);

        // The method returns null silently on errors, so we need to check and provide better feedback
        assertNotNull(exif, "EXIF extraction failed. The file exists but exiftool command might not be working. " +
                          "Make sure exiftool is installed and accessible in the PATH. File: " + file.getAbsolutePath());
    }
}