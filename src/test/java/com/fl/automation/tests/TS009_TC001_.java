package com.fl.automation.tests;

import com.fl.automation.core.ConfigReader;
import com.fl.automation.core.DriverFactory;
import com.fl.automation.pages.HomePage;
import com.fl.automation.pages.StoreLocatorPage;
import com.fl.automation.pages.StoreResultsPage;
import org.testng.Assert;
import org.testng.annotations.*;

/**
 * Acceptance Criteria ID: 2171
 * Test Scenario ID: SCRUM-15408 TS-009
 * Test Case ID: TC-001
 * Description: Launch website -> Click Find a Store -> Click Select My Store -> Leave the Location textbox empty -> Observe the state of the Search for Stores button
 */
public class TS009_TC001_ {
    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        DriverFactory.getDriver().get(ConfigReader.get("baseUrl"));
    }
    @Test
    public void testSearchButtonDisabledWhenLocationEmpty() {
        HomePage home = new HomePage();
        home.clickFindAStore();
        StoreLocatorPage locator = new StoreLocatorPage();
        locator.clickSelectMyStore();
        StoreResultsPage results = new StoreResultsPage();
        Assert.assertFalse(results.isSearchButtonEnabled(), "Search for Stores button should be disabled when location is empty");
    }
    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
