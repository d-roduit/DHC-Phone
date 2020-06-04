package applications.Photos.views;

import applications.Photos.models.AlbumModel;
import applications.Photos.models.GalleryModel;
import jiconfont.icons.font_awesome.FontAwesome;
import jiconfont.icons.google_material_design_icons.GoogleMaterialDesignIcons;
import jiconfont.swing.IconFontSwing;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

import java.util.List;

public class GalleryView extends JPanel {

    private final GalleryModel galleryModel;

    private JPanel topBarPanel;
    private JScrollPane albumsScrollPane;
    private JPanel albumsPanel;
    private JButton goBackButton;
    private JButton createAlbumButton;

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
        Color textColor = new Color(217, 169, 25);

        JPanel topBarPanel = new JPanel();
        topBarPanel.setOpaque(false);
        topBarPanel.setLayout(new BorderLayout());

        Icon goBackIcon = IconFontSwing.buildIcon(FontAwesome.ANGLE_LEFT, 24, textColor);
        goBackButton = createIconButton(goBackIcon);

        JLabel viewTitle = new JLabel(galleryModel.getGalleryTitle());
        viewTitle.setFont(new Font("Arial", Font.BOLD, 25));
        viewTitle.setHorizontalAlignment(JLabel.CENTER);
        viewTitle.setForeground(Color.WHITE);

        Icon addAlbumIcon = IconFontSwing.buildIcon(GoogleMaterialDesignIcons.ADD, 22, textColor);
        createAlbumButton = createIconButton(addAlbumIcon);

        topBarPanel.add(goBackButton, BorderLayout.WEST);
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

        albumsPanel.add(createSeparator());

        for (AlbumModel albumModel: albumModels) {
            addAlbumPreview(albumModel);
        }

        albumsScrollPane = new JScrollPane(albumsPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        albumsScrollPane.setOpaque(false);
        albumsScrollPane.getViewport().setOpaque(false);
        albumsScrollPane.setBorder(new EmptyBorder(20, 0, 0, 0));
        albumsScrollPane.getVerticalScrollBar().setUnitIncrement(10); // Change the scrolling speed

        return albumsScrollPane;
    }

    private JPanel createAlbumPreviewPanel(AlbumModel albumModel) {
        Dimension albumPreviewPanelDimension = new Dimension(345, 60);

        JPanel albumPreviewPanel = new JPanel();
        albumPreviewPanel.setOpaque(false);
        albumPreviewPanel.setLayout(new BorderLayout());
        albumPreviewPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        albumPreviewPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        albumPreviewPanel.setMinimumSize(albumPreviewPanelDimension);
        albumPreviewPanel.setPreferredSize(albumPreviewPanelDimension);
        albumPreviewPanel.setMaximumSize(albumPreviewPanelDimension);

        JPanel imageReplacementPanel = new JPanel();
        imageReplacementPanel.setOpaque(true);
        imageReplacementPanel.setBackground(Color.BLUE);
        imageReplacementPanel.setPreferredSize(new Dimension(60, 0));
        imageReplacementPanel.setMaximumSize(new Dimension(60, 0));

//        ImageLabel thumbnailImageLabel = new ImageLabel(albumModel.getThumbnail());


        JPanel albumNamePanel = new JPanel();
        albumNamePanel.setLayout(new BorderLayout());
        albumNamePanel.setOpaque(false);

        JLabel albumName = new JLabel(albumModel.getName());
        albumName.setHorizontalAlignment(SwingConstants.LEFT);

        JPanel albumNbPicturesPanel = new JPanel();
        albumNbPicturesPanel.setLayout(new BorderLayout());
        albumNbPicturesPanel.setOpaque(false);

        JLabel albumNbPictures = new JLabel(String.valueOf(albumModel.getNbPictures()));
        albumNbPictures.setHorizontalAlignment(SwingConstants.LEFT);

        albumNamePanel.add(Box.createRigidArea(new Dimension(15, 0)), BorderLayout.WEST);
        albumNamePanel.add(albumName, BorderLayout.CENTER);

        albumNbPicturesPanel.add(albumNbPictures, BorderLayout.CENTER);
        albumNbPicturesPanel.add(Box.createRigidArea(new Dimension(15, 0)), BorderLayout.EAST);

//        albumPreviewPanel.add(thumbnailImageLabel, BorderLayout.WEST);
        albumPreviewPanel.add(imageReplacementPanel, BorderLayout.WEST);
        albumPreviewPanel.add(albumNamePanel, BorderLayout.CENTER);
        albumPreviewPanel.add(albumNbPicturesPanel, BorderLayout.EAST);

        return albumPreviewPanel;
    }

    private JSeparator createSeparator() {
        // Separator properties
        Dimension separatorDimension = new Dimension(345, 2);
        Color separatorColor = new Color(49, 49, 49);

        JSeparator separator = new JSeparator();
        separator.setPreferredSize(separatorDimension);
        separator.setMaximumSize(separatorDimension);
        separator.setBackground(separatorColor);
        separator.setForeground(separatorColor);
        separator.setAlignmentX(Component.LEFT_ALIGNMENT);

        return separator;
    }

    private JButton createIconButton(Icon icon) {
        JButton button = new JButton(icon);

        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setOpaque(false);

        return button;
    }

    public void addAlbumPreview(AlbumModel albumModel) {
        albumsPanel.add(createAlbumPreviewPanel(albumModel));
        albumsPanel.add(createSeparator());

        albumsPanel.revalidate();
        albumsPanel.repaint();
    }

    public GalleryModel getGalleryModel() {
        return galleryModel;
    }

    public JButton getGoBackButton() {
        return goBackButton;
    }

    public JButton getCreateAlbumButton() {
        return createAlbumButton;
    }
}
