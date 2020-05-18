package applications.Photos;

import ch.dhc.Application;

import java.awt.*;

public class Main extends Application {

    public Main() {
        super("Photos", "icon\\app_icon_photos.png");
    }

    @Override
    public void onRun() {
        setBackground(Color.GREEN);
    }

    @Override
    public void onClose() {
        System.out.println("Photos dit 'Au revoir !'");
    }

}
