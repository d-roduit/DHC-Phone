package ch.dhc;

import javax.swing.*;
import java.awt.*;

public final class UIManager {

    private static UIManager instance;

    private SmartphoneFrame smartphoneFrame;
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
        cardLayout.show(contentPanel, application.getName());
    }

    public void displayHome() {
        cardLayout.show(contentPanel, homePanel.getName());
    }

    public void setSmartphoneFrame(SmartphoneFrame smartphoneFrame) {
        this.smartphoneFrame = smartphoneFrame;

        TurnedOnScreenPanel turnedOnScreenPanel = smartphoneFrame.getScreenPanel().getTurnedOnScreenPanel();

        setCardLayout(turnedOnScreenPanel.getCardLayout());
        setStatusBarPanel(turnedOnScreenPanel.getStatusBarPanel());
        setContentPanel(turnedOnScreenPanel.getContentPanel());
        setHomePanel(turnedOnScreenPanel.getHomePanel());
    }

    public CardLayout getCardLayout() {
        return cardLayout;
    }

    public void setCardLayout(CardLayout cardLayout) {
        this.cardLayout = cardLayout;
    }

    public JPanel getStatusBarPanel() {
        return statusBarPanel;
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

    public HomePanel getHomePanel() {
        return homePanel;
    }

    public void setHomePanel(HomePanel homePanel) {
        this.homePanel = homePanel;
    }
}