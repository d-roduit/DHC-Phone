package ch.dhc;

import java.util.ArrayList;
import java.util.Arrays;

public final class ApplicationManager {

    private static ApplicationManager instance;

    private final ArrayList<Application> runningApplications = new ArrayList<Application>();


    private ApplicationManager() {}

    public static ApplicationManager getInstance() {
        if (instance == null) {
            instance = new ApplicationManager();
        }
        return instance;
    }

    public void run(Application application) {
        if (isRunning(application)) {
            display(application);
        } else {
            try {
                // TODO: Placer le panel de l'application dans le BorderLayout.CENTER du screenPanel
                // TODO: Mettre le backgroundColor et foregroundColor de la statusBar aux propriétés définies dans l'app.
                application.onRun();
                runningApplications.add(application);
            } catch (Exception e) {
                System.out.println(e.getMessage() + "\n" + Arrays.toString(e.getStackTrace()));
            }
        }

    }

    public void close(Application application) {
        if (isRunning(application)) {
            try {
                application.onClose();
                runningApplications.remove(application);
            } catch (Exception e) {
                System.out.println(e.getMessage() + "\n" + Arrays.toString(e.getStackTrace()));
            }
        }
    }

    public void closeAllApplications() {
        for (Application application: runningApplications) {
            close(application);
        }
    }

    public void display(Application application) {
        //TODO: Display l'application
    }

    public void displayHomeScreen() {
        //TODO: display Home Screen
        //TODO: Peut-être fermer les autres applications ? Ou alors simplement afficher le homeScreen
    }

    public boolean isRunning(Application application) {
        return runningApplications.contains(application);
    }

}
