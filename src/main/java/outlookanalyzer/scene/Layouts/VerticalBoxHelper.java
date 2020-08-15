package outlookanalyzer.scene.Layouts;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

public class VerticalBoxHelper {
    
    public static VBox createVBox(){
        VBox VBox = new VBox();
        VBox.setAlignment(Pos.CENTER_LEFT);
        return VBox;
    }
    public static VBox createVBox(double spacing){
        VBox vBox = createVBox();
        vBox.setSpacing(spacing);
        return vBox;
    }

    public static VBox createVBox(double spacing, Node... nodes){
        VBox vBox = createVBox(spacing);

        vBox.getChildren().addAll(nodes);

        return  vBox;
    }
}
