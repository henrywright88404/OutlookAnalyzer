package outlookanalyzer.models;

import com.microsoft.graph.models.extensions.Message;

import java.util.ArrayList;
import java.util.List;

public class Inbox {

    private List<Message> messages = new ArrayList<>();

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public void addMessage(Message message){
        this.messages.add(message);
    }

    public List<Message> getMessages() {
        return messages;
    }


}
