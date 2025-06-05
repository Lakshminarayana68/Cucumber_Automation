package testbase;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import utilities.ConfigReader;
import utilities.DriverFactory;

public class BaseTest {
	
	protected WebDriver driver;

    @BeforeMethod
    public void setUp() {
        // Load browser name and URL from config file
        String browser = ConfigReader.getProperty("browser");
        String url = ConfigReader.getProperty("url");

        // Initialize driver with desired browser
//        driver = DriverFactory.initDriver(browser);
//        driver.get(url);
    }

    @AfterMethod
    public void tearDown() {
        // Close the driver instance
        DriverFactory.quitDriver();
    }

}
