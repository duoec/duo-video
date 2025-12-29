
package com.duoec.video.jy.dto.font;

import java.io.Serializable;
import java.util.List;

public class FontConfig implements Serializable {

    public List<Style> styles;

    public String text;

    public List<Style> getStyles() {
        return styles;
    }

    public FontConfig setStyles(List<Style> styles) {
        this.styles = styles;
        return this;
    }

    public String getText() {
        return text;
    }

    public FontConfig setText(String text) {
        this.text = text;
        return this;
    }
}
