package com.duoec.video.jy.utils;

import com.duoec.base.core.DuoServerConsts;
import com.duoec.base.core.util.FileUtils;
import com.duoec.base.core.util.UrlUtils;
import com.duoec.base.exceptions.DuoServiceException;
import com.duoec.video.jy.JianyingBuilder;
import com.duoec.video.jy.JianyingProjectBuildState;
import com.duoec.video.jy.dto.JyResource;
import com.duoec.video.utils.ZipUtils;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.File;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

public class JianyingResourceUtils {
    private static final Logger logger = LoggerFactory.getLogger(JianyingResourceUtils.class);

    public static final String LOCAL_RESOURCE_PATH = "##_draftpath_placeholder_0E685133-18CE-45ED-8CB8-2904A212EC80_##/Resources/local/";
    public static final String RS_NAME_TEXT_TEMPLATE = "text_template";
    public static final String RS_NAME_IMAGE = "image";
    public static final String RS_NAME_AUDIO = "audio";
    public static final String RS_NAME_VIDEO = "video";
    public static final String RS_NAME_FONTS = "fonts";
    public static final String RS_NAME_FLOWER = "flower";
    public static final String RS_NAME_DEFAULT = "default";
    public static final String RS_BASE_DIR = "rs";

    public static final String PROJECT_DIR = "projects";
    public static final String JY_RESOURCE_LOCAL = "Resources/local";

    public static final String DEFAULT_FONT_NAME = "抖音美好体";
    public static final String DEFAULT_FILL_COLOR = "#00c1cd";
    public static final File JY_TEMP_DIR = new File("tmp");
    public static final File JY_RS_DIR = new File(JY_TEMP_DIR, RS_BASE_DIR);
    public static final File JY_PROJECT_DIR = new File(JY_TEMP_DIR, PROJECT_DIR);
    private static final Map<String, String> FONT_MAP = Maps.newHashMap();

    private static final Map<String, String> RESOURCE_TYPE = Maps.newHashMap();
    public static final String BASE_JY_RESOURCE_URL = "https://api.duoec.com/public/";
    public static final String STR_JSON = ".json";
    public static final String STR_ZIP = ".zip";

    static {
        RESOURCE_TYPE.put(".wav", "audio");
        RESOURCE_TYPE.put(".mp3", "audio");
        RESOURCE_TYPE.put(".otf", "fonts");
        RESOURCE_TYPE.put(".ttf", "fonts");

        resourceDirInit();

        setFontMap(FileUtils.readJson("jy/font.json", Map.class));
    }

    private static void resourceDirInit() {
        if (!JY_TEMP_DIR.exists()) {
            logger.info("创建临时目录：{}", JY_TEMP_DIR);
            JY_TEMP_DIR.mkdirs();
        }

        initDir(JY_TEMP_DIR, RS_BASE_DIR);
        initDir(JY_TEMP_DIR, PROJECT_DIR);
        initDir(JY_RS_DIR, RS_NAME_DEFAULT);
        initDir(JY_RS_DIR, RS_NAME_FONTS);
        initDir(JY_RS_DIR, RS_NAME_VIDEO);
        initDir(JY_RS_DIR, RS_NAME_AUDIO);
        initDir(JY_RS_DIR, RS_NAME_IMAGE);
        initDir(JY_RS_DIR, RS_NAME_TEXT_TEMPLATE);
    }

    private static File initDir(File parentDir, String name) {
        File dir = new File(parentDir, name);
        if (!dir.exists()) {
            if (dir.mkdirs()) {
                logger.info("创建临时目录：{}", dir);
            } else {
                logger.error("创建临时目录失败：{}", dir);
            }
        }
        return dir;
    }

    public static File getFontFile(String fontName) {
        //获取资源路径
        String fontFileUrl = FONT_MAP.get(fontName);
        if (!StringUtils.hasLength(fontFileUrl)) {
            throw new DuoServiceException("没有找到字体【" + fontName + "】的链接");
        }

        String fontFileName = FileUtils.getFileName(fontFileUrl);
        if (!StringUtils.hasLength(fontFileName)) {
            throw new DuoServiceException("字体名称异常！");
        }
        File jyFontDir = new File(JY_RS_DIR, RS_NAME_FONTS);
        File jyResourceFontFile = new File(jyFontDir, UrlUtils.decode(fontFileName));

        if (!jyResourceFontFile.exists()) {
            // 下载
            JianyingBuilder.storageService.download(fontFileUrl, jyResourceFontFile);
            if (!jyResourceFontFile.exists()) {
                throw new DuoServiceException("下载字体文件失败：" + fontFileUrl);
            }
        }
        return jyResourceFontFile;
    }

