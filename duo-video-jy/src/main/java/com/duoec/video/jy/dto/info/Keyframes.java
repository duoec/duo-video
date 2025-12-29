
package com.duoec.video.jy.dto.info;

import java.io.Serializable;
import java.util.List;

public class Keyframes implements Serializable {

    public List<Object> adjusts;

    public List<Object> audios;

    public List<Object> effects;

    public List<Object> filters;

    public List<Object> handwrites;

    public List<Object> getAdjusts() {
        return adjusts;
    }

    public Keyframes setAdjusts(List<Object> adjusts) {
        this.adjusts = adjusts;
        return this;
    }

    public List<Object> getAudios() {
        return audios;
    }

    public Keyframes setAudios(List<Object> audios) {
        this.audios = audios;
        return this;
    }

    public List<Object> getEffects() {
        return effects;
    }

    public Keyframes setEffects(List<Object> effects) {
        this.effects = effects;
        return this;
    }

    public List<Object> getFilters() {
        return filters;
    }

    public Keyframes setFilters(List<Object> filters) {
        this.filters = filters;
        return this;
    }

    public List<Object> getHandwrites() {
        return handwrites;
    }

    public Keyframes setHandwrites(List<Object> handwrites) {
        this.handwrites = handwrites;
        return this;
    }

    public List<Object> getStickers() {
        return stickers;
    }

    public Keyframes setStickers(List<Object> stickers) {
        this.stickers = stickers;
        return this;
    }

    public List<Object> getTexts() {
        return texts;
    }

    public Keyframes setTexts(List<Object> texts) {
        this.texts = texts;
        return this;
    }

    public List<Object> getVideos() {
        return videos;
    }

    public Keyframes setVideos(List<Object> videos) {
        this.videos = videos;
        return this;
    }

    public List<Object> stickers;

    public List<Object> texts;

    public List<Object> videos;


}
