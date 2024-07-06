package jupiter.module1;

import jupiter.base.BrowserManager;
import jupiter.utilities.*;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;
import java.util.Map;

public class TC1_01 extends BrowserManager {
    Map<String, DataModel> dataMap;

    @Test
    public void TC1_01_CONTACT_US_VERIFY_All_ERROR_MESSAGE() throws InterruptedException, IOException {

        dataMap = new DataReader().customerInput();
        DataModel Data = dataMap.get("missing_fields_contact_us");

        String className = new Throwable().getStackTrace()[0].getMethodName();

        Jupiter.testTitle(driver, className);

        Jupiter.clickContact(driver);

        Jupiter.submitContact(driver);
        Jupiter.logs("User clicked Submit button to see all error messages");

        Assert.assertTrue(driver.findElement(By.id("header-message")).getText().contentEquals(Data.getNotification_modal()));
        Jupiter.logs("The Error message for Notification header is: " + Data.getNotification_modal());
        Jupiter.logsPassed("The user validated that the Header notification error message is correct");

        Assert.assertTrue(driver.findElement(By.id("forename-err")).getText().contentEquals(Data.getForename_error_message()));
        Jupiter.logs("The Error message of the Forename field is: " + Data.getForename_error_message());
        Jupiter.logsPassed("The user validated that the Forename field error message is correct");

        Assert.assertTrue(driver.findElement(By.id("email-err")).getText().contentEquals(Data.getEmail_error_message()));
        Jupiter.logs("The Error message of the Email field is: " + Data.getEmail_error_message());
        Jupiter.logsPassed("The user validated that the Email field error message is correct");

        Assert.assertTrue(driver.findElement(By.id("message-err")).getText().contentEquals(Data.getError_message_box()));
        Jupiter.logs("The Error message of the Message field is: " + Data.getError_message_box());
        Jupiter.logsPassed("The user validated that the Message field error message is correct");


        Log.endTest(className);

        // WebDriver Screenshot
        Screen.screenPass(driver, className);

    }

}
