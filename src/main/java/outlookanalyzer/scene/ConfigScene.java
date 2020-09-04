package outlookanalyzer.scene;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.json.JSONObject;
import outlookanalyzer.App;
import outlookanalyzer.Utils.JSONUtil;
import outlookanalyzer.outlookconnection.Graph;
import outlookanalyzer.outlookconnection.Mail;

public class ConfigScene {


    public static Scene getConfigScene(){

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(new Label("Welcome " + App.getUser().displayName));

        TableView table = new TableView();
        TableColumn firstNameCol = new TableColumn("Inbox");
        TableColumn lastNameCol = new TableColumn("Team");

        JSONObject mailboxes = JSONUtil.readJsonFile("mailboxes");
//        mailboxes.toMap().forEach(mailbox -> firstNameCol.);

        table.setEditable(false);
        table.setPlaceholder(new Label("My table is empty"));
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
