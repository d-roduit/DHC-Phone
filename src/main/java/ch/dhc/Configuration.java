package ch.dhc;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

public class Configuration {

    private static Configuration instance;
    private static final ObjectMapper mapper = new ObjectMapper();
    private static final String configFileName = "config.json";
    private static final String jarDirectoryPath = findJarDirectoryPath();
    private static final File configurationFile = new File(jarDirectoryPath + "/" + configFileName);

    private String notesFolderPath;
    private String contactsFolderPath;
    private String picturesFolderPath;

    private Configuration() {

    }

    private Configuration(String contactsFolderPath, String picturesFolderPath, String notesFolderPath) {
        this.notesFolderPath = notesFolderPath;
        this.contactsFolderPath = contactsFolderPath;
        this.picturesFolderPath = picturesFolderPath;
    }

    public static Configuration getInstance() {
        if (instance == null) {
            try {
                instance = createConfigFromConfigFile();
            } catch (Exception e) {
                System.out.println("Unable to read file '" + configFileName + "' : " + e.getMessage());
                System.out.println("Creating default configuration...");

                // Default configuration values
                String notesFolderPath = jarDirectoryPath + "/notes/";
                String contactsFolderPath = jarDirectoryPath + "/contacts/";
                String picturesFolderPath = jarDirectoryPath + "/pictures/";

                // Creates default folders in accordance with the configuration default values.
                createFolder(notesFolderPath);
                createFolder(contactsFolderPath);
                createFolder(picturesFolderPath);

                Configuration defaultConfiguration = new Configuration(contactsFolderPath, picturesFolderPath, notesFolderPath);

                try {
                    createConfigFile(defaultConfiguration);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }

        return instance;
    }

    private static String findJarDirectoryPath() {
        String jarDirectoryPath = "";

        try {
            jarDirectoryPath = (new File(Configuration.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getParent());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return jarDirectoryPath;
    }

    private static void createFolder(String folderPath) {
        File directoryFile = new File(folderPath);
        directoryFile.mkdir();
    }

    private static Configuration createConfigFromConfigFile() throws IOException {
        return mapper.readValue(configurationFile, Configuration.class);
    }

    private static void createConfigFile(Configuration configuration) throws IOException {
        mapper.writeValue(configurationFile, configuration);
    }

    public String getNotesFolderPath() {
        return notesFolderPath;
    }

    public String getContactsFolderPath() {
        return contactsFolderPath;
    }

    public String getPicturesFolderPath() {
        return picturesFolderPath;
    }

}
