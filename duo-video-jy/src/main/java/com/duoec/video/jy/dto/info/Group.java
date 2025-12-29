package com.duoec.video.jy.dto.info;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

public class Group implements Serializable {
    @JsonProperty("group_index")
    private Integer groupIndex;

    @JsonProperty("group_main_id")
    private String groupMainId;

    @JsonProperty("group_members")
    private List<String> groupMembers;

    private String id;

    @JsonProperty("is_expand")
    private Boolean expand;

    @JsonProperty("material_draft")
    private Draft materialDraft;

    private String name;

    public Integer getGroupIndex() {
        return groupIndex;
    }

    public Group setGroupIndex(Integer groupIndex) {
        this.groupIndex = groupIndex;
        return this;
    }

    public String getGroupMainId() {
        return groupMainId;
    }

    public Group setGroupMainId(String groupMainId) {
        this.groupMainId = groupMainId;
        return this;
    }

    public List<String> getGroupMembers() {
        return groupMembers;
    }

    public Group setGroupMembers(List<String> groupMembers) {
        this.groupMembers = groupMembers;
        return this;
    }

    public String getId() {
        return id;
    }

    public Group setId(String id) {
        this.id = id;
        return this;
    }

    public Boolean getExpand() {
        return expand;
    }

    public Group setExpand(Boolean expand) {
        this.expand = expand;
        return this;
    }

    public Draft getMaterialDraft() {
        return materialDraft;
    }

    public Group setMaterialDraft(Draft materialDraft) {
        this.materialDraft = materialDraft;
        return this;
    }

    public String getName() {
        return name;
    }

    public Group setName(String name) {
        this.name = name;
        return this;
    }
}
