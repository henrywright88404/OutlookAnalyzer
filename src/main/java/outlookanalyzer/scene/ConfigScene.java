package outlookanalyzer.scene;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import outlookanalyzer.App;
import outlookanalyzer.outlookconnection.Graph;

public class ConfigScene {


    public static Scene getConfigScene(){

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(new Label("Welcome " + App.getUser().displayName));

       // Graph.getMessagesFromMailBox("sarah.moore@ando.co.nz");
        Graph.getMessagesFromMe();

        TableView table = new TableView();
        TableColumn firstNameCol = new TableColumn("Inbox");
        TableColumn lastNameCol = new TableColumn("Team");

        table.setEditable(true);
//        table.set
        table.getColumns().addAll(firstNameCol,lastNameCol);

        final VBox tableBox = new VBox();
        tableBox.getChildren().addAll(table);




        borderPane.setLeft(tableBox);
        Scene scene = new Scene(borderPane, App.getMainstage().getWidth(),App.getMainstage().getHeight());


        return scene;
    }

    public static void show(Stage mainStage){
        Scene scene = getConfigScene();

        mainStage.setScene(getConfigScene());
    }
}
