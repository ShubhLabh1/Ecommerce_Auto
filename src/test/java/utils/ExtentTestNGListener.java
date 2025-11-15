// java
package utils;

import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentTestNGListener implements ITestListener {
    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();


    @Override
    public void onStart(ITestContext context) {
            try {
                /*/ Create report directory
                Path out = Path.of("target", "extent-report");
                Files.createDirectories(out);

                // Generate unique timestamp for each run
                String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
                String reportName = "ExtentReport_" + timestamp + ".html";

                // Create a new report file for each execution
                ExtentSparkReporter spark = new ExtentSparkReporter(out.resolve(reportName).toString());

                extent = new ExtentReports();
                extent.attachReporter(spark);

                // Optional: add system info
                extent.setSystemInfo("Environment", "QA");
                extent.setSystemInfo("Tester", "YourName"); */

                // Create base report folder
                Path reportFolder = Path.of("target", "extent-reports");
                Files.createDirectories(reportFolder);

                // Add timestamp to make each report unique
                String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
                String reportFileName = "ExtentReport_" + timestamp + ".html";

                // Full path for the report
                String reportPath = reportFolder.resolve(reportFileName).toString();

                // Create the reporter
                ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
                spark.config().setReportName("Automation Test Results - " + timestamp);
                spark.config().setDocumentTitle("Execution Report");

                // Attach to ExtentReports
                extent = new ExtentReports();
                extent.attachReporter(spark);
                extent.setSystemInfo("Environment", "QA");
                extent.setSystemInfo("Tester", "Shubham");
            } catch (Exception e) {
                e.printStackTrace();
            }
    }


    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest t = extent.createTest(result.getMethod().getMethodName());
        test.set(t);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.get().pass("Test passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.get().fail(result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.get().skip("Test skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        if (extent != null) extent.flush();
    }

    // Optional getter if you want to log extra details from test classes
    public static ExtentTest getTest() {
        return test.get();
    }
}