package outlookanalyzer.scene.Layouts;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.HBox;

public class HoizontalBoxHelper extends Box {

    public static HBox createHBox(){
        javafx.scene.layout.HBox hBox = new javafx.scene.layout.HBox();
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(SPACING);
        return hBox;
    }
    public static HBox createHBox(double spacing){
        javafx.scene.layout.HBox hBox = createHBox();
        hBox.setSpacing(spacing);
        return hBox;
    }

    public static HBox createHBox(double spacing, Node... nodes){
        javafx.scene.layout.HBox hBox = createHBox(spacing);

        hBox.getChildren().addAll(nodes);

        return  hBox;
    }
}
