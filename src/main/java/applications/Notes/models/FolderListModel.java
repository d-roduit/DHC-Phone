package applications.Notes.models;

import ch.dhc.Configuration;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FolderListModel {

    private List<FolderModel> folderModels;

    public FolderListModel() {
        folderModels = fetchFolders();
    }

    public FolderModel addFolder(String folderTitle) {
        System.out.println("Add folder clicked");
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
        FolderModel folderModel = new FolderModel(folderTitle, dateFormat.format(date), new ArrayList<>());
        folderModels.add(folderModel);

        return folderModel;
    }

    public void deleteFolder(FolderModel folderModel) {
        System.out.println("Delete folder clicked : " + folderModel.getFolderTitle());
        folderModels.remove(folderModel);
    }

    private List<FolderModel> fetchFolders() {

        ObjectMapper mapper = new ObjectMapper();
        Configuration configuration = Configuration.getInstance();

        File folderFile = new File(configuration.getNotesFolderPath() + "folders.json");

        folderModels = new ArrayList<FolderModel>();

        try {
            JavaType type = mapper.getTypeFactory().constructCollectionType(List.class, FolderModel.class);

            folderModels = mapper.readValue(folderFile, type);

        } catch (IOException e) {
            e.printStackTrace();
        }

//        for(FolderModel folderModel : folderModels) {
//
//            NoteModel[] noteModels = folderModel.getNotes();
//
//            for(NoteModel noteModel : noteModels) {
//                System.out.println(noteModel.getNoteTitle());
//                System.out.println(noteModel.getNoteCreationDate());
//                System.out.println(noteModel.getContent());
//            }
//        }

        return folderModels;

    }

    public List<FolderModel> getFolderModels() {
        return folderModels;
    }
}
