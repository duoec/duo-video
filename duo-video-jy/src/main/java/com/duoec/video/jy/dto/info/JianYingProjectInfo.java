
package com.duoec.video.jy.dto.info;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

public class JianYingProjectInfo implements Serializable {
    @JsonProperty("canvas_config")
    public CanvasConfig canvasConfig;

    @JsonProperty("color_space")
    public Integer colorSpace;

    public Config config;

    public Object cover;

    @JsonProperty("create_time")
    public Integer createTime;

    public Long duration;

    @JsonProperty("extra_info")
    public Object extraInfo;

    public Double fps;

    @JsonProperty("free_render_index_mode_on")
    public Boolean freeRenderIndexModeOn;

    @JsonProperty("group_container")
    public GroupContainer groupContainer;

    public String id;

    @JsonProperty("keyframe_graph_list")
    public List<Object> keyframeGraphList;

    public Keyframes keyframes;

    @JsonProperty("last_modified_platform")
    public Platform lastModifiedPlatform;

    public Materials materials;

    @JsonProperty("mutable_config")
    public Object mutableConfig;

    public String name;

    @JsonProperty("new_version")
    public String newVersion = "101.0.0";

    public Platform platform;

    public List<Object> relationships;

    @JsonProperty("render_index_track_mode_on")
    public Boolean renderIndexTrackModeOn;

    @JsonProperty("retouch_cover")
    public Object retouchCover;

    public String source;

    @JsonProperty("static_cover_image_path")
    public String staticCoverImagePath;

    public List<Track> tracks;

    @JsonProperty("update_time")
    public Integer updateTime;

    public Integer version;

    @JsonProperty("uneven_animation_template_info")
    public UnevenAnimationTemplateInfo unevenAnimationTemplateInfo;

    @JsonProperty("smart_ads_info")
    public SmartAdsInfo smartAdsInfo;

    public String path;

    @JsonProperty("lyrics_effects")
    public List<Object> lyricsEffects;

    @JsonProperty("draft_type")
    public String draftType;

    @JsonProperty("function_assistant_info")
    public FunctionAssistantInfo functionAssistantInfo;

    @JsonProperty("is_drop_frame_timecode")
    public Boolean isDropFrameTimecode;

    public CanvasConfig getCanvasConfig() {
        return canvasConfig;
    }

    public JianYingProjectInfo setCanvasConfig(CanvasConfig canvasConfig) {
        this.canvasConfig = canvasConfig;
        return this;
    }

    public Integer getColorSpace() {
        return colorSpace;
    }

    public JianYingProjectInfo setColorSpace(Integer colorSpace) {
        this.colorSpace = colorSpace;
        return this;
    }

    public Config getConfig() {
        return config;
    }

    public JianYingProjectInfo setConfig(Config config) {
        this.config = config;
        return this;
    }

    public Object getCover() {
        return cover;
    }

    public JianYingProjectInfo setCover(Object cover) {
        this.cover = cover;
        return this;
    }

    public Integer getCreateTime() {
        return createTime;
    }

    public JianYingProjectInfo setCreateTime(Integer createTime) {
        this.createTime = createTime;
        return this;
    }

    public Long getDuration() {
        return duration;
    }

    public JianYingProjectInfo setDuration(Long duration) {
        this.duration = duration;
        return this;
    }

    public Object getExtraInfo() {
        return extraInfo;
    }

    public JianYingProjectInfo setExtraInfo(Object extraInfo) {
        this.extraInfo = extraInfo;
        return this;
    }

    public Double getFps() {
        return fps;
    }

    public JianYingProjectInfo setFps(Double fps) {
        this.fps = fps;
        return this;
    }

    public Boolean getFreeRenderIndexModeOn() {
        return freeRenderIndexModeOn;
    }

    public JianYingProjectInfo setFreeRenderIndexModeOn(Boolean freeRenderIndexModeOn) {
        this.freeRenderIndexModeOn = freeRenderIndexModeOn;
        return this;
    }

    public GroupContainer getGroupContainer() {
        return groupContainer;
    }

    public JianYingProjectInfo setGroupContainer(GroupContainer groupContainer) {
        this.groupContainer = groupContainer;
        return this;
    }

    public String getId() {
        return id;
    }

    public JianYingProjectInfo setId(String id) {
        this.id = id;
        return this;
    }

    public List<Object> getKeyframeGraphList() {
        return keyframeGraphList;
    }

    public JianYingProjectInfo setKeyframeGraphList(List<Object> keyframeGraphList) {
        this.keyframeGraphList = keyframeGraphList;
        return this;
    }

    public Keyframes getKeyframes() {
        return keyframes;
    }

