package outlookanalyzer.models;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.json.JSONObject;

import java.time.LocalDate;
import java.util.Date;

public class user {
    String userName = "";
    String emailAddress = "";
    String team = "";
    JSONObject history = new JSONObject();

    public user(String userName, String emailAddress) {
        this.userName = userName;
        this.emailAddress = emailAddress;
    }
    public user(String emailAddress) {
        this.userName = emailAddress.split("@")[0];
        this.emailAddress = emailAddress;
    }
    public user() {
    }

    public JSONObject getInbox() {
        return history;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }


    public void addCount(LocalDate date, String category, int count){
        if(history.has(date.toString())){
            JSONObject countOnDate = history.getJSONObject(date.toString());
            countOnDate.put(category,count);
        }else{
            JSONObject categoryCountObject = new JSONObject();
            categoryCountObject.put(category,count);
            history.put(date.toString(),categoryCountObject);
        }
    }

    public JSONObject getHistory(){
        return history;
    }

    public JSONObject getHistory(Date onDate){
        return history.getJSONObject(onDate.toString());
    }




    public String toJson() {
        ObjectWriter ow = new ObjectMapper().writer().forType(this.getClass());

        try {
            return ow.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }

    }
}
