
package com.duoec.video.jy.dto.font;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class Content implements Serializable {
    public static final String RENDER_TYPE_SOLID = "solid";

    @JsonProperty("render_type")
    public String renderType;

    public Solid solid;

    public Gradient gradient;
}
