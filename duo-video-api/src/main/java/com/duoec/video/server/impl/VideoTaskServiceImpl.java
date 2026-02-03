package com.duoec.video.server.impl;

import com.duoec.base.core.util.FileUtils;
import com.duoec.base.core.util.JsonUtils;
import com.duoec.base.core.util.SnowflakeIdUtils;
import com.duoec.base.exceptions.DuoServiceException;
import com.duoec.video.dto.request.VideoTaskCreateRequest;
import com.duoec.video.dto.request.VideoTaskRequest;
import com.duoec.video.dto.request.VideoTaskUpdateRequest;
import com.duoec.video.dto.response.VideoTaskDetail;
import com.duoec.video.dto.response.VideoTaskResponse;
import com.duoec.video.server.VideoTaskService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.Arrays;
import java.util.Comparator;

/**
 * 当前仅是简单的实现，生产环境下，需要更完善的实现！！
 * 使用目录存储所有文件，按任务状态组织目录：
 *  tmp/task_{taskStatus}/{taskId}.json
 */
@Service
@RequiredArgsConstructor
public class VideoTaskServiceImpl implements VideoTaskService {

    // Task status constants
    public static final int TASK_FAILED = -11;
    public static final int TASK_CANCELLED = -10;
    public static final int TASK_WAITING = 0;
    public static final int TASK_RECEIVED = 10;
    public static final int TASK_DOWNLOAD_COMPLETE = 11;
    public static final int TASK_EXTRACT_COMPLETE = 12;
    public static final int TASK_PROJECT_OPENED = 13;
    public static final int TASK_VIDEO_GENERATING = 14;
    public static final int TASK_EXPORT_COMPLETE = 100;

    public static final int[] PROCESSING_STATUSES = new int[]{TASK_RECEIVED, TASK_DOWNLOAD_COMPLETE, TASK_EXTRACT_COMPLETE, TASK_PROJECT_OPENED, TASK_VIDEO_GENERATING};
    public static final int[] STATUSES = new int[]{TASK_FAILED, TASK_CANCELLED, TASK_WAITING, TASK_RECEIVED,
            TASK_DOWNLOAD_COMPLETE, TASK_EXTRACT_COMPLETE, TASK_PROJECT_OPENED, TASK_VIDEO_GENERATING, TASK_EXPORT_COMPLETE};

    private final static String BASE_DIR = "tmp";

    @PostConstruct
    public void init() throws IOException {
        // Create base directory and status subdirectories
        Path basePath = Paths.get(BASE_DIR);

        // Create directories for each status using status % 100 for directory naming
        // Handle negative mod results by converting to positive equivalent
        Arrays.stream(STATUSES).map(this::mod).forEach(dirIndex -> {
            try {
                FileUtils.mkdirs(basePath.resolve("task_" + dirIndex));
            } catch (Exception e) {
                throw new DuoServiceException("创建目录失败");
            }
        });
    }

    /**
     * Calculate modulo that always returns a positive value
     */
    private int mod(int x) {
        return (x - x % 10) % 1000;
    }

    /**
     * 添加视频创作任务
     * @param request 任务创建请求
     * @return 返回任务ID
     */
    @Override
    public long addTask(VideoTaskCreateRequest request) {
        try {
            long taskId = SnowflakeIdUtils.nextTmpId();

            // Create task detail object
            VideoTaskDetail taskDetail = new VideoTaskDetail();
            taskDetail.setTaskId(taskId);
            taskDetail.setVideoId(request.getVideoId());
            taskDetail.setPriority(request.getPriority() != null ? request.getPriority() : 1000); // default priority
            taskDetail.setJyZipUrl(request.getJyZipUrl());
            taskDetail.setStatus(TASK_WAITING);
            taskDetail.setRetryCount(0); // Initialize retry count
            taskDetail.setTaskUpdateTime(Instant.now().toEpochMilli());

            // Save task to the waiting directory
            saveTaskToFile(taskDetail, TASK_WAITING);

            return taskId;
        } catch (IOException e) {
            throw new RuntimeException("Failed to add task", e);
        }
    }

