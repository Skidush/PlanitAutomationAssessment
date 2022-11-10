package com.planit.helpers;

import com.planit.drivers.DriverInstance;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LazyElement {
    private WebElement _element;
    private WebDriverWait _explicitWait;
    private final By _by;

    public LazyElement(By by) {
        _by = by;
    }

    private By _getByOfElement(WebElement webElement) throws Exception {
        By by = null;

        List<String> locators = Arrays.asList(webElement.toString().split("->"));
        List<String> descendantLocators = new ArrayList<>();
        String replaceRegex = "(?s)(.*)]";

        for (int i = 2 ; i < locators.size(); i++) {
            String locatorParsed = locators.get(i).replaceFirst(replaceRegex, "$1" + "").split(":")[1].trim();
            descendantLocators.add(locatorParsed);
            replaceRegex += "]";
        }

        String[] pathVariables = (locators.get(1).replaceFirst(replaceRegex, "$1" + "")).split(":");

        String selectorType = pathVariables[0].trim();
        String selectorValue = pathVariables[1].trim() + " " + String.join(" ", descendantLocators);

        switch (selectorType) {
            case "id":
                by = By.id(selectorValue);
                break;
            case "cssSelector":
                by = By.cssSelector(selectorValue);
                break;
            case "xpath":
                by = By.xpath(selectorValue);
                break;
            default:
                throw new Exception("Selector type not yet supported");
        }

        return by;
    }

    private void _throwExceptionIfUnsuppressed(boolean isSupressed, Exception exception) throws Exception {
        if (!isSupressed) {
            throw exception;
        }
    }

    private WebElement _waitForElementPresence(boolean supressExceptions) throws Exception {
        WebElement element = null;
        try {
            element = DriverInstance.explicitWait.until(ExpectedConditions.presenceOfElementLocated(_by));
        } catch (Exception e) {
            _throwExceptionIfUnsuppressed(supressExceptions, e);
        }

        return element;
    }

    public WebElement getNativeElement() {
        return DriverInstance.explicitWait.until(ExpectedConditions.presenceOfElementLocated(_by));
    }

    public LazyElement findElement(By by) throws Exception {
        WebElement web = waitToLoad().getNativeElement().findElement(by);
        By byOfChildElement = _getByOfElement(web);
        return new LazyElement(byOfChildElement);
    }

    public LazyElement waitToLoad(boolean suppressExceptions) throws Exception {
        _waitForElementPresence(suppressExceptions);
        return new LazyElement(_by);
    }

    public LazyElement waitToLoad() throws Exception {
        return this.waitToLoad(true);
    }

    public void click(boolean suppressExceptions) throws Exception {
        WebElement element = _waitForElementPresence(suppressExceptions);
        element.click();
    }

    public void click() throws Exception {
        this.click(true);
    }

    public String getText(boolean suppressExceptions) throws Exception {
        WebElement element = _waitForElementPresence(suppressExceptions);
        return element.getText();
    }

    public String getText() throws Exception {
        return this.getText(true);
    }

    public String getValue(boolean suppressExceptions) throws Exception {
        WebElement element = _waitForElementPresence(suppressExceptions);
        return element.getAttribute("value");
    }

    public String getValue() throws Exception {
        return this.getValue(true);
    }

    public void sendKeys(String keys, boolean suppressExceptions) throws Exception {
        WebElement element = _waitForElementPresence(suppressExceptions);
        element.sendKeys(keys);
    }

    public void sendKeys(String keys) throws Exception {
        this.sendKeys(keys, true);
    }

    public boolean waitForAbsence(boolean suppressExceptions) throws Exception {
        boolean isElementAbsent = false;
        try {
            isElementAbsent = DriverInstance.explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(_by));
        } catch (Exception e) {
            _throwExceptionIfUnsuppressed(suppressExceptions, e);
        }

        return isElementAbsent;
    }

    public boolean waitForAbsence() throws Exception {
        return this.waitForAbsence(true);
    }
}
