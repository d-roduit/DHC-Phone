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

/**
 * <b>FolderListModel is the class that represents a folder list with its properties and its methods.</b>
 *
 * @author Cathy Gay
 */
public class FolderListModel {

    /**
     * The folderlist list of folders.
     */
    private List<FolderModel> folderModels;

    /**
     * FolderListModel constructor.
     *
     * @see FolderListModel#fetchFolders()
     */
    public FolderListModel() {
        folderModels = fetchFolders();
    }

    /**
     * Returns a new folder.
     * <p>
     *    The new folder is created with a title, a creation date that is the current date, and en empty list of NoteModel.
     *    The new folder is then added to the list of FolderModel.
     * </p>
     *
     * @param folderTitle
     *              The folder title.
     *
     * @return The new FolderModel.
     *
     * @see FolderModel
     * @see Date
     * @see SimpleDateFormat
     */
    public FolderModel addFolder(String folderTitle) {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
        FolderModel folderModel = new FolderModel(folderTitle, dateFormat.format(date), new ArrayList<>());
        folderModels.add(folderModel);

        return folderModel;
    }

    /**
     * Delete a folder.
     * <p>
     *     The folder is removed from the list of FolderModel.
     * </p>
     *
     * @param folderModel
     *              The FolderModel that should be deleted.
     *
     * @see FolderModel
     */
    public void deleteFolder(FolderModel folderModel) {
        folderModels.remove(folderModel);
    }

    /**
     * Returns a list of folders read from a json file.
     * <p>
     *     This will read the folder json file which path is gotten from the configuration file.
     * </p>
     *
     * @return A list of FolderModel.
     *
     * @see FolderModel
     * @see ObjectMapper
     * @see Configuration
     * @see File
     */
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

        return folderModels;

    }

    /**
     * Returns the list of folders.
     *
     * @return The list of FolderModel.
     */
    public List<FolderModel> getFolderModels() {
        return folderModels;
    }
}
