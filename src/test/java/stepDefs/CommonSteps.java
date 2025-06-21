package stepDefs;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.time.Duration;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.BasePage;
import utilities.DataGenerator;
import utilities.DriverFactory;
import utilities.ExcelReader;
import utilities.PageContextManager;
import utilities.WaitUtils;

public class CommonSteps extends BasePage {

	  @Then("^user wait for (.*) seconds$")
	    public void user_wait_for(int time) throws InterruptedException {
	        Thread.sleep(Duration.ofSeconds(time).toMillis());
	    }

	    @Then("^user navigated to \"([^\"]*)\" page$")
	    public void user_navigated_to(String pageName) {
	        try {
	            Class<?> pageClass = Class.forName("pages." + pageName);
	            Object pageInstance = pageClass.getDeclaredConstructor().newInstance();
	            PageContextManager.setCurrentPage(pageClass);

	            Method verifyMethod = pageClass.getMethod("pageTitleVerify");
	            boolean result = (boolean) verifyMethod.invoke(pageInstance);

	            if (!result) {
	                throw new RuntimeException(pageName + " page did not load correctly.");
	            }

	        } catch (Exception e) {
	            throw new RuntimeException("Unable to load page: " + pageName, e);
	        }
	    }
	    
	    @When("^user verify \"([^\"]*)\" text$")
	    public void user_verify_test(String elementName) {
	    	WebElement element=getElementFromPage(elementName);
	    	WaitUtils.waitForVisible(element, 5);
	    	element.isDisplayed();
	    }

	    @When("^user enters \"([^\"]*)\" in \"([^\"]*)\"$")
	    public void user_enters_text_in_element(String value, String elementName) {
	        WebElement element = getElementFromPage(elementName);
	        String username=DataGenerator.createUsername();
	        String emailName=DataGenerator.generateRandomEmail();
	        if(value.equals("username")) {
	        	element.clear();
	        	element.sendKeys(username);
	        }
	        else if(value.equals("email")) {
	        	element.clear();
	        	element.sendKeys(emailName);
	        }
	        
	    }
	    
	    @When("^user enter \"([^\"]*)\" and \"([^\"]*)\" from excel$")
	    public void user_enter_from_excel(String username,String password) {
	    	 String filePath = "src/test/resources/testdata/Automation_testdata.xlsx";
	    	    String sheetName = "cred";
	    	    List<Map<String, String>> excelData = ExcelReader.getExcelData(filePath, sheetName);

	    	    for (Map<String, String> row : excelData) {
	    	        String usernameName = row.get("username");
	    	        String passwordName = row.get("password");

	    	        WebElement usernameField = getElementFromPage("usernamefield");
	    	        WebElement passwordField = getElementFromPage("passwordfield");
	    	        //WebElement loginBtn = loginPage.getElement("loginbtn");

	    	        usernameField.clear();
	    	        usernameField.sendKeys(username);
	    	        passwordField.clear();
	    	        passwordField.sendKeys(password);
	    	        //loginBtn.click();

	    	        // Optional validation/assertion here

	    	        // Logout & reset for next loop if needed
	    	        driver.navigate().refresh(); // or driver.quit/initDriver() if session isolation required
	    	    }
	    		
	    	
	    	
	    	
	    	
	    	
	    		
	    		    	
	    }
	    
	    @When("^user enters \"([^\"]*)\" in \"([^\"]*)\" field$")
	    public void user_enters(String value,String elementName) {
	    	WebElement element=getElementFromPage(elementName);
	    	
	    	if(elementName.equals("username")) {
	    		element.sendKeys(DataGenerator.createUsername());
	    	}
	    	else if(elementName.equals("email")) {
	    		element.sendKeys(DataGenerator.generateRandomEmail());
	    	}
	    	else if(elementName.equals("password")) {
	    		element.sendKeys(DataGenerator.generatePassword());
	    	}
	    	else if(elementName.equals("first_name")) {
	    		element.sendKeys(DataGenerator.createUsername());
	    	}
	    	else if(elementName.equals("last_name")) {
	    		element.sendKeys(DataGenerator.createUsername());
	    	}
	    	else if(elementName.equals("address")) {
	    		element.sendKeys(DataGenerator.generateAddress());
	    	}
	    	else if(elementName.equals("state")) {
	    		element.sendKeys(DataGenerator.generateState());
	    	}
	    	else if(elementName.equals("zipcode")) {
	    		element.sendKeys(DataGenerator.generateZipCode());
	    	}
	    	else if(elementName.equals("city name")) {
	    		element.sendKeys(DataGenerator.generateCity());
	    	}
	    	else if(elementName.equals("mobile number")) {
	    		element.sendKeys(DataGenerator.generatePhoneNumber());
	    	}
	    	
	    	
	    	
	    }
	    
	    @When("^user click on \"([^\"]*)\" button$")
	    public void user_click_on_button(String elementName) {
	    	WebElement element=getElementFromPage(elementName);
	    	try {
	    		element.click();
	    	}
	    	catch(Exception e) {
	    		throw new RuntimeException("unable to click"+e);
	    	}
	    	
	    	
	    }
	    
	    @When("^user click on \"([^\"]*)\"$")
	    public void user_click_on(String elementName) {
	    	WebElement element=getElementFromPage(elementName);
	    	element.click();
	    }
	    
	    @When("^user select \"([^\"]*)\" from \"([^\"]*)\" dropdown$")
	    public void user_Selects_dropdown(String value,String elementName) {
	    	WebElement element=getElementFromPage(elementName);
	    	Select sc=new Select(element);
	    	sc.selectByVisibleText(value);
	    }
	    

	    private WebElement getElementFromPage(String elementName) {
	        try {
	            Class<?> pageClass = PageContextManager.getPage();
	            Object pageInstance = pageClass.getDeclaredConstructor().newInstance();
	            Method getElementMethod = pageClass.getMethod("getElement", String.class);
	            return (WebElement) getElementMethod.invoke(pageInstance, elementName);
	        } catch (Exception e) {
	            throw new RuntimeException("Unable to retrieve element: " + elementName, e);
	        }
	    }
	
	
	//entering data to comm

}
