package applications.Photos.controllers;

import applications.Photos.models.AlbumModel;
import applications.Photos.models.GalleryModel;
import applications.Photos.views.GalleryView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GalleryController {

    private final GalleryModel galleryModel;
    private final GalleryView galleryView;

    public GalleryController(GalleryModel galleryModel, GalleryView galleryView) {
        this.galleryModel = galleryModel;
        this.galleryView = galleryView;

        initListeners();
    }

    private void goBack() {
        System.out.println("go back cliqué");
    }

    private void createAlbum() {
        AlbumModel newAlbumModel = galleryModel.createAlbum("Album " + galleryModel.getAlbumModels().size());

        galleryView.addAlbumPreview(newAlbumModel);

        System.out.println("Nouvel album créé");
    }

    private void initListeners() {
        galleryView.getGoBackButton().addActionListener(e -> goBack());
        galleryView.getCreateAlbumButton().addActionListener(e -> createAlbum());
    }

    public GalleryModel getGalleryModel() {
        return galleryModel;
    }

    public GalleryView getGalleryView() {
        return galleryView;
    }
}
