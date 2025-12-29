package com.duoec.video.project.material;

public class TextWord extends TextStyle {
    /**
     * 文本索引
     *
     * @demo 0
     */
    private Integer index;

    /**
     * 文本长度
     * @demo 3
     */
    private Integer length;

    public Integer getIndex() {
        return index;
    }

    public TextWord setIndex(Integer index) {
        this.index = index;
        return this;
    }

    public Integer getLength() {
        return length;
    }

    public TextWord setLength(Integer length) {
        this.length = length;
        return this;
    }
}