    /**
     * 获取一个创作任务（用于创作）
     */
    @Override
    public VideoTaskResponse getTask(VideoTaskRequest request) {
        try {
            // Find the oldest waiting task (by creation time/priority)
            File waitingDir = new File(BASE_DIR, "task_" + mod(TASK_WAITING));
            File[] taskFiles = waitingDir.listFiles((dir, name) -> name.endsWith(".json"));

            if (taskFiles == null || taskFiles.length == 0) {
                return null; // No waiting tasks
            }

            // Sort by file name (which contains task ID) to get the oldest task first
            Arrays.sort(taskFiles, Comparator.comparing(f -> Long.parseLong(
                    f.getName().substring(0, f.getName().lastIndexOf('.')))));

            // Take the first task (oldest)
            File taskFile = taskFiles[0];
            String taskIdStr = taskFile.getName().substring(0, taskFile.getName().lastIndexOf('.'));
            long taskId = Long.parseLong(taskIdStr);

            // Load the task
            VideoTaskDetail taskDetail = loadTaskFromFile(taskId, TASK_WAITING);
            if (taskDetail == null) {
                return null; // Task might have been removed concurrently
            }

            // Update task status to received and assign server
            taskDetail.setStatus(TASK_RECEIVED);
            taskDetail.setServerName(request.getServerName());
            taskDetail.setTaskFetchTime(Instant.now().toEpochMilli());
            taskDetail.setTaskUpdateTime(Instant.now().toEpochMilli());
            // Ensure retry count is initialized if not already set
            if (taskDetail.getRetryCount() == null) {
                taskDetail.setRetryCount(0);
            }

            // Move task from waiting to received directory
            updateTaskStatus(taskDetail, TASK_WAITING, TASK_RECEIVED);

            // Return simplified response
            VideoTaskResponse response = new VideoTaskResponse();
            response.setTaskId(taskDetail.getTaskId());
            response.setVideoId(taskDetail.getVideoId());
            response.setJyZipUrl(taskDetail.getJyZipUrl());

            return response;
        } catch (Exception e) {
            throw new RuntimeException("Failed to get task", e);
        }
    }

    /**
     * 获取视频任务详情
     */
    @Override
    public VideoTaskDetail getTaskDetail(long taskId) {
        try {
            // Check all status directories for the task
            for (int status : STATUSES) {
                VideoTaskDetail taskDetail = loadTaskFromFile(taskId, status);
                if (taskDetail != null) {
                    return taskDetail;
                }
            }

            return null; // Task not found
        } catch (Exception e) {
            throw new RuntimeException("Failed to get task detail", e);
        }
    }

