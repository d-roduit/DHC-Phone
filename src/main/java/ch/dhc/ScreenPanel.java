package ch.dhc;

import javax.swing.*;
import java.awt.*;

/**
 * <b>ScreenPanel is the class that represents the screen of the smartphone.</b>
 *
 * @see JPanel
 *
 * @author Daniel Roduit
 * @author Cathy Gay
 */
class ScreenPanel extends JPanel {

    /**
     * The state of the screen.
     *
     * <p>
     *     It can only take two values :
     *     <ul>
     *         <li>{@link State#TURNEDON}</li>
     *         <li>{@link State#TURNEDOFF}</li>
     *     </ul>
     * </p>
     *
     * @see State
     * @see State#TURNEDOFF
     * @see ScreenPanel#toggleHome()
     * @see ScreenPanel#toggleScreen()
     */
    private State state = State.TURNEDOFF;

    /**
     * The panel used to display a turned on screen.
     * <p>
     *      It is added to the ScreenPanel {@link BorderLayout#CENTER} position when the screen
     *      is turned on.
     * </p>
     *
     * @see TurnedOnScreenPanel
     * @see TurnedOnScreenPanel#TurnedOnScreenPanel(String)
     * @see ScreenPanel#turnOn()
     * @see ScreenPanel#turnOff()
     * @see ScreenPanel#getTurnedOnScreenPanel()
     */
    private final TurnedOnScreenPanel turnedOnScreenPanel = new TurnedOnScreenPanel("images/home_background.png");

    /**
     * The state the screen can be in.
     *
     * <p>
     *     The state is used to determine if the screen must be turned on
     *     or turned off when the smartphone home button is clicked.
     *</p>
     * <p>
     *     The states that can be used :
     *     <ul>
     *         <li>{@link #TURNEDON}</li>
     *         <li>{@link #TURNEDOFF}</li>
     *     </ul>
     * </p>
     *
     * @see ScreenPanel#turnOn()
     * @see ScreenPanel#turnOff()
     */
    enum State {
        TURNEDON, TURNEDOFF;
    }

    /**
     * ScreenPanel constructor.
     * <p>
     *     ScreenPanel is created with a {@link Color#BLACK} background color and a {@link BorderLayout} layout.
     *     <br>
     *     It is used to display either a black background or its {@link #turnedOnScreenPanel} property in its
     *     {@link BorderLayout#CENTER} position depending of the value of its {@link #state} property.
     * </p>
     *
     * @see SmartphoneFrame#getScreenPanel()
     */
    public ScreenPanel() {
        setBackground(Color.BLACK);
        setLayout(new BorderLayout());
    }

    /**
     * Turn on or turn off the screen depending of the {@link #state} property.
     *
     * @see State
     * @see #turnOn()
     * @see #turnOff()
     */
    public void toggleScreen() {
        switch (state) {
            case TURNEDON:
                turnOff();
                break;

            case TURNEDOFF:
                turnOn();
                break;
        }

        revalidate();
        repaint();
    }

    /**
     * Turn on the screen if the {@link #state} of the screen = {@link State#TURNEDOFF},
     * else display the home screen.
     *
     * @see #state
     * @see State
     * @see UIManager
     * @see #turnOff()
     */
    public void toggleHome() {
        switch (state) {
            case TURNEDON:
                UIManager.getInstance().displayHome();
                break;

            case TURNEDOFF:
                turnOn();
                break;
        }

        revalidate();
        repaint();
    }

    /**
     * Turn on the screen.
     *
     * @see #turnedOnScreenPanel
     * @see State
     * @see #turnOff()
     * @see #toggleScreen()
     * @see #toggleHome()
     */
    private void turnOn() {
        add(turnedOnScreenPanel, BorderLayout.CENTER);
        state = State.TURNEDON;
    }

    /**
     * Turn off the screen.
     *
     * @see #turnedOnScreenPanel
     * @see State
     * @see #turnOn()
     * @see #toggleScreen()
     */
    private void turnOff() {
        remove(turnedOnScreenPanel);
        state = State.TURNEDOFF;
    }

    /**
     * Returns the turnedOnScreen panel.
     *
     * @return The turnedOnScreen panel.
     *
     * @see #turnedOnScreenPanel
     * @see TurnedOnScreenPanel
     * @see UIManager
     */
    public TurnedOnScreenPanel getTurnedOnScreenPanel() {
        return turnedOnScreenPanel;
    }

}
