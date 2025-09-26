package page;

import Base.BaseTest;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;


public class WorkingOnMobileBrowsers extends BaseTest {

    public WorkingOnMobileBrowsers(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@placeholder='Username']")
    private WebElement admin;

    @FindBy(xpath = "//input[@placeholder='Password']")
    private WebElement password;

    @FindBy(xpath = "//button[text()=' Login ']")
    private WebElement login;

    @FindBy(xpath = "//input[@placeholder='First Name']")
    private WebElement firstName;

    @FindBy(xpath = "//input[@placeholder='Last Name']")
    private WebElement lastName;

    @FindBy(xpath = "//textarea[@ng-model='Adress']")
    private WebElement address;

    @FindBy(xpath = "//input[@ng-model='EmailAdress']")
    private WebElement email;

    @FindBy(xpath = "//input[@ng-model='Phone']")
    private WebElement phone;

    @FindBy(xpath = "//option[text()='APIs']")
    private WebElement dropDownOption;

    @FindBy(xpath = "//select[@id='Skills']")
    private WebElement dropDown;

    @FindBy(xpath = "//select[@placeholder='Year']")
    private WebElement dobYear;

    @FindBy(xpath = "//select[@placeholder='Month']")
    private WebElement dobMonth;

    @FindBy(xpath = "//select[@placeholder='Day']")
    private WebElement dobDay;

    @FindBy(xpath = "//input[@ng-model='Password']")
    private WebElement passwordDemoQA;

    @FindBy(xpath = "//input[@ng-model='CPassword']")
    private WebElement confirmPassword;

    @FindBy(xpath = "//span[text()='Toggle Navigation']//parent::button")
    private WebElement toogleButton;

    @FindBy(xpath = "//a[text()='SwitchTo']")
    private WebElement switchTo;

    @FindBy(xpath = "//a[text()='Alerts']")
    private WebElement alerts;

    @FindBy(xpath = "(//button[contains(@class,'btn btn')])[2]")
    private WebElement alertConfirm;

    public void automateOrangeHRM(String userName, String passwordValue) throws InterruptedException {
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        syncUntil(8000);
        admin.click();
        admin.sendKeys(userName);
        password.click();
        password.sendKeys(passwordValue);
        login.click();
    }

    public void selectClassForBrowserDropDown(WebElement locator, String text) {
        Select dropdown = new Select(locator);
        dropdown.selectByVisibleText(text);
    }

    public void demoRegister(String firstNameVal, String lastNameVal, String add,
                             String emailVal, String phoneNum, String gender, String hobbies,
                             String skills, String year, String month, String day, String password) throws InterruptedException {
        driver.get("https://demo.automationtesting.in/Register.html");
        syncUntil(4000);
        firstName.click();
        firstName.sendKeys(firstNameVal);
        lastName.click();
        lastName.sendKeys(lastNameVal);
        address.click();
        address.sendKeys(add);
        email.click();
        email.sendKeys(emailVal);
        phone.click();
        phone.sendKeys(phoneNum);
        driver.findElement(By.xpath("//input[@value='" + gender + "']")).click();
        driver.findElement(By.xpath("//input[@value='" + hobbies + "']")).click();
        selectClassForBrowserDropDown(dropDown, skills);
        selectClassForBrowserDropDown(dobYear, year);
        selectClassForBrowserDropDown(dobMonth, month);
        selectClassForBrowserDropDown(dobDay, day);
        passwordDemoQA.click();
        passwordDemoQA.sendKeys(password);
        confirmPassword.click();
        confirmPassword.sendKeys(password);
    }

    public void findElementByImage() throws IOException, InterruptedException {
        syncUntil(4000);
        // driver.setSetting(Settings.GET_MATCHED_IMAGE_RESULT, true);
        String imagePath = "src/test/resources/images/demoRegisterTest_20250819_120445.png";
        byte[] fileContent = Files.readAllBytes(Paths.get(imagePath));
        String base64Image = Base64.getEncoder().encodeToString(fileContent);

        WebElement imageElement = driver.findElement(AppiumBy.image(base64Image));
        System.out.println(imageElement.getText());

    }

    public void alertOptions() {
        toogleButton.click();
        switchTo.click();
        alerts.click();
        alertConfirm.click();
        driver.switchTo().alert().accept();

    }

}
