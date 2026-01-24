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
                value = TextMaterial.class,
                name = MaterialTypeEnum.MATERIAL_TYPE_TEXT
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
        ),
        @JsonSubTypes.Type(
                value = SoundMaterial.class,
                name = MaterialTypeEnum.MATERIAL_TYPE_SOUND
        ),
        @JsonSubTypes.Type(
                value = StickerMaterial.class,
                name = MaterialTypeEnum.MATERIAL_TYPE_STICKER
        ),
        @JsonSubTypes.Type(
                value = TransitionMaterial.class,
                name = MaterialTypeEnum.MATERIAL_TYPE_TRANSITION
        ),
        @JsonSubTypes.Type(
                value = VideoEffectMaterial.class,
                name = MaterialTypeEnum.MATERIAL_TYPE_VIDEO_EFFECT
        ),
        @JsonSubTypes.Type(
                value = FaceEffectMaterial.class,
                name = MaterialTypeEnum.MATERIAL_TYPE_FACE_EFFECT
        ),
        @JsonSubTypes.Type(
                value = MaskMaterial.class,
                name = MaterialTypeEnum.MATERIAL_TYPE_MASK
        ),
        @JsonSubTypes.Type(
                value = StyleMaterial.class,
                name = MaterialTypeEnum.MATERIAL_TYPE_STYLE
        )
})
public class BaseMaterial implements Serializable {
    /**
     * 素材ID。
     * 素材ID在系统层面应该是唯一的，比如它就是数据库里的一个ID。在后续的创作中，会以此ID为名称，缓存到本地。如果ID重复，会导致文件错乱！！
     */
    private Long id;

    /**
     * 素材链接
     * 链接必须是可直接下载的，如果需要签名，可能会导致下载失败而最终创作失败
     */
    private String url;

    /**
     * 素材类型
     */
    private String type;

    /**
     * 本地文件
     * 创作过程，一些多媒体素材将直接设置进来，方便后续的引用
     * 本数据不会写入工程文件！
     */
    @JsonIgnore
    private File localFile;
}
