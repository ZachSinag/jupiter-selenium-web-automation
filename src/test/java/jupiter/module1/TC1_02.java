package jupiter.module1;

import jupiter.base.BrowserManager;
import jupiter.utilities.*;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.Map;

public class TC1_02 extends BrowserManager {
    Map<String, DataModel> dataMap;

    @Test
    public void TC1_02_CONTACT_US_VERIFY_ERROR_MESSAGE_EMAIL_FORMAT() throws InterruptedException, IOException {

        dataMap = new DataReader().customerInput();
        DataModel Data = dataMap.get("invalid_email_contact_us");

        String className = new Throwable().getStackTrace()[0].getMethodName();

        Jupiter.testTitle(driver, className);

        Jupiter.clickContact(driver);

        Jupiter.contactDetails(driver, Data);

        Assert.assertTrue(driver.findElement(By.id("email-err")).getText().contentEquals(Data.getEmail_error_message()));
        Jupiter.logs("The Error message of the Email filed is: " + Data.getEmail_error_message());
        Jupiter.logsPassed("The user validated that the Email field error message is correct");



        Log.endTest(className);

        // WebDriver Screenshot
        Screen.screenPass(driver, className);


    }
}
