package com.fl.automation.tests;

import com.fl.automation.core.ConfigReader;
import com.fl.automation.core.DriverFactory;
import com.fl.automation.pages.HomePage;
import com.fl.automation.pages.StoreLocatorPage;
import com.fl.automation.pages.StoreResultsPage;
import org.testng.Assert;
import org.testng.annotations.*;

/**
 * Acceptance Criteria ID: 2172
 * Test Scenario ID: SCRUM-15408 TS-009
 * Test Case ID: TC-002
 * Description: Launch website -> Click Find a Store -> Click Select My Store -> Leave the Location textbox empty -> If Search for Stores button is enabled, click it
 */
public class TS009_TC002_ {
    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        DriverFactory.getDriver().get(ConfigReader.get("baseUrl"));
    }
    @Test
    public void testErrorMessageWhenLocationEmptyAndSearchClicked() {
        HomePage home = new HomePage();
        home.clickFindAStore();
        StoreLocatorPage locator = new StoreLocatorPage();
        locator.clickSelectMyStore();
        StoreResultsPage results = new StoreResultsPage();
        if (results.isSearchButtonEnabled()) {
            results.clickSearch();
            Assert.assertTrue(results.isErrorMessageDisplayed(), "Error message should be displayed indicating Location field is required");
        } else {
            Assert.assertTrue(true, "Search button is disabled as expected");
        }
    }
    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
