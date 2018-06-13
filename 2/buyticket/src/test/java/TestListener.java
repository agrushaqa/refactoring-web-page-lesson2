import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestListener implements ITestListener {
    final static Logger logger = Logger.getLogger(TestListener.class);
    String current_date = new SimpleDateFormat("dd-MM-yyyy HH-mm ").format(new Date());


    @Override
    public void onTestStart(ITestResult iTestResult) {
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        logger.info("Test completed successfully!");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        logger.error("Test completed with errors! Host:" + iTestResult.getHost() + "; Name:" + iTestResult.getTestName());
        logger.error("Class:" + iTestResult.getTestClass());
        StringWriter writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        Throwable error_cause = iTestResult.getThrowable();
        error_cause.printStackTrace(printWriter);
        logger.error(writer.getBuffer().toString());
        createScreenshot();
    }

    private void createScreenshot(){
        try {
            WebDriver driver = WebSource.getInstance().getDriver();
            File screenFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenFile, new File("./target/" + current_date + "error_screenshot.png"));
        } catch (IOException e){
            logger.error("failed to create screenshot");
        }
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {

    }

    @Override
    public void onFinish(ITestContext iTestContext) {
    }
}
