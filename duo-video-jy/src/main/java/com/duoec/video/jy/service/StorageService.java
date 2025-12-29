package com.duoec.video.jy.service;

import java.io.File;

public interface StorageService {
    void asyncDownload(Long taskId, String url, File file);

    boolean waitAsyncDownloadTask(Long taskId);

    void download(String url, File targetFile);
}
