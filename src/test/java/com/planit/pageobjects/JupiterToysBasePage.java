package com.planit.pageobjects;

import org.openqa.selenium.WebDriver;

public class JupiterToysPagez<T> extends JupiterToysBasePage<T> {

    public JupiterToysPagez(WebDriver webDriver, String title, String mainSelector, String baseUrl) {
        super(webDriver, title, mainSelector, baseUrl);
    }
}
