package ch.dhc;

import javax.swing.*;
import java.awt.*;

/**
 * <b>ImageLabel is the class that creates an image in a JLabel.</b>
 *
 * @see JLabel
 *
 * @author Cathy Gay
 * @author Daniel Roduit
 */
public class ImageLabel extends JLabel {

    /**
     * ImageLabel Constructor.
     *
     * @param imageData
     *          Path of the image.
     *
     * @see ImageIcon
     */
    public ImageLabel(Image imageData) {
        ImageIcon img = new ImageIcon(imageData);
        setIcon(img);
    }

}
