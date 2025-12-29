package com.duoec.video.jy.dto.info;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Algorithm implements Serializable {
    @JsonProperty("algorithm_id")
    private String algorithmId;

    private String type;

    public String getAlgorithmId() {
        return algorithmId;
    }

    public Algorithm setAlgorithmId(String algorithmId) {
        this.algorithmId = algorithmId;
        return this;
    }

    public String getType() {
        return type;
    }

    public Algorithm setType(String type) {
        this.type = type;
        return this;
    }
}
