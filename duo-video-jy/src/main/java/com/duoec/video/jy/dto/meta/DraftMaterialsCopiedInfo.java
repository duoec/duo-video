
package com.duoec.video.jy.dto.meta;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class DraftMaterialsCopiedInfo implements Serializable {

    @JsonProperty("dst_path")
    public String dstPath;

    @JsonProperty("src_path")
    public String srcPath;

    public String getDstPath() {
        return dstPath;
    }

    public DraftMaterialsCopiedInfo setDstPath(String dstPath) {
        this.dstPath = dstPath;
        return this;
    }

    public String getSrcPath() {
        return srcPath;
    }

    public DraftMaterialsCopiedInfo setSrcPath(String srcPath) {
        this.srcPath = srcPath;
        return this;
    }
}
