package com.duoec.video.project;

import lombok.Data;

import java.io.Serializable;

@Data
public class MaterialReference implements Serializable {
    private Integer id;

    private String type;
}
