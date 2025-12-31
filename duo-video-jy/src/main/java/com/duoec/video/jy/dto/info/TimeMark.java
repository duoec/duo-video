package com.duoec.video.jy.dto.info;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

public class TimeMark extends BaseMaterial {

    @JsonProperty("mark_items")
    private List<TimeMarkItem> markItems;


    public TimeMark setId(String id) {
        this.id = id;
        return this;
    }

    public List<TimeMarkItem> getMarkItems() {
        return markItems;
    }

    public TimeMark setMarkItems(List<TimeMarkItem> markItems) {
        this.markItems = markItems;
        return this;
    }
}
