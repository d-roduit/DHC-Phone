package ch.dhc;

import javax.swing.*;

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
     * @param imagePath
     *          Path of the image.
     *
     * @see ImageIcon
     */
    public ImageLabel(String imagePath) {
        ImageIcon img = new ImageIcon(imagePath);
        setIcon(img);

        // TODO: Mettre une taille maximale pour l'affichage de l'icône (60px x 60px comme Cathy)
    }

}
