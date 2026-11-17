package basepackage;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class reporterclass {

    public static ExtentSparkReporter reporter;
    public static ExtentReports extent;

    public static ExtentReports sam() {

        
        new File(".\\Reports").mkdirs();

        
        reporter = new ExtentSparkReporter(
                ".\\Reports\\swag_report.html");

        reporter.config().setDocumentTitle("Automation Report");
        reporter.config().setReportName("SauceDemo Functional Testing");
        reporter.config().setTheme(Theme.DARK);

        
        extent = new ExtentReports();

        extent.attachReporter(reporter);

        
        extent.setSystemInfo("Hostname", "localhost");
        extent.setSystemInfo("OS", "Windows 11");
        extent.setSystemInfo("Tester Name", "Tester");
        extent.setSystemInfo("Browser Name", "Edge");

        return extent;
    }

    @AfterMethod
    public void browserclose(ITestResult result) throws IOException {

        if (baseclass.test == null) {
            return;
        }

        if (result.getStatus() == ITestResult.FAILURE) {

            baseclass.test.log(Status.FAIL,
                    "Test case failed: " + result.getName());

            baseclass.test.log(Status.FAIL,
                    result.getThrowable());

            String screenshotName = result.getName();

            String path = screenshotMethod(
                    baseclass.driver, screenshotName);

            baseclass.test.addScreenCaptureFromPath(path);
        }

        else if (result.getStatus() == ITestResult.SKIP) {

            baseclass.test.log(Status.SKIP,
                    "Test case skipped: " + result.getName());
        }

        else if (result.getStatus() == ITestResult.SUCCESS) {

            baseclass.test.log(Status.PASS,
                    "Test case passed: " + result.getName());
        }
    }

    public static String screenshotMethod(
            WebDriver driver, String screenshotname)
            throws IOException {

        
        File folder = new File(".\\Reports\\screenshot");
        folder.mkdirs();

        
        File src = ((TakesScreenshot) driver)
                .getScreenshotAs(OutputType.FILE);

        String destination = ".\\Reports\\screenshot\\"
                + screenshotname + ".png";

        
        FileHandler.copy(src, new File(destination));

        return destination;
    }
}

