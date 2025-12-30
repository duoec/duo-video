package com.duoec.video.jy.utils;

import com.duoec.base.exceptions.DuoServiceException;
import com.duoec.video.jy.JianyingBuilder;
import com.duoec.video.jy.dto.TextTemplateDto;
import com.duoec.video.jy.dto.info.Effect;
import com.duoec.video.jy.dto.info.Text;
import com.duoec.video.jy.dto.info.TextTemplate;
import com.duoec.video.utils.ProcessBuilderUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.File;
import java.util.List;

/**
 * 文本模板适配器
 */
public class TextTemplateAdapter {
    private static final Logger logger = LoggerFactory.getLogger(TextTemplateAdapter.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();

    // Go程序路径，可以通过环境变量或配置文件指定
    public static String JY_TEXT_TEMPLATE_ADAPTER_PATH = System.getProperty("jy.text.template.adapter");

    /**
     * 调用jy_text_template_adapter程序获取文本模板
     */
    public static TextTemplateDto getTextTemplate(
            long resourceId,
            List<String> texts,
            String jyResourcePath,
            String projectLocalResourcePath,
            long duration) {

        // 确定要使用的程序路径
        String adapterPath = getAdapterPath();

        if (texts == null || texts.isEmpty()) {
            logger.warn("文本列表为空");
            return null;
        }

        try {
            // 构建命令行参数
            String[] commands = new String[]{
                    adapterPath,
                    "-r", String.valueOf(resourceId),
                    "-t", convertTextsToJson(texts),
                    "-p", jyResourcePath,
                    "-l", projectLocalResourcePath,
                    "-d", String.valueOf(duration) // duration单位为毫秒，直接传递
            };

            // 使用 ProcessBuilderUtils 执行命令，超时时间30秒
            ProcessBuilderUtils.ExecutionResult result = ProcessBuilderUtils.execWithResult(30, commands);

            if (!result.isSuccess()) {
                logger.error("jy_text_template_adapter 执行失败，退出码: {}", result.exitCode());
                return null;
            }

            // 将输出行列表合并为完整的JSON字符串
            String json = String.join("\n", result.output()).trim();
            logger.debug("jy_text_template_adapter JSON输出: {}", json);

            return parseGoOutput(json);

        } catch (Exception e) {
            logger.error("jy_text_template_adapter 失败", e);
            return null;
        }
    }

    private static String getAdapterPath() {
        if (StringUtils.hasLength(JY_TEXT_TEMPLATE_ADAPTER_PATH)) {
            File configuredAdapter = new File(JY_TEXT_TEMPLATE_ADAPTER_PATH);
            if (configuredAdapter.exists() && configuredAdapter.canExecute()) {
                // 如果配置的路径存在且可执行，优先使用（主要用于开发环境）
                logger.debug("使用配置的 jy_text_template_adapter: {}", JY_TEXT_TEMPLATE_ADAPTER_PATH);
                return JY_TEXT_TEMPLATE_ADAPTER_PATH;
            }
        }
        String adapterPath;
        // 否则使用下载的版本
        File jyTextTemplateAdapter = new File(JianyingResourceUtils.JY_TEMP_DIR, "jy_text_template_adapter");
        if (!jyTextTemplateAdapter.exists()) {
            // 根据当前的操作系统，下载对应的程序
            downloadTextTemplateAdapter(jyTextTemplateAdapter);
        }
        adapterPath = jyTextTemplateAdapter.getAbsolutePath();
        logger.debug("使用下载的 jy_text_template_adapter: {}", adapterPath);
        return adapterPath;
    }

    /**
     * 将文本列表转换为JSON数组格式
     */
    private static String convertTextsToJson(List<String> texts) {
        try {
            return objectMapper.writeValueAsString(texts);
        } catch (Exception e) {
            logger.error("转换文本列表为JSON失败", e);
            return "[]";
        }
    }

    /**
     * 解析jy_text_template_adapter 的JSON输出
     */
    private static TextTemplateDto parseGoOutput(String json) {
        try {
            JsonNode root = objectMapper.readTree(json);

            // 解析text
            Text text = null;
            if (root.has("text")) {
                text = objectMapper.treeToValue(root.get("text"), Text.class);
            }

            // 解析text_template
            TextTemplate textTemplate = null;
            if (root.has("text_template")) {
                textTemplate = objectMapper.treeToValue(root.get("text_template"), TextTemplate.class);
            }

            // 解析effect
            Effect effect = null;
            if (root.has("effect")) {
                effect = objectMapper.treeToValue(root.get("effect"), Effect.class);
            }

            return new TextTemplateDto(text, textTemplate, effect, root.get("version").asText());
        } catch (Exception e) {
            logger.error("解析jy_text_template_adapter JSON输出失败", e);
            return null;
        }
    }

    /**
     * 根据当前操作系统和架构下载对应的 jy_text_template_adapter 程序
     */
    private static void downloadTextTemplateAdapter(File targetFile) {
        try {
            // 检测操作系统和架构
            String osName = System.getProperty("os.name").toLowerCase();
            String osArch = System.getProperty("os.arch").toLowerCase();

            String programName;

            if (osName.contains("win")) {
                // Windows 系统
                if (osArch.contains("amd64") || osArch.contains("x86_64")) {
                    programName = "jy_text_template_adapter_windows_amd64.exe";
                } else {
                    programName = "jy_text_template_adapter_windows_386.exe";
                }
            } else if (osName.contains("mac") || osName.contains("darwin")) {
                // macOS 系统
                if (osArch.contains("aarch64") || osArch.contains("arm64")) {
                    programName = "jy_text_template_adapter_darwin_arm64";
                } else {
                    programName = "jy_text_template_adapter_darwin_amd64";
                }
            } else {
                // Linux 系统
                if (osArch.contains("aarch64") || osArch.contains("arm64")) {
                    programName = "jy_text_template_adapter_linux_arm64";
                } else {
                    programName = "jy_text_template_adapter_linux_amd64";
                }
            }

            String downloadUrl = JianyingResourceUtils.BASE_JY_RESOURCE_URL + programName;
            logger.info("开始下载 jy_text_template_adapter: {} -> {}", downloadUrl, targetFile.getAbsolutePath());

            // 下载文件
            JianyingBuilder.storageService.download(downloadUrl, targetFile);

            // 对于 Unix 系统（Mac/Linux），设置可执行权限
            if (!osName.contains("win")) {
                boolean executable = targetFile.setExecutable(true, false);
                if (executable) {
                    logger.info("已设置文件可执行权限: {}", targetFile.getAbsolutePath());
                } else {
                    logger.warn("设置文件可执行权限失败: {}", targetFile.getAbsolutePath());
                }
            }

            logger.info("jy_text_template_adapter 下载成功: {}", targetFile.getAbsolutePath());

        } catch (Exception e) {
            logger.error("下载 jy_text_template_adapter 失败", e);
            throw new DuoServiceException("下载 jy_text_template_adapter 失败: " + e.getMessage(), e);
        }
    }
}
