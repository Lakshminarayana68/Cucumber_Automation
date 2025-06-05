package utilities;

//import com.aventstack.extentreports.ExtentTest;
//
//import io.cucumber.messages.types.TestCaseStarted;
//import io.cucumber.messages.types.TestStepFinished;
//import io.cucumber.plugin.ConcurrentEventListener;
//import io.cucumber.plugin.event.EventPublisher;

//import com.aventstack.extentreports.ExtentTest;
//import com.aventstack.extentreports.Status;
//import io.cucumber.plugin.ConcurrentEventListener;
//import io.cucumber.plugin.event.*;
//import utilities.ExtentReporter;
//
//public class CucumberExtentListener implements ConcurrentEventListener {
//	
//	
////	 private ExtentTest test;
////
////	    @Override
////	    public void setEventPublisher(EventPublisher publisher) {
////	        publisher.registerHandlerFor(TestCaseStarted.class, this::onTestStart);
////	        publisher.registerHandlerFor(TestStepFinished.class, this::onStepFinished);
////	    }
////
////	    private void onTestStart(TestCaseStarted event) {
////	        test = ExtentReporter.createTest(event.getTestCase().getName());
////	        ExtentReporter.setExtentTest(test);
////	    }
////
////	    private void onStepFinished(TestStepFinished event) {
////	        if (event.getTestStep() instanceof PickleStepTestStep) {
////	            PickleStepTestStep step = (PickleStepTestStep) event.getTestStep();
////	            String stepText = step.getStep().getKeyword() + step.getStep().getText();
////
////	            switch (event.getResult().getStatus()) {
////	                case PASSED:
////	                    test.log(Status.PASS, stepText);
////	                    break;
////	                case FAILED:
////	                    test.log(Status.FAIL, stepText + " ❌");
////	                    break;
////	                case SKIPPED:
////	                    test.log(Status.SKIP, stepText + " ⚠️");
////	                    break;
////	                default:
////	                    test.log(Status.INFO, stepText);
////	            }
////	        }
////	    }
//
//}
