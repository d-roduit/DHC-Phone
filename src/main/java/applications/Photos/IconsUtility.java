package applications.Photos;

import javax.swing.*;
import java.awt.*;

public class IconsUtility {

    public static Color iconsColor = new Color(217, 169, 25);

    public static JButton createIconButton(Icon icon) {
        JButton button = new JButton(icon);

        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setOpaque(false);

        return button;
    }

}
