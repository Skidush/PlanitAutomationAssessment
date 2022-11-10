package com.planit.components;

import com.planit.helpers.LazyElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductComponent extends JupiterToysComponent {
    private final LazyElement _productCard;
    private final WebDriver _webDriver;

    public ProductComponent(WebDriver webDriver, String xPathParentSelector) {
        super(webDriver, xPathParentSelector);

        _webDriver = webDriver;
        _productCard = new LazyElement(new By.ByXPath(xPathParentSelector));
    }

    public void buy(int quantity) {
        WebElement buyButton = _productCard.getNativeElement();
        buyButton = buyButton.findElement(new By.ByCssSelector("a[ng-click=\"add(item)\"]"));

        for (int i = 1; i <= quantity; i++) {
            buyButton.click();
        }
    }
}
