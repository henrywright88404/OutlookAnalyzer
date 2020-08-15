package outlookanalyzer.outlookconnection;

import ch.qos.logback.classic.Logger;
import com.microsoft.aad.msal4j.DeviceCode;
import com.microsoft.aad.msal4j.DeviceCodeFlowParameters;
import com.microsoft.aad.msal4j.IAuthenticationResult;
import com.microsoft.aad.msal4j.PublicClientApplication;
import org.slf4j.LoggerFactory;
import outlookanalyzer.App;
import outlookanalyzer.scene.SignInLink;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;

/**
 * Authentication
 */
public class Authentication {

    private static final Logger log = (Logger) LoggerFactory.getLogger(Authentication.class);
    private static final Properties oAuthProperties = new Properties();
    private static String[] appScopes;
    private static String applicationId;
    // Set authority to allow only organizational accounts
    // Device code flow only supports organizational accounts
    private final static String authority = "https://login.microsoftonline.com/common/";

    public static Authentication initialize() {

        loadProperties();

        applicationId = oAuthProperties.getProperty("app.id");
        appScopes = oAuthProperties.getProperty("app.scopes").split(",");

        return new Authentication();
    }

    public static String getUserAccessToken() {
        SignInLink signInLink = new SignInLink();

        if (applicationId == null) {
            log.error("You must initialize Authentication before calling getUserAccessToken");
            return null;
        }

        Set<String> scopeSet = Set.of(appScopes);

        ExecutorService pool = Executors.newFixedThreadPool(1);
        PublicClientApplication app;
        try {
            // Build the MSAL application object with
            // app ID and authority
            app = PublicClientApplication.builder(applicationId)
                    .authority(authority)
                    .executorService(pool)
                    .build();
        } catch (MalformedURLException e) {
            return null;
        }

        // Create consumer to receive the DeviceCode object
        // This method gets executed during the flow and provides
        // the URL the user logs into and the device code to enter
        AtomicReference<String> message = new AtomicReference<>();

        Consumer<DeviceCode> deviceCodeConsumer = (DeviceCode deviceCode) -> {
            message.set(deviceCode.message());
            log.info(deviceCode.message());
        };

        // Request a token, passing the requested permission scopes

        CompletableFuture<IAuthenticationResult> result = app.acquireToken(
                DeviceCodeFlowParameters
                        .builder(scopeSet, deviceCodeConsumer)
                        .build()
        ).exceptionally(ex -> {
            log.error("Unable to authenticate - " + ex.getMessage());
            return null;
        });

        log.debug("shutting down pool");

        long timeLimit = 5000;

        while(message.get() == null && timeLimit > 0){
            try {
                log.debug("Sleeping");
                Thread.sleep(100);
                timeLimit -= 100;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        try {
            signInLink.setLinkAndCode(message.get());
            signInLink.call();
        } catch (Exception e) {
            e.printStackTrace();
        }

        IAuthenticationResult result1 = result.join();

        pool.shutdown();

        if (result != null) {
            SimpleAuthProvider.setAccessToken(result1.accessToken());

            return result1.accessToken();
        }

        return null;
    }

    private static void loadProperties() {
        try {
            oAuthProperties.load(App.class.getClassLoader().getResourceAsStream("oAuth.properties"));
        } catch (IOException e) {
            log.error("Unable to read OAuth configuration. Make sure you have a properly formatted oAuth.properties file. See README for details.");
            return;
        }

    }
}