
package com.duoec.video.jy.dto.info;

import com.duoec.video.jy.dto.TimeRange;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

public class Segment implements Serializable {

    public Boolean cartoon;

    public Clip clip;

    @JsonProperty("common_keyframes")
    public List<Keyframe> commonKeyframes;

    @JsonProperty("enable_adjust")
    public Boolean enableAdjust;

    @JsonProperty("enable_color_correct_adjust")
    public Boolean enableColorCorrectAdjust;

    @JsonProperty("enable_color_curves")
    public Boolean enableColorCurves;

    @JsonProperty("enable_color_match_adjust")
    public Boolean enableColorMatchAdjust;

    @JsonProperty("enable_color_wheels")
    public Boolean enableColorWheels;

    @JsonProperty("enable_lut")
    public Boolean enableLut;

    @JsonProperty("enable_smart_color_adjust")
    public Boolean enableSmartColorAdjust;

    @JsonProperty("extra_material_refs")
    public List<String> extraMaterialRefs;

    @JsonProperty("group_id")
    public String groupId;

    /**
     * HDR转换工具。当 check_flag=62975 开启时有效
     */
    @JsonProperty("hdr_settings")
    public HdrSettings hdrSettings;

    public String id;

    @JsonProperty("intensifies_audio")
    public Boolean intensifiesAudio;

    @JsonProperty("keyframe_refs")
    public List<Object> keyframeRefs;

    @JsonProperty("last_nonzero_volume")
    public Double lastNonzeroVolume;

    @JsonProperty("material_id")
    public String materialId;

    @JsonProperty("render_index")
    public Integer renderIndex;

    @JsonProperty("responsive_layout")
    public ResponsiveLayout responsiveLayout;

    public Boolean reverse;

    @JsonProperty("source_timerange")
    public TimeRange sourceTimeRange;

    public Double speed;

    @JsonProperty("target_timerange")
    public TimeRange targetTimeRange;

    @JsonProperty("template_id")
    public String templateId;

    @JsonProperty("template_scene")
    public String templateScene;

    @JsonProperty("track_attribute")
    public Integer trackAttribute;

    @JsonProperty("track_render_index")
    public Integer trackRenderIndex;

    @JsonProperty("uniform_scale")
    public UniformScale uniformScale;

    public Boolean visible;

    /**
     * 音量的线性倍数（Amplitude Gain）
     * 分贝（dB）与线性倍数的关系：
     * G = POWER(10, dB/20)
     * 0=静音 1=原音，无增益
     */
    public Double volume;

    @JsonProperty("is_tone_modify")
    private Boolean isToneModify;

    @JsonProperty("enable_adjust_mask")
    private Boolean enableAdjustMask;

    @JsonProperty("enable_video_mask")
    private Boolean enableVideoMask;

    @JsonProperty("enable_hsl")
    private Boolean enableHsl;

    @JsonProperty("enable_hsl_curves")
    private Boolean enableHslCurves;

    private String desc;

    private Integer state;

    @JsonProperty("is_loop")
    private Boolean isLoop;

    private String source;

    @JsonProperty("render_timerange")
    private TimeRange renderTimeRange;

    @JsonProperty("raw_segment_id")
    private String rawSegmentId;

    @JsonProperty("color_correct_alg_result")
    private String colorCorrectAlgResult;

    @JsonProperty("digital_human_template_group_id")
    private String digitalHumanTemplateGroupId;

    @JsonProperty("is_placeholder")
    private Boolean isPlaceholder;

    public Boolean getCartoon() {
        return cartoon;
    }

    public Segment setCartoon(Boolean cartoon) {
        this.cartoon = cartoon;
        return this;
    }

    public Clip getClip() {
        return clip;
    }

    public Segment setClip(Clip clip) {
        this.clip = clip;
        return this;
    }

    public List<Keyframe> getCommonKeyframes() {
        return commonKeyframes;
    }

    public Segment setCommonKeyframes(List<Keyframe> commonKeyframes) {
        this.commonKeyframes = commonKeyframes;
        return this;
    }

    public Boolean getEnableAdjust() {
        return enableAdjust;
    }

    public Segment setEnableAdjust(Boolean enableAdjust) {
        this.enableAdjust = enableAdjust;
        return this;
    }

