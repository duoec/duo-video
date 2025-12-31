
package com.duoec.video.jy.dto.info;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

public class FunctionAssistantInfo implements Serializable {

    @JsonProperty("auto_adjust")
    public Boolean autoAdjust;

    @JsonProperty("auto_caption")
    public Boolean autoCaption;

    @JsonProperty("enhande_voice")
    public Boolean enhandeVoice;

    @JsonProperty("auto_adjust_fixed")
    public Boolean autoAdjustFixed;

    @JsonProperty("fixed_rec_applied")
    public Boolean fixedRecApplied;

    @JsonProperty("retouch_segid_list")
    public List<Object> retouchSegidList;

    public Boolean retouch;

    @JsonProperty("enhande_voice_fixed")
    public Boolean enhandeVoiceFixed;

    @JsonProperty("enhance_quality_segid_list")
    public List<Object> enhanceQualitySegidList;

    @JsonProperty("deflicker_segid_list")
    public List<Object> deflickerSegidList;

    @JsonProperty("auto_caption_template_id")
    public String autoCaptionTemplateId;

    @JsonProperty("color_correction")
    public Boolean colorCorrection;

    @JsonProperty("caption_opt")
    public Boolean captionOpt;

    @JsonProperty("audio_noise_segid_list")
    public List<Object> audioNoiseSegidList;

    @JsonProperty("auto_caption_segid_list")
    public List<Object> autoCaptionSegidList;

    @JsonProperty("normalize_loudness_fixed")
    public Boolean normalizeLoudnessFixed;

    @JsonProperty("normalize_loudness_segid_list")
    public List<Object> normalizeLoudnessSegidList;

    @JsonProperty("eye_correction")
    public Boolean eyeCorrection;

    @JsonProperty("enhance_quality_fixed")
    public Boolean enhanceQualityFixed;

    @JsonProperty("color_correction_fixed_value")
    public Integer colorCorrectionFixedValue;

    @JsonProperty("normalize_loudness_audio_denoise_segid_list")
    public List<Object> normalizeLoudnessAudioDenoiseSegidList;

    @JsonProperty("video_noise_segid_list")
    public List<Object> videoNoiseSegidList;

    @JsonProperty("eye_correction_segid_list")
    public List<Object> eyeCorrectionSegidList;

    @JsonProperty("retouch_fixed")
    public Boolean retouchFixed;

    @JsonProperty("auto_adjust_segid_list")
    public List<Object> autoAdjustSegidList;

    @JsonProperty("enhance_voice_segid_list")
    public List<Object> enhanceVoiceSegidList;

    @JsonProperty("smooth_slow_motion_fixed")
    public Boolean smoothSlowMotionFixed;

    @JsonProperty("caption_opt_segid_list")
    public List<Object> captionOptSegidList;

    @JsonProperty("color_correction_segid_list")
    public List<Object> colorCorrectionSegidList;

    @JsonProperty("smooth_slow_motion")
    public Boolean smoothSlowMotion;

    @JsonProperty("smart_segid_list")
    public List<Object> smartSegidList;

    @JsonProperty("smart_rec_applied")
    public Boolean smartRecApplied;

    @JsonProperty("color_correction_fixed")
    public Boolean colorCorrectionFixed;

    public FpsInfo fps;

    @JsonProperty("auto_adjust_fixed_value")
    public Integer autoAdjustFixedValue;

    @JsonProperty("normalize_loudness")
    public Boolean normalizeLoudness;

    @JsonProperty("enhance_quality")
    public Boolean enhanceQuality;

    public Boolean getAutoAdjust() {
        return autoAdjust;
    }

    public FunctionAssistantInfo setAutoAdjust(Boolean autoAdjust) {
        this.autoAdjust = autoAdjust;
        return this;
    }

    public Boolean getAutoCaption() {
        return autoCaption;
    }

    public FunctionAssistantInfo setAutoCaption(Boolean autoCaption) {
        this.autoCaption = autoCaption;
        return this;
    }

    public Boolean getEnhandeVoice() {
        return enhandeVoice;
    }

    public FunctionAssistantInfo setEnhandeVoice(Boolean enhandeVoice) {
        this.enhandeVoice = enhandeVoice;
        return this;
    }

    public Boolean getAutoAdjustFixed() {
        return autoAdjustFixed;
    }

    public FunctionAssistantInfo setAutoAdjustFixed(Boolean autoAdjustFixed) {
        this.autoAdjustFixed = autoAdjustFixed;
        return this;
    }

    public Boolean getFixedRecApplied() {
        return fixedRecApplied;
    }

