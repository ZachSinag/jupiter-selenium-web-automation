package jupiter.utilities;

import jupiter.listener;
import junit.framework.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MyElements extends listener {

    public static void click(WebDriver driver, By by) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(by));
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
        driver.findElement(by).click();
    }

    public static void wait(WebDriver driver, String xpath) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(xpath))));
    }

    public static void sendKeys(WebDriver driver, By by, String keys) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
        driver.findElement(by).sendKeys(keys);
    }

    public static String getText(WebDriver driver, By by) {

        String displayText;

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        displayText = driver.findElement(by).getText();

        return displayText;
    }

    public static void textClick(WebDriver driver, String textElement) {
        String holder = "//*[text()='" + textElement + "']";
        (new WebDriverWait(driver, Duration.ofSeconds(10))).until(ExpectedConditions.elementToBeClickable(By.xpath(holder)));
        driver.findElement(By.xpath(holder)).click();
    }

    public static void textWaitInvisible(WebDriver driver, String textElement) {
        String holder = "//*[text()='" + textElement + "']";
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(holder)));
    }

}
