package com.planit.pageobjects;

import org.openqa.selenium.WebDriver;

public class HomePage extends JupiterToysBasePage {
    public HomePage(WebDriver webDriver) {
        super(webDriver, "Jupiter Toys",
                "body[ng-app=\"jupiterApp\"] div.container-fluid div.row-fluid.ng-scope",
                "https://jupiter.cloud.planittesting.com/#/home");
    }
}
