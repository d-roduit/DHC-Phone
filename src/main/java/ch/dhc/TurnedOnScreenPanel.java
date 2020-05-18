package ch.dhc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TurnedOnScreenPanel extends BackgroundPanel {

    public TurnedOnScreenPanel(String imagePath) {
        super(imagePath);

        add(createStatusBarPanel(), BorderLayout.NORTH);
        add(UIManager.getInstance().getContentPanel(), BorderLayout.CENTER);
    }

    public JPanel createStatusBarPanel() {
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
        networkText.setForeground(Color.white);

        ImageLabel wifiIcon = new ImageLabel("src\\main\\resources\\images\\statusBar\\wifi_icon.png");

        leftPanel.add(networkIcon);
        leftPanel.add(networkText);
        leftPanel.add(wifiIcon);

        return leftPanel;
    }

    private JPanel createCenterPanelStatusBar() {
        JPanel centerPanel = createFlowLayoutPanelStatusBar(1);

        JLabel timeLabel = new JLabel();

        DateFormat timeFormat = new SimpleDateFormat("HH:mm");
        ActionListener timerListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date date = new Date();
                String time = timeFormat.format(date);
                timeLabel.setText(time);
                timeLabel.setForeground(Color.WHITE);
            }
        };

        Timer timer = new Timer(1000, timerListener);
        timer.setInitialDelay(0);
        timer.start();

        centerPanel.add(timeLabel);

        return centerPanel;
    }

    private JPanel createRightPanelStatusBar() {
        JPanel rightPanel = createFlowLayoutPanelStatusBar(2);
        rightPanel.setPreferredSize(new Dimension(114, 0));

        JLabel batteryText = new JLabel("80%");
        batteryText.setForeground(Color.WHITE);

        ImageLabel batteryIcon = new ImageLabel("src\\main\\resources\\images\\statusBar\\battery_icon.png");

        rightPanel.add(batteryText);
        rightPanel.add(batteryIcon);

        return rightPanel;
    }

    private JPanel createFlowLayoutPanelStatusBar(int alignment) {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(alignment));
        panel.setOpaque(false);

        return panel;
    }
}


