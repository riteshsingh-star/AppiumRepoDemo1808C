package Utils;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class AppiumLogDriverActions {

    private AppiumDriver driver;
    private ExtentTest extentTest;

    public AppiumLogDriverActions(AppiumDriver driver, ExtentTest test) {
        this.driver = driver;
        this.extentTest = test;
    }

    public void click(By locator) {
        try{
            driver.findElement(locator).click();
            extentTest.log(Status.PASS, "Clicked element " + locator.toString());
    }catch(Exception e){
        extentTest.log(Status.FAIL, "Error clicking element " + locator.toString());}
    }

    public void sendKeys(By locator, String text) {
        try{
            driver.findElement(locator).sendKeys(text);
            extentTest.log(Status.PASS, "Sent keys '" + text + "' to: " + locator.toString());
        }catch (Exception e){
            extentTest.log(Status.FAIL, "Error sending keys " + locator.toString());
        }
    }

    public void getText(By locator) {
        try{
            driver.findElement(locator).getText();
            extentTest.log(Status.PASS, "Text '" + locator.toString() + "' to: " + locator.toString());
        }catch (Exception e){
            extentTest.log(Status.FAIL, "Error getting text " + locator.toString());
        }
    }



}
