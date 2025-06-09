package utilities;

public class BrowserText {
	
	 private static ThreadLocal<String> browser = new ThreadLocal<>();
	 private static ThreadLocal<String> platform = new ThreadLocal<>();

	    public static void setBrowser(String browserName) {
	        browser.set(browserName);
	    }

	    public static String getBrowser() {
	        return browser.get();
	    }
	    
	    public static void setPlatform(String platformName) {
	    	platform.set(platformName);
	    }
	    
	    public static String getPlatform() {
	    	return platform.get();
	    }
	    
	    public static void clear() {
	        browser.remove();
	        platform.remove();
	    }

}
