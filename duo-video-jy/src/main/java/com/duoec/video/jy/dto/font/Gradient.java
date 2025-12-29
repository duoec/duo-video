package com.duoec.video.jy.dto.font;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

@Data
@Accessors(chain = true)
public class Gradient implements Serializable {
    private String mode;

    private Integer angle;

    private List<Double> percent;

    private List<Integer> alpha;

    private List<List<Double>> color;
}
