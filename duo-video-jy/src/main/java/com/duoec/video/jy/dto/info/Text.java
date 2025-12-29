
package com.duoec.video.jy.dto.info;

import com.duoec.video.jy.dto.font.FontConfig;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Text extends BaseMaterial {
    public static final String TEXT_CONTENT = "--text--";

    @JsonProperty("add_type")
    private Integer addType;

    /**
     * 对齐方式：0=横左 1=横中 2=横右 3=竖上 4=竖中 5=竖下
     */
    private Integer alignment;

    @JsonProperty("background_alpha")
    private Double backgroundAlpha;

    @JsonProperty("background_color")
    private String backgroundColor;

    @JsonProperty("background_height")
    private Double backgroundHeight;

    @JsonProperty("background_horizontal_offset")
    private Double backgroundHorizontalOffset;

    @JsonProperty("background_round_radius")
    private Double backgroundRoundRadius;

    @JsonProperty("background_style")
    private Integer backgroundStyle;

    @JsonProperty("background_vertical_offset")
    private Double backgroundVerticalOffset;

    @JsonProperty("background_width")
    private Double backgroundWidth;

    @JsonProperty("bold_width")
    private Double boldWidth;

    @JsonProperty("border_alpha")
    private Double borderAlpha;

    @JsonProperty("border_color")
    private String borderColor;

    @JsonProperty("border_width")
    private Double borderWidth;

    @JsonProperty("caption_template_info")
    private CaptionTemplateInfo captionTemplateInfo;

    /**
     * 31=背景
     */
    @JsonProperty("check_flag")
    private Integer checkFlag;

    @JsonProperty("combo_info")
    private ComboInfo comboInfo;

    /**
     * 内容，json结构
     * @see FontConfig
     */
    private String content;

    @JsonProperty("base_content")
    private String baseContent;

    @JsonProperty("fixed_height")
    private Double fixedHeight;

    @JsonProperty("fixed_width")
    private Double fixedWidth;

    @JsonProperty("font_category_id")
    private String fontCategoryId;

    @JsonProperty("font_category_name")
    private String fontCategoryName;

    @JsonProperty("font_id")
    private String fontId;

    @JsonProperty("font_name")
    private String fontName;

    @JsonProperty("font_path")
    private String fontPath;

    @JsonProperty("font_resource_id")
    private String fontResourceId;

    @JsonProperty("font_size")
    private Double fontSize;

    @JsonProperty("font_source_platform")
    private Integer fontSourcePlatform;

    @JsonProperty("font_team_id")
    private String fontTeamId;

    @JsonProperty("font_title")
    private String fontTitle;

    @JsonProperty("font_url")
    private String fontUrl;

    private List<Font> fonts;

    @JsonProperty("force_apply_line_max_width")
    private Boolean forceApplyLineMaxWidth;

    @JsonProperty("global_alpha")
    private Double globalAlpha;

    @JsonProperty("group_id")
    private String groupId;

    @JsonProperty("has_shadow")
    private Boolean hasShadow;

    @JsonProperty("initial_scale")
    private Double initialScale;

    @JsonProperty("is_rich_text")
    private Boolean isRichText;

    @JsonProperty("italic_degree")
    private Integer italicDegree;

    @JsonProperty("ktv_color")
    private String ktvColor;

    private String language;

    @JsonProperty("layer_weight")
    private Integer layerWeight;

    /**
     * 字间距
     * 剪映上为：n，值 = n * 0.05
     */
    @JsonProperty("letter_spacing")
    private Double letterSpacing;

    @JsonProperty("line_feed")
    private Integer lineFeed;

    @JsonProperty("line_max_width")
    private Double lineMaxWidth;

    @JsonProperty("line_spacing")
    private Double lineSpacing;

    private String name;

    @JsonProperty("original_size")
    private List<Object> originalSize;

    @JsonProperty("preset_category")
    private String presetCategory;

    @JsonProperty("preset_category_id")
    private String presetCategoryId;

    @JsonProperty("preset_has_set_alignment")
    private Boolean presetHasSetAlignment;

    @JsonProperty("preset_id")
    private String presetId;

    @JsonProperty("preset_index")
    private Integer presetIndex;

    @JsonProperty("preset_name")
    private String presetName;

    @JsonProperty("recognize_task_id")
    private String recognizeTaskId;

    @JsonProperty("recognize_type")
    private Integer recognizeType;

    @JsonProperty("relevance_segment")
    private List<Object> relevanceSegment;

    @JsonProperty("shadow_alpha")
    private Double shadowAlpha;

    @JsonProperty("shadow_angle")
    private Double shadowAngle;

    @JsonProperty("shadow_color")
    private String shadowColor;

    @JsonProperty("shadow_distance")
    private Double shadowDistance;

    @JsonProperty("shadow_point")
    private Point shadowPoint;

    @JsonProperty("shadow_smoothing")
    private Double shadowSmoothing;

    @JsonProperty("shape_clip_x")
    private Boolean shapeClipX;

    @JsonProperty("shape_clip_y")
    private Boolean shapeClipY;

    @JsonProperty("style_name")
    private String styleName;

    @JsonProperty("sub_type")
    private Integer subType;

    @JsonProperty("subtitle_keywords")
    private SubtitleKeyword subtitleKeywords;

    @JsonProperty("text_alpha")
    private Double textAlpha;

    @JsonProperty("text_color")
    private String textColor;

    @JsonProperty("text_curve")
    private Object textCurve;

    @JsonProperty("text_preset_resource_id")
    private String textPresetResourceId;

    @JsonProperty("text_size")
    private Integer textSize;

    @JsonProperty("text_to_audio_ids")
    private List<Object> textToAudioIds;

    @JsonProperty("tts_auto_update")
    private Boolean ttsAutoUpdate;

    private Integer typesetting;

    private Boolean underline;

    @JsonProperty("underline_offset")
    private Double underlineOffset;

    @JsonProperty("underline_width")
    private Double underlineWidth;

    @JsonProperty("use_effect_default_color")
    private Boolean useEffectDefaultColor;

    private Words words;

    @JsonProperty("source_from")
    private String sourceFrom;

    @JsonProperty("operation_type")
    private Integer operationType;

    @JsonProperty("text_typesetting_path_index")
    private Integer textTypesettingPathIndex;

    @JsonProperty("is_batch_replace")
    private Boolean isBatchReplace;

    @JsonProperty("multi_language_current")
    private String multiLanguageCurrent;

    @JsonProperty("lyrics_template")
    private LyricsTemplate lyricsTemplate;

    @JsonProperty("punc_model")
    private String puncModel;

    @JsonProperty("ssml_content")
    private String ssmlContent;

    @JsonProperty("font_third_resource_id")
    private String fontThirdResourceId;

    @JsonProperty("is_lyric_effect")
    private Boolean isLyricEffect;

    @JsonProperty("oneline_cutoff")
    private Boolean onelineCutoff;

    @JsonProperty("current_words")
    private Words currentWords;

    @JsonProperty("sub_template_id")
    private Integer subTemplateId;

    @JsonProperty("is_words_linear")
    private Boolean isWordsLinear;

    @JsonProperty("inner_padding")
    private Double innerPadding;

    @JsonProperty("text_typesetting_paths_file")
    private String textTypesettingPathsFile;

    @JsonProperty("background_fill")
    private String backgroundFill;

    @JsonProperty("enable_path_typesetting")
    private Boolean enablePathTypesetting;

    @JsonProperty("offset_on_path")
    private Double offsetOnPath;

    @JsonProperty("translate_original_text")
    private String translateOriginalText;

    @JsonProperty("recognize_text")
    private String recognizeText;

    @JsonProperty("subtitle_template_original_fontsize")
    private Double subtitleTemplateOriginalFontsize;

    @JsonProperty("text_exceeds_path_process_type")
    private Integer textExceedsPathProcessType;

    @JsonProperty("lyric_group_id")
    private String lyricGroupId;

    @JsonProperty("recognize_model")
    private String recognizeModel;

    @JsonProperty("cutoff_postfix")
    private String cutoffPostfix;

    @JsonProperty("text_loop_on_path")
    private Boolean textLoopOnPath;

    public Integer getAddType() {
        return addType;
    }

    public Text setAddType(Integer addType) {
        this.addType = addType;
        return this;
    }

    public Integer getAlignment() {
        return alignment;
    }

    public Text setAlignment(Integer alignment) {
        this.alignment = alignment;
        return this;
    }

    public Double getBackgroundAlpha() {
        return backgroundAlpha;
    }

    public Text setBackgroundAlpha(Double backgroundAlpha) {
        this.backgroundAlpha = backgroundAlpha;
        return this;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public Text setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
        return this;
    }

    public Double getBackgroundHeight() {
        return backgroundHeight;
    }

    public Text setBackgroundHeight(Double backgroundHeight) {
        this.backgroundHeight = backgroundHeight;
        return this;
    }

    public Double getBackgroundHorizontalOffset() {
        return backgroundHorizontalOffset;
    }

    public Text setBackgroundHorizontalOffset(Double backgroundHorizontalOffset) {
        this.backgroundHorizontalOffset = backgroundHorizontalOffset;
        return this;
    }

    public Double getBackgroundRoundRadius() {
        return backgroundRoundRadius;
    }

    public Text setBackgroundRoundRadius(Double backgroundRoundRadius) {
        this.backgroundRoundRadius = backgroundRoundRadius;
        return this;
    }

    public Integer getBackgroundStyle() {
        return backgroundStyle;
    }

    public Text setBackgroundStyle(Integer backgroundStyle) {
        this.backgroundStyle = backgroundStyle;
        return this;
    }

    public Double getBackgroundVerticalOffset() {
        return backgroundVerticalOffset;
    }

    public Text setBackgroundVerticalOffset(Double backgroundVerticalOffset) {
        this.backgroundVerticalOffset = backgroundVerticalOffset;
        return this;
    }

    public Double getBackgroundWidth() {
        return backgroundWidth;
    }

    public Text setBackgroundWidth(Double backgroundWidth) {
        this.backgroundWidth = backgroundWidth;
        return this;
    }

    public Double getBoldWidth() {
        return boldWidth;
    }

    public Text setBoldWidth(Double boldWidth) {
        this.boldWidth = boldWidth;
        return this;
    }

    public Double getBorderAlpha() {
        return borderAlpha;
    }

    public Text setBorderAlpha(Double borderAlpha) {
        this.borderAlpha = borderAlpha;
        return this;
    }

    public String getBorderColor() {
        return borderColor;
    }

    public Text setBorderColor(String borderColor) {
        this.borderColor = borderColor;
        return this;
    }

    public Double getBorderWidth() {
        return borderWidth;
    }

    public Text setBorderWidth(Double borderWidth) {
        this.borderWidth = borderWidth;
        return this;
    }

    public CaptionTemplateInfo getCaptionTemplateInfo() {
        return captionTemplateInfo;
    }

    public Text setCaptionTemplateInfo(CaptionTemplateInfo captionTemplateInfo) {
        this.captionTemplateInfo = captionTemplateInfo;
        return this;
    }

    public Integer getCheckFlag() {
        return checkFlag;
    }

    public Text setCheckFlag(Integer checkFlag) {
        this.checkFlag = checkFlag;
        return this;
    }

    public ComboInfo getComboInfo() {
        return comboInfo;
    }

    public Text setComboInfo(ComboInfo comboInfo) {
        this.comboInfo = comboInfo;
        return this;
    }

    public String getContent() {
        return content;
    }

    public Text setContent(String content) {
        this.content = content;
        return this;
    }

    public Double getFixedHeight() {
        return fixedHeight;
    }

    public Text setFixedHeight(Double fixedHeight) {
        this.fixedHeight = fixedHeight;
        return this;
    }

    public Double getFixedWidth() {
        return fixedWidth;
    }

    public Text setFixedWidth(Double fixedWidth) {
        this.fixedWidth = fixedWidth;
        return this;
    }

    public String getFontCategoryId() {
        return fontCategoryId;
    }

    public Text setFontCategoryId(String fontCategoryId) {
        this.fontCategoryId = fontCategoryId;
        return this;
    }

    public String getFontCategoryName() {
        return fontCategoryName;
    }

    public Text setFontCategoryName(String fontCategoryName) {
        this.fontCategoryName = fontCategoryName;
        return this;
    }

    public String getFontId() {
        return fontId;
    }

    public Text setFontId(String fontId) {
        this.fontId = fontId;
        return this;
    }

    public String getFontName() {
        return fontName;
    }

    public Text setFontName(String fontName) {
        this.fontName = fontName;
        return this;
    }

    public String getFontPath() {
        return fontPath;
    }

    public Text setFontPath(String fontPath) {
        this.fontPath = fontPath;
        return this;
    }

    public String getFontResourceId() {
        return fontResourceId;
    }

    public Text setFontResourceId(String fontResourceId) {
        this.fontResourceId = fontResourceId;
        return this;
    }

    public Double getFontSize() {
        return fontSize;
    }

    public Text setFontSize(Double fontSize) {
        this.fontSize = fontSize;
        return this;
    }

    public Integer getFontSourcePlatform() {
        return fontSourcePlatform;
    }

    public Text setFontSourcePlatform(Integer fontSourcePlatform) {
        this.fontSourcePlatform = fontSourcePlatform;
        return this;
    }

    public String getFontTeamId() {
        return fontTeamId;
    }

    public Text setFontTeamId(String fontTeamId) {
        this.fontTeamId = fontTeamId;
        return this;
    }

    public String getFontTitle() {
        return fontTitle;
    }

    public Text setFontTitle(String fontTitle) {
        this.fontTitle = fontTitle;
        return this;
    }

    public String getFontUrl() {
        return fontUrl;
    }

    public Text setFontUrl(String fontUrl) {
        this.fontUrl = fontUrl;
        return this;
    }

    public List<Font> getFonts() {
        return fonts;
    }

    public Text setFonts(List<Font> fonts) {
        this.fonts = fonts;
        return this;
    }

    public Boolean getForceApplyLineMaxWidth() {
        return forceApplyLineMaxWidth;
    }

    public Text setForceApplyLineMaxWidth(Boolean forceApplyLineMaxWidth) {
        this.forceApplyLineMaxWidth = forceApplyLineMaxWidth;
        return this;
    }

    public Double getGlobalAlpha() {
        return globalAlpha;
    }

    public Text setGlobalAlpha(Double globalAlpha) {
        this.globalAlpha = globalAlpha;
        return this;
    }

    public String getGroupId() {
        return groupId;
    }

    public Text setGroupId(String groupId) {
        this.groupId = groupId;
        return this;
    }

    public Boolean getHasShadow() {
        return hasShadow;
    }

    public Text setHasShadow(Boolean hasShadow) {
        this.hasShadow = hasShadow;
        return this;
    }

    public Text setId(String id) {
        this.id = id;
        return this;
    }

    public Double getInitialScale() {
        return initialScale;
    }

    public Text setInitialScale(Double initialScale) {
        this.initialScale = initialScale;
        return this;
    }

    public Boolean getRichText() {
        return isRichText;
    }

    public Text setRichText(Boolean richText) {
        isRichText = richText;
        return this;
    }

    public Integer getItalicDegree() {
        return italicDegree;
    }

    public Text setItalicDegree(Integer italicDegree) {
        this.italicDegree = italicDegree;
        return this;
    }

    public String getKtvColor() {
        return ktvColor;
    }

    public Text setKtvColor(String ktvColor) {
        this.ktvColor = ktvColor;
        return this;
    }

    public String getLanguage() {
        return language;
    }

    public Text setLanguage(String language) {
        this.language = language;
        return this;
    }

    public Integer getLayerWeight() {
        return layerWeight;
    }

    public Text setLayerWeight(Integer layerWeight) {
        this.layerWeight = layerWeight;
        return this;
    }

    public Double getLetterSpacing() {
        return letterSpacing;
    }

    public Text setLetterSpacing(Double letterSpacing) {
        this.letterSpacing = letterSpacing;
        return this;
    }

    public Integer getLineFeed() {
        return lineFeed;
    }

    public Text setLineFeed(Integer lineFeed) {
        this.lineFeed = lineFeed;
        return this;
    }

    public Double getLineMaxWidth() {
        return lineMaxWidth;
    }

    public Text setLineMaxWidth(Double lineMaxWidth) {
        this.lineMaxWidth = lineMaxWidth;
        return this;
    }

    public Double getLineSpacing() {
        return lineSpacing;
    }

    public Text setLineSpacing(Double lineSpacing) {
        this.lineSpacing = lineSpacing;
        return this;
    }

    public String getName() {
        return name;
    }

    public Text setName(String name) {
        this.name = name;
        return this;
    }

    public List<Object> getOriginalSize() {
        return originalSize;
    }

    public Text setOriginalSize(List<Object> originalSize) {
        this.originalSize = originalSize;
        return this;
    }

    public String getPresetCategory() {
        return presetCategory;
    }

    public Text setPresetCategory(String presetCategory) {
        this.presetCategory = presetCategory;
        return this;
    }

    public String getPresetCategoryId() {
        return presetCategoryId;
    }

    public Text setPresetCategoryId(String presetCategoryId) {
        this.presetCategoryId = presetCategoryId;
        return this;
    }

    public Boolean getPresetHasSetAlignment() {
        return presetHasSetAlignment;
    }

    public Text setPresetHasSetAlignment(Boolean presetHasSetAlignment) {
        this.presetHasSetAlignment = presetHasSetAlignment;
        return this;
    }

    public String getPresetId() {
        return presetId;
    }

    public Text setPresetId(String presetId) {
        this.presetId = presetId;
        return this;
    }

    public Integer getPresetIndex() {
        return presetIndex;
    }

    public Text setPresetIndex(Integer presetIndex) {
        this.presetIndex = presetIndex;
        return this;
    }

    public String getPresetName() {
        return presetName;
    }

    public Text setPresetName(String presetName) {
        this.presetName = presetName;
        return this;
    }

    public String getRecognizeTaskId() {
        return recognizeTaskId;
    }

    public Text setRecognizeTaskId(String recognizeTaskId) {
        this.recognizeTaskId = recognizeTaskId;
        return this;
    }

    public Integer getRecognizeType() {
        return recognizeType;
    }

    public Text setRecognizeType(Integer recognizeType) {
        this.recognizeType = recognizeType;
        return this;
    }

    public List<Object> getRelevanceSegment() {
        return relevanceSegment;
    }

    public Text setRelevanceSegment(List<Object> relevanceSegment) {
        this.relevanceSegment = relevanceSegment;
        return this;
    }

    public Double getShadowAlpha() {
        return shadowAlpha;
    }

    public Text setShadowAlpha(Double shadowAlpha) {
        this.shadowAlpha = shadowAlpha;
        return this;
    }

    public Double getShadowAngle() {
        return shadowAngle;
    }

    public Text setShadowAngle(Double shadowAngle) {
        this.shadowAngle = shadowAngle;
        return this;
    }

    public String getShadowColor() {
        return shadowColor;
    }

    public Text setShadowColor(String shadowColor) {
        this.shadowColor = shadowColor;
        return this;
    }

    public Double getShadowDistance() {
        return shadowDistance;
    }

    public Text setShadowDistance(Double shadowDistance) {
        this.shadowDistance = shadowDistance;
        return this;
    }

    public Point getShadowPoint() {
        return shadowPoint;
    }

    public Text setShadowPoint(Point shadowPoint) {
        this.shadowPoint = shadowPoint;
        return this;
    }

    public Double getShadowSmoothing() {
        return shadowSmoothing;
    }

    public Text setShadowSmoothing(Double shadowSmoothing) {
        this.shadowSmoothing = shadowSmoothing;
        return this;
    }

    public Boolean getShapeClipX() {
        return shapeClipX;
    }

    public Text setShapeClipX(Boolean shapeClipX) {
        this.shapeClipX = shapeClipX;
        return this;
    }

    public Boolean getShapeClipY() {
        return shapeClipY;
    }

    public Text setShapeClipY(Boolean shapeClipY) {
        this.shapeClipY = shapeClipY;
        return this;
    }

    public String getStyleName() {
        return styleName;
    }

    public Text setStyleName(String styleName) {
        this.styleName = styleName;
        return this;
    }

    public Integer getSubType() {
        return subType;
    }

    public Text setSubType(Integer subType) {
        this.subType = subType;
        return this;
    }

    public SubtitleKeyword getSubtitleKeywords() {
        return subtitleKeywords;
    }

    public Text setSubtitleKeywords(SubtitleKeyword subtitleKeywords) {
        this.subtitleKeywords = subtitleKeywords;
        return this;
    }

    public Double getTextAlpha() {
        return textAlpha;
    }

    public Text setTextAlpha(Double textAlpha) {
        this.textAlpha = textAlpha;
        return this;
    }

    public String getTextColor() {
        return textColor;
    }

    public Text setTextColor(String textColor) {
        this.textColor = textColor;
        return this;
    }

    public Object getTextCurve() {
        return textCurve;
    }

    public Text setTextCurve(Object textCurve) {
        this.textCurve = textCurve;
        return this;
    }

    public String getTextPresetResourceId() {
        return textPresetResourceId;
    }

    public Text setTextPresetResourceId(String textPresetResourceId) {
        this.textPresetResourceId = textPresetResourceId;
        return this;
    }

    public Integer getTextSize() {
        return textSize;
    }

    public Text setTextSize(Integer textSize) {
        this.textSize = textSize;
        return this;
    }

    public List<Object> getTextToAudioIds() {
        return textToAudioIds;
    }

    public Text setTextToAudioIds(List<Object> textToAudioIds) {
        this.textToAudioIds = textToAudioIds;
        return this;
    }

    public Boolean getTtsAutoUpdate() {
        return ttsAutoUpdate;
    }

    public Text setTtsAutoUpdate(Boolean ttsAutoUpdate) {
        this.ttsAutoUpdate = ttsAutoUpdate;
        return this;
    }

    public Text setType(String type) {
        this.type = type;
        return this;
    }

    public Integer getTypesetting() {
        return typesetting;
    }

    public Text setTypesetting(Integer typesetting) {
        this.typesetting = typesetting;
        return this;
    }

    public Boolean getUnderline() {
        return underline;
    }

    public Text setUnderline(Boolean underline) {
        this.underline = underline;
        return this;
    }

    public Double getUnderlineOffset() {
        return underlineOffset;
    }

    public Text setUnderlineOffset(Double underlineOffset) {
        this.underlineOffset = underlineOffset;
        return this;
    }

    public Double getUnderlineWidth() {
        return underlineWidth;
    }

    public Text setUnderlineWidth(Double underlineWidth) {
        this.underlineWidth = underlineWidth;
        return this;
    }

    public Boolean getUseEffectDefaultColor() {
        return useEffectDefaultColor;
    }

    public Text setUseEffectDefaultColor(Boolean useEffectDefaultColor) {
        this.useEffectDefaultColor = useEffectDefaultColor;
        return this;
    }

    public Words getWords() {
        return words;
    }

    public Text setWords(Words words) {
        this.words = words;
        return this;
    }

    public String getBaseContent() {
        return baseContent;
    }

    public Text setBaseContent(String baseContent) {
        this.baseContent = baseContent;
        return this;
    }

    public String getSourceFrom() {
        return sourceFrom;
    }

    public Text setSourceFrom(String sourceFrom) {
        this.sourceFrom = sourceFrom;
        return this;
    }

    public Integer getOperationType() {
        return operationType;
    }

    public Text setOperationType(Integer operationType) {
        this.operationType = operationType;
        return this;
    }

    public Integer getTextTypesettingPathIndex() {
        return textTypesettingPathIndex;
    }

    public Text setTextTypesettingPathIndex(Integer textTypesettingPathIndex) {
        this.textTypesettingPathIndex = textTypesettingPathIndex;
        return this;
    }

    public Boolean getBatchReplace() {
        return isBatchReplace;
    }

    public Text setBatchReplace(Boolean batchReplace) {
        isBatchReplace = batchReplace;
        return this;
    }

    public String getMultiLanguageCurrent() {
        return multiLanguageCurrent;
    }

    public Text setMultiLanguageCurrent(String multiLanguageCurrent) {
        this.multiLanguageCurrent = multiLanguageCurrent;
        return this;
    }

    public LyricsTemplate getLyricsTemplate() {
        return lyricsTemplate;
    }

    public Text setLyricsTemplate(LyricsTemplate lyricsTemplate) {
        this.lyricsTemplate = lyricsTemplate;
        return this;
    }

    public String getPuncModel() {
        return puncModel;
    }

    public Text setPuncModel(String puncModel) {
        this.puncModel = puncModel;
        return this;
    }

    public String getSsmlContent() {
        return ssmlContent;
    }

    public Text setSsmlContent(String ssmlContent) {
        this.ssmlContent = ssmlContent;
        return this;
    }

    public String getFontThirdResourceId() {
        return fontThirdResourceId;
    }

    public Text setFontThirdResourceId(String fontThirdResourceId) {
        this.fontThirdResourceId = fontThirdResourceId;
        return this;
    }

    public Boolean getLyricEffect() {
        return isLyricEffect;
    }

    public Text setLyricEffect(Boolean lyricEffect) {
        isLyricEffect = lyricEffect;
        return this;
    }

    public Boolean getOnelineCutoff() {
        return onelineCutoff;
    }

    public Text setOnelineCutoff(Boolean onelineCutoff) {
        this.onelineCutoff = onelineCutoff;
        return this;
    }

    public Words getCurrentWords() {
        return currentWords;
    }

    public Text setCurrentWords(Words currentWords) {
        this.currentWords = currentWords;
        return this;
    }

    public Integer getSubTemplateId() {
        return subTemplateId;
    }

    public Text setSubTemplateId(Integer subTemplateId) {
        this.subTemplateId = subTemplateId;
        return this;
    }

    public Boolean getWordsLinear() {
        return isWordsLinear;
    }

    public Text setWordsLinear(Boolean wordsLinear) {
        isWordsLinear = wordsLinear;
        return this;
    }

    public Double getInnerPadding() {
        return innerPadding;
    }

    public Text setInnerPadding(Double innerPadding) {
        this.innerPadding = innerPadding;
        return this;
    }

    public String getTextTypesettingPathsFile() {
        return textTypesettingPathsFile;
    }

    public Text setTextTypesettingPathsFile(String textTypesettingPathsFile) {
        this.textTypesettingPathsFile = textTypesettingPathsFile;
        return this;
    }

    public String getBackgroundFill() {
        return backgroundFill;
    }

    public Text setBackgroundFill(String backgroundFill) {
        this.backgroundFill = backgroundFill;
        return this;
    }

    public Boolean getEnablePathTypesetting() {
        return enablePathTypesetting;
    }

    public Text setEnablePathTypesetting(Boolean enablePathTypesetting) {
        this.enablePathTypesetting = enablePathTypesetting;
        return this;
    }

    public Double getOffsetOnPath() {
        return offsetOnPath;
    }

    public Text setOffsetOnPath(Double offsetOnPath) {
        this.offsetOnPath = offsetOnPath;
        return this;
    }

    public String getTranslateOriginalText() {
        return translateOriginalText;
    }

    public Text setTranslateOriginalText(String translateOriginalText) {
        this.translateOriginalText = translateOriginalText;
        return this;
    }

    public String getRecognizeText() {
        return recognizeText;
    }

    public Text setRecognizeText(String recognizeText) {
        this.recognizeText = recognizeText;
        return this;
    }

    public Double getSubtitleTemplateOriginalFontsize() {
        return subtitleTemplateOriginalFontsize;
    }

    public Text setSubtitleTemplateOriginalFontsize(Double subtitleTemplateOriginalFontsize) {
        this.subtitleTemplateOriginalFontsize = subtitleTemplateOriginalFontsize;
        return this;
    }

    public Integer getTextExceedsPathProcessType() {
        return textExceedsPathProcessType;
    }

    public Text setTextExceedsPathProcessType(Integer textExceedsPathProcessType) {
        this.textExceedsPathProcessType = textExceedsPathProcessType;
        return this;
    }

    public String getLyricGroupId() {
        return lyricGroupId;
    }

    public Text setLyricGroupId(String lyricGroupId) {
        this.lyricGroupId = lyricGroupId;
        return this;
    }

    public String getRecognizeModel() {
        return recognizeModel;
    }

    public Text setRecognizeModel(String recognizeModel) {
        this.recognizeModel = recognizeModel;
        return this;
    }

    public String getCutoffPostfix() {
        return cutoffPostfix;
    }

    public Text setCutoffPostfix(String cutoffPostfix) {
        this.cutoffPostfix = cutoffPostfix;
        return this;
    }

    public Boolean getTextLoopOnPath() {
        return textLoopOnPath;
    }

    public Text setTextLoopOnPath(Boolean textLoopOnPath) {
        this.textLoopOnPath = textLoopOnPath;
        return this;
    }
}
