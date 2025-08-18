package Utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.appium.java_client.AppiumDriver;
import org.testng.*;
import java.lang.reflect.Field;

import static Utils.ScreenshotUtils.takeScreenshot;

public class Listeners implements ITestListener, ISuiteListener{

    private static ExtentReports extent = ExtentReportManager.getInstance();
    private static ThreadLocal<ExtentTest> testThread = new ThreadLocal<>();

    @Override
    public void onTestFailure(ITestResult result) {
        testThread.get().log(Status.FAIL, "Test failed: " + result.getThrowable());
        Object testClass = result.getInstance();
        AppiumDriver driver = getDriverFromBase(testClass);

        if (driver != null) {
            String screenshotPath = takeScreenshot(driver, result.getMethod().getMethodName());
            try {
                testThread.get().addScreenCaptureFromPath(screenshotPath);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest test = extent.createTest(result.getMethod().getMethodName());
        testThread.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        testThread.get().log(Status.PASS, "Test passed");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        testThread.get().log(Status.SKIP, "Test skipped: " + result.getThrowable());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext context) {

    }

    @Override
    public void onFinish(ISuite suite) {
        extent.flush();
    }

    private AppiumDriver getDriverFromBase(Object instance) {
        try {
            Field driverField = instance.getClass().getSuperclass().getDeclaredField("driver");
            driverField.setAccessible(true);
            return (AppiumDriver) driverField.get(instance);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
