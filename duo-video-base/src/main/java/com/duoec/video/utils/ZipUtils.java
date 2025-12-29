package com.duoec.video.utils;

import com.duoec.base.core.DuoServerConsts;
import com.duoec.base.core.util.FileUtils;
import com.duoec.base.exceptions.DuoServiceException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ZipUtils {
    private ZipUtils() {
    }

    public static void zip(File dir, File zipFile, Set<String> excludeFiles) {
        try (
                FileOutputStream fos = new FileOutputStream(zipFile);
                ZipOutputStream zipOut = new ZipOutputStream(fos);
        ) {
            zipFile(dir, dir.getName(), zipOut, excludeFiles);
        } catch (Exception e) {
            throw new DuoServiceException("压缩失败", e);
        }
    }

    private static void zipFile(File fileToZip, String fileName, ZipOutputStream zipOut, Set<String> excludeFiles) throws IOException {
        String absolutePath = fileToZip.getAbsolutePath();

        // 检查当前文件或目录是否在排除列表中
        if (excludeFiles != null && excludeFiles.contains(absolutePath)) {
            return; // 如果在排除列表中，则跳过
        }
        if (fileToZip.isHidden()) {
            return;
        }
        if (fileToZip.isDirectory()) {
            if (fileName.endsWith(DuoServerConsts.OBLIQUE_LINE_STR)) {
                zipOut.putNextEntry(new ZipEntry(fileName));
                zipOut.closeEntry();
            } else {
                zipOut.putNextEntry(new ZipEntry(fileName + DuoServerConsts.OBLIQUE_LINE_STR));
                zipOut.closeEntry();
            }

            File[] children = fileToZip.listFiles();
            for (File childFile : children) {
                zipFile(childFile, fileName + DuoServerConsts.OBLIQUE_LINE_STR + childFile.getName(), zipOut, excludeFiles);
            }
            return;
        }
        try (FileInputStream fis = new FileInputStream(fileToZip)) {
            ZipEntry zipEntry = new ZipEntry(fileName);
            zipOut.putNextEntry(zipEntry);
            byte[] bytes = new byte[1024];
            int length;
            while ((length = fis.read(bytes)) >= 0) {
                zipOut.write(bytes, 0, length);
            }
        }
    }

    public static void unzip(File zipFile, File unzipDir) {
        if (!unzipDir.exists()) {
            FileUtils.mkdirs(unzipDir);
        }

        try (ZipInputStream zipIn = new ZipInputStream(new FileInputStream(zipFile))) {
            ZipEntry entry = zipIn.getNextEntry();

            while (entry != null) {
                File newFile = newFile(unzipDir, entry);
                if (entry.isDirectory()) {
                    newFile.mkdirs();
                } else {
                    // Ensure parent directories exist
                    File parent = newFile.getParentFile();
                    if (parent != null && !parent.exists()) {
                        parent.mkdirs();
                    }

                    // Write file content
                    try (FileOutputStream fos = new FileOutputStream(newFile)) {
                        byte[] buffer = new byte[1024];
                        int len;
                        while ((len = zipIn.read(buffer)) > 0) {
                            fos.write(buffer, 0, len);
                        }
                    }
                }
                zipIn.closeEntry();
                entry = zipIn.getNextEntry();
            }
        } catch (Exception e) {
            throw new DuoServiceException("解压失败", e);
        }
    }

    private static File newFile(File destinationDir, ZipEntry zipEntry) throws IOException {
        File destFile = new File(destinationDir, zipEntry.getName());

        String destDirPath = destinationDir.getCanonicalPath();
        String destFilePath = destFile.getCanonicalPath();

        if (!destFilePath.startsWith(destDirPath + File.separator)) {
            throw new IOException("Entry is outside of the target dir: " + zipEntry.getName());
        }

        return destFile;
    }
}
