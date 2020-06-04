package applications.Notes.controllers;

import applications.Notes.Main;
import applications.Notes.models.FolderListModel;
import applications.Notes.models.FolderModel;
import applications.Notes.models.NoteModel;
import applications.Notes.views.FolderListView;
import applications.Notes.views.FolderView;
import applications.Notes.views.NoteView;

import java.awt.*;
import java.io.IOException;

/**
 * <b>Folder controller.</b>
 *
 * @author Cathy Gay
 */
public class AppController {

    private NoteModel noteModel;
    private FolderModel folderModel;
    private FolderListModel folderListModel;
    private NoteView noteView;
    private FolderView folderView;
    private FolderListView folderListView;

    private Main main;

    public AppController(Main main, NoteModel noteModel, FolderModel folderModel, FolderListModel folderListModel, NoteView noteView, FolderView folderView, FolderListView folderListView) {

        this.main = main;
        this.folderListModel = folderListModel;
        this.folderListView = folderListView;
        this.folderView = folderView;
        this.folderModel = folderModel;
        this.noteView = noteView;
        this.noteModel = noteModel;

        this.folderListView.addAddFolderListener(e -> this.folderListModel.addFolder());

        this.folderView.addAddNoteListener(e -> this.folderModel.addNote());
        this.folderView.addDeleteFolderListener(e -> this.folderListModel.deleteFolder());
        this.folderView.addReturnToFolderListListener(e -> ((CardLayout)main.getLayout()).show(main, String.valueOf(folderListView.hashCode())));

    }



}
