package utilities;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtils {
	
	private static final int DEFAULT_TIMEOUT=10;
	private static final int DEFAULT_POLL=1;
	
	private static WebDriver driver=DriverFactory.getDriver();
	
	public static WebElement waitForVisible(WebElement element, int timeoutSeconds) {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(timeoutSeconds));
		return wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public static WebElement waitForClick(WebElement element,int timeOutSeconds) {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(timeOutSeconds));
		return wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public static WebElement fluentWait(WebElement element,int timeOutSeconds) {
		Wait<WebDriver> wait=new FluentWait<>(driver)
				             .withTimeout(Duration.ofSeconds(timeOutSeconds))
				             .pollingEvery(Duration.ofSeconds(timeOutSeconds))
				             .ignoring(Exception.class);
		
		return wait.until(driver->{
			if(element.isDisplayed()) {
				return element;
			}
			else {
				return null;
			}
		});
	}

}
