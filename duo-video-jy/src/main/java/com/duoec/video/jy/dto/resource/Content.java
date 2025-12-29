package com.duoec.video.jy.dto.resource;

import java.io.Serializable;
import java.util.List;

public class Content implements Serializable {
    private String type;

    private ContentRoot root;

    private List<ContentItem> children;

    public String getType() {
        return type;
    }

    public Content setType(String type) {
        this.type = type;
        return this;
    }

    public ContentRoot getRoot() {
        return root;
    }

    public Content setRoot(ContentRoot root) {
        this.root = root;
        return this;
    }

    public List<ContentItem> getChildren() {
        return children;
    }

    public Content setChildren(List<ContentItem> children) {
        this.children = children;
        return this;
    }
}
