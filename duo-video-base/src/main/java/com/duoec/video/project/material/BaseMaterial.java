package com.duoec.video.project.material;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;

import java.io.File;
import java.io.Serializable;

@Data
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(
                value = VideoMaterial.class,
                name = MaterialTypeEnum.MATERIAL_TYPE_VIDEO
        ),
        @JsonSubTypes.Type(
                value = ImageMaterial.class,
                name = MaterialTypeEnum.MATERIAL_TYPE_IMAGE
        ),
        @JsonSubTypes.Type(
                value = VideoGreenBackgroundMaterial.class,
                name = MaterialTypeEnum.MATERIAL_TYPE_GREEN_BG_VIDEO
        ),
        @JsonSubTypes.Type(
                value = TextMaterial.class,
                name = MaterialTypeEnum.MATERIAL_TYPE_TEXT
        ),
        @JsonSubTypes.Type(
                value = SubtitleMaterial.class,
                name = MaterialTypeEnum.MATERIAL_TYPE_SUBTITLE
        ),
        @JsonSubTypes.Type(
                value = TextTemplateMaterial.class,
                name = MaterialTypeEnum.MATERIAL_TYPE_TEXT_TEMPLATE
        ),
        @JsonSubTypes.Type(
                value = LutMaterial.class,
                name = MaterialTypeEnum.MATERIAL_TYPE_LUT
        ),
        @JsonSubTypes.Type(
                value = AudioMaterial.class,
                name = MaterialTypeEnum.MATERIAL_TYPE_AUDIO
        )
})
public class BaseMaterial implements Serializable {
    /**
     * 素材ID
     */
    private Long id;

    /**
     * 素材链接
     */
    private String url;

    /**
     * 素材类型
     */
    private String type;

    /**
     * 本地文件
     */
    @JsonIgnore
    private File localFile;
}
