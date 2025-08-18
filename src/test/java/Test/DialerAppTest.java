package Test;

import Base.BaseTest;
import Utils.JsonUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import page.WorkingOnMobileBrowsers;
import page.WorkingWithDialerButton;

import java.util.List;
import java.util.Map;

public class DialerAppTest extends BaseTest {

    private WorkingWithDialerButton test;
    private List<Map<String, String>> testData;
    Map<String, String> data;

    @BeforeClass
    public void setUpPage() {
        test = new WorkingWithDialerButton(driver);
        testData= JsonUtils.getJsonArrayFromJsonFile("BrowserTesting.json");
        data = testData.get(0);
    }

    @Test
    public void performDialerOperation() throws Exception {
        test.longPressOnDialerBackSpaceRotateLock();
    }

    @Test
    public void performCall(){
        test.performCallOperation();
    }
}
