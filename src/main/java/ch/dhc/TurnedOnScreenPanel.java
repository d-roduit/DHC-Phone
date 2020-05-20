package ch.dhc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TurnedOnScreenPanel extends BackgroundPanel {

    private Color textColor = Color.WHITE;
    private final CardLayout cardLayout;
    private final JPanel statusBarPanel;
    private final JPanel contentPanel;
    private final HomePanel homePanel;

    public TurnedOnScreenPanel(String imagePath) {
        super(imagePath);

        cardLayout = new CardLayout();
        statusBarPanel = createStatusBarPanel();
        homePanel = new HomePanel();
        contentPanel = createContentPanel(homePanel);

        add(statusBarPanel, BorderLayout.NORTH);
        add(contentPanel, BorderLayout.CENTER);
    }

    private JPanel createContentPanel(HomePanel homePanel) {
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(cardLayout);
        contentPanel.setOpaque(false);

        contentPanel.add(homePanel, homePanel.getName());

        return contentPanel;
    }

    private JPanel createStatusBarPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setOpaque(false);

        JPanel leftPanel = createLeftPanelStatusBar();

        JPanel centerPanel = createCenterPanelStatusBar();

        JPanel rightPanel = createRightPanelStatusBar();

        panel.add(leftPanel, BorderLayout.WEST);
        panel.add(centerPanel, BorderLayout.CENTER);
        panel.add(rightPanel, BorderLayout.EAST);

        return panel;
    }

    private JPanel createLeftPanelStatusBar() {
        JPanel leftPanel = createFlowLayoutPanelStatusBar(FlowLayout.LEFT);

        ImageLabel networkIcon = new ImageLabel("src\\main\\resources\\images\\statusBar\\network_icon.png");

        JLabel networkText = new JLabel("DHCnet");
        networkText.setForeground(textColor);

        ImageLabel wifiIcon = new ImageLabel("src\\main\\resources\\images\\statusBar\\wifi_icon.png");

        leftPanel.add(networkIcon);
        leftPanel.add(networkText);
        leftPanel.add(wifiIcon);

        return leftPanel;
    }

    private JPanel createCenterPanelStatusBar() {
        JPanel centerPanel = createFlowLayoutPanelStatusBar(FlowLayout.CENTER);

        JLabel timeLabel = new JLabel();

        DateFormat timeFormat = new SimpleDateFormat("HH:mm");
        ActionListener timerListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date date = new Date();
                String time = timeFormat.format(date);
                timeLabel.setText(time);
                timeLabel.setForeground(textColor);
            }
        };

        Timer timer = new Timer(1000, timerListener);
        timer.setInitialDelay(0);
        timer.start();

        centerPanel.add(timeLabel);

        return centerPanel;
    }

    private JPanel createRightPanelStatusBar() {
        JPanel rightPanel = createFlowLayoutPanelStatusBar(FlowLayout.RIGHT);
        rightPanel.setPreferredSize(new Dimension(114, 0));

        JLabel batteryText = new JLabel("80%");
        batteryText.setForeground(textColor);

        ImageLabel batteryIcon = new ImageLabel("src\\main\\resources\\images\\statusBar\\battery_icon.png");

        rightPanel.add(batteryText);
        rightPanel.add(batteryIcon);

        return rightPanel;
    }

    private JPanel createFlowLayoutPanelStatusBar(int flowLayoutAlignment) {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(flowLayoutAlignment));
        panel.setOpaque(false);

        return panel;
    }

    public Color getTextColor() {
        return textColor;
    }

    public void setTextColor(Color textColor) {
        this.textColor = textColor;
    }

    public CardLayout getCardLayout() {
        return cardLayout;
    }

    public JPanel getStatusBarPanel() {
        return statusBarPanel;
    }

    public JPanel getContentPanel() {
        return contentPanel;
    }

    public HomePanel getHomePanel() {
        return homePanel;
    }
}


