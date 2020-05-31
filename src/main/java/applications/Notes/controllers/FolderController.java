package applications.Notes.controllers;

import applications.Notes.models.FolderListModel;
import applications.Notes.models.FolderModel;
import applications.Notes.views.FolderView;

/**
 * <b>Folder controller.</b>
 *
 * @author Cathy Gay
 */
public class FolderController {

    private FolderView folderView;
    private FolderModel folderModel;
    private FolderListModel folderListModel;

    public FolderController(FolderView folderView, FolderModel folderModel, FolderListModel folderListModel) {

        this.folderView = folderView;
        this.folderModel = folderModel;
        this.folderListModel = folderListModel;

        this.folderView.addAddNoteListener(e -> this.folderModel.addNote());
        this.folderView.addDeleteFolderListener(e -> this.folderListModel.deleteFolder());
    }
}
