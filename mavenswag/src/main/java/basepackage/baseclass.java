package basepackage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import java.util.HashMap;
import java.util.Map;

import com.aventstack.extentreports.ExtentReports;

public class baseclass {

    public static ExtentReports extent;
    public static WebDriver driver;
    public static ExtentTest test;

    public String url = "https://www.saucedemo.com/";

    @BeforeSuite
    public void browser_launch() {

        
        extent = reporterclass.sam();

        
        EdgeOptions options = new EdgeOptions();

        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);

        options.setExperimentalOption("prefs", prefs);
        options.addArguments("--disable-features=PasswordLeakDetection");

        driver = new EdgeDriver(options);
       

        
        driver.get(url);

        
        driver.manage().window().maximize();
    }

    @AfterSuite
    public void teardown() {


        if (extent != null) {
            extent.flush();
        }
    }
}
