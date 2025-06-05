package utilities;

import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;




public class LoggerUtil {
	
	private static final Logger logger = LogManager.getLogger(LoggerUtil.class);

    public static void info(String message) {
        logger.info(message);  // Log info level
    }

    public static void error(String message) {
        logger.error(message); // Log error level
    }

}
