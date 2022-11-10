package com.planit.pageobjects;

import org.openqa.selenium.WebDriver;

public class HomePage extends JupiterToysPage<HomePage> {
    public HomePage(WebDriver webDriver) {
        super(webDriver, "Jupiter Toys",
                "div.row-fluid.ng-scope",
                "home");
    }
}
