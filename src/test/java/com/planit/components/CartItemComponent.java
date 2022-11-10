package com.planit.components;

import com.planit.helpers.LazyElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CartItemComponent extends JupiterToysComponent {
    private final LazyElement _cartItem;
    private final WebDriver _webDriver;

    public CartItemComponent(WebDriver webDriver, String xPathParentSelector) {
        super(webDriver, xPathParentSelector);

        _webDriver = webDriver;
        _cartItem = new LazyElement(new By.ByXPath(xPathParentSelector));
    }

    private LazyElement _getCartItemColumn(int columnIndex) throws Exception {
        return _cartItem.findElement(new By.ByXPath("//td[" + columnIndex + "]"));
    }

    public double getPrice() throws Exception {
        return new Double(_getCartItemColumn(2).getText().replace("$", ""));
    }

    public int getQuantity() throws Exception {
        return new Integer(_getCartItemColumn(3).findElement(new By.ByXPath("//input")).getValue());
    }

    public double getSubtotal() throws Exception {
        return new Double(_getCartItemColumn(4).getText().replace("$", ""));
    }
}
