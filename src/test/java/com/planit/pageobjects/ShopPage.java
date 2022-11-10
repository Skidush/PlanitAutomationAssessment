package com.planit.pageobjects;

import com.planit.components.ProductComponent;
import com.planit.helpers.CSVDataLoader;
import org.openqa.selenium.WebDriver;

import java.io.*;
import java.net.URISyntaxException;
import java.util.HashMap;

public class ShopPage extends JupiterToysPage<ShopPage> {
    private HashMap<String, ProductComponent> _products = new HashMap<>();
    public HashMap<String, Double> productPrices = new HashMap<>();
    public ShopPage(WebDriver webDriver) throws IOException, URISyntaxException {
        super(webDriver, "Jupiter Toys",
                "div.container-fluid div.products",
                "shop");

        CSVDataLoader.loadShopItems().forEach((item, price) -> {
            _products.put(item, _getShopItem(item));
            productPrices.put(item, price);
        });
    }

    private ProductComponent _getShopItem(String itemName) {
        return new ProductComponent(webDriver, ".//*[./h4[contains(text(),'"+ itemName +"')]]");
    }

    public ProductComponent getShopItem(String productName) {
        return _products.get(productName);
    }
}
