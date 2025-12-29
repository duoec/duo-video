
package com.duoec.video.jy.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class TimeRange implements Serializable {
    public Long duration;

    public Long start;
}
