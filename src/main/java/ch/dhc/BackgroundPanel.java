package ch.dhc;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * <b>BackgroundPanel is a class that creates a JPanel as a background image.</b>
 *
 * @see JPanel
 *
 * @author Cathy Gay
 * @author Daniel Roduit
 */
class BackgroundPanel extends JPanel {

    /**
     * The image of BackgroundPanel.
     *
     * @see Image
     * @see BackgroundPanel#BackgroundPanel(String)
     */
    private Image img;

    /**
     * BackgroundPanel constructor.
     * <p>
     *     When BackgroundPanel is created, it has a BorderLayout by default.
     *     Reads the image.
     * </p>
     *
     * @param path
     *              The path of the image.
     *
     * @see ImageIO
     * @see IOException
     * @see BackgroundPanel#img
     */
    public BackgroundPanel(String path) {
        setLayout(new BorderLayout());

        try {
            img = ImageIO.read(BackgroundPanel.class.getResource(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Draws an image as a graphic component.
     *<p>
     *     The image will have the same size as the BackgroundPanel that contains it.
     *</p>
     *
     * @param g
     *              Graphic component.
     *
     * @see Graphics
     * @see BackgroundPanel#img
     */
    public void paintComponent(Graphics g) {
        g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
    }

}
