package Test;

import Base.BaseTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import page.WorkingOnWdioDemo;


public class WdioDemoTest extends BaseTest {

    private WorkingOnWdioDemo test;


    @BeforeClass
    public void setUpPage() {
        test = new WorkingOnWdioDemo(driver);
    }


    @Test
    public void dragAndDropOperationOnRobotPuzzle(){
        test.workingOnDragAndDropRoboPuzzle();
    }
}
