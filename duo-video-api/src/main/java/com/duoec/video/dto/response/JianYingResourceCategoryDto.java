package com.duoec.video.dto.response;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class JianYingResourceCategoryDto implements Serializable {
    private Long id;

    private Integer categoryId;

    private String type;

    private String name;
}
