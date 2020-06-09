package applications.Photos.views;

import applications.Photos.IconsUtility;
import applications.Photos.ThumbnailGenerator;
import applications.Photos.WrapLayout;
import applications.Photos.models.AlbumModel;
import applications.Photos.models.PictureModel;
import jiconfont.icons.google_material_design_icons.GoogleMaterialDesignIcons;
import jiconfont.swing.IconFontSwing;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class AlbumView extends JPanel {

    AlbumModel albumModel;

    private JLabel albumNameLabel;
    private final JPanel topBarPanel;
    private JScrollPane picturesScrollPane;
    private JButton goBackButton;
    private JButton addPictureButton;
    private JButton deleteAlbumButton;
    HashMap<JLabel, PictureModel> labelToModelMap = new HashMap<JLabel, PictureModel>();

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

        albumNameLabel = new JLabel(albumModel.getName());
        albumNameLabel.setFont(new Font("Arial", Font.BOLD, 25));
        albumNameLabel.setHorizontalAlignment(JLabel.CENTER);
        albumNameLabel.setForeground(Color.WHITE);

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
        topBarPanel.add(albumNameLabel, BorderLayout.CENTER);
        topBarPanel.add(albumOperationsButtonsPanel, BorderLayout.EAST);

        return topBarPanel;
    }

    private JScrollPane createPicturesScrollPane() {
        JPanel picturesPanel = new JPanel();
        picturesPanel.setOpaque(false);
        picturesPanel.setLayout(new WrapLayout(WrapLayout.LEFT, 5, 5));

        List<PictureModel> pictureModels = albumModel.getPictureModels();

        for (PictureModel pictureModel: pictureModels) {
            JLabel pictureLabel = createPictureLabel(pictureModel);

            picturesPanel.add(pictureLabel);

            labelToModelMap.put(pictureLabel, pictureModel);
        }

        JScrollPane picturesScrollPane = new JScrollPane(picturesPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        picturesScrollPane.setOpaque(false);
        picturesScrollPane.getViewport().setOpaque(false);
        picturesScrollPane.setBorder(new EmptyBorder(20, 3, 0, 0));
        picturesScrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(0,0));
        picturesScrollPane.getVerticalScrollBar().setUnitIncrement(10); // Change the scrolling speed

        return picturesScrollPane;
    }

    private JLabel createPictureLabel(PictureModel pictureModel) {
        JLabel pictureLabel = new JLabel();
        pictureLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));

        try {
            pictureLabel.setIcon(new ImageIcon(ThumbnailGenerator.generate(new File(pictureModel.getPath()), 106, 106)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return pictureLabel;
    }

    public JLabel getAlbumNameLabel() {
        return albumNameLabel;
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

    public HashMap<JLabel, PictureModel> getLabelToModelMap() {
        return labelToModelMap;
    }

}
