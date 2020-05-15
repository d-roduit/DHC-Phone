package ch.dhc;

import javax.swing.*;
import java.awt.*;

public class HomeApplication extends Application {

    public HomeApplication() {
        super();

        setOpaque(false);

        add(createAppIconsListPanel(), BorderLayout.CENTER);
        add(Box.createRigidArea(new Dimension(0, 50)), BorderLayout.SOUTH);
    }

    public JPanel createAppIconsListPanel() {
        int nbRows = 5;
        int nbColumns = 4;

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(nbRows, nbColumns));
        panel.setOpaque(false);

        panel.add(createAppIconPanel("Contacts", "src\\main\\resources\\images\\apps\\app_icon_contacts.png"));
        panel.add(createAppIconPanel("Photos", "src\\main\\resources\\images\\apps\\app_icon_photos.png"));
        panel.add(createAppIconPanel("Notes", "src\\main\\resources\\images\\apps\\app_icon_notes.png"));

        for (int i = 0; i < nbRows * nbColumns - panel.getComponentCount(); i++) {
            panel.add(createAppIconPanel("",""));
        }

        return panel;
    }

    public JPanel createAppIconPanel(String appName, String imagePath) {
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

    @Override
    public void onRun() {

    }

    @Override
    public void onClose() {

    }
}
