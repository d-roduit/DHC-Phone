package ch.dhc;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * <b>ApplicationManager is the class that manages all operations that can be performed on applications.</b>
 *
 * @author Daniel Roduit
 */
final class ApplicationManager {

    /**
     * The instance of a ApplicationManager object.
     *
     * @see #getInstance()
     */
    private static ApplicationManager instance;

    /**
     * The uiManager.
     * <p>
     *     It is used to perform graphic operations regarding the applications.
     * </p>
     *
     * @see UIManager
     * @see #setUiManager(UIManager)
     */
    private UIManager uiManager;

    /**
     * The applications installed on the smartphone.
     * <p>
     *     The applications defined as "installed" are the ones found in the folder specified
     *     by {@link Configuration#getApplicationsDirectoryPath()}.
     * </p>
     *
     * @see Application
     * @see #fetchInstalledApplications()
     * @see #getInstalledApplications()
     */
    private final Application[] installedApplications = fetchInstalledApplications();

    /**
     * The applications currently running on the smartphone.
     * <p>
     *     The applications defined as "running" are the ones which have been opened and not been closed yet.
     * </p>
     *
     * @see ArrayList
     * @see Application
     * @see #run(Application)
     * @see #close(Application)
     * @see #closeAllApplications()
     * @see #isRunning(Application)
     */
    private final ArrayList<Application> runningApplications = new ArrayList<Application>();

    /**
     * ApplicationManager private constructor.
     *
     * <p>
     *     It delegates the object instantiation to the {@link #getInstance()} method.
     * </p>
     *
     * @see #getInstance()
     */
    private ApplicationManager() {}

    /**
     * ApplicationManager singleton constructor.
     *
     * <p>
     *     It ensures that the ApplicationManager class will only be instantiated once.
     * </p>
     *
     * @return the ApplicationManager object.
     *
     * @see #ApplicationManager()
     */
    public static ApplicationManager getInstance() {
        if (instance == null) {
            instance = new ApplicationManager();
        }
        return instance;
    }

    /**
     * Runs an application if it has not been run yet, displays it otherwise.
     *
     * @param application
     *              The application which will be opened.
     *
     * @see Application
     * @see #isRunning(Application)
     * @see UIManager#display(Application)
     * @see #run(Application)
     */
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

    /**
     * Runs an application.
     *
     * <p>
     *      Calls the {@link Application#onRun()}} method to do so.
     * </p>
     *
     * @param application
     *              The application which will be run.
     *
     * @see Application
     * @see Application#onRun()
     * @see #runningApplications
     */
    private void run(Application application) {
        application.onRun();
        uiManager.getContentPanel().add(application, String.valueOf(application.hashCode()));
        runningApplications.add(application);
    }

    /**
     * Closes a running application.
     *
     * <p>
     *     Calls the {@link Application#onClose()}} method to do so.
     * </p>
     *
     * @param application
     *              The application which will be closed.
     *
     * @see Application
     * @see Application#onClose()
     * @see #runningApplications
     */
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

    /**
     * Closes all running applications.
     *
     * @see #runningApplications
     * @see #close(Application)
     * @see Application
     */
    public void closeAllApplications() {
        Application[] applicationsArray = runningApplications.toArray(new Application[0]);

        for (Application application: applicationsArray) {
            close(application);
        }
    }

    /**
     * Checks if an application is currently running.
     *
     * @param application
     *              The application to check.
     *
     * @return true if the application is running, false otherwise.
     *
     * @see #runningApplications
     * @see Application
     */
    private boolean isRunning(Application application) {
        return runningApplications.contains(application);
    }

    /**
     * Fetches the applications installed on the smartphone and returns them in an array.
     *
     * @return The array of installed applications.
     *
     * @see Application
     * @see #fetchFoldersInDirectory(String)
     * @see Configuration
     * @see Class
     */
    private Application[] fetchInstalledApplications() {
        File[] foldersInDirectory = fetchFoldersInDirectory(Configuration.getInstance().getApplicationsDirectoryPath());

        int nbApplications = 0;

        if (foldersInDirectory != null) {
            nbApplications = foldersInDirectory.length;
        }

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

    /**
     * Fetches the folders inside a given directory and returns them in an array.
     *
     * @return The array of folders.
     *
     * @see File
     */
    private File[] fetchFoldersInDirectory(String directoryPath) {
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
