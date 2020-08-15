package outlookanalyzer.sysInfo;

import javafx.scene.control.Label;
import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;

import java.io.FileReader;
import java.io.IOException;

public class SystemInfo {

    public static String javaVersion() {
        return System.getProperty("java.version");
    }

    public static String javafxVersion() {
        return System.getProperty("javafx.version");
    }

    public static String getAnalyzerVersion() {
        MavenXpp3Reader reader = new MavenXpp3Reader();
        Model model = null;
        try {
            model = reader.read(new FileReader("pom.xml"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        return model.getVersion();
    }

    public static Label getSystemInfoLabel(){

        String javaVersion = SystemInfo.javaVersion();
        String javafxVersion = SystemInfo.javafxVersion();
        String outlookAnalyzerVersion = SystemInfo.getAnalyzerVersion();

        return new Label(" JavaFX Version: " + javafxVersion + "\n Java Version: " + javaVersion + "\n Outlook Analyzer Version: "+outlookAnalyzerVersion + ".");


    }
}