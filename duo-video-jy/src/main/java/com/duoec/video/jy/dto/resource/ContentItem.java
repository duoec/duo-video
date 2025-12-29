package com.duoec.video.jy.dto.resource;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Lists;

import java.io.Serializable;
import java.util.List;

public class ContentItem implements Serializable {
    private String guid;

    private List<ContentAnim> anims;

    private Double duration;

    private List<Object> children;

    @JsonProperty("layout_params")
    private ContentLayoutParams layoutParams;

    private Integer lineFeedKind;

    private String name;

    /**
     * 层级，越高越上层（会互相遮挡）
     */
    @JsonProperty("order_in_layer")
    private Integer orderInLayer;

    @JsonProperty("original_size")
    private List<Integer> originalSize;

    @JsonIgnore
    private List<Double> changeSize;

    private List<Double> position;

    /**
     * 此值有时时 Integer ，有时时List<Integer>
     */
    private Object rotation;

    private List<Double> scale;

    @JsonProperty("start_time")
    private Double startTime;

    @JsonProperty("text_params")
    private ContentTextParams textParams;

    @JsonProperty("sticker_alpha")
    private Double stickerAlpha;

    @JsonProperty("sticker_design_type")
    private Integer stickerDesignType;

    @JsonProperty("sticker_loop")
    private Boolean stickerLoop;

    @JsonProperty("sticker_path")
    private String stickerPath;

    @JsonProperty("sticker_resource_id")
    private String stickerResourceId;

    @JsonProperty("anim_duration_mode")
    private Integer animDurationMode;

    @JsonProperty("anim_duration_scale_min")
    private Integer animDurationScaleMin;

    private String type;

    private Boolean visible;

    public List<ContentAnim> getAnims() {
        return anims;
    }

    public ContentItem setAnims(List<ContentAnim> anims) {
        this.anims = anims;
        return this;
    }

    public Double getDuration() {
        return duration;
    }

    public ContentItem setDuration(Double duration) {
        this.duration = duration;
        return this;
    }

    public ContentLayoutParams getLayoutParams() {
        return layoutParams;
    }

    public ContentItem setLayoutParams(ContentLayoutParams layoutParams) {
        this.layoutParams = layoutParams;
        return this;
    }

    public String getName() {
        return name;
    }

    public ContentItem setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getOrderInLayer() {
        return orderInLayer;
    }

    public ContentItem setOrderInLayer(Integer orderInLayer) {
        this.orderInLayer = orderInLayer;
        return this;
    }

    public List<Integer> getOriginalSize() {
        return originalSize;
    }

    public ContentItem setOriginalSize(List<Integer> originalSize) {
        this.originalSize = originalSize;
        return this;
    }

    public List<Double> getPosition() {
        return position;
    }

    public ContentItem setPosition(List<Double> position) {
        this.position = position;
        return this;
    }

    public Object getRotation() {
        return rotation;
    }

    public ContentItem setRotation(Object rotation) {
        this.rotation = rotation;
        return this;
    }

    public List<Double> getScale() {
        return scale;
    }

    public ContentItem setScale(List<Double> scale) {
        this.scale = scale;
        return this;
    }

    public Double getStartTime() {
        return startTime;
    }

    public ContentItem setStartTime(Double startTime) {
        this.startTime = startTime;
        return this;
    }

    public Double getStickerAlpha() {
        return stickerAlpha;
    }

    public ContentItem setStickerAlpha(Double stickerAlpha) {
        this.stickerAlpha = stickerAlpha;
        return this;
    }

    public Integer getStickerDesignType() {
        return stickerDesignType;
    }

    public ContentItem setStickerDesignType(Integer stickerDesignType) {
        this.stickerDesignType = stickerDesignType;
        return this;
    }

    public Boolean getStickerLoop() {
        return stickerLoop;
    }

    public ContentItem setStickerLoop(Boolean stickerLoop) {
        this.stickerLoop = stickerLoop;
        return this;
    }

    public String getStickerPath() {
        return stickerPath;
    }

    public ContentItem setStickerPath(String stickerPath) {
        this.stickerPath = stickerPath;
        return this;
    }

    public String getStickerResourceId() {
        return stickerResourceId;
    }

    public ContentItem setStickerResourceId(String stickerResourceId) {
        this.stickerResourceId = stickerResourceId;
        return this;
    }

    public String getType() {
        return type;
    }

    public ContentItem setType(String type) {
        this.type = type;
        return this;
    }

    public Boolean getVisible() {
        return visible;
    }

    public ContentItem setVisible(Boolean visible) {
        this.visible = visible;
        return this;
    }

    public Integer getLineFeedKind() {
        return lineFeedKind;
    }

    public ContentItem setLineFeedKind(Integer lineFeedKind) {
        this.lineFeedKind = lineFeedKind;
        return this;
    }

    public ContentTextParams getTextParams() {
        return textParams;
    }

    public ContentItem setTextParams(ContentTextParams textParams) {
        this.textParams = textParams;
        return this;
    }

    public String getGuid() {
        return guid;
    }

    public ContentItem setGuid(String guid) {
        this.guid = guid;
        return this;
    }

    public List<Object> getChildren() {
        return children;
    }

    public ContentItem setChildren(List<Object> children) {
        this.children = children;
        return this;
    }

    public Integer getAnimDurationMode() {
        return animDurationMode;
    }

    public ContentItem setAnimDurationMode(Integer animDurationMode) {
        this.animDurationMode = animDurationMode;
        return this;
    }

    public Integer getAnimDurationScaleMin() {
        return animDurationScaleMin;
    }

    public ContentItem setAnimDurationScaleMin(Integer animDurationScaleMin) {
        this.animDurationScaleMin = animDurationScaleMin;
        return this;
    }

    public List<Double> getChangeSize() {
        return changeSize;
    }

    public ContentItem setChangeSize(List<Double> changeSize) {
        this.changeSize = changeSize;
        return this;
    }
}