    public Boolean getEnableColorCorrectAdjust() {
        return enableColorCorrectAdjust;
    }

    public Segment setEnableColorCorrectAdjust(Boolean enableColorCorrectAdjust) {
        this.enableColorCorrectAdjust = enableColorCorrectAdjust;
        return this;
    }

    public Boolean getEnableColorCurves() {
        return enableColorCurves;
    }

    public Segment setEnableColorCurves(Boolean enableColorCurves) {
        this.enableColorCurves = enableColorCurves;
        return this;
    }

    public Boolean getEnableColorMatchAdjust() {
        return enableColorMatchAdjust;
    }

    public Segment setEnableColorMatchAdjust(Boolean enableColorMatchAdjust) {
        this.enableColorMatchAdjust = enableColorMatchAdjust;
        return this;
    }

    public Boolean getEnableColorWheels() {
        return enableColorWheels;
    }

    public Segment setEnableColorWheels(Boolean enableColorWheels) {
        this.enableColorWheels = enableColorWheels;
        return this;
    }

    public Boolean getEnableLut() {
        return enableLut;
    }

    public Segment setEnableLut(Boolean enableLut) {
        this.enableLut = enableLut;
        return this;
    }

    public Boolean getEnableSmartColorAdjust() {
        return enableSmartColorAdjust;
    }

    public Segment setEnableSmartColorAdjust(Boolean enableSmartColorAdjust) {
        this.enableSmartColorAdjust = enableSmartColorAdjust;
        return this;
    }

    public List<String> getExtraMaterialRefs() {
        return extraMaterialRefs;
    }

    public Segment setExtraMaterialRefs(List<String> extraMaterialRefs) {
        this.extraMaterialRefs = extraMaterialRefs;
        return this;
    }

    public String getGroupId() {
        return groupId;
    }

    public Segment setGroupId(String groupId) {
        this.groupId = groupId;
        return this;
    }

    public HdrSettings getHdrSettings() {
        return hdrSettings;
    }

    public Segment setHdrSettings(HdrSettings hdrSettings) {
        this.hdrSettings = hdrSettings;
        return this;
    }

    public String getId() {
        return id;
    }

    public Segment setId(String id) {
        this.id = id;
        return this;
    }

    public Boolean getIntensifiesAudio() {
        return intensifiesAudio;
    }

    public Segment setIntensifiesAudio(Boolean intensifiesAudio) {
        this.intensifiesAudio = intensifiesAudio;
        return this;
    }

    public List<Object> getKeyframeRefs() {
        return keyframeRefs;
    }

    public Segment setKeyframeRefs(List<Object> keyframeRefs) {
        this.keyframeRefs = keyframeRefs;
        return this;
    }

    public Double getLastNonzeroVolume() {
        return lastNonzeroVolume;
    }

    public Segment setLastNonzeroVolume(Double lastNonzeroVolume) {
        this.lastNonzeroVolume = lastNonzeroVolume;
        return this;
    }

    public String getMaterialId() {
        return materialId;
    }

    public Segment setMaterialId(String materialId) {
        this.materialId = materialId;
        return this;
    }

    public Integer getRenderIndex() {
        return renderIndex;
    }

    public Segment setRenderIndex(Integer renderIndex) {
        this.renderIndex = renderIndex;
        return this;
    }

    public ResponsiveLayout getResponsiveLayout() {
        return responsiveLayout;
    }

    public Segment setResponsiveLayout(ResponsiveLayout responsiveLayout) {
        this.responsiveLayout = responsiveLayout;
        return this;
    }

    public Boolean getReverse() {
        return reverse;
    }

    public Segment setReverse(Boolean reverse) {
        this.reverse = reverse;
        return this;
    }

    public TimeRange getSourceTimeRange() {
        return sourceTimeRange;
    }

    public Segment setSourceTimeRange(TimeRange sourceTimeRange) {
        this.sourceTimeRange = sourceTimeRange;
        return this;
    }

    public Double getSpeed() {
        return speed;
    }

    public Segment setSpeed(Double speed) {
        this.speed = speed;
        return this;
    }

    public TimeRange getTargetTimeRange() {
        return targetTimeRange;
    }

