package outlookanalyzer.scene.Layouts;

import ch.qos.logback.classic.Logger;
import com.microsoft.graph.models.extensions.Event;
import com.microsoft.graph.models.extensions.User;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.slf4j.LoggerFactory;
import outlookanalyzer.outlookconnection.Graph;
import outlookanalyzer.outlookconnection.SimpleAuthProvider;

public class testStage {
    public static Logger log= (Logger)LoggerFactory.getLogger(testStage.class);
    public void setNewTestStage(Stage mainStage){
        User user = Graph.getUser(SimpleAuthProvider.getAccessToken());


        VBox box = new VBox();
        box.getChildren().add(new Label("Welcome " + user.displayName));
        log.info("Welcome " + user.displayName);

        for (Event event: Graph.getEvents(SimpleAuthProvider.getAccessToken())
             ) {
            log.info(event.subject + " " + event.organizer + " " + event.start.toString() + " " + event.end.toString());
            box.getChildren().add(new Label(event.subject + " " + event.organizer + " " + event.start.toString() + " " + event.end.toString()));
        }

        Scene scene = new Scene(box);

        mainStage.setScene(scene);


    }
}
