package ch.dhc;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <b>TurnedOnScreenPanel is the class that is used to display a turned on screen.</b>
 *
 * <p>
 *     It contains two panels used to display content :
 *     <ul>
 *         <li>
 *             the status bar panel, which is displayed at the top of the smartphone
 *             and contains all the status icons
 *         </li>
 *         <li>
 *             the screen content, which takes all the remaining space of the screen to display
 *             the useful content (for example the applications)
 *         </li>
 *     </ul>
 * </p>
 *
 *
 * @author Daniel Roduit
 * @author Cathy Gay
 */
class TurnedOnScreenPanel extends BackgroundPanel {

    /**
     * The text color used by labels inside the status bar.
     * 
     * @see Color
     * @see #getStatusBarTextColor()
     * @see #setStatusBarTextColor(Color)
     * @see #createLeftPanelStatusBar()
     * @see #createCenterPanelStatusBar()
     * @see #createRightPanelStatusBar()
     */
    private Color statusBarTextColor = Color.WHITE;

    /**
     * The cardLayout layout used to change what is displayed in the content panel.
     *
     * @see CardLayout
     * @see #getCardLayout()
     * @see #TurnedOnScreenPanel(String)
     * @see #createContentPanel(HomePanel)
     */
    private final CardLayout cardLayout;

    /**
     * The status bar panel that displays smartphone status information.
     *
     * @see JPanel
     */
    private final JPanel statusBarPanel;

    /**
     * The content panel that displays content on the screen.
     *
     * @see JPanel
     */
    private final JPanel contentPanel;

    /**
     * The home panel that contains the applications icons.
     *
     * <p>
     *     It is used as the default content for the {@link #contentPanel}.
     * </p>
     *
     * @see HomePanel
     */
    private final HomePanel homePanel;

    /**
     * TurnedOnScreenPanel constructor.
     *
     * @param wallPaperImagePath
     *              The path of the image which will be displayed as the smartphone screen wallpaper.
     *
     * @see BackgroundPanel
     * @see CardLayout
     * @see #createStatusBarPanel()
     * @see HomePanel
     * @see #createContentPanel(HomePanel)
     */
    public TurnedOnScreenPanel(String wallPaperImagePath) {
        super(wallPaperImagePath);

        cardLayout = new CardLayout();
        statusBarPanel = createStatusBarPanel();
        homePanel = new HomePanel();
        contentPanel = createContentPanel(homePanel);

        add(statusBarPanel, BorderLayout.NORTH);
        add(contentPanel, BorderLayout.CENTER);
    }

    /**
     * Creates the panel that will take the applications content in it.
     *
     * @param homePanel
     *              The home panel containing the applications icons.
     *
     * @return The content JPanel.
     *
     * @see JPanel
     * @see HomePanel
     * @see #homePanel
     * @see #getHomePanel()
     * @see #getContentPanel()
     */
    private JPanel createContentPanel(HomePanel homePanel) {
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(cardLayout);
        contentPanel.setOpaque(false);

        contentPanel.add(homePanel, homePanel.getName());

        return contentPanel;
    }

    /**
     * Creates the status bar panel that will take the smartphone status information in it.
     *
     * @return The status bar JPanel.
     *
     * @see JPanel
     * @see #createLeftPanelStatusBar()
     * @see #createCenterPanelStatusBar()
     * @see #createRightPanelStatusBar()
     */
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

    /**
     * Creates a sub-panel for status bar panel that contains three components :
     *
     * <ul>
     *     <li>the network icon</li>
     *     <li>the network name</li>
     *     <li>the wifi icon</li>
     * </ul>
     *
     * @return The sub-panel for status bar JPanel.
     *
     * @see JPanel
     * @see #createFlowLayoutPanelStatusBar(int)
     * @see ImageLabel
     * @see ImageIO#read(URL)
     *
     */
    private JPanel createLeftPanelStatusBar() {
        JPanel leftPanel = createFlowLayoutPanelStatusBar(FlowLayout.LEFT);

        // Network Icon
        ImageLabel networkIcon = null;

        try {
            String networkIconPath = "images/statusBar/network_icon.png";
            networkIcon = new ImageLabel(ImageIO.read(TurnedOnScreenPanel.class.getResourceAsStream(networkIconPath)));
        } catch (Exception e) {
            e.printStackTrace();
        }

        JLabel networkText = new JLabel("DHCnet");
        networkText.setForeground(statusBarTextColor);

        ImageLabel wifiIcon = null;


        // Wifi Icon
        try {
            String wifiIconPath = "images/statusBar/wifi_icon.png";
            wifiIcon = new ImageLabel(ImageIO.read(TurnedOnScreenPanel.class.getResourceAsStream(wifiIconPath)));
        } catch (Exception e) {
            e.printStackTrace();
        }

        leftPanel.add(networkIcon);
        leftPanel.add(networkText);
        leftPanel.add(wifiIcon);

        return leftPanel;
    }

