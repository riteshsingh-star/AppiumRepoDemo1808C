package Utils;

import org.jspecify.annotations.Nullable;
import org.openqa.selenium.*;

import java.util.List;

public class LoggingWebElement implements WebElement {

    private final WebElement element;

    public LoggingWebElement(WebElement element) {
        this.element = element;
    }
    @Override
    public void click() {
        ExtentManager.getTest().info("Clicking on element: <code>" + element.toString() + "</code>");
        element.click();
    }

    @Override
    public void sendKeys(CharSequence... keysToSend) {
        ExtentManager.getTest().info("Sending keys: <b>" + String.join("", keysToSend) + "</b> to element: <code>" + element.toString() + "</code>");
        element.sendKeys(keysToSend);
    }


    @Override
    public void submit() {

    }

    @Override
    public void clear() {

    }


    @Override
    public String getTagName() {
        return "";
    }

    @Override
    public @Nullable String getAttribute(String name) {
        return "";
    }

    @Override
    public boolean isSelected() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    @Override
    public String getText() {
        return "";
    }

    @Override
    public List<WebElement> findElements(By by) {
        return List.of();
    }

    @Override
    public WebElement findElement(By by) {
        return null;
    }

    @Override
    public boolean isDisplayed() {
        return false;
    }

    @Override
    public Point getLocation() {
        return null;
    }

    @Override
    public Dimension getSize() {
        return null;
    }

    @Override
    public Rectangle getRect() {
        return null;
    }

    @Override
    public String getCssValue(String propertyName) {
        return "";
    }

    @Override
    public <X> X getScreenshotAs(OutputType<X> target) throws WebDriverException {
        return null;
    }
}
