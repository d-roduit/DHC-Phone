package ch.dhc;

import javax.swing.*;
import java.awt.*;

public class ScreenPanel extends JPanel {

    private State state = State.TURNEDOFF;

    private final TurnedOnScreenPanel turnedOnScreenPanel = new TurnedOnScreenPanel("src\\main\\resources\\images\\home_background.png");

    enum State {
        TURNEDON, TURNEDOFF;
    }

    public ScreenPanel() {
        setBackground(Color.BLACK);
        setLayout(new BorderLayout());
    }

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


    private void turnOn() {
        add(turnedOnScreenPanel, BorderLayout.CENTER);
        state = State.TURNEDON;
    }

    private void turnOff() {
        remove(turnedOnScreenPanel);
        state = State.TURNEDOFF;
    }

}