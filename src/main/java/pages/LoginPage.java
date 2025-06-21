package pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import utilities.DriverFactory;


import org.testng.Assert;

//import utilities.DriverFactory;

public class LoginPage extends BasePage {
	
	  public LoginPage() {
	        super();
	        PageFactory.initElements(driver, this);
	    }
	

	    @FindBy(name = "email")
	    private WebElement usernameField;

	    @FindBy(name = "password")
	    private WebElement passwordField;

	    @FindBy(xpath="//*[@data-qa='login-button']")
	    private WebElement LoginBtn;
	    

	    
	  

	    @FindBy(xpath="//*[@name='name']")
	    private WebElement username;
	    
	    @FindBy(xpath="//*[@data-qa='signup-email']")
	    private WebElement email;
	    
	    @FindBy(xpath="//*[text()='New User Signup!']")
	    private WebElement signup_text;
	    
	    @FindBy(xpath="//*[text()='Signup']")
	    private WebElement signup_button;
	    
	    
	    
	    



	    // Action methods with Java encapsulation
	    public void enterUsernameAndpassword(String username,String password) {
	        usernameField.sendKeys(username);
	        passwordField.sendKeys(password);
	    }
	    
	    

	    public boolean pageTitleVerify() {
	          String title=driver.getTitle();
	          return title.contains("Automation Exercise - Signup / Login");
	    }
	    
	    
	    public void clickOnLogin() {
	    	LoginBtn.click();
	    }
	    
	    
	    public WebElement getElement(String elementName) {

			

	        switch (elementName.toLowerCase()) {
	            case "username":
	                return username;
	            case "email":
	                return email;
	            case "loginbtn":
	                return LoginBtn;
	            case "usernamefield":
	                return usernameField;
	            case "passwordfield":
	                return passwordField;
	            case "signup header":
	            	return signup_text;
	            case "signup":
	            	return signup_button;
	            default:
	                throw new IllegalArgumentException("No such element: " + elementName);
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
