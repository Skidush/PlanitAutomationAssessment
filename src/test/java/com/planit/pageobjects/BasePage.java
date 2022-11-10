package com.planit.pageobjects;

import org.openqa.selenium.WebDriver;

public interface BasePage {
    boolean isLoaded(boolean suppressExceptions) throws Exception;
    boolean isLoaded() throws Exception;
    void waitToLoad() throws Exception;
    <T> T navigate() throws Exception;
}
