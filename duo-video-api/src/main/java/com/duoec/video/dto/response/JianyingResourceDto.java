package com.duoec.video.dto.response;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class JianyingResourceDto implements Serializable {
    /**
     * 主键
     */
    private String id;

    /**
     * 资源名称
     */
    private String name;

    /**
     * 归属分类
     */
    private List<OptionDto<Integer>> categories;

    /**
     * 资源静态封面图
     */
    private String coverImg;

    /**
     * 资源动态封面图
     */
    private String coverGif;

    /**
     * 是否允许商业应用
     */
    private Boolean isBusiness;

    /**
     * 是否开放
     */
    private Boolean open;

    /**
     * 商用使用范围
     */
    private String businessScope;

    /**
     * 剪映资源类型
     */
    private String type;

    /**
     * 文本段落数量
     */
    private Integer textCount;

    /**
     * 时长，单位：毫秒
     */
    private Long duration;

    /**
     * 是否已收藏
     */
    private Boolean collected;

    /**
     * 创建时间，时间戳，粒度：毫秒
     */
    private Long createTime;

    /**
     * 更新时间，时间戳，粒度：毫秒
     */
    private Long updateTime;

    /**
     * 获取资源错误信息
     */
    private String errorMsg;

    /**
     * 音频地址，仅在 type=sound 时有值
     */
    private String audioUrl;
}
