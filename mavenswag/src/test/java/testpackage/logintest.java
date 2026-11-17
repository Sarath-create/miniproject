package testpackage;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import basepackage.baseclass;
import pagepackage.loginpage;
import utilitypackage.Exceltest;

public class logintest extends baseclass {

    loginpage log;

   
    @DataProvider(name = "loginData")
    public Object[][] getData() throws Exception {

        String path = "C:\\Users\\Sarath\\Downloads\\swaglabsdatas.xlsx";
        Object[][] data = Exceltest.getTestData(path, "Sheet1");

        System.out.println("Total Excel records: " + data.length);
        for (Object[] row : data) {
            System.out.println("Username: " + row[0]
                    + " | Password: " + row[1]);
        }

        return data;
    }


    

   
    @Test(priority = 1, dataProvider = "loginData")
    public void loginsetup(String user, String password)
            throws InterruptedException, IOException {

       
        driver.get(url);

        
        test = extent.createTest("Login Test - " + user);

        
        log = new loginpage(driver);

       
        WebElement logo = log.logoverification();
        if (logo.isDisplayed()) {
            System.out.println("Logo is verified");
            test.pass("Logo verification passed");
        } else {
            System.out.println("Logo is not displayed");
            test.fail("Logo verification failed");
        }
        
      




        String actualText = log.buttontext()
                .getDomAttribute("value");

        String expectedText = "Login";

        Assert.assertEquals(actualText, expectedText,
                "Login button text is incorrect");
        
      
        test.info("Login button text verification passed");
        System.out.println("Testing username: " + user);
        System.out.println("Login button text verification passed");


        
        log.setvalue(user, password);

        System.out.println("Executed for: " + user);

        
        String currentUrl = driver.getCurrentUrl();

        String expectedUrl =
                "https://www.saucedemo.com/inventory.html";

        if (user.equals("standard_user")
                && password.equals("secret_sauce")) {

            Assert.assertEquals(currentUrl, expectedUrl,
                    "Valid login was unsuccessful");

            test.pass("Login successful for: " + user);

        } else {

            
            Assert.assertEquals(currentUrl, url,
                    "Invalid user unexpectedly logged in");

            WebElement error = driver.findElement(
                    By.cssSelector("[data-test='error']"));

            Assert.assertTrue(error.isDisplayed(),
                    "Login error message is not displayed");

            test.pass("Invalid login correctly rejected for: " + user);
        }
    }
}

	
	


