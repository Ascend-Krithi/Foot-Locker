package com.fl.automation.tests;

import com.fl.automation.core.DriverFactory;
import com.fl.automation.core.ConfigReader;
import com.fl.automation.pages.HomePage;
import com.fl.automation.pages.StoreLocatorPage;
import com.fl.automation.pages.StoreResultsPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
Acceptance Criteria ID: 
Test Scenario ID: SCRUM-17166 TS-001
Test Case ID: TC-004 (3196)
Description: Search Boston MA, verify store at 375 Washington Street exists
*/
public class TS001_TC004_3196 {
    private WebDriver driver;
    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        driver = DriverFactory.getDriver();
        driver.get(ConfigReader.getProperty("base.url"));
    }
    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
    @Test
    public void testStoreAddressPresent() {
        HomePage home = new HomePage(driver);
        home.clickFindAStore();
        StoreLocatorPage locator = new StoreLocatorPage(driver);
        locator.clickSelectMyStore();
        StoreResultsPage results = new StoreResultsPage(driver);
        results.enterLocation("Boston MA");
        results.clickSearchButton();
        Assert.assertTrue(results.isStoreAddressPresent("375 Washington Street"), "Store at 375 Washington Street should be present");
    }
}
