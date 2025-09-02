package page;

import Base.BaseTest;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import java.util.List;
import java.util.Set;

public class WorkingWithGeneralStore extends BaseTest {

    public WorkingWithGeneralStore(AndroidDriver driver) {
        this.driver = driver;
    }

    private By nameField = By.id("com.androidsample.generalstore:id/nameField");
    private By letsShopButton = By.id("com.androidsample.generalstore:id/btnLetsShop");
    private By addToCartButton = By.id("com.androidsample.generalstore:id/productAddCart");
    private By moveToCart = By.id("com.androidsample.generalstore:id/appbar_btn_cart");
    private By getMessageFromCheckBox = By.xpath("//android.widget.CheckBox[@text='Send me e-mails on discounts related to selected products in future']");
    private By termsAndCondition = By.id("com.androidsample.generalstore:id/termsButton");
    private By closeButton = By.id("android:id/button1");
    private By visitWebsiteForPurchase=By.id("com.androidsample.generalstore:id/btnProceed");
    private By productName=By.id("com.androidsample.generalstore:id/productName");
    private By countrySelector= AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"android:id/text1\")");

    public void workingWithGeneralStoreApk(String dropDownValue,String nameValue,String gender,String itemType,String viewName1,String viewName2) throws Exception {
        syncUntil(5000);
        driver.findElement(countrySelector).click();
        WebElement element = driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()." +
                "scrollable(true))" + ".scrollIntoView(new UiSelector().text(\"" + dropDownValue + "\"));"));
        element.click();
        WebElement name = driver.findElement(nameField);
        name.click();
        name.sendKeys(nameValue);

        driver.findElement(By.id("com.androidsample.generalstore:id/radio" + gender)).click();
        driver.findElement(letsShopButton).click();

        WebElement ele = driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + itemType + "\"));"));
        List<WebElement> element1 = driver.findElements(productName);

        for (int i = 0; i < element1.size(); i++) {
            if (element1.get(i).getText().equals(itemType)) {
                driver.findElements(addToCartButton).get(i).click();
                break;
            }

        }
        driver.findElement(moveToCart).click();
        WebElement checkbox = driver.findElement(getMessageFromCheckBox);
        waitUntilExpectedCondition(checkbox);
        performTapOperation(checkbox);

        WebElement termsandCondition = driver.findElement(termsAndCondition);
        performLongPress(termsandCondition);
        driver.findElement(closeButton).click();
        driver.findElement(visitWebsiteForPurchase).click();

        syncUntil(5000);
        Set<String> contextName = driver.getContextHandles();
        for (String contextNames : contextName) {
            System.out.println(contextNames);
        }
        driver.context(viewName1);
        Thread.sleep(2000);
        driver.findElement(By.name("q")).sendKeys("India");
        driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
        driver.pressKey(new KeyEvent(AndroidKey.BACK));
        Thread.sleep(2000);
        driver.context(viewName2);
        driver.pressKey(new KeyEvent(AndroidKey.HOME));
    }


    public void workingOnSwipeOperation() throws Exception {
        driver.findElement(By.xpath("//android.widget.TextView[@text=\"\uDB82\uDD3C\"]")).click();
        Thread.sleep(4000);
        WebElement element = driver.findElement(By.xpath("(//android.view.ViewGroup[@content-desc='card'])[1]"));
        Thread.sleep(2000);
        swipeOnElement(element, ScrollDirection.DOWN, 700);
        swipeOnElement(element, ScrollDirection.LEFT, 300);

    }
}
