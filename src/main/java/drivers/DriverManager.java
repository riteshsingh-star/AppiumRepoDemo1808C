package drivers;

import Utils.JsonUtils;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.apache.log4j.BasicConfigurator;
import org.testng.annotations.AfterTest;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DriverManager {

    private static AndroidDriver driver;

    private static List<Map<String, String>> testData;
    static Map<String, String> data;
    static Map<String, String> data2;

    public static void initializeDriver() {

        testData = JsonUtils.getJsonArrayFromJsonFile("Capabilities.json");
        data = testData.get(0);
        data2 = testData.get(1);
        String testType = data.get("testType");
        BasicConfigurator.configure();
        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName(data.get("platformName"));
        options.setDeviceName(data.get("deviceName"));
        options.setAutomationName(data.get("automationName"));
        try {
            if (testType.equalsIgnoreCase("Apk")) {
                File appDir = new File(data.get("FilePath"));
                File app = new File(appDir, data.get("AppName") + ".apk");
                options.setCapability("app", app.getAbsolutePath());
            } else if (testType.equalsIgnoreCase("App")) {
                BasicConfigurator.configure();
                options.setCapability("appPackage", data.get("AppPackage"));
                options.setCapability("appActivity", data.get("AppActivity"));
            } else if (testType.equalsIgnoreCase("Browser")) {
                options.setCapability("browserName", data.get("BrowserName"));
            } else if (testType.equalsIgnoreCase("Cloud")) {
                HashMap<String, Object> browserStackOptions = new HashMap<>();
                browserStackOptions.put("userName", "codecraft1");
                browserStackOptions.put("accessKey", "jxLRH9wEpQpgP2zrKptA");
                options.setPlatformVersion(data.get("platformVersion"));
                options.setCapability("app", data.get("AppUrl"));
                options.setCapability("bstack:options", browserStackOptions);
                driver = new AndroidDriver(new URL("https://hub.browserstack.com/wd/hub"), options);

            }
            // driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }


    public static AndroidDriver getDriver() {
        return driver;
    }

    @AfterTest
    public static void tearDown() {

        driver.quit();
    }
}
