package pagepackage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class loginpage {

    WebDriver driver;

   
    @FindBy(id = "user-name")
    public WebElement username;

    
    @FindBy(id = "password")
    public WebElement pass;

    
    @FindBy(xpath = "//*[@id=\"login-button\"]")
    public WebElement login;

    
    @FindBy(xpath = "//div[@class='login_logo']")
    public WebElement logo;

   
    public loginpage(WebDriver driver) {

        this.driver = driver;

        PageFactory.initElements(driver, this);
    }

    
    public WebElement logoverification() {

        return logo;
    }

    
    public WebElement buttontext() {

        return login;
    }
    
    

    
    public void setvalue(String user, String pwd)
            throws InterruptedException {

        username.clear();
        username.sendKeys(user);

        pass.clear();
        pass.sendKeys(pwd);

        login.click();
    }
}
