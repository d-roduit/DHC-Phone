package ch.dhc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SmartphoneFrame extends JFrame {

    private final BackgroundPanel smartphoneBackgroundPanel = new BackgroundPanel("src\\main\\resources\\images\\smartphone.png");
    private final ScreenPanel screenPanel = new ScreenPanel();
    private ApplicationManager applicationManager;

    public SmartphoneFrame() {
        setSize(400, 795);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setBackground(new Color(0, 0, 0, 0));

        smartphoneBackgroundPanel.add(createDragPanel(), BorderLayout.NORTH);

        smartphoneBackgroundPanel.add(screenPanel, BorderLayout.CENTER);
        smartphoneBackgroundPanel.add(createHomeButtonPanel(), BorderLayout.SOUTH);
        smartphoneBackgroundPanel.add(Box.createRigidArea(new Dimension(28, 0)), BorderLayout.WEST);
        smartphoneBackgroundPanel.add(createLockPanel(), BorderLayout.EAST);

        setContentPane(smartphoneBackgroundPanel);
    }

    public JPanel createLockPanel() {
        JPanel lockPanel = new JPanel();
        lockPanel.setLayout(new BorderLayout());
        lockPanel.setOpaque(false);

        lockPanel.add(Box.createRigidArea(new Dimension(0, 61)), BorderLayout.NORTH);
        lockPanel.add(Box.createRigidArea(new Dimension(0, 502)), BorderLayout.SOUTH);
        lockPanel.add(createLockButton(), BorderLayout.CENTER);

        return lockPanel;
    }


    public JButton createLockButton() {
        JButton lockButton = new JButton();
        lockButton.setPreferredSize(new Dimension(27, 0));
        lockButton.setContentAreaFilled(false);
        lockButton.setBorderPainted(false);
        lockButton.setFocusPainted(false);
        lockButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        lockButton.addActionListener(actionEvent -> screenPanel.toggleScreen());

//         TODO: Ajouter le long pressed
//        lockButton.addActionListener(actionEvent -> turnOff());

        return lockButton;
    }


    public JPanel createHomeButtonPanel() {
        JPanel homeButtonPanel = new JPanel();

        CircleButton circleButton = new CircleButton(57);
        circleButton.addActionListener(actionEvent -> screenPanel.toggleHome());

        GridBagConstraints gbc = new GridBagConstraints();
        homeButtonPanel.add(circleButton, gbc);

        homeButtonPanel.setLayout(new GridBagLayout());
        homeButtonPanel.setOpaque(false);
        homeButtonPanel.setPreferredSize(new Dimension(0, 90));

        return homeButtonPanel;
    }

    public JPanel createDragPanel() {
        DragPanel dragPanel = new DragPanel(this);
        dragPanel.setPreferredSize(new Dimension(0, 89));
        dragPanel.setOpaque(false);

        return dragPanel;
    }

    private void turnOff() {
        this.dispose();
    }

}
