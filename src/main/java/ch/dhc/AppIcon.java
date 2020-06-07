package ch.dhc;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

/**
 * <b>AppIcon is the class that represents an application icon displayed on the home screen.</b>
 *
 * @see ImageLabel
 *
 * @author Daniel Roduit
 */
class AppIcon extends ImageLabel {


    /**
     * The application that will be opened when you click on this icon.
     *
     * @see Application
     * @see AppIcon#AppIcon(Application)
     */
    private final Application application;

    /**
     * AppIcon constructor
     *
     * <p>
     *     AppIcon is constructed with an Application object which is the application
     *     that will be opened when the AppIcon object is clicked.
     *     This Application object is used to get two things :
     *     <ol>
     *         <li>the application icon path, which is used to retrieve the icon image file.</li>
     *         <li>the application name, which is displayed under the application icon.</li>
     *     </ol>
     * </p>
     *
     * @param application
     *              The Application object.
     *
     * @throws IOException In case the ImageIO.read() method does not find or cannot load the application icon path.
     *
     * @see Application
     * @see Application#getFolder()
     * @see Application#getIconPath()
     * @see Application#getName()
     * @see ImageIO#read(java.io.InputStream)
     * @see Dimension
     * @see ApplicationManager#open(Application)
     */
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