    /**
     * Creates a sub-panel for status bar panel that contains one component :
     *
     * <ul>
     *     <li>the clock</li>
     * </ul>
     *
     * @return The sub-panel for status bar JPanel.
     *
     * @see JPanel
     * @see #createFlowLayoutPanelStatusBar(int)
     * @see DateFormat
     * @see SimpleDateFormat
     * @see java.util.Timer
     */
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
                timeLabel.setForeground(statusBarTextColor);
            }
        };

        Timer timer = new Timer(1000, timerListener);
        timer.setInitialDelay(0);
        timer.start();

        centerPanel.add(timeLabel);

        return centerPanel;
    }

    /**
     * Creates a sub-panel for status bar panel that contains two components :
     *
     * <ul>
     *     <li>the battery percentage</li>
     *     <li>the battery icon</li>
     * </ul>
     *
     * @return The sub-panel for status bar JPanel.
     *
     * @see JPanel
     * @see #createFlowLayoutPanelStatusBar(int)
     * @see ImageLabel
     * @see ImageIO#read(URL)
     *
     */
    private JPanel createRightPanelStatusBar() {
        JPanel rightPanel = createFlowLayoutPanelStatusBar(FlowLayout.RIGHT);
        rightPanel.setPreferredSize(new Dimension(114, 0));

        JLabel batteryText = new JLabel("80%");
        batteryText.setForeground(statusBarTextColor);

        ImageLabel batteryIcon = null;

        // Battery Icon
        try {
            String batteryIconPath = "images/statusBar/battery_icon.png";
            batteryIcon = new ImageLabel(ImageIO.read(TurnedOnScreenPanel.class.getResourceAsStream(batteryIconPath)));
        } catch (Exception e) {
            e.printStackTrace();
        }

        rightPanel.add(batteryText);
        rightPanel.add(batteryIcon);

        return rightPanel;
    }

    /**
     * Creates a generic empty FlowLayout JPanel
     *
     * @return The FlowLayout JPanel.
     *
     * @see JPanel
     */
    private JPanel createFlowLayoutPanelStatusBar(int flowLayoutAlignment) {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(flowLayoutAlignment));
        panel.setOpaque(false);

        return panel;
    }

    /**
     * Returns the status bar text color
     *
     * @return The status bar text color.
     *
     * @see Color
     */
    public Color getStatusBarTextColor() {
        return statusBarTextColor;
    }

    /**
     * Sets the status bar text color.
     *
     * @param statusBarTextColor
     *              A color object
     *
     * @see Color
     */
    public void setStatusBarTextColor(Color statusBarTextColor) {
        this.statusBarTextColor = statusBarTextColor;
    }

    /**
     * Returns the cardLayout.
     *
     * @return The cardLayout.
     *
     * @see CardLayout
     */
    public CardLayout getCardLayout() {
        return cardLayout;
    }

    /**
     * Returns the statusBarPanel.
     *
     * @return The statusBarPanel.
     *
     * @see JPanel
     */
    public JPanel getStatusBarPanel() {
        return statusBarPanel;
    }

    /**
     * Returns the contentPanel.
     *
     * @return The contentPanel.
     *
     * @see JPanel
     */
    public JPanel getContentPanel() {
        return contentPanel;
    }

    /**
     * Returns the homePanel.
     *
     * @return The homePanel.
     *
     * @see HomePanel
     */
    public HomePanel getHomePanel() {
        return homePanel;
    }
}


