//package utilities;
//
//import java.io.File;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//import io.cucumber.plugin.event.Status; // for Cucumber
//import io.cucumber.plugin.event.Step;
//
////import com.aventstack.extentreports.Status;
//
//import org.apache.commons.io.FileUtils;
//import org.openqa.selenium.OutputType;
//import org.openqa.selenium.TakesScreenshot;
//import org.openqa.selenium.WebDriver;
//
//import com.aventstack.extentreports.ExtentReports;
//import com.aventstack.extentreports.ExtentTest;
//import com.aventstack.extentreports.MediaEntityBuilder;
//import com.aventstack.extentreports.reporter.ExtentSparkReporter;
//import com.aventstack.extentreports.reporter.configuration.Theme;
//
//import io.cucumber.plugin.ConcurrentEventListener;
//import io.cucumber.plugin.event.EventPublisher;
//import io.cucumber.plugin.event.PickleStepTestStep;
////import io.cucumber.plugin.event.Status;
////import io.cucumber.plugin.event.Status;
//import io.cucumber.plugin.event.Status;
////import com.aventstack.extentreports.Status;
//import io.cucumber.plugin.event.TestCaseFinished;
//import io.cucumber.plugin.event.TestCaseStarted;
//import io.cucumber.plugin.event.TestStepFinished;
//
//public class ExtentReporter implements ConcurrentEventListener {
//	
//	  private static final ThreadLocal<ExtentTest> scenarioTest = new ThreadLocal<>();
//	    private static final ThreadLocal<ExtentTest> stepTest = new ThreadLocal<>();
//	    private static ExtentReports extent;
//	    private static final String REPORT_DIR = "target/Reports/";
//
//	    static {
//	        ExtentSparkReporter reporter = new ExtentSparkReporter(REPORT_DIR + "ExtentReport_" +
//	                new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()) + ".html");
//	        extent = new ExtentReports();
//	        extent.attachReporter(reporter);
//	        extent.setSystemInfo("Environment", "QA");
//	        extent.setSystemInfo("Browser", "Chrome");
//	    }
//
//	    @Override
//	    public void setEventPublisher(EventPublisher publisher) {
//	        publisher.registerHandlerFor(TestCaseStarted.class, this::onScenarioStart);
//	        publisher.registerHandlerFor(TestStepFinished.class, this::onStepFinish);
//	        publisher.registerHandlerFor(TestCaseFinished.class, this::onScenarioFinish);
//	    }
//
//	    private void onScenarioStart(TestCaseStarted event) {
//	        ExtentTest scenario = extent.createTest(event.getTestCase().getName());
//	        scenarioTest.set(scenario);
//	    }
//       
////	    @Override
////	    public void step(Step step) {
////	        StepResult result = event.getResult();
////	        String stepText = step.getKeyword() + step.getText();
////
////	        // Log step based on Cucumber status
////	        if (result.getStatus() == io.cucumber.plugin.event.Status.PASSED) {
////	            extentTest.log(com.aventstack.extentreports.Status.PASS, stepText);
////	        } else if (result.getStatus() == io.cucumber.plugin.event.Status.FAILED) {
////	            // Take screenshot and attach
////	            String screenshotPath = ScreenshotUtil.captureScreenshot(driver, stepText);
////	            extentTest.log(com.aventstack.extentreports.Status.FAIL, stepText,
////	                MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
////	        } else if (result.getStatus() == io.cucumber.plugin.event.Status.SKIPPED) {
////	            extentTest.log(com.aventstack.extentreports.Status.SKIP, stepText);
////	        } else {
////	            extentTest.log(com.aventstack.extentreports.Status.WARNING, "Unhandled step result: " + result.getStatus());
////	        }
////	    }
//	    
//	    
//	    private void onStepFinish(TestStepFinished event) {
//	        if (event.getTestStep() instanceof PickleStepTestStep) {
//	            PickleStepTestStep step = (PickleStepTestStep) event.getTestStep();
//	            String stepText = step.getStep().getText();
//
//	            Status status;
//	            if (event.getResult().getStatus().is(Status.PASSED)) {
//	                status = Status.PASSED;
//	            } else if (event.getResult().getStatus().is(Status.FAILED)) {
//	                status = Status.FAILED;
//	            } else {
//	                status = Status.SKIPPED;
//	            }
//
//	            ExtentTest logStep = scenarioTest.get().createNode(step.getStep().getKeyword() + stepText);
//
//	            if (status == Status.FAILED) {
//	                Throwable error = event.getResult().getError();
//	                String screenshotPath = takeScreenshot(stepText); // See below
//	                logStep.fail(error,
//	                        MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
//	            } else {
//	                logStep.pass("Step passed");
//	            }
//	        }
//	    }
//
//	    private void onScenarioFinish(TestCaseFinished event) {
//	        extent.flush();
//	    }
//
//	    private String takeScreenshot(String stepName) {
//	        // Your existing WebDriver-based screenshot logic here
//	        byte[] screenshot = ((TakesScreenshot) DriverFactory.getDriver()).getScreenshotAs(OutputType.BYTES);
//	        String fileName = stepName.replaceAll("[^a-zA-Z0-9]", "_") + "_" +
//	                new SimpleDateFormat("HHmmss").format(new Date()) + ".png";
//	        String path = "target/Screenshots/" + fileName;
//	        try {
//	            Files.createDirectories(Paths.get("target/Screenshots/"));
//	            Files.write(Paths.get(path), screenshot);
//	        } catch (IOException e) {
//	            e.printStackTrace();
//	        }
//	        return "../Screenshots/" + fileName;
//	    }
//}
