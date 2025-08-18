package page;

import Base.BaseTest;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;


public class WorkingOnMobileBrowsers extends BaseTest {

    public  WorkingOnMobileBrowsers(AndroidDriver driver) {
        this.driver = driver;
    }

    private By admin=By.xpath("//input[@placeholder='Username']");
    private By password=By.xpath("//input[@placeholder='Password']");
    private By login=By.xpath("//button[text()=' Login ']");

    public void automateOrangeHRM(String userName,String passwordValue) throws InterruptedException {
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        syncUntil(8000);
        driver.findElement(admin).click();
        driver.findElement(admin).sendKeys(userName);
        driver.findElement(password).click();
        driver.findElement(password).sendKeys(passwordValue);
        driver.findElement(login).click();
    }


}
