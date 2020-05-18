package ch.dhc;


import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class AppIcon extends ImageLabel {

    Application application;

    public AppIcon(Application application) {
        super(Configuration.getInstance().getApplicationsDirectoryPath() + application.getFolder() + "\\" + application.getIconPath());

        this.application = application;

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                System.out.println("application " + application.getName() + " cliquée");

                ApplicationManager.getInstance().run(application);
            }
        });
    }
}
