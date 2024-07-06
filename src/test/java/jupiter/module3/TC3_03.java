package jupiter.module3;

import jupiter.base.BrowserManager;
import jupiter.utilities.*;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.Map;

public class TC3_03 extends BrowserManager {
    Map<String, DataModel> dataMap;

    @Test
    public void TC3_03_CART_PAGE_VERIFY_THE_PRICE_FOR_EACH_PRODUCTS() throws IOException, InterruptedException {

        dataMap = new DataReader().customerInput();
        DataModel data = dataMap.get("cart_price");

        String className = new Throwable().getStackTrace()[0].getMethodName();

        Jupiter.testTitle(driver, className);

        Jupiter.clickCart(driver);

        MyElements.textClick(driver, "Start Shopping »");
        Jupiter.logs("User clicked the Start Shopping button");


        Jupiter.buyProduct(driver, "product-2", 2, "Stuffed Frog");
        Jupiter.buyProduct(driver, "product-4", 5, "Fluffy Bunny");
        Jupiter.buyProduct(driver, "product-7", 3, "Valentine Bear");

        MyElements.click(driver, By.id("nav-cart"));
        Jupiter.logs("User go back to Cart page");

        //check the price of the Stuffed Frog
        String frogPrice = data.getStuff_frog_price();
        double frogPriced = Double.parseDouble(frogPrice);
        String frogActualPrice = MyElements.getText(driver, By.xpath("//td[normalize-space()='$10.99']"));
        double actualFrogPrice = Double.parseDouble(frogActualPrice.replace("$", "")); // Remove the dollar sign and convert the subtotal to a double

        Assert.assertEquals(actualFrogPrice, frogPriced, "The actual price does not match to the expected total price");
        Jupiter.logsPassed("The Price of the Stuffed Frog : $" + actualFrogPrice + " in their cart is correct");

        //check the price of the Fluffy Bunny
        String bunnyPrice = data.getFluffy_bunny_price();
        double bunnyPriced = Double.parseDouble(bunnyPrice);
        String bunnyActualTotal = MyElements.getText(driver, By.xpath("//td[normalize-space()='$9.99']"));
        double actualBunnyPrice = Double.parseDouble(bunnyActualTotal.replace("$", "")); // Remove the dollar sign and convert the subtotal to a double

        Assert.assertEquals(actualBunnyPrice, bunnyPriced, "The subtotal does not match the expected total price");
        Jupiter.logsPassed("The Price of the Fluffy Bunny : $" + actualBunnyPrice + " in their cart is correct");

        //check the price of the Valentine Bear
        String bearPrice = data.getValentines_bear_price();
        double bearPriced = Double.parseDouble(bearPrice);
        String bearActualTotal = MyElements.getText(driver, By.xpath("//td[normalize-space()='$14.99']"));
        double actualBearPrice = Double.parseDouble(bearActualTotal.replace("$", "")); // Remove the dollar sign and convert the subtotal to a double

        Assert.assertEquals(actualBearPrice, bearPriced, "The subtotal does not match the expected total price");
        Jupiter.logsPassed("The Price of the Valentine Bear: $" + actualBearPrice + " in their cart is correct");


        Log.endTest(className);

        Screen.screenPass(driver, className);
    }


}
