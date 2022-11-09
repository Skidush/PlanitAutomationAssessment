package com.planit.drivers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementActions {
    private static WebDriverWait explicitWait;
    private static WebDriver driver;

    public static void setDriver(WebDriver driver) {
        ElementActions.driver = driver;
    }

    public static void setExplicitWait(WebDriverWait explicitWait) {
        ElementActions.explicitWait = explicitWait;
    }

    public static void waitToLoad(By by, boolean suppressExceptions) throws Exception {
        WebElement element = null;
        Exception exception = null;
        try {
            element = explicitWait.until(ExpectedConditions.presenceOfElementLocated(by));
        } catch (Exception e) {
            exception = e;
        }

        if (!suppressExceptions && element == null) {
            exception = exception != null ? exception : new Exception("The element " + by.toString() + " was not loaded!");
            throw exception;
        }
    }

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
