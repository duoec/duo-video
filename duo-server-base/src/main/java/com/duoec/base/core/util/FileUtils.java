package com.duoec.base.core.util;

import com.duoec.base.core.DuoServerConsts;
import com.duoec.base.exceptions.DuoServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Set;

public class FileUtils {
    private static final Logger logger = LoggerFactory.getLogger(FileUtils.class);

    private static final Set<String> IMAGE_FILES = Set.of(
            "jpg", "png", "jpeg", "heic", "gif"
    );

    private static final Set<String> VIDEO_FILE_TYPE = Set.of(
            "mp4",
            "mov",
            "avi"
    );

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

    public static String readFile(File file) {
        if (!file.isFile()) {
            return null;
        }
        try (FileInputStream inputStream = new FileInputStream(file)) {
            byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes);
            return new String(bytes, StandardCharsets.UTF_8);
        } catch (IOException e) {
            logger.error("读取文件失败: {}", file.getAbsolutePath(), e);
        }
        return null;
    }

    public static void writeFile(String content, String filePath) {
        try (FileOutputStream out = new FileOutputStream(filePath)) {
            out.write(content.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            logger.error("写入文件失败: {}", filePath, e);
        }
    }

    public static void writeFile(byte[] bytes, File file) {
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        try (FileOutputStream out = new FileOutputStream(file)) {
            out.write(bytes);
        } catch (IOException e) {
            logger.error("写入文件失败: {}", file.getAbsolutePath(), e);
        }
    }

    public static <T extends Serializable> List<T> readJsonList(String resource, Class<T> clazz) {
        String content = readResources(resource);
        JsonUtils.getObjectMapper().setDateFormat(new SimpleDateFormat(DateTimeUtils.YYYY_MM_DD_HH_MM_SS));
        return JsonUtils.toObjectList(content, clazz);
    }

    public static <T> T readJson(String resource, Class<T> clazz) {
        String content = readResources(resource);
        JsonUtils.getObjectMapper().setDateFormat(new SimpleDateFormat(DateTimeUtils.YYYY_MM_DD_HH_MM_SS));
        return JsonUtils.toObject(content, clazz);
    }

    public static String readResources(String resource) {
        try (InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(resource)) {
            if (in == null) {
                throw new DuoServiceException("资源未找到：" + resource);
            }
            return new String(in.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new DuoServiceException("读取资源失败：" + resource, e);
        }
    }

    public static <T> T readJson(File jsonFile, Class<T> clazz) {
        String jsonStr = readFile(jsonFile);
        if (StringUtils.hasLength(jsonStr)) {
            JsonUtils.getObjectMapper().setDateFormat(new SimpleDateFormat(DateTimeUtils.YYYY_MM_DD_HH_MM_SS));
            return JsonUtils.toObject(jsonStr, clazz);
        }

        return null;
    }

    public static void copyFile(File srcFile, File distFile) {
        if (distFile.exists()) {
            if (srcFile.getAbsolutePath().equals(distFile.getAbsolutePath())) {
                // 同一个文件，不需要复制
                return;
            }
        }

        mkdirs(distFile.getParentFile());
        try {
            Files.copy(srcFile.toPath(), distFile.toPath(), StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.COPY_ATTRIBUTES);
        } catch (IOException e) {
            throw new DuoServiceException("复制文件失败", e);
        }
    }

    public static void mkdirs(File dir) {
        if (dir.exists()) {
            return;
        }
        mkdirs(dir.getParentFile());
        dir.mkdir();
    }

    /**
     * 获取文件后缀（带点），并转换为小写，比如：.mp3
     * 兼容URL和文件路径
     */
    public static String getFileSuffix(String filePath) {
        int index = filePath.indexOf(DuoServerConsts.ASK_STR);
        if (index != -1) {
            // 去掉后面 带问号的参数
            filePath = filePath.substring(0, index);
        }

        index = filePath.lastIndexOf(DuoServerConsts.DOT_STR);
        if (index == -1) {
            return DuoServerConsts.EMPTY_STR;
        }
        return filePath.substring(index).toLowerCase();
    }

    public static void deletes(File file) {
        if (!file.getAbsolutePath().contains("/duo-api/") && !file.getAbsolutePath().contains("com.lveditor.draft")) {
            //安全起见，不允许删除其它目录的文件
            logger.warn("安全起见，不允许删除不带/duo-api/的文件: {}", file.getAbsolutePath());
            return;
        }
        if (!file.exists()) {
            return;
        }
        if (file.isFile()) {
            file.delete();
        } else if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files != null) {
                for (File f : files) {
                    deletes(f);
                }
            }
            file.delete();
        }
    }


    public static boolean isImageFile(String fileName) {
        int index = fileName.lastIndexOf(DuoServerConsts.DOT_STR);
        if (index == -1) {
            return false;
        }
        String suffix = fileName.substring(index + 1).toLowerCase();
        return IMAGE_FILES.contains(suffix);
    }

    public static boolean isVideoFile(String fileName) {
        int index = fileName.lastIndexOf(DuoServerConsts.DOT_STR);
        if (index == -1) {
            return false;
        }
        String suffix = fileName.substring(index + 1).toLowerCase();
        return VIDEO_FILE_TYPE.contains(suffix);
    }
}
