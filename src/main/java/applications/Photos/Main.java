package applications.Photos;

import applications.Photos.controllers.GalleryController;
import applications.Photos.models.GalleryModel;
import applications.Photos.views.GalleryView;
import ch.dhc.Application;
import ch.dhc.Configuration;
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
    }

    @Override
    public void onRun() {

        // Regarder si de nouvelles photos ont été ajoutées
        //  -> Lire tous les fichiers photos dans le dossier externes "photos" -> en faire un tableau.
        //  -> Lire tous les fichiers photos de tous les albums qui sont dans le fichier `albums.json` -> en faire un tableau.
        //  -> Faire tableauDephotosExternes.removeAll(tableauDePhotoDuAlbums.json);

        // Créer le dossier thumbnails s'il n'existe pas

        // Si le dossier thumbnail existe, regarder quelles thumbnails doivent être regénérés.
        //  -> ThumbnailsQu'onDoitGénérer = faire ThumbnailsQu'onDoitAvoir[].removeAll(ThumbnailsDejaGénérées[]);
        //  -> ThumbnailsÀSupprimer = faire ThumbnailsDejaGénérées[].removeAll(ThumbnailsQu'onDoitAvoir[])

        // Supprimer les thumbnails inutiles

        // Générer les thumbnails manquantes



        String picturesFolderPath = Configuration.getInstance().getPicturesFolderPath();

        String[] thumbnailsNames = new String[] {
            "photo1.jpg",
            "photo2.png",
            "photo3.png",
            "photo4.png",
        };

        File thumbnailsFolder = new File(picturesFolderPath + "thumbnails/");
        if (!thumbnailsFolder.exists()) {
            thumbnailsFolder.mkdir();
        }

        for (String thumbnailsName: thumbnailsNames) {
            File sourceFile = new File(picturesFolderPath + thumbnailsName);

            try {
                ThumbnailGenerator.generate(sourceFile, picturesFolderPath + "thumbnails/" + thumbnailsName);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        GalleryModel galleryModel = new GalleryModel();
        GalleryView galleryView = new GalleryView(galleryModel);
        GalleryController galleryController = new GalleryController(galleryModel, galleryView);

        add(galleryController.getGalleryView());
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
