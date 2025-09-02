package Utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import drivers.DriverManager;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.screenrecording.CanRecordScreen;
import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.testng.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

import static Utils.ScreenshotUtils.takeScreenshot;

public class Listeners implements ITestListener, ISuiteListener{

    static final Logger logger = Logger.getLogger(Listeners.class);
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
                File file=new File(screenshotPath);
                Allure.addAttachment("Screenshot", FileUtils.openInputStream(file));
                logger.error(result.getMethod().getMethodName()+"Failed");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        saveVideo(result.getName());
    }

    @Override
    public void onTestStart(ITestResult result) {

        ExtentTest test = extent.createTest(result.getMethod().getMethodName());
        testThread.set(test);
        ((CanRecordScreen) DriverManager.getDriver()).startRecordingScreen();
        logger.info(result.getMethod().getMethodName()+"Started");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        testThread.get().log(Status.PASS, "Test passed");
        saveVideo(result.getName());
        logger.info(result.getMethod().getMethodName()+"Passed");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        testThread.get().log(Status.SKIP, "Test skipped: " + result.getThrowable());
        logger.info(result.getMethod().getMethodName()+"Skipped");
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
        try {
            Runtime.getRuntime().exec("cmd /c start cmd.exe /K \"allure generate allure-results --clean -o allure-report && allure open allure-report\"");

        } catch (IOException e) {
            e.printStackTrace();
        }
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

    private void saveVideo(String testName) {
        String base64Video = ((CanRecordScreen) DriverManager.getDriver()).stopRecordingScreen();
        byte[] videoBytes = Base64.getDecoder().decode(base64Video);
        try {
            Path videoDir = Paths.get("videos");
            Files.createDirectories(videoDir);
            Files.write(videoDir.resolve(testName + ".mp4"), videoBytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
