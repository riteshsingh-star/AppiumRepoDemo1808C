package page;

import Base.BaseTest;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.Set;

public class WorkingWithGeneralStore extends BaseTest {

    AndroidDriver androidDriver;
    public WorkingWithGeneralStore(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        androidDriver = (AndroidDriver) driver;
    }

    @FindBy(id = "com.androidsample.generalstore:id/nameField")
    private WebElement nameField;

    @FindBy(id = "com.androidsample.generalstore:id/btnLetsShop")
    private WebElement letsShopButton;

    private final By addToCartButton=By.id("com.androidsample.generalstore:id/productAddCart");

    @FindBy(id = "com.androidsample.generalstore:id/appbar_btn_cart")
    private WebElement moveToCart;

    @FindBy(xpath = "//android.widget.CheckBox[@text='Send me e-mails on discounts related to selected products in future']")
    private WebElement getMessageFromCheckBox;

    @FindBy(id = "com.androidsample.generalstore:id/termsButton")
    private WebElement termsAndCondition;

    @FindBy(id = "android:id/button1")
    private WebElement closeButton;

    @FindBy(id = "com.androidsample.generalstore:id/btnProceed")
    private WebElement visitWebsiteForPurchase;

    private final By productName=By.id("com.androidsample.generalstore:id/productName");

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"android:id/text1\"]")
    private WebElement countrySelector;

    public void workingWithGeneralStoreApk(String dropDownValue,String nameValue,String gender,String itemType,String viewName1,String viewName2) throws Exception {
        syncUntil(5000);
        countrySelector.click();
        WebElement element = driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()." +
                "scrollable(true))" + ".scrollIntoView(new UiSelector().text(\"" + dropDownValue + "\"));"));
        element.click();
        nameField.click();
        nameField.sendKeys(nameValue);

        driver.findElement(By.id("com.androidsample.generalstore:id/radio" + gender)).click();
        letsShopButton.click();

        WebElement ele = driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + itemType + "\"));"));
        List<WebElement> element1 = driver.findElements(productName);

        for (int i = 0; i < element1.size(); i++) {
            if (element1.get(i).getText().equals(itemType)) {
                driver.findElements(addToCartButton).get(i).click();
                break;
            }

        }
        moveToCart.click();
        waitUntilExpectedCondition(getMessageFromCheckBox);
        performTapOperation(getMessageFromCheckBox);

        performLongPress(termsAndCondition);
        closeButton.click();
        visitWebsiteForPurchase.click();

        syncUntil(5000);
        Set<String> contextName = androidDriver.getContextHandles();
        for (String contextNames : contextName) {
            System.out.println(contextNames);
        }
        androidDriver.context(viewName1);
        Thread.sleep(2000);
        driver.findElement(By.name("q")).sendKeys("India");
        driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
        androidDriver.pressKey(new KeyEvent(AndroidKey.BACK));
        Thread.sleep(2000);
        androidDriver.context(viewName2);
        androidDriver.pressKey(new KeyEvent(AndroidKey.HOME));
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
