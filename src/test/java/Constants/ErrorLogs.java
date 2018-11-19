package Constants;

/**
 * Created by bkaratepe on 7/2/18.
 */
public enum ErrorLogs {
    WRONG_APP("", "Bad app:", "App couldn't find in the computer...........................!"),
    APP_NOT_INSTALLED("Activity used to start app doesn't exist or cannot be launched", "App with bundle", "App couldn't find in the device......................!"),
    NO_DEVICE("Could not find a connected", "Unknown device or", "Test device didn't intstalled to the computer...................!"),
    NO_INTERNET_CONNECTION("", "", "Computer doesn't have internet connection or no test(s) in queue........................:!"),
    WEBDRIVER_AGENT_FAILURE("", "Unable to launch WebDriverAgent", "Coundn't start WebDriverAgent, please re-connect device...........................!");

    private String androidError, iOSError, userError;

    ErrorLogs(String androidError, String iOSError, String userError) {
        this.androidError = androidError;
        this.iOSError = iOSError;
        this.userError = userError;
    }

    public String getAndroidError() {
        return androidError;
    }

    public String getiOSError() {
        return iOSError;
    }

    public String getUserError() {
        return userError;
    }

}

