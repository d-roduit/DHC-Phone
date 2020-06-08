package applications.Photos.controllers;

import applications.Photos.Main;
import applications.Photos.models.AlbumModel;
import applications.Photos.models.GalleryModel;
import applications.Photos.views.AlbumView;
import applications.Photos.views.GalleryView;
import ch.dhc.Configuration;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

public class GalleryController {

    private GalleryModel galleryModel;
    private GalleryView galleryView;

    private final Main main;

    public GalleryController(GalleryModel galleryModel, GalleryView galleryView, Main main) {
        this.galleryModel = galleryModel;
        this.galleryView = galleryView;
        this.main = main;

        initListeners();
    }

    private void initListeners() {
        galleryView.getCreateAlbumButton().addActionListener(e -> createAlbum());

        galleryView.getPanelAlbumModelMap().forEach((albumPreviewPanel, albumModel) -> {
            albumPreviewPanel.addMouseListener(albumPreviewPanelMouseListener(albumModel));
        });
    }

    public void deleteAlbum(AlbumController albumController) {
        AlbumModel albumModelToDelete = albumController.getAlbumModel();

        // Delete album folder on the file system.
        File albumFolder = new File(Configuration.getInstance().getPicturesFolderPath() + albumModelToDelete.getName());
        if (albumFolder.exists()) {
            albumFolder.delete();
        }

        // Update the model.
        galleryModel.deleteAlbum(albumModelToDelete);

        // Remove ancient gallery view from main.
        main.remove(galleryView);

        // Create a new gallery view.
        galleryView = new GalleryView(galleryModel);

        // Add the new gallery view to main.
        main.add(galleryView, String.valueOf(galleryView.hashCode()));

        // Add the events on the albumPanels
        initListeners();

        // Show the galleryView
        displayGalleryView();
    }

    private void createAlbum() {
        String albumName = JOptionPane.showInputDialog(
                main,
                "Folder name",
                "Create folder",
                JOptionPane.QUESTION_MESSAGE
        );

        if (albumName != null) {
            // Create new Directory on physical drive
            File albumFolder = new File(Configuration.getInstance().getPicturesFolderPath() + albumName);

            if (!albumFolder.exists()) {
                boolean folderHasBeenCreated = albumFolder.mkdir();

                if (folderHasBeenCreated) {

                    albumName = albumName.replaceAll("[\\Q^<>:\\\"/\\E]", "");

                    AlbumModel newAlbumModel = galleryModel.createAlbum(albumName);

                    JPanel albumPreviewPanelAdded = galleryView.addAlbumPreview(newAlbumModel);

                    albumPreviewPanelAdded.addMouseListener(albumPreviewPanelMouseListener(newAlbumModel));
                } else {
                    JOptionPane.showMessageDialog(
                            main,
                            "Error when creating the folder. Try another name.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        }
    }

    private MouseAdapter albumPreviewPanelMouseListener(AlbumModel albumModel) {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                displayAlbum(albumModel);
            }
        };
    }

    private void displayAlbum(AlbumModel albumModel) {
        System.out.println("Album '" + albumModel.getName() + "' cliqué");

        // Create albumView and show it with CardLayout
        AlbumView albumView = new AlbumView(albumModel);
        AlbumController albumController = new AlbumController(albumModel, albumView, this);

        main.add(albumView, String.valueOf(albumView.hashCode()));

        main.getCardLayout().show(main, String.valueOf(albumView.hashCode()));
    }

    public void displayGalleryView() {
        main.getCardLayout().show(main, String.valueOf(galleryView.hashCode()));
    }

    public GalleryModel getGalleryModel() {
        return galleryModel;
    }

    public GalleryView getGalleryView() {
        return galleryView;
    }

    public Main getMain() {
        return main;
    }
}
