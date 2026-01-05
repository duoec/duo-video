package com.duoec.video.project.material;

public enum MaterialTypeEnum {

    /**
     * 视频素材
     */
    VIDEO_MATERIAL(MaterialTypeEnum.MATERIAL_TYPE_VIDEO),

    /**
     * 图片素材
     */
    IMAGE_MATERIAL(MaterialTypeEnum.MATERIAL_TYPE_IMAGE),

    /**
     * 贴纸
     */
    STICKER_MATERIAL(MaterialTypeEnum.MATERIAL_TYPE_STICKER),

    /**
     * 音频
     */
    AUDIO_MATERIAL(MaterialTypeEnum.MATERIAL_TYPE_AUDIO),

    /**
     * 特效音
     */
    SOUND_MATERIAL(MaterialTypeEnum.MATERIAL_TYPE_SOUND),

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
     * 转场
     */
    TRANSITION_MATERIAL(MaterialTypeEnum.MATERIAL_TYPE_TRANSITION),

    /**
     * LUT
     */
    LUT_MATERIAL(MaterialTypeEnum.MATERIAL_TYPE_LUT),
    ;

    public static final String MATERIAL_TYPE_VIDEO = "video";
    public static final String MATERIAL_TYPE_IMAGE = "image";
    public static final String MATERIAL_TYPE_STICKER = "sticker";
    public static final String MATERIAL_TYPE_AUDIO = "audio";
    public static final String MATERIAL_TYPE_SOUND = "sound";
    public static final String MATERIAL_TYPE_TEXT = "text";
    public static final String MATERIAL_TYPE_SUBTITLE = "subtitle";
    public static final String MATERIAL_TYPE_TEXT_TEMPLATE = "textTemplate";
    public static final String MATERIAL_TYPE_LUT = "lut";
    public static final String MATERIAL_TYPE_TRANSITION = "transition";

    private final String type;

    MaterialTypeEnum(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
