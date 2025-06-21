package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class SignUp extends BasePage {
	
	public SignUp() {
		super();
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath="//*[text()='Enter Account Information']")
	private WebElement signup_header;
	
	@FindBy(id="id_gender1")
	private WebElement radio_btn1;
	
	@FindBy(id="id_gender2")
	private WebElement radio_btn2;
	
	@FindBy (id="password")
	private WebElement password;
	
	@FindBy(id="days")
	private WebElement day;
	
	@FindBy(id="months")
	private WebElement month;
	
	@FindBy(id="years")
	private WebElement year;
	
	@FindBy(id="newsletter")
	private WebElement newletter_checkbox;
	
	@FindBy(css="#first_name")
	private WebElement first_name;
	
	@FindBy(css="#last_name")
	private WebElement last_name;
	
	@FindBy(id="address1")
	private WebElement address;
	
	@FindBy(id="country")
	private WebElement country;
	
	@FindBy(id="state")
	private WebElement state;
	
	@FindBy(id="city")
	private WebElement city;
	
	@FindBy(id="zipcode")
	private WebElement zipcode;
	
	@FindBy(name="mobile_number")
	private WebElement mobile_number;
	@FindBy(xpath=("//*[text()='Create Account']"))
	private WebElement create_account;
	
	
	
	
	public WebElement getElement(String elementName) {
		
		switch(elementName.toLowerCase()) {
		
		case "signup_header":
		  return signup_header;
		case "male":
			return radio_btn1;
		case "Female":
			return radio_btn2;
		case "password":
			return password;
		case "day":
			return day;
		case "month":
			return month;
		case "year":
			return year;
		case "newsletter":
			return newletter_checkbox;
		case "first_name":
			return first_name;
		case "last_name":
			return last_name;
		case "address":
			return address;
		case "country":
			return country;
		case "state":
			return state;
		case "zipcode":
			return zipcode;
		case "mobile_number":
			return mobile_number;
		case "city":
			return city;
		case "create_account":
			return create_account;
		default:
			throw new RuntimeException("element not found in page!!!");
		
		
		
		
		
		
		
		
		
		  
		}
	}


	@Override
	public boolean pageTitleVerify() {
		// TODO Auto-generated method stub
		String title=driver.getTitle();
		return title.contains("Automation Exercise - Signup");
	}
	

}
