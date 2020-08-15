package outlookanalyzer.scene;

import ch.qos.logback.classic.Logger;
import javafx.scene.control.Alert;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignInLink {
    private static final Logger log = (Logger) LoggerFactory.getLogger(SignInLink.class);


    private static String linkAndCode;

    public  String call() throws Exception {
        createSignInLinkScene();
        return null;
    }

    private void createSignInLinkScene() {


        Label toSignInMessage = new Label("To sign in, use a web browser to open the page ");
        Hyperlink hyperlink = getHyperlink();
        Label andEnterTheCode = new Label("and enter the code ");
        Label code = getCode();
        Label toAuthenticate = new Label(andEnterTheCode.getText()+code.getText() +" to Authenticate (Automatically copied to the clipboard). Once Signed in please close this dialog");



        Alert alert = new Alert(
                Alert.AlertType.INFORMATION,
                "Authentication required");
        alert.setHeaderText("Authentication required");

        FlowPane fp = new FlowPane();
        fp.getChildren().addAll( toSignInMessage,hyperlink,toAuthenticate);

        hyperlink.setOnAction( (evt) -> {
            StringSelection stringSelection = new StringSelection(code.getText());
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(stringSelection, null);
            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                try {
                    Desktop.getDesktop().browse(new URI(hyperlink.getText()));
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
            }
        } );

        alert.getDialogPane().contentProperty().set( fp );

        alert.showAndWait();


//        HBox hBox = new HBox();
//        hBox.getChildren().addAll(toSignInMessage,hyperlink,andEnterTheCode,code,toAuthenticate);
//
//

    }


    private static Hyperlink getHyperlink(){
        Pattern pattern =  Pattern.compile("(https:\\/\\/)[A-Za-z./]*");
        Matcher matcher = pattern.matcher(linkAndCode);
        boolean matchFound = matcher.find();

        Hyperlink hyperlink = new Hyperlink();

        if(matchFound) {
            return new Hyperlink(matcher.group(0));
        } else {
            return new Hyperlink("ERROR: NO LINK");
        }
    }

    private static Label getCode(){
        Pattern pattern =  Pattern.compile("(?<=code )(.*)(?= to)");
        Matcher matcher = pattern.matcher(linkAndCode);
        boolean matchFound = matcher.find();
        if(matchFound) {
            return new Label(matcher.group(0));
        } else {
            return new Label("ERROR: NO CODE");
        }
    }
    public static String getLinkAndCode() {
        return linkAndCode;
    }

    public static void setLinkAndCode(String linkAndCode) {
        SignInLink.linkAndCode = linkAndCode;
    }
}
