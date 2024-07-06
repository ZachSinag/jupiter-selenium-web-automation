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

public class TC2_01 extends BrowserManager {
    Map<String, DataModel> dataMap;

    @Test
    public void TC2_01_CONTACT_US_VERIFY_All_SUBMITTED_APPLICATION() throws InterruptedException, IOException {

        dataMap = new DataReader().customerInput();
        DataModel Data = dataMap.get("success_application");

        String className = new Throwable().getStackTrace()[0].getMethodName();

        Jupiter.testTitle(driver, className);

        Jupiter.clickContact(driver);

        Jupiter.contactDetails(driver, Data);

        Jupiter.submitContact(driver);
        Jupiter.logs("User clicked Submit button");

        MyElements.textWaitInvisible(driver, "Sending Feedback");
        Jupiter.logs("User is able to see the Sending Feedback loading modal");

        Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[2]/div/div")).getText().contentEquals(Data.getNotification_modal()));
        Jupiter.logs("The successful submission message is: " + Data.getNotification_modal());
        Jupiter.logsPassed("The user validated that the Header notification message is correct");


        Log.endTest(className);

        // WebDriver Screenshot
        Screen.screenPass(driver, className);

    }

}
