package com.duoec.video.jy.service.impl;

import com.duoec.base.core.DuoServerConsts;
import com.duoec.base.core.util.DateTimeUtils;
import com.duoec.base.core.util.FileUtils;
import com.duoec.base.exceptions.DuoServiceException;
import com.duoec.video.jy.service.StorageService;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

@Service
public class StorageServiceImpl implements StorageService {
    private static final Logger logger = LoggerFactory.getLogger(StorageServiceImpl.class);

    private static final int MAX_CORE_THREAD = 10;
    private static final Map<Long, BatchDownloadTask> TASK_MAP = new ConcurrentHashMap<>();

    private static final Executor EXECUTOR = Executors.newFixedThreadPool(MAX_CORE_THREAD, r -> {
        String traceId = UUID.randomUUID().toString().substring(0, 8);
        Thread t = new Thread(r);
        t.setName(traceId);
        return t;
    });


    @Override
    public void asyncDownload(Long taskId, String url, File file) {
        BatchDownloadTask batchDownloadTask = TASK_MAP.computeIfAbsent(taskId, k -> new BatchDownloadTask());
        DownloadTask task = batchDownloadTask.addTask(url, file);

        //发起任务
        ThreadPoolExecutor executor = ((ThreadPoolExecutor) this.EXECUTOR);
        if (executor.getActiveCount() > MAX_CORE_THREAD - 2) {
            logger.warn("下载线程池即将用完：{}/{}", executor.getActiveCount(), MAX_CORE_THREAD);
        }

        this.EXECUTOR.execute(() -> startDownload(batchDownloadTask, task));
    }

    @Override
    public boolean waitAsyncDownloadTask(Long taskId) {
        BatchDownloadTask batchDownloadTask = TASK_MAP.get(taskId);
        if (batchDownloadTask == null) {
            return true;
        }

        int maxWaitCount = 3000; //等待5分钟
        while (true) {
            if (maxWaitCount < 0) {
                StringBuilder sb = new StringBuilder("下载失败：\n");
                batchDownloadTask.tasks.stream().filter(task -> task.downloadStatus != 0).forEach(task -> {
                    sb.append(DuoServerConsts.TAB_STR).append(task.url).append(DuoServerConsts.TURN_LINE);
                });
                logger.error(sb.toString());
                break;
            }
            int status = batchDownloadTask.getDownloadStatus();
            if (status == 0) {
                //不没有下载完成，等待100ms
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    logger.error("休眠失败", e);
                }
                --maxWaitCount;
            } else {
                return status == 1;
            }
        }
        throw new DuoServiceException("下载失败！");
    }

    private void startDownload(BatchDownloadTask batchTask, DownloadTask task) {
        File file = task.file;
        if (file.exists()) {
            task.downloadStatus = 1;
            return;
        }

        try {
            download(task.url, task.file);
            task.downloadStatus = 1;
        } catch (Exception e) {
            task.retryCount = 2;
            task.downloadStatus = -1;
        }
    }

    @Override
    public void download(String url, File targetFile) {
        download(url, targetFile, 0);
    }

    private void download(String url, File distFile, int retryCount) {
        File parentDir = distFile.getParentFile();
        FileUtils.mkdirs(parentDir);

        long t = System.currentTimeMillis();
        url = getDownloadUrl(url);

        File tmpFile = new File(parentDir, UUID.randomUUID().toString() + ".tmp");

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

    private String getDownloadUrl(String url) {
        //检查是否是压缩视频，可兼容：
        int index = url.indexOf("_low.");
        if (index > -1) {
            url = url.substring(0, index) + url.substring(index + 4);
        }

        return url;
    }

    private class BatchDownloadTask {
        private List<DownloadTask> tasks;

        public BatchDownloadTask() {
            this.tasks = Lists.newArrayList();
        }

        public DownloadTask addTask(String url, File filePath) {
            DownloadTask task = new DownloadTask(url, filePath);
            tasks.add(task);
            return task;
        }

        public int getDownloadStatus() {
            for (DownloadTask task : tasks) {
                if (task.downloadStatus == -1) {
                    return -1;
                } else if (task.downloadStatus == 0) {
                    return 0;
                }
            }
            return 1;
        }
    }

    private static class DownloadTask {
        private final String url;

        private final File file;

        private int retryCount = 0;

        /**
         * 下载状态：-1=下载失败 0=初始化 1=下载成功
         */
        private Integer downloadStatus;

        public DownloadTask(String url, File file) {
            this.url = url;
            this.file = file;
            downloadStatus = 0;
        }
    }
}
