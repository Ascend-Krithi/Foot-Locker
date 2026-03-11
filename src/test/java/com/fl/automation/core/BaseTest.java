package com.fl.automation.core;

import com.fl.automation.helpers.StoreLocatorHelper;
import com.fl.automation.pages.HomePage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;
    protected HomePage homePage;
    protected StoreLocatorHelper storeLocatorHelper;
    protected String baseUrl;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        String headlessProperty = System.getProperty("headless", System.getenv("HEADLESS"));
        boolean headless = headlessProperty != null && headlessProperty.equalsIgnoreCase("true");
        
        driver = DriverFactory.createChromeDriver(headless);
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(90));
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(30));
        
        baseUrl = System.getProperty("base.url", System.getenv("BASE_URL"));
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "https://www.footlocker.com/";
        }
        
        homePage = new HomePage(driver);
        storeLocatorHelper = new StoreLocatorHelper(driver);
        
        driver.get(baseUrl);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}