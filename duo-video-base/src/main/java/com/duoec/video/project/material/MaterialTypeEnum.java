package com.duoec.video.project.material;

public enum MaterialTypeEnum {

    /**
     * 视频素材
     */
    VIDEO_MATERIAL(MaterialTypeEnum.MATERIAL_TYPE_VIDEO),

    /**
     * 绿幕视频素材
     */
    GREEN_BG_VIDEO_MATERIAL(MaterialTypeEnum.MATERIAL_TYPE_GREEN_BG_VIDEO),

    /**
     * 图片素材
     */
    IMAGE_MATERIAL(MaterialTypeEnum.MATERIAL_TYPE_IMAGE),

    /**
     * 音频
     */
    AUDIO_MATERIAL(MaterialTypeEnum.MATERIAL_TYPE_AUDIO),

    /**
     * 文本
     */
    TEXT_MATERIAL(MaterialTypeEnum.MATERIAL_TYPE_TEXT),

    /**
     * 字幕
     */
    SUBTITLE_MATERIAL(MaterialTypeEnum.MATERIAL_TYPE_SUBTITLE),

    /**
     * 文本模板
     */
    TEXT_TEMPLATE_MATERIAL(MaterialTypeEnum.MATERIAL_TYPE_TEXT_TEMPLATE),

    /**
     * LUT
     */
    LUT_MATERIAL(MaterialTypeEnum.MATERIAL_TYPE_LUT),
    ;

    public static final String MATERIAL_TYPE_VIDEO = "video";
    public static final String MATERIAL_TYPE_GREEN_BG_VIDEO = "greenBgVideo";
    public static final String MATERIAL_TYPE_IMAGE = "image";
    public static final String MATERIAL_TYPE_AUDIO = "audio";
    public static final String MATERIAL_TYPE_TEXT = "text";
    public static final String MATERIAL_TYPE_SUBTITLE = "subtitle";
    public static final String MATERIAL_TYPE_TEXT_TEMPLATE = "textTemplate";
    public static final String MATERIAL_TYPE_LUT = "lut";

    private final String type;

    MaterialTypeEnum(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
