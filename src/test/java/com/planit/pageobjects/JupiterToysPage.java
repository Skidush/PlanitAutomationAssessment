package com.planit.pageobjects;

import com.planit.helpers.LazyElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Objects;

public class JupiterToysPage<T> extends JupiterToysBasePage {
    public JupiterToysPage(WebDriver webDriver, String title, String parentSelector, String baseUrl) {
        super(webDriver, title, parentSelector, baseUrl);
    }

    public JupiterToysPage(WebDriver webDriver) {
        super(webDriver, "Jupiter Toys",
                "div.row-fluid.ng-scope",
                "home");
    }
}
