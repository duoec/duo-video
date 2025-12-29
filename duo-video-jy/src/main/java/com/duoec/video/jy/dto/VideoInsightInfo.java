package com.duoec.video.jy.dto;

public class VideoInsightInfo {
    /**
     * 旋转角度
     */
    private Integer rotate;

    /**
     * 绿幕颜色，如果是绿幕视频；否则为空
     */
    private String dominantColorRgb;

    /**
     * 是否有语音 -1: 无 0:未知 1:有
     */
    private Integer hasVoice;

    public Integer getRotate() {
        return rotate;
    }

    public VideoInsightInfo setRotate(Integer rotate) {
        this.rotate = rotate;
        return this;
    }

    public String getDominantColorRgb() {
        return dominantColorRgb;
    }

    public VideoInsightInfo setDominantColorRgb(String dominantColorRgb) {
        this.dominantColorRgb = dominantColorRgb;
        return this;
    }

    public Integer getHasVoice() {
        return hasVoice;
    }

    public VideoInsightInfo setHasVoice(Integer hasVoice) {
        this.hasVoice = hasVoice;
        return this;
    }
}
