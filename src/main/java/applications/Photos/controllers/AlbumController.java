package applications.Photos.controllers;

import applications.Photos.Main;
import applications.Photos.models.AlbumModel;
import applications.Photos.models.GalleryModel;
import applications.Photos.models.PictureModel;
import applications.Photos.views.AlbumView;
import applications.Photos.views.GalleryView;
import applications.Photos.views.PictureView;
import ch.dhc.Configuration;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;

public class AlbumController {

    private AlbumView albumView;
    private final AlbumModel albumModel;
    private final GalleryController galleryController;
    private final Main main;

    public enum MoveDirection {
        NONE, NEXT_ONLY, PREVIOUS_ONLY, PREVIOUS_NEXT
    }

    public AlbumController(AlbumModel albumModel, AlbumView albumView, GalleryController galleryController) {
        this.albumModel = albumModel;
        this.albumView = albumView;
        this.galleryController = galleryController;
        this.main = galleryController.getMain();

        initListeners();
    }

    private void initListeners() {
        albumView.getGoBackButton().addActionListener(e -> goBack());
        albumView.getDeleteAlbumButton().addActionListener(e -> deleteAlbum());
        albumView.getPictureLabelPanelMap().forEach((pictureLabelPanel, pictureModel) -> {
            pictureLabelPanel.addMouseListener(pictureLabelPanelMouseListener(pictureModel));
        });
        albumView.getAddPictureButton().addActionListener(e -> addPictures());
    }

    private MouseAdapter pictureLabelPanelMouseListener(PictureModel pictureModel) {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                displayPicture(pictureModel);
            }
        };
    }

    public void displayPicture(PictureModel pictureModel) {
        System.out.println("picture '" + pictureModel.getName() + "' cliquée");

        List<PictureModel> pictureModelsList = albumModel.getPictureModels();

        int pictureModelIndex = pictureModelsList.indexOf(pictureModel);

        MoveDirection moveDirection = MoveDirection.PREVIOUS_NEXT;

        if (pictureModelsList.size() == 1) {
            moveDirection = MoveDirection.NONE;
        } else {
            if (pictureModelIndex == 0) {
                moveDirection = MoveDirection.NEXT_ONLY;
            } else if (pictureModelIndex == pictureModelsList.size() - 1) {
                moveDirection = MoveDirection.PREVIOUS_ONLY;
            }
        }

        // Create pictureView and show it with CardLayout
        PictureView pictureView = new PictureView(pictureModel, moveDirection);
        PictureController pictureController = new PictureController(pictureModel, pictureView, this);

        main.add(pictureView, String.valueOf(pictureView.hashCode()));

        main.getCardLayout().show(main, String.valueOf(pictureView.hashCode()));
    }

    private void goBack() {
        // Re-create gallery view to have thumbnails and pictures count updated
        GalleryModel galleryModelUpdated = new GalleryModel();
        GalleryView galleryViewUpdated = new GalleryView(galleryModelUpdated);
        GalleryController galleryControllerUpdated = new GalleryController(galleryModelUpdated, galleryViewUpdated, main);

        main.add(galleryControllerUpdated.getGalleryView(), String.valueOf(galleryControllerUpdated.getGalleryView().hashCode()));

        main.remove(galleryController.getGalleryView());

        galleryControllerUpdated.displayGalleryView();
    }

    private void deleteAlbum() {
        int deleteAlbumOption = JOptionPane.showConfirmDialog(
                main,
                "Are you sure you want to delete this album ?",
                "Delete album",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
        );

        if (deleteAlbumOption == JOptionPane.YES_OPTION) {
            String albumFolderPath = Configuration.getInstance().getPicturesFolderPath() + albumModel.getName();

            // Delete all pictures of the album.
            for (PictureModel pictureModel: albumModel.getPictureModels()) {
                File pictureFile = new File(albumFolderPath + "/" + pictureModel.getName());

                if (pictureFile.exists()) {
                    pictureFile.delete();
                }
            }

            galleryController.deleteAlbum(this);
        }
    }

    private void addPictures() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setMultiSelectionEnabled(true);
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setAcceptAllFileFilterUsed(false);

        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                ".jpg, .jpeg, .png, .gif",
                "jpg", "jpeg", "png", "gif"
        );

        fileChooser.setFileFilter(filter);

        int fileChooserResponse = fileChooser.showOpenDialog(main);

        // Files have been chosen and OK button clicked.
        if (fileChooserResponse == JFileChooser.APPROVE_OPTION) {
            File[] selectedFiles = fileChooser.getSelectedFiles();

            String picturesFolderPath = Configuration.getInstance().getPicturesFolderPath();
            String albumName = albumModel.getName();

            for (File selectedFile: selectedFiles) {
                File destinationFile = new File(picturesFolderPath + albumName + "/" + selectedFile.getName());

                if (!destinationFile.exists()) {
                    try {
                        Files.copy(selectedFile.toPath(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

                        // Update the view to display the copied files.
                        PictureModel pictureModel = new PictureModel(selectedFile.getName(), albumName);

                        albumModel.addPicture(pictureModel);

                    } catch (IOException e) {
                        System.err.println("Copying files failed.");
                        e.printStackTrace();
                    }
                }
            }

            // Update the view after adding all the picturePreviews
            updateAlbumView(albumModel);

        } else {
            System.out.println("No files have been chosen.");
        }
    }

    public void displayAlbumView() {
        main.getCardLayout().show(main, String.valueOf(albumView.hashCode()));
    }

    public void deletePicture(PictureController pictureController) {
        PictureModel pictureModelToDelete = pictureController.getPictureModel();

        // Delete picture on the file system.
        File pictureFile = new File(pictureModelToDelete.getPath());
        if (pictureFile.exists()) {
            pictureFile.delete();
        }

        // Update the model.
        albumModel.deletePicture(pictureModelToDelete);
        albumModel.updateThumbnail();

        // Update the view
        updateAlbumView(albumModel);

        // Show the galleryView
        displayAlbumView();
    }

    private void updateAlbumView(AlbumModel albumModel) {
        main.remove(albumView);

        albumView = new AlbumView(albumModel);

        main.add(albumView, String.valueOf(albumView.hashCode()));

        main.getCardLayout().show(main, String.valueOf(albumView.hashCode()));

        initListeners();
    }

    public AlbumView getAlbumView() {
        return albumView;
    }

    public AlbumModel getAlbumModel() {
        return albumModel;
    }

    public Main getMain() {
        return main;
    }
}
