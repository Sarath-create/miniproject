package testpackage;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import basepackage.baseclass;
import basepackage.reporterclass;

import pagepackage.loginpage;
import pagepackage.productpage;

public class producttest extends baseclass {

    loginpage log;
    productpage pdt;

    
    @Test(priority = 1)
    public void logo() throws InterruptedException, IOException {

        
        test = extent.createTest("Product Page Logo Validation");

        
        log = new loginpage(driver);

 
        pdt = new productpage(driver);

        
        WebElement x = pdt.logoverify();

        if (x.isDisplayed()) {

            test.pass("Logo displayed in product page");

        } else {

            String path = reporterclass.screenshotMethod(
                    driver, "product_logo_validation");

            test.addScreenCaptureFromPath(path);

            test.fail("Logo not displayed in product page");

            Assert.fail("Logo not displayed");
        }
    }

    
    @Test(priority = 2)
    public void additemstocart() throws InterruptedException {

        
        test = extent.createTest("Add All Products To Cart");

        
        pdt = new productpage(driver);

        
        List<WebElement> dtl = pdt.productdtls();

        System.out.println(
                "Total Number of Products: " + dtl.size());

        
        for (WebElement product : dtl) {

            System.out.println(product.getText());
        }

        
        Assert.assertEquals(dtl.size(), 6,
                "Expected six products on the product page");

        test.info("Six products displayed on product page");

        
        pdt.additems();

        test.pass("All products added to cart successfully");
    }
}

