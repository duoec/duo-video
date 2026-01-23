package com.duoec.video.jy.dto;

import com.duoec.video.jy.dto.info.Animation;
import com.duoec.video.jy.dto.info.Effect;
import com.duoec.video.jy.dto.info.Text;
import com.duoec.video.jy.dto.info.TextTemplate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TextTemplateDto {
    TextTemplate textTemplate;
    List<Text> texts;
    List<Effect> effects;
    String version;
}
