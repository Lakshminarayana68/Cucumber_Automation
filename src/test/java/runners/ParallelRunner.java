package runners;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import utilities.BrowserText;
import utilities.DriverFactory;

@CucumberOptions(
		
		features= "src/test/resources/features",
		glue = {"stepDefs", "hooks"},
		plugin= {"pretty","listeners.CustomExtentReporter"},
		monochrome=true
		
		
		
		)

public class ParallelRunner extends AbstractTestNGCucumberTests {

	
//	@Override
//	@DataProvider(parallel=true)
//	public Object[][] scenarios(){
//		return super.scenarios();
//	}
	
	 @Parameters("browser")
	    @BeforeClass(alwaysRun = true)
	    public void setup(String browser) {
	        // Store browser into thread-local context
	      BrowserText.setBrowser(browser);
	    }
	  @AfterClass
	    public void teardown() {
	       DriverFactory.getDriver();
	    }
}
