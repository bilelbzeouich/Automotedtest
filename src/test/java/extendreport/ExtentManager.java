package extendreport;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentManager {
    private static ExtentReports extent;
    private static String reportPath;
    private static boolean initialized = false;

    public static synchronized ExtentReports getInstance() {
        // Always create a new report with timestamp for each test run
        if (!initialized) {
            String timeStamp = new SimpleDateFormat("yyyyMMdd-HHmmss").format(new Date());
            reportPath = "target/ExtentReport_" + timeStamp + ".html";

            ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
            reporter.config().setTheme(Theme.DARK);
            reporter.config().setDocumentTitle("Test Report - " + timeStamp);
            reporter.config().setReportName("Test Execution Report - " + timeStamp);
            extent = new ExtentReports();
            extent.attachReporter(reporter);

            System.out.println("ExtentReport generated at: " + reportPath);
            initialized = true;
        }
        return extent;
    }

    public static String getReportPath() {
        return reportPath;
    }

    public static void reset() {
        if (extent != null) {
            extent.flush();
            extent = null;
        }
        initialized = false;
        reportPath = null;
    }
}
