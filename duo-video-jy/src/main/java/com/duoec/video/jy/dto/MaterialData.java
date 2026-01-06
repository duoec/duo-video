package com.duoec.video.jy.dto;

import com.duoec.video.jy.dto.info.BaseMaterial;
import com.duoec.video.jy.dto.info.Segment;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MaterialData {
    private Segment segment;

    private BaseMaterial material;

    private Boolean fromCache = false;

    private String combinationDraftId;
}
