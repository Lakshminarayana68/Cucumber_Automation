package runners;

import java.util.List;
import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.TestNGCucumberRunner;
import utilities.ExcelReader;

@CucumberOptions(
	    features = "src/test/resources/features",   // <== This connects to your feature file!
	    glue = {"stepDefs"}                         // <== This connects to your step definition classes!
	)

public class LocalRunner {
	
	 @DataProvider
	    public Object[][] scenarios() {
	        List<Map<String, String>> data = ExcelReader.getExcelData("Excel Path", "Sheet");

	        // Convert each row into Object array
	        Object[][] result = new Object[data.size()][];
	        for (int i = 0; i < data.size(); i++) {
	            result[i] = new Object[]{
	                data.get(i).get("username"),
	                data.get(i).get("password")
	            };
	        }
	        return result;
	    }
        

	    @Test(dataProvider = "scenarios")
	    public void runScenario(Object... parameters) {
	        TestNGCucumberRunner runner = new TestNGCucumberRunner(this.getClass());
	        //runner.runScenario("Scenario Name", parameters);  // This triggers the Gherkin scenario dynamically
	    }

}
