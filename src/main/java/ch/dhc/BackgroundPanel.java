package ch.dhc;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class BackgroundPanel extends JPanel {

    private Image img;

    public BackgroundPanel(String path) {
        setLayout(new BorderLayout());

        try {
            img = ImageIO.read(new File(path));
        } catch (IOException e) {
            System.out.println(e.getMessage()+"\n"+ Arrays.toString(e.getStackTrace()));
        }
    }

    public void paintComponent(Graphics g) {
        g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
    }

}
