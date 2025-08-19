package Test;

import Base.BaseTest;
import Utils.JsonUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import page.WorkingOnMobileBrowsers;

import java.util.List;
import java.util.Map;

public class WorkingWithBrowserTest extends BaseTest {

    private WorkingOnMobileBrowsers test;
    private List<Map<String, String>> testData;
    private Map<String, String> data;

    @BeforeClass
    public void setUpPage() {
        // Make sure driver is initialized before using
        if (driver == null) {
            throw new RuntimeException("Driver not initialized! Check BaseTest setup.");
        }
        test = new WorkingOnMobileBrowsers(driver);
        testData = JsonUtils.getJsonArrayFromJsonFile("BrowserTesting.json");
        data = testData.get(0);
    }

    @Test
    public void performBrowserAction() throws InterruptedException {
        String userName = data.get("UserName");   // must match JSON key
        String password = data.get("Password");
        test.automateOrangeHRM(userName, password);
    }
}
