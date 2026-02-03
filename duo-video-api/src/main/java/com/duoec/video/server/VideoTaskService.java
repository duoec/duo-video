package com.duoec.video.server;

import com.duoec.video.dto.request.VideoTaskCreateRequest;
import com.duoec.video.dto.request.VideoTaskRequest;
import com.duoec.video.dto.request.VideoTaskUpdateRequest;
import com.duoec.video.dto.response.VideoTaskDetail;
import com.duoec.video.dto.response.VideoTaskResponse;

public interface VideoTaskService {
    /**
     * 添加视频创作任务
     * @param request 任务创建请求
     * @return 返回任务ID
     */
    long addTask(VideoTaskCreateRequest request);

    /**
     * 获取一个创作任务（用于创作）
     */
    VideoTaskResponse getTask(VideoTaskRequest request);

    /**
     * 更新创作任务状态
     * @param request 创作任务变更请求
     */
    void updateTaskState(VideoTaskUpdateRequest request);

    /**
     * 获取视频任务详情
     * @param taskId 任务ID
     * @return 视频任务详情
     */
    VideoTaskDetail getTaskDetail(long taskId);
}
