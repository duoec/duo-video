
package com.duoec.video.jy.dto.info;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Canvase extends BaseMaterial {

    @JsonProperty("album_image")
    public String albumImage;

    public Double blur;

    public String color;

    public String image;

    @JsonProperty("image_id")
    public String imageId;

    @JsonProperty("image_name")
    public String imageName;

    @JsonProperty("source_platform")
    public Integer sourcePlatform;

    @JsonProperty("team_id")
    public String teamId;

    public String getAlbumImage() {
        return albumImage;
    }

    public Canvase setAlbumImage(String albumImage) {
        this.albumImage = albumImage;
        return this;
    }

    public Double getBlur() {
        return blur;
    }

    public Canvase setBlur(Double blur) {
        this.blur = blur;
        return this;
    }

    public String getColor() {
        return color;
    }

    public Canvase setColor(String color) {
        this.color = color;
        return this;
    }

    public Canvase setId(String id) {
        this.id = id;
        return this;
    }

    public String getImage() {
        return image;
    }

    public Canvase setImage(String image) {
        this.image = image;
        return this;
    }

    public String getImageId() {
        return imageId;
    }

    public Canvase setImageId(String imageId) {
        this.imageId = imageId;
        return this;
    }

    public String getImageName() {
        return imageName;
    }

    public Canvase setImageName(String imageName) {
        this.imageName = imageName;
        return this;
    }

    public Integer getSourcePlatform() {
        return sourcePlatform;
    }

    public Canvase setSourcePlatform(Integer sourcePlatform) {
        this.sourcePlatform = sourcePlatform;
        return this;
    }

    public String getTeamId() {
        return teamId;
    }

    public Canvase setTeamId(String teamId) {
        this.teamId = teamId;
        return this;
    }

    public Canvase setType(String type) {
        this.type = type;
        return this;
    }
}
