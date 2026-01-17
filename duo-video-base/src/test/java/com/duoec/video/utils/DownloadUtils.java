package com.duoec.video.utils;

import com.duoec.base.core.util.DateTimeUtils;
import com.duoec.base.core.util.FileUtils;
import com.duoec.base.exceptions.DuoServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.UUID;

public class DownloadUtils {
    private static final Logger logger = LoggerFactory.getLogger(DownloadUtils.class);

    public static void download(String url, File targetFile) {
        download(url, targetFile, 0);
    }

    private static void download(String url, File distFile, int retryCount) {
        File parentDir = distFile.getParentFile();
        FileUtils.mkdirs(parentDir);

        long t = System.currentTimeMillis();

        File tmpFile = new File(parentDir, UUID.randomUUID() + ".tmp");

        // 回退到HTTP下载
        try {
            URLConnection connection = new URL(url).openConnection();
            try (
                    InputStream in = connection.getInputStream();
                    FileOutputStream out = new FileOutputStream(tmpFile)
            ) {
                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = in.read(buffer)) != -1) {
                    out.write(buffer, 0, bytesRead);
                }
            }
            if (tmpFile.renameTo(distFile)) {
                logger.info("{}，[HTTP]耗时：{}", url, DateTimeUtils.getTimeDisplay(t));
            } else {
                throw new DuoServiceException("临时文件[" + tmpFile.getAbsolutePath() + "]重命名[" + distFile.getAbsolutePath() + "]失败，耗时：" + DateTimeUtils.getTimeDisplay(t));
            }
        } catch (Exception e) {
            logger.error("下载失败：{}", url, e);
            if (tmpFile.exists()) {
                tmpFile.delete();
            }

            //重试
            if (retryCount < 3) {
                download(url, distFile, retryCount + 1);
            } else {
                throw new DuoServiceException("下载失败：" + url, e);
            }
        }
    }
}
