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
     * 分类IDs
     */
    private List<Integer> categoryIds;

    /**
     * 分类
     */
    private String categoryName;

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
     * 资源静态封面图
     */
    private String coverImg;

    /**
     * 资源动态封面图
     */
    private String coverGif;

    /**
     * 资源主配置
     */
    private String mainConfig;

    /**
     * 文本特效（仅花字）
     */
    private String effect;

    /**
     * 文本配置 (仅文本模板)
     */
    private List<TextResource> texts;

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

    /**
     * 时长，单位：毫秒
     */
    private Long duration;

    @Data
    public static class TextResource {
        /**
         * 文本配置(JSON)
         */
        public String text;

        /**
         * 文本内容
         */
        public String content;

        /**
         * 文本特效(JSON)
         */
        private List<String> effects;

        /**
         * 动画配置(JSON)
         */
        private List<String> animations;
    }
}
