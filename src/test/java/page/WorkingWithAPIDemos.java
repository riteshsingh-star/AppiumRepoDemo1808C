package page;

import Base.BaseTest;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import java.time.Duration;
import java.util.Collections;
import java.util.SequencedSet;


public class WorkingWithAPIDemos extends BaseTest {

    public WorkingWithAPIDemos(AndroidDriver driver) {
        this.driver = driver;
    }

    private By view = By.xpath("//android.widget.TextView[@content-desc='Views']");
    private By control = By.xpath("//android.widget.TextView[@content-desc='Controls']");
    private By lightTheme = AppiumBy.accessibilityId("1. Light Theme");
    private By textField = By.id("io.appium.android.apis:id/edit");
    private By checkBox = AppiumBy.accessibilityId("Checkbox 2");
    private By radioButton = By.id("io.appium.android.apis:id/radio2");
    private By dropdown = By.id("io.appium.android.apis:id/spinner1");
    private By dropDownOption = By.id("android:id/text1");
    private By dragAndDrop=AppiumBy.accessibilityId("Drag and Drop");
    private By dragSorces=By.xpath("//android.view.View[@resource-id='io.appium.android.apis:id/drag_dot_1']");
    private By dropTarget=By.xpath("//android.view.View[@resource-id='io.appium.android.apis:id/drag_dot_2']");



    public void simpleCheckBoxTextArea(String text) {
        System.out.println("Application Started");
        driver.findElement(view).click();
        driver.findElement(control).click();
        driver.findElement(lightTheme).click();
        driver.findElement(textField).sendKeys(text);
        driver.findElement(checkBox).click();
        driver.findElement(radioButton).click();
    }

    public void dropDownActions() throws InterruptedException {
        driver.findElement(dropdown).click();
        syncUntil(2000);
        driver.findElements(dropDownOption).get(2).click();
        System.out.println("DropDown Element Selected");
        driver.navigate().back();
        driver.navigate().back();
    }

    public void dragAndDrop() throws Exception {

        driver.findElement(view).click();
        driver.findElement(dragAndDrop).click();
        WebElement source = driver.findElement(dragSorces);
        WebElement target = driver.findElement(dropTarget);
        dragAndDrop(source, target);
        System.out.println("Drag and Drop is successfully");
        driver.navigate().back();
        driver.navigate().back();
    }

    public void scrollAndSwitches() {
        String switches = "Switches";
        WebElement element = driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()." +
                "scrollable(true))" + ".scrollIntoView(new UiSelector().text(\"" + switches + "\"));"));
        element.click();
        WebElement monitoredSwitch = driver.findElement(By.xpath("//android.widget.Switch[@content-desc='Monitored switch']"));
        if (monitoredSwitch.isSelected())
            System.out.println("Monitored Switch is selected");
        else {
            System.out.println("Monitored Switch is off.Doing Switch ON");
            monitoredSwitch.click();
        }
        driver.navigate().back();
        driver.navigate().back();
    }



    public void hardwareKeysInteraction() throws Exception {
        driver.findElement(view).click();
        driver.findElement(control).click();
        //performing back operation
        driver.pressKey(new KeyEvent(AndroidKey.BACK));
        //perform back to home operation
        driver.pressKey(new KeyEvent(AndroidKey.HOME));
        //perform increasing the volume
        driver.pressKey(new KeyEvent(AndroidKey.VOLUME_UP));
        //perform decreasing the volume
        driver.pressKey(new KeyEvent(AndroidKey.VOLUME_DOWN));
        //perform mute on volume button
        driver.pressKey(new KeyEvent(AndroidKey.VOLUME_MUTE));
        //perform switch off operation
        //driver.pressKey(new KeyEvent(AndroidKey.POWER));
        //perform switch on operation
        //driver.pressKey(new KeyEvent(AndroidKey.POWER));

        //Thread.sleep(8000);
        driver.pressKey(new KeyEvent(AndroidKey.HOME));
        driver.pressKey(new KeyEvent(AndroidKey.CALENDAR));
    }

    public void installAndUninstall() throws Exception {
        Thread.sleep(2000);
        if (driver.isAppInstalled("io.appium.android.apis")) {
            driver.removeApp("io.appium.android.apis");
        }

        driver.installApp("C:\\CodeCraft Code\\appiumDemoFramework\\apps\\ApiDemos-debug.apk");
        //driver.reset
    }

    public void doubleClickOperation() {
        WebElement element = driver.findElement(By.xpath("//android.widget.ImageButton[@content-desc=\"Customize and control Google Chrome\"]"));
        doubleClick(element);
    }

    public void performRightToLeftSwipe(){
        WebElement element=driver.findElement(By.xpath(""));
        int widthVal=element.getSize().width;
        int heightVal=element.getSize().height;
        int startX=(int)(widthVal*0.8);
        int startY=heightVal/2;
        int endX=(int)(widthVal*0.2);
        int endY=heightVal/2;

        PointerInput finger=new PointerInput(PointerInput.Kind.TOUCH,"finger");
        Sequence seq1=new Sequence(finger,1).
                addAction(finger.createPointerMove(Duration.ZERO,PointerInput.Origin.viewport(),startX,startY)).
                addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg())).
                addAction(new Pause(finger,Duration.ofMillis(100))).
                addAction(finger.createPointerMove(Duration.ofMillis(100),PointerInput.Origin.viewport(),endX,endY)).
                addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

    }
}
