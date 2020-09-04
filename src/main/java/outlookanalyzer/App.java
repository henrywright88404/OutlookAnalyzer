package outlookanalyzer;

import com.microsoft.graph.models.extensions.User;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import org.json.JSONObject;
import outlookanalyzer.Utils.JSONUtil;
import outlookanalyzer.models.user;
import outlookanalyzer.outlookconnection.Authentication;
import outlookanalyzer.outlookconnection.SimpleAuthProvider;
import outlookanalyzer.scene.ConfigScene;
import outlookanalyzer.scene.MainWindow;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {


    private static User user;

    private static Stage mainstage;
    public static SimpleAuthProvider simpleAuthProvider = new SimpleAuthProvider();
    public static ConfigScene configScene = new ConfigScene();
    public static MainWindow mainWindow = new MainWindow();

    @Override
    public void start(Stage stage) throws IOException, XmlPullParserException {


        Authentication.initialize();

        mainstage = stage;
        Scene scene = mainWindow.getMainWindow(stage);

        stage.setMaximized(true);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public static Stage getMainstage() {
        return mainstage;
    }

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        App.user = user;
    }
}