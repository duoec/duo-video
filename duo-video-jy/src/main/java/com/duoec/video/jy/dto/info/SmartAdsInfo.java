
package com.duoec.video.jy.dto.info;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class SmartAdsInfo implements Serializable {

    @JsonProperty("page_from")
    public String pageFrom;

    public String routine;

    @JsonProperty("draft_url")
    public String draftUrl;

    public String getPageFrom() {
        return pageFrom;
    }

    public SmartAdsInfo setPageFrom(String pageFrom) {
        this.pageFrom = pageFrom;
        return this;
    }

    public String getRoutine() {
        return routine;
    }

    public SmartAdsInfo setRoutine(String routine) {
        this.routine = routine;
        return this;
    }

    public String getDraftUrl() {
        return draftUrl;
    }

    public SmartAdsInfo setDraftUrl(String draftUrl) {
        this.draftUrl = draftUrl;
        return this;
    }
}
