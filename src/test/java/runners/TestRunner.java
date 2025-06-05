package runners;


	
	import io.cucumber.testng.AbstractTestNGCucumberTests;
	import io.cucumber.testng.CucumberOptions;
	import org.testng.annotations.*;

	import utilities.ConfigReader;
	import utilities.DriverFactory;

	@CucumberOptions(
	        features = "src/test/resources/features",
	        glue = {"stepDefs", "hooks"},
	        plugin = {"pretty",
	                
	                "listeners.CustomExtentReporter"},
	        tags= "@addtoCart"
	        
	)
	public class TestRunner extends AbstractTestNGCucumberTests {

	    @Parameters("browser")
	    @BeforeClass
	    public void setup(@Optional("chrome") String browser) {
	        ConfigReader.setProperty("browser", browser); // override default
	    }

	    @AfterClass
	    public void teardown() {
	        DriverFactory.quitDriver();
	    }
	}


