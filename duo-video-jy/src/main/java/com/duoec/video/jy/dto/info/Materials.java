
package com.duoec.video.jy.dto.info;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

public class Materials implements Serializable {
    @JsonProperty("ai_translates")
    private List<AiTranslate> aiTranslates;

    @JsonProperty("audio_balances")
    public List<Object> audioBalances;

    @JsonProperty("audio_effects")
    public List<AudioEffect> audioEffects;

    @JsonProperty("audio_fades")
    public List<AudioFade> audioFades;

    @JsonProperty("audio_track_indexes")
    public List<Object> audioTrackIndexes;

    public List<Audio> audios;

    public List<Beat> beats;

    public List<Canvase> canvases;

    public List<Chroma> chromas;

    @JsonProperty("color_curves")
    public List<Object> colorCurves;

    @JsonProperty("digital_humans")
    public List<Object> digitalHumans;

    public List<Draft> drafts;

    public List<Effect> effects;

    /**
     * 花字
     */
    public List<Object> flowers;

    @JsonProperty("green_screens")
    public List<Object> greenScreens;

    public List<Object> handwrites;

    public List<Object> hsl;

    public List<Object> images;

    @JsonProperty("log_color_wheels")
    public List<Object> logColorWheels;

    public List<Loudness> loudnesses;

    @JsonProperty("manual_deformations")
    public List<Object> manualDeformations;

    public List<Mask> masks;

    @JsonProperty("common_mask")
    public List<CommonMask> commonMask;

    @JsonProperty("material_animations")
    public List<MaterialAnimation> materialAnimations;

    @JsonProperty("material_colors")
    public List<Object> materialColors;

    public List<Placeholder> placeholders;

    @JsonProperty("plugin_effects")
    public List<Object> pluginEffects;

    @JsonProperty("primary_color_wheels")
    public List<Object> primaryColorWheels;

    @JsonProperty("realtime_denoises")
    public List<RealtimeDenoise> realtimeDenoises;

    public List<Object> shapes;

    @JsonProperty("smart_crops")
    public List<Object> smartCrops;

    @JsonProperty("smart_relights")
    public List<Object> smartRelights;

    @JsonProperty("sound_channel_mappings")
    public List<SoundChannelMapping> soundChannelMappings;

    public List<Speed> speeds;

    public List<Sticker> stickers;

    @JsonProperty("tail_leaders")
    public List<Object> tailLeaders;

    @JsonProperty("text_templates")
    public List<TextTemplate> textTemplates;

    public List<Text> texts;

    public List<Transition> transitions;

    @JsonProperty("video_effects")
    public List<VideoEffect> videoEffects;

    @JsonProperty("video_trackings")
    public List<VideoTracking> videoTrackings;

    public List<Video> videos;

    @JsonProperty("vocal_beautifys")
    public List<VocalBeautify> vocalBeautifys;

    @JsonProperty("vocal_separations")
    public List<VocalSeparation> vocalSeparations;

    @JsonProperty("time_marks")
    public List<TimeMark> timeMarks;

    public List<Object> getAudioBalances() {
        return audioBalances;
    }

    public Materials setAudioBalances(List<Object> audioBalances) {
        this.audioBalances = audioBalances;
        return this;
    }

    public List<AudioEffect> getAudioEffects() {
        return audioEffects;
    }

    public Materials setAudioEffects(List<AudioEffect> audioEffects) {
        this.audioEffects = audioEffects;
        return this;
    }

    public List<AudioFade> getAudioFades() {
        return audioFades;
    }

    public Materials setAudioFades(List<AudioFade> audioFades) {
        this.audioFades = audioFades;
        return this;
    }

    public List<Audio> getAudios() {
        return audios;
    }

    public Materials setAudios(List<Audio> audios) {
        this.audios = audios;
        return this;
    }

    public List<Beat> getBeats() {
        return beats;
    }

    public Materials setBeats(List<Beat> beats) {
        this.beats = beats;
        return this;
    }

    public List<Canvase> getCanvases() {
        return canvases;
    }

