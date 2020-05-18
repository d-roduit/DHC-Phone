package ch.dhc;

import javax.swing.*;
import java.awt.*;

public class HomePanel extends JPanel {

    private final String name = "Home";

    public HomePanel() {
        setOpaque(false);

        add(createAppIconsListPanel(), BorderLayout.CENTER);
        add(Box.createRigidArea(new Dimension(0, 50)), BorderLayout.SOUTH);
    }

    private JPanel createAppIconsListPanel() {
        int nbRows = 5;
        int nbColumns = 4;

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(nbRows, nbColumns));
        panel.setOpaque(false);

        Application[] installedApplications = ApplicationManager.getInstance().getInstalledApplications();

        for (Application application: installedApplications) {
            panel.add(createAppIconPanel(application));
        }

        int nbApp = panel.getComponentCount();

        for (int i = 0; i < nbRows * nbColumns - nbApp; i++) {
            panel.add(createAppIconPanel("",""));
        }

        return panel;
    }

    private JPanel createAppIconPanel(String appName, String imagePath) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setOpaque(false);

        ImageLabel appIconLabel = new ImageLabel(imagePath);

        JLabel appTextLabel = new JLabel(appName);
        appTextLabel.setForeground(Color.white);
        appTextLabel.setHorizontalAlignment(JLabel.CENTER);

        panel.add(Box.createRigidArea(new Dimension(0, 20)), BorderLayout.NORTH);
        panel.add(Box.createRigidArea(new Dimension(10, 0)), BorderLayout.EAST);
        panel.add(Box.createRigidArea(new Dimension(10, 0)), BorderLayout.WEST);
        panel.add(appIconLabel, BorderLayout.CENTER);
        panel.add(appTextLabel, BorderLayout.SOUTH);

        return panel;
    }

    public String getName() {
        return name;
    }
}
