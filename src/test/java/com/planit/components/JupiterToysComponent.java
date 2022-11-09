package com.planit.pageobjects;

import com.planit.helpers.LazyElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Objects;

public class JupiterToysComponent implements BaseComponent {
    protected final WebDriver webDriver;

    public final String parentSelector;
    public final LazyElement parentElement;

    public JupiterToysComponent(WebDriver webDriver, String parentSelector) {
        this.webDriver = webDriver;

        parentElement = new LazyElement(new By.ByCssSelector(parentSelector));

        this.parentSelector = parentSelector;
    }

    public boolean isLoaded(boolean suppressExceptions) throws Exception {
        boolean elementIsLoaded = false;

        try {
            LazyElement element = parentElement.waitToLoad(false);
            elementIsLoaded = Objects.nonNull(element);
        } catch (Exception e) {
            if (!suppressExceptions) {
                throw e;
            }
        }

        return elementIsLoaded;
    }

    public boolean isLoaded() throws Exception {
        return this.isLoaded(true);
    }

    public void waitToLoad() throws Exception {
        parentElement.waitToLoad(false);
    }
}
