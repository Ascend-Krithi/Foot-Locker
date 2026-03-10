package com.fl.automation.core;

import com.fl.automation.pages.HomePage;
import com.fl.automation.utils.ExtentManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    protected WebDriver driver;
    protected HomePage homePage;
    protected static final String BASE_URL = "https://www.footlocker.com/";

    @BeforeMethod
    public void setup() {
        String headlessProperty = System.getProperty("headless", "false");
        boolean headless = Boolean.parseBoolean(headlessProperty);
        
        driver = DriverFactory.createDriver(headless);
        DriverFactory.setDriver(driver);
        
        homePage = new HomePage(driver);
        
        driver.get(BASE_URL);
        homePage.acceptCookiesIfPresent();
        homePage.closeFlxRewardsIfPresent();
    }

    @AfterMethod(alwaysRun = true)
    public void teardown() {
        DriverFactory.quitDriver();
        ExtentManager.removeTest();
    }
}