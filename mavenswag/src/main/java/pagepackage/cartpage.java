package pagepackage;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class cartpage {

    WebDriver driver;

   
    @FindBy(id = "shopping_cart_container")
    WebElement carticon;

    
    @FindBy(id = "checkout")
    WebElement checkout;

    
    @FindBy(id = "first-name")
    WebElement firstname;

    
    @FindBy(id = "last-name")
    WebElement lastname;

    
    @FindBy(id = "postal-code")
    WebElement zipcode;

    
    @FindBy(id = "continue")
    WebElement continuebutton;

    
    @FindBy(id = "finish")
    WebElement finish;

    
    @FindBy(id = "back-to-products")
    WebElement backhome;
    @FindBy(id = "react-burger-menu-btn")
    WebElement menu;

    @FindBy(xpath = "//*[@id=\"logout_sidebar_link\"]")
    WebElement logoutbutton;
    @FindBy(className = "complete-header")
    WebElement confirmation;

    
    public cartpage(WebDriver driver) {

        this.driver = driver;

        PageFactory.initElements(driver, this);
    }

    
    public void cartclick() {

        carticon.click();

        checkout.click();
    }

    
    public void customerDetails(
            String fname, String lname, String i) {

        firstname.sendKeys(fname);

        lastname.sendKeys(lname);

        zipcode.sendKeys(i);

        continuebutton.click();
    }

    
    public void finishorder() {

        finish.click();
    }

   
    public void backhomeclick() {

        backhome.click();
    }
    public void logout() {

        WebDriverWait wait = new WebDriverWait(
                driver, Duration.ofSeconds(10));

        menu.click();

        wait.until(ExpectedConditions.elementToBeClickable(logoutbutton));

        logoutbutton.click();
    }
    public WebElement orderconfirmation() {
        return confirmation;
    }
    
    public void checkoutclick()
    {
    	checkout.click();
    }
    public void opencart() {
        carticon.click();
    }

    
}
	


