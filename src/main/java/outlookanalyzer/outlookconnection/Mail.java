package outlookanalyzer.outlookconnection;

import com.microsoft.graph.models.extensions.Message;
import com.microsoft.graph.options.Option;
import com.microsoft.graph.options.QueryOption;
import com.microsoft.graph.requests.extensions.IMessageCollectionPage;

import java.util.LinkedList;
import java.util.List;

public class Mail extends Graph implements MailOptions{

    public static double getNumberOfMessagesfromInbox(String mailbox){
        ensureGraphClient(SimpleAuthProvider.getAccessToken());
        List<Option> options = createOptionsList(returnCountTrue,orderByCreatedDateTimeDecending);
        IMessageCollectionPage messageCollectionPage;

        if(mailbox == null || mailbox.isEmpty()) {
            messageCollectionPage = graphClient
                    .me()
                    .mailFolders("inbox")
                    .messages()
                    .buildRequest(options).top(10)
                    .get();
        } else {
            messageCollectionPage = graphClient
                    .users(mailbox)
                    .mailFolders("inbox")
                    .messages()
                    .buildRequest(options).top(10)
                    .get();
        }

        return messageCollectionPage.getRawObject().get("count").getAsDouble();
    }

    public static void getMessagesFromMailBox(String mailbox){

        ensureGraphClient(SimpleAuthProvider.getAccessToken());

        List<Option> options = createOptionsList(returnCountTrue,orderByCreatedDateTimeDecending);

        IMessageCollectionPage messageCollectionPage = graphClient
                .users(mailbox)
                .mailFolders("inbox")
                .messages()
                .buildRequest(options).top(10)
                .get();


        List<Message> messageList = messageCollectionPage.getCurrentPage();

        for (Message e:messageList
        ) {
            System.out.println(e.subject + " " +e.categories);

        }

    }

    public static void getMessagesFromMe(){
        ensureGraphClient(SimpleAuthProvider.getAccessToken());
        // Use QueryOption to specify the $orderby query parameter
        List<Option> options = createOptionsList(returnCountTrue,orderByCreatedDateTimeDecending);

        IMessageCollectionPage messageCollectionPage = graphClient
                .me()
                .mailFolders("inbox")
                .messages()
                .buildRequest(options).top(10)
                .get();


        List<Message> messageList = messageCollectionPage.getCurrentPage();

        for (Message e:messageList
        ) {
            System.out.println(e.subject + " " +e.categories);

        }
    }




    private static List<Option> createOptionsList(QueryOption ... options ){
        List<Option> returnOptions  = new LinkedList<Option>();

        for( Option option : options){
            returnOptions.add(option);
        }
        return returnOptions;
    }
}
