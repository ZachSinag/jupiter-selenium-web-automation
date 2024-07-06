package jupiter;

import jupiter.utilities.Log;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import jupiter.utilities.ExtentReporter;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class listener implements ITestListener {

    public static final String userName = (System.getProperty("user.name"));
    public static final String app = "jupiter.cloud.planittesting.";
    public static final String OS = (System.getProperty("os.name"));

    public static Log log = new Log();

    public static String path = "./Automation Reports/";
    public static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
    // Screenshot Set Up
    static SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy HH");
    static Date d = new Date();
    static String setDate = sdf.format(d);
    public static String dateName = setDate.toString().replace(" ", "_");
    private static ExtentReports extent = ExtentReporter.ReportGenerator();
    public DesiredCapabilities cap = new DesiredCapabilities();
    ExtentTest test;


    @Override
    public void onTestStart(ITestResult result) {
        // TODO Auto-generated method stub
        test = extent.createTest(result.getMethod().getMethodName()); // Test
        // Case
        // Title

        extentTest.set(test);
    }

    @Override
    public void onFinish(ITestContext context) {
        // TODO Auto-generated method stub
        double totalTC = context.getPassedTests().size() + context.getFailedTests().size() + context.getSkippedTests().size();
        int TCCount = (int) totalTC;
        int TCPassed = context.getPassedTests().size();
        System.out.println("Total Number of Executed Tests: "+ TCCount);
        System.out.println("Passed Tests: "+ TCPassed);
        double percentage = (TCPassed/totalTC) * 100;
        DecimalFormat df = new DecimalFormat("0.00");
        System.out.println("Percentage of Passed Tests: " + df.format(percentage) + "%");
        extent.flush();
    }


}
