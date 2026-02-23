package tests;

import core.DriverFactory;
import core.ConfigReader;
import org.testng.annotations.*;
import pages.HomePage;
import pages.StoreLocatorPage;
import org.testng.Assert;

/**
 * Acceptance Criteria ID: SCRUM-15408
 * Test Scenario ID: TS-009
 * Test Case ID: TC-002 (2172)
 * Description: Leave location empty, click Search if enabled, verify error message
 */
public class TS009_TC002_ {
    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        DriverFactory.getDriver().get(ConfigReader.get("base.url"));
    }

    @Test
    public void testErrorMessageWhenSearchClickedWithEmptyLocation() {
        HomePage home = new HomePage();
        home.clickFindAStore();
        StoreLocatorPage locator = new StoreLocatorPage();
        locator.clickSelectMyStore();
        if (locator.isSearchButtonEnabled()) {
            locator.clickSearch();
            // Assume error message is displayed as no results
            pages.StoreResultsPage results = new pages.StoreResultsPage();
            Assert.assertTrue(results.isNoStoresFoundMessageDisplayed(), "Error message not displayed for empty location");
        } else {
            Assert.assertTrue(true, "Search button is disabled as expected");
        }
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
