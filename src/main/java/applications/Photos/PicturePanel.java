package applications.Photos;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PicturePanel extends JPanel {
    String picturePath;

    public PicturePanel(String picturePath) {
        this.picturePath = picturePath;

        setBackground(Color.WHITE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        File pictureFile = new File(picturePath);

        BufferedImage picture = null;

        try {
            picture = ImageIO.read(pictureFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (picture != null) {
            Dimension pictureDimension = new Dimension(picture.getWidth(), picture.getHeight());
//            Dimension boundaryDimension = new Dimension(345, 526);
            Dimension boundaryDimension = new Dimension(345, 492);
            Dimension scaledDimension = getScaledDimension(pictureDimension, boundaryDimension);

            int dx1;
            int dy1;
            int dx2;
            int dy2;

            if (boundaryDimension.width == scaledDimension.width) {
                dx1 = 0;
                dy1 = (boundaryDimension.height - scaledDimension.height) / 2;
                dx2 = scaledDimension.width;
                dy2 = dy1 + scaledDimension.height;
                System.out.println("1st with " + picturePath);
            } else if (boundaryDimension.height == scaledDimension.height) {
                dx1 = (boundaryDimension.width - scaledDimension.width) / 2;
                dy1 = 0;
                dx2 = dx1 + scaledDimension.width;
                dy2 = scaledDimension.height;
                System.out.println("2nd with " + picturePath);
            } else { // The image is smaller than the boundary.
                dx1 = (boundaryDimension.width - pictureDimension.width) / 2;
                dy1 = (boundaryDimension.height - pictureDimension.height) / 2;
                dx2 = dx1 + pictureDimension.width;
                dy2 = dy1 + pictureDimension.height;
                System.out.println("3rd with " + picturePath);
            }

            g.drawImage(
                    picture, dx1, dy1, dx2, dy2,
                    0, 0, picture.getWidth(), picture.getHeight(), Color.WHITE, null
            );
        }
    }

    public static Dimension getScaledDimension(Dimension imgSize, Dimension boundary) {
        int originalWidth = imgSize.width;
        int originalHeight = imgSize.height;
        int boundWidth = boundary.width;
        int boundHeight = boundary.height;
        int newWidth = originalWidth;
        int newHeight = originalHeight;

        // first check if we need to scale width
        if (originalWidth > boundWidth) {
            //scale width to fit
            newWidth = boundWidth;
            //scale height to maintain aspect ratio
            newHeight = (newWidth * originalHeight) / originalWidth;
        }

        // then check if we need to scale even with the new height
        if (newHeight > boundHeight) {
            //scale height to fit instead
            newHeight = boundHeight;
            //scale width to maintain aspect ratio
            newWidth = (newHeight * originalWidth) / originalHeight;
        }

        return new Dimension(newWidth, newHeight);
    }
}
