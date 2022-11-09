package com.planit.pageobjects;

import org.openqa.selenium.WebDriver;

public class ShopPage extends JupiterToysPage<ShopPage> {
    public ShopPage(WebDriver webDriver) {
        super(webDriver, "Jupiter Toys",
                "div.row-fluid.ng-scope",
                "home");
    }
}
