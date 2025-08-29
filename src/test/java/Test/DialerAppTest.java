package Test;

import Base.BaseTest;
import Utils.JsonUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import page.WorkingOnMobileBrowsers;
import page.WorkingWithDialerButton;

import java.util.ArrayList;
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

    @Test(priority = 0)
    public void findDuplicates(){
        List<String> duplist = new ArrayList<>();
        duplist.add("first");
        duplist.add("second");
        duplist.add("third");
        duplist.add("fourth");
        duplist.add("first");
        for(int i=0;i<duplist.size();i++){
            if(duplist.get(i)==duplist.get(i-1)){
                System.out.println(duplist.get(i));
            }
        }
    }
}
