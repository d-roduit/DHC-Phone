package applications.Photos.models;

import applications.Photos.ThumbnailGenerator;
import ch.dhc.Configuration;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.imageio.ImageIO;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AlbumModel {

    private String thumbnailName;

    private String name;

    private final List<PictureModel> pictureModels;

    public AlbumModel(String name) {
        this.name = name;

        pictureModels = fetchPictures();

        generateThumbnail();
    }

    private List<PictureModel> fetchPictures() {
        String picturesFolderPath = Configuration.getInstance().getPicturesFolderPath();
        String[] extensionsToFetch = {"jpg", "jpeg", "png", "gif"};

        File[] pictureFiles = fetchFilesInDirectory(picturesFolderPath + name, extensionsToFetch);

        List<PictureModel> pictureModelsList = new ArrayList<PictureModel>();

        if (pictureFiles != null) {
            for (File pictureFile: pictureFiles) {
                PictureModel pictureModel = new PictureModel(pictureFile.getName(), name);

                pictureModelsList.add(pictureModel);
            }
        }

        return pictureModelsList;
    }

    private File[] fetchFilesInDirectory(String directoryPath, String[] fileExtensions) {
        try {
            File directory = new File(directoryPath);

            if (!directory.isDirectory()) {
                System.out.println("The given directory path is not a directory");
                return null;
            }

            File[] files = directory.listFiles(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    for (String extension: fileExtensions) {
                        if (name.endsWith(extension.toLowerCase()) || name.endsWith(extension.toUpperCase())) {
                            return true;
                        }
                    }
                    return false;
                }
            });

            return files;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public void addPicture() {

    }

    public void deletePicture(PictureModel pictureModel) {
        pictureModels.remove(pictureModel);
    }

    public void generateThumbnail() {
        updateThumbnail();

        String picturesFolderPath = Configuration.getInstance().getPicturesFolderPath();

        if (thumbnailName != null) {
            File thumbnailFile = new File(picturesFolderPath + "thumbnails/" + thumbnailName);

            if (!thumbnailFile.exists()) {
                File sourceImage = new File(picturesFolderPath + name + "/" + thumbnailName);

                if (sourceImage.exists()) {
                    try {
                        ThumbnailGenerator.generate(sourceImage, picturesFolderPath + "thumbnails/" + thumbnailName);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public void updateThumbnail() {
        boolean albumHasPictures = getNbPictures() > 0;

        if (albumHasPictures) {
            thumbnailName = pictureModels.get(getNbPictures() - 1).getName();
        }
    }

    public int getNbPictures() {
        return pictureModels.size();
    }

    public BufferedImage getThumbnailImage() throws IOException {
        String thumbnailImagePath = Configuration.getInstance().getPicturesFolderPath() + "thumbnails/" + thumbnailName;

        File thumbnailImageFile = new File(thumbnailImagePath);

        if (thumbnailName == null || !thumbnailImageFile.exists()) {
            return null;
        }

        return ImageIO.read(new File(thumbnailImagePath));
    }

    public String getThumbnailName() {
        return thumbnailName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PictureModel> getPictureModels() {
        return pictureModels;
    }
}