    public Segment setTargetTimeRange(TimeRange targetTimeRange) {
        this.targetTimeRange = targetTimeRange;
        return this;
    }

    public String getTemplateId() {
        return templateId;
    }

    public Segment setTemplateId(String templateId) {
        this.templateId = templateId;
        return this;
    }

    public String getTemplateScene() {
        return templateScene;
    }

    public Segment setTemplateScene(String templateScene) {
        this.templateScene = templateScene;
        return this;
    }

    public Integer getTrackAttribute() {
        return trackAttribute;
    }

    public Segment setTrackAttribute(Integer trackAttribute) {
        this.trackAttribute = trackAttribute;
        return this;
    }

    public Integer getTrackRenderIndex() {
        return trackRenderIndex;
    }

    public Segment setTrackRenderIndex(Integer trackRenderIndex) {
        this.trackRenderIndex = trackRenderIndex;
        return this;
    }

    public UniformScale getUniformScale() {
        return uniformScale;
    }

    public Segment setUniformScale(UniformScale uniformScale) {
        this.uniformScale = uniformScale;
        return this;
    }

    public Boolean getVisible() {
        return visible;
    }

    public Segment setVisible(Boolean visible) {
        this.visible = visible;
        return this;
    }

    public Double getVolume() {
        return volume;
    }

    public Segment setVolume(Double volume) {
        this.volume = volume;
        return this;
    }

    public Boolean getToneModify() {
        return isToneModify;
    }

    public Segment setToneModify(Boolean toneModify) {
        isToneModify = toneModify;
        return this;
    }

    public Boolean getEnableAdjustMask() {
        return enableAdjustMask;
    }

    public Segment setEnableAdjustMask(Boolean enableAdjustMask) {
        this.enableAdjustMask = enableAdjustMask;
        return this;
    }

    public Boolean getEnableVideoMask() {
        return enableVideoMask;
    }

    public Segment setEnableVideoMask(Boolean enableVideoMask) {
        this.enableVideoMask = enableVideoMask;
        return this;
    }

    public Boolean getEnableHsl() {
        return enableHsl;
    }

    public Segment setEnableHsl(Boolean enableHsl) {
        this.enableHsl = enableHsl;
        return this;
    }

    public Boolean getEnableHslCurves() {
        return enableHslCurves;
    }

    public Segment setEnableHslCurves(Boolean enableHslCurves) {
        this.enableHslCurves = enableHslCurves;
        return this;
    }

    public String getDesc() {
        return desc;
    }

    public Segment setDesc(String desc) {
        this.desc = desc;
        return this;
    }

    public Integer getState() {
        return state;
    }

    public Segment setState(Integer state) {
        this.state = state;
        return this;
    }

    public Boolean getLoop() {
        return isLoop;
    }

    public Segment setLoop(Boolean loop) {
        isLoop = loop;
        return this;
    }

    public String getSource() {
        return source;
    }

    public Segment setSource(String source) {
        this.source = source;
        return this;
    }

    public TimeRange getRenderTimeRange() {
        return renderTimeRange;
    }

    public Segment setRenderTimeRange(TimeRange renderTimeRange) {
        this.renderTimeRange = renderTimeRange;
        return this;
    }

    public String getRawSegmentId() {
        return rawSegmentId;
    }

    public Segment setRawSegmentId(String rawSegmentId) {
        this.rawSegmentId = rawSegmentId;
        return this;
    }

    public String getColorCorrectAlgResult() {
        return colorCorrectAlgResult;
    }

    public Segment setColorCorrectAlgResult(String colorCorrectAlgResult) {
        this.colorCorrectAlgResult = colorCorrectAlgResult;
        return this;
    }

    public String getDigitalHumanTemplateGroupId() {
        return digitalHumanTemplateGroupId;
    }

    public Segment setDigitalHumanTemplateGroupId(String digitalHumanTemplateGroupId) {
        this.digitalHumanTemplateGroupId = digitalHumanTemplateGroupId;
        return this;
    }

    public Boolean getPlaceholder() {
        return isPlaceholder;
    }

    public Segment setPlaceholder(Boolean placeholder) {
        isPlaceholder = placeholder;
        return this;
    }
}
