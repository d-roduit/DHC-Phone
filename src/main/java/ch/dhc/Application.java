package ch.dhc;

import javax.swing.*;
import java.awt.*;

public abstract class Application extends JPanel {

    private String folder;
    private final String name;
    private final String iconPath;

    private Color statusBarBackgroundColor = Color.BLACK;
    private Color statusBarForegroundColor = Color.WHITE;


    public Application(String name, String iconPath) {
        this.name = name;
        this.iconPath = iconPath;

        setLayout(new BorderLayout());
    }

    public abstract void onRun();

    public abstract void onClose();

    public String getName() {
        return name;
    };

    public String getIconPath() {
        return iconPath;
    };

    public String getFolder() {
        return folder;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }
}