package drivers;

import Utils.AppiumLogDriverActions;
import Utils.JsonUtils;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.apache.log4j.BasicConfigurator;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DriverManager {

    // ThreadLocal for parallel execution
    private static ThreadLocal<AndroidDriver> driver = new ThreadLocal<>();

    private static List<Map<String, String>> testData;

    @BeforeTest(alwaysRun = true)
    @org.testng.annotations.Parameters({"deviceIndex"})
    public static void initializeDriver(int deviceIndex) {

        testData = JsonUtils.getJsonArrayFromJsonFile("Capabilities.json");
        Map<String, String> data = testData.get(deviceIndex);

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
                options.setCapability("appPackage", data.get("AppPackage"));
                options.setCapability("appActivity", data.get("AppActivity"));

            } else if (testType.equalsIgnoreCase("Browser")) {
                options.setCapability("browserName", data.get("BrowserName"));
                options.setCapability("appium:plugin:images", true);

            } else if (testType.equalsIgnoreCase("Cloud")) {
                HashMap<String, Object> browserStackOptions = new HashMap<>();
                browserStackOptions.put("userName", "codecraft1");
                browserStackOptions.put("accessKey", "jxLRH9wEpQpgP2zrKptA");
                options.setPlatformVersion(data.get("platformVersion"));
                options.setCapability("app", data.get("AppUrl"));
                options.setCapability("bstack:options", browserStackOptions);
                driver.set(new AppiumLogDriverActions(new URL("https://hub.browserstack.com/wd/hub"), options));
                return;
            }

            driver.set(new AndroidDriver(new URL("http://127.0.0.1:"+data.get("Port")), options));

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }

    public static AndroidDriver getDriver() {
        return driver.get();
    }

    @AfterTest(alwaysRun = true)
    public static void tearDown() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}
