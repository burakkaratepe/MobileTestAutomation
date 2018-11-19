package Action;

import Constants.ErrorLogs;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * Created by bkaratepe on 6/28/18.
 */
public class DriverSetUp {
//    public static String getIpAddress() {
//        try {
//            Document doc = Jsoup.connect("http://checkip.amazonaws.com/").get();
//            return doc.text();
//        } catch (Exception e) {
//            System.out.println("Cannot reach ip address");
//            return null;
//        }
//    }

    public static String setDevicePlatformName() {
        return (System.getProperty("os.name")).startsWith("Windows") || (System.getProperty("os.name")).startsWith("Linux") ? "Android" : "iOS";
    }

    public static AppiumDriver setUpAppium(String platformName, String URL, String deviceName, String appPackage, String appActivity) throws MalformedURLException {

        AppiumDriver driver;
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("noReset", true);
        capabilities.setCapability("deviceName", deviceName);
        capabilities.setCapability("platformName", platformName);
        try {
            if (platformName.equals("Android")) {
                capabilities.setCapability("appPackage", appPackage);
                capabilities.setCapability("appActivity", appActivity);
                driver = new AndroidDriver(new URL(URL), capabilities);
            } else {
                capabilities.setCapability("automationName", "XCUITest");
                capabilities.setCapability("app", appPackage);
                capabilities.setCapability("UDID", "XX");
                driver = new IOSDriver(new URL(URL), capabilities);
            }
            int impilicityWaitTimeSeconds = 20;
            driver.manage().timeouts().implicitlyWait(impilicityWaitTimeSeconds, TimeUnit.SECONDS);
            return driver;
        } catch (Exception e) {
            System.out.println(appiumSetUpError(e));
            System.exit(1);
        }
        return null;
    }

    private static String appiumSetUpError(Exception e) {
        String message = "";
        if (e.getMessage().contains(ErrorLogs.WRONG_APP.getAndroidError()) || e.getMessage().contains(ErrorLogs.WRONG_APP.getiOSError())) {
            message = ErrorLogs.WRONG_APP.getUserError();
        } else if (e.getMessage().contains(ErrorLogs.NO_DEVICE.getAndroidError()) || e.getMessage().contains(ErrorLogs.NO_DEVICE.getiOSError())) {
            message = ErrorLogs.NO_DEVICE.getUserError();
        } else if (e.getMessage().contains(ErrorLogs.NO_INTERNET_CONNECTION.getAndroidError()) || e.getMessage().contains(ErrorLogs.NO_INTERNET_CONNECTION.getiOSError())) {
            message = ErrorLogs.NO_INTERNET_CONNECTION.getUserError();
        } else if (e.getMessage().contains(ErrorLogs.APP_NOT_INSTALLED.getAndroidError()) || e.getMessage().contains(ErrorLogs.APP_NOT_INSTALLED.getiOSError())) {
            message = ErrorLogs.APP_NOT_INSTALLED.getUserError();
        } else if (e.getMessage().contains(ErrorLogs.WEBDRIVER_AGENT_FAILURE.getiOSError())) {
            message = ErrorLogs.WEBDRIVER_AGENT_FAILURE.getUserError();
        }
        return message;
    }

    public static void getDeviceNameProperty(String platformName){
                String s;
                String command;
                Process p;
                try {
                    command = platformName.equals("Android") ? "adb shell getprop ro.product.model" : "instruments -s devices";
                    p = Runtime.getRuntime().exec(command);
                    BufferedReader br = new BufferedReader(
                            new InputStreamReader(p.getInputStream()));
                    while ((s = br.readLine()) != null)
                        System.out.println("line: " + s);
                    p.waitFor();
                    System.out.println ("exit: " + p.exitValue());
                    p.destroy();
                } catch (Exception e) {}
        }
}
