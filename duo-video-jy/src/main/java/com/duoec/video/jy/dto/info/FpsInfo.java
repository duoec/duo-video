
package com.duoec.video.jy.dto.info;

import java.io.Serializable;

public class FpsInfo implements Serializable {

    public Integer den;

    public Integer num;

    public Integer getDen() {
        return den;
    }

    public FpsInfo setDen(Integer den) {
        this.den = den;
        return this;
    }

    public Integer getNum() {
        return num;
    }

    public FpsInfo setNum(Integer num) {
        this.num = num;
        return this;
    }
}
