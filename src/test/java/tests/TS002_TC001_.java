package tests;

import core.DriverFactory;
import core.ConfigReader;
import org.testng.annotations.*;
import pages.HomePage;
import pages.StoreLocatorPage;
import org.testng.Assert;

/**
 * Acceptance Criteria ID: SCRUM-15408
 * Test Scenario ID: TS-002
 * Test Case ID: TC-001 (2164)
 * Description: Launch homepage, click Find a Store, click Select My Store, verify Location textbox and Search button
 */
public class TS002_TC001_ {
    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        DriverFactory.getDriver().get(ConfigReader.get("base.url"));
    }

    @Test
    public void testSelectMyStoreShowsLocationInput() {
        HomePage home = new HomePage();
        home.clickFindAStore();
        StoreLocatorPage locator = new StoreLocatorPage();
        locator.clickSelectMyStore();
        Assert.assertTrue(locator.isLocationInputDisplayed(), "Location textbox not displayed");
        Assert.assertTrue(locator.isSearchButtonEnabled(), "Search button not enabled");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
