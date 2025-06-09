package stepDefs;

import static org.testng.Assert.fail;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.aventstack.extentreports.Status;

import hooks.ExtentHooks;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.LoginPage;
import testbase.BaseTest;
import utilities.ConfigReader;
import utilities.DriverFactory;
import utilities.ExcelUtils;
import utilities.LoggerUtil;
import utilities.ScreenShotUtils;


public class LoginSteps  {
	
	 private WebDriver driver;
	    private LoginPage loginPage;
	    
	   
	   
	    @Given("user launches the browser")
	    public void user_launches_browser() {
	        driver = DriverFactory.initDriver();
            driver.get(ConfigReader.getProperty("url"));
	    	
	        loginPage = new LoginPage(driver);
	       LoggerUtil.info("User launches app");
	       
	        
	        
	    }
	    
	    @When("user navigates to Home page")
	    public void user_navigates_to_Home_page() throws InterruptedException {
	    	String txt=driver.getTitle();
	    	Assert.assertEquals(txt,"Automation Exercise");
	    	Thread.sleep(6000);
	    	
	    
	    	
	    	LoggerUtil.info("User navigated to home page");
	    	
	    }
	    
	    @When("user enter {string} and {string}")
		public void user_enter(String username,String password) {
			loginPage.enterUsernameAndpassword(username, password);
		}

//	    @When("user logs in using data from excel sheet {string} row {int}")
//	    public void user_logs_in_using_excel_data(String sheetName, Integer row) {
//	        String username = ExcelUtils.getCellValue(sheetName, row, 0);
//	        String password = ExcelUtils.getCellValue(sheetName, row, 1);
//	        loginPage.login(username, password);
//	        LoggerUtil.info("Logged in using Username: " + username);
//	    }

	    @Then("user should be redirected to homepage")
	    public void verify_user_redirected() {
	        // Placeholder: Add assertion based on your homepage
	        LoggerUtil.info("User successfully redirected to homepage.");
	    }
	 

}
