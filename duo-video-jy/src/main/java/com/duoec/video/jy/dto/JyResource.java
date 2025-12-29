package com.duoec.video.jy.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Data
@Accessors(chain = true)
public class JyResource implements Serializable {
    /**
     * 自增主键
     */
    private Long id;

    /**
     * 资源名称
     */
    private String name;

    /**
     * 分类
     */
    private String categoryName;

    /**
     * 资源预览地址
     */
    private String demoUrl;

    /**
     * 是否允许商业应用
     */
    private Boolean isBusiness;

    /**
     * 商用使用范围
     */
    private String businessScope;

    /**
     * 剪映上的资源id
     */
    private String resourceId;

    /**
     * 剪映资源类型
     */
    private String type;

    /**
     * 状态：-1=停用 1=正常使用
     */
    private Integer status;

    /**
     * 资源主配置
     */
    private String mainConfig;

    /**
     * 文本特效
     */
    private String effect;

    /**
     * 动画配置
     */
    private String animation;

    /**
     * 文本配置
     */
    private String text;

    /**
     * 用户配置
     */
    private Map<String, Object> config;

    /**
     * 资源列表
     */
    private Set<String> resources;

    /**
     * 资源Map resourceId => resourceUrl
     */
    @JsonIgnore
    private Map<String, String> resourceMap;
}
