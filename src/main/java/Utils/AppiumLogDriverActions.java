package Utils;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


import java.net.URL;


public class AppiumLogDriverActions extends AndroidDriver {

    public AppiumLogDriverActions(URL remoteAddress, UiAutomator2Options options) {
        super(remoteAddress, options);
    }

    @Override
    public WebElement findElement(By by) {
        ExtentManager.getTest().info("Finding element: <code>" + by.toString() + "</code>");
        WebElement element = super.findElement(by);
        ExtentManager.getTest().info("Found element: <code>" + element.toString() + "</code>");
        return new LoggingWebElement(element);
    }


    @Override
    public void get(String url) {
        System.out.println("[NAVIGATE] Navigating to: " + url);
        super.get(url);
    }

    @Override
    public void quit() {
        System.out.println("[QUIT] Quitting driver...");
        super.quit();
    }


}