    public static void setFontMap(Map<String, String> fontMap) {
        if (!CollectionUtils.isEmpty(fontMap)) {
            FONT_MAP.clear();
            FONT_MAP.putAll(fontMap);
        }
    }

    /**
     * 复制文件到剪映本地目录
     * @param state 项目状态
     * @param file 需要复制的文件
     * @param type 文件类型
     * @return 本地目录占位符地址
     */
    public static String copyToLocalResources(JianyingProjectBuildState state, File file, String type) {
        if (!file.exists()) {
            throw new DuoServiceException("需要复制的文件不存在: " + file.getAbsolutePath());
        }
        String fileName = UrlUtils.decode(file.getName());
        if (!StringUtils.hasLength(fileName)) {
            throw new DuoServiceException("无效的文件名：" + file.getAbsolutePath());
        }

        File projectLocalResourceDir = state.getProjectLocalResourceDir();
        String resourceType = StringUtils.hasLength(type) ? type : RS_NAME_DEFAULT;
        File resourceDir = new File(projectLocalResourceDir, resourceType);
        File jyResourceFontFile = new File(resourceDir, fileName);
        //复制到剪映资源目录
        FileUtils.copyFile(file, jyResourceFontFile);

        return LOCAL_RESOURCE_PATH + resourceType + DuoServerConsts.OBLIQUE_LINE_STR + file.getName();
    }

    public static JyResource getJyResource(String resourceType, long resourceId) {
        String rid = resourceType + DuoServerConsts.OBLIQUE_LINE_STR + resourceId + STR_JSON;
        File resourceConfigFile = new File(JianyingResourceUtils.JY_RS_DIR, rid);
        if (!resourceConfigFile.exists()) {
            // 从服务端下载
            String url = BASE_JY_RESOURCE_URL + "jianying/rs/" + rid;
            JianyingBuilder.storageService.download(url, resourceConfigFile);
        }
        return FileUtils.readJson(resourceConfigFile, JyResource.class);
    }

    public static void downloadResources(File localDir, Collection<String> resources) {
        if (CollectionUtils.isEmpty(resources)) {
            return;
        }

        resources.forEach(resourceUrl -> {
            if (!StringUtils.hasLength(resourceUrl)) {
                return;
            }
            String[] items = resourceUrl.split(DuoServerConsts.OBLIQUE_LINE_STR);
            String type = items[items.length - 2];
            File resourceDir = new File(JianyingResourceUtils.JY_RS_DIR, type);
            FileUtils.mkdirs(resourceDir);

            String fileName = items[items.length - 1];
            File resourceFile = new File(resourceDir, fileName);
            if (!resourceFile.exists()) {
                String urlEncodedFileName = UrlUtils.encode(fileName);
                if (!fileName.equals(urlEncodedFileName)) {
                    resourceUrl = resourceUrl.replace(fileName, urlEncodedFileName);
                }
                try {
                    JianyingBuilder.storageService.download(BASE_JY_RESOURCE_URL + resourceUrl, resourceFile);
                } catch (Exception e) {
                    logger.error("剪映资源[{}]下载失败", resourceUrl, e);
                }
            }
            if (!resourceFile.exists()) {
                return;
            }

            if (resourceUrl.toLowerCase().endsWith(STR_ZIP)) {
                // ZIP 文件
                File distDir = new File(localDir, type + DuoServerConsts.OBLIQUE_LINE_STR + fileName.substring(0, fileName.length() - 4));
                if (distDir.exists()) {
                    return;
                }
                ZipUtils.unzip(resourceFile, distDir);

                File dir = distDir.listFiles()[0];
                Arrays.stream(dir.listFiles()).forEach(file -> {
                    file.renameTo(new File(distDir, file.getName()));
                });
                dir.delete();
            } else {
                String fileSuffix = FileUtils.getFileSuffix(fileName);
                String resourceType = RESOURCE_TYPE.get(fileSuffix);
                File srcFontFile;
                if (StringUtils.hasLength(resourceType)) {
                    File localResourceDir = new File(localDir, resourceType);
                    srcFontFile = new File(localResourceDir, DuoServerConsts.OBLIQUE_LINE_STR + fileName);
                } else {
                    srcFontFile = new File(resourceDir, fileName);
                }
                FileUtils.copyFile(resourceFile, srcFontFile);
            }
        });
    }
}
