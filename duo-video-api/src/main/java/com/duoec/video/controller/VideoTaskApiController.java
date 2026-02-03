package com.duoec.video.controller;

import com.duoec.base.dto.response.BaseResponse;
import com.duoec.video.dto.request.VideoTaskCreateRequest;
import com.duoec.video.dto.request.VideoTaskRequest;
import com.duoec.video.dto.request.VideoTaskUpdateRequest;
import com.duoec.video.dto.response.VideoTaskDetail;
import com.duoec.video.dto.response.VideoTaskResponse;
import com.duoec.video.server.VideoTaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @chapter 视频
 * @section 视频任务
 */
@RestController
@RequestMapping("/api/video-task")
@RequiredArgsConstructor
public class VideoTaskApiController {
    private final VideoTaskService videoTaskService;

    /**
     * 拉取视频任务
     * @param request 视频任务拉取请求
     */
    @GetMapping
    public BaseResponse<VideoTaskResponse> getTask(VideoTaskRequest request) {
        return BaseResponse.success(videoTaskService.getTask(request));
    }

    /**
     * 获取视频任务详细
     * @param taskId 任务ID
     */
    @GetMapping("/{id:\\d+}")
    public BaseResponse<VideoTaskDetail> getTaskDetail(
            @PathVariable("id") long taskId
    ) {
        return BaseResponse.success(videoTaskService.getTaskDetail(taskId));
    }

    /**
     * 上报视频任务进度
     * @param request 上报请求
     */
    @PutMapping("/state")
    public BaseResponse<Boolean> updateTaskState(@RequestBody VideoTaskUpdateRequest request) {
        videoTaskService.updateTaskState(request);
        return BaseResponse.success(true);
    }

    /**
     * 添加视频创作任务
     * @param request 任务创建请求
     * @return 任务ID
     */
    @PostMapping
    public BaseResponse<Long> create(@RequestBody VideoTaskCreateRequest request) {
        return BaseResponse.success(videoTaskService.addTask(request));
    }
}
