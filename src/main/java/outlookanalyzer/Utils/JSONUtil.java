package outlookanalyzer.Utils;

import ch.qos.logback.classic.Logger;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.slf4j.LoggerFactory;
import outlookanalyzer.models.user;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class JSONUtil {
    private static final Logger log = (Logger) LoggerFactory.getLogger(JSONUtil.class);
    private static final Path resourceDirectory  = Paths.get("src","main","resources");

    public static JSONObject readJsonFile(String filename){
       return readJsonFile(filename,false);
    }

    public static JSONObject readJsonFile(String filename, boolean createIfNotExists){
        JSONObject data = null;

        filename = filename.contains(".json") ? filename : filename+".json" ;

        InputStream is = JSONUtil.class.getClassLoader().getResourceAsStream(filename);

        try{
            if (is == null) {
                if(createIfNotExists){
                    log.info("No file called "+ filename +" exists. Creating file as instructed by createIfNotExists parameter");
                    writeJSONObject(new JSONObject(),filename);
                }else {
                    log.info("No file called " + filename + " exists. createIfNotExists parameter is false. returning null");
                    return null;
                }
            }else {
                //JSON parser object to parse read file
                JSONTokener tokener = new JSONTokener(is);
                data = new JSONObject(tokener);
            }

        }catch (Exception e){
            log.error(e.toString());
            throw e;
        }

        return data;
    }

    public static boolean writeJSONObject(JSONObject data, String fileName){
        boolean result = true;
        fileName = fileName.contains(".json") ? fileName : fileName+".json" ;

        File tmpFile = new File(resourceDirectory.toFile().getAbsolutePath()+"\\"+fileName);

        log.info("Path to "+ fileName +" is " +tmpFile.getAbsolutePath());
        if(!tmpFile.exists()){
            try {
                tmpFile.createNewFile();
            } catch (IOException e) {
                log.error(e.toString());
            }
        }


        try (BufferedWriter writer = Files
                                        .newBufferedWriter(Path
                                            .of(tmpFile.getPath()
                                            )
                                        )
            )
        {
            data.write(writer, 2, 0);
        } catch (Exception ex) {
            log.error(String.valueOf(ex));
            result = false;
        }

        return result;
    }

public static List<user> getMailboxes(String mailboxJsonFile) throws Exception {
        List<user> userList = new ArrayList<>();

        JSONObject mailBoxesObject = readJsonFile(mailboxJsonFile);
        JSONArray mailBoxesArray = mailBoxesObject.getJSONArray("user");

    for (Object mailBox : mailBoxesArray) {
        try {
            userList.add(new ObjectMapper().readValue(mailBox.toString(), user.class));
        } catch (JsonProcessingException e) {
            log.error("userMapping error" + e);
            throw e;
        }
    }

    return userList;
    }
}
