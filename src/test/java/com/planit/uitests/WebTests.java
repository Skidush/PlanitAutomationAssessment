package com.planit.uitest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

public class WebTests {
    public WebDriver driver;
    public WebDriverWait explicitWait;

    /*From the home page go to contact page
    Click submit button
    Verify error messages
    Populate mandatory fields
    Validate errors are gone*/

    @Test
    public void test() {
        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();
        driver.manage().window().maximize();

        explicitWait = new WebDriverWait(driver, Duration.ofMinutes(1));

        /*BrowserActions.setDriver(driver);
        BrowserActions.setExplicitWait(explicitWait);*/

        driver.get("https://jupiter.cloud.planittesting.com/#/");
    }
}
