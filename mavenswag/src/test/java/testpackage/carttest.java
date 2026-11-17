package testpackage;

import static org.testng.Assert.fail;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import basepackage.baseclass;
import basepackage.reporterclass;

import pagepackage.cartpage;
import pagepackage.loginpage;

public class carttest extends baseclass {

    cartpage cart;

    @Test(priority = 1)
    public void checkout() throws IOException {

        
        test = extent.createTest("Cart and Checkout Test");

        
        cart = new cartpage(driver);

        
        cart.cartclick();

        test.info("Cart opened and Checkout clicked");

        
        cart.customerDetails("zam", "Turner", "7785");

        test.info("Customer details entered successfully");

        
        cart.finishorder();

        test.info("Finish button clicked");

        
        WebElement confirmation = cart.orderconfirmation();

        if (confirmation.isDisplayed()) {

            String actualMessage = confirmation.getText();

            Assert.assertEquals(
                    actualMessage,
                    "Thank you for your order!",
                    "Order confirmation message is incorrect");

            test.pass("Order placed successfully");

        } else {

            String path = reporterclass.screenshotMethod(
                    driver, "order_confirmation_failure");

            test.addScreenCaptureFromPath(path);

            test.fail("Order confirmation not displayed");

            Assert.fail("Order confirmation not displayed");
        }

        
        cart.backhomeclick();

        test.info("Back Home button clicked");

        
        Assert.assertTrue(
                driver.getCurrentUrl().contains("inventory.html"),
                "Did not navigate back to Products page");

        test.pass("Successfully returned to Products page");
    }

    
    @Test(priority = 2)
    public void logouttest() throws IOException {

        test = extent.createTest("Logout Test");

        cart = new cartpage(driver);

        
        cart.logout();

        
        Assert.assertEquals(
                driver.getCurrentUrl(),
                url,
                "Logout was unsuccessful");

        test.pass("Logout successful");
    }  
        

    	@Test(priority = 3)
    	public void CheckoutWithoutProducts() throws IOException, InterruptedException {

    	    test = extent.createTest("Checkout Without Products Test");

    	    driver.get(url);

    	    
    	    loginpage log = new loginpage(driver);
    	    log.setvalue("standard_user", "secret_sauce");

    	    test.info("Login successful");

    	    
    	    cart = new cartpage(driver);
    	    cart.opencart();

    	    test.info("Cart page opened");

    	   
    	    int cartItems = driver.findElements(
    	            By.className("cart_item")).size();

    	    Assert.assertEquals(cartItems, 0,
    	            "Cart is not empty");

    	    test.info("Cart is empty");

    	    
    	    String cartUrl = driver.getCurrentUrl();

    	    cart.checkoutclick();

    	  
    	    if (driver.getCurrentUrl().contains("checkout-step-one.html")) {

    	        String path = reporterclass.screenshotMethod(
    	                driver, "empty_cart_checkout_failure");

    	        test.addScreenCaptureFromPath(path);

    	        test.fail("Bug: Checkout allowed without adding products");

    	        Assert.fail("Checkout allowed with an empty cart");

    	    } else {

    	        Assert.assertEquals(
    	                driver.getCurrentUrl(),
    	                cartUrl,
    	                "Unexpected navigation after checkout click");

    	        test.pass("Checkout correctly prevented for an empty cart");
    	    }
    	}
    	
    	
    	   @Test(priority = 4)
    	    public void postalCodeValidation() throws IOException {

    	        test = extent.createTest("Postal Code Validation Test");

    	        
    	        cart = new cartpage(driver);
    	        cart.cartclick();

    	        test.info("Cart opened and Checkout clicked");

    	        
    	        cart.customerDetails("Sam", "Smith", "abcd");

    	        test.info("Entered alphabetic characters in Postal Code field");

    	        
    	        if (driver.getCurrentUrl().contains("checkout-step-two.html")) {

    	            String path = reporterclass.screenshotMethod(
    	                    driver, "postal_code_validation_failure");

    	            test.addScreenCaptureFromPath(path);

    	            test.fail("BUG: Postal Code field accepts alphabetic characters");

    	            Assert.fail("Postal Code field should not accept alphabets");

    	        } else {

    	            test.pass("Postal Code validation correctly rejected alphabets");
    	        }
    	    }
    	   
    	   @Test(priority = 5)
    	   public void firstandlastname() throws IOException
    	   {
    		   test = extent.createTest("first and last name single character validation");
    		   cart = new cartpage(driver);
    		   cart.cartclick();
    		   test.info("Cart opened and Checkout Clicked");
    		   cart.customerDetails("m", "v", "1");
    		   test.info("Entered single characters in first name field and last name field at the same time");
    		   if (driver.getCurrentUrl().contains("checkout-step-two.html")) {
    			   
    			   String path = reporterclass.screenshotMethod(driver, "first and last name field failure");
    			   test.addScreenCaptureFromPath(path);
    			   test.fail("BUG: first name field and last name field accepts single characters the same time");
    			   Assert.fail("first name and last name field should not accept single characters at the same time");
    			   
    			   
    		   }
    		   else {
    			   test.pass("first and last name reject single characters");
    		   }
    	   }
    	   @Test(priority = 6)
    	   public void singlepostalcode() throws IOException {
    		   
    		   test = extent.createTest("single character on post code validation");
    		   cart = new cartpage(driver);
    		   cart.cartclick();
    		   test.info("cart opened and Checkout Clicked");
    		   cart.customerDetails("zam", "turner", "1");
    		   test.info("Entered Single Character in Postal Code");
    		   if(driver.getCurrentUrl().contains("checkout-step-two.html")) {
    			   
    			   String path = reporterclass.screenshotMethod(driver, "single Character Postal Code Failure");
    			   test.addScreenCaptureFromPath(path
    			   );
    			   test.fail("BUG: postal code should not accept single characters");
    			   Assert.fail("Postal Code Should not Accept Single Characters");
    			   
    		   }
    		   else {
    			   test.pass("single character entry on postal field is rejected upon clicking Countinue");
    		   }
    				   
    	   }
    	   
