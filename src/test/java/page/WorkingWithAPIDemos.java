package page;

import Base.BaseTest;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class WorkingWithAPIDemos extends BaseTest {

    static final Logger logger = Logger.getLogger(WorkingWithAPIDemos.class);
    AndroidDriver androidDriver;
    public WorkingWithAPIDemos(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
        androidDriver = (AndroidDriver) driver;
    }

    @FindBy(xpath = "//android.widget.TextView[@content-desc='Views']")
    private WebElement view;

    @FindBy(xpath = "//android.widget.TextView[@content-desc='Controls']")
    private WebElement control;

    @AndroidFindBy(accessibility = "1. Light Theme")
    private WebElement lightTheme;

    @FindBy(id = "io.appium.android.apis:id/edit")
    private WebElement textField;

    @AndroidFindBy(accessibility = "Checkbox 2")
    private WebElement checkBox;

    @FindBy(id = "io.appium.android.apis:id/radio2")
    private WebElement radioButton;

    @FindBy(id = "io.appium.android.apis:id/spinner1")
    private WebElement dropdown;

    private final By dropDownOption=AppiumBy.id("android:id/text1");

    @AndroidFindBy(accessibility = "Drag and Drop")
    private WebElement dragAndDrop;

    @FindBy(xpath = "//android.view.View[@resource-id='io.appium.android.apis:id/drag_dot_1']")
    private WebElement dragSources;

    @FindBy(xpath = "//android.view.View[@resource-id='io.appium.android.apis:id/drag_dot_2']")
    private WebElement dropTarget;


    @Step
    public void simpleCheckBoxTextArea(String text) {
        System.out.println("Application Started");
        view.click();
        control.click();
        lightTheme.click();
        textField.sendKeys(text);
        checkBox.click();
        radioButton.click();
    }

    @Step
    public void dropDownActions() throws InterruptedException {
        dropdown.click();
        syncUntil(2000);
        driver.findElements(dropDownOption).get(2).click();
        System.out.println("DropDown Element Selected");
        driver.navigate().back();
        driver.navigate().back();
    }

    @Step
    public void dragAndDrop() throws Exception {
        view.click();
        dragAndDrop.click();
        dragAndDrop(dragSources, dropTarget);
        System.out.println("Drag and Drop is successfully");
        driver.navigate().back();
        driver.navigate().back();
    }

    @Step
    public void scrollAndSwitches() {
        String switches = "Switches";
        WebElement element = driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()." +
                "scrollable(true))" + ".scrollIntoView(new UiSelector().text(\"" + switches + "\"));"));
        element.click();
        WebElement monitoredSwitch = driver.findElement(By.xpath("//android.widget.Switch[@content-desc='Monitored switch']"));
        if (monitoredSwitch.isSelected())
            logger.info("Monitored Switch is selected");
        else {
            logger.info("Monitored Switch is off.Doing Switch ON");
            monitoredSwitch.click();
        }
        driver.navigate().back();
        driver.navigate().back();
    }

    @Step
    public void hardwareKeysInteraction() throws Exception {
        view.click();
        control.click();
        //performing back operation
        androidDriver.pressKey(new KeyEvent(AndroidKey.BACK));
        //perform back to home operation
        androidDriver.pressKey(new KeyEvent(AndroidKey.HOME));
        //perform increasing the volume
        androidDriver.pressKey(new KeyEvent(AndroidKey.VOLUME_UP));
        //perform decreasing the volume
        androidDriver.pressKey(new KeyEvent(AndroidKey.VOLUME_DOWN));
        //perform mute on volume button
        androidDriver.pressKey(new KeyEvent(AndroidKey.VOLUME_MUTE));
        //perform switch off operation
        //driver.pressKey(new KeyEvent(AndroidKey.POWER));
        //perform switch on operation
        //driver.pressKey(new KeyEvent(AndroidKey.POWER));

        //Thread.sleep(8000);
        androidDriver.pressKey(new KeyEvent(AndroidKey.HOME));
        androidDriver.pressKey(new KeyEvent(AndroidKey.CALENDAR));
    }

    @Step
    public void installAndUninstall() throws Exception {
        Thread.sleep(2000);
        if (androidDriver.isAppInstalled("io.appium.android.apis")) {
            androidDriver.removeApp("io.appium.android.apis");
        }

        androidDriver.installApp("C:\\CodeCraft Code\\appiumDemoFramework\\apps\\ApiDemos-debug.apk");
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
