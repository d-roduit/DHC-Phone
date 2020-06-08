package applications.Photos.models;

import ch.dhc.Configuration;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

public class GalleryModel {

    private final String galleryTitle = "Albums";
    private final List<AlbumModel> albumModels;
    private final String thumbnailDirectoryName = "thumbnails";

    public GalleryModel() {
        albumModels = fetchAlbums();
    }

    public AlbumModel createAlbum(String albumName) {
        AlbumModel albumModel = new AlbumModel(albumName);

        albumModels.add(albumModel);

        return albumModel;
    }

    public void deleteAlbum(AlbumModel albumModel) {
        albumModels.remove(albumModel);
    }

    private List<AlbumModel> fetchAlbums() {

        File[] albumFolders = fetchFoldersInDirectory(Configuration.getInstance().getPicturesFolderPath());

        List<AlbumModel> albumModelsList = new ArrayList<AlbumModel>();

        if (albumFolders != null) {
            for (File albumFolder: albumFolders) {
                // Check that the folder is not the thumbnails folder.
                if (!albumFolder.getName().equals(thumbnailDirectoryName)) {
                    AlbumModel albumModel = new AlbumModel(albumFolder.getName());

                    albumModelsList.add(albumModel);
                }
            }
        }

        return albumModelsList;
    }

    private File[] fetchFoldersInDirectory(String directoryPath) {
        try {
            File directory = new File(directoryPath);

            if (!directory.isDirectory()) {
                System.err.println("The given directory path is not a directory");
                return null;
            }

            File[] folders = directory.listFiles(new FileFilter() {
                @Override
                public boolean accept(File f) {
                    return f.isDirectory();
                }
            });

            return folders;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public String getGalleryTitle() {
        return galleryTitle;
    }

    public List<AlbumModel> getAlbumModels() {
        return albumModels;
    }

    public String getThumbnailDirectoryName() {
        return thumbnailDirectoryName;
    }

    public String getThumbnailDirectoryPath() {
        return Configuration.getInstance().getPicturesFolderPath() + thumbnailDirectoryName + "/";
    }

}
