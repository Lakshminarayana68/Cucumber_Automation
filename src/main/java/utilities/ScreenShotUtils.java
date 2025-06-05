package utilities;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenShotUtils {
	
//	 public static WebDriver driver; // Assign your actual driver
//
//	    public static String captureScreenshot(String stepName) {
//	        String timestamp = new SimpleDateFormat("HHmmss").format(new Date());
//	        String fileName = "Screenshot_" + stepName.replaceAll("[^a-zA-Z0-9]", "_") + "_" + timestamp + ".png";
//	        String path = "target/Screenshots/" + fileName;
//
//	        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//	        File dest = new File(path);
//	        try {
//	            Files.createDirectories(dest.getParentFile().toPath());
//	            Files.copy(src.toPath(), dest.toPath());
//	        } catch (IOException e) {
//	            e.printStackTrace();
//	        }
//
//	        return "../Screenshots/" + fileName;
//	    }
	
	
	 public static String captureScreenshot(String stepName) {
	        WebDriver driver = DriverFactory.getDriver(); // Static getter method

	        if (driver == null) {
	            throw new RuntimeException("WebDriver is null - cannot capture screenshot");
	        }

	        byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
	        String timestamp = new SimpleDateFormat("HHmmss").format(new Date());
	        String fileName = stepName.replaceAll("[^a-zA-Z0-9]", "_") + "_" + timestamp + ".png";
	        String screenshotPath = "target/Screenshots/" + fileName;

	        try {
	            File destFile = new File(screenshotPath);
	            Files.createDirectories(destFile.getParentFile().toPath());
	            Files.write(destFile.toPath(), screenshot);
	            return destFile.getAbsolutePath();  // Extent needs absolute path
	        } catch (IOException e) {
	            throw new RuntimeException("Failed to save screenshot: " + e.getMessage(), e);
	        }
	    }
	

}
