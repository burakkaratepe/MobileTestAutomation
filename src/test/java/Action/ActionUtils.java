package Action;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by bkaratepe on 6/22/18.
 */
public class ActionUtils {
    private String nOKText = " ---------------------------->NOK!";
    private long time;

    public static HashMap<String, String> otpUrls = new HashMap<String, String>() {
        {
            put("Mssidn1", "OtpGeneratorLink1");
            put("Mssidn2", "OtpGeneratorLink2");
        }
    };

    public void insertOTPFromGenerator(WebElement e, String number) {
        try {
            time = System.currentTimeMillis();
            Document doc = Jsoup.connect(otpUrls.get(number)).get();
            e.sendKeys(doc.getElementById("otp").text());
            System.out.print("OTP for " + number + " is inserted");
            calculateTime(time);
        } catch (Exception exception) {
            System.out.println("Something went wrong with otp" + nOKText);
        }
    }

    public void clickElement(WebElement e) {
        try {
            time = System.currentTimeMillis();
            e.click();
            System.out.print("Clicked element");
            calculateTime(time);
        } catch (Exception ex) {
            System.out.println(ex.getMessage() + nOKText);
        }

    }

    public void clearElement(WebElement e) {
        try {
            e.clear();
            System.out.print("Cleared element");
            calculateTime(time);
        } catch (Exception ex) {
            System.out.println(ex.getMessage() + nOKText);
        }

    }

    public void setElement(WebElement e, String text) {
        try {
            time = System.currentTimeMillis();
            e.sendKeys(text);
            System.out.print("Edited element");
            calculateTime(time);
        } catch (Exception ex) {
            System.out.println(ex.getMessage() + nOKText);
        }

    }

    public void alertAction(AppiumDriver driver, boolean accept) {
        try {
            if (!(driver instanceof AndroidDriver)) {
                new WebDriverWait(driver, 10).until(ExpectedConditions.alertIsPresent());
                if (accept) {
                    driver.switchTo().alert().accept();
                    System.out.println("Permission id accepted.");
                } else {
                    driver.switchTo().alert().dismiss();
                    System.out.println("Permission is dismissed.");
                }
            } else if (accept) {
                driver.findElement(By.id("com.android.packageinstaller:id/permission_allow_button")).click();
                System.out.println("Permission is accepted.");
            } else {
                driver.findElement(By.id("com.android.packageinstaller:id/permission_deny_button")).click();
                System.out.println("Permission is dismissed.");
            }
        } catch (Exception noAlert) {
            System.out.println("No alert on screen!" + nOKText);
        }


    }

    public void elementExists(WebElement e, boolean exists) {
        try {
            time = System.currentTimeMillis();
            String result = e != null ? exists == true ? "Element is on screen...OK" : "Element is on screen...NOK!" : exists == false ? "No element on screen...OK" : "No element on screen...NOK!";
            System.out.print(result);
            calculateTime(time);
        } catch (Exception ex) {
            System.out.println(ex.getMessage() + nOKText);
        }

    }

    public void wait(int miliSeconds) {
        try {
            time = System.currentTimeMillis();
            Thread.sleep(miliSeconds);
            System.out.print("Waited");
            calculateTime(time);
        } catch (Exception ex) {
            System.out.println(ex.getMessage() + nOKText);
        }

    }

    public boolean takeScreenShot(AppiumDriver driver) {
        try {
            time = System.currentTimeMillis();
            System.out.print("Taking screenshot...");
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss");
            Date date = new Date();
            String screenshotDirectory = System.getProperty("appium.screenshots.dir", System.getProperty("java.io.tmpdir", ""));
            File screenshot = driver.getScreenshotAs(OutputType.FILE);
            calculateTime(time);
            return screenshot.renameTo(new File(screenshotDirectory, String.format("%s.png", formatter.format(date))));
        } catch (Exception ex) {
            System.out.println(ex.getMessage() + nOKText);
        }
        return Boolean.parseBoolean(null);
    }

    public void alertActionStatus(AppiumDriver driver, boolean isAccept, boolean seen) {
        String deviceVersion = driver instanceof AndroidDriver ? driver.getCapabilities().getCapability("platformVersion").toString().substring(0, 1) : "";
        if (seen && (Integer.parseInt(deviceVersion) >= 6 || deviceVersion.isEmpty())) {
            alertAction(driver, isAccept);
        } else if (deviceVersion.isEmpty() || Integer.parseInt(deviceVersion) > 5) {
            System.out.println("Permission is skipping because seen before...");
        } else {
            System.out.println("Permission is skipping because OS version is lower than 6(" + deviceVersion + ")");
        }
    }

    private void calculateTime(long time) {
        System.out.println(" (" + (System.currentTimeMillis() - time) + " ms)");

    }


}
