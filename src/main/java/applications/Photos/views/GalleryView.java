package applications.Photos.views;

import applications.Photos.AlbumPreviewPanel;
import applications.Photos.ComponentUtility;
import applications.Photos.IconsUtility;
import applications.Photos.models.AlbumModel;
import applications.Photos.models.GalleryModel;
import jiconfont.icons.google_material_design_icons.GoogleMaterialDesignIcons;
import jiconfont.swing.IconFontSwing;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GalleryView extends JPanel {

    private final GalleryModel galleryModel;

    private JPanel topBarPanel;
    private JScrollPane albumsScrollPane;
    private JPanel albumsPanel;
    private JButton createAlbumButton;
    private List<AlbumPreviewPanel> albumPreviewPanelList = new ArrayList<AlbumPreviewPanel>();

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

    public AlbumPreviewPanel addAlbumPreview(AlbumModel albumModel) {
        AlbumPreviewPanel albumPreviewPanel = new AlbumPreviewPanel(albumModel);

        albumPreviewPanelList.add(albumPreviewPanel);

        albumsPanel.add(albumPreviewPanel);
        albumsPanel.add(ComponentUtility.createSeparator());

        albumsPanel.revalidate();
        albumsPanel.repaint();

        return albumPreviewPanel;
    }

    public GalleryModel getGalleryModel() {
        return galleryModel;
    }

    public JButton getCreateAlbumButton() {
        return createAlbumButton;
    }

    public List<AlbumPreviewPanel> getAlbumPreviewPanelList() {
        return albumPreviewPanelList;
    }
}
