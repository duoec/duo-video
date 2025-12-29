package com.duoec.video.jy.dto.info;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Audio extends BaseMaterial {
    @JsonProperty("aigc_history_id")
    public String aigcHistoryId;

    @JsonProperty("aigc_item_id")
    public String aigcItemId;

    @JsonProperty("app_id")
    public Integer appId;

    @JsonProperty("category_id")
    public String categoryId;

    @JsonProperty("category_name")
    public String categoryName;

    @JsonProperty("check_flag")
    public Integer checkFlag;

    @JsonProperty("duration")
    public Long duration;

    @JsonProperty("effect_id")
    public String effectId;

    @JsonProperty("formula_id")
    public String formulaId;

    @JsonProperty("intensifies_path")
    public String intensifiesPath;

    @JsonProperty("is_ai_clone_tone")
    public Boolean isAiCloneTone;

    @JsonProperty("is_text_edit_overdub")
    public Boolean isTextEditOverdub;

    @JsonProperty("is_ugc")
    public Boolean isUgc;

    @JsonProperty("local_material_id")
    public String localMaterialId;

    @JsonProperty("music_id")
    public String musicId;

    @JsonProperty("name")
    public String name;

    @JsonProperty("path")
    public String path;

    @JsonProperty("query")
    public String query;

    @JsonProperty("request_id")
    public String requestId;

    @JsonProperty("resource_id")
    public String resourceId;

    @JsonProperty("search_id")
    public String searchId;

    @JsonProperty("source_from")
    public String sourceFrom;

    @JsonProperty("source_platform")
    public Integer sourcePlatform;

    @JsonProperty("team_id")
    public String teamId;

    @JsonProperty("text_id")
    public String textId;

    @JsonProperty("tone_category_id")
    public String toneCategoryId;

    @JsonProperty("tone_category_name")
    public String toneCategoryName;

    @JsonProperty("tone_effect_id")
    public String toneEffectId;

    @JsonProperty("tone_effect_name")
    public String toneEffectName;

    @JsonProperty("tone_platform")
    public String tonePlatform;

    @JsonProperty("tone_second_category_id")
    public String toneSecondCategoryId;

    @JsonProperty("tone_second_category_name")
    public String toneSecondCategoryName;

    @JsonProperty("tone_speaker")
    public String toneSpeaker;

    @JsonProperty("tone_type")
    public String toneType;

    @JsonProperty("video_id")
    public String videoId;

    @JsonProperty("wave_points")
    public List<Point> wavePoints;

    public Integer getAppId() {
        return appId;
    }

    public Audio setAppId(Integer appId) {
        this.appId = appId;
        return this;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public Audio setCategoryId(String categoryId) {
        this.categoryId = categoryId;
        return this;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public Audio setCategoryName(String categoryName) {
        this.categoryName = categoryName;
        return this;
    }

    public Integer getCheckFlag() {
        return checkFlag;
    }

    public Audio setCheckFlag(Integer checkFlag) {
        this.checkFlag = checkFlag;
        return this;
    }

    public Long getDuration() {
        return duration;
    }

    public Audio setDuration(Long duration) {
        this.duration = duration;
        return this;
    }

    public String getEffectId() {
        return effectId;
    }

    public Audio setEffectId(String effectId) {
        this.effectId = effectId;
        return this;
    }

    public String getFormulaId() {
        return formulaId;
    }

    public Audio setFormulaId(String formulaId) {
        this.formulaId = formulaId;
        return this;
    }

    public Audio setId(String id) {
        this.id = id;
        return this;
    }

    public String getIntensifiesPath() {
        return intensifiesPath;
    }

    public Audio setIntensifiesPath(String intensifiesPath) {
        this.intensifiesPath = intensifiesPath;
        return this;
    }

    public Boolean getAiCloneTone() {
        return isAiCloneTone;
    }

    public Audio setAiCloneTone(Boolean aiCloneTone) {
        isAiCloneTone = aiCloneTone;
        return this;
    }

    public Boolean getTextEditOverdub() {
        return isTextEditOverdub;
    }

    public Audio setTextEditOverdub(Boolean textEditOverdub) {
        isTextEditOverdub = textEditOverdub;
        return this;
    }

    public Boolean getUgc() {
        return isUgc;
    }

    public Audio setUgc(Boolean ugc) {
        isUgc = ugc;
        return this;
    }

    public String getLocalMaterialId() {
        return localMaterialId;
    }

    public Audio setLocalMaterialId(String localMaterialId) {
        this.localMaterialId = localMaterialId;
        return this;
    }

    public String getMusicId() {
        return musicId;
    }

    public Audio setMusicId(String musicId) {
        this.musicId = musicId;
        return this;
    }

    public String getName() {
        return name;
    }

    public Audio setName(String name) {
        this.name = name;
        return this;
    }

    public String getPath() {
        return path;
    }

    public Audio setPath(String path) {
        this.path = path;
        return this;
    }

    public String getQuery() {
        return query;
    }

    public Audio setQuery(String query) {
        this.query = query;
        return this;
    }

    public String getRequestId() {
        return requestId;
    }

    public Audio setRequestId(String requestId) {
        this.requestId = requestId;
        return this;
    }

    public String getResourceId() {
        return resourceId;
    }

    public Audio setResourceId(String resourceId) {
        this.resourceId = resourceId;
        return this;
    }

    public String getSearchId() {
        return searchId;
    }

    public Audio setSearchId(String searchId) {
        this.searchId = searchId;
        return this;
    }

    public String getSourceFrom() {
        return sourceFrom;
    }

    public Audio setSourceFrom(String sourceFrom) {
        this.sourceFrom = sourceFrom;
        return this;
    }

    public Integer getSourcePlatform() {
        return sourcePlatform;
    }

    public Audio setSourcePlatform(Integer sourcePlatform) {
        this.sourcePlatform = sourcePlatform;
        return this;
    }

    public String getTeamId() {
        return teamId;
    }

    public Audio setTeamId(String teamId) {
        this.teamId = teamId;
        return this;
    }

    public String getTextId() {
        return textId;
    }

    public Audio setTextId(String textId) {
        this.textId = textId;
        return this;
    }

    public String getToneCategoryId() {
        return toneCategoryId;
    }

    public Audio setToneCategoryId(String toneCategoryId) {
        this.toneCategoryId = toneCategoryId;
        return this;
    }

    public String getToneCategoryName() {
        return toneCategoryName;
    }

    public Audio setToneCategoryName(String toneCategoryName) {
        this.toneCategoryName = toneCategoryName;
        return this;
    }

    public String getToneEffectId() {
        return toneEffectId;
    }

    public Audio setToneEffectId(String toneEffectId) {
        this.toneEffectId = toneEffectId;
        return this;
    }

    public String getToneEffectName() {
        return toneEffectName;
    }

    public Audio setToneEffectName(String toneEffectName) {
        this.toneEffectName = toneEffectName;
        return this;
    }

    public String getTonePlatform() {
        return tonePlatform;
    }

    public Audio setTonePlatform(String tonePlatform) {
        this.tonePlatform = tonePlatform;
        return this;
    }

    public String getToneSecondCategoryId() {
        return toneSecondCategoryId;
    }

    public Audio setToneSecondCategoryId(String toneSecondCategoryId) {
        this.toneSecondCategoryId = toneSecondCategoryId;
        return this;
    }

    public String getToneSecondCategoryName() {
        return toneSecondCategoryName;
    }

    public Audio setToneSecondCategoryName(String toneSecondCategoryName) {
        this.toneSecondCategoryName = toneSecondCategoryName;
        return this;
    }

    public String getToneSpeaker() {
        return toneSpeaker;
    }

    public Audio setToneSpeaker(String toneSpeaker) {
        this.toneSpeaker = toneSpeaker;
        return this;
    }

    public String getToneType() {
        return toneType;
    }

    public Audio setToneType(String toneType) {
        this.toneType = toneType;
        return this;
    }

    public Audio setType(String type) {
        this.type = type;
        return this;
    }

    public String getVideoId() {
        return videoId;
    }

    public Audio setVideoId(String videoId) {
        this.videoId = videoId;
        return this;
    }

    public List<Point> getWavePoints() {
        return wavePoints;
    }

    public Audio setWavePoints(List<Point> wavePoints) {
        this.wavePoints = wavePoints;
        return this;
    }

    public String getAigcHistoryId() {
        return aigcHistoryId;
    }

    public Audio setAigcHistoryId(String aigcHistoryId) {
        this.aigcHistoryId = aigcHistoryId;
        return this;
    }

    public String getAigcItemId() {
        return aigcItemId;
    }

    public Audio setAigcItemId(String aigcItemId) {
        this.aigcItemId = aigcItemId;
        return this;
    }
}