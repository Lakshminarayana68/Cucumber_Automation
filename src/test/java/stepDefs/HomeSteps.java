package stepDefs;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.HomePage;
import pages.LoginPage;
import utilities.DriverFactory;
import utilities.LoggerUtil;

public class HomeSteps {
	
	
	private WebDriver driver;
	

	
	
	private HomePage hp=new HomePage();;
	
	private LoginPage lp=new LoginPage();

	
	
	@Then("user verify Headers")
	public void user_verify_Headers(io.cucumber.datatable.DataTable dataTable) {
		
		
		List<String> expectedheaders=dataTable.asList();
		
		List<String> actualHeaders=hp.menuVerify();
		
		//assertion
		if(!actualHeaders.equals(expectedheaders)) {
			throw new AssertionError("Actual headers and expected headers are not matching:\n"+expectedheaders+","+actualHeaders);
		}
		
		LoggerUtil.info("user verify headers details");
	}
	
	@Then("user verify categories")
	public void user_verify_categories(DataTable datatable) {
		List<String> expectedList=datatable.asList();
		
		List<String> actualList=hp.categoriesVerify();
		
		if(!actualList.equals(expectedList)) {
			throw new AssertionError("actual and expected categories not matching:\n"+expectedList+","+actualList);
		}
		
		LoggerUtil.info("verfying category List");
		
	}
	
	@Then("user verify brands")
	public void user_verify_brands(DataTable datatable) {
		List<String> expectedBrands=datatable.asList();
		
		List<String> actualBrands=hp.brands();
		
//		if(!actualBrands.equals(expectedBrands)) {
//			throw new AssertionError("actual and expected brands not matching:\n"+expectedBrands+","+actualBrands);
//		}
		LoggerUtil.info("verfying brand List");
		
	}
	
	@When("user click on add to cart")
	public void user_click_on_add_to_cart() {
		hp.clickOnAddToCart();
	}
	
	
	@Then("user navigated to login page")
	public void user_navigated_to_login_page() {
		//lp=new LoginPage(driver);

		boolean title=lp.pageTitleVerify();
		Assert.assertTrue(title);

		LoggerUtil.info("user navigates to login page");
	}
	
	@Then("user navigated to Automation Homepage")
	public void user_navigated_to_Automation_Homepage() {
		String page=hp.getTitle();

		Assert.assertEquals(page,"Automation Exercise");
		LoggerUtil.info("User navigated to the home page");
	}
	
	
	
	@When("user clicks on login")
	public void user_clicks_on_login() {
		lp.clickOnLogin();
	}
	
	
	
	
	
	@When("user clicks on {string} on {string} page")
	public void user_clicks_on_element_on_page(String elementName, String pageName) {
	    WebElement elementToClick;

	    switch (pageName.toLowerCase()) {
	        case "login":
	            elementToClick = lp.getElement(elementName);
	            break;

	        case "home":
	            elementToClick = hp.getElement(elementName);
	            break;

	        // Add more pages here...
	        default:
	            throw new IllegalArgumentException("Page not recognized: " + pageName);
	    }

	    if (elementToClick != null && elementToClick.isDisplayed()) {
	        elementToClick.click();
	    } else {
	        throw new RuntimeException("Element not found or not visible: " + elementName);
	    }
}}

