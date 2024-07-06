package jupiter.utilities;

import jupiter.listener;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.openqa.selenium.*;
import org.testng.Assert;
import java.io.IOException;
import java.util.Map;


public class Jupiter extends listener {
    static Map<String, DataModel> dataMap;

    public static void logs(String info) {

        extentTest.get().log(Status.INFO, info);
        Log.info(info);
    }


    public static void failedLogs(String info) {

        extentTest.get().log(Status.FAIL, info);
        Log.error("Failed: " + info);
    }

    public static void logsPassed(String info) {

        extentTest.get().log(Status.PASS, info);
        Log.info("Passed: " + info);
    }

    public static void logsCapture(WebDriver driver, String className, String info) throws IOException {

        Screen.capture(driver, className, info);
        Log.info(info);
    }


    public static void testTitle(WebDriver driver, String className) throws IOException {

        String url = driver.getCurrentUrl();
        Log.startTest(className);
        String testTitle = String.format("<b>%s Test Start</b>", className);
        Markup markTitle = MarkupHelper.createLabel(testTitle, ExtentColor.BLUE);
        extentTest.get().log(Status.INFO, markTitle);

        MyElements.wait(driver, "/html/body/div[2]/div/div/div[2]/div/p[1]");

        Jupiter.logsCapture(driver, className, "User accessed the following link: " + url);

    }

    public static void clickContact(WebDriver driver)  {

        MyElements.textClick(driver, "Contact");
        Jupiter.logs("User clicked Contact in the header section of the page");

    }

    public static void clickCart(WebDriver driver)  {

        MyElements.click(driver, By.id("nav-cart"));
        Jupiter.logs("User clicked Cart in the header section of the page");


        String url = driver.getCurrentUrl();

        Assert.assertTrue(url.contentEquals("https://jupiter.cloud.planittesting.com/#/cart"));
        Jupiter.logsPassed("The user redirected to cart page " + url);

    }

    public static void submitContact(WebDriver driver) {

        MyElements.textClick(driver, "Submit");

    }

    public static void contactDetails(WebDriver driver, DataModel Data) {

        MyElements.sendKeys(driver,By.id("forename"),Data.getForename());
        Jupiter.logs("User Enter Forename: " + Data.getForename());

        MyElements.sendKeys(driver,By.id("email"),Data.getEmail());
        Jupiter.logs("User Enter Email: " + Data.getEmail());

        MyElements.sendKeys(driver,By.id("message"),Data.getMessage());
        Jupiter.logs("User Enter Message: " + Data.getMessage());

    }

    public static void validateErrorMessage(WebDriver driver, By errorLocator) {
        String errorText = MyElements.getText(driver, errorLocator);
        Assert.assertTrue(errorText.isEmpty(), "Error message is not automatically disappeared for " + errorLocator.toString());
        Jupiter.logsPassed("The error message is automatically disappeared beside " + errorLocator.toString().replace("By.id: ", ""));
    }

    public static void enterAndValidateInput(WebDriver driver, By locator, String value, String fieldName) {
        MyElements.sendKeys(driver, locator, value);
        Jupiter.logs("User Enter " + fieldName + ": " + value);
        validateErrorMessage(driver, locator);
    }

    public static void buyProduct(WebDriver driver, String productId, int quantity, String productName) {
        for (int i = 0; i < quantity; i++) {
            MyElements.click(driver, By.xpath("//li[@id='" + productId + "']//a[@class='btn btn-success'][normalize-space()='Buy']"));
        }
        Jupiter.logs("The user bought " + quantity + " " + productName);
    }


}
