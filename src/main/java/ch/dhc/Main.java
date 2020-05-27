package ch.dhc;

import com.formdev.flatlaf.FlatDarculaLaf;

import java.awt.*;

public class Main {

    public static void main(String[] args) {

        FlatDarculaLaf.install();

        javax.swing.UIManager.put("defaultFont", new Font("Dialog", Font.BOLD, 12));

        SmartphoneFrame smartphoneFrame = new SmartphoneFrame();

        UIManager uiManager = UIManager.getInstance();
        uiManager.setSmartphoneFrame(smartphoneFrame);

        ApplicationManager applicationManager = ApplicationManager.getInstance();
        applicationManager.setUiManager(uiManager);

        smartphoneFrame.setVisible(true);
    }

}
