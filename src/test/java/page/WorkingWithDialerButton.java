package page;

import Base.BaseTest;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;

public class WorkingWithDialerButton extends BaseTest {

    public WorkingWithDialerButton(AndroidDriver driver) {
        this.driver = driver;
    }

    private By digit4 = By.xpath("//android.widget.TextView[@resource-id='com.google.android.dialer:id/dialpad_key_number' and @text='4']");
    private By digit5 = By.xpath("//android.widget.TextView[@resource-id='com.google.android.dialer:id/dialpad_key_number' and @text='5']");
    private By digit7 = By.xpath("//android.widget.TextView[@resource-id='com.google.android.dialer:id/dialpad_key_number' and @text='7']");
    private By digit8 = By.xpath("//android.widget.TextView[@resource-id='com.google.android.dialer:id/dialpad_key_number' and @text='8']");
    private By dialPadOpenButton = By.xpath("//com.google.android.material.floatingactionbutton.FloatingActionButton[@content-desc='key pad']");
    private By dialerPadArea=By.id("com.google.android.dialer:id/digits");
    private By dialPad=AppiumBy.accessibilityId("dial");

    public void longPressOnDialerBackSpaceRotateLock() throws Exception {
        driver.findElement(dialPadOpenButton).click();
        driver.findElement(digit4).click();
        driver.findElement(digit5).click();
        driver.findElement(digit7).click();
        driver.findElement(digit8).click();
        WebElement backButton = driver.findElement(AppiumBy.accessibilityId("backspace"));
        performLongPress(backButton);
        driver.findElement(digit7).click();
        driver.findElement(digit8).click();
        performTapOperation(backButton);
        ScreenOrientation orientation = driver.getOrientation();
        System.out.println("Current Orientation: " + orientation);
        driver.rotate(ScreenOrientation.LANDSCAPE);
        driver.lockDevice();
        Thread.sleep(4000);
        boolean isLocked = driver.isDeviceLocked();
        System.out.println("Is device locked? " + isLocked);
        if (isLocked)
            driver.unlockDevice();
    }

    public void performCallOperation(){
        driver.findElement(dialPadOpenButton).click();
        driver.findElement(dialerPadArea).click();
        driver.findElement(dialerPadArea).sendKeys("98740918");
        driver.findElement(dialPad).click();
    }
}
