package applications.Photos.models;

import applications.Photos.controllers.PictureController;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PictureModel {

    @JsonProperty("pictureName")
    String name;

    public PictureModel() {

    }

    public void copyPictureToAlbum() {

    }

    public void displayRealSizePicture() {

    }

    public void displayThumbnail() {

    }

    @JsonProperty("pictureName")
    public String getName() {
        return name;
    }

    @JsonProperty("pictureName")
    public void setName(String name) {
        this.name = name;
    }

}
