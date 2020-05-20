package applications.Photos;

import ch.dhc.Application;

import java.awt.*;

public class Main extends Application {

    Color backgroundColor = Color.BLACK;

    public Main() {
        super("Photos", "icon\\app_icon_photos.png");
    }

    @Override
    public void onRun() {
        setBackground(backgroundColor);
    }

    @Override
    public void onClose() {
        System.out.println("Photos dit 'Au revoir !'");
    }

    @Override
    public Color getStatusBarBackgroundColor() {
        return backgroundColor;
    }

}