    /**
     * 更新创作任务状态
     * @param request 创作任务变更请求
     */
    @Override
    public void updateTaskState(VideoTaskUpdateRequest request) {
        try {
            VideoTaskDetail taskDetail = null;
            int currentStatus = -100; // Invalid status

            for (int status : STATUSES) {
                taskDetail = loadTaskFromFile(request.getTaskId(), status);
                if (taskDetail != null) {
                    currentStatus = status;
                    break;
                }
            }

            if (taskDetail == null) {
                throw new RuntimeException("Task not found: " + request.getTaskId());
            }

            // Update task fields based on the request
            if (request.getStatus() != null) {
                taskDetail.setStatus(request.getStatus());

                // Update finish time if task is completed or failed
                if (request.getStatus() == TASK_EXPORT_COMPLETE || request.getStatus() == TASK_FAILED) {
                    taskDetail.setTaskFinishTime(Instant.now().toEpochMilli());
                }
            }

            if (request.getInfo() != null) {
                taskDetail.setTaskInfo(request.getInfo());
            }

            if (request.getVideoUrl() != null) {
                taskDetail.setVideoUrl(request.getVideoUrl());
            }

            if (request.getServerName() != null) {
                taskDetail.setServerName(request.getServerName());
            }

            taskDetail.setTaskUpdateTime(Instant.now().toEpochMilli());

            // If status changed, move the file to the new status directory
            if (request.getStatus() != null && request.getStatus() != currentStatus) {
                updateTaskStatus(taskDetail, currentStatus, request.getStatus());
            } else {
                // Just update the file in the current directory
                saveTaskToFile(taskDetail, currentStatus);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to update task state", e);
        }
    }

    /**
     * Save task detail to file in the specified status directory
     */
    private void saveTaskToFile(VideoTaskDetail taskDetail, int status) throws IOException {
        String fileName = taskDetail.getTaskId() + ".json";
        File file = new File(BASE_DIR + "/task_" + mod(status) + "/" + fileName);
        FileUtils.writeFile(JsonUtils.toJsonString(taskDetail).getBytes(StandardCharsets.UTF_8), file);
    }

    /**
     * Load task detail from file in the specified status directory
     */
    private VideoTaskDetail loadTaskFromFile(long taskId, int status) throws IOException {
        String fileName = taskId + ".json";
        Path filePath = Paths.get(BASE_DIR, "task_" + mod(status), fileName);

        if (!Files.exists(filePath)) {
            return null;
        }

        return JsonUtils.getObjectMapper().readValue(filePath.toFile(), VideoTaskDetail.class);
    }

    /**
     * Update task status by moving file from old status directory to new status directory
     */
    private void updateTaskStatus(VideoTaskDetail taskDetail, int oldStatus, int newStatus) throws IOException {
        // Remove from old directory
        String fileName = taskDetail.getTaskId() + ".json";
        Path oldFilePath = Paths.get(BASE_DIR, "task_" + mod(oldStatus), fileName);

        if (Files.exists(oldFilePath)) {
            Files.delete(oldFilePath);
        }

        // Save to new directory
        saveTaskToFile(taskDetail, newStatus);
    }

    /**
     * 定时任务：处理超时的任务
     * 将超过 20 分钟未完成的进行中任务（status > 0 && status < 100）的任务，
     * 如果 retryCount >= 2 时，设置为失败；否则放回 task_0/ 下，并将 retryCount + 1
     */
    @Scheduled(fixedRate = 60000) // 每分钟执行一次
    public void handleTimeoutTasks() {
        try {
            // 查找所有进行中的任务（status > 0 && status < 100）
            for (int status : PROCESSING_STATUSES) {
                File statusDir = new File(BASE_DIR, "task_" + mod(status));
                File[] taskFiles = statusDir.listFiles((dir, name) -> name.endsWith(".json"));

                if (taskFiles == null || taskFiles.length == 0) {
                    continue;
                }

                long currentTime = Instant.now().toEpochMilli();
                long timeoutThreshold = 20 * 60 * 1000L; // 20分钟 = 20 * 60 * 1000 毫秒

                for (File taskFile : taskFiles) {
                    try {
                        // 解析任务ID
                        String taskIdStr = taskFile.getName().substring(0, taskFile.getName().lastIndexOf('.'));
                        long taskId = Long.parseLong(taskIdStr);

                        // 加载任务详情
                        VideoTaskDetail taskDetail = loadTaskFromFile(taskId, status);
                        if (taskDetail == null) {
                            continue; // 任务可能已被其他进程处理
                        }

                        // 检查任务是否超时（基于最后更新时间）
                        if (taskDetail.getTaskUpdateTime() != null &&
                                (currentTime - taskDetail.getTaskUpdateTime()) > timeoutThreshold) {

                            // 根据重试次数决定处理方式
                            int currentRetryCount = taskDetail.getRetryCount() != null ? taskDetail.getRetryCount() : 0;

                            if (currentRetryCount >= 2) {
                                // 重试次数达到上限，设置为失败
                                taskDetail.setStatus(TASK_FAILED);
                                taskDetail.setTaskInfo("Task failed due to timeout after " + currentRetryCount + " retries");
                                taskDetail.setTaskUpdateTime(currentTime);

                                // 移动到失败目录
                                updateTaskStatus(taskDetail, status, TASK_FAILED);
                            } else {
                                // 增加重试次数，放回等待队列
                                taskDetail.setRetryCount(currentRetryCount + 1);
                                taskDetail.setStatus(TASK_WAITING);
                                taskDetail.setTaskInfo("Task returned to queue after timeout, retry count: " + (currentRetryCount + 1));
                                taskDetail.setTaskUpdateTime(currentTime);
                                taskDetail.setServerName(null); // 清除服务器分配

                                // 移动到等待目录
                                updateTaskStatus(taskDetail, status, TASK_WAITING);
                            }
                        }
                    } catch (Exception e) {
                        // 记录错误但继续处理其他任务
                        System.err.println("Error processing timeout task: " + e.getMessage());
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Error in handleTimeoutTasks: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // For testing purposes only - allows direct manipulation of task files
    protected void saveTaskToFileForTest(long taskId, VideoTaskDetail taskDetail, int status) {
        try {
            updateTaskStatus(taskDetail, taskDetail.getStatus(), status);
        } catch (IOException e) {
            throw new RuntimeException("Failed to update task status for test", e);
        }
    }
}
