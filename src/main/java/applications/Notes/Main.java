package applications.Notes;

import applications.Notes.controllers.FolderListController;
import applications.Notes.models.FolderListModel;
import applications.Notes.views.FolderListView;
import ch.dhc.Application;

import java.awt.*;

public class Main extends Application {

    String name = "Notes";
    String iconPath = "icon/app_icon_notes.png";
    CardLayout cardLayout = new CardLayout();


    public Main() {

    }

    @Override
    public void onRun() {

        FolderListModel folderListModel = new FolderListModel();

        FolderListView folderListView = new FolderListView(folderListModel);

        FolderListController folderListController = new FolderListController(this, folderListModel, folderListView);

        setLayout(cardLayout);

        add(folderListView, String.valueOf(folderListView.hashCode()));

    }

    @Override
    public void onClose() {
        removeAll();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getIconPath() {
        return iconPath;
    }

    public CardLayout getCardLayout() {
        return cardLayout;
    }
}
