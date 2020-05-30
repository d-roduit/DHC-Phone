package ch.dhc;

import javax.swing.*;
import java.awt.*;


/**
 * <b>UIManager is the class that manages all the graphic operations made on the smartphone.</b>
 *
 * @author Daniel Roduit
 */
final class UIManager {

    /**
     * The instance of a UIManager object.
     *
     * @see #getInstance()
     */
    private static UIManager instance;

    /**
     * The smartphone frame.
     * <p>
     *     It is a reference to the program frame that has the smartphone image as background.
     * </p>
     *
     * @see SmartphoneFrame
     * @see #setSmartphoneFrame(SmartphoneFrame)
     */
    private SmartphoneFrame smartphoneFrame;

    /**
     * The turnedOnScreen panel.
     * <p>
     *     It is a reference to the turnedOnScreen panel that contains the element displayed
     *     when the screen is turned on.
     * </p>
     *
     * @see TurnedOnScreenPanel
     * @see #setTurnedOnScreenPanel(TurnedOnScreenPanel)
     */
    private TurnedOnScreenPanel turnedOnScreenPanel;

    /**
     * The cardLayout layout.
     * <p>
     *     It is a reference to the cardLayout layout that of the turnedOnScreen object.
     *     Is is used to display a specific panel to the user, for example an application.
     * </p>
     *
     * @see CardLayout
     * @see #setCardLayout(CardLayout)
     */
    private CardLayout cardLayout;

    /**
     * The statusBar panel.
     * <p>
     *     It is a reference to the statusBar panel of the turnedOnScreen object.
     * </p>
     *
     * @see JPanel
     * @see #setStatusBarPanel(JPanel)
     */
    private JPanel statusBarPanel;

    /**
     * The content panel.
     * <p>
     *     It is a reference to the content panel of the turnedOnScreen object.
     * </p>
     *
     * @see JPanel
     * @see #getContentPanel()
     * @see #setContentPanel(JPanel)
     */
    private JPanel contentPanel;

    /**
     * The home panel.
     * <p>
     *     It is a reference to the home panel of the turnedOnScreen object.
     * </p>
     *
     * @see HomePanel
     * @see #setHomePanel(HomePanel)
     */
    private HomePanel homePanel;

    /**
     * UIManager private constructor.
     *
     * <p>
     *     It delegates the object instantiation to the {@link #getInstance()} method.
     * </p>
     *
     * @see #getInstance()
     */
    private UIManager() {}


    /**
     * UIManager singleton constructor.
     *
     * <p>
     *     It ensures that the UIManager class will only be instantiated once.
     * </p>
     *
     * @return the UIManager object.
     *
     * @see #UIManager()
     */
    public static UIManager getInstance() {
        if (instance == null) {
            instance = new UIManager();
        }

        return instance;
    }

    /**
     * Displays an application in the {@link #contentPanel} using the {@link #cardLayout}.
     *
     * <p>
     *     For the UIManager to be able to display an application, the application must first
     *     have been added previously to the {@link #contentPanel} using {@link ApplicationManager#open(Application)}.
     * </p>
     *
     * @param application The application whose panel will be displayed.
     *
     * @see Application
     * @see #cardLayout
     * @see CardLayout#show(Container, String)
     */
    public void display(Application application) {
        statusBarPanel.setOpaque(true);
        statusBarPanel.setBackground(application.getStatusBarBackgroundColor());
        statusBarPanel.revalidate();
        statusBarPanel.repaint();
        cardLayout.show(contentPanel, String.valueOf(application.hashCode()));
    }
    
    /**
     * Displays the home panel in the {@link #contentPanel} using the {@link #cardLayout}.
     *
     * @see #homePanel
     * @see #statusBarPanel
     * @see #cardLayout
     * @see CardLayout#show(Container, String)
     */
    public void displayHome() {
        statusBarPanel.setOpaque(false);
        cardLayout.show(contentPanel, homePanel.getName());
    }

    /**
     * Sets the {@link #smartphoneFrame} property as well as all the other properties of the class.
     *
     * <p>
     *     It uses the other setters to set their property value using the objects
     *     that {@link #smartphoneFrame} contains.
     *     See section <code>@see</code> to see the underlying setters used by this method.
     * </p>
     *
     * @see SmartphoneFrame
     * @see SmartphoneFrame#getScreenPanel()
     * @see TurnedOnScreenPanel
     * @see ScreenPanel#getTurnedOnScreenPanel()
     * @see #setTurnedOnScreenPanel(TurnedOnScreenPanel)
     * @see #setCardLayout(CardLayout)
     * @see #setStatusBarPanel(JPanel)
     * @see #setContentPanel(JPanel)
     * @see #setHomePanel(HomePanel)
     */
    public void setSmartphoneFrame(SmartphoneFrame smartphoneFrame) {
        this.smartphoneFrame = smartphoneFrame;

        TurnedOnScreenPanel turnedOnScreenPanel = smartphoneFrame.getScreenPanel().getTurnedOnScreenPanel();

        setTurnedOnScreenPanel(turnedOnScreenPanel);
        setCardLayout(turnedOnScreenPanel.getCardLayout());
        setStatusBarPanel(turnedOnScreenPanel.getStatusBarPanel());
        setContentPanel(turnedOnScreenPanel.getContentPanel());
        setHomePanel(turnedOnScreenPanel.getHomePanel());
    }

    public void setTurnedOnScreenPanel(TurnedOnScreenPanel turnedOnScreenPanel) {
        this.turnedOnScreenPanel = turnedOnScreenPanel;
    }

    public void setCardLayout(CardLayout cardLayout) {
        this.cardLayout = cardLayout;
    }

    public void setStatusBarPanel(JPanel statusBarPanel) {
        this.statusBarPanel = statusBarPanel;
    }

    public JPanel getContentPanel() {
        return contentPanel;
    }

    public void setContentPanel(JPanel contentPanel) {
        this.contentPanel = contentPanel;
    }

    public void setHomePanel(HomePanel homePanel) {
        this.homePanel = homePanel;
    }
}