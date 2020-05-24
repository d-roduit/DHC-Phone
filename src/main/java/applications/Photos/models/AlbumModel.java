package applications.Photos.models;

import applications.Photos.controllers.PictureController;

public class AlbumModel {

    private String name;
    private PictureController[] pictureControllers;


    public void addPicture() {

    }

    public void displayPictures() {

    }

    // Quand on supprime une photo dans un album, ca la supprime dans tous les autres albums où elle se trouve
    public void deletePicture() {

    }

    public void setName(String name) {
        this.name = name;
    }

}
