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
        noteView.getReturnToNoteListButton().addActionListener(e -> {
            folderController.updateFolderView(folderController.getFolderModel());
        });


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

        //Listener for saving the note.
        noteView.getSaveNoteButton().addActionListener(e -> saveNote());

        //Listener for showing the preview
        noteView.getPreviewButton().addActionListener(e -> showPreview());

        //Listener for setting the selected text in bold
        noteView.getBoldButton().addActionListener(e -> bold());

        //Listener for setting the selected text in italic
        noteView.getItalicButton().addActionListener(e -> italic());

        //Listener for underlining the selected text
        noteView.getUnderlineButton().addActionListener(e -> underline());
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
            if(!newNoteTitle.equals("")) {
                Configuration configuration = Configuration.getInstance();
                ObjectMapper mapper = new ObjectMapper();
                ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
                File folderFile = new File(configuration.getNotesFolderPath() + "folders.json");

                noteModel.setNoteTitle(newNoteTitle);

                List<FolderModel> folderModels = folderController.getFolderListController().getFolderListModel().getFolderModels();

                writer.writeValue(folderFile, folderModels);

                updateNoteView(noteModel);
            } else {
                JOptionPane.showMessageDialog(folderController.getFolderListController().getMain(),
                        "The note title is empty !",
                        "Warning",
                        JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void saveNote() {
        try {
            if (noteView.getEditorPane().getContentType().equals("text/plain")) {
                Configuration configuration = Configuration.getInstance();
                ObjectMapper mapper = new ObjectMapper();
                ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
                File folderFile = new File(configuration.getNotesFolderPath() + "folders.json");

                String noteContent = noteView.getEditorPane().getText();

                String pattern = "[^(<BR>)(<BR>\r)]\n";
//                String pattern = "[(?<!<BR>)(?<!<BR>\r)]\n";
//                String patternFull = "[^(<BR>)]\r\n";
                String patternFull = "(?<!<BR>)\r\n";


                String newNoteContent = noteContent.replaceAll(patternFull, "<BR>\r\n");

                String finalNoteContent = newNoteContent.replaceAll(pattern, "<BR>\n");

                noteModel.setContent(finalNoteContent);

                List<FolderModel> folderModels = folderController.getFolderListController().getFolderListModel().getFolderModels();

                writer.writeValue(folderFile, folderModels);

                updateNoteView(noteModel);
            }

            } catch(Exception e){
                e.printStackTrace();
            }
    }

    private void showPreview() {
            saveNote();
            if(noteView.getEditorPane().getContentType().equals("text/plain")) {
                updateHtmlNoteView(noteModel);
                noteView.getEditorPane().setEditable(false);
                noteView.getPreviewButton().setContentAreaFilled(true);
                noteView.getPreviewButton().setToolTipText("Show edit");
            } else {
                updateNoteView(noteModel);
                noteView.getEditorPane().setEditable(true);
            }
    }

    private void bold() {
        String selectedText = noteView.getEditorPane().getSelectedText();

        if(selectedText != null) {
                noteView.getEditorPane().replaceSelection("<B>" + selectedText + "</B>");
        }
    }

    private void italic() {
        String selectedText = noteView.getEditorPane().getSelectedText();

        if(selectedText != null) {
            noteView.getEditorPane().replaceSelection("<I>" + selectedText + "</I>");
        }
    }

    private void underline() {
        String selectedText = noteView.getEditorPane().getSelectedText();

        if(selectedText != null) {
            noteView.getEditorPane().replaceSelection("<U>" + selectedText + "</U>");
        }
    }

    public void updateHtmlNoteView(NoteModel noteModel) {
        folderController.getFolderListController().getMain().remove(noteView);
        noteView = new NoteView(noteModel);
        noteView.getEditorPane().setContentType("text/html");
        noteView.getEditorPane().setText(noteModel.getContent());

        folderController.getFolderListController().getMain().add(noteView, String.valueOf(noteView.hashCode()));
        folderController.getFolderListController().getMain().revalidate();
        folderController.getFolderListController().getMain().repaint();
        folderController.getFolderListController().getMain().getCardLayout().show(folderController.getFolderListController().getMain(), String.valueOf(noteView.hashCode()));

        initListeners();
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
