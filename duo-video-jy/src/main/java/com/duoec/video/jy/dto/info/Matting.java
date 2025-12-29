
package com.duoec.video.jy.dto.info;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

public class Matting implements Serializable {

    public Integer flag;

    @JsonProperty("has_use_quick_brush")
    public Boolean hasUseQuickBrush;

    @JsonProperty("has_use_quick_eraser")
    public Boolean hasUseQuickEraser;

    public List<Object> interactiveTime;

    public String path;

    public List<Object> strokes;

    public Integer getFlag() {
        return flag;
    }

    public Matting setFlag(Integer flag) {
        this.flag = flag;
        return this;
    }

    public Boolean getHasUseQuickBrush() {
        return hasUseQuickBrush;
    }

    public Matting setHasUseQuickBrush(Boolean hasUseQuickBrush) {
        this.hasUseQuickBrush = hasUseQuickBrush;
        return this;
    }

    public Boolean getHasUseQuickEraser() {
        return hasUseQuickEraser;
    }

    public Matting setHasUseQuickEraser(Boolean hasUseQuickEraser) {
        this.hasUseQuickEraser = hasUseQuickEraser;
        return this;
    }

    public List<Object> getInteractiveTime() {
        return interactiveTime;
    }

    public Matting setInteractiveTime(List<Object> interactiveTime) {
        this.interactiveTime = interactiveTime;
        return this;
    }

    public String getPath() {
        return path;
    }

    public Matting setPath(String path) {
        this.path = path;
        return this;
    }

    public List<Object> getStrokes() {
        return strokes;
    }

    public Matting setStrokes(List<Object> strokes) {
        this.strokes = strokes;
        return this;
    }
}
