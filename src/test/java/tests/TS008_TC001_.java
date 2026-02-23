package tests;

import core.DriverFactory;
import core.ConfigReader;
import org.testng.annotations.*;
import pages.HomePage;
import pages.StoreLocatorPage;
import pages.StoreResultsPage;
import org.testng.Assert;

/**
 * Acceptance Criteria ID: SCRUM-15408
 * Test Scenario ID: TS-008
 * Test Case ID: TC-001 (2170)
 * Description: Search for invalid location 'Atlantis' and verify 'No stores found' message
 */
public class TS008_TC001_ {
    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        DriverFactory.getDriver().get(ConfigReader.get("base.url"));
    }

    @Test
    public void testInvalidLocationShowsNoStoresFound() {
        HomePage home = new HomePage();
        home.clickFindAStore();
        StoreLocatorPage locator = new StoreLocatorPage();
        locator.clickSelectMyStore();
        locator.enterLocation("Atlantis");
        locator.clickSearch();
        StoreResultsPage results = new StoreResultsPage();
        Assert.assertTrue(results.isNoStoresFoundMessageDisplayed(), "No stores found message not displayed");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
