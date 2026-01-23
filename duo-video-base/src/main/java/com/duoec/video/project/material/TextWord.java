package com.duoec.video.project.material;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
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

    /**
     * 预设样式ID。[type='style', id=${styleId}]
     * 如果同时设置 styleId 和 style时，会优先加载 styleId 的样式，再使用 style 覆盖！
     */
    private Long styleId;
}
