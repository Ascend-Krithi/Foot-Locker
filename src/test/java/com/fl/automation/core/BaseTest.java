package com.fl.automation.core;

import com.fl.automation.pages.HomePage;
import com.fl.automation.helpers.StoreLocatorHelper;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseTest {
    protected HomePage homePage;
    protected StoreLocatorHelper storeLocatorHelper;

    @BeforeMethod
    @Parameters({"browser"})
    public void setUp(@Optional("chrome") String browser) {
        DriverFactory.initDriver(browser);
        homePage = new HomePage(DriverFactory.getDriver());
        storeLocatorHelper = new StoreLocatorHelper(DriverFactory.getDriver());
        homePage.navigateToHomePage();
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}