    public FunctionAssistantInfo setFixedRecApplied(Boolean fixedRecApplied) {
        this.fixedRecApplied = fixedRecApplied;
        return this;
    }

    public List<Object> getRetouchSegidList() {
        return retouchSegidList;
    }

    public FunctionAssistantInfo setRetouchSegidList(List<Object> retouchSegidList) {
        this.retouchSegidList = retouchSegidList;
        return this;
    }

    public Boolean getRetouch() {
        return retouch;
    }

    public FunctionAssistantInfo setRetouch(Boolean retouch) {
        this.retouch = retouch;
        return this;
    }

    public Boolean getEnhandeVoiceFixed() {
        return enhandeVoiceFixed;
    }

    public FunctionAssistantInfo setEnhandeVoiceFixed(Boolean enhandeVoiceFixed) {
        this.enhandeVoiceFixed = enhandeVoiceFixed;
        return this;
    }

    public List<Object> getEnhanceQualitySegidList() {
        return enhanceQualitySegidList;
    }

    public FunctionAssistantInfo setEnhanceQualitySegidList(List<Object> enhanceQualitySegidList) {
        this.enhanceQualitySegidList = enhanceQualitySegidList;
        return this;
    }

    public List<Object> getDeflickerSegidList() {
        return deflickerSegidList;
    }

    public FunctionAssistantInfo setDeflickerSegidList(List<Object> deflickerSegidList) {
        this.deflickerSegidList = deflickerSegidList;
        return this;
    }

    public String getAutoCaptionTemplateId() {
        return autoCaptionTemplateId;
    }

    public FunctionAssistantInfo setAutoCaptionTemplateId(String autoCaptionTemplateId) {
        this.autoCaptionTemplateId = autoCaptionTemplateId;
        return this;
    }

    public Boolean getColorCorrection() {
        return colorCorrection;
    }

    public FunctionAssistantInfo setColorCorrection(Boolean colorCorrection) {
        this.colorCorrection = colorCorrection;
        return this;
    }

    public Boolean getCaptionOpt() {
        return captionOpt;
    }

    public FunctionAssistantInfo setCaptionOpt(Boolean captionOpt) {
        this.captionOpt = captionOpt;
        return this;
    }

    public List<Object> getAudioNoiseSegidList() {
        return audioNoiseSegidList;
    }

    public FunctionAssistantInfo setAudioNoiseSegidList(List<Object> audioNoiseSegidList) {
        this.audioNoiseSegidList = audioNoiseSegidList;
        return this;
    }

    public List<Object> getAutoCaptionSegidList() {
        return autoCaptionSegidList;
    }

    public FunctionAssistantInfo setAutoCaptionSegidList(List<Object> autoCaptionSegidList) {
        this.autoCaptionSegidList = autoCaptionSegidList;
        return this;
    }

    public Boolean getNormalizeLoudnessFixed() {
        return normalizeLoudnessFixed;
    }

    public FunctionAssistantInfo setNormalizeLoudnessFixed(Boolean normalizeLoudnessFixed) {
        this.normalizeLoudnessFixed = normalizeLoudnessFixed;
        return this;
    }

    public List<Object> getNormalizeLoudnessSegidList() {
        return normalizeLoudnessSegidList;
    }

    public FunctionAssistantInfo setNormalizeLoudnessSegidList(List<Object> normalizeLoudnessSegidList) {
        this.normalizeLoudnessSegidList = normalizeLoudnessSegidList;
        return this;
    }

    public Boolean getEyeCorrection() {
        return eyeCorrection;
    }

    public FunctionAssistantInfo setEyeCorrection(Boolean eyeCorrection) {
        this.eyeCorrection = eyeCorrection;
        return this;
    }

    public Boolean getEnhanceQualityFixed() {
        return enhanceQualityFixed;
    }

    public FunctionAssistantInfo setEnhanceQualityFixed(Boolean enhanceQualityFixed) {
        this.enhanceQualityFixed = enhanceQualityFixed;
        return this;
    }

    public Integer getColorCorrectionFixedValue() {
        return colorCorrectionFixedValue;
    }

    public FunctionAssistantInfo setColorCorrectionFixedValue(Integer colorCorrectionFixedValue) {
        this.colorCorrectionFixedValue = colorCorrectionFixedValue;
        return this;
    }

    public List<Object> getNormalizeLoudnessAudioDenoiseSegidList() {
        return normalizeLoudnessAudioDenoiseSegidList;
    }

