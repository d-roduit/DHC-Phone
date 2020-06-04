package applications.Photos.models;

import applications.Photos.controllers.PictureController;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties({"thumbnail"})
public class AlbumModel {

    private String thumbnail;

    @JsonProperty("albumName")
    private String name;

    @JsonProperty("pictures")
    private PictureModel[] pictureModels = new PictureModel[0];

    public AlbumModel() {

    }

    public AlbumModel(String name) {
        this.name = name;
    }

    public void addPicture() {

    }

    public void displayPictures() {

    }

    // Quand on supprime une photo dans un album, ca la supprime dans tous les autres albums où elle se trouve
    public void deletePicture() {

    }

    public int getNbPictures() {
        return pictureModels.length;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    @JsonProperty("albumName")
    public String getName() {
        return name;
    }

    @JsonProperty("albumName")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("pictures")
    public PictureModel[] getPictureModels() {
        return pictureModels;
    }

    @JsonProperty("pictures")
    public void setPictureModels(PictureModel[] pictureModels) {
        this.pictureModels = pictureModels;
    }

}
