package com.duoec.video.utils;

import com.duoec.base.core.DuoServerConsts;
import com.duoec.base.exceptions.DuoServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ProcessBuilderUtils {
    private static final Logger logger = LoggerFactory.getLogger(ProcessBuilderUtils.class);

    private ProcessBuilderUtils() {
    }

    /**
     * 执行shell命令并返回输出
     *
     * @param timeoutSeconds 超时时间（秒），0表示不限制
     * @param commands 命令参数
     * @return 命令输出行列表
     * @throws DuoServiceException 如果命令执行失败或超时
     */
    public static List<String> exec(long timeoutSeconds, String... commands) {
        ExecutionResult result = execWithResult(timeoutSeconds, commands);
        if (!result.isSuccess()) {
            throw new DuoServiceException("命令执行失败，exitCode=" + result.exitCode()
                    + ", output=" + String.join("\n", result.output()));
        }
        return result.output();
    }

    /**
     * 执行shell命令并返回详细结果（包括exitCode）
     *
     * @param timeoutSeconds 超时时间（秒），0表示不限制
     * @param commands 命令参数
     * @return 执行结果
     */
    public static ExecutionResult execWithResult(long timeoutSeconds, String... commands) {
        if (commands == null || commands.length == 0) {
            throw new IllegalArgumentException("命令不能为空");
        }

        // 使用数组形式传递命令，避免命令注入
        ProcessBuilder processBuilder = new ProcessBuilder(commands);
        processBuilder.redirectErrorStream(true);

        String cmdStr = String.join(" ", commands);
        logger.info("准备执行命令：{}", cmdStr);

        long startTime = System.currentTimeMillis();
        Process process = null;
        try {
            process = processBuilder.start();

            // 读取输出
            List<String> output = new ArrayList<>();
            try (InputStream inputStream = process.getInputStream();
                 BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    output.add(line);
                }
            }

            // 等待命令执行完成（带超时）
            int exitCode;
            if (timeoutSeconds > 0) {
                boolean finished = process.waitFor(timeoutSeconds, TimeUnit.SECONDS);
                if (!finished) {
                    process.destroyForcibly();
                    long duration = System.currentTimeMillis() - startTime;
                    logger.error("命令执行超时（{}秒）：{}, 耗时：{}ms", timeoutSeconds, cmdStr, duration);
                    throw new DuoServiceException("命令执行超时（" + timeoutSeconds + "秒）：" + cmdStr);
                }
                exitCode = process.exitValue();
            } else {
                exitCode = process.waitFor();
            }

            long duration = System.currentTimeMillis() - startTime;

            if (exitCode == 0) {
                logger.info("命令执行成功，耗时：{}ms，输出行数：{}", duration, output.size());
            } else {
                logger.error("命令执行失败，exitCode={}，耗时：{}ms，命令：{}", exitCode, duration, cmdStr);
            }

            return new ExecutionResult(exitCode, output);

        } catch (IOException e) {
            long duration = System.currentTimeMillis() - startTime;
            logger.error("命令执行出错，耗时：{}ms，命令：{}", duration, cmdStr, e);
            throw new DuoServiceException("命令执行出错：" + cmdStr, e);
        } catch (InterruptedException e) {
            long duration = System.currentTimeMillis() - startTime;
            logger.error("命令执行被中断，耗时：{}ms，命令：{}", duration, cmdStr, e);
            Thread.currentThread().interrupt();
            throw new DuoServiceException("命令执行被中断：" + cmdStr, e);
        } finally {
            if (process != null && process.isAlive()) {
                process.destroyForcibly();
            }
        }
    }

    public static String addQuotationStr(String path) {
        if (!path.startsWith("'") && !path.startsWith("\"")) {
            return DuoServerConsts.SINGLE_QUOTATION_STR + path + DuoServerConsts.SINGLE_QUOTATION_STR;
        }

        return path;
    }

    public static String addQuotationStr(File file) {
        return addQuotationStr(file.getAbsolutePath());
    }

    /**
     * 命令执行结果
     */
    public record ExecutionResult(int exitCode, List<String> output) {
        public boolean isSuccess() {
            return exitCode == 0;
        }
    }
}
