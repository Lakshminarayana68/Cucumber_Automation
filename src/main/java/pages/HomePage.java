package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.DriverFactory;

public class HomePage {
   
	
	private WebDriver driver;
	
	@FindBy(xpath="//*[text()='Home']")
	private WebElement Home;
	
	@FindBy(xpath="//*[text()='Products']")
	private WebElement Products;
	
	@FindBy(xpath="//*[text()='Cart']")
	 private WebElement cart;
	
	@FindBy(xpath="//*[text()=' Signup / Login']")
	private WebElement signup_login;
	
	@FindBy(xpath="//*[@id='accordian']//h4")
	private List<WebElement> categoryList;
	
	@FindBy(xpath="//*[@class='nav nav-pills nav-stacked']//li//a[normalize-space()]")
	private List<WebElement> brandList;
	
	@FindBy(xpath="//*[text()=' Products']")
	private WebElement products;
	
	@FindBy(xpath="(//*[text()='View Product'])[1]")
	private WebElement addtoCart;
	
//	@FindBy(xpath="//*[@class='nav navbar-nav']//li")
//	private List<WebElement> menuList;
	
	
	// Signup / Login
	
	public HomePage(WebDriver driver) {
		this.driver=DriverFactory.getDriver();
		PageFactory.initElements(driver,this);
	}
	
	
	public void clickOnAddToCart() {
		addtoCart.click();
	}
	
	public List<String> categoriesVerify(){
		List<String> list1= new ArrayList<>();
		for(WebElement option:categoryList) {
			String cat=option.getText();
			
			list1.add(cat);
			
		}
		
		return list1;
		
	}
	
	public List<String> brands(){
		List<String> list2=new ArrayList<>();
		for(WebElement option:brandList) {
			String brand=option.getText().trim().replaceAll("\\(\\d+\\)", "");
			list2.add(brand);
		}
		
		return list2;
	}
	
	private JavascriptExecutor js;
	
	
	public List<String> menuVerify() {
		js=(JavascriptExecutor)driver;
		
		List<WebElement> menuList=driver.findElements(By.xpath("//*[@class='nav navbar-nav']//li"));
		List<String> headers=new ArrayList<>();
		for(WebElement header:menuList) {
			
			headers.add(header.getText().trim());
		}
		
		return headers;
	}
	
	
	public String getTitle() {
		return driver.getTitle();
	}
	
//	public void enterUserAndPwd(String username,String password) {
//		
//	}
	
	public WebElement getElement(String elementName) {
		switch(elementName.toLowerCase()) {
		case "signup_login":
			return signup_login;
		case "cart":
			return cart;
		
		default: throw new IllegalArgumentException("no such element in this page");
		}
	}
	
}
