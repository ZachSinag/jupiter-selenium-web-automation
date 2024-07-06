package jupiter.module3;

import jupiter.base.BrowserManager;
import jupiter.utilities.*;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.Map;

public class TC3_04 extends BrowserManager {
    Map<String, DataModel> dataMap;

    @Test
    public void TC3_04_CART_PAGE_VERIFY_THE_SUB_TOTAL_OF_ALL_PRODUCTS() throws IOException, InterruptedException {

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


        String frogSubTotal = MyElements.getText(driver, By.xpath("//td[normalize-space()='$21.98']"));
        double frogSubTotalDouble = Double.parseDouble(frogSubTotal.replace("$", "")); // Remove the dollar sign and convert the subtotal to a double

        String bunnySubTotal = MyElements.getText(driver, By.xpath("//td[normalize-space()='$49.95']"));
        double bunnySubTotalDouble = Double.parseDouble(bunnySubTotal.replace("$", "")); // Remove the dollar sign and convert the subtotal to a double

        String bearSubTotal = MyElements.getText(driver, By.xpath("//td[normalize-space()='$44.97']"));
        double bearSubTotalDouble = Double.parseDouble(bearSubTotal.replace("$", "")); // Remove the dollar sign and convert the subtotal to a double

        double productsTotal = frogSubTotalDouble + bunnySubTotalDouble + bearSubTotalDouble;

        String actualProductsTotal = MyElements.getText(driver, By.xpath("//tfoot//tr[1]//td[1]"));
        double ActualSubTotalDouble = Double.parseDouble(actualProductsTotal.replace("Total: ", "")); // Remove the Total:  and convert the subtotal to a double

        Assert.assertEquals(productsTotal, ActualSubTotalDouble, "The subtotal does not match the expected Products Total price");
        Jupiter.logsPassed("The Subtotal of All products order : $" + ActualSubTotalDouble + " in their cart is correct");


        Log.endTest(className);

        Screen.screenPass(driver, className);
    }


}
