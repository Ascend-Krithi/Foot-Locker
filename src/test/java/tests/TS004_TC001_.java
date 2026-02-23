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
 * Test Scenario ID: TS-004
 * Test Case ID: TC-001 (2166)
 * Description: Search Boston, MA and verify specific store address '375 Washington Street, Boston, MA 02108' is visible
 */
public class TS004_TC001_ {
    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        DriverFactory.getDriver().get(ConfigReader.get("base.url"));
    }

    @Test
    public void testSpecificStoreAddressVisible() {
        HomePage home = new HomePage();
        home.clickFindAStore();
        StoreLocatorPage locator = new StoreLocatorPage();
        locator.clickSelectMyStore();
        locator.enterLocation("Boston, MA");
        locator.clickSearch();
        StoreResultsPage results = new StoreResultsPage();
        Assert.assertTrue(results.isSpecificStoreAddressVisible("375 Washington Street, Boston, MA 02108"), "Specific store address not visible");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
