package com.duoec.video.project;

import lombok.Data;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.List;

@Data
public class VideoScript implements Serializable {
    /**
     * 限定时间（开始、时长）
     * 如果未指定，则为自动扩容，以segments占用时间为准
     * 如果设定时间，当前segments超出部分将会被隐藏
     */
    private VideoTimeRange time;

    /**
     * 片段
     */
    private List<VideoSegment> segments;

    public Long getEndTime() {
        if (time != null && time.getStart() != null && time.getDuration() != null) {
            // 限定时间
            return time.getEndTime();
        }
        if (CollectionUtils.isEmpty(segments)) {
            return 0L;
        }
        return segments.stream()
                .filter(segment -> segment.getVisible() == null || segment.getVisible())
                .mapToLong(VideoSegment::getEndTime)
                .max().orElse(0L);
    }
}
