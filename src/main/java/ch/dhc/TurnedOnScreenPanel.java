package ch.dhc;

import javax.swing.*;
import java.awt.*;

public class TurnedOnScreenPanel extends BackgroundPanel {

    public TurnedOnScreenPanel(String imagePath) {
        super(imagePath);

        add(createStatusBarPanel(), BorderLayout.NORTH);
        add(createContentPanel(), BorderLayout.CENTER);
    }

    public JPanel createStatusBarPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.setOpaque(false);

        ImageLabel networkIcon = new ImageLabel("src\\main\\resources\\images\\statusBar\\network_icon.png");

        ImageLabel networkText = new ImageLabel("DHCnet");
        networkText.setForeground(Color.white);

        ImageLabel wifiIcon = new ImageLabel("src\\main\\resources\\images\\statusBar\\wifi_icon.png");

        panel.add(networkIcon);
        panel.add(networkText);
        panel.add(wifiIcon);

        return panel;
    }

    public JPanel createContentPanel() {
        JPanel panel = new JPanel();

        panel.setLayout(new CardLayout());

        return panel;
    }

}