    public JianYingProjectInfo setKeyframes(Keyframes keyframes) {
        this.keyframes = keyframes;
        return this;
    }

    public Platform getLastModifiedPlatform() {
        return lastModifiedPlatform;
    }

    public JianYingProjectInfo setLastModifiedPlatform(Platform lastModifiedPlatform) {
        this.lastModifiedPlatform = lastModifiedPlatform;
        return this;
    }

    public Materials getMaterials() {
        return materials;
    }

    public JianYingProjectInfo setMaterials(Materials materials) {
        this.materials = materials;
        return this;
    }

    public Object getMutableConfig() {
        return mutableConfig;
    }

    public JianYingProjectInfo setMutableConfig(Object mutableConfig) {
        this.mutableConfig = mutableConfig;
        return this;
    }

    public String getName() {
        return name;
    }

    public JianYingProjectInfo setName(String name) {
        this.name = name;
        return this;
    }

    public String getNewVersion() {
        return newVersion;
    }

    public JianYingProjectInfo setNewVersion(String newVersion) {
        this.newVersion = newVersion;
        return this;
    }

    public Platform getPlatform() {
        return platform;
    }

    public JianYingProjectInfo setPlatform(Platform platform) {
        this.platform = platform;
        return this;
    }

    public List<Object> getRelationships() {
        return relationships;
    }

    public JianYingProjectInfo setRelationships(List<Object> relationships) {
        this.relationships = relationships;
        return this;
    }

    public Boolean getRenderIndexTrackModeOn() {
        return renderIndexTrackModeOn;
    }

    public JianYingProjectInfo setRenderIndexTrackModeOn(Boolean renderIndexTrackModeOn) {
        this.renderIndexTrackModeOn = renderIndexTrackModeOn;
        return this;
    }

    public Object getRetouchCover() {
        return retouchCover;
    }

    public JianYingProjectInfo setRetouchCover(Object retouchCover) {
        this.retouchCover = retouchCover;
        return this;
    }

    public String getSource() {
        return source;
    }

    public JianYingProjectInfo setSource(String source) {
        this.source = source;
        return this;
    }

    public String getStaticCoverImagePath() {
        return staticCoverImagePath;
    }

    public JianYingProjectInfo setStaticCoverImagePath(String staticCoverImagePath) {
        this.staticCoverImagePath = staticCoverImagePath;
        return this;
    }

    public List<Track> getTracks() {
        return tracks;
    }

    public JianYingProjectInfo setTracks(List<Track> tracks) {
        this.tracks = tracks;
        return this;
    }

    public Integer getUpdateTime() {
        return updateTime;
    }

    public JianYingProjectInfo setUpdateTime(Integer updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public Integer getVersion() {
        return version;
    }

    public JianYingProjectInfo setVersion(Integer version) {
        this.version = version;
        return this;
    }

    public UnevenAnimationTemplateInfo getUnevenAnimationTemplateInfo() {
        return unevenAnimationTemplateInfo;
    }

    public JianYingProjectInfo setUnevenAnimationTemplateInfo(UnevenAnimationTemplateInfo unevenAnimationTemplateInfo) {
        this.unevenAnimationTemplateInfo = unevenAnimationTemplateInfo;
        return this;
    }

    public SmartAdsInfo getSmartAdsInfo() {
        return smartAdsInfo;
    }

    public JianYingProjectInfo setSmartAdsInfo(SmartAdsInfo smartAdsInfo) {
        this.smartAdsInfo = smartAdsInfo;
        return this;
    }

    public String getPath() {
        return path;
    }

    public JianYingProjectInfo setPath(String path) {
        this.path = path;
        return this;
    }

    public List<Object> getLyricsEffects() {
        return lyricsEffects;
    }

    public JianYingProjectInfo setLyricsEffects(List<Object> lyricsEffects) {
        this.lyricsEffects = lyricsEffects;
        return this;
    }

    public String getDraftType() {
        return draftType;
    }

    public JianYingProjectInfo setDraftType(String draftType) {
        this.draftType = draftType;
        return this;
    }

    public FunctionAssistantInfo getFunctionAssistantInfo() {
        return functionAssistantInfo;
    }

    public JianYingProjectInfo setFunctionAssistantInfo(FunctionAssistantInfo functionAssistantInfo) {
        this.functionAssistantInfo = functionAssistantInfo;
        return this;
    }

    public Boolean getIsDropFrameTimecode() {
        return isDropFrameTimecode;
    }

    public JianYingProjectInfo setIsDropFrameTimecode(Boolean isDropFrameTimecode) {
        this.isDropFrameTimecode = isDropFrameTimecode;
        return this;
    }
}
