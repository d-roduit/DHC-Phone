package ch.dhc;

import javax.swing.*;
import java.awt.*;

public abstract class Application extends JPanel {

    public Color statusBarBackgroundColor = Color.BLACK;
    public Color statusBarForegroundColor = Color.WHITE;

    public Application() {
        setLayout(new BorderLayout());
    }

    public abstract void onRun();

    public abstract void onClose();

}
