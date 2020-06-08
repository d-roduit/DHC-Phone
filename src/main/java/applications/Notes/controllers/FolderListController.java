package applications.Notes.controllers;

import applications.Notes.Main;
import applications.Notes.models.FolderListModel;
import applications.Notes.models.FolderModel;
import applications.Notes.views.FolderListView;
import applications.Notes.views.FolderView;
import ch.dhc.Configuration;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;

/**
 * <b>Folder controller.</b>
 *
 * @author Cathy Gay
 */
public class FolderListController {

    private FolderListModel folderListModel;
    private FolderListView folderListView;

    private Main main;

    public FolderListController(Main main, FolderListModel folderListModel, FolderListView folderListView) {

        this.main = main;
        this.folderListModel = folderListModel;
        this.folderListView = folderListView;

        initListeners();

    }

    private void initListeners() {

        //Listener for adding a folder.
        this.folderListView.getAddFolderButton().addActionListener(e -> {
            createNewFolder();
            main.remove(folderListView);
            folderListView = new FolderListView(folderListModel);
            main.add(folderListView, String.valueOf(folderListView.hashCode()));
            main.revalidate();
            main.repaint();
            main.getCardLayout().show(main, String.valueOf(folderListView.hashCode()));
            initListeners();
        });

        //Listener for going to a folder view.
        this.folderListView.getPanelFolderModelMap().forEach((panel, folderModel) -> {
            panel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    FolderView folderView = new FolderView(folderModel);
                    FolderController folderController = new FolderController(folderModel, folderView, FolderListController.this);
                    main.add(folderView, String.valueOf(folderView.hashCode()));
                    main.getCardLayout().show(main, String.valueOf(folderView.hashCode()));
                }
            });
        });

    }

    private void createNewFolder() {
        String newFolderTitle = JOptionPane.showInputDialog(
                main,
                "New Folder Title :",
                "Create New Folder",
                JOptionPane.QUESTION_MESSAGE
        );

        try {
            if(newFolderTitle != null) {
                Configuration configuration = Configuration.getInstance();
                ObjectMapper mapper = new ObjectMapper();
                ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());

                File folderFile = new File(configuration.getNotesFolderPath() + "folders.json");

                folderListModel.addFolder(newFolderTitle);
                List<FolderModel> folderModels = folderListModel.getFolderModels();

                writer.writeValue(folderFile, folderModels);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public FolderListModel getFolderListModel() {
        return folderListModel;
    }

    public FolderListView getFolderListView() {
        return folderListView;
    }

    public Main getMain() {
        return main;
    }

}
