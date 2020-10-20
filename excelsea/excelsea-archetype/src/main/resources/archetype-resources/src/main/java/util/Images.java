package ${package}.util;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public enum Images {
    LOGO("/images/logo.png");

    private static final Logger LOG= LoggerFactory.getLogger(Images.class);
    private final String imagePath;

    Images(String imagePath) {
        this.imagePath = imagePath;
    }

    public Image getImage() {
        return new Image(Image.class.getResource(imagePath).toString());
    }

    public ImageView getImage(double width,double height) {
        ImageView view=new ImageView(getImage());
        view.setFitHeight(height);
        view.setFitWidth(width);
        return view;
    }
}
