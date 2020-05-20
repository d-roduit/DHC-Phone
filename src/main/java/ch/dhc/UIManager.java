package ch.dhc;

import javax.swing.*;
import java.awt.*;

public final class UIManager {

    private static UIManager instance;

    private SmartphoneFrame smartphoneFrame;
    private TurnedOnScreenPanel turnedOnScreenPanel;
    private CardLayout cardLayout;
    private JPanel statusBarPanel;
    private JPanel contentPanel;
    private HomePanel homePanel;


    private UIManager() {}

    public static UIManager getInstance() {
        if (instance == null) {
            instance = new UIManager();
        }

        return instance;
    }

    public void display(Application application) {
        statusBarPanel.setOpaque(true);
        statusBarPanel.setBackground(application.getStatusBarBackgroundColor());
        statusBarPanel.revalidate();
        statusBarPanel.repaint();
        cardLayout.show(contentPanel, String.valueOf(application.hashCode()));
    }

    public void displayHome() {
        statusBarPanel.setOpaque(false);
        cardLayout.show(contentPanel, homePanel.getName());
    }

    public void setSmartphoneFrame(SmartphoneFrame smartphoneFrame) {
        this.smartphoneFrame = smartphoneFrame;

        TurnedOnScreenPanel turnedOnScreenPanel = smartphoneFrame.getScreenPanel().getTurnedOnScreenPanel();

        setTurnedOnScreenPanel(turnedOnScreenPanel);
        setCardLayout(turnedOnScreenPanel.getCardLayout());
        setStatusBarPanel(turnedOnScreenPanel.getStatusBarPanel());
        setContentPanel(turnedOnScreenPanel.getContentPanel());
        setHomePanel(turnedOnScreenPanel.getHomePanel());
    }

    public void setTurnedOnScreenPanel(TurnedOnScreenPanel turnedOnScreenPanel) {
        this.turnedOnScreenPanel = turnedOnScreenPanel;
    }

    public void setCardLayout(CardLayout cardLayout) {
        this.cardLayout = cardLayout;
    }

    public void setStatusBarPanel(JPanel statusBarPanel) {
        this.statusBarPanel = statusBarPanel;
    }

    public JPanel getContentPanel() {
        return contentPanel;
    }

    public void setContentPanel(JPanel contentPanel) {
        this.contentPanel = contentPanel;
    }

    public void setHomePanel(HomePanel homePanel) {
        this.homePanel = homePanel;
    }
}