package ch.dhc;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class AppIcon extends ImageLabel {

    Application application;

    public AppIcon(Application application) {
        super(Configuration.getInstance().getApplicationsDirectoryPath() + application.getFolder() + "\\" + application.getIconPath());

        this.application = application;

        setCursor(new Cursor(Cursor.HAND_CURSOR));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                System.out.println("application " + application.getName() + " cliquée");

                ApplicationManager.getInstance().open(application);
            }
        });
    }
}
