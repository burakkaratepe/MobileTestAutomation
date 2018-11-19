package Action;

import ElementUtils.Elements;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.Objects;

/**
 * Created by bkaratepe on 6/25/18.
 */
public class FindElements {

    private String getEnumNameByString(String elementPath) {
        for (Elements e : Elements.values()) {
            if (Objects.equals(elementPath, e.getSelector(driver))) return e.name();
        }
        return null;
    }

    private AppiumDriver driver;

    public WebElement findByX(String Path) {
        String elementName = getEnumNameByString(Path);
        String findType = Path.substring(Path.lastIndexOf("/")).replace("/", "");
        String accessString = Path.substring(0, Path.lastIndexOf("/"));
        System.out.println("Trying to select " + elementName + " by using " + findType + "...");
        try {
            switch (findType) {
                case "xpath":
                    return driver.findElementByXPath("//" + accessString);
                case "text":
                    return driver instanceof AndroidDriver ? driver.findElement(By.xpath("//*[contains(@text, '" + accessString + "')]")) : driver.findElement(By.xpath("//*[contains(@label, '" + accessString + "')] | //*[contains(@name, '" + accessString + "')] "));
                case "classname":
                    return driver.findElementByClassName(accessString);
                default:
                    return driver.findElementById(accessString);
            }
        } catch (Exception e) {
            return null;

        }
    }

    public FindElements(AppiumDriver driver) {
        this.driver = driver;
    }
}