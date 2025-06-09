package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
	
	

	    private static Properties prop;

	    static {
	        try {
	            FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"/src/test/resources/config.properties");
	            prop = new Properties();
	            prop.load(fis);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	    public static String getProperty(String key) {
	        return prop.getProperty(key);
	    }
	    
	    public static void setProperty(String key, String value) {
	        prop.setProperty(key, value);
	    }

}