    public FunctionAssistantInfo setNormalizeLoudnessAudioDenoiseSegidList(List<Object> normalizeLoudnessAudioDenoiseSegidList) {
        this.normalizeLoudnessAudioDenoiseSegidList = normalizeLoudnessAudioDenoiseSegidList;
        return this;
    }

    public List<Object> getVideoNoiseSegidList() {
        return videoNoiseSegidList;
    }

    public FunctionAssistantInfo setVideoNoiseSegidList(List<Object> videoNoiseSegidList) {
        this.videoNoiseSegidList = videoNoiseSegidList;
        return this;
    }

    public List<Object> getEyeCorrectionSegidList() {
        return eyeCorrectionSegidList;
    }

    public FunctionAssistantInfo setEyeCorrectionSegidList(List<Object> eyeCorrectionSegidList) {
        this.eyeCorrectionSegidList = eyeCorrectionSegidList;
        return this;
    }

    public Boolean getRetouchFixed() {
        return retouchFixed;
    }

    public FunctionAssistantInfo setRetouchFixed(Boolean retouchFixed) {
        this.retouchFixed = retouchFixed;
        return this;
    }

    public List<Object> getAutoAdjustSegidList() {
        return autoAdjustSegidList;
    }

    public FunctionAssistantInfo setAutoAdjustSegidList(List<Object> autoAdjustSegidList) {
        this.autoAdjustSegidList = autoAdjustSegidList;
        return this;
    }

    public List<Object> getEnhanceVoiceSegidList() {
        return enhanceVoiceSegidList;
    }

    public FunctionAssistantInfo setEnhanceVoiceSegidList(List<Object> enhanceVoiceSegidList) {
        this.enhanceVoiceSegidList = enhanceVoiceSegidList;
        return this;
    }

    public Boolean getSmoothSlowMotionFixed() {
        return smoothSlowMotionFixed;
    }

    public FunctionAssistantInfo setSmoothSlowMotionFixed(Boolean smoothSlowMotionFixed) {
        this.smoothSlowMotionFixed = smoothSlowMotionFixed;
        return this;
    }

    public List<Object> getCaptionOptSegidList() {
        return captionOptSegidList;
    }

    public FunctionAssistantInfo setCaptionOptSegidList(List<Object> captionOptSegidList) {
        this.captionOptSegidList = captionOptSegidList;
        return this;
    }

    public List<Object> getColorCorrectionSegidList() {
        return colorCorrectionSegidList;
    }

    public FunctionAssistantInfo setColorCorrectionSegidList(List<Object> colorCorrectionSegidList) {
        this.colorCorrectionSegidList = colorCorrectionSegidList;
        return this;
    }

    public Boolean getSmoothSlowMotion() {
        return smoothSlowMotion;
    }

    public FunctionAssistantInfo setSmoothSlowMotion(Boolean smoothSlowMotion) {
        this.smoothSlowMotion = smoothSlowMotion;
        return this;
    }

    public List<Object> getSmartSegidList() {
        return smartSegidList;
    }

    public FunctionAssistantInfo setSmartSegidList(List<Object> smartSegidList) {
        this.smartSegidList = smartSegidList;
        return this;
    }

    public Boolean getSmartRecApplied() {
        return smartRecApplied;
    }

    public FunctionAssistantInfo setSmartRecApplied(Boolean smartRecApplied) {
        this.smartRecApplied = smartRecApplied;
        return this;
    }

    public Boolean getColorCorrectionFixed() {
        return colorCorrectionFixed;
    }

    public FunctionAssistantInfo setColorCorrectionFixed(Boolean colorCorrectionFixed) {
        this.colorCorrectionFixed = colorCorrectionFixed;
        return this;
    }

    public FpsInfo getFps() {
        return fps;
    }

    public FunctionAssistantInfo setFps(FpsInfo fps) {
        this.fps = fps;
        return this;
    }

    public Integer getAutoAdjustFixedValue() {
        return autoAdjustFixedValue;
    }

    public FunctionAssistantInfo setAutoAdjustFixedValue(Integer autoAdjustFixedValue) {
        this.autoAdjustFixedValue = autoAdjustFixedValue;
        return this;
    }

    public Boolean getNormalizeLoudness() {
        return normalizeLoudness;
    }

    public FunctionAssistantInfo setNormalizeLoudness(Boolean normalizeLoudness) {
        this.normalizeLoudness = normalizeLoudness;
        return this;
    }

    public Boolean getEnhanceQuality() {
        return enhanceQuality;
    }

    public FunctionAssistantInfo setEnhanceQuality(Boolean enhanceQuality) {
        this.enhanceQuality = enhanceQuality;
        return this;
    }
}
