package com.duoec.video.utils;

import com.duoec.base.core.DuoServerConsts;

import java.io.File;

public class FileUtils {
    public static void mkdirs(File dir) {
        if (dir.exists()) {
            return;
        }
        mkdirs(dir.getParentFile());
        dir.mkdir();
    }

    public static String getFileName(String url) {
        if (url == null) {
            return DuoServerConsts.EMPTY_STR;
        }
        int index = url.indexOf(DuoServerConsts.ASK_STR);
        if (index != -1) {
            url = url.substring(0, index);
        }
        index = url.lastIndexOf(DuoServerConsts.OBLIQUE_LINE_STR);
        if (index != -1) {
            url = url.substring(index + 1);
        }
        return url;
    }
}
