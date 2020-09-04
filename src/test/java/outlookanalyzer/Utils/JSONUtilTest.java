package outlookanalyzer.Utils;

import ch.qos.logback.classic.Logger;
import com.microsoft.graph.models.extensions.User;
import org.json.JSONObject;
import org.junit.jupiter.api.Order;
import org.slf4j.LoggerFactory;
import outlookanalyzer.models.user;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JSONUtilTest {

    private static final Logger log = (Logger) LoggerFactory.getLogger(JSONUtilTest.class);
    private static final String TESTJSONFILE = "testJSON";
    JSONObject mailboxes = new JSONObject();
    user user1;
    user user2;




    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        log.debug("======== setting up =========");

        mailboxes = new JSONObject();
        user1 = new user("marc.henrywright@ando.co.nz");
        user2 = new user("sarah.moore@ando.co.nz");
        user1.setTeam("Property");
        user1.addCount(LocalDate.now(),"TCategory",95);
        user1.addCount(LocalDate.now(),"TeCategory",75);
        user1.addCount(LocalDate.now(),"TesCategory",5);
        user1.addCount(LocalDate.now(),"TestCategory",1);

        user2.setTeam("Motor");
        user2.addCount(LocalDate.now(),"TCategory1",95);
        user2.addCount(LocalDate.now(),"TeCategory2",75);
        user2.addCount(LocalDate.now(),"TesCategory3",5);
        user2.addCount(LocalDate.now(),"TestCategory4",1);

        mailboxes.append("user",user1.toJson());
        mailboxes.append("user",user2.toJson());




    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
        mailboxes = new JSONObject();
    }




    @Order(1)
    @org.junit.jupiter.api.Test
    void writeJSONObject() {
        assertTrue(JSONUtil.writeJSONObject(mailboxes,"testJSON"),"testJSON file created");
    }

    @Order(2)
    @org.junit.jupiter.api.Test
    void readJsonFile() {
        JSONObject readValue = JSONUtil.readJsonFile("testJSON");

        for (int i = 0; i <= mailboxes.length() ; i++) {
            log.debug("Expected : " + mailboxes.getJSONArray("user").get(i));
            log.debug("Actual   : " + readValue.getJSONArray("user").get(i));
            assertEquals(mailboxes.getJSONArray("user").get(i),readValue.getJSONArray("user").get(i));
        }

    }


    @Order(3)
    @org.junit.jupiter.api.Test
    void getMailboxes() throws Exception {
       List<user> readUsers =  JSONUtil.getMailboxes("testJSON");

       assertNotNull(readUsers, "JSONUtil.getMailboxes() returned some objects and did not retun null");

        for (user u: readUsers) {
            boolean userNameMatches = u.getUserName().equals(user1.getUserName()) ? true : u.getUserName().equals(user2.getUserName());
            assertTrue(userNameMatches,"userName was read from JSON correctly and u.getUserName() contained correct information");

            boolean emailAddressMatches = u.getEmailAddress().equals(user1.getEmailAddress()) ? true : u.getEmailAddress().equals(user2.getUserName());
            assertTrue(emailAddressMatches,"u.getEmailAddress() was read from JSON correctly and u.getEmailAddress() contained correct information");

            boolean teamMatches = u.getTeam().equals(user1.getTeam()) ? true : u.getTeam().equals(user2.getTeam());
            assertTrue(teamMatches,"u.getTeam() was read from JSON correctly u.getTeam() contained correct information ");

            assert(u.getHistory().toString().equals(user1.getHistory().toString())||u.getHistory().toString().equals(user2.getHistory().toString()));
        }
    }
}