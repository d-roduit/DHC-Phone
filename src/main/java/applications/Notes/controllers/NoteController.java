package applications.Notes.controllers;

import applications.Notes.models.FolderModel;
import applications.Notes.models.NoteModel;
import applications.Notes.views.FolderView;
import applications.Notes.views.NoteView;
import ch.dhc.Configuration;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.List;

public class NoteController {

    private NoteModel noteModel;
    private NoteView noteView;
    private FolderController folderController;

    public NoteController(NoteModel noteModel, NoteView noteView, FolderController folderController) {
        this.noteModel = noteModel;
        this.noteView = noteView;
        this.folderController = folderController;

        initListeners();
    }

    private void initListeners() {

        //Listener for going back to the folder.
        noteView.getReturnToNoteListButton().addActionListener(e ->
                folderController.updateFolderView(folderController.getFolderModel()));

        //Listener for deleting a note.
        noteView.getDeleteNoteButton().addActionListener(e -> deleteNote());

        //Listener for modifying the note title.
        noteView.getNoteTitleLabel().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() == 2) {
                    super.mouseClicked(e);
                    modifyNoteTitle();
                }
            }
        });
    }

    private void deleteNote() {

        int option = JOptionPane.showConfirmDialog(
                folderController.getFolderListController().getMain(),
                "Are you sure you want to delete \"" + noteModel.getNoteTitle() + "\" note ? \nThis will delete all its content.",
                "Delete Note",
                JOptionPane.YES_NO_OPTION
        );

        try {
            if(option == JOptionPane.YES_OPTION) {
                Configuration configuration = Configuration.getInstance();
                ObjectMapper mapper = new ObjectMapper();
                ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
                File folderFile = new File(configuration.getNotesFolderPath() + "folders.json");

                folderController.getFolderModel().deleteNote(noteModel);

                List<FolderModel> folderModels = folderController.getFolderListController().getFolderListModel().getFolderModels();

                writer.writeValue(folderFile, folderModels);

                folderController.updateFolderView(folderController.getFolderModel());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void modifyNoteTitle() {
        String newNoteTitle = (String) JOptionPane.showInputDialog(
                folderController.getFolderListController().getMain(),
                "Note Title :",
                "Note Title",
                JOptionPane.QUESTION_MESSAGE,
                null,
                null,
                noteModel.getNoteTitle()
        );

        try {
            if(newNoteTitle != null) {
                Configuration configuration = Configuration.getInstance();
                ObjectMapper mapper = new ObjectMapper();
                ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
                File folderFile = new File(configuration.getNotesFolderPath() + "folders.json");

                noteModel.setNoteTitle(newNoteTitle);

                List<FolderModel> folderModels = folderController.getFolderListController().getFolderListModel().getFolderModels();

                writer.writeValue(folderFile, folderModels);

                updateNoteView(noteModel);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateNoteView(NoteModel noteModel) {
        folderController.getFolderListController().getMain().remove(noteView);
        noteView = new NoteView(noteModel);
        folderController.getFolderListController().getMain().add(noteView, String.valueOf(noteView.hashCode()));
        folderController.getFolderListController().getMain().revalidate();
        folderController.getFolderListController().getMain().repaint();
        folderController.getFolderListController().getMain().getCardLayout().show(folderController.getFolderListController().getMain(), String.valueOf(noteView.hashCode()));
        initListeners();
    }
}
