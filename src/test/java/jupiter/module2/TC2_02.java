package jupiter.module2;

import jupiter.base.BrowserManager;
import jupiter.utilities.*;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;
import java.util.Map;

public class TC2_02 extends BrowserManager {
    Map<String, DataModel> dataMap;

    @Test
    public void TC2_02_CONTACT_US_BACK_AFTER_SUBMITTED_APPLICATION() throws InterruptedException, IOException {

        dataMap = new DataReader().customerInput();
        DataModel Data = dataMap.get("valid_contact_us");

        String className = new Throwable().getStackTrace()[0].getMethodName();

        Jupiter.testTitle(driver, className);

        Jupiter.clickContact(driver);

        Jupiter.contactDetails(driver, Data);

        Jupiter.submitContact(driver);
        Jupiter.logs("User clicked Submit button");

        MyElements.textWaitInvisible(driver, "Sending Feedback");
        Jupiter.logs("User is able to see the Sending Feedback loading modal");

        Jupiter.logs("User is able to see the Successful message");

        MyElements.textClick(driver, "« Back");
        Jupiter.logs("User clicked Back button");

        String contactPage = MyElements.getText(driver, By.id("header-message"));
        Assert.assertTrue(contactPage.contentEquals(Data.getNotification_modal()));
        Jupiter.logsPassed("The user Redirected back to contact page ");

        Log.endTest(className);

        // WebDriver Screenshot
        Screen.screenPass(driver, className);

    }

}
