package applications.Notes;

import ch.dhc.Application;

import java.awt.*;

public class Main extends Application {

    String name = "Notes";
    String iconPath = "icon\\app_icon_notes.png";

    public Main() {

    }

    @Override
    public void onRun() {
        setBackground(Color.RED);
    }

    @Override
    public void onClose() {
        System.out.println("Notes dit 'Au revoir !'");
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getIconPath() {
        return iconPath;
    }

}
