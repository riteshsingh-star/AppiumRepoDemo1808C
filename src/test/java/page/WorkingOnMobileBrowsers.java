package page;

import Base.BaseTest;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;


public class WorkingOnMobileBrowsers extends BaseTest {

    public WorkingOnMobileBrowsers(AndroidDriver driver) {
        this.driver = driver;
    }

    private By admin = By.xpath("//input[@placeholder='Username']");
    private By password = By.xpath("//input[@placeholder='Password']");
    private By login = By.xpath("//button[text()=' Login ']");
    private By firstName = By.xpath("//input[@placeholder='First Name']");
    private By lastName = By.xpath("//input[@placeholder='Last Name']");
    private By address = By.xpath("//textarea[@ng-model='Adress']");
    private By email = By.xpath("//input[@ng-model='EmailAdress']");
    private By phone = By.xpath("//input[@ng-model='Phone']");
    private By dropDownOption = By.xpath("//option[text()='APIs']");
    private By dropDown = By.xpath("//select[@id='Skills']");
    private By dobYear = By.xpath("//select[@placeholder='Year']");
    private By dobMonth = By.xpath("//select[@placeholder='Month']");
    private By dobDay = By.xpath("//select[@placeholder='Day']");
    private By passwordDemoQA = By.xpath("//input[@ng-model='Password']");
    private By confirmPassword = By.xpath("//input[@ng-model='CPassword']");
    private By toogleButton=By.xpath("//span[text()='Toggle Navigation']//parent::button");
    private By switchTo=By.xpath("//a[text()='SwitchTo']");
    private By alerts=By.xpath("//a[text()='Alerts']");
    private By alertConfirm=By.xpath("(//button[contains(@class,'btn btn')])[2]");

    public void automateOrangeHRM(String userName, String passwordValue) throws InterruptedException {
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        syncUntil(8000);
        driver.findElement(admin).click();
        driver.findElement(admin).sendKeys(userName);
        driver.findElement(password).click();
        driver.findElement(password).sendKeys(passwordValue);
        driver.findElement(login).click();
    }

    public void selectClassForBrowserDropDown(By locator, String text) {
        Select dropdown = new Select(driver.findElement(locator));
        dropdown.selectByVisibleText(text);
    }

    public void demoRegister(String firstNameVal, String lastNameVal, String add,
                             String emailVal, String phoneNum, String gender, String hobbies,
                             String skills, String year, String month, String day, String password) throws InterruptedException {
        driver.get("https://demo.automationtesting.in/Register.html");
        syncUntil(4000);
        driver.findElement(firstName).click();
        driver.findElement(firstName).sendKeys(firstNameVal);
        driver.findElement(lastName).click();
        driver.findElement(lastName).sendKeys(lastNameVal);
        driver.findElement(address).click();
        driver.findElement(address).sendKeys(add);
        driver.findElement(email).click();
        driver.findElement(email).sendKeys(emailVal);
        driver.findElement(phone).click();
        driver.findElement(phone).sendKeys(phoneNum);
        driver.findElement(By.xpath("//input[@value='"+gender+"']")).click();
        driver.findElement(By.xpath("//input[@value='"+hobbies+"']")).click();
        selectClassForBrowserDropDown(dropDown, skills);
        selectClassForBrowserDropDown(dobYear, year);
        selectClassForBrowserDropDown(dobMonth, month);
        selectClassForBrowserDropDown(dobDay, day);
        driver.findElement(passwordDemoQA).click();
        driver.findElement(passwordDemoQA).sendKeys(password);
        driver.findElement(confirmPassword).click();
        driver.findElement(confirmPassword).sendKeys(password);
    }

    public void findElementByImage() throws IOException, InterruptedException {
        syncUntil(4000);
        String imagePath = "src/test/resources/images/demoRegisterTest_20250819_120445.png";
        byte[] fileContent = Files.readAllBytes(Paths.get(imagePath));
        String base64Image = Base64.getEncoder().encodeToString(fileContent);
        WebElement imageElement = driver.findElement(AppiumBy.image(base64Image));
       System.out.println(imageElement.getText());

    }

    public void alertOptions(){
        driver.findElement(toogleButton).click();
        driver.findElement(switchTo).click();
        driver.findElement(alerts).click();
        driver.findElement(alertConfirm).click();
        driver.switchTo().alert().accept();

    }

}
