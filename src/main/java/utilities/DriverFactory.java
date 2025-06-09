package utilities;

import java.net.URL;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {
	
	  // ThreadLocal for thread-safe WebDriver in parallel runs
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver initDriver() {

        String browser = BrowserText.getBrowser();
        String platformName = BrowserText.getPlatform(); // NEW
        String runmode = ConfigReader.getProperty("runMode");

        if (browser == null || browser.isEmpty()) {
            throw new RuntimeException("Browser is not set. Please check test runner.");
        }

        try {
            if (runmode.equalsIgnoreCase("remote")) {
                String gridUrl = ConfigReader.getProperty("gridUrl");

                MutableCapabilities capabilities;

                switch (browser.toLowerCase()) {
                    case "chrome":
                        ChromeOptions chromeOptions = new ChromeOptions();
                        setPlatformCapability(chromeOptions, platformName);
                        capabilities = chromeOptions;
                        break;

                    case "firefox":
                        FirefoxOptions firefoxOptions = new FirefoxOptions();
                        setPlatformCapability(firefoxOptions, platformName);
                        capabilities = firefoxOptions;
                        break;

                    case "edge":
                        EdgeOptions edgeOptions = new EdgeOptions();
                        setPlatformCapability(edgeOptions, platformName);
                        capabilities = edgeOptions;
                        break;

                    default:
                        throw new IllegalArgumentException("Invalid browser for remote: " + browser);
                }

                driver.set(new RemoteWebDriver(new URL(gridUrl), capabilities));
            }
            else if (runmode.equalsIgnoreCase("local")) {
                switch (browser.toLowerCase()) {
                    case "chrome":
                        WebDriverManager.chromedriver().setup();
                        driver.set(new ChromeDriver());
                        break;
                    case "firefox":
                        WebDriverManager.firefoxdriver().setup();
                        driver.set(new FirefoxDriver());
                        break;
                    case "edge":
                        WebDriverManager.edgedriver().setup();
                        driver.set(new EdgeDriver());
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid browser in config: " + browser);
                }
            } else {
                throw new IllegalArgumentException("Execution type must be 'local' or 'remote'");
            }

            getDriver().manage().window().maximize();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to initialize WebDriver", e);
        }

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

    private static void setPlatformCapability(MutableCapabilities options, String platformName) {
        if (platformName == null || platformName.isEmpty()) return;

        switch (platformName.toLowerCase()) {
            case "windows":
                options.setCapability("platformName", Platform.WINDOWS);
                break;
            case "linux":
            case "ubuntu":
                options.setCapability("platformName", Platform.LINUX);
                break;
            case "mac":
                options.setCapability("platformName", Platform.MAC);
                break;
            default:
                throw new RuntimeException("Unsupported platform: " + platformName);
        }

}}