    	   @Test(priority = 7)
    	   public void maxlengthfirstname() throws IOException
    	   {
    		   test = extent.createTest("max length on first name field validation");
    		   cart = new cartpage(driver);
    		   cart.cartclick();
    		   test.info("cart opened and Checkout clicked");
    		   cart.customerDetails("ddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd", "turner","5575");
    		   test.info("Enterd 50+ characters in first name ");
    		   if(driver.getCurrentUrl().contains("checkout-step-two.html")) {
    			   String path = reporterclass.screenshotMethod(driver,"50+ character in first name failure");
    			   test.addScreenCaptureFromPath(path);
    			   test.fail("BUG: first name field should not accept 50+ characters");
    			   Assert.fail("First name Field Should not accept 50+ characters");
    		   }
    		   else {
    			   test.pass("50+characters are rejected in firstname upon clicking continue");
    		   }
    		   
    		   
    	   }
    	   @Test(priority = 8)
    	   public void maxlengthlastname() throws IOException
    	   {
    		   test = extent.createTest("max length on first name field validation");
    		   cart = new cartpage(driver);
    		   cart.cartclick();
    		   test.info("cart opened and Checkout clicked");
    		   cart.customerDetails("zam", "ddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd","5575");
    		   test.info("Enterd 50+ characters in last name ");
    		   if(driver.getCurrentUrl().contains("checkout-step-two.html")) {
    			   String path = reporterclass.screenshotMethod(driver,"50+ character in last name failure");
    			   test.addScreenCaptureFromPath(path);
    			   test.fail("BUG: last name field should not accept 50+ characters");
    			   Assert.fail("last name Field Should not accept 50+ characters"); 
    	   }
    		   else {
    			   test.pass("50+ characters rejected in last name field upon clicking continue");
    		   }
    	   }
    	   
    	   @Test(priority = 9)
    	   public void numbersinfirstname() throws IOException
    	   {
    		   test = extent.createTest("numbers on first name field validation");
    		   cart = new cartpage(driver);
    		   cart.cartclick();
    		   test.info("cart opened and Checkout clicked");
    		   cart.customerDetails("123", "turner","5575");
    		   test.info("Enterd numbers in first name ");
    		   if(driver.getCurrentUrl().contains("checkout-step-two.html")) {
    			   String path = reporterclass.screenshotMethod(driver,"numbers in first name failure");
    			   test.addScreenCaptureFromPath(path);
    			   test.fail("BUG: first name field should not accept numbers");
    			   Assert.fail("first name field should not accept numbers "); 
    	   }
    		   else {
    			   test.pass("numbers get rejected from first name field upon clicking continue");
    		   }
    	   }
    	   @Test(priority = 10)
    	   public void numbersinlastname() throws IOException {
    		   
    		   test = extent.createTest("numbers on last name field validation");
    		   cart = new cartpage(driver);
    		   cart.cartclick();
    		   test.info("cart opened and Checkout clicked");
    		   cart.customerDetails("zam", "123","5575");
    		   test.info("Enterd numbers in last name");
    		   if(driver.getCurrentUrl().contains("checkout-step-two.html")) {
    			   String path = reporterclass.screenshotMethod(driver,"numbers in last name failure");
    			   test.addScreenCaptureFromPath(path);
    			   test.fail("BUG: last name field should not accept numbers");
    			   Assert.fail("last name field should not accept numbers "); 
    	   }
    		   else {
    			   test.pass("numbers get rejected from last name field upon clicking continue");
    		   }
    		   
    	   }
    	   
    	   @Test(priority = 11)
    	   public void maxlengthinpostalcode() throws IOException
    	   {
    		   test = extent.createTest("50+ characters on post code validation");
    		   cart = new cartpage(driver);
    		   cart.cartclick();
    		   test.info("cart opened and Checkout Clicked");
    		   cart.customerDetails("zam", "turner", "9878465456135553131231231322313213154546869879443513212316549879454");
    		   test.info("Entered 50+ Characters in Postal Code");
    		   if(driver.getCurrentUrl().contains("checkout-step-two.html")) {
    			   
    			   String path = reporterclass.screenshotMethod(driver, "50+ Characters Postal Code Failure");
    			   test.addScreenCaptureFromPath(path
    			   );
    			   test.fail("BUG: postal code should not accept 50+ characters");
    			   Assert.fail("Postal Code Should not Accept 50+ Characters");
    			   
    		   }
    		   else {
    			   test.pass("50+ characters entry on postal field is rejected upon clicking Countinue");
    		   }
    	   }

    
    @Test(priority = 12)
    public void details() {
    	
    	cart.customerDetails("zam", "Turner", "7785");
    	cart.finishorder();
    }
    
 
}

    
