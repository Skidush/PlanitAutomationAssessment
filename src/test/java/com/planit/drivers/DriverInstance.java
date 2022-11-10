package com.planit.drivers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DriverInstance {
    public static WebDriverWait explicitWait;
    public static WebDriver driver;

    public static void clickElementByXpath(String elementXpath) {
        WebElement elementByXpath = explicitWait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(elementXpath))));
        elementByXpath.click();
    }

    public static void clickElementByID( String elementID) {
        WebElement elementByID = explicitWait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id(elementID))));
        elementByID.click();
    }

    public static void clickElementByCSS(String elementCSS) {
        WebElement elementByCSS = explicitWait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector(elementCSS))));
        elementByCSS.click();
    }

    public static void sendKeysToElementByID(String elementID, String keys) {
        WebElement elementByID = explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id(elementID))));
        elementByID.sendKeys(keys);
    }

    public static String getTextOfElementByCSS(String elementCSS) {
        WebElement elementInPage = explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(elementCSS))));
        return elementInPage.getText();
    }
}
