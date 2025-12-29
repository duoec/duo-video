
package com.duoec.video.jy.dto.info;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

public class ComboInfo implements Serializable {
    @JsonProperty("text_templates")
    public List<Object> textTemplates;

    public List<Object> getTextTemplates() {
        return textTemplates;
    }

    public ComboInfo setTextTemplates(List<Object> textTemplates) {
        this.textTemplates = textTemplates;
        return this;
    }
}
