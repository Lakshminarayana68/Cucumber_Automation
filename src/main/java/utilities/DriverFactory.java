package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {
	
	  // ThreadLocal for thread-safe WebDriver in parallel runs
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver initDriver(String browser) {
    	
//    	 String browser = ConfigReader.getProperty("browser");
//
//         switch (browser.toLowerCase()) {
//             case "chrome":
//                 WebDriverManager.chromedriver().setup();
//                 driver.set(new ChromeDriver());
//                 break;
//
//             case "firefox":
//                 WebDriverManager.firefoxdriver().setup();
//                 driver.set(new FirefoxDriver());
//                 break;
//
//             case "edge":
//                 WebDriverManager.edgedriver().setup();
//                 driver.set(new EdgeDriver());
//                 break;
//
//             default:
//                 throw new IllegalArgumentException("Invalid browser in config: " + browser);
//         }
    	
    	
    	
    	
        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver.set(new ChromeDriver());
        } else if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver.set(new FirefoxDriver());
        } else if (browser.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            driver.set(new EdgeDriver());
        } else {
            throw new RuntimeException("Invalid browser: " + browser);
        }

        // Maximize and return driver
        getDriver().manage().window().maximize();
        return getDriver();
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void quitDriver() {
        if (getDriver() != null) {
            getDriver().quit();
            driver.remove();
        }
    }

}
