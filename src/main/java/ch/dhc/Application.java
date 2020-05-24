package ch.dhc;

import javax.swing.*;
import java.awt.*;

/**
 * <b>Application is the class that represents applications.</b>
 *
 * @see JPanel
 *
 * @author Daniel Roduit
 * @author Cathy Gay
 */
public abstract class Application extends JPanel {

    /**
     * Folder of Application.
     *
     * @see Application#getFolder()
     * @see Application#setFolder(String)
     */
    private String folder;

    /**
     * Background color of the Application status bar.
     *
     * @see Color
     * @see Application#getStatusBarBackgroundColor()
     */
    private Color statusBarBackgroundColor = Color.BLACK;

    /**
     * Text color of the Application status bar.
     *
     * @see Color
     * @see Application#getStatusBarForegroundColor()
     */
    private Color statusBarForegroundColor = Color.WHITE;

    /**
     * Application constructor.
     *<p>
     *     Application is created with a BorderLayout by default.
     *</p>
     */
    public Application() {
        setLayout(new BorderLayout());
    }

    /**
     * What will do the Application when it's running.
     */
    public abstract void onRun();

    /**
     * What will do the Application when it's closed.
     */
    public abstract void onClose();

    /**
     * Returns the name of the Application.
     *
     * @return The name of the Application.
     */
    public abstract String getName();

    /**
     * Returns the icon path of the Application.
     *
     * @return The icon path of the Application.
     */
    public abstract String getIconPath();

    /**
     * Returns the background color of the Application status bar.
     *
     * @return The background color of the Application status bar.
     */
    public Color getStatusBarBackgroundColor() {
        return statusBarBackgroundColor;
    };

    /**
     * Returns the text color of the Application status bar.
     *
     * @return The text color of the Application status bar.
     */
    public Color getStatusBarForegroundColor() {
        return statusBarForegroundColor;
    }

    /**
     * Returns the folder of the Application.
     *
     * @return The folder of the Application.
     */
    public String getFolder() {
        return folder;
    }

    /**
     * Set the folder of the Application.
     *
     * @param folder
     *              The folder of the Application.
     */
    public void setFolder(String folder) {
        this.folder = folder;
    }

}