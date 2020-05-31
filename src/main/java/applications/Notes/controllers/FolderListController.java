package applications.Notes.controllers;

import applications.Notes.models.FolderListModel;
import applications.Notes.views.FolderListView;

public class FolderListController {

    private FolderListView folderListView;
    private FolderListModel folderListModel;

    public FolderListController(FolderListView folderListView, FolderListModel folderListModel) {

        this.folderListView = folderListView;
        this.folderListModel = folderListModel;

        this.folderListView.addAddFolderListener(e -> this.folderListModel.addFolder());
    }
}
