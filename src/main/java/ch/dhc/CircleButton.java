package ch.dhc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CircleButton extends JButton {

    private int diameter;
    private Color buttonColor;

    public CircleButton(int diameter, Color buttonColor) {
        this.diameter = diameter;
        this.buttonColor = buttonColor;

        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setOpaque(false);
        setFocusPainted(false);
        setBorderPainted(false);
    }

    public CircleButton(int diameter) {
        this(diameter, new Color(0, 0, 0, 0));
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(diameter, diameter);
    }

    @Override
    public void paintComponent(Graphics g) {
        g.setColor(buttonColor);
        g.fillOval(0,0, diameter, diameter);
    }
}
