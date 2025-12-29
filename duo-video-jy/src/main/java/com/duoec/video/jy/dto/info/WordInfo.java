
package com.duoec.video.jy.dto.info;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class WordInfo {

    @JsonProperty("end_time")
    private Long endTime;

    @JsonProperty("keyword_ranges")
    private List<Object> keywordRanges;

    @JsonProperty("start_time")
    private Long startTime;

    private List<Object> words;

    private String text;

    public Long getEndTime() {
        return endTime;
    }

    public WordInfo setEndTime(Long endTime) {
        this.endTime = endTime;
        return this;
    }

    public List<Object> getKeywordRanges() {
        return keywordRanges;
    }

    public WordInfo setKeywordRanges(List<Object> keywordRanges) {
        this.keywordRanges = keywordRanges;
        return this;
    }

    public Long getStartTime() {
        return startTime;
    }

    public WordInfo setStartTime(Long startTime) {
        this.startTime = startTime;
        return this;
    }

    public List<Object> getWords() {
        return words;
    }

    public WordInfo setWords(List<Object> words) {
        this.words = words;
        return this;
    }

    public String getText() {
        return text;
    }

    public WordInfo setText(String text) {
        this.text = text;
        return this;
    }
}
