package com.duoec.video.jy.dto;

import com.duoec.video.jy.dto.info.Effect;
import com.duoec.video.jy.dto.info.Text;
import com.duoec.video.jy.dto.info.TextTemplate;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TextTemplateDto {
    Text text;
    TextTemplate textTemplate;
    Effect effect;
    String version;
}