    public Materials setCanvases(List<Canvase> canvases) {
        this.canvases = canvases;
        return this;
    }

    public List<Chroma> getChromas() {
        return chromas;
    }

    public Materials setChromas(List<Chroma> chromas) {
        this.chromas = chromas;
        return this;
    }

    public List<Object> getColorCurves() {
        return colorCurves;
    }

    public Materials setColorCurves(List<Object> colorCurves) {
        this.colorCurves = colorCurves;
        return this;
    }

    public List<Object> getDigitalHumans() {
        return digitalHumans;
    }

    public Materials setDigitalHumans(List<Object> digitalHumans) {
        this.digitalHumans = digitalHumans;
        return this;
    }

    public List<Draft> getDrafts() {
        return drafts;
    }

    public Materials setDrafts(List<Draft> drafts) {
        this.drafts = drafts;
        return this;
    }

    public List<Effect> getEffects() {
        return effects;
    }

    public Materials setEffects(List<Effect> effects) {
        this.effects = effects;
        return this;
    }

    public List<Object> getFlowers() {
        return flowers;
    }

    public Materials setFlowers(List<Object> flowers) {
        this.flowers = flowers;
        return this;
    }

    public List<Object> getGreenScreens() {
        return greenScreens;
    }

    public Materials setGreenScreens(List<Object> greenScreens) {
        this.greenScreens = greenScreens;
        return this;
    }

    public List<Object> getHandwrites() {
        return handwrites;
    }

    public Materials setHandwrites(List<Object> handwrites) {
        this.handwrites = handwrites;
        return this;
    }

    public List<Object> getHsl() {
        return hsl;
    }

    public Materials setHsl(List<Object> hsl) {
        this.hsl = hsl;
        return this;
    }

    public List<Object> getImages() {
        return images;
    }

    public Materials setImages(List<Object> images) {
        this.images = images;
        return this;
    }

    public List<Object> getLogColorWheels() {
        return logColorWheels;
    }

    public Materials setLogColorWheels(List<Object> logColorWheels) {
        this.logColorWheels = logColorWheels;
        return this;
    }

    public List<Loudness> getLoudnesses() {
        return loudnesses;
    }

    public Materials setLoudnesses(List<Loudness> loudnesses) {
        this.loudnesses = loudnesses;
        return this;
    }

    public List<Object> getManualDeformations() {
        return manualDeformations;
    }

    public Materials setManualDeformations(List<Object> manualDeformations) {
        this.manualDeformations = manualDeformations;
        return this;
    }

    public List<Mask> getMasks() {
        return masks;
    }

    public Materials setMasks(List<Mask> masks) {
        this.masks = masks;
        return this;
    }

    public List<MaterialAnimation> getMaterialAnimations() {
        return materialAnimations;
    }

    public Materials setMaterialAnimations(List<MaterialAnimation> materialAnimations) {
        this.materialAnimations = materialAnimations;
        return this;
    }

    public List<Object> getMaterialColors() {
        return materialColors;
    }

    public Materials setMaterialColors(List<Object> materialColors) {
        this.materialColors = materialColors;
        return this;
    }

    public List<Placeholder> getPlaceholders() {
        return placeholders;
    }

    public Materials setPlaceholders(List<Placeholder> placeholders) {
        this.placeholders = placeholders;
        return this;
    }

    public List<Object> getPluginEffects() {
        return pluginEffects;
    }

    public Materials setPluginEffects(List<Object> pluginEffects) {
        this.pluginEffects = pluginEffects;
        return this;
    }

    public List<Object> getPrimaryColorWheels() {
        return primaryColorWheels;
    }

    public Materials setPrimaryColorWheels(List<Object> primaryColorWheels) {
        this.primaryColorWheels = primaryColorWheels;
        return this;
    }

    public List<RealtimeDenoise> getRealtimeDenoises() {
        return realtimeDenoises;
    }

    public Materials setRealtimeDenoises(List<RealtimeDenoise> realtimeDenoises) {
        this.realtimeDenoises = realtimeDenoises;
        return this;
    }

