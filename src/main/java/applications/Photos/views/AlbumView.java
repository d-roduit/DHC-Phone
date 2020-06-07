package applications.Photos.views;

import applications.Photos.ComponentUtility;
import applications.Photos.IconsUtility;
import applications.Photos.models.AlbumModel;
import applications.Photos.models.PictureModel;
import jiconfont.icons.google_material_design_icons.GoogleMaterialDesignIcons;
import jiconfont.swing.IconFontSwing;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.HashMap;
import java.util.List;

public class AlbumView extends JPanel {

    AlbumModel albumModel;

    private final JPanel topBarPanel;
    private JScrollPane picturesScrollPane;
    private JPanel picturesPanel;
    private JButton goBackButton;
    private JButton addPictureButton;
    private JButton deleteAlbumButton;
    HashMap<JPanel, PictureModel> pictureLabelPanelMap = new HashMap<JPanel, PictureModel>();

    public AlbumView(AlbumModel albumModel) {
        this.albumModel = albumModel;

        setLayout(new BorderLayout());
        setOpaque(false);

        topBarPanel = createTopBarPanel();
        picturesScrollPane = createPicturesScrollPane();

        add(topBarPanel, BorderLayout.NORTH);
        add(picturesScrollPane, BorderLayout.CENTER);
    }

    private JPanel createTopBarPanel() {
        JPanel topBarPanel = new JPanel();
        topBarPanel.setOpaque(false);
        topBarPanel.setLayout(new BorderLayout());
        topBarPanel.setBorder(new EmptyBorder(0, 2, 0, 2));

        JLabel viewTitle = new JLabel(albumModel.getName());
        viewTitle.setFont(new Font("Arial", Font.BOLD, 25));
        viewTitle.setHorizontalAlignment(JLabel.CENTER);
        viewTitle.setForeground(Color.WHITE);

        Icon goBackIcon = IconFontSwing.buildIcon(GoogleMaterialDesignIcons.KEYBOARD_ARROW_LEFT, 29, IconsUtility.iconsColor);
        goBackButton = IconsUtility.createIconButton(goBackIcon);

        JPanel albumOperationsButtonsPanel = new JPanel();
        albumOperationsButtonsPanel.setLayout(new BorderLayout());
        albumOperationsButtonsPanel.setOpaque(false);

        Icon addPictureIcon = IconFontSwing.buildIcon(GoogleMaterialDesignIcons.ADD, 24, IconsUtility.iconsColor);
        addPictureButton = IconsUtility.createIconButton(addPictureIcon);

        Icon deleteAlbumIcon = IconFontSwing.buildIcon(GoogleMaterialDesignIcons.DELETE, 22, IconsUtility.iconsColor);
        deleteAlbumButton = IconsUtility.createIconButton(deleteAlbumIcon);

        albumOperationsButtonsPanel.add(addPictureButton, BorderLayout.WEST);
        albumOperationsButtonsPanel.add(deleteAlbumButton, BorderLayout.EAST);

        topBarPanel.add(goBackButton, BorderLayout.WEST);
        topBarPanel.add(viewTitle, BorderLayout.CENTER);
        topBarPanel.add(albumOperationsButtonsPanel, BorderLayout.EAST);

        return topBarPanel;
    }

    private JScrollPane createPicturesScrollPane() {
        picturesPanel = new JPanel();
        picturesPanel.setOpaque(false);
        picturesPanel.setLayout(new BoxLayout(picturesPanel, BoxLayout.Y_AXIS));
        picturesPanel.setPreferredSize(null);

        List<PictureModel> pictureModels = albumModel.getPictureModels();

        picturesPanel.add(ComponentUtility.createSeparator());

        for (PictureModel pictureModel: pictureModels) {
            addPictureLabel(pictureModel);
        }

        JScrollPane picturesScrollPane = new JScrollPane(picturesPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        picturesScrollPane.setOpaque(false);
        picturesScrollPane.getViewport().setOpaque(false);
        picturesScrollPane.setBorder(new EmptyBorder(20, 0, 0, 0));
        picturesScrollPane.getVerticalScrollBar().setUnitIncrement(10); // Change the scrolling speed

        return picturesScrollPane;
    }

    public void addPictureLabel(PictureModel pictureModel) {
        JPanel pictureLabelPanel = createPictureLabelPanel(pictureModel);

        pictureLabelPanelMap.put(pictureLabelPanel, pictureModel);

        picturesPanel.add(pictureLabelPanel);
        picturesPanel.add(ComponentUtility.createSeparator());

        picturesPanel.revalidate();
        picturesPanel.repaint();
    }

    private JPanel createPictureLabelPanel(PictureModel pictureModel) {
        Dimension dimension = new Dimension(345, 60);

        JPanel pictureLabelPanel = new JPanel();
        pictureLabelPanel.setOpaque(false);
        pictureLabelPanel.setLayout(new BorderLayout());
        pictureLabelPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        pictureLabelPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        pictureLabelPanel.setMinimumSize(dimension);
        pictureLabelPanel.setPreferredSize(dimension);
        pictureLabelPanel.setMaximumSize(dimension);
        pictureLabelPanel.setBorder(new EmptyBorder(0, 20, 0, 15));

        JLabel pictureLabel = new JLabel(pictureModel.getName());

        Icon goToImageIcon = IconFontSwing.buildIcon(GoogleMaterialDesignIcons.KEYBOARD_ARROW_RIGHT, 22, IconsUtility.iconsColor);
        JLabel goToImageIconLabel = new JLabel(goToImageIcon);

        pictureLabelPanel.add(pictureLabel, BorderLayout.WEST);
        pictureLabelPanel.add(goToImageIconLabel, BorderLayout.EAST);

        return pictureLabelPanel;
    }

    public AlbumModel getAlbumModel() {
        return albumModel;
    }

    public JButton getGoBackButton() {
        return goBackButton;
    }

    public JButton getAddPictureButton() {
        return addPictureButton;
    }

    public JButton getDeleteAlbumButton() {
        return deleteAlbumButton;
    }

    public HashMap<JPanel, PictureModel> getPictureLabelPanelMap() {
        return pictureLabelPanelMap;
    }
}
