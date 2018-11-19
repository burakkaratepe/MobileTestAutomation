package Tests;

import Action.ActionUtils;
import Action.FindElements;
import ElementUtils.Elements;
import io.appium.java_client.AppiumDriver;

/**
 * Created by bkaratepe on 6/26/18.
 */
public class LogoutFromHopi {
    public static void logout(ActionUtils actionUtils, FindElements findElements, AppiumDriver driver) throws InterruptedException {
        System.out.println("-----------------Logout scnario is started!------------------");
        actionUtils.clickElement(findElements.findByX(Elements.TABBAR_PROFILE.getSelector(driver)));
        actionUtils.wait(1000);
        actionUtils.elementExists(findElements.findByX(Elements.PROFILE_SETTINGS_BUTTON.getSelector(driver)), true);
        actionUtils.clickElement(findElements.findByX(Elements.PROFILE_SETTINGS_BUTTON.getSelector(driver)));
        actionUtils.clickElement(findElements.findByX(Elements.SETTINGS_LOGOUT_BUTTON.getSelector(driver)));
        actionUtils.clickElement(findElements.findByX(Elements.LOGOUT_REASON_POPUP_FIRST_ANSWER.getSelector(driver)));
        actionUtils.clickElement(findElements.findByX(Elements.LOGOUT_REASON_POPUP_LOGOUT_BUTTON.getSelector(driver)));
        actionUtils.elementExists(findElements.findByX(Elements.ZATEN_HOPILIYIM_BUTTON.getSelector(driver)), true);
        actionUtils.wait(5000);
        actionUtils.takeScreenShot(driver);
    }
}
