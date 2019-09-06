import Action.ActionUtils;
import Action.FindElements;
import Action.DriverSetUp;
import io.appium.java_client.AppiumDriver;
import org.testng.annotations.*;

import java.net.MalformedURLException;

import static Action.DriverSetUp.setUpAppium;
import static Tests.LoginToHopi.login;
import static Tests.LogoutFromHopi.logout;

/**
 * Created by bkaratepe on 6/22/18.
 */
public class Main {

    private static final String URL = "http://" + "127.0.0.1" + ":4723/wd/hub"; //156 for iOS
    private static AppiumDriver driver;
    private static FindElements findElements;
    private final ActionUtils actionUtils = new ActionUtils();
    private final static String platformName = DriverSetUp.setDevicePlatformName();
    private final static String appPackage = "AppPackage";
    private final static String appActivity = "AppActivity";

    @BeforeSuite
    private void setUp() throws MalformedURLException {

        driver = setUpAppium("Android", URL, "Galaxy S9", appPackage, appActivity);
        System.out.println("Test starting...");

    }

    @Test
    public void tesRun() throws InterruptedException {

        findElements = new FindElements(driver);
        System.out.println("Test is running...");
        String loginNumber = "MssidnNo";
        login(actionUtils, findElements, driver, loginNumber, false);  //Login
        logout(actionUtils, findElements, driver);  //Logout

        Thread.sleep(1000);

    }

    @AfterSuite
    private void afterTest() {
        System.out.println("Test finished.");
        driver.quit();

    }
}
