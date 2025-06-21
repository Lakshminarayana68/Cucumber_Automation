package pages;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import utilities.DriverFactory;

public class BasePage {
	
	 protected WebDriver driver;

	    public BasePage() {
	        this.driver = DriverFactory.getDriver(); // Uses your shared driver
	    }
	    
	    
	    public boolean pageTitleVerify() {
	    	return true;
	    };
	
	
	//to get page dynamically from pages

//	public <T> T getPage(Class<T> pageClass) {
//		
//		try {
//			T page=pageClass.getDeclaredConstructor().newInstance();
//			PageFactory.initElements(driver,this);
//			return page;
//		}
//		catch(Exception e) {
//			throw new RuntimeException("unable load page:"+pageClass.getName());
//		}
//		
//	}
//	

}
