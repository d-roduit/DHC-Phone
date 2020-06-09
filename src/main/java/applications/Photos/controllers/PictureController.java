package applications.Photos.controllers;

import applications.Photos.Main;
import applications.Photos.models.PictureModel;
import applications.Photos.views.PictureView;
import ch.dhc.Configuration;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.List;

public class PictureController {

    private final PictureModel pictureModel;
    private PictureView pictureView;
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
        pictureView.getPictureNameLabel().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() == 2) {
                    super.mouseClicked(e);
                    modifyPictureName();
                }
            }
        });
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

    private void modifyPictureName() {
        String newPictureName = (String) JOptionPane.showInputDialog(
                main,
                "Picture name :",
                "Picture name :",
                JOptionPane.QUESTION_MESSAGE,
                null,
                null,
                pictureModel.getName()
        );

        if (newPictureName != null) {
            String albumFolderPath = Configuration.getInstance().getPicturesFolderPath() + albumController.getAlbumModel().getName();

            File currentPicturePath = new File(pictureModel.getPath());
            File newPicturePath = new File(albumFolderPath + "/" + newPictureName);

            boolean renamedSuccessfully = currentPicturePath.renameTo(newPicturePath);

            if (renamedSuccessfully) {
                pictureModel.setName(newPictureName);

                updatePictureView(pictureModel);
            } else {
                JOptionPane.showMessageDialog(
                    main,
                    "Error when renaming the picture. Try another name.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }

    private void updatePictureView(PictureModel pictureModel) {
        main.remove(pictureView);

        AlbumController.MoveDirection moveDirection = pictureView.getMoveDirection();

        pictureView = new PictureView(pictureModel, moveDirection);

        main.add(pictureView, String.valueOf(pictureView.hashCode()));

        main.getCardLayout().show(main, String.valueOf(pictureView.hashCode()));

        initListeners();
    }

    public PictureModel getPictureModel() {
        return pictureModel;
    }
}
