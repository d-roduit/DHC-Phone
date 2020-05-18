package ch.dhc;

import javax.swing.*;
import java.awt.*;

public class TurnedOnScreenPanel extends BackgroundPanel {

    public TurnedOnScreenPanel(String imagePath) {
        super(imagePath);

        add(createStatusBarPanel(), BorderLayout.NORTH);
        add(UIManager.getInstance().getContentPanel(), BorderLayout.CENTER);
    }

    public JPanel createStatusBarPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.setOpaque(false);

        ImageLabel networkIcon = new ImageLabel("src\\main\\resources\\images\\statusBar\\network_icon.png");

        JLabel networkText = new JLabel("DHCnet");
        networkText.setForeground(Color.white);

        ImageLabel wifiIcon = new ImageLabel("src\\main\\resources\\images\\statusBar\\wifi_icon.png");

        panel.add(networkIcon);
        panel.add(networkText);
        panel.add(wifiIcon);

        return panel;
    }
}


