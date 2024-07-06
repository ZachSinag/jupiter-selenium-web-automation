package jupiter.module2;

import jupiter.base.BrowserManager;
import jupiter.utilities.*;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;
import java.util.Map;

public class TC2_03 extends BrowserManager {
    Map<String, DataModel> dataMap;

    @Test
    public void TC2_03_CONTACT_US_VERIFY_WITH_ERROR_SUBMITTED_APPLICATION() throws InterruptedException, IOException {

        dataMap = new DataReader().customerInput();
        DataModel Data = dataMap.get("submit_invalid");

        String className = new Throwable().getStackTrace()[0].getMethodName();

        Jupiter.testTitle(driver, className);

        Jupiter.clickContact(driver);

        Jupiter.contactDetails(driver, Data);

        Assert.assertTrue(driver.findElement(By.id("email-err")).getText().contentEquals(Data.getEmail_error_message()));
        Jupiter.logs("The Email Field error message is: " + Data.getEmail_error_message());

        Jupiter.submitContact(driver);
        Jupiter.logs("User clicked Submit button");

        String headerMessage = driver.findElement(By.id("header-message")).getText();
        boolean isNotificationCorrect = headerMessage.contentEquals(Data.getNotification_modal());

        if (isNotificationCorrect) {
            Jupiter.logs("The Notification is: " + Data.getNotification_modal());
            Jupiter.logsPassed("The user is able to see the correct error message");
        } else {
            Jupiter.failedLogs("The user is able to submit the application even though there is incorrect input");
        }

        // Perform the assertion
        Assert.assertTrue(isNotificationCorrect, "The notification message is incorrect");

        Log.endTest(className);

        // WebDriver Screenshot
        Screen.screenPass(driver, className);
    }
}
