package ch.dhc;

public class Main {

    public static void main(String[] args) {
        SmartphoneFrame smartphoneFrame = new SmartphoneFrame();

        UIManager uiManager = UIManager.getInstance();
        uiManager.setSmartphoneFrame(smartphoneFrame);

        ApplicationManager applicationManager = ApplicationManager.getInstance();
        applicationManager.setUiManager(uiManager);

        smartphoneFrame.setVisible(true);
    }

}
