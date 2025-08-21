package Test;

import Base.BaseTest;
import Utils.JsonUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import page.WorkingOnMobileBrowsers;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class WorkingWithBrowserTest extends BaseTest {

    private WorkingOnMobileBrowsers test;
    private List<Map<String, String>> testData;
    private Map<String, String> data;

    @BeforeClass
    public void setUpPage() {
        if (driver == null) {
            throw new RuntimeException("Driver not initialized! Check BaseTest setup.");
        }
        test = new WorkingOnMobileBrowsers(driver);
        testData = JsonUtils.getJsonArrayFromJsonFile("BrowserTesting.json");
        data = testData.get(0);
    }

    @Test(priority = 2)
    public void performBrowserAction() throws InterruptedException {
        String userName = data.get("UserName");   // must match JSON key
        String password = data.get("Password");
        test.automateOrangeHRM(userName, password);
    }

    @Test(priority = 0)
    public void demoRegisterTest() throws InterruptedException {
        test.demoRegister(data.get("firstNameVal"),data.get("lastNameVal"),data.get("add"),data.get("emailVal")
        ,data.get("phoneNum"),data.get("gender"),data.get("hobbies"),data.get("skills"),data.get("year"),data.get("month"),data.get("day"),data.get("password"));

    }

    //@Test(priority = 1)
    public void imageElementFind() throws IOException, InterruptedException {
        test.findElementByImage();
    }

    @Test(priority = 1)
    public void alertsDemo(){
        test.alertOptions();
    }
}
