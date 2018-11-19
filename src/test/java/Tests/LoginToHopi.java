package Tests;

import Action.ActionUtils;
import Action.FindElements;
import ElementUtils.Elements;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

/**
 * Created by bkaratepe on 6/26/18.
 */
public class LoginToHopi {
    public static void login(ActionUtils actionUtils, FindElements findElements, AppiumDriver driver, String loginNumber, boolean firstLogin) throws InterruptedException {
        System.out.println("-----------------Login scenario is started-----------------");

        actionUtils.wait(5000);

        actionUtils.alertActionStatus(driver, true, firstLogin);
        actionUtils.clickElement(findElements.findByX(Elements.ZATEN_HOPILIYIM_BUTTON.getSelector(driver)));
        actionUtils.wait(1000);
        actionUtils.clearElement(findElements.findByX(Elements.MSSIDN_TEL_NUMBER_PIN.getSelector(driver)));
        actionUtils.setElement(findElements.findByX(Elements.MSSIDN_TEL_NUMBER_PIN.getSelector(driver)), loginNumber);
        actionUtils.clickElement(findElements.findByX(Elements.MSSIDN_CONTIUNE_BUTTON.getSelector(driver)));
        if (driver instanceof AndroidDriver) {
            actionUtils.alertActionStatus(driver, true, firstLogin);
        }
        actionUtils.insertOTPFromGenerator(findElements.findByX(Elements.LOGIN_PAGE_OTP_PIN.getSelector(driver)), loginNumber);
        actionUtils.clickElement(findElements.findByX(Elements.LOGIN_PAGE_LOGIN_BUTTON.getSelector(driver)));
        actionUtils.wait(10000);
        actionUtils.alertActionStatus(driver, true, firstLogin);
        actionUtils.alertActionStatus(driver, true, firstLogin);
        actionUtils.elementExists(findElements.findByX(Elements.RIVER_HOPI_ICON.getSelector(driver)), true);


    }
}
