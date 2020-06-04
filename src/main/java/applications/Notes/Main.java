package applications.Notes;

import applications.Notes.controllers.AppController;
import applications.Notes.models.FolderListModel;
import applications.Notes.models.FolderModel;
import applications.Notes.models.NoteModel;
import applications.Notes.views.FolderListView;
import applications.Notes.views.FolderView;
import applications.Notes.views.NoteView;
import ch.dhc.Application;
import ch.dhc.Configuration;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.awt.*;

public class Main extends Application {

    String name = "Notes";
    String iconPath = "icon/app_icon_notes.png";


    public Main() {

    }

    @Override
    public void onRun() {

        NoteModel noteModel = new NoteModel();
        FolderModel folderModel = new FolderModel();
        FolderListModel folderListModel = new FolderListModel();

        NoteView noteView = new NoteView();
        FolderView folderView = new FolderView();
        FolderListView folderListView = new FolderListView(folderListModel);


        AppController folderController = new AppController(this, noteModel, folderModel, folderListModel, noteView, folderView, folderListView);

        CardLayout cardLayout = new CardLayout();

        setLayout(cardLayout);

        add(folderListView, String.valueOf(folderListView.hashCode()));
        add(folderView, String.valueOf(folderView.hashCode()));

        cardLayout.show(this, String.valueOf(folderView.hashCode()));


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
