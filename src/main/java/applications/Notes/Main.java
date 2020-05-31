package applications.Notes;

import applications.Notes.controllers.FolderController;
import applications.Notes.controllers.FolderListController;
import applications.Notes.controllers.NoteController;
import applications.Notes.models.FolderListModel;
import applications.Notes.models.FolderModel;
import applications.Notes.models.NoteModel;
import applications.Notes.views.FolderListView;
import applications.Notes.views.FolderView;
import applications.Notes.views.NoteView;
import ch.dhc.Application;

import java.awt.*;

public class Main extends Application {

    String name = "Notes";
    String iconPath = "icon/app_icon_notes.png";


    public Main() {

        FolderListView folderListView = new FolderListView();
        FolderListModel folderListModel = new FolderListModel();
        FolderListController folderListController = new FolderListController(folderListView, folderListModel);

        FolderView folderView = new FolderView();
        FolderModel folderModel = new FolderModel();
        FolderController folderController = new FolderController(folderView, folderModel, folderListModel);

        NoteView noteView = new NoteView();
        NoteModel noteModel = new NoteModel();
        NoteController noteController = new NoteController(noteView, noteModel);

        add(folderView);
    }

    @Override
    public void onRun() {
        //setBackground(Color.RED);
//        add(folderListPanel);
//        add(folderPanel);
    }

    @Override
    public void onClose() {
        System.out.println("Notes dit 'Au revoir !'");
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getIconPath() {
        return iconPath;
    }

}