    public List<Object> getShapes() {
        return shapes;
    }

    public Materials setShapes(List<Object> shapes) {
        this.shapes = shapes;
        return this;
    }

    public List<Object> getSmartCrops() {
        return smartCrops;
    }

    public Materials setSmartCrops(List<Object> smartCrops) {
        this.smartCrops = smartCrops;
        return this;
    }

    public List<Object> getSmartRelights() {
        return smartRelights;
    }

    public Materials setSmartRelights(List<Object> smartRelights) {
        this.smartRelights = smartRelights;
        return this;
    }

    public List<SoundChannelMapping> getSoundChannelMappings() {
        return soundChannelMappings;
    }

    public Materials setSoundChannelMappings(List<SoundChannelMapping> soundChannelMappings) {
        this.soundChannelMappings = soundChannelMappings;
        return this;
    }

    public List<Speed> getSpeeds() {
        return speeds;
    }

    public Materials setSpeeds(List<Speed> speeds) {
        this.speeds = speeds;
        return this;
    }

    public List<Sticker> getStickers() {
        return stickers;
    }

    public Materials setStickers(List<Sticker> stickers) {
        this.stickers = stickers;
        return this;
    }

    public List<Object> getTailLeaders() {
        return tailLeaders;
    }

    public Materials setTailLeaders(List<Object> tailLeaders) {
        this.tailLeaders = tailLeaders;
        return this;
    }

    public List<TextTemplate> getTextTemplates() {
        return textTemplates;
    }

    public Materials setTextTemplates(List<TextTemplate> textTemplates) {
        this.textTemplates = textTemplates;
        return this;
    }

    public List<Text> getTexts() {
        return texts;
    }

    public Materials setTexts(List<Text> texts) {
        this.texts = texts;
        return this;
    }

    public List<Transition> getTransitions() {
        return transitions;
    }

    public Materials setTransitions(List<Transition> transitions) {
        this.transitions = transitions;
        return this;
    }

    public List<VideoEffect> getVideoEffects() {
        return videoEffects;
    }

    public Materials setVideoEffects(List<VideoEffect> videoEffects) {
        this.videoEffects = videoEffects;
        return this;
    }

    public List<VideoTracking> getVideoTrackings() {
        return videoTrackings;
    }

    public Materials setVideoTrackings(List<VideoTracking> videoTrackings) {
        this.videoTrackings = videoTrackings;
        return this;
    }

    public List<Video> getVideos() {
        return videos;
    }

    public Materials setVideos(List<Video> videos) {
        this.videos = videos;
        return this;
    }

    public List<VocalBeautify> getVocalBeautifys() {
        return vocalBeautifys;
    }

    public Materials setVocalBeautifys(List<VocalBeautify> vocalBeautifys) {
        this.vocalBeautifys = vocalBeautifys;
        return this;
    }

    public List<VocalSeparation> getVocalSeparations() {
        return vocalSeparations;
    }

    public Materials setVocalSeparations(List<VocalSeparation> vocalSeparations) {
        this.vocalSeparations = vocalSeparations;
        return this;
    }

    public List<Object> getAudioTrackIndexes() {
        return audioTrackIndexes;
    }

    public Materials setAudioTrackIndexes(List<Object> audioTrackIndexes) {
        this.audioTrackIndexes = audioTrackIndexes;
        return this;
    }

    public List<AiTranslate> getAiTranslates() {
        return aiTranslates;
    }

    public Materials setAiTranslates(List<AiTranslate> aiTranslates) {
        this.aiTranslates = aiTranslates;
        return this;
    }

    public List<TimeMark> getTimeMarks() {
        return timeMarks;
    }

    public Materials setTimeMarks(List<TimeMark> timeMarks) {
        this.timeMarks = timeMarks;
        return this;
    }

    public List<CommonMask> getCommonMask() {
        return commonMask;
    }

    public Materials setCommonMask(List<CommonMask> commonMask) {
        this.commonMask = commonMask;
        return this;
    }
}
