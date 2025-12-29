package com.duoec.video.project;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Optional;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class VideoTimeRange implements Serializable {
    /**
     * 开始时间，时间戳，单位：毫秒
     */
    private Long start;

    /**
     * 持续时间，单位：毫秒
     */
    private Long duration;

    public long getEndTime() {
        return Optional.ofNullable(start).orElse(0L) + Optional.ofNullable(duration).orElse(0L);
    }
}
