
package com.duoec.video.jy.dto.info;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Video extends BaseMaterial {

    @JsonProperty("aigc_history_id")
    public String aigcHistoryId;

    @JsonProperty("aigc_item_id")
    public String aigcItemId;

    @JsonProperty("aigc_type")
    public String aigcType;

    @JsonProperty("audio_fade")
    public AudioFade audioFade;

    @JsonProperty("cartoon_path")
    public String cartoonPath;

    @JsonProperty("category_id")
    public String categoryId;

    @JsonProperty("category_name")
    public String categoryName;

    /**
     * HDR转换工具。
     * 开启后可设置 segments.hdr_settings值
     * 62975=关闭
     * 63487=开启 sdr To Rec.709
     * 62978047=Rec.2100 HLG To Rec.709
     */
    @JsonProperty("check_flag")
    public Integer checkFlag;

    public Crop crop;

    @JsonProperty("crop_ratio")
    public String cropRatio;

    @JsonProperty("crop_scale")
    public Double cropScale;

    public Long duration;

    @JsonProperty("extra_type_option")
    public Integer extraTypeOption;

    @JsonProperty("formula_id")
    public String formulaId;

    public Object freeze;

    public Object gameplay;

    @JsonProperty("has_audio")
    public Boolean hasAudio;

    public Integer height;

    @JsonProperty("intensifies_audio_path")
    public String intensifiesAudioPath;

    @JsonProperty("intensifies_path")
    public String intensifiesPath;

    @JsonProperty("is_ai_generate_content")
    public Boolean isAiGenerateContent;

    @JsonProperty("is_copyright")
    public Boolean isCopyright;

    @JsonProperty("is_unified_beauty_mode")
    public Boolean isUnifiedBeautyMode;

    @JsonProperty("is_text_edit_overdub")
    public Boolean textEditOverdub;

    @JsonProperty("local_id")
    public String localId;

    @JsonProperty("local_material_id")
    public String localMaterialId;

    @JsonProperty("material_id")
    public String materialId;

    @JsonProperty("material_name")
    public String materialName;

    @JsonProperty("material_url")
    public String materialUrl;

    public Matting matting;

    @JsonProperty("media_path")
    public String mediaPath;

    @JsonProperty("object_locked")
    public Object objectLocked;

    @JsonProperty("origin_material_id")
    public String originMaterialId;

    public String path;

    @JsonProperty("picture_from")
    public String pictureFrom;

    @JsonProperty("picture_set_category_id")
    public String pictureSetCategoryId;

    @JsonProperty("picture_set_category_name")
    public String pictureSetCategoryName;

    @JsonProperty("request_id")
    public String requestId;

    @JsonProperty("reverse_intensifies_path")
    public String reverseIntensifiesPath;

    @JsonProperty("reverse_path")
    public String reversePath;

    @JsonProperty("smart_motion")
    public Object smartMotion;

    public Integer source;

    @JsonProperty("source_platform")
    public Integer sourcePlatform;

    public Stable stable;

    @JsonProperty("team_id")
    public String teamId;

    @JsonProperty("video_algorithm")
    public VideoAlgorithm videoAlgorithm;

    public Integer width;

    public String getAigcType() {
        return aigcType;
    }

    public Video setAigcType(String aigcType) {
        this.aigcType = aigcType;
        return this;
    }

    public AudioFade getAudioFade() {
        return audioFade;
    }

    public Video setAudioFade(AudioFade audioFade) {
        this.audioFade = audioFade;
        return this;
    }

    public String getCartoonPath() {
        return cartoonPath;
    }

    public Video setCartoonPath(String cartoonPath) {
        this.cartoonPath = cartoonPath;
        return this;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public Video setCategoryId(String categoryId) {
        this.categoryId = categoryId;
        return this;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public Video setCategoryName(String categoryName) {
        this.categoryName = categoryName;
        return this;
    }

    public Integer getCheckFlag() {
        return checkFlag;
    }

    public Video setCheckFlag(Integer checkFlag) {
        this.checkFlag = checkFlag;
        return this;
    }

    public Crop getCrop() {
        return crop;
    }

    public Video setCrop(Crop crop) {
        this.crop = crop;
        return this;
    }

    public String getCropRatio() {
        return cropRatio;
    }

    public Video setCropRatio(String cropRatio) {
        this.cropRatio = cropRatio;
        return this;
    }

    public Double getCropScale() {
        return cropScale;
    }

    public Video setCropScale(Double cropScale) {
        this.cropScale = cropScale;
        return this;
    }

    public Long getDuration() {
        return duration;
    }

    public Video setDuration(Long duration) {
        this.duration = duration;
        return this;
    }

    public Integer getExtraTypeOption() {
        return extraTypeOption;
    }

    public Video setExtraTypeOption(Integer extraTypeOption) {
        this.extraTypeOption = extraTypeOption;
        return this;
    }

    public String getFormulaId() {
        return formulaId;
    }

    public Video setFormulaId(String formulaId) {
        this.formulaId = formulaId;
        return this;
    }

    public Object getFreeze() {
        return freeze;
    }

    public Video setFreeze(Object freeze) {
        this.freeze = freeze;
        return this;
    }

    public Object getGameplay() {
        return gameplay;
    }

    public Video setGameplay(Object gameplay) {
        this.gameplay = gameplay;
        return this;
    }

    public Boolean getHasAudio() {
        return hasAudio;
    }

    public Video setHasAudio(Boolean hasAudio) {
        this.hasAudio = hasAudio;
        return this;
    }

    public Integer getHeight() {
        return height;
    }

    public Video setHeight(Integer height) {
        this.height = height;
        return this;
    }

    public Video setId(String id) {
        this.id = id;
        return this;
    }

    public String getIntensifiesAudioPath() {
        return intensifiesAudioPath;
    }

    public Video setIntensifiesAudioPath(String intensifiesAudioPath) {
        this.intensifiesAudioPath = intensifiesAudioPath;
        return this;
    }

    public String getIntensifiesPath() {
        return intensifiesPath;
    }

    public Video setIntensifiesPath(String intensifiesPath) {
        this.intensifiesPath = intensifiesPath;
        return this;
    }

    public Boolean getAiGenerateContent() {
        return isAiGenerateContent;
    }

    public Video setAiGenerateContent(Boolean aiGenerateContent) {
        isAiGenerateContent = aiGenerateContent;
        return this;
    }

    public Boolean getUnifiedBeautyMode() {
        return isUnifiedBeautyMode;
    }

    public Video setUnifiedBeautyMode(Boolean unifiedBeautyMode) {
        isUnifiedBeautyMode = unifiedBeautyMode;
        return this;
    }

    public String getLocalId() {
        return localId;
    }

    public Video setLocalId(String localId) {
        this.localId = localId;
        return this;
    }

    public String getLocalMaterialId() {
        return localMaterialId;
    }

    public Video setLocalMaterialId(String localMaterialId) {
        this.localMaterialId = localMaterialId;
        return this;
    }

    public String getMaterialId() {
        return materialId;
    }

    public Video setMaterialId(String materialId) {
        this.materialId = materialId;
        return this;
    }

    public String getMaterialName() {
        return materialName;
    }

    public Video setMaterialName(String materialName) {
        this.materialName = materialName;
        return this;
    }

    public String getMaterialUrl() {
        return materialUrl;
    }

    public Video setMaterialUrl(String materialUrl) {
        this.materialUrl = materialUrl;
        return this;
    }

    public Matting getMatting() {
        return matting;
    }

    public Video setMatting(Matting matting) {
        this.matting = matting;
        return this;
    }

    public String getMediaPath() {
        return mediaPath;
    }

    public Video setMediaPath(String mediaPath) {
        this.mediaPath = mediaPath;
        return this;
    }

    public Object getObjectLocked() {
        return objectLocked;
    }

    public Video setObjectLocked(Object objectLocked) {
        this.objectLocked = objectLocked;
        return this;
    }

    public String getOriginMaterialId() {
        return originMaterialId;
    }

    public Video setOriginMaterialId(String originMaterialId) {
        this.originMaterialId = originMaterialId;
        return this;
    }

    public String getPath() {
        return path;
    }

    public Video setPath(String path) {
        this.path = path;
        return this;
    }

    public String getPictureFrom() {
        return pictureFrom;
    }

    public Video setPictureFrom(String pictureFrom) {
        this.pictureFrom = pictureFrom;
        return this;
    }

    public String getPictureSetCategoryId() {
        return pictureSetCategoryId;
    }

    public Video setPictureSetCategoryId(String pictureSetCategoryId) {
        this.pictureSetCategoryId = pictureSetCategoryId;
        return this;
    }

    public String getPictureSetCategoryName() {
        return pictureSetCategoryName;
    }

    public Video setPictureSetCategoryName(String pictureSetCategoryName) {
        this.pictureSetCategoryName = pictureSetCategoryName;
        return this;
    }

    public String getRequestId() {
        return requestId;
    }

    public Video setRequestId(String requestId) {
        this.requestId = requestId;
        return this;
    }

    public String getReverseIntensifiesPath() {
        return reverseIntensifiesPath;
    }

    public Video setReverseIntensifiesPath(String reverseIntensifiesPath) {
        this.reverseIntensifiesPath = reverseIntensifiesPath;
        return this;
    }

    public String getReversePath() {
        return reversePath;
    }

    public Video setReversePath(String reversePath) {
        this.reversePath = reversePath;
        return this;
    }

    public Object getSmartMotion() {
        return smartMotion;
    }

    public Video setSmartMotion(Object smartMotion) {
        this.smartMotion = smartMotion;
        return this;
    }

    public Integer getSource() {
        return source;
    }

    public Video setSource(Integer source) {
        this.source = source;
        return this;
    }

    public Integer getSourcePlatform() {
        return sourcePlatform;
    }

    public Video setSourcePlatform(Integer sourcePlatform) {
        this.sourcePlatform = sourcePlatform;
        return this;
    }

    public Stable getStable() {
        return stable;
    }

    public Video setStable(Stable stable) {
        this.stable = stable;
        return this;
    }

    public String getTeamId() {
        return teamId;
    }

    public Video setTeamId(String teamId) {
        this.teamId = teamId;
        return this;
    }

    public Video setType(String type) {
        this.type = type;
        return this;
    }

    public VideoAlgorithm getVideoAlgorithm() {
        return videoAlgorithm;
    }

    public Video setVideoAlgorithm(VideoAlgorithm videoAlgorithm) {
        this.videoAlgorithm = videoAlgorithm;
        return this;
    }

    public Integer getWidth() {
        return width;
    }

    public Video setWidth(Integer width) {
        this.width = width;
        return this;
    }

    public Boolean getCopyright() {
        return isCopyright;
    }

    public Video setCopyright(Boolean copyright) {
        isCopyright = copyright;
        return this;
    }

    public String getAigcHistoryId() {
        return aigcHistoryId;
    }

    public Video setAigcHistoryId(String aigcHistoryId) {
        this.aigcHistoryId = aigcHistoryId;
        return this;
    }

    public String getAigcItemId() {
        return aigcItemId;
    }

    public Video setAigcItemId(String aigcItemId) {
        this.aigcItemId = aigcItemId;
        return this;
    }

    public Boolean getTextEditOverdub() {
        return textEditOverdub;
    }

    public Video setTextEditOverdub(Boolean textEditOverdub) {
        this.textEditOverdub = textEditOverdub;
        return this;
    }
}
