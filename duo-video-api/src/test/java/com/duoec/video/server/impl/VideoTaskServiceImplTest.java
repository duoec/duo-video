package com.duoec.video.server.impl;

import com.duoec.video.dto.request.VideoTaskCreateRequest;
import com.duoec.video.dto.request.VideoTaskRequest;
import com.duoec.video.dto.request.VideoTaskUpdateRequest;
import com.duoec.video.dto.response.VideoTaskDetail;
import com.duoec.video.dto.response.VideoTaskResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class VideoTaskServiceImplTest {

    @Autowired
    private VideoTaskServiceImpl videoTaskService;

    @BeforeEach
    void setUp() throws Exception {
        // Clean up test directory before each test
        String baseDir = "tmp";
        File baseDirFile = new File(baseDir);
        if (baseDirFile.exists()) {
            deleteDirectory(baseDirFile);
        }
    }

    @Test
    void testAddTask() {
        VideoTaskCreateRequest request = new VideoTaskCreateRequest();
        request.setVideoId(1L);
        request.setJyZipUrl("http://example.com/test.zip");
        request.setPriority(100);

        long taskId = videoTaskService.addTask(request);

        assertTrue(taskId > 0, "Task ID should be greater than 0");

        // Verify task exists in waiting directory
        VideoTaskDetail taskDetail = videoTaskService.getTaskDetail(taskId);
        assertNotNull(taskDetail, "Task should exist");
        assertEquals(taskId, taskDetail.getTaskId().longValue(), "Task ID should match");
        assertEquals(1L, taskDetail.getVideoId().longValue(), "Video ID should match");
        assertEquals("http://example.com/test.zip", taskDetail.getJyZipUrl(), "JY Zip URL should match");
        assertEquals(VideoTaskServiceImpl.TASK_WAITING, taskDetail.getStatus().intValue(), "Status should be waiting");
        assertEquals(Integer.valueOf(0), taskDetail.getRetryCount(), "Retry count should be initialized to 0");
    }

    @Test
    void testGetTask() {
        // Add a task first
        VideoTaskCreateRequest createRequest = new VideoTaskCreateRequest();
        createRequest.setVideoId(1L);
        createRequest.setJyZipUrl("http://example.com/test.zip");
        long taskId = videoTaskService.addTask(createRequest);

        // Get the task
        VideoTaskRequest getRequest = new VideoTaskRequest();
        getRequest.setServerName("server1");
        VideoTaskResponse response = videoTaskService.getTask(getRequest);

        assertNotNull(response, "Response should not be null");
        assertEquals(taskId, response.getTaskId().longValue(), "Task ID should match");
        assertEquals(1L, response.getVideoId().longValue(), "Video ID should match");
        assertEquals("http://example.com/test.zip", response.getJyZipUrl(), "JY Zip URL should match");

        // Verify task status is updated to received
        VideoTaskDetail taskDetail = videoTaskService.getTaskDetail(taskId);
        assertEquals(VideoTaskServiceImpl.TASK_RECEIVED, taskDetail.getStatus().intValue(), "Status should be received");
        assertEquals("server1", taskDetail.getServerName(), "Server name should match");
        assertEquals(Integer.valueOf(0), taskDetail.getRetryCount(), "Retry count should remain 0");
    }

    @Test
    void testUpdateTaskState() {
        // Add a task first
        VideoTaskCreateRequest createRequest = new VideoTaskCreateRequest();
        createRequest.setVideoId(1L);
        createRequest.setJyZipUrl("http://example.com/test.zip");
        long taskId = videoTaskService.addTask(createRequest);

        // Update task state
        VideoTaskUpdateRequest updateRequest = new VideoTaskUpdateRequest();
        updateRequest.setTaskId(taskId);
        updateRequest.setStatus(VideoTaskServiceImpl.TASK_DOWNLOAD_COMPLETE);
        updateRequest.setInfo("Download completed");
        updateRequest.setServerName("server1");

        videoTaskService.updateTaskState(updateRequest);

        // Verify task state is updated
        VideoTaskDetail taskDetail = videoTaskService.getTaskDetail(taskId);
        assertEquals(VideoTaskServiceImpl.TASK_DOWNLOAD_COMPLETE, taskDetail.getStatus().intValue(), "Status should be download complete");
        assertEquals("Download completed", taskDetail.getTaskInfo(), "Task info should match");
        assertEquals("server1", taskDetail.getServerName(), "Server name should match");
    }

    @Test
    void testGetTaskDetail() {
        // Add a task first
        VideoTaskCreateRequest createRequest = new VideoTaskCreateRequest();
        createRequest.setVideoId(1L);
        createRequest.setJyZipUrl("http://example.com/test.zip");
        createRequest.setPriority(500);
        long taskId = videoTaskService.addTask(createRequest);

        // Get task detail
        VideoTaskDetail taskDetail = videoTaskService.getTaskDetail(taskId);

        assertNotNull(taskDetail, "Task detail should not be null");
        assertEquals(taskId, taskDetail.getTaskId().longValue(), "Task ID should match");
        assertEquals(1L, taskDetail.getVideoId().longValue(), "Video ID should match");
        assertEquals("http://example.com/test.zip", taskDetail.getJyZipUrl(), "JY Zip URL should match");
        assertEquals(Integer.valueOf(500), taskDetail.getPriority(), "Priority should match");
        assertEquals(VideoTaskServiceImpl.TASK_WAITING, taskDetail.getStatus().intValue(), "Status should be waiting");
        assertEquals(Integer.valueOf(0), taskDetail.getRetryCount(), "Retry count should be initialized to 0");
    }

    private void deleteDirectory(File directory) {
        if (directory.isDirectory()) {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    deleteDirectory(file);
                }
            }
        }
        directory.delete();
    }

}