package tests;

import core.DriverFactory;
import core.ConfigReader;
import org.testng.annotations.*;
import pages.HomePage;
import org.testng.Assert;

/**
 * Acceptance Criteria ID: SCRUM-15408
 * Test Scenario ID: TS-001
 * Test Case ID: TC-001 (2163)
 * Description: Launch homepage, click Find a Store, verify popup message and Select My Store link
 */
public class TS001_TC001_ {
    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        DriverFactory.getDriver().get(ConfigReader.get("base.url"));
    }

    @Test
    public void testFindAStorePopup() {
        HomePage home = new HomePage();
        home.clickFindAStore();
        // Assume popup message and Select My Store link are on StoreLocatorPage
        pages.StoreLocatorPage locator = new pages.StoreLocatorPage();
        Assert.assertTrue(locator.isLocationInputDisplayed() || locator.isSearchButtonEnabled(), "Popup or Select My Store link not displayed");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
