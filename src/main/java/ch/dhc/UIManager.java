package ch.dhc;

import javax.swing.*;
import java.awt.*;

public class UIManager {

    public static UIManager instance;
    private final HomePanel homePanel = new HomePanel();
    private final CardLayout cardLayout = new CardLayout();
    private final JPanel contentPanel = createContentPanel();

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

    public CardLayout getCardLayout() {
        return cardLayout;
    }
}