package applications.Photos.views;

import applications.Photos.IconsUtility;
import applications.Photos.ThumbnailGenerator;
import applications.Photos.WrapLayout;
import applications.Photos.models.AlbumModel;
import applications.Photos.models.GalleryModel;
import ch.dhc.ImageLabel;
import jiconfont.icons.google_material_design_icons.GoogleMaterialDesignIcons;
import jiconfont.swing.IconFontSwing;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GalleryView extends JPanel {

    private final GalleryModel galleryModel;

    private JPanel topBarPanel;
    private JScrollPane albumsScrollPane;
    private JButton createAlbumButton;
    private Map<JPanel, AlbumModel> panelAlbumModelMap = new HashMap<JPanel, AlbumModel>();

    public GalleryView(GalleryModel galleryModel) {
        this.galleryModel = galleryModel;

        setLayout(new BorderLayout());
        setOpaque(false);

        topBarPanel = createTopBarPanel();
        albumsScrollPane = createAlbumsScrollPane();

        add(topBarPanel, BorderLayout.NORTH);
        add(albumsScrollPane, BorderLayout.CENTER);
    }

    private JPanel createTopBarPanel() {
        JPanel topBarPanel = new JPanel();
        topBarPanel.setOpaque(false);
        topBarPanel.setLayout(new BorderLayout());

        JLabel viewTitle = new JLabel(galleryModel.getGalleryTitle());
        viewTitle.setFont(new Font("Arial", Font.BOLD, 25));
        viewTitle.setHorizontalAlignment(JLabel.CENTER);
        viewTitle.setForeground(Color.WHITE);

        Icon addAlbumIcon = IconFontSwing.buildIcon(GoogleMaterialDesignIcons.ADD, 22, IconsUtility.iconsColor);
        createAlbumButton = IconsUtility.createIconButton(addAlbumIcon);

        topBarPanel.add(Box.createRigidArea(new Dimension(32, 0)), BorderLayout.WEST);
        topBarPanel.add(viewTitle, BorderLayout.CENTER);
        topBarPanel.add(createAlbumButton, BorderLayout.EAST);

        return topBarPanel;
    }

    private JScrollPane createAlbumsScrollPane() {
        JPanel albumsPanel = new JPanel();
        albumsPanel.setOpaque(false);
        albumsPanel.setLayout(new WrapLayout(WrapLayout.LEFT, 10, 15));

        List<AlbumModel> albumModels = galleryModel.getAlbumModels();

        for (AlbumModel albumModel: albumModels) {
            JPanel albumPreviewPanel = createAlbumPreviewPanel(albumModel);

            albumsPanel.add(albumPreviewPanel);

            panelAlbumModelMap.put(albumPreviewPanel, albumModel);
        }

        JScrollPane albumsScrollPane = new JScrollPane(albumsPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        albumsScrollPane.setOpaque(false);
        albumsScrollPane.getViewport().setOpaque(false);
        albumsScrollPane.setBorder(new EmptyBorder(20, 3, 0, 0));
        albumsScrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(0,0));
        albumsScrollPane.getVerticalScrollBar().setUnitIncrement(10); // Change the scrolling speed

        return albumsScrollPane;
    }

    private JPanel createAlbumPreviewPanel(AlbumModel albumModel) {
        JPanel albumPreviewPanel = new JPanel();

        albumPreviewPanel.setOpaque(false);
        albumPreviewPanel.setLayout(new BorderLayout());
        albumPreviewPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));

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

        Dimension imageReplacementPanelDimension = ThumbnailGenerator.getThumbnailDimension();
        JPanel imageReplacementPanel = new JPanel();
        imageReplacementPanel.setLayout(new BorderLayout());
        imageReplacementPanel.setOpaque(true);
        imageReplacementPanel.setBackground(Color.GRAY);
        imageReplacementPanel.setPreferredSize(imageReplacementPanelDimension);
        imageReplacementPanel.setMaximumSize(imageReplacementPanelDimension);

        Icon imageReplacementIcon = IconFontSwing.buildIcon(GoogleMaterialDesignIcons.FILTER, 35, Color.BLACK);
        JLabel imageReplacementLabel = new JLabel(imageReplacementIcon);

        JLabel albumNameLabel = new JLabel(albumModel.getName());
        albumNameLabel.setHorizontalAlignment(SwingConstants.LEFT);

        JLabel albumNbPicturesLabel = new JLabel(String.valueOf(albumModel.getNbPictures()));
        albumNbPicturesLabel.setHorizontalAlignment(SwingConstants.LEFT);

        if (thumbnailImageLabel != null) {
            albumPreviewPanel.add(thumbnailImageLabel, BorderLayout.NORTH);
        } else {
            imageReplacementPanel.add(imageReplacementLabel, BorderLayout.CENTER);
            albumPreviewPanel.add(imageReplacementPanel, BorderLayout.NORTH);
        }

        albumPreviewPanel.add(albumNameLabel, BorderLayout.CENTER);
        albumPreviewPanel.add(albumNbPicturesLabel, BorderLayout.SOUTH);

        return albumPreviewPanel;
    }

    public JButton getCreateAlbumButton() {
        return createAlbumButton;
    }

    public Map<JPanel, AlbumModel> getPanelAlbumModelMap() {
        return panelAlbumModelMap;
    }
}
