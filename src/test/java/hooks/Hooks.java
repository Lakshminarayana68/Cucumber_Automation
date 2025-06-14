package hooks;

import org.openqa.selenium.WebDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import utilities.DriverFactory;
import utilities.LoggerUtil;

public class Hooks {
	
	  @Before
	    public void beforeScenario() {
	        LoggerUtil.info("==== Scenario started ====");
	    }

	    @After
	    public void afterScenario() throws InterruptedException {
	        WebDriver driver = DriverFactory.getDriver();
	        if (driver != null) {
	            driver.quit();
	            Thread.sleep(3000);
	            LoggerUtil.info("Browser closed after scenario.");
	        }
	        LoggerUtil.info("==== Scenario ended ====");
	    }

}
