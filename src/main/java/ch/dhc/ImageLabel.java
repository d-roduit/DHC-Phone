package ch.dhc;

import javax.swing.*;

public class ImageLabel extends JLabel {

    public ImageLabel(String imagePath) {
        ImageIcon img = new ImageIcon(imagePath);
        setIcon(img);
    }

}
