package ch.dhc;

import java.util.ArrayList;

/**
 * <b>ApplicationManager is the class that manages all operations that can be performed on applications.</b>
 *
 * @author Daniel Roduit
 */
public final class ApplicationManager {

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
                e.printStackTrace();
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
                e.printStackTrace();
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
     * @see Class
     */
    private Application[] fetchInstalledApplications() {
        final String[] applicationFolderNames = new String[] {
                "Contacts",
                "Notes",
                "Photos",
                "WhatsApp"
        };

        final Application[] applicationList = new Application[applicationFolderNames.length];

        for (int i = 0; i < applicationFolderNames.length; i++) {
            try {
                String fullyQualifiedClassName = "applications." + applicationFolderNames[i] + ".Main";

                Class applicationClass = Class.forName(fullyQualifiedClassName);
                Application application = (Application) applicationClass.getDeclaredConstructor().newInstance();
                application.setFolder(applicationFolderNames[i]);

                applicationList[i] = application;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return applicationList;
    }

    public Application[] getInstalledApplications() {
        return installedApplications;
    }

    public void setUiManager(UIManager uiManager) {
        this.uiManager = uiManager;
    }

}
