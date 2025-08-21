package Test;

import Base.BaseTest;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import page.WorkingOnWdioDemo;


public class WdioDemoTest extends BaseTest {

    static final Logger logger = Logger.getLogger(WdioDemoTest.class);
    private WorkingOnWdioDemo test;


    @BeforeClass
    public void setUpPage() {
        test = new WorkingOnWdioDemo(driver);
    }


    @Test
    public void dragAndDropOperationOnRobotPuzzle(){
        logger.info("Start dragAndDropOperationOnRobotPuzzle");
        test.workingOnDragAndDropRoboPuzzle();
        logger.info("Finished dragAndDropOperationOnRobotPuzzle");
    }
}
