package ch.dhc;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ApplicationManagerTest {

    @Test
    void getInstance_sameHashCode() {
        assertSame(ApplicationManager.getInstance(), ApplicationManager.getInstance());
    }

    @Test
    void getInstalledApplications_isNotNull() {
        Application[] installedApplications = ApplicationManager.getInstance().getInstalledApplications();
        assertNotNull(installedApplications, "The property installedApplications cannot be null");
    }
}