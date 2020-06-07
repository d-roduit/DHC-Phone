package applications.Photos;

import javax.swing.*;
import java.awt.*;

public class ComponentUtility {

    public static JSeparator createSeparator() {
        // Separator properties
        Dimension separatorDimension = new Dimension(345, 2);
        Color separatorColor = new Color(49, 49, 49);

        JSeparator separator = new JSeparator();
        separator.setPreferredSize(separatorDimension);
        separator.setMaximumSize(separatorDimension);
        separator.setBackground(separatorColor);
        separator.setForeground(separatorColor);
        separator.setAlignmentX(Component.LEFT_ALIGNMENT);

        return separator;
    }
}
