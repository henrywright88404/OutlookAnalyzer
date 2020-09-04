package outlookanalyzer.scene;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import outlookanalyzer.App;
import outlookanalyzer.outlookconnection.Authentication;
import outlookanalyzer.outlookconnection.Graph;
import outlookanalyzer.outlookconnection.SimpleAuthProvider;
import outlookanalyzer.sysInfo.SystemInfo;

public class MainWindow {
    public static Scene getMainWindow(Stage mainStage) {

    // labels and system info

        HBox systemInfo = new HBox(SystemInfo.getSystemInfoLabel());
        systemInfo.alignmentProperty().setValue(Pos.BOTTOM_RIGHT);

        Button login = new Button("Login");

        EventHandler<ActionEvent> loginButtonEvent = (actionEvent) -> {
            Authentication.getUserAccessToken();
            App.setUser(Graph.getUser(SimpleAuthProvider.getAccessToken()));
            if(App.getUser() == null){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("There was an error logging in please try again");
            } else {
                App.configScene.show(mainStage);
            }
        };

        login.setOnAction(loginButtonEvent);





        //login button

        VBox loginContainer = new VBox();
        loginContainer.setAlignment(Pos.CENTER);
        loginContainer.getChildren().addAll(login);

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(loginContainer);
        borderPane.setBottom(systemInfo);


        Scene scene = new Scene(borderPane);

        return scene;
    }

}
