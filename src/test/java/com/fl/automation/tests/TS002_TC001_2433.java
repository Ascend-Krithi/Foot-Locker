package com.fl.automation.tests;

import com.fl.automation.core.ConfigReader;
import com.fl.automation.core.DriverFactory;
import com.fl.automation.pages.HomePage;
import com.fl.automation.pages.StoreLocatorPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
Acceptance Criteria ID: 2433
Test Scenario ID: SCRUM-15408 TS-002 TC-001
Test Case ID: 2433
Description: Launch homepage, click 'Find a Store', click 'Select My Store', verify Location textbox and Search button
*/
public class TS002_TC001_2433 {
    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver("chrome");
        driver = DriverFactory.getDriver();
        driver.get(ConfigReader.get("base.url"));
    }

    @Test
    public void testSelectMyStoreDisplaysLocationAndSearch() {
        HomePage homePage = new HomePage(driver);
        homePage.clickFindAStore();
        homePage.clickSelectMyStore();
        StoreLocatorPage locatorPage = new StoreLocatorPage(driver);
        Assert.assertTrue(locatorPage.isLocationTextboxVisible(), "Location textbox is not visible");
        Assert.assertTrue(locatorPage.isSearchForStoresButtonVisible(), "Search for Stores button is not visible");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
