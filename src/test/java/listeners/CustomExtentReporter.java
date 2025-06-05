package listeners;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.MediaEntityBuilder;
import io.cucumber.plugin.ConcurrentEventListener;
import io.cucumber.plugin.event.*;
import io.cucumber.plugin.event.Status;
import utilities.ScreenShotUtils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CustomExtentReporter implements ConcurrentEventListener {

    private static final ExtentReports extent = new ExtentReports();
    private static final ThreadLocal<ExtentTest> scenarioTest = new ThreadLocal<>();
    private static final ThreadLocal<ExtentTest> stepNode = new ThreadLocal<>();

    static {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String reportPath = "target/Reports/ExtentReport_" + timestamp + ".html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(new File(reportPath));
        extent.attachReporter(reporter);
    }

    @Override
    public void setEventPublisher(EventPublisher publisher) {
        publisher.registerHandlerFor(TestCaseStarted.class, this::startScenario);
        publisher.registerHandlerFor(TestStepFinished.class, this::logStep);
        publisher.registerHandlerFor(TestCaseFinished.class, this::finishScenario);
    }

    private void startScenario(TestCaseStarted event) {
        ExtentTest test = extent.createTest(event.getTestCase().getName());
        scenarioTest.set(test);
    }

    private void logStep(TestStepFinished event) {
        if (!(event.getTestStep() instanceof PickleStepTestStep)) return;

        PickleStepTestStep step = (PickleStepTestStep) event.getTestStep();
        String stepText = step.getStep().getKeyword() + step.getStep().getText();
        ExtentTest node = scenarioTest.get().createNode(stepText);
        stepNode.set(node);

        Status status = event.getResult().getStatus();

        switch (status) {
            case PASSED:
                node.pass("Step passed");
                break;
            case FAILED:
                try {
                    String screenshotPath = ScreenShotUtils.captureScreenshot(stepText);
                    node.fail(event.getResult().getError(),
                        MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
                } catch (Exception e) {
                    node.fail("Failed with exception: " + event.getResult().getError());
                }
                break;
            default:
                node.skip("Step skipped: " + status.name());
        }
    }

    private void finishScenario(TestCaseFinished event) {
        extent.flush();
    }
}
