package com.duoec.video.jy.dto.info;

import com.duoec.video.jy.dto.TimeRange;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

public class GameplayConfig implements Serializable {
    @JsonProperty("ability_flag")
    private Integer abilityFlag;

    @JsonProperty("adjust_params")
    public List<AdjustParam> adjustParams;

    @JsonProperty("adjustable_config")
    public AdjustableConfig adjustableConfig;

    public String algorithm;

    @JsonProperty("algorithm_id")
    public String algorithmId;

    @JsonProperty("algorithm_type")
    public String algorithmType;

    @JsonProperty("category_id")
    public String categoryId;

    @JsonProperty("category_name")
    public String categoryName;

    @JsonProperty("gameplay_metatype")
    public String gameplayMetatype;

    @JsonProperty("gameplay_name")
    public String gameplayName;

    @JsonProperty("gameplay_source_timerange")
    private TimeRange gameplaySourceTimerange;

    @JsonProperty("is_limit_bach_feature")
    private Boolean isLimitBachFeature;

    @JsonProperty("local_task_id")
    private String localTaskId;

    private String path;

    private Boolean reshape;

    @JsonProperty("show_report_entrance")
    private Boolean showReportEntrance;

    public Integer getAbilityFlag() {
        return abilityFlag;
    }

    public GameplayConfig setAbilityFlag(Integer abilityFlag) {
        this.abilityFlag = abilityFlag;
        return this;
    }

    public List<AdjustParam> getAdjustParams() {
        return adjustParams;
    }

    public GameplayConfig setAdjustParams(List<AdjustParam> adjustParams) {
        this.adjustParams = adjustParams;
        return this;
    }

    public AdjustableConfig getAdjustableConfig() {
        return adjustableConfig;
    }

    public GameplayConfig setAdjustableConfig(AdjustableConfig adjustableConfig) {
        this.adjustableConfig = adjustableConfig;
        return this;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public GameplayConfig setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
        return this;
    }

    public String getAlgorithmId() {
        return algorithmId;
    }

    public GameplayConfig setAlgorithmId(String algorithmId) {
        this.algorithmId = algorithmId;
        return this;
    }

    public String getAlgorithmType() {
        return algorithmType;
    }

    public GameplayConfig setAlgorithmType(String algorithmType) {
        this.algorithmType = algorithmType;
        return this;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public GameplayConfig setCategoryId(String categoryId) {
        this.categoryId = categoryId;
        return this;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public GameplayConfig setCategoryName(String categoryName) {
        this.categoryName = categoryName;
        return this;
    }

    public String getGameplayMetatype() {
        return gameplayMetatype;
    }

    public GameplayConfig setGameplayMetatype(String gameplayMetatype) {
        this.gameplayMetatype = gameplayMetatype;
        return this;
    }

    public String getGameplayName() {
        return gameplayName;
    }

    public GameplayConfig setGameplayName(String gameplayName) {
        this.gameplayName = gameplayName;
        return this;
    }

    public TimeRange getGameplaySourceTimerange() {
        return gameplaySourceTimerange;
    }

    public GameplayConfig setGameplaySourceTimerange(TimeRange gameplaySourceTimerange) {
        this.gameplaySourceTimerange = gameplaySourceTimerange;
        return this;
    }

    public Boolean getLimitBachFeature() {
        return isLimitBachFeature;
    }

    public GameplayConfig setLimitBachFeature(Boolean limitBachFeature) {
        isLimitBachFeature = limitBachFeature;
        return this;
    }

    public String getLocalTaskId() {
        return localTaskId;
    }

    public GameplayConfig setLocalTaskId(String localTaskId) {
        this.localTaskId = localTaskId;
        return this;
    }

    public String getPath() {
        return path;
    }

    public GameplayConfig setPath(String path) {
        this.path = path;
        return this;
    }

    public Boolean getReshape() {
        return reshape;
    }

    public GameplayConfig setReshape(Boolean reshape) {
        this.reshape = reshape;
        return this;
    }

    public Boolean getShowReportEntrance() {
        return showReportEntrance;
    }

    public GameplayConfig setShowReportEntrance(Boolean showReportEntrance) {
        this.showReportEntrance = showReportEntrance;
        return this;
    }
}
