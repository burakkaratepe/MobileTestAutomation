package ElementUtils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

/**
 * Created by bkaratepe on 6/22/18.
 * <p>
 * <p>
 * DECLERATION EXPAMLES
 * <p>
 * xpath -> Variable("Androidselector/xpath" , "iOSselector/xpath")
 * id -> Variable("Androidselector/id" , "iOSselector/id")
 * text -> Variable("Androidselector/text" , "iOSselector/text")
 * classname -> Variable("Androidselector/classname" , "iOSselector/classname")
 */

public enum Elements {
    ZATEN_HOPILIYIM_BUTTON("Zaten Hopiliyim/text", "btn_zaten_hopiliyim/id"),
    MSSIDN_TEL_NUMBER_PIN("request_otp_hopi_edit_text_phone/id", "textfield_phonenumber/id"),
    MSSIDN_CONTIUNE_BUTTON("request_otp_hopi_button_send/id", "button_send/id"),
    LOGIN_PAGE_OTP_PIN("editText1/id", "view_password/id"),
    LOGIN_PAGE_LOGIN_BUTTON("enter_otp_hopi_button_continue/id", "button_send/id"),
    RIVER_HOPI_ICON("search_box_home_image_view_hopi_icon/id", "hopilogo/id"),
    TABBAR_PROFILE("home_imageview_profile/id", "XCUIElementTypeTabBar/XCUIElementTypeButton[5]/xpath"),
    PROFILE_SETTINGS_BUTTON("home_imageview_header_right_button/id", "Settings/id"),
    SETTINGS_LOGOUT_BUTTON("android.widget.TextView[contains(@text, 'Yap')]/xpath", "*[contains(@label, 'ÇIKIŞ YAP')]/xpath"),
    LOGOUT_REASON_POPUP_FIRST_ANSWER("android.widget.RadioButton[1]/xpath", "XCUIElementTypeCell[1]/*[contains(@name,'.')]/xpath"),
    LOGOUT_REASON_POPUP_LOGOUT_BUTTON("text_view_logout/id", "XCUIElementTypeOther/XCUIElementTypeButton[@name='ÇIKIŞ YAP']/xpath");

    private String androidId, iOSId;

    Elements(String androidId, String iOSId) {
        this.androidId = androidId;
        this.iOSId = iOSId;
    }

    public String getSelector(AppiumDriver driver) {
        return driver == null ? "" : driver instanceof AndroidDriver ? androidId : iOSId;
    }
}