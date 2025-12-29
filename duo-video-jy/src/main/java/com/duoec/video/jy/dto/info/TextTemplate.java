
package com.duoec.video.jy.dto.info;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class TextTemplate extends BaseMaterial {
    @JsonProperty("aigc_config")
    public AigcConfig aigcConfig;

    @JsonProperty("aigc_type")
    public String aigcType;

    @JsonProperty("category_id")
    public String categoryId;

    @JsonProperty("category_name")
    public String categoryName;

    @JsonProperty("check_flag")
    public Integer checkFlag;

    @JsonProperty("effect_id")
    public String effectId;

    @JsonProperty("formula_id")
    public String formulaId;

    @JsonProperty("is_3d")
    public Boolean is3d;

    @JsonProperty("is_pre_rendered")
    public Boolean isPreRendered;

    public String name;

    @JsonProperty("non_text_info_resources")
    public List<NonTextInfoResource> nonTextInfoResources;

    public String path;

    public String platform;

    @JsonProperty("request_id")
    public String requestId;

    @JsonProperty("resource_id")
    public String resourceId;

    public List<Resource> resources;

    @JsonProperty("source_platform")
    public Integer sourcePlatform;

    @JsonProperty("text_info_resources")
    public List<TextInfoResource> textInfoResources;

    @JsonProperty("text_template_preset_resource_id")
    public String textTemplatePresetResourceId;

    @JsonProperty("text_to_audio_ids")
    public List<Object> textToAudioIds;

    public String version;

    @JsonProperty("material_text_ranges")
    private List<Object> materialTextRanges;

    @JsonProperty("is_uneven_animation")
    private Boolean isUnevenAnimation;

    @JsonProperty("origin_word_info")
    private WordInfo originWordInfo;

    @JsonProperty("merge_content")
    private String mergeContent;

    @JsonProperty("is_lyric_effect")
    private Boolean isLyricEffect;

    @JsonProperty("is_ai_emoji")
    private Boolean isAiEmoji;

    @JsonProperty("text_template_resource_type")
    private String textTemplateResourceType;

    @JsonProperty("lyric_group_id")
    private String lyricGroupId;

    @JsonProperty("is_dynamic_build")
    private Boolean isDynamicBuild;

    @JsonProperty("current_word_info")
    private WordInfo currentWordInfo;

    @JsonProperty("third_resource_id")
    private String thirdResourceId;

    public AigcConfig getAigcConfig() {
        return aigcConfig;
    }

    public TextTemplate setAigcConfig(AigcConfig aigcConfig) {
        this.aigcConfig = aigcConfig;
        return this;
    }

    public String getAigcType() {
        return aigcType;
    }

    public TextTemplate setAigcType(String aigcType) {
        this.aigcType = aigcType;
        return this;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public TextTemplate setCategoryId(String categoryId) {
        this.categoryId = categoryId;
        return this;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public TextTemplate setCategoryName(String categoryName) {
        this.categoryName = categoryName;
        return this;
    }

    public Integer getCheckFlag() {
        return checkFlag;
    }

    public TextTemplate setCheckFlag(Integer checkFlag) {
        this.checkFlag = checkFlag;
        return this;
    }

    public String getEffectId() {
        return effectId;
    }

    public TextTemplate setEffectId(String effectId) {
        this.effectId = effectId;
        return this;
    }

    public String getFormulaId() {
        return formulaId;
    }

    public TextTemplate setFormulaId(String formulaId) {
        this.formulaId = formulaId;
        return this;
    }

    public TextTemplate setId(String id) {
        this.id = id;
        return this;
    }

    public Boolean getIs3d() {
        return is3d;
    }

    public TextTemplate setIs3d(Boolean is3d) {
        this.is3d = is3d;
        return this;
    }

    public Boolean getPreRendered() {
        return isPreRendered;
    }

    public TextTemplate setPreRendered(Boolean preRendered) {
        isPreRendered = preRendered;
        return this;
    }

    public String getName() {
        return name;
    }

    public TextTemplate setName(String name) {
        this.name = name;
        return this;
    }

    public List<NonTextInfoResource> getNonTextInfoResources() {
        return nonTextInfoResources;
    }

    public TextTemplate setNonTextInfoResources(List<NonTextInfoResource> nonTextInfoResources) {
        this.nonTextInfoResources = nonTextInfoResources;
        return this;
    }

    public String getPath() {
        return path;
    }

    public TextTemplate setPath(String path) {
        this.path = path;
        return this;
    }

    public String getPlatform() {
        return platform;
    }

    public TextTemplate setPlatform(String platform) {
        this.platform = platform;
        return this;
    }

    public String getRequestId() {
        return requestId;
    }

    public TextTemplate setRequestId(String requestId) {
        this.requestId = requestId;
        return this;
    }

    public String getResourceId() {
        return resourceId;
    }

    public TextTemplate setResourceId(String resourceId) {
        this.resourceId = resourceId;
        return this;
    }

    public List<Resource> getResources() {
        return resources;
    }

    public TextTemplate setResources(List<Resource> resources) {
        this.resources = resources;
        return this;
    }

    public Integer getSourcePlatform() {
        return sourcePlatform;
    }

    public TextTemplate setSourcePlatform(Integer sourcePlatform) {
        this.sourcePlatform = sourcePlatform;
        return this;
    }

    public List<TextInfoResource> getTextInfoResources() {
        return textInfoResources;
    }

    public TextTemplate setTextInfoResources(List<TextInfoResource> textInfoResources) {
        this.textInfoResources = textInfoResources;
        return this;
    }

    public String getTextTemplatePresetResourceId() {
        return textTemplatePresetResourceId;
    }

    public TextTemplate setTextTemplatePresetResourceId(String textTemplatePresetResourceId) {
        this.textTemplatePresetResourceId = textTemplatePresetResourceId;
        return this;
    }

    public List<Object> getTextToAudioIds() {
        return textToAudioIds;
    }

    public TextTemplate setTextToAudioIds(List<Object> textToAudioIds) {
        this.textToAudioIds = textToAudioIds;
        return this;
    }

    public TextTemplate setType(String type) {
        this.type = type;
        return this;
    }

    public String getVersion() {
        return version;
    }

    public TextTemplate setVersion(String version) {
        this.version = version;
        return this;
    }

    public List<Object> getMaterialTextRanges() {
        return materialTextRanges;
    }

    public TextTemplate setMaterialTextRanges(List<Object> materialTextRanges) {
        this.materialTextRanges = materialTextRanges;
        return this;
    }

    public Boolean getUnevenAnimation() {
        return isUnevenAnimation;
    }

    public TextTemplate setUnevenAnimation(Boolean unevenAnimation) {
        isUnevenAnimation = unevenAnimation;
        return this;
    }

    public WordInfo getOriginWordInfo() {
        return originWordInfo;
    }

    public TextTemplate setOriginWordInfo(WordInfo originWordInfo) {
        this.originWordInfo = originWordInfo;
        return this;
    }

    public String getMergeContent() {
        return mergeContent;
    }

    public TextTemplate setMergeContent(String mergeContent) {
        this.mergeContent = mergeContent;
        return this;
    }

    public Boolean getLyricEffect() {
        return isLyricEffect;
    }

    public TextTemplate setLyricEffect(Boolean lyricEffect) {
        isLyricEffect = lyricEffect;
        return this;
    }

    public Boolean getAiEmoji() {
        return isAiEmoji;
    }

    public TextTemplate setAiEmoji(Boolean aiEmoji) {
        isAiEmoji = aiEmoji;
        return this;
    }

    public String getTextTemplateResourceType() {
        return textTemplateResourceType;
    }

    public TextTemplate setTextTemplateResourceType(String textTemplateResourceType) {
        this.textTemplateResourceType = textTemplateResourceType;
        return this;
    }

    public String getLyricGroupId() {
        return lyricGroupId;
    }

    public TextTemplate setLyricGroupId(String lyricGroupId) {
        this.lyricGroupId = lyricGroupId;
        return this;
    }

    public Boolean getDynamicBuild() {
        return isDynamicBuild;
    }

    public TextTemplate setDynamicBuild(Boolean dynamicBuild) {
        isDynamicBuild = dynamicBuild;
        return this;
    }

    public WordInfo getCurrentWordInfo() {
        return currentWordInfo;
    }

    public TextTemplate setCurrentWordInfo(WordInfo currentWordInfo) {
        this.currentWordInfo = currentWordInfo;
        return this;
    }

    public String getThirdResourceId() {
        return thirdResourceId;
    }

    public TextTemplate setThirdResourceId(String thirdResourceId) {
        this.thirdResourceId = thirdResourceId;
        return this;
    }
}
