package ch.dhc;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

class HomePanel extends JPanel {

    private final String name = "Home";

    public HomePanel() {
        setOpaque(false);

        add(createAppIconsListPanel(), BorderLayout.CENTER);
        setBorder(new EmptyBorder(20, 0, 50, 0));
    }

    private JPanel createAppIconsListPanel() {
        int nbRows = 5;
        int nbColumns = 4;

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(nbRows, nbColumns, 20, 20));
        panel.setOpaque(false);

        Application[] installedApplications = ApplicationManager.getInstance().getInstalledApplications();

        for (Application application: installedApplications) {
            panel.add(createAppIconPanel(application));
        }

        int nbApp = panel.getComponentCount();

        for (int i = 0; i < nbRows * nbColumns - nbApp; i++) {
            panel.add(createEmptyPanel());
        }

        return panel;
    }

    private JPanel createAppIconPanel(Application application) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setOpaque(false);

        AppIcon appIconLabel = new AppIcon(application);

        JLabel appTextLabel = new JLabel(application.getName());
        appTextLabel.setForeground(Color.white);
        appTextLabel.setHorizontalAlignment(JLabel.CENTER);

        panel.add(appIconLabel, BorderLayout.CENTER);
        panel.add(appTextLabel, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createEmptyPanel() {
        JPanel panel = new JPanel();
        panel.setOpaque(false);

        return panel;
    }

    public String getName() {
        return name;
    }
}
