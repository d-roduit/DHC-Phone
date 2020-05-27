package ch.dhc;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * <b>HomePanel is the class that represents the smartphone home screen.</b>
 *
 * @see JPanel
 *
 * @author Cathy Gay
 * @author Daniel Roduit
 */
class HomePanel extends JPanel {

    /**
     * Final name of HomePanel.
     *
     * @see HomePanel#getName()
     */
    private final String name = "Home";

    /**
     * HomePanel constructor.
     * <p>
     *     HomePanel is created transparent with a top and bottom margin.
     * </p>
     *
     * @see HomePanel#createAppIconsListPanel()
     */
    public HomePanel() {
        setOpaque(false);

        add(createAppIconsListPanel());
        setBorder(new EmptyBorder(20, 0, 50, 0));
    }

    /**
     * Returns a JPanel that represents the list of applications icons.
     * <p>
     *     Displays the installed applications in a GridLayout.
     * </p>
     *
     * @return The applications icons list JPanel.
     *
     * @see JPanel
     * @see Application
     * @see HomePanel#createAppIconPanel(Application)
     * @see ApplicationManager#getInstalledApplications()
     * @see HomePanel#createEmptyPanel()
     */
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

    /**
     * Returns a JPanel that represents the application icon.
     * <p>
     *     Creates JPanel with the image icon of the application and its name below.
     * </p>
     *
     * @param application
     *              An Application.
     *
     * @return The application icon JPanel.
     *
     * @see JPanel
     * @see AppIcon
     * @see Application
     * @see Application#getName()
     */
    private JPanel createAppIconPanel(Application application) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setOpaque(false);

        AppIcon appIconLabel = null;

        try {
            appIconLabel = new AppIcon(application);
        } catch (Exception e) {
            System.out.println(e);
        }

        JLabel appTextLabel = new JLabel(application.getName());
        appTextLabel.setForeground(Color.white);
        appTextLabel.setHorizontalAlignment(JLabel.CENTER);

        panel.add(appIconLabel, BorderLayout.CENTER);
        panel.add(appTextLabel, BorderLayout.SOUTH);

        return panel;
    }

    /**
     * Returns an empty JPanel.
     *
     * @return An empty JPanel.
     *
     * @see JPanel
     * @see HomePanel#createAppIconsListPanel()
     */
    private JPanel createEmptyPanel() {
        JPanel panel = new JPanel();
        panel.setOpaque(false);

        return panel;
    }

    /**
     * Returns the name of HomePanel.
     *
     * @return The name of HomePanel.
     *
     * @see HomePanel#name
     */
    public String getName() {
        return name;
    }
}
