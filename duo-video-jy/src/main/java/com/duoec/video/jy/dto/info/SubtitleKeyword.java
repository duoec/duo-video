package com.duoec.video.jy.dto.info;

import java.io.Serializable;
import java.util.List;

public class SubtitleKeyword implements Serializable {
    private List<Range> range;

    public List<Range> getRange() {
        return range;
    }

    public SubtitleKeyword setRange(List<Range> range) {
        this.range = range;
        return this;
    }
}
