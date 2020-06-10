package applications.Photos;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ThumbnailGenerator {

    private static int thumbnailWidth = 155;
    private static int thumbnailHeight = 155;
    private static FileFormat fileFormat = FileFormat.JPG;
    private static boolean appendFileFormat = false;

    public enum FileFormat {
        PNG, JPG, GIF;
    }

    public static void generate(File file, int thumbnailWidth, int thumbnailHeight, String outputPath, FileFormat fileFormat, boolean appendFileFormat) throws Exception {
        BufferedImage thumbnail = (BufferedImage) generate(file, thumbnailWidth, thumbnailHeight);

        String outputFilePath;

        if (appendFileFormat) {
            outputFilePath = outputPath + "." + fileFormat.name().toLowerCase();
        } else {
            outputFilePath = outputPath;
        }

        ImageIO.write(thumbnail, fileFormat.name(), new File(outputFilePath));
    }

    public static void generate(File file, String outputPath, FileFormat fileFormat, boolean appendFileFormat) throws Exception {
        generate(file, thumbnailWidth, thumbnailHeight, outputPath, fileFormat, appendFileFormat);
    }

    public static void generate(File file, String outputPath, FileFormat fileFormat) throws Exception {
        generate(file, thumbnailWidth, thumbnailHeight, outputPath, fileFormat, appendFileFormat);
    }

    public static void generate(File file, String outputPath) throws Exception {
        generate(file, thumbnailWidth, thumbnailHeight, outputPath, fileFormat, appendFileFormat);
    }

    public static Image generate(BufferedImage bufferedImage, int thumbnailWidth, int thumbnailHeight) throws IOException {
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

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        int sx1;
        int sy1;
        int sx2;
        int sy2;

        int imgWidth = bufferedImage.getWidth();
        int imgHeight = bufferedImage.getHeight();

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
                bufferedImage, 0, 0, thumbnail.getWidth(), thumbnail.getHeight(),
                sx1, sy1, sx2, sy2, bgColor, null
        );

        g2d.dispose();

        return thumbnail;
    }

    public static Image generate(File file, int thumbnailWidth, int thumbnailHeight) throws IOException {
        BufferedImage bufferedImage = ImageIO.read(file);

        return generate(bufferedImage, thumbnailWidth, thumbnailHeight);
    }

    public static Image generate(String filePath, int thumbnailWidth, int thumbnailHeight) throws IOException {
        File imageFile = new File(filePath);

        BufferedImage bufferedImage = ImageIO.read(imageFile);

        return generate(bufferedImage, thumbnailWidth, thumbnailHeight);
    }

    public static Dimension getThumbnailDimension() {
        return new Dimension(thumbnailWidth, thumbnailHeight);
    }

    public static int getThumbnailWidth() {
        return thumbnailWidth;
    }

    public static int getThumbnailHeight() {
        return thumbnailHeight;
    }

    public static FileFormat getFileFormat() {
        return fileFormat;
    }

    public static boolean hasAppendFileFormat() {
        return appendFileFormat;
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

    public static void setAppendFileFormat(boolean appendFileFormat) {
        ThumbnailGenerator.appendFileFormat = appendFileFormat;
    }
}
