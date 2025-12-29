package com.duoec.video.jy.dto.info;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class AigcConfig implements Serializable {
    @JsonProperty("font_item")
    private Font fontItem;

    private String model;

    private String prompt;

    private Integer seed;

    public Font getFontItem() {
        return fontItem;
    }

    public AigcConfig setFontItem(Font fontItem) {
        this.fontItem = fontItem;
        return this;
    }

    public String getModel() {
        return model;
    }

    public AigcConfig setModel(String model) {
        this.model = model;
        return this;
    }

    public String getPrompt() {
        return prompt;
    }

    public AigcConfig setPrompt(String prompt) {
        this.prompt = prompt;
        return this;
    }

    public Integer getSeed() {
        return seed;
    }

    public AigcConfig setSeed(Integer seed) {
        this.seed = seed;
        return this;
    }
}
