package com.duoec.video.jy.dto.info;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

public class AiBeats implements Serializable
{

    @JsonProperty("beat_speed_infos")
    public List<Object> beatSpeedInfoList;

    @JsonProperty("beats_path")
    public String beatsPath;

    @JsonProperty("beats_url")
    public String beatsUrl;

    @JsonProperty("melody_path")
    public String melodyPath;

    @JsonProperty("melody_percents")
    public List<Double> melodyPercents;

    @JsonProperty("melody_url")
    public String melodyUrl;

    public List<Object> getBeatSpeedInfoList() {
        return beatSpeedInfoList;
    }

    public AiBeats setBeatSpeedInfoList(List<Object> beatSpeedInfoList) {
        this.beatSpeedInfoList = beatSpeedInfoList;
        return this;
    }

    public String getBeatsPath() {
        return beatsPath;
    }

    public AiBeats setBeatsPath(String beatsPath) {
        this.beatsPath = beatsPath;
        return this;
    }

    public String getBeatsUrl() {
        return beatsUrl;
    }

    public AiBeats setBeatsUrl(String beatsUrl) {
        this.beatsUrl = beatsUrl;
        return this;
    }

    public String getMelodyPath() {
        return melodyPath;
    }

    public AiBeats setMelodyPath(String melodyPath) {
        this.melodyPath = melodyPath;
        return this;
    }

    public List<Double> getMelodyPercents() {
        return melodyPercents;
    }

    public AiBeats setMelodyPercents(List<Double> melodyPercents) {
        this.melodyPercents = melodyPercents;
        return this;
    }

    public String getMelodyUrl() {
        return melodyUrl;
    }

    public AiBeats setMelodyUrl(String melodyUrl) {
        this.melodyUrl = melodyUrl;
        return this;
    }
}
