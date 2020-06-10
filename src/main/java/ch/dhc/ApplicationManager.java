package ch.dhc;

import java.util.ArrayList;
import java.util.List;

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
     * @see #run(Application, boolean)
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
     * @see #run(Application, boolean)
     */
    public void open(Application application, boolean addToRunningApps) {
        if (isRunning(application)) {
            moveToEnd(runningApplications, application);
            uiManager.display(application);
        } else {
            try {
                run(application, addToRunningApps);
                uiManager.display(application);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * Runs an application if it has not been run yet, displays it otherwise.
     * Overloading of {@link #open(Application, boolean)}
     *
     * @param application
     *              The application which will be opened.
     *
     * @see Application
     * @see #open(Application, boolean) 
     */
    public void open(Application application) {
        open(application, true);
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
     * @param addToRunningApps
     *              A boolean that says if the application must be added to the list of running applications.
     *              If you wish to open an application as a sub-process of another application,
     *              put its value to false.
     *
     * @see Application
     * @see Application#onRun()
     * @see #runningApplications
     */
    private void run(Application application, boolean addToRunningApps) {
        application.onRun();

        uiManager.getContentPanel().add(application, String.valueOf(application.hashCode()));

        if (addToRunningApps) {
            runningApplications.add(application);
        }
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
                "Photos"
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

    /**
     * Returns if there are applications that are currently running or not.
     *
     * @return true if there are applications that are currently running on the smartphone, false otherwise.
     */
    public boolean hasRunningApplications() {
        return runningApplications.size() > 0;
    }

    /**
     * Move an application to the end of an applicationList.
     * It is used to keep track of which application has been opened or reopened at last.
     *
     * @param applicationList The list of applications in which the application is moved to the end.
     * @param application The application that will be moved to the end of the list.
     */
    private void moveToEnd(List<Application> applicationList, Application application) {
        applicationList.remove(application);
        applicationList.add(application);
    }

    /**
     * Returns the list of applications that are currently running.
     *
     * @return The list of currently running applications.
     *
     * @see Application
     * @see ArrayList
     */
    public ArrayList<Application> getRunningApplications() {
        return runningApplications;
    }

    /**
     * Returns the array of applications that are installed on the smartphone.
     *
     * @return The array of applications that are installed on the smartphone.
     *
     * @see Application
     */
    public Application[] getInstalledApplications() {
        return installedApplications;
    }

    /**
     * Sets the UIManager object that is used to handle graphic / display operations.
     *
     * @param uiManager
     *              The UIManager object that handle graphic / display operations.
     *
     * @see UIManager
     */
    public void setUiManager(UIManager uiManager) {
        this.uiManager = uiManager;
    }

}
