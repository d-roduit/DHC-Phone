package applications.Notes.controllers;

import applications.Notes.models.FolderModel;
import applications.Notes.models.NoteModel;
import applications.Notes.views.FolderListView;
import applications.Notes.views.FolderView;
import ch.dhc.Configuration;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import javax.swing.*;
import java.io.File;
import java.util.List;

public class FolderController {

    private FolderModel folderModel;
    private FolderView folderView;
    private FolderListController folderListController;

    public FolderController(FolderModel folderModel, FolderView folderView, FolderListController folderListController) {
        this.folderModel = folderModel;
        this.folderView = folderView;
        this.folderListController = folderListController;

        initListeners();
    }

    private void initListeners() {

        //Listener for adding a note.
        folderView.getAddNoteButton().addActionListener(e -> {
//            folderModel.addNote();
            createNewNote();
            updateFolderView(folderModel);
        });

        //Listener for deleting a folder.
        folderView.getDeleteFolderButton().addActionListener(e -> deleteFolder());

        //Listener for going back to the FolderList.
        folderView.getReturnToFolderListButton().addActionListener(e ->
                folderListController.updateFolderListView(folderListController.getFolderListModel()));

    }

    private void createNewNote() {

        String newNoteTitle = JOptionPane.showInputDialog(
                folderListController.getMain(),
                "New Note Title :",
                "Create New Note",
                JOptionPane.QUESTION_MESSAGE
        );

        try {
            if(newNoteTitle != null) {
                Configuration configuration = Configuration.getInstance();
                ObjectMapper mapper = new ObjectMapper();
                ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());

                File folderFile = new File(configuration.getNotesFolderPath() + "folders.json");

                folderModel.addNote(newNoteTitle);

                List<FolderModel> folderModels = folderListController.getFolderListModel().getFolderModels();

                writer.writeValue(folderFile, folderModels);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void deleteFolder() {

        int option = JOptionPane.showConfirmDialog(
                folderListController.getMain(),
                "Are you sure you want to delete \"" + folderModel.getFolderTitle() + "\" folder ? \nThis will delete all its notes as well.",
                "Delete Folder",
                JOptionPane.YES_NO_OPTION
        );

        try {
            if(option == JOptionPane.YES_OPTION) {
                Configuration configuration = Configuration.getInstance();
                ObjectMapper mapper = new ObjectMapper();
                ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
                File folderFile = new File(configuration.getNotesFolderPath() + "folders.json");

                folderListController.getFolderListModel().deleteFolder(folderModel);

                List<FolderModel> folderModels = folderListController.getFolderListModel().getFolderModels();

                writer.writeValue(folderFile, folderModels);

                folderListController.updateFolderListView(folderListController.getFolderListModel());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateFolderView(FolderModel folderModel) {
        folderListController.getMain().remove(folderView);
        folderView = new FolderView(folderModel);
        folderListController.getMain().add(folderView, String.valueOf(folderView.hashCode()));
        folderListController.getMain().revalidate();
        folderListController.getMain().repaint();
        folderListController.getMain().getCardLayout().show(folderListController.getMain(), String.valueOf(folderView.hashCode()));
        initListeners();
    }
}
