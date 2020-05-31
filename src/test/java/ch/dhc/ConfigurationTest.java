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

        assertNotNull(configuration.getNotesFolderPath(), "The property notesFolderPath cannot be null");
        assertNotNull(configuration.getContactsFolderPath(), "The property contactsFolderPath cannot be null");
        assertNotNull(configuration.getPicturesFolderPath(), "The property picturesFolderPath cannot be null");
    }
}