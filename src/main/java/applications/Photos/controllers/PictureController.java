package applications.Photos.controllers;

import applications.Photos.Main;
import applications.Photos.models.PictureModel;
import applications.Photos.views.PictureView;

import javax.swing.*;
import java.util.List;

public class PictureController {

    private final PictureModel pictureModel;
    private final PictureView pictureView;
    private final AlbumController albumController;
    private final Main main;

    public PictureController(PictureModel pictureModel, PictureView pictureView, AlbumController albumController) {
        this.pictureModel = pictureModel;
        this.pictureView = pictureView;
        this.albumController = albumController;
        this.main = albumController.getMain();

        initListeners();
    }

    private void initListeners() {
        pictureView.getGoBackButton().addActionListener(e -> goBack());
        pictureView.getDeletePictureButton().addActionListener(e -> deletePicture());
        pictureView.getPreviousPictureButton().addActionListener(e -> displayPreviousPicture());
        pictureView.getNextPictureButton().addActionListener(e -> displayNextPicture());
    }

    private void goBack() {
        albumController.displayAlbumView();
    }

    private void deletePicture() {
        int deletePictureOption = JOptionPane.showConfirmDialog(
                main,
                "Are you sure you want to delete this picture ?",
                "Delete picture",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
        );

        if (deletePictureOption == JOptionPane.YES_OPTION) {
            albumController.deletePicture(this);
        }

    }

    private void displayPreviousPicture() {
        int currentPictureModelIndex = albumController.getAlbumModel().getPictureModels().indexOf(pictureModel);

        if (currentPictureModelIndex > 0) {
            PictureModel previousPictureModel = albumController.getAlbumModel().getPictureModels().get(currentPictureModelIndex - 1);

            albumController.displayPicture(previousPictureModel);
        }
    }

    private void displayNextPicture() {
        List<PictureModel> pictureModelsList = albumController.getAlbumModel().getPictureModels();

        int currentPictureModelIndex = pictureModelsList.indexOf(pictureModel);

        if (currentPictureModelIndex < pictureModelsList.size() - 1) {
            PictureModel nextPictureModel = pictureModelsList.get(currentPictureModelIndex + 1);

            albumController.displayPicture(nextPictureModel);
        }
    }

    public PictureModel getPictureModel() {
        return pictureModel;
    }
}
