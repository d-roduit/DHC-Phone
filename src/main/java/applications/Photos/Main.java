package applications.Photos;

import ch.dhc.Application;

import java.awt.*;

public class Main extends Application {

    String name = "Photos";
    String iconPath = "icon/app_icon_photos.png";

    Color backgroundColor = Color.BLACK;

    public Main() {
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
    public String getName() {
        return name;
    }

    @Override
    public String getIconPath() {
        return iconPath;
    }

    @Override
    public Color getStatusBarBackgroundColor() {
        return backgroundColor;
    }

}
