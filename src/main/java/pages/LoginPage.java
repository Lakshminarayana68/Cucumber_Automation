package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.DriverFactory;

public class LoginPage {
	
	 private WebDriver driver;

	    // Constructor to initialize elements using PageFactory
	  

	    // Web elements using @FindBy annotation (Page Factory)
	    @FindBy(name = "email")
	    private WebElement usernameField;

	    @FindBy(name = "password")
	    private WebElement passwordField;

	    @FindBy(xpath="//*[@data-qa='login-button']")
	    private WebElement LoginBtn;
	    
	    
	    public LoginPage(WebDriver driver) {
	        this.driver = DriverFactory.getDriver();
	        PageFactory.initElements(driver, this); // Initializes all @FindBy elements
	    }

	    // Action methods with Java encapsulation
	    public void enterUsernameAndpassword(String username,String password) {
	        usernameField.sendKeys(username);
	        passwordField.sendKeys(password);
	    }
	    
	    
	    public String pageTitleVerify() {
	          return driver.getTitle();
	    }
	    
	    
	    public void clickOnLogin() {
	    	LoginBtn.click();
	    }
	    
	    
	    public WebElement getElement(String elementName) {
			switch(elementName.toLowerCase()) {
			case "LoginBtn":
				return LoginBtn;
			case "cart":
				return passwordField;
			
			default: throw new IllegalArgumentException("no such element in this page");
			}
		}

	    public void enterPassword(String password) {
	        passwordField.sendKeys(password);
	    }

//	    public void clickLogin() {
//	        loginBtn.click();
//	    }

//	    public void login(String username, String password) {
//	        enterUsername(username);
//	        enterPassword(password);
//	        clickLogin();
//	    }
}
