package outlookanalyzer.outlookconnection;

import ch.qos.logback.classic.Logger;
import com.microsoft.graph.authentication.IAuthenticationProvider;
import com.microsoft.graph.http.IHttpRequest;
import org.slf4j.LoggerFactory;

import java.security.AuthProvider;

/**
 * SimpleAuthProvider
 */
public class SimpleAuthProvider implements IAuthenticationProvider {

    private static Logger log = (Logger) LoggerFactory.getLogger(SimpleAuthProvider.class);
    private static String accessToken;

    public SimpleAuthProvider() {
        log.info("No Access token provided.");
    }

    public SimpleAuthProvider(String accessToken) {
        SimpleAuthProvider.accessToken = accessToken;
    }

    public static void setAccessToken(String accessToken) {
        SimpleAuthProvider.accessToken = accessToken;
    }

    public static String getAccessToken() {
        return accessToken;
    }

    @Override
    public void authenticateRequest(IHttpRequest request) {

        if(accessToken == null){
            log.error("No Access token. Please use Authentication.initialize() and Authentication.getUserAccessToken()");
        }
        // Add the access token in the Authorization header
        request.addHeader("Authorization", "Bearer " + accessToken);
    }
}
