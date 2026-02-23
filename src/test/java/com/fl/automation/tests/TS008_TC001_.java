package com.fl.automation.tests;

import com.fl.automation.core.ConfigReader;
import com.fl.automation.core.DriverFactory;
import com.fl.automation.pages.HomePage;
import com.fl.automation.pages.StoreLocatorPage;
import com.fl.automation.pages.StoreResultsPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Acceptance Criteria ID: SCRUM-15408
 * Test Scenario ID: TS-008
 * Test Case ID: TC-001
 * Description: Search for stores with invalid location, verify error message or 'No results found'
 */
public class TS008_TC001_ {
    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
    }

    @Test
    public void testNoResultsForInvalidLocation() {
        HomePage home = new HomePage();
        StoreLocatorPage locator = new StoreLocatorPage();
        StoreResultsPage results = new StoreResultsPage();
        home.open(ConfigReader.get("base.url"));
        home.clickFindAStore();
        locator.clickSelectMyStore();
        locator.enterLocation("InvalidLocation123");
        locator.clickSearchForStores();
        Assert.assertTrue(results.isNoResultsMessageDisplayed(), "No results message not displayed for invalid location");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
