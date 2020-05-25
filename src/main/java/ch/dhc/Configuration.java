package ch.dhc;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;

public class Configuration {

    private static Configuration instance;
    private static final ObjectMapper mapper = new ObjectMapper();
    private static final String configFilePath = "config.json";

    private String contactDirectoryPath;
    private String picturesDirectoryPath;
    private String applicationsDirectoryPath;

    private Configuration() {

    }

    public static Configuration getInstance() {
        if (instance == null) {
            try {
                instance = createConfigFromConfigFile();
            } catch (Exception e) {
                System.out.println(e);
                // TODO: Créer / écraser un fichier config.json à la racine du programme
                // TODO: Écrire dans config.json des valeurs par défaut
                // TODO: Attribuer à la variable la valeur ' new Configuration(defaultParam1, defaultParam2, ...) '
            }
        }

        return instance;
    }

    public String getContactDirectoryPath() {
        return contactDirectoryPath;
    }

    public String getPicturesDirectoryPath() {
        return picturesDirectoryPath;
    }

    public String getApplicationsDirectoryPath() {
        return applicationsDirectoryPath;
    }

    private static Configuration createConfigFromConfigFile() {
        try {
            File jarFilePath = new File(Configuration.class.getProtectionDomain().getCodeSource().getLocation().toURI());
            File configurationFile = new File(jarFilePath.getParent() + "/config.json");
            return mapper.readValue(configurationFile, Configuration.class);
        } catch (IOException | URISyntaxException e) {
            System.out.println(e);

            // TODO: Retourner une configuration avec des valeurs par défaut.
            return getInstance();
        }
    }
}
