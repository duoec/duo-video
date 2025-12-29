
package com.duoec.video.jy.dto.meta;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

public class DraftEnterpriseInfo implements Serializable {

    @JsonProperty("draft_enterprise_extra")
    public String draftEnterpriseExtra;

    @JsonProperty("draft_enterprise_id")
    public String draftEnterpriseId;

    @JsonProperty("draft_enterprise_name")
    public String draftEnterpriseName;

    @JsonProperty("enterprise_material")
    public List<Object> enterpriseMaterial;

    public String getDraftEnterpriseExtra() {
        return draftEnterpriseExtra;
    }

    public DraftEnterpriseInfo setDraftEnterpriseExtra(String draftEnterpriseExtra) {
        this.draftEnterpriseExtra = draftEnterpriseExtra;
        return this;
    }

    public String getDraftEnterpriseId() {
        return draftEnterpriseId;
    }

    public DraftEnterpriseInfo setDraftEnterpriseId(String draftEnterpriseId) {
        this.draftEnterpriseId = draftEnterpriseId;
        return this;
    }

    public String getDraftEnterpriseName() {
        return draftEnterpriseName;
    }

    public DraftEnterpriseInfo setDraftEnterpriseName(String draftEnterpriseName) {
        this.draftEnterpriseName = draftEnterpriseName;
        return this;
    }

    public List<Object> getEnterpriseMaterial() {
        return enterpriseMaterial;
    }

    public DraftEnterpriseInfo setEnterpriseMaterial(List<Object> enterpriseMaterial) {
        this.enterpriseMaterial = enterpriseMaterial;
        return this;
    }
}
