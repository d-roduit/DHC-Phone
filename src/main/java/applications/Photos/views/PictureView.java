package applications.Photos.views;

import applications.Photos.IconsUtility;
import applications.Photos.PicturePanel;
import applications.Photos.controllers.AlbumController;
import applications.Photos.models.PictureModel;
import jiconfont.icons.google_material_design_icons.GoogleMaterialDesignIcons;
import jiconfont.swing.IconFontSwing;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class PictureView extends JPanel {

    PictureModel pictureModel;
    AlbumController.MoveDirection moveDirection;

    private final JPanel topBarPanel;
    private final JPanel picturePanel;
    private final JPanel bottomBarPanel;
    private JButton goBackButton;
    private JButton deletePictureButton;
    private JButton previousPictureButton;
    private JButton nextPictureButton;


    public PictureView(PictureModel pictureModel, AlbumController.MoveDirection moveDirection) {
        this.pictureModel = pictureModel;
        this.moveDirection = moveDirection;

        setLayout(new BorderLayout());
        setOpaque(false);

        topBarPanel = createTopBarPanel();
        picturePanel = createPicturePanel();
        bottomBarPanel = createBottomBarPanel();

        add(topBarPanel, BorderLayout.NORTH);
        add(picturePanel, BorderLayout.CENTER);
        add(bottomBarPanel, BorderLayout.SOUTH);
    }

    private JPanel createTopBarPanel() {
        JPanel topBarPanel = new JPanel();
        topBarPanel.setOpaque(false);
        topBarPanel.setLayout(new BorderLayout());
        topBarPanel.setBorder(new EmptyBorder(0, 2, 0, 2));

        JLabel viewTitle = new JLabel(pictureModel.getName());
        viewTitle.setFont(new Font("Arial", Font.BOLD, 15));
        viewTitle.setHorizontalAlignment(JLabel.CENTER);
        viewTitle.setForeground(Color.WHITE);

        Icon goBackIcon = IconFontSwing.buildIcon(GoogleMaterialDesignIcons.KEYBOARD_ARROW_LEFT, 29, IconsUtility.iconsColor);
        goBackButton = IconsUtility.createIconButton(goBackIcon);

        Icon deletePictureIcon = IconFontSwing.buildIcon(GoogleMaterialDesignIcons.DELETE, 22, IconsUtility.iconsColor);
        deletePictureButton = IconsUtility.createIconButton(deletePictureIcon);

        topBarPanel.add(goBackButton, BorderLayout.WEST);
        topBarPanel.add(viewTitle, BorderLayout.CENTER);
        topBarPanel.add(deletePictureButton, BorderLayout.EAST);

        return topBarPanel;
    }

    private JPanel createPicturePanel() {
        return new PicturePanel(pictureModel.getPath());
    }

    private JPanel createBottomBarPanel() {
        JPanel bottomBarPanel = new JPanel();
        bottomBarPanel.setOpaque(false);
        bottomBarPanel.setLayout(new BorderLayout());
        bottomBarPanel.setBorder(new EmptyBorder(0, 100, 0, 100));

        Icon previousPictureIcon = IconFontSwing.buildIcon(GoogleMaterialDesignIcons.KEYBOARD_ARROW_LEFT, 29, IconsUtility.iconsColor);
        previousPictureButton = IconsUtility.createIconButton(previousPictureIcon);

        Icon nextPictureIcon = IconFontSwing.buildIcon(GoogleMaterialDesignIcons.KEYBOARD_ARROW_RIGHT, 29, IconsUtility.iconsColor);
        nextPictureButton = IconsUtility.createIconButton(nextPictureIcon);

        switch (moveDirection) {
            case PREVIOUS_NEXT:
                bottomBarPanel.add(previousPictureButton, BorderLayout.WEST);
                bottomBarPanel.add(nextPictureButton, BorderLayout.EAST);
                break;
            case NONE:
                bottomBarPanel.add(Box.createRigidArea(new Dimension(30, 40)), BorderLayout.WEST);
                bottomBarPanel.add(Box.createRigidArea(new Dimension(30, 40)), BorderLayout.EAST);
                break;
            case NEXT_ONLY:
                bottomBarPanel.add(Box.createRigidArea(new Dimension(30, 0)), BorderLayout.WEST);
                bottomBarPanel.add(nextPictureButton, BorderLayout.EAST);
                break;
            case PREVIOUS_ONLY:
                bottomBarPanel.add(previousPictureButton, BorderLayout.WEST);
                bottomBarPanel.add(Box.createRigidArea(new Dimension(30, 0)), BorderLayout.EAST);
                break;
        }
        
        return bottomBarPanel;
    }

    public JButton getGoBackButton() {
        return goBackButton;
    }

    public JButton getDeletePictureButton() {
        return deletePictureButton;
    }

    public JButton getPreviousPictureButton() {
        return previousPictureButton;
    }

    public JButton getNextPictureButton() {
        return nextPictureButton;
    }

}
