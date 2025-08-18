package Test;

import Base.BaseTest;
import Utils.JsonUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import page.WorkingWithAPIDemos;
import page.WorkingWithGeneralStore;

import java.util.List;
import java.util.Map;

public class WorkingWithGeneralStoreTest extends BaseTest {

    private WorkingWithGeneralStore test;
    private List<Map<String, String>> testData;
    Map<String, String> data;

    @BeforeClass
    public void setUpPage() {
        test = new WorkingWithGeneralStore(driver);
        testData= JsonUtils.getJsonArrayFromJsonFile("GeneralStore.json");
        data = testData.get(0);
    }

    @Test
    public void basicActionOnGeneralStoreApp() throws Exception {
        String dropDownValue=data.get("dropDownValue");
        String nameValue=data.get("nameValue");
        String gender=data.get("gender");
        String itemType=data.get("itemType");
        String viewName1=data.get("viewName1");
        String viewName2=data.get("viewName2");

        test.workingWithGeneralStoreApk(dropDownValue,nameValue,gender,itemType,viewName1,viewName2);
    }

}
