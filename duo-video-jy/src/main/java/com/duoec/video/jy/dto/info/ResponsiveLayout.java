
package com.duoec.video.jy.dto.info;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class ResponsiveLayout implements Serializable {

    public Boolean enable;

    @JsonProperty("horizontal_pos_layout")
    public Integer horizontalPosLayout;

    @JsonProperty("size_layout")
    public Integer sizeLayout;

    @JsonProperty("target_follow")
    public String targetFollow;

    @JsonProperty("vertical_pos_layout")
    public Integer verticalPosLayout;

    public Boolean getEnable() {
        return enable;
    }

    public ResponsiveLayout setEnable(Boolean enable) {
        this.enable = enable;
        return this;
    }

    public Integer getHorizontalPosLayout() {
        return horizontalPosLayout;
    }

    public ResponsiveLayout setHorizontalPosLayout(Integer horizontalPosLayout) {
        this.horizontalPosLayout = horizontalPosLayout;
        return this;
    }

    public Integer getSizeLayout() {
        return sizeLayout;
    }

    public ResponsiveLayout setSizeLayout(Integer sizeLayout) {
        this.sizeLayout = sizeLayout;
        return this;
    }

    public String getTargetFollow() {
        return targetFollow;
    }

    public ResponsiveLayout setTargetFollow(String targetFollow) {
        this.targetFollow = targetFollow;
        return this;
    }

    public Integer getVerticalPosLayout() {
        return verticalPosLayout;
    }

    public ResponsiveLayout setVerticalPosLayout(Integer verticalPosLayout) {
        this.verticalPosLayout = verticalPosLayout;
        return this;
    }
}
