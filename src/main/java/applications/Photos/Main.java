package applications.Photos;

import applications.Photos.controllers.GalleryController;
import applications.Photos.models.AlbumModel;
import applications.Photos.models.GalleryModel;
import applications.Photos.models.PictureModel;
import applications.Photos.views.GalleryView;
import ch.dhc.Application;
import ch.dhc.Configuration;
import jiconfont.icons.font_awesome.FontAwesome;
import jiconfont.icons.google_material_design_icons.GoogleMaterialDesignIcons;
import jiconfont.swing.IconFontSwing;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.File;
import java.util.Arrays;
import java.util.List;

public class Main extends Application {

    private final String name = "Photos";
    private final String iconPath = "icon/app_icon_photos.png";

    private final CardLayout cardLayout = new CardLayout();
    private final Color backgroundColor = Color.BLACK;

    public Main() {
        setLayout(cardLayout);
        setBackground(backgroundColor);
        setBorder(new EmptyBorder(15, 0, 0, 0));
    }

    @Override
    public void onRun() {
        // Crée le dossier 'thumbnails' s'il n'existe pas déjà.
        createThumbnailFolderIfNotExists();

        GalleryModel galleryModel = new GalleryModel();
        GalleryView galleryView = new GalleryView(galleryModel);
        GalleryController galleryController = new GalleryController(galleryModel, galleryView, this);

        add(galleryController.getGalleryView(), String.valueOf(galleryController.getGalleryView().hashCode()));
    }


    @Override
    public void onClose() {
        System.out.println("Photos dit 'Au revoir !'");
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getIconPath() {
        return iconPath;
    }

    @Override
    public Color getStatusBarBackgroundColor() {
        return backgroundColor;
    }

    private void createThumbnailFolderIfNotExists() {
        File thumbnailsFolder = new File(Configuration.getInstance().getPicturesFolderPath() + "thumbnails/");

        if (!thumbnailsFolder.exists()) {
            thumbnailsFolder.mkdir();
        }
    }

    public CardLayout getCardLayout() {
        return cardLayout;
    }

}
