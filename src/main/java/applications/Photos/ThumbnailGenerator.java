package applications.Photos;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class ThumbnailGenerator {

    private static int thumbnailWidth = 100;
    private static int thumbnailHeight = 100;
    private static FileFormat fileFormat = FileFormat.JPG;

    public enum FileFormat {
        PNG, JPG, GIF;
    }

    public static void generate(File file, int thumbnailWidth, int thumbnailHeight, String outputPath, FileFormat fileFormat) throws Exception {
        BufferedImage img = ImageIO.read(file);

        int bufferedImageType;
        Color bgColor;

        if (fileFormat == FileFormat.PNG) {
            bufferedImageType = BufferedImage.TYPE_INT_ARGB;
            bgColor = null;
        } else {
            bufferedImageType = BufferedImage.TYPE_INT_RGB;
            bgColor = Color.WHITE;
        }

        BufferedImage thumbnail = new BufferedImage(thumbnailWidth, thumbnailHeight, bufferedImageType);

        Graphics2D g2d = (Graphics2D) thumbnail.getGraphics();

        int sx1;
        int sy1;
        int sx2;
        int sy2;

        int imgWidth = img.getWidth();
        int imgHeight = img.getHeight();

        if (imgWidth < imgHeight) {
            sx1 = 0;
            sy1 = (imgHeight - imgWidth) / 2;
            sx2 = imgWidth;
            sy2 = ((imgHeight - imgWidth) / 2) + imgWidth;
        } else if (imgHeight < imgWidth) {
            sx1 = (imgWidth - imgHeight) / 2;
            sy1 = 0;
            sx2 = ((imgWidth - imgHeight) / 2) + imgHeight;
            sy2 = imgHeight;
        } else {
            sx1 = 0;
            sy1 = 0;
            sx2 = imgWidth;
            sy2 = imgHeight;
        }

        g2d.drawImage(
                img, 0, 0, thumbnail.getWidth(), thumbnail.getHeight(),
                sx1, sy1, sx2, sy2, bgColor, null
        );

        g2d.dispose();

        String outputPathWithExtension = outputPath + "." + fileFormat.name().toLowerCase();

        ImageIO.write(thumbnail, fileFormat.name(), new File(outputPathWithExtension));
    }

    public static void generate(File file, String outputPath, FileFormat fileFormat) throws Exception {
        generate(file, thumbnailWidth, thumbnailHeight, outputPath, fileFormat);
    }

    public static void generate(File file, String outputPath) throws Exception {
        generate(file, thumbnailWidth, thumbnailHeight, outputPath, fileFormat);
    }

    public static void setThumbnailWidth(int width) {
        thumbnailWidth = width;
    }

    public static void setThumbnailHeight(int height) {
        thumbnailHeight = height;
    }

    public static void setFileFormat(FileFormat format) {
        fileFormat = format;
    }
}
