package applications.Notes;

import ch.dhc.Application;

import java.awt.*;

public class Main extends Application {

    public Main() {
        super("Notes", "icon\\app_icon_notes.png");
    }

    @Override
    public void onRun() {
        setBackground(Color.RED);
    }

    @Override
    public void onClose() {
        System.out.println("Notes dit 'Au revoir !'");
    }

}
