
package com.duoec.video.jy.dto.info;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

public class Config implements Serializable {
    @JsonProperty("adjust_max_index")
    public Integer adjustMaxIndex;

    @JsonProperty("attachment_info")
    public List<Object> attachmentInfo;

    @JsonProperty("combination_max_index")
    public Integer combinationMaxIndex;

    @JsonProperty("export_range")
    public Object exportRange;

    @JsonProperty("extract_audio_last_index")
    public Integer extractAudioLastIndex;

    @JsonProperty("lyrics_recognition_id")
    public String lyricsRecognitionId;

    @JsonProperty("lyrics_sync")
    public Boolean lyricsSync;

    @JsonProperty("lyrics_taskinfo")
    public List<Object> lyricsTaskInfo;

    @JsonProperty("maintrack_adsorb")
    public Boolean mainTrackAdsorb;

    @JsonProperty("material_save_mode")
    public Integer materialSaveMode;

    @JsonProperty("original_sound_last_index")
    public Integer originalSoundLastIndex;

    @JsonProperty("record_audio_last_index")
    public Integer recordAudioLastIndex;

    @JsonProperty("sticker_max_index")
    public Integer stickerMaxIndex;

    @JsonProperty("subtitle_recognition_id")
    public String subtitleRecognitionId;

    @JsonProperty("subtitle_sync")
    public Boolean subtitleSync;

    @JsonProperty("subtitle_taskinfo")
    public List<SubtitleTaskInfo> subtitleTaskInfo;

    @JsonProperty("system_font_list")
    public List<Object> systemFontList;

    @JsonProperty("video_mute")
    public Boolean videoMute;

    @JsonProperty("zoom_info_params")
    public Object zoomInfoParams;

    @JsonProperty("subtitle_keywords_config")
    public SubtitleKeywordsConfig subtitleKeywordsConfig;

    public Integer getAdjustMaxIndex() {
        return adjustMaxIndex;
    }

    public Config setAdjustMaxIndex(Integer adjustMaxIndex) {
        this.adjustMaxIndex = adjustMaxIndex;
        return this;
    }

    public List<Object> getAttachmentInfo() {
        return attachmentInfo;
    }

    public Config setAttachmentInfo(List<Object> attachmentInfo) {
        this.attachmentInfo = attachmentInfo;
        return this;
    }

    public Integer getCombinationMaxIndex() {
        return combinationMaxIndex;
    }

    public Config setCombinationMaxIndex(Integer combinationMaxIndex) {
        this.combinationMaxIndex = combinationMaxIndex;
        return this;
    }

    public Object getExportRange() {
        return exportRange;
    }

    public Config setExportRange(Object exportRange) {
        this.exportRange = exportRange;
        return this;
    }

    public Integer getExtractAudioLastIndex() {
        return extractAudioLastIndex;
    }

    public Config setExtractAudioLastIndex(Integer extractAudioLastIndex) {
        this.extractAudioLastIndex = extractAudioLastIndex;
        return this;
    }

    public String getLyricsRecognitionId() {
        return lyricsRecognitionId;
    }

    public Config setLyricsRecognitionId(String lyricsRecognitionId) {
        this.lyricsRecognitionId = lyricsRecognitionId;
        return this;
    }

    public Boolean getLyricsSync() {
        return lyricsSync;
    }

    public Config setLyricsSync(Boolean lyricsSync) {
        this.lyricsSync = lyricsSync;
        return this;
    }

    public List<Object> getLyricsTaskInfo() {
        return lyricsTaskInfo;
    }

    public Config setLyricsTaskInfo(List<Object> lyricsTaskInfo) {
        this.lyricsTaskInfo = lyricsTaskInfo;
        return this;
    }

    public Boolean getMainTrackAdsorb() {
        return mainTrackAdsorb;
    }

    public Config setMainTrackAdsorb(Boolean mainTrackAdsorb) {
        this.mainTrackAdsorb = mainTrackAdsorb;
        return this;
    }

    public Integer getMaterialSaveMode() {
        return materialSaveMode;
    }

    public Config setMaterialSaveMode(Integer materialSaveMode) {
        this.materialSaveMode = materialSaveMode;
        return this;
    }

    public Integer getOriginalSoundLastIndex() {
        return originalSoundLastIndex;
    }

    public Config setOriginalSoundLastIndex(Integer originalSoundLastIndex) {
        this.originalSoundLastIndex = originalSoundLastIndex;
        return this;
    }

    public Integer getRecordAudioLastIndex() {
        return recordAudioLastIndex;
    }

    public Config setRecordAudioLastIndex(Integer recordAudioLastIndex) {
        this.recordAudioLastIndex = recordAudioLastIndex;
        return this;
    }

    public Integer getStickerMaxIndex() {
        return stickerMaxIndex;
    }

    public Config setStickerMaxIndex(Integer stickerMaxIndex) {
        this.stickerMaxIndex = stickerMaxIndex;
        return this;
    }

    public String getSubtitleRecognitionId() {
        return subtitleRecognitionId;
    }

    public Config setSubtitleRecognitionId(String subtitleRecognitionId) {
        this.subtitleRecognitionId = subtitleRecognitionId;
        return this;
    }

    public Boolean getSubtitleSync() {
        return subtitleSync;
    }

    public Config setSubtitleSync(Boolean subtitleSync) {
        this.subtitleSync = subtitleSync;
        return this;
    }

    public List<SubtitleTaskInfo> getSubtitleTaskInfo() {
        return subtitleTaskInfo;
    }

    public Config setSubtitleTaskInfo(List<SubtitleTaskInfo> subtitleTaskInfo) {
        this.subtitleTaskInfo = subtitleTaskInfo;
        return this;
    }

    public List<Object> getSystemFontList() {
        return systemFontList;
    }

    public Config setSystemFontList(List<Object> systemFontList) {
        this.systemFontList = systemFontList;
        return this;
    }

    public Boolean getVideoMute() {
        return videoMute;
    }

    public Config setVideoMute(Boolean videoMute) {
        this.videoMute = videoMute;
        return this;
    }

    public Object getZoomInfoParams() {
        return zoomInfoParams;
    }

    public Config setZoomInfoParams(Object zoomInfoParams) {
        this.zoomInfoParams = zoomInfoParams;
        return this;
    }

    public SubtitleKeywordsConfig getSubtitleKeywordsConfig() {
        return subtitleKeywordsConfig;
    }

    public Config setSubtitleKeywordsConfig(SubtitleKeywordsConfig subtitleKeywordsConfig) {
        this.subtitleKeywordsConfig = subtitleKeywordsConfig;
        return this;
    }
}
