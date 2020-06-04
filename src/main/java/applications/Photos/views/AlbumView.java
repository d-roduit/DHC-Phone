package applications.Photos.views;

import applications.Photos.models.AlbumModel;
import applications.Photos.models.GalleryModel;

public class AlbumView {

    AlbumModel albumModel;

    public AlbumView(AlbumModel albumModel) {
        this.albumModel = albumModel;
    }

    public AlbumModel getAlbumModel() {
        return albumModel;
    }
}
