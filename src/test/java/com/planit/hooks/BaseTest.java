package com.planit.hooks;

import com.planit.drivers.DriverInstance;
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
    public static String baseUrl = "https://jupiter.cloud.planittesting.com/#/";

    @BeforeMethod
    public void initialize() {
        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();
        driver.manage().window().maximize();

        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(20));

        DriverInstance.driver = driver;
        DriverInstance.explicitWait = explicitWait;

        driver.get(baseUrl);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) throws IOException {
        driver.quit();
    }
}
