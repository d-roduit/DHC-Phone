package ch.dhc;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Arrays;

final class ApplicationManager {

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

    public void open(Application application) {
        if (isRunning(application)) {
            uiManager.display(application);
        } else {
            try {
                run(application);
                uiManager.display(application);
            } catch (Exception e) {
                System.out.println(e);
            }
        }

    }

    public void run(Application application) {
        application.onRun();
        uiManager.getContentPanel().add(application, String.valueOf(application.hashCode()));
        runningApplications.add(application);
    }

    public void close(Application application) {
        if (isRunning(application)) {
            try {
                application.onClose();
                uiManager.getContentPanel().remove(application);
                runningApplications.remove(application);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public void closeAllApplications() {
        for (Application application: runningApplications) {
            close(application);
        }
    }

    private boolean isRunning(Application application) {
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
                System.out.println(e);
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
            System.out.println(e);
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
