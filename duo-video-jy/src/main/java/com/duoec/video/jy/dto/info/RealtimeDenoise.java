package com.duoec.video.jy.dto.info;

import com.duoec.video.jy.dto.BaseResource;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RealtimeDenoise extends BaseResource {
    @JsonProperty("denoise_mode")
    private Double denoiseMode;

    @JsonProperty("denoise_rate")
    private Double denoiseRate;

    @JsonProperty("is_denoise")
    private Boolean isDenoise;

    @JsonProperty("sami_name")
    private String samiName;

    @JsonProperty("sami_type")
    private Integer samiType;

    @JsonProperty("sami_version")
    private String samiVersion;

    private String type;

    @Override
    public RealtimeDenoise setId(String id) {
        super.setId(id);
        return this;
    }

    @Override
    public RealtimeDenoise setPath(String path) {
        super.setPath(path);
        return this;
    }

    public Double getDenoiseMode() {
        return denoiseMode;
    }

    public RealtimeDenoise setDenoiseMode(Double denoiseMode) {
        this.denoiseMode = denoiseMode;
        return this;
    }

    public Double getDenoiseRate() {
        return denoiseRate;
    }

    public RealtimeDenoise setDenoiseRate(Double denoiseRate) {
        this.denoiseRate = denoiseRate;
        return this;
    }

    public Boolean getDenoise() {
        return isDenoise;
    }

    public RealtimeDenoise setDenoise(Boolean denoise) {
        isDenoise = denoise;
        return this;
    }

    public String getSamiName() {
        return samiName;
    }

    public RealtimeDenoise setSamiName(String samiName) {
        this.samiName = samiName;
        return this;
    }

    public Integer getSamiType() {
        return samiType;
    }

    public RealtimeDenoise setSamiType(Integer samiType) {
        this.samiType = samiType;
        return this;
    }

    public String getSamiVersion() {
        return samiVersion;
    }

    public RealtimeDenoise setSamiVersion(String samiVersion) {
        this.samiVersion = samiVersion;
        return this;
    }

    public String getType() {
        return type;
    }

    public RealtimeDenoise setType(String type) {
        this.type = type;
        return this;
    }
}
