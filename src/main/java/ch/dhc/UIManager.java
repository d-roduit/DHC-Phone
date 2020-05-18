package ch.dhc;

import javax.swing.*;
import java.awt.*;

public class UIManager {

    public static UIManager instance;
    HomePanel homePanel = new HomePanel();
    CardLayout cardLayout = new CardLayout();
    JPanel contentPanel = createContentPanel();

    private UIManager() {

    }

    public static UIManager getInstance() {
        if (instance == null) {
            instance = new UIManager();
        }
        return instance;
    }

    private JPanel createContentPanel() {
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(cardLayout);
        contentPanel.setOpaque(false);

        contentPanel.add(homePanel, homePanel.getName());

        return contentPanel;
    }

    public void displayHome() {
        cardLayout.show(contentPanel, homePanel.getName());
    }

    public JPanel getContentPanel() {
        return contentPanel;
    }
}