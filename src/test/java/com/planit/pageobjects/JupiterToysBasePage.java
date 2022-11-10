package com.planit.pageobjects;

import com.planit.components.HeaderComponent;
import com.planit.helpers.LazyElement;
import com.planit.hooks.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Objects;

public abstract class JupiterToysBasePage implements BasePage {
    protected final WebDriver webDriver;

    public final LazyElement parentElement;
    public final String parentSelector;
    public final String title;
    public final String baseUrl;
    public final HeaderComponent header;

    public JupiterToysBasePage(WebDriver webDriver, String title, String parentSelector, String baseUrl) {
        this.webDriver = webDriver;

        parentElement = new LazyElement(new By.ByCssSelector(parentSelector));

        // body always contains the main page's elements
        String bodySelector = "body[ng-app=\"jupiterApp\"] div.container-fluid";
        this.parentSelector = bodySelector + " " + parentSelector;
        this.title = title;
        this.baseUrl = BaseTest.baseUrl + baseUrl;

        header = new HeaderComponent(this.webDriver,
                "body[ng-app=\"jupiterApp\"] div.navbar.navbar-inverse.navbar-fixed-top div.navbar-inner div.container-fluid");
    }

    public boolean isLoaded(boolean suppressExceptions) throws Exception {
        boolean elementIsLoaded = false;
        boolean titleIsCorrect = false;
        boolean urlIsCorrect = false;

        try {
            LazyElement element = parentElement.waitToLoad(false);

            elementIsLoaded = Objects.nonNull(element);
            titleIsCorrect = Objects.equals(webDriver.getTitle(), title);
            urlIsCorrect = Objects.equals(webDriver.getCurrentUrl(), baseUrl);

            if (!titleIsCorrect || !elementIsLoaded || !urlIsCorrect) {
                throw new Exception("The " + title + " page was not loaded!");
            }
        } catch (Exception e) {
            if (!suppressExceptions) {
                throw e;
            }
        }

        return elementIsLoaded && titleIsCorrect && urlIsCorrect;
    }

    public boolean isLoaded() throws Exception {
        return this.isLoaded(true);
    }

    public void waitToLoad() throws Exception {
        parentElement.waitToLoad(false);
    }

    public <T> T navigate(boolean expectSuccess) throws Exception {
        boolean isUserRedirected = false;
        Exception exception = null;

        webDriver.navigate().to(baseUrl);

        try {
            isUserRedirected = isLoaded();
            waitToLoad();
        } catch (Exception e) {
            exception = e;
        }

        if (expectSuccess && (Objects.nonNull(exception) || !isUserRedirected)) {
            throw exception;
        }

        return (T) this;
    }

    public <T> T navigate() throws Exception {
        return (T) this.navigate(false);
    }
}
