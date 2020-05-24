package applications.Photos.controllers;

import applications.Photos.models.GalleryModel;
import applications.Photos.views.GalleryView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GalleryController {

    private GalleryView galleryView;
    private GalleryModel galleryModel;

    public GalleryController(GalleryView galleryView, GalleryModel galleryModel) {
        this.galleryView = galleryView;
        this.galleryModel = galleryModel;

        this.galleryView.addGoBackButtonListener(e -> goBack());
        this.galleryView.addCreateAlbumButtonListener(e -> createAlbum());
    }

    private void goBack() {
        System.out.println("go back cliqué");
    }

    private void createAlbum() {
        System.out.println("album cliqué");
    }

}
