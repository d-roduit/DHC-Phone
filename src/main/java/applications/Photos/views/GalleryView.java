package applications.Photos.views;

import applications.Photos.ComponentUtility;
import applications.Photos.IconsUtility;
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
    private JPanel albumsPanel;
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
        albumsPanel = new JPanel();
        albumsPanel.setOpaque(false);
        albumsPanel.setLayout(new BoxLayout(albumsPanel, BoxLayout.Y_AXIS));
        albumsPanel.setPreferredSize(null);

        List<AlbumModel> albumModels = galleryModel.getAlbumModels();

        albumsPanel.add(ComponentUtility.createSeparator());

        for (AlbumModel albumModel: albumModels) {
            addAlbumPreview(albumModel);
        }

        JScrollPane albumsScrollPane = new JScrollPane(albumsPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        albumsScrollPane.setOpaque(false);
        albumsScrollPane.getViewport().setOpaque(false);
        albumsScrollPane.setBorder(new EmptyBorder(20, 0, 0, 0));
        albumsScrollPane.getVerticalScrollBar().setUnitIncrement(10); // Change the scrolling speed

        return albumsScrollPane;
    }

    public JPanel addAlbumPreview(AlbumModel albumModel) {
        JPanel albumPreviewPanel = createAlbumPreviewPanel(albumModel);

        panelAlbumModelMap.put(albumPreviewPanel, albumModel);

        albumsPanel.add(albumPreviewPanel);
        albumsPanel.add(ComponentUtility.createSeparator());

        albumsPanel.revalidate();
        albumsPanel.repaint();

        return albumPreviewPanel;
    }

    private JPanel createAlbumPreviewPanel(AlbumModel albumModel) {
        JPanel albumPreviewPanel = new JPanel();

        Dimension dimension = new Dimension(345, 60);

        albumPreviewPanel.setOpaque(false);
        albumPreviewPanel.setLayout(new BorderLayout());
        albumPreviewPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        albumPreviewPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        albumPreviewPanel.setMinimumSize(dimension);
        albumPreviewPanel.setPreferredSize(dimension);
        albumPreviewPanel.setMaximumSize(dimension);

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
            albumPreviewPanel.add(thumbnailImageLabel, BorderLayout.WEST);
        } else {
            imageReplacementPanel.add(imageReplacementLabel, BorderLayout.CENTER);
            albumPreviewPanel.add(imageReplacementPanel, BorderLayout.WEST);
        }

        albumPreviewPanel.add(albumNamePanel, BorderLayout.CENTER);
        albumPreviewPanel.add(albumNbPicturesPanel, BorderLayout.EAST);

        return albumPreviewPanel;
    }

    public GalleryModel getGalleryModel() {
        return galleryModel;
    }

    public JButton getCreateAlbumButton() {
        return createAlbumButton;
    }

    public Map<JPanel, AlbumModel> getPanelAlbumModelMap() {
        return panelAlbumModelMap;
    }
}
