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
        @JsonSubTypes.Type(name = MaterialTypeEnum.MATERIAL_TYPE_VIDEO, value = VideoMaterial.class),
        @JsonSubTypes.Type(name = MaterialTypeEnum.MATERIAL_TYPE_IMAGE, value = ImageMaterial.class),

        @JsonSubTypes.Type(name = MaterialTypeEnum.MATERIAL_TYPE_TEXT, value = TextMaterial.class),
        @JsonSubTypes.Type(name = MaterialTypeEnum.MATERIAL_TYPE_TEXT_TEMPLATE, value = TextTemplateMaterial.class),
        @JsonSubTypes.Type(name = MaterialTypeEnum.MATERIAL_TYPE_LUT, value = LutMaterial.class),
        @JsonSubTypes.Type(name = MaterialTypeEnum.MATERIAL_TYPE_AUDIO, value = AudioMaterial.class),
        @JsonSubTypes.Type(name = MaterialTypeEnum.MATERIAL_TYPE_SOUND, value = SoundMaterial.class),
        @JsonSubTypes.Type(name = MaterialTypeEnum.MATERIAL_TYPE_STICKER, value = StickerMaterial.class),
        @JsonSubTypes.Type(name = MaterialTypeEnum.MATERIAL_TYPE_TRANSITION, value = TransitionMaterial.class),
        @JsonSubTypes.Type(name = MaterialTypeEnum.MATERIAL_TYPE_VIDEO_EFFECT, value = VideoEffectMaterial.class),
        @JsonSubTypes.Type(name = MaterialTypeEnum.MATERIAL_TYPE_FACE_EFFECT, value = FaceEffectMaterial.class),
        @JsonSubTypes.Type(name = MaterialTypeEnum.MATERIAL_TYPE_MASK, value = MaskMaterial.class),
        @JsonSubTypes.Type(name = MaterialTypeEnum.MATERIAL_TYPE_STYLE, value = StyleMaterial.class)
})
public class BaseMaterial implements Serializable {
    public BaseMaterial() {
        this.type = getType();
    }

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
