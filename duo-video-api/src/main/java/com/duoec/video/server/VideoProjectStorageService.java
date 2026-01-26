package com.duoec.video.server;

import com.duoec.video.project.VideoProject;

public interface VideoProjectStorageService {
    /**
     * 获取视频工程
     * @param videoProjectId 视频工程ID
     * @return 视频工程
     */
    VideoProject get(long videoProjectId);

    /**
     * 保存视频工程
     * @param videoProject 视频工程
     */
    void save(VideoProject videoProject);
}
