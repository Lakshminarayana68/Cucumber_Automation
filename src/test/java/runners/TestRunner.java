package runners;


	
	import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import io.cucumber.testng.AbstractTestNGCucumberTests;
//import utilities.TestConext;
	import io.cucumber.testng.CucumberOptions;
import utilities.BrowserText;
import utilities.ConfigReader;
import utilities.DriverFactory;

	//import utilities.ConfigReader;
	//import utilities.DriverFactory;
    //import utilities.DriverFactory;

	@CucumberOptions(
	        features = "src/test/resources/features",
	        glue = {"stepDefs", "hooks"},
	        plugin = {"pretty",
	                
	                "listeners.CustomExtentReporter"},
	        tags= "@addtoCart"
	        
	)
	public class TestRunner extends AbstractTestNGCucumberTests {

//		@Parameters("browser")
//		@BeforeClass
//		public void setup(@Optional String browser) {
//		    if (browser != null && !browser.isEmpty()) {
//		        ConfigReader.setProperty("browser", browser); // Override from XML
//		    }}
		
//		  @Parameters("browser")
//		    @BeforeClass
//		    public void setup(@Optional("") String browser) {
//		        if (browser != null && !browser.isEmpty()) {
//		            ConfigReader.setProperty("browser", browser); // override config value
//		        }
//		        System.out.println("Running tests on browser: " + ConfigReader.getProperty("browser"));
//		    }
////		  
//		   @Parameters("browser")
//		    @BeforeClass(alwaysRun = true)
//		    public void setup(String browser) {
//		        // Store browser into thread-local context
//		      BrowserText.setBrowser(browser);
//		    }
		
		@Parameters({"browser", "platform"})
		@BeforeClass
		public void setup(String browser, String platform) {
		    BrowserText.setBrowser(browser);
		    BrowserText.setPlatform(platform);
		    //DriverFactory.initDriver();
		}


	    @AfterClass
	    public void teardown() throws InterruptedException {
	       DriverFactory.quitDriver();
	       Thread.sleep(2000);
	    }
	    
	    
	}


