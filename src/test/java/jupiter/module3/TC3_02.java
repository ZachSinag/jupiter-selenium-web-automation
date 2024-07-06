package jupiter.module3;

import jupiter.base.BrowserManager;
import jupiter.utilities.*;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.Map;

public class TC3_02 extends BrowserManager {
    Map<String, DataModel> dataMap;

    @Test
    public void TC3_02_CART_PAGE_VERIFY_SUB_TOTAL_FOR_EACH_PRODUCTS() throws IOException, InterruptedException {

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

        String stuffedFrog = MyElements.getText(driver, By.xpath("//td[normalize-space()='Stuffed Frog']"));
        Assert.assertEquals(stuffedFrog, "Stuffed Frog");
        Jupiter.logsPassed("The user able to see the : " + stuffedFrog + " on their cart");

        String fluffyBunny = MyElements.getText(driver, By.xpath("//td[normalize-space()='Fluffy Bunny']"));
        Assert.assertEquals(fluffyBunny, "Fluffy Bunny");
        Jupiter.logsPassed("The user able to see the : " + fluffyBunny + " on their cart");

        String valentineBear = MyElements.getText(driver, By.xpath("//td[normalize-space()='Valentine Bear']"));
        Assert.assertEquals(valentineBear, "Valentine Bear");
        Jupiter.logsPassed("The user able to see the : " + valentineBear + " on their cart");

        //check the subtotal of the 2 Stuffed Frog
        String frogPriceStr = data.getStuff_frog_price();
        double frogPrice = Double.parseDouble(frogPriceStr);  // Convert the string to a double
        double frogPriceTotal = frogPrice * 2;

        String frogSubTotal = MyElements.getText(driver, By.xpath("//td[normalize-space()='$21.98']"));
        double frogSubTotalDouble = Double.parseDouble(frogSubTotal.replace("$", "")); // Remove the dollar sign and convert the subtotal to a double

        Assert.assertEquals(frogSubTotalDouble, frogPriceTotal, "The subtotal does not match the expected total price");
        Jupiter.logsPassed("The Subtotal of the Stuffed Frog : $" + frogSubTotalDouble + " in their cart is correct");

        //check the subtotal of the 5 Fluffy Bunny
        String bunnyPriceStr = data.getFluffy_bunny_price();
        double bunnyPrice = Double.parseDouble(bunnyPriceStr);  // Convert the string to a double
        double bunnyPriceTotal = bunnyPrice * 5;

        String bunnySubTotal = MyElements.getText(driver, By.xpath("//td[normalize-space()='$49.95']"));
        double bunnySubTotalDouble = Double.parseDouble(bunnySubTotal.replace("$", "")); // Remove the dollar sign and convert the subtotal to a double

        Assert.assertEquals(bunnySubTotalDouble, bunnyPriceTotal, "The subtotal does not match the expected total price");
        Jupiter.logsPassed("The Subtotal of the Fluffy Bunny : $" + bunnySubTotalDouble + " in their cart is correct");

        //check the subtotal of the 3 Valentine Bear
        String bearPriceStr = data.getValentines_bear_price();
        double bearPrice = Double.parseDouble(bearPriceStr);  // Convert the string to a double
        double bearPriceTotal = bearPrice * 3;

        String bearSubTotal = MyElements.getText(driver, By.xpath("//td[normalize-space()='$44.97']"));
        double bearSubTotalDouble = Double.parseDouble(bearSubTotal.replace("$", "")); // Remove the dollar sign and convert the subtotal to a double

        Assert.assertEquals(bearSubTotalDouble, bearPriceTotal, "The subtotal does not match the expected total price");
        Jupiter.logsPassed("The Subtotal of the Valentine Bear : $" + bearSubTotalDouble + " in their cart is correct");


        Log.endTest(className);

        Screen.screenPass(driver, className);
    }


}
