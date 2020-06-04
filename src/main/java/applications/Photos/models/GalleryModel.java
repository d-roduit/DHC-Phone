package applications.Photos.models;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class GalleryModel {

    String galleryTitle = "Albums";
    List<AlbumModel> albumModels = fetchAlbums();

    public GalleryModel() {

    }

    public AlbumModel createAlbum(String name) {
        AlbumModel newAlbumModel = new AlbumModel(name);

        albumModels.add(newAlbumModel);

        return newAlbumModel;
    }

    public void deleteAlbum() {

    }

    public void displayAlbums() {

    }

    private List<AlbumModel> fetchAlbums() {

        ObjectMapper objectMapper = new ObjectMapper();

        String albumsJSONFilePath = "/applications/Photos/albums.json";

        InputStream albumsJSONFileInputStream = GalleryModel.class.getResourceAsStream(albumsJSONFilePath);

        List<AlbumModel> albumModels = null;

        JavaType type = objectMapper.getTypeFactory().constructCollectionType(List.class, AlbumModel.class);

        try {
            albumModels = objectMapper.readValue(albumsJSONFileInputStream, type);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (AlbumModel albumModel: albumModels) {
            System.out.println(albumModel.getName());

            for (PictureModel pictureModel: albumModel.getPictureModels()) {
                System.out.println(pictureModel.getName());
            }
        }

        return albumModels;
    }

    public String getGalleryTitle() {
        return galleryTitle;
    }

    public void setGalleryTitle(String galleryTitle) {
        this.galleryTitle = galleryTitle;
    }

    public List<AlbumModel> getAlbumModels() {
        return albumModels;
    }
}
