package Test;

import Base.BaseTest;
import Utils.JsonUtils;
import org.apache.xmlbeans.impl.xb.ltgfmt.TestCase;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import page.WorkingOnMobileBrowsers;
import page.WorkingWithAPIDemos;

import java.util.List;
import java.util.Map;

public class WorkingWithBrowserTest extends BaseTest {

    private WorkingOnMobileBrowsers test;
    private List<Map<String, String>> testData;
    Map<String, String> data;

    @BeforeClass
    public void setUpPage() {
        test = new WorkingOnMobileBrowsers(driver);
        testData= JsonUtils.getJsonArrayFromJsonFile("BrowserTesting.json");
        data = testData.get(0);
    }

    @Test()
    public void performBrowserAction() throws InterruptedException {
        String userName = data.get("UserName");
        String password = data.get("Password");
        test.automateOrangeHRM(userName,password);
    }
}
