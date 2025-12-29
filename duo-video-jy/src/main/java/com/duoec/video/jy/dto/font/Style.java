
package com.duoec.video.jy.dto.font;

import com.duoec.video.jy.dto.BaseResource;

import java.io.Serializable;
import java.util.List;

public class Style implements Serializable {

    /**
     * 填充
     */
    public FontStyle fill;

    /**
     * 描边
     */
    public List<FontStyle> strokes;

    private Boolean useLetterColor;

    /**
     * 阴影
     */
    public List<FontStyle> shadows;

    public Font font;

    public List<Integer> range;

    public Double size;

    private BaseResource effectStyle;

    private Boolean bold;

    private Boolean italic;

    private Boolean underline;

    public FontStyle getFill() {
        return fill;
    }

    public Style setFill(FontStyle fill) {
        this.fill = fill;
        return this;
    }

    public Font getFont() {
        return font;
    }

    public Style setFont(Font font) {
        this.font = font;
        return this;
    }

    public List<Integer> getRange() {
        return range;
    }

    public Style setRange(List<Integer> range) {
        this.range = range;
        return this;
    }

    public Double getSize() {
        return size;
    }

    public Style setSize(Double size) {
        this.size = size;
        return this;
    }

    public List<FontStyle> getStrokes() {
        return strokes;
    }

    public Style setStrokes(List<FontStyle> strokes) {
        this.strokes = strokes;
        return this;
    }

    public List<FontStyle> getShadows() {
        return shadows;
    }

    public Style setShadows(List<FontStyle> shadows) {
        this.shadows = shadows;
        return this;
    }

    public Boolean getUseLetterColor() {
        return useLetterColor;
    }

    public Style setUseLetterColor(Boolean useLetterColor) {
        this.useLetterColor = useLetterColor;
        return this;
    }

    public BaseResource getEffectStyle() {
        return effectStyle;
    }

    public Style setEffectStyle(BaseResource effectStyle) {
        this.effectStyle = effectStyle;
        return this;
    }

    public void setBold(Boolean bold) {
        this.bold = bold;
    }

    public Boolean isBold() {
        return bold;
    }

    public void setItalic(Boolean italic) {
        this.italic = italic;
    }

    public Boolean isItalic() {
        return italic;
    }

    public void setUnderline(Boolean underline) {
        this.underline = underline;
    }

    public Boolean isUnderline() {
        return underline;
    }
}
