package com.duoec.video.server.impl;

import com.duoec.base.core.util.FileUtils;
import com.duoec.base.core.util.JsonUtils;
import com.duoec.video.project.VideoProject;
import com.duoec.video.server.VideoProjectStorageService;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.charset.StandardCharsets;

@Service
public class VideoProjectStorageServiceImpl implements VideoProjectStorageService {
    private static final File PROJECT_DIR = new File("./tmp/projects");
    public static final String STR_JSON = ".json";

    static {
        FileUtils.mkdirs(PROJECT_DIR);
    }
    
    @Override
    public VideoProject get(long videoProjectId) {
        File projectFile = new File(PROJECT_DIR, videoProjectId + STR_JSON);
        return FileUtils.readJson(projectFile, VideoProject.class);
    }

    @Override
    public void save(VideoProject videoProject) {
        File projectFile = new File(PROJECT_DIR, videoProject.getId() + STR_JSON);
        FileUtils.writeFile(JsonUtils.toJsonString(videoProject).getBytes(StandardCharsets.UTF_8), projectFile);
    }
}
