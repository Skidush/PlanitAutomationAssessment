package com.planit.pageobjects;

import com.planit.components.CartItemComponent;
import com.planit.helpers.LazyElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;

public class CartPage extends JupiterToysPage<CartPage> {
    public CartPage(WebDriver webDriver) {
        super(webDriver, "Jupiter Toys",
                "div[ui-if]",
                "cart");
    }

    public CartItemComponent getCartItem(String itemName) {
        return new CartItemComponent(webDriver, ".//*[./td[contains(text(),' " + itemName + "')]]");
    }

    public double getTotal() throws Exception {
        LazyElement totalElement = new LazyElement(new By.ByCssSelector(parentSelector + " strong.total"));
        return new Double(totalElement.getText().split(":")[1].trim());
    }
}
