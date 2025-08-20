package Utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReportManager {

    private static ExtentReports extent;
    private static ExtentSparkReporter spark;
    public static ExtentReports getInstance() {
        if (extent == null) {
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String reportPath = "reports/AppiumTest_" + timestamp + ".html";
            spark = new ExtentSparkReporter(reportPath);
            spark.config().setReportName("AppiumTest_" + timestamp);
            spark.config().setDocumentTitle("Appium Test Report");
            spark.config().setTheme(Theme.DARK);
            extent = new ExtentReports();
            extent.attachReporter(spark);
            extent.setSystemInfo("Platform", "Android");
            extent.setSystemInfo("Tester Name", "Appium Tester");
            extent.setSystemInfo("OS Version", "13.0");
        }
        return extent;
    }
}
