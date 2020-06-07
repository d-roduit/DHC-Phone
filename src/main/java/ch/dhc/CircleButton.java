package ch.dhc;

import javax.swing.*;
import java.awt.*;

/**
 * <b>CircleButton is the class that represents a button as a circle.</b>
 *
 * @see JButton
 *
 * @author Daniel Roduit
 */
class CircleButton extends JButton {

    /**
     * Diameter of CircleButton.
     *
     * @see CircleButton#CircleButton(int, Color)
     * @see CircleButton#CircleButton(int)
     */
    private int diameter;

    /**
     * Color of CircleButton.
     *
     * @see Color
     * @see CircleButton#CircleButton(int, Color)
     */
    private Color buttonColor;

    /**
     * CircleButton constructor.
     *
     * <p>
     *     When CircleButton is created, the default decoration of the button is removed
     *     and the default cursor is changed to a hand cursor.
     * </p>
     *
     * @param diameter
     *              The diameter of CircleButton.
     * @param buttonColor
     *              The color of CircleButton.
     *
     * @see CircleButton#diameter
     * @see CircleButton#buttonColor
     */
    public CircleButton(int diameter, Color buttonColor) {
        this.diameter = diameter;
        this.buttonColor = buttonColor;

        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setOpaque(false);
        setFocusPainted(false);
        setBorderPainted(false);
    }

    /**
     * CircleButton constructor.
     * <p>
     *     CircleButton is created with a default color (transparent).
     * </p>
     *
     * @param diameter
     *              The diameter of CircleButton.
     *
     * @see CircleButton#diameter
     * @see CircleButton#CircleButton(int, Color)
     */
    public CircleButton(int diameter) {
        this(diameter, new Color(0, 0, 0, 0));
    }

    /**
     * Returns the preferred dimension of CircleButton.
     *
     * @return The dimension of CircleButton, with his diameter as width and height.
     *
     * @see Dimension
     * @see CircleButton#diameter
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(diameter, diameter);
    }

    /**
     * Draws a circle graphic component.
     *<p>
     *     The circle is drawn with the color and the dimension of CircleButton.
     *</p>
     * @param g
     *              Graphic component.
     *
     * @see Graphics
     * @see CircleButton#buttonColor
     * @see CircleButton#diameter
     */
    @Override
    public void paintComponent(Graphics g) {
        g.setColor(buttonColor);
        g.fillOval(0,0, diameter, diameter);
    }
}
