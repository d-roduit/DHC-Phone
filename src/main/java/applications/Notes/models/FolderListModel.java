package applications.Notes.models;

import ch.dhc.Configuration;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class FolderListModel {

    private FolderModel [] folderModels = fetchFolders();

    public FolderListModel() {

    }

    public void addFolder() {
        System.out.println("Add folder clicked");
    }

    public void deleteFolder() {
        System.out.println("Delete folder clicked");
    }

    private FolderModel[] fetchFolders() {

        ObjectMapper mapper = new ObjectMapper();
        Configuration configuration = Configuration.getInstance();

        File folderFile = new File(configuration.getNotesFolderPath() + "folders.json");

        FolderModel[] folderModels = new FolderModel[0];

        try {
            folderModels = mapper.readValue(folderFile, FolderModel[].class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return folderModels;
    }

    public FolderModel[] getFolderModels() {
        return folderModels;
    }


}
