package applications.Contacts;

import ch.dhc.Application;

import java.awt.*;

public class Main extends Application {

    public Main() {
        super("Contacts", "icon\\app_icon_contacts.png");
    }

    @Override
    public void onRun() {
        setBackground(Color.PINK);
    }

    @Override
    public void onClose() {
        System.out.println("Contacts dit 'Au revoir !'");
    }

}
