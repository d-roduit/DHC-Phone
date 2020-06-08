package applications.Photos.views;

import applications.Photos.ComponentUtility;
import applications.Photos.IconsUtility;
import applications.Photos.ThumbnailGenerator;
import applications.Photos.models.AlbumModel;
import applications.Photos.models.PictureModel;
import ch.dhc.Configuration;
import jiconfont.icons.google_material_design_icons.GoogleMaterialDesignIcons;
import jiconfont.swing.IconFontSwing;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImagingOpException;
import java.io.File;
import java.io.IOException;
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
        picturesPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
        picturesPanel.setPreferredSize(new Dimension(345, 500));

        List<PictureModel> pictureModels = albumModel.getPictureModels();

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

    private void addPictureLabel(PictureModel pictureModel) {
        JLabel pictureLabel = createPictureLabel(pictureModel);

        labelToModelMap.put(pictureLabel, pictureModel);

        picturesPanel.add(pictureLabel);

//        picturesPanel.revalidate();
//        picturesPanel.repaint();
    }

    private JLabel createPictureLabel(PictureModel pictureModel) {
        JLabel pictureLabel = new JLabel();

        try {
            pictureLabel.setIcon(new ImageIcon(ThumbnailGenerator.generate(new File(pictureModel.getPath()), 108, 108)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return pictureLabel;
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
