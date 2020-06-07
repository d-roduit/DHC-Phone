package applications.Photos;

import applications.Photos.models.AlbumModel;
import ch.dhc.ImageLabel;
import jiconfont.icons.google_material_design_icons.GoogleMaterialDesignIcons;
import jiconfont.swing.IconFontSwing;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public final class AlbumPreviewPanel extends JPanel {

    AlbumModel albumModel;
    Dimension dimension = new Dimension(345, 60);

    public AlbumPreviewPanel(AlbumModel albumModel) {
        this.albumModel = albumModel;
        createAlbumPreviewPanel();
    }

    private void createAlbumPreviewPanel() {
        setOpaque(false);
        setLayout(new BorderLayout());
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setAlignmentX(Component.LEFT_ALIGNMENT);
        setMinimumSize(dimension);
        setPreferredSize(dimension);
        setMaximumSize(dimension);

        // Création de la thumbnail s'il existe une image dans l'album.
        ImageLabel thumbnailImageLabel = null;

        try {
            BufferedImage thumbnailImage = albumModel.getThumbnailImage();

            if (thumbnailImage != null) {
                thumbnailImageLabel = new ImageLabel(thumbnailImage);
            }
        } catch (IOException e) {
            System.out.println("Exception levée : Création d'un panel de remplacement pour l'image.");
            e.printStackTrace();
        }

        JPanel imageReplacementPanel = new JPanel();
        imageReplacementPanel.setLayout(new BorderLayout());
        Dimension imageReplacementPanelDimension = new Dimension(60, 0);
        imageReplacementPanel.setOpaque(true);
        imageReplacementPanel.setBackground(Color.GRAY);
        imageReplacementPanel.setPreferredSize(imageReplacementPanelDimension);
        imageReplacementPanel.setMaximumSize(imageReplacementPanelDimension);

        Icon imageReplacementIcon = IconFontSwing.buildIcon(GoogleMaterialDesignIcons.FILTER, 24, Color.BLACK);
        JLabel imageReplacementLabel = new JLabel(imageReplacementIcon);

        JPanel albumNamePanel = new JPanel();
        albumNamePanel.setLayout(new BorderLayout());
        albumNamePanel.setOpaque(false);
        albumNamePanel.setBorder(new EmptyBorder(0, 15, 0, 0));

        JLabel albumName = new JLabel(albumModel.getName());
        albumName.setHorizontalAlignment(SwingConstants.LEFT);

        JPanel albumNbPicturesPanel = new JPanel();
        albumNbPicturesPanel.setLayout(new BorderLayout());
        albumNbPicturesPanel.setOpaque(false);
        albumNbPicturesPanel.setBorder(new EmptyBorder(0, 0, 0, 15));

        JLabel albumNbPictures = new JLabel(String.valueOf(albumModel.getNbPictures()));
        albumNbPictures.setHorizontalAlignment(SwingConstants.LEFT);

        albumNamePanel.add(albumName, BorderLayout.WEST);

        albumNbPicturesPanel.add(albumNbPictures, BorderLayout.EAST);

        if (thumbnailImageLabel != null) {
            add(thumbnailImageLabel, BorderLayout.WEST);
        } else {
            imageReplacementPanel.add(imageReplacementLabel, BorderLayout.CENTER);
            add(imageReplacementPanel, BorderLayout.WEST);
        }

        add(albumNamePanel, BorderLayout.CENTER);
        add(albumNbPicturesPanel, BorderLayout.EAST);
    }

    public AlbumModel getAlbumModel() {
        return albumModel;
    }
}
