package com.planit.drivers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.IOException;
import java.time.Duration;

public class BaseTest implements Test {
    public WebDriver driver;

    @BeforeMethod
    public void initialize() {
        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();
        driver.manage().window().maximize();

        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofMinutes(1));
        FluentWait fluentWait = new FluentWait(driver);

        DriverInstance.driver = driver;
        DriverInstance.explicitWait = explicitWait;

        driver.get("https://jupiter.cloud.planittesting.com/#/");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) throws IOException {

    }
}
