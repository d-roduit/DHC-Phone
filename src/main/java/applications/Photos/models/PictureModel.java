package applications.Photos.models;

import ch.dhc.Configuration;

public class PictureModel {

    private String name;
    private String albumName;

    public PictureModel(String name, String albumName) {
        this.name = name;
        this.albumName = albumName;
    }

    public void copyPictureToAlbum() {

    }

    public void displayRealSizePicture() {

    }

    public void displayThumbnail() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getPath() {
        return Configuration.getInstance().getPicturesFolderPath() + albumName + "/" + name;
    }
}
