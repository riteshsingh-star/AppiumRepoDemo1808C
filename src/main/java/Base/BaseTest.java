package Base;

import com.google.common.collect.ImmutableList;
import drivers.DriverManager;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;


public class BaseTest {

    static final Logger logger = Logger.getLogger(BaseTest.class);

    public AndroidDriver driver;


    @BeforeTest(alwaysRun = true)
    @Parameters({"deviceIndex"})
    public void setUp(int deviceIndex) {
        DriverManager.initializeDriver(deviceIndex); // pass index
        driver = DriverManager.getDriver();
        logger.info("Driver initialized");
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
        logger.info("Driver closed");
    }

    public void performTapOperation(WebElement element) {
        Point location = element.getLocation();
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "Finger");
        Sequence sequence = new Sequence(finger, 1).
                addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), location)).
                addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg())).
                addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Collections.singletonList(sequence));
    }

    public void performPinchOperation(WebDriver driver1){
        Dimension dimension = driver1.manage().window().getSize();
        int centerX = dimension.width / 2;
        int centerY = dimension.height / 2;

        int startY1 = centerY - centerY / 2;
        int startY2 = centerY + centerY / 2;

        PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "Finger");
        PointerInput finger2 = new PointerInput(PointerInput.Kind.TOUCH, "Finger");
        Sequence sequence1=new Sequence(finger1,1).
               addAction(finger1.createPointerMove(Duration.ZERO,PointerInput.Origin.viewport(),centerX,startY1)).
                addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg())).
                addAction(finger1.createPointerMove(Duration.ofMillis(200),PointerInput.Origin.viewport(),centerX,centerY)).
                addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        Sequence sequence2=new Sequence(finger2,1).
                addAction(finger2.createPointerMove(Duration.ZERO),PointerInput.Origin.viewport(),centerX,startY2).
                addAction(finger2.createPointerDown(PointerInput.MouseButton.LEFT.asArg())).
                addAction(finger2.createPointerMove(Duration.ofMillis(200),PointerInput.Origin.viewport(),centerX,centerY)).
                addAction(finger2.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Arrays.asList(sequence1,sequence2));
    }

    public void performZoomOperation(WebDriver driver2){
        Dimension dimension = driver2.manage().window().getSize();
        int centerX = dimension.width / 2;
        int centerY = dimension.height / 2;

        int endY1 = centerY - centerY / 2;
        int endY2 = centerY + centerY / 2;

        PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "Finger");
        PointerInput finger2 = new PointerInput(PointerInput.Kind.TOUCH, "Finger");

        Sequence sequence1=new Sequence(finger1,1).
                addAction(finger1.createPointerMove(Duration.ZERO,PointerInput.Origin.viewport(),centerX,centerY)).
                addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg())).
                addAction(finger1.createPointerMove(Duration.ofMillis(200),PointerInput.Origin.viewport(),centerX,endY1)).
                addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        Sequence sequence2=new Sequence(finger2,1).
                addAction(finger2.createPointerMove(Duration.ZERO,PointerInput.Origin.viewport(),centerX,centerY)).
                addAction(finger2.createPointerDown(PointerInput.MouseButton.LEFT.asArg())).
                addAction(finger2.createPointerMove(Duration.ofMillis(200),PointerInput.Origin.viewport(),centerX,endY2)).
                addAction(finger2.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Arrays.asList(sequence1,sequence2));
    }

    public void performLongPress(WebElement e) {
        Point location = e.getLocation();
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "Finger");
        Sequence sequence = new Sequence(finger, 1).
                addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), location)).
                addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg())).
                addAction(new Pause(finger, Duration.ofMillis(2000))).
                addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Collections.singletonList(sequence));

    }

    public void dragAndDrop(WebElement source, WebElement target) {

        Point location1 = source.getLocation();
        Point location2 = target.getLocation();
        Dimension size1 = source.getSize();
        Dimension size2 = target.getSize();

        Point centerSource = new Point(location1.x + size1.width / 2, location1.y + size1.height / 2);
        Point centerTarget=new Point(location2.x + size2.width / 2, location2.y + size2.height / 2);
        PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
        Sequence sequence = new Sequence(finger1, 1).
                addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), centerSource)).
                addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg())).
                addAction(new Pause(finger1, Duration.ofMillis(500))).
                addAction(finger1.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), centerTarget)).
                addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Arrays.asList(sequence));
    }

    public void doubleClick(WebElement element) {
        Point location = element.getLocation();
        Dimension size = element.getSize();
        int centerX = location.getX() + size.getWidth() / 2;
        int centerY = location.getY() + size.getHeight() / 2;
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "Finger");
        Sequence sequence = new Sequence(finger, 1).
                addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), centerX, centerY)).
                addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg())).
                addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg())).
                addAction(new Pause(finger, Duration.ofMillis(100))).
                addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg())).
                addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Collections.singletonList(sequence));
    }

    public enum ScrollDirection {
        UP, DOWN, LEFT, RIGHT;
    }

    public void PerformSwipeOperation(ScrollDirection direction, double scrollRatio, WebElement element) throws Exception {
        Duration duration = Duration.ofMillis(300);
        //Dimension size = driver.manage().window().getSize();
        Dimension size = element.getSize();
        Point centre = new Point((int) (size.width * 0.5), (int) (size.height * 0.5));
        int bottom = centre.y + (int) (centre.y * scrollRatio);
        int top = centre.y - (int) (centre.y * scrollRatio);
        int left = centre.x - (int) (centre.y * scrollRatio);
        int right = centre.x + (int) (centre.y * scrollRatio);
        if (direction == ScrollDirection.UP) {
            swipeOperation(new Point(centre.x, top), new Point(centre.x, bottom), duration);
        }
        if (direction == ScrollDirection.DOWN) {
            swipeOperation(new Point(centre.x, bottom), new Point(centre.x, top), duration);
        }
        if (direction == ScrollDirection.LEFT) {
            swipeOperation(new Point(left, centre.y), new Point(right, centre.y), duration);
        }
        if (direction == ScrollDirection.RIGHT) {
            swipeOperation(new Point(right, centre.y), new Point(left, centre.y), duration);
        }

    }


    public void swipeOnElement(WebElement element, ScrollDirection direction, int durationMs) {
        performSwipe(direction, element.getSize().width, element.getSize().height, durationMs, element);
    }

    private void performSwipe(ScrollDirection direction, int width, int height, int durationMs, WebElement element) {
        int startX, startY, endX, endY;

        switch (direction) {
            case UP:
                startX = width / 2;
                startY = (int) (height * 0.8);
                endX = width / 2;
                endY = (int) (height * 0.2);
                break;
            case DOWN:
                startX = width / 2;
                startY = (int) (height * 0.2);
                endX = width / 2;
                endY = (int) (height * 0.8);
                break;
            case LEFT:
                startX = (int) (width * 0.8);
                startY = height / 2;
                endX = (int) (width * 0.2);
                endY = height / 2;
                break;
            case RIGHT:
                startX = (int) (width * 0.2);
                startY = height / 2;
                endX = (int) (width * 0.8);
                endY = height / 2;
                break;
            default:
                throw new IllegalArgumentException("Invalid swipe direction");
        }

        // If element-based, offset coordinates
        if (element != null) {
            int elX = element.getLocation().getX();
            int elY = element.getLocation().getY();
            startX += elX;
            startY += elY;
            endX += elX;
            endY += elY;
        }

        PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
        Sequence sequence = new Sequence(finger1, 0).
                addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY)).
                addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg())).
                addAction(new Pause(finger1, Duration.ofMillis(500))).
                addAction(finger1.createPointerMove(Duration.ofMillis(durationMs), PointerInput.Origin.viewport(), endX, endY)).
                addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(ImmutableList.of(sequence));

    }

    public void swipeOperation(Point startPoint, Point endPoint, Duration duration) throws Exception {
        PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
        Sequence sequence = new Sequence(finger1, 0).
                addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startPoint.x, startPoint.y)).
                addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg())).
                //addAction(new Pause(finger1, Duration.ofMillis(500))).
                        addAction(finger1.createPointerMove(duration, PointerInput.Origin.viewport(), endPoint.x, endPoint.y)).
                addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(ImmutableList.of(sequence));
    }

    public AndroidDriver getDriver() {
        return driver;
    }

    public static void syncUntil(int milliSecond) throws InterruptedException {
        Thread.sleep(milliSecond);
    }

    public void waitUntilExpectedCondition(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static Point findCentre(WebElement element) {
        Point location = element.getLocation();
        Dimension size = element.getSize();
        Point center = new Point(location.x + size.width / 2, location.y + size.height / 2);
        return center;
    }

    public void fluentCustomPooling(){
        FluentWait<WebDriver> wait = new FluentWait<>(driver);
        wait.pollingEvery(Duration.ofMillis(500)).
                withTimeout(Duration.ofMillis(100)).
                ignoring(Exception.class);
    }

    static {
        DOMConfigurator.configure("src/main/resources/log4j.xml");
    }
    public void scrollForwardAndSelectOption(String textToFind){
        WebElement element = driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()." + "" +
                "scrollable(true))" + ".scrollIntoView(new UiSelector(text(\"" + textToFind + "\")));"));
        element.click();
    }

    public void scrollBackwardAndSelectOption(String textToFind){
        WebElement element = driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()." + "" +
                "scrollable(true))" + ".scrollBackward().scrollIntoView(new UiSelector(text(\"" + textToFind + "\")));"));
        element.click();
    }
}
