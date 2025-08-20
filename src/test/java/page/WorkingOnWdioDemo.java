package page;

import Base.BaseTest;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Arrays;

public class WorkingOnWdioDemo extends BaseTest {

    public WorkingOnWdioDemo(AndroidDriver driver) {
        this.driver = driver;
    }

    private By dragNDrop = By.xpath("//android.widget.TextView[@text=\"Drag\"]");
    private By successMessage=AppiumBy.androidUIAutomator("new UiSelector().text(\"You made it, click retry if you want to try it again.\")");


    public void workingOnDragAndDropRoboPuzzle() {
            driver.findElement(dragNDrop).click();
            for(int i=0;i<getSourceItems.size();i++){
                dragAndDrop(getEl(getSourceItems.get(i)),getEl(getTargetItems.get(i)));
            }
            System.out.println(driver.findElement(successMessage).getText());
    }

    static ArrayList<String> getSourceItems = new ArrayList<>(
            Arrays.asList(
                    "drag-l1", "drag-l2", "drag-l3", "drag-c1", "drag-c2", "drag-c3", "drag-r1", "drag-r2", "drag-r3"
            )
    );

    static ArrayList<String> getTargetItems = new ArrayList<>(
            Arrays.asList(
                    "drop-l1", "drop-l2", "drop-l3", "drop-c1", "drop-c2", "drop-c3", "drop-r1", "drop-r2", "drop-r3"
            )
    );

    public WebElement getEl(String item) {
        return driver.findElement(AppiumBy.accessibilityId(item));

    }
}
