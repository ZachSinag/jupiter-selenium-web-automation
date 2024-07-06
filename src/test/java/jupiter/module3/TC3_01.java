package jupiter.module3;

import jupiter.base.BrowserManager;
import jupiter.utilities.*;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.Map;

public class TC3_01 extends BrowserManager {
    Map<String, DataModel> dataMap;

    @Test
    public void TC3_01_CART_PAGE_VERIFY_CART_NO_ITEM() throws IOException, InterruptedException {

        dataMap = new DataReader().customerInput();
        DataModel data = dataMap.get("empty_cart");

        String className = new Throwable().getStackTrace()[0].getMethodName();

        Jupiter.testTitle(driver, className);

        Jupiter.clickCart(driver);

        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='alert']")).getText(), data.getNotification_modal());
        Jupiter.logsPassed("The user able to see the empty cart with notification message " + data.getNotification_modal());


        Log.endTest(className);

        Screen.screenPass(driver, className);
    }


}
