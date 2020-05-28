package applications.Photos;

import applications.Photos.controllers.GalleryController;
import applications.Photos.models.GalleryModel;
import applications.Photos.views.GalleryView;
import ch.dhc.Application;
import jiconfont.icons.font_awesome.FontAwesome;
import jiconfont.icons.google_material_design_icons.GoogleMaterialDesignIcons;
import jiconfont.swing.IconFontSwing;

import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.File;
import java.util.Arrays;

public class Main extends Application {

    String name = "Photos";
    String iconPath = "icon/app_icon_photos.png";

    Color backgroundColor = Color.BLACK;

    public Main() {
        setBackground(backgroundColor);
        setBorder(new EmptyBorder(15, 0, 0, 0));

        GalleryView galleryView = new GalleryView();
        GalleryModel galleryModel = new GalleryModel();

        GalleryController galleryController = new GalleryController(galleryView, galleryModel);

        add(galleryView);
    }

    @Override
    public void onRun() {

        File sourceFile = new File("fb.png");

        try {
            ThumbnailGenerator.generate(sourceFile, "thumbnail");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // cardlayout.show(this, galleryView);

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

}
