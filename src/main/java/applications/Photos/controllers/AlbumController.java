package applications.Photos.controllers;

import applications.Photos.models.AlbumModel;
import applications.Photos.views.AlbumView;

public class AlbumController {

    private final AlbumView albumView;
    private final AlbumModel albumModel;

    public AlbumController(AlbumModel albumModel, AlbumView albumView) {
        this.albumModel = albumModel;
        this.albumView = albumView;
    }

    public AlbumView getAlbumView() {
        return albumView;
    }

    public AlbumModel getAlbumModel() {
        return albumModel;
    }
}
