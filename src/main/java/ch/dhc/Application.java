package ch.dhc;

import javax.swing.*;
import java.awt.*;

public abstract class Application extends JPanel {

    private String folder;
    private String name;
    private String iconPath;
    private Color statusBarBackgroundColor = Color.BLACK;
    private Color statusBarForegroundColor = Color.WHITE;

    public Application() {
        setLayout(new BorderLayout());
    }

    public abstract void onRun();

    public abstract void onClose();

    public abstract String getName();

    public abstract String getIconPath();

    public Color getStatusBarBackgroundColor() {
        return statusBarBackgroundColor;
    };

    public Color getStatusBarForegroundColor() {
        return statusBarForegroundColor;
    }


    public String getFolder() {
        return folder;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }

}