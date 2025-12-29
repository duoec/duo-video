
package com.duoec.video.jy.dto.info;

import java.io.Serializable;

public class Flip implements Serializable {
    public Boolean horizontal;

    public Boolean vertical;

    public Boolean getHorizontal() {
        return horizontal;
    }

    public Flip setHorizontal(Boolean horizontal) {
        this.horizontal = horizontal;
        return this;
    }

    public Boolean getVertical() {
        return vertical;
    }

    public Flip setVertical(Boolean vertical) {
        this.vertical = vertical;
        return this;
    }
}
