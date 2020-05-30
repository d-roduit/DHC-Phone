package ch.dhc;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConfigurationTest {

    @Test
    void getInstance_sameHashCode() {
        assertSame(Configuration.getInstance(), Configuration.getInstance());
    }

    @Test
    void getInstance_hasNotNullConfigValues() {
        Configuration configuration = Configuration.getInstance();

        assertNotNull(configuration.getContactDirectoryPath(), "The property contactDirectoryPath cannot be null");
        assertNotNull(configuration.getPicturesDirectoryPath(), "The property picturesDirectoryPath cannot be null");
    }
}