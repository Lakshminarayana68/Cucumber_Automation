package hooks;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utilities.DriverFactory;

import org.openqa.selenium.*;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentHooks {

    private static ExtentReports extent;
    private static final ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    private static final String reportPath;

    static {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        reportPath = "target/Reports/ExtentReport_" + timestamp + ".html";
        ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
        extent = new ExtentReports();
        extent.attachReporter(spark);
    }

    @Before
    public void beforeScenario(Scenario scenario) {
        ExtentTest extentTest = extent.createTest(scenario.getName());
        test.set(extentTest);
    }

//    @AfterStep
//    public void afterStep(Scenario scenario) throws IOException {
////        if (scenario.isFailed()) {
////            WebDriver driver = DriverFactory.getDriver(); // ensure this returns the current WebDriver
////            if (driver != null) {
////                byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
////                String screenshotPath = saveScreenshot(screenshot, scenario.getName());
////                test.get().fail("Step Failed",
////				    MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
////            } else {
////                test.get().fail("Step Failed - WebDriver is null");
////            }
////        } else {
////            test.get().pass("Step Passed");
////        }
//    	
//    	 if (scenario.isFailed()) {
//    	        WebDriver driver = DriverFactory.getDriver();
//    	        if (driver != null) {
//    	            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
//    	            String screenshotPath = saveScreenshot(screenshot, scenario.getName());
//
//    	            test.get().fail("Step Failed",
//    	                MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
//    	        } else {
//    	            test.get().fail("Step Failed - WebDriver is null");
//    	        }
//    	    } else {
//    	        test.get().pass("Step Passed");
//    	    }
//    }
//
    @After
   public void afterScenario() {
        extent.flush(); // flush after each scenario is acceptable for individual report update
    }
    
    
    
    private String saveScreenshot(byte[] screenshot, String scenarioName) {
        String timestamp = new SimpleDateFormat("HHmmss").format(new Date());
        String fileName = scenarioName.replaceAll(" ", "_") + "_" + timestamp + ".png";
        String screenshotPath = "target/Screenshots/" + fileName;
        try {
            File destFile = new File(screenshotPath);
            Files.createDirectories(destFile.getParentFile().toPath());
            Files.write(destFile.toPath(), screenshot);
            
            // 🔥 Return absolute path so Extent can find it
            return destFile.getAbsolutePath();
        } catch (IOException e) {
            throw new RuntimeException("Failed to save screenshot", e);
        }
    }

//    private String saveScreenshot(byte[] screenshot, String scenarioName) {
//        String timestamp = new SimpleDateFormat("HHmmss").format(new Date());
//        String fileName = scenarioName.replaceAll("[^a-zA-Z0-9]", "_") + "_" + timestamp + ".png";
//        String screenshotPath = "target/Screenshots/" + fileName;
//        try {
//            File destFile = new File(screenshotPath);
//            Files.createDirectories(destFile.getParentFile().toPath());
//            Files.write(destFile.toPath(), screenshot);
//            return "../Screenshots/" + fileName; // path relative to HTML report
//        } catch (IOException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
    
    
//    @After
//    public void afterReport() {
//        extent.flush();
//        openReport(reportPath);  // Add this line
//    }
//
//    private void openReport(String reportPath) {
//        try {
//            File htmlFile = new File(reportPath);
//            if (htmlFile.exists()) {
//                Desktop.getDesktop().browse(htmlFile.toURI());
//            }
//        } catch (IOException e) {
//            System.err.println("Unable to open report: " + e.getMessage());
//        }
//    }
}
