package jupiter.module1;

import jupiter.base.BrowserManager;
import jupiter.utilities.*;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.Map;

public class TC1_03 extends BrowserManager {
    Map<String, DataModel> dataMap;

    @Test
    public void TC1_03_CONTACT_US_VERIFY_All_ERROR_MESSAGE_AUTOMATICALLY_REMOVE() throws IOException, InterruptedException {

        dataMap = new DataReader().customerInput();
        DataModel data = dataMap.get("valid_contact_us");

        String className = new Throwable().getStackTrace()[0].getMethodName();

        Jupiter.testTitle(driver, className);

        Jupiter.clickContact(driver);

        Jupiter.submitContact(driver);

        Jupiter.logsCapture(driver, className, "User is able to see all error message after clicking submit button");

        Jupiter.enterAndValidateInput(driver, By.id("forename"), data.getForename(), "forename");
        Jupiter.enterAndValidateInput(driver, By.id("email"), data.getEmail(), "email");
        Jupiter.enterAndValidateInput(driver, By.id("message"), data.getMessage(), "message");

        Assert.assertEquals(driver.findElement(By.id("header-message")).getText(), data.getNotification_modal(),
                "Header message validation failed");

        Log.endTest(className);

        Screen.screenPass(driver, className);
    }


}
