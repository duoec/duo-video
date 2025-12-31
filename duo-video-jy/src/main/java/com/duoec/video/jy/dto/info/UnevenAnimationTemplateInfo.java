
package com.duoec.video.jy.dto.info;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

public class UnevenAnimationTemplateInfo implements Serializable {

    public String order;

    @JsonProperty("sub_template_info_list")
    public List<Object> subTemplateInfoList;

    public String content;

    public String composition;

    public String getOrder() {
        return order;
    }

    public UnevenAnimationTemplateInfo setOrder(String order) {
        this.order = order;
        return this;
    }

    public List<Object> getSubTemplateInfoList() {
        return subTemplateInfoList;
    }

    public UnevenAnimationTemplateInfo setSubTemplateInfoList(List<Object> subTemplateInfoList) {
        this.subTemplateInfoList = subTemplateInfoList;
        return this;
    }

    public String getContent() {
        return content;
    }

    public UnevenAnimationTemplateInfo setContent(String content) {
        this.content = content;
        return this;
    }

    public String getComposition() {
        return composition;
    }

    public UnevenAnimationTemplateInfo setComposition(String composition) {
        this.composition = composition;
        return this;
    }
}
