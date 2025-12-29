package com.duoec.video.jy.dto.info;

import java.io.Serializable;
import java.util.List;

public class GroupContainer implements Serializable {
    private List<Group> groups;

    public List<Group> getGroups() {
        return groups;
    }

    public GroupContainer setGroups(List<Group> groups) {
        this.groups = groups;
        return this;
    }
}
