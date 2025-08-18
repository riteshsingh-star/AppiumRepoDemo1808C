package Test;

import Base.BaseTest;
import Utils.JsonUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import page.WorkingWithAPIDemos;
import java.util.List;
import java.util.Map;

public class WorkingWithApiDemoTest extends BaseTest {

    private WorkingWithAPIDemos test;
    private List<Map<String, String>> testData;
    Map<String, String> data;

    @BeforeClass
    public void setUpPage() {
        test = new WorkingWithAPIDemos(driver);
       testData= JsonUtils.getJsonArrayFromJsonFile("ApiDemos.json");
        data = testData.get(0);
    }

    @Test(priority = 0)
    public void simpleTextareaCheckBox() {
        String text=data.get("TextField");
        test.simpleCheckBoxTextArea(text);
    }

    @Test(priority = 1)
    public void dropDownActionsTest() throws InterruptedException {
        test.dropDownActions();
    }

    @Test(priority = 2)
    public void scrollSwitches(){
        test.scrollAndSwitches();
    }

    @Test(priority = 3)
    public void dragDrop() throws Exception {
        test.dragAndDrop();
    }

    @Test(priority = 4)
    public void interactWithHardwareKeys() throws Exception {
        test.hardwareKeysInteraction();
    }

   // @Test(priority = 5)
    public void installAndUninstallApps() throws Exception {
        test.installAndUninstall();
    }

}
