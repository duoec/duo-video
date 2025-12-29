
package com.duoec.video.jy.dto.info;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class TextInfoResource extends BaseTextInfoResource {
    @JsonProperty("extra_material_refs")
    public List<String> extraMaterialRefs;

    @JsonProperty("text_material_id")
    public String textMaterialId;

    @Override
    public TextInfoResource setAttachInfo(AttachInfo attachInfo) {
        super.setAttachInfo(attachInfo);
        return this;
    }

    public List<String> getExtraMaterialRefs() {
        return extraMaterialRefs;
    }

    public TextInfoResource setExtraMaterialRefs(List<String> extraMaterialRefs) {
        this.extraMaterialRefs = extraMaterialRefs;
        return this;
    }

    public String getTextMaterialId() {
        return textMaterialId;
    }

    public TextInfoResource setTextMaterialId(String textMaterialId) {
        this.textMaterialId = textMaterialId;
        return this;
    }
}
