package ch.dhc;

import applications.Photos.IconsUtility;
import jiconfont.icons.google_material_design_icons.GoogleMaterialDesignIcons;
import jiconfont.swing.IconFontSwing;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.List;

public class ApplicationManagerPanel extends JPanel {

    public ApplicationManagerPanel() {
        setOpaque(false);
        setLayout(new BorderLayout());

        Dimension heightDimension = new Dimension(0, 50);

        add(Box.createRigidArea(heightDimension), BorderLayout.NORTH);
        add(Box.createRigidArea(heightDimension), BorderLayout.SOUTH);
        add(createAppIconsListScrollPane(), BorderLayout.CENTER);
    }

    private JScrollPane createAppIconsListScrollPane() {
        JPanel appIconsListPanel = new JPanel();
        appIconsListPanel.setOpaque(false);
        appIconsListPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0));
        appIconsListPanel.setBorder(new EmptyBorder(0,0,0,30));

        List<Application> runningApplications = ApplicationManager.getInstance().getRunningApplications();

        for (Application application: runningApplications) {
            if (application != null) {
                appIconsListPanel.add(createApplicationPanel(application));
            }
        }

        JScrollPane scrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.getViewport().setView(appIconsListPanel);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getHorizontalScrollBar().setUnitIncrement(10);
        scrollPane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

        return scrollPane;
    }

    private JPanel createApplicationPanel(Application application) {
        JPanel applicationPanel = new JPanel();
        applicationPanel.setOpaque(false);
        applicationPanel.setLayout(new BorderLayout());

        JPanel applicationInfoPanel = new JPanel();
        applicationInfoPanel.setOpaque(false);
        applicationInfoPanel.setLayout(new BorderLayout());

        JLabel applicationNameLabel = new JLabel(application.getName());
        applicationNameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        applicationNameLabel.setForeground(Color.WHITE);

        JLabel screenshotLabel = createScreenshotLabel(application);

        screenshotLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        screenshotLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                ApplicationManager.getInstance().open(application);
            }
        });

        Icon closeApplicationIcon = IconFontSwing.buildIcon(GoogleMaterialDesignIcons.CANCEL, 35, Color.WHITE);
        JButton closeApplicationButton = IconsUtility.createIconButton(closeApplicationIcon);
        closeApplicationButton.setHorizontalAlignment(SwingConstants.CENTER);

        // Add the listener to call the close() method when the button is clicked.
        addListener(closeApplicationButton, application);

        applicationInfoPanel.add(applicationNameLabel, BorderLayout.NORTH);
        applicationInfoPanel.add(Box.createRigidArea(new Dimension(0, 5)), BorderLayout.SOUTH);

        applicationPanel.add(applicationInfoPanel, BorderLayout.NORTH);
        applicationPanel.add(screenshotLabel, BorderLayout.CENTER);
        applicationPanel.add(closeApplicationButton, BorderLayout.SOUTH);

        return applicationPanel;
    }

    private JLabel createScreenshotLabel(Application application) {
        BufferedImage screenshotImage = getScreenShot(application);
        Image screenshotImageResized = screenshotImage.getScaledInstance(265, 400, Image.SCALE_SMOOTH);

        JLabel screenshotImageLabel = new JLabel();
        screenshotImageLabel.setIcon(new ImageIcon(screenshotImageResized));

        return screenshotImageLabel;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        int width = getWidth();
        int height = getHeight();

        Color color1 = new Color(28, 38, 74);
        Color color2 = new Color(68, 138, 223);

        GradientPaint gp = new GradientPaint(0, 0, color1, 0, height, color2);
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, width, height);
    }

    private void addListener(JButton button, Application application) {
        button.addActionListener(e -> {
            ApplicationManager.getInstance().close(application);

            UIManager.getInstance().getContentPanel().remove(this);

            if (ApplicationManager.getInstance().hasRunningApplications()) {
                UIManager.getInstance().display(new ApplicationManagerPanel());
            } else {
                UIManager.getInstance().displayHome();
            }
        });
    }

    public static BufferedImage getScreenShot(Component component) {
        BufferedImage image = new BufferedImage(
            component.getWidth(),
            component.getHeight(),
            BufferedImage.TYPE_INT_RGB
        );

        component.paint(image.getGraphics());

        return image;
    }
}
