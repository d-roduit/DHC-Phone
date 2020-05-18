package ch.dhc;

import javax.swing.*;

public class ImageLabel extends JLabel {

    public ImageLabel(String imagePath) {
        ImageIcon img = new ImageIcon(imagePath);
        setIcon(img);

        // TODO: Mettre une taille maximale pour l'affichage de l'icône (60px x 60px comme Cathy)
    }

}
