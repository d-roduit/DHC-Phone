package applications.Photos.controllers;

import applications.Photos.models.AlbumModel;
import applications.Photos.views.AlbumView;

public class AlbumController {

    private AlbumView albumView;
    private AlbumModel albumModel;

    public AlbumController(AlbumView albumView, AlbumModel albumModel) {
        this.albumView = albumView;
        this.albumModel = albumModel;

//        this.albumView.addGoBackButtonListener(e -> goBack());
//        this.albumView.addCreateAlbumButtonListener(e -> createAlbum());
    }

    private void goBack() {
        System.out.println("go back cliqué");
    }

    private void createAlbum() {
        System.out.println("album cliqué");
    }
}
