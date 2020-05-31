package ch.dhc;

import com.formdev.flatlaf.FlatDarculaLaf;
import jiconfont.icons.font_awesome.FontAwesome;
import jiconfont.icons.google_material_design_icons.GoogleMaterialDesignIcons;
import jiconfont.swing.IconFontSwing;

import java.awt.*;

public class Main {

    public static void main(String[] args) {

        // FlatLaf Darcula Look and Feel
        FlatDarculaLaf.install();

        // Change the default Look and Feel font
        javax.swing.UIManager.put("defaultFont", new Font("Dialog", Font.BOLD, 12));

        // Register the FontAwesome and Google Material Design Icons to be able to use them everywhere
        // (in the smartphone or in the applications)
        IconFontSwing.register(FontAwesome.getIconFont());
        IconFontSwing.register(GoogleMaterialDesignIcons.getIconFont());

            Configuration.getInstance();

        SmartphoneFrame smartphoneFrame = new SmartphoneFrame();

        UIManager uiManager = UIManager.getInstance();
        uiManager.setSmartphoneFrame(smartphoneFrame);

        ApplicationManager applicationManager = ApplicationManager.getInstance();
        applicationManager.setUiManager(uiManager);

        smartphoneFrame.setVisible(true);
    }

}
