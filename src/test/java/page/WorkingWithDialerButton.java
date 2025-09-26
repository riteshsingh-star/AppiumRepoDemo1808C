package page;

import Base.BaseTest;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class WorkingWithDialerButton extends BaseTest {
    AndroidDriver androidDriver = (AndroidDriver) driver;
    ScreenOrientation orientation = androidDriver.getOrientation();
    WebElement backButton;

    public WorkingWithDialerButton(AppiumDriver driver) {
        this.driver = driver;
    }

    @FindBy(xpath = "//android.widget.TextView[@resource-id='com.google.android.dialer:id/dialpad_key_number' and @text='4']")
    private WebElement digit4;

    @FindBy(xpath = "//android.widget.TextView[@resource-id='com.google.android.dialer:id/dialpad_key_number' and @text='5']")
    private WebElement digit5;

    @FindBy(xpath = "//android.widget.TextView[@resource-id='com.google.android.dialer:id/dialpad_key_number' and @text='7']")
    private WebElement digit7;

    @FindBy(xpath = "//android.widget.TextView[@resource-id='com.google.android.dialer:id/dialpad_key_number' and @text='8']")
    private WebElement digit8;

    @FindBy(xpath = "//com.google.android.material.floatingactionbutton.FloatingActionButton[@content-desc='key pad']")
    private WebElement dialPadOpenButton;

    @FindBy(id = "com.google.android.dialer:id/digits")
    private WebElement dialerPadArea;

    @AndroidFindBy(accessibility = "dial")
    private WebElement dialPad;

    public void longPressOnDialerBackSpaceRotateLock() throws Exception {
        dialPadOpenButton.click();
        digit4.click();
        digit5.click();
        digit7.click();
        digit8.click();
        backButton = driver.findElement(AppiumBy.accessibilityId("backspace"));
        performLongPress(backButton);
        digit7.click();
        digit8.click();
        performTapOperation(backButton);
        System.out.println("Current Orientation: " + orientation);
        androidDriver.rotate(ScreenOrientation.LANDSCAPE);
        androidDriver.lockDevice();
        Thread.sleep(4000);
        boolean isLocked = androidDriver.isDeviceLocked();
        System.out.println("Is device locked? " + isLocked);
        if (isLocked)
            androidDriver.unlockDevice();
    }

    public void performCallOperation() {
        dialPadOpenButton.click();
        dialerPadArea.click();
        dialerPadArea.sendKeys("98740918");
        dialPad.click();
    }
}
