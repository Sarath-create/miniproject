package pagepackage;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class productpage {

    WebDriver driver;

    
    @FindBy(xpath = "//div[@class='app_logo']")
    WebElement logo;

    
    @FindBy(xpath = "//*[text()='Add to cart']")
    List<WebElement> cartbutton;

    
    @FindBy(className = "inventory_item")
    List<WebElement> productdetails;

    
    public productpage(WebDriver driver) {

        this.driver = driver;

        PageFactory.initElements(driver, this);
    }

   
    public WebElement logoverify() {

        return logo;
    }

    
    public void additems() {

        for (WebElement items : cartbutton) {

            items.click();
        }
    }

    
    public List<WebElement> productdtls() {

        return productdetails;
    }
}


