package ch.dhc;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

class AppIcon extends ImageLabel {

    Application application;

    public AppIcon(Application application) throws IOException {
        super(ImageIO.read(AppIcon.class.getResourceAsStream("/applications/" + application.getFolder() + "/" + application.getIconPath())));

        this.application = application;

        Dimension appIconDimension = new Dimension(60, 60);

        setMinimumSize(appIconDimension);
        setPreferredSize(appIconDimension);
        setMaximumSize(appIconDimension);

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
