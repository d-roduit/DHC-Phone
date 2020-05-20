package ch.dhc;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Arrays;

public final class ApplicationManager {

    private static ApplicationManager instance;

    private UIManager uiManager;
    private final Application[] installedApplications = fetchInstalledApplications();
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
                // TODO: Mettre le backgroundColor et foregroundColor de la statusBar aux propriétés définies dans l'app.
                application.onRun();
                uiManager.getContentPanel().add(application, application.getName());
                display(application);
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
                //TODO: REMOVE DU CONTENTPANEL
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
        uiManager.display(application);
    }

    public void displayHomeScreen() {
        //TODO: display Home Screen
        //TODO: Peut-être fermer les autres applications ? Ou alors simplement afficher le homeScreen
    }

    public boolean isRunning(Application application) {
        return runningApplications.contains(application);
    }

    private Application[] fetchInstalledApplications() {
        File[] foldersInDirectory = getFoldersInDirectory(Configuration.getInstance().getApplicationsDirectoryPath());

        int nbApplications = foldersInDirectory.length;

        Application[] applicationList = new Application[nbApplications];

        for (int i = 0; i < nbApplications; i++) {
            try {
                File folder = foldersInDirectory[i];

                String fullyQualifiedClassName = "applications." + folder.getName() + ".Main";

                Class cls = Class.forName(fullyQualifiedClassName);
                Application application = (Application) cls.getDeclaredConstructor().newInstance();
                application.setFolder(folder.getName());

                applicationList[i] = application;
            } catch (Exception e) {
                System.out.println(e.getMessage() + "\n" + Arrays.toString(e.getStackTrace()));
            }
        }

        return applicationList;
    }

    private File[] getFoldersInDirectory(String directoryPath) {
        try {
            File file = new File(directoryPath);
            File[] files = file.listFiles(new FileFilter() {
                @Override
                public boolean accept(File f) {
                    return f.isDirectory();
                }
            });

            return files;
        } catch (Exception e) {
            System.out.println(e.getMessage() + "\n" + Arrays.toString(e.getStackTrace()));
            return null;
        }
    }

    public Application[] getInstalledApplications() {
        return installedApplications;
    }

    public void setUiManager(UIManager uiManager) {
        this.uiManager = uiManager;
    }

}
