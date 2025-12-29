package com.duoec.video.jy.dto.info;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Draft implements Serializable {
    @JsonProperty("category_id")
    private String categoryId;

    @JsonProperty("category_name")
    private String categoryName;

    @JsonProperty("combination_id")
    private String combinationId;

    private JianYingProjectInfo draft;

    @JsonProperty("formula_id")
    private String formulaId;

    private String id;

    private String name;

    @JsonProperty("precompile_combination")
    private boolean precompileCombination;

    private String type;

    public String getCategoryId() {
        return categoryId;
    }

    public Draft setCategoryId(String categoryId) {
        this.categoryId = categoryId;
        return this;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public Draft setCategoryName(String categoryName) {
        this.categoryName = categoryName;
        return this;
    }

    public String getCombinationId() {
        return combinationId;
    }

    public Draft setCombinationId(String combinationId) {
        this.combinationId = combinationId;
        return this;
    }

    public JianYingProjectInfo getDraft() {
        return draft;
    }

    public Draft setDraft(JianYingProjectInfo draft) {
        this.draft = draft;
        return this;
    }

    public String getFormulaId() {
        return formulaId;
    }

    public Draft setFormulaId(String formulaId) {
        this.formulaId = formulaId;
        return this;
    }

    public String getId() {
        return id;
    }

    public Draft setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Draft setName(String name) {
        this.name = name;
        return this;
    }

    public boolean isPrecompileCombination() {
        return precompileCombination;
    }

    public Draft setPrecompileCombination(boolean precompileCombination) {
        this.precompileCombination = precompileCombination;
        return this;
    }

    public String getType() {
        return type;
    }

    public Draft setType(String type) {
        this.type = type;
        return this;
    }
}
