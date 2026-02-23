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
 * Test Scenario ID: TS-007
 * Test Case ID: TC-001 (2169)
 * Description: Set Boston store, navigate to another page, verify store persists
 */
public class TS007_TC001_ {
    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        DriverFactory.getDriver().get(ConfigReader.get("base.url"));
    }

    @Test
    public void testStorePersistsAfterNavigation() {
        HomePage home = new HomePage();
        home.clickFindAStore();
        StoreLocatorPage locator = new StoreLocatorPage();
        locator.clickSelectMyStore();
        locator.enterLocation("Boston, MA");
        locator.clickSearch();
        StoreResultsPage results = new StoreResultsPage();
        results.setMyStore("375 Washington Street, Boston, MA 02108");
        Assert.assertTrue(results.isConfirmationIndicatorDisplayed(), "Confirmation indicator not displayed");
        // Navigate to homepage again
        DriverFactory.getDriver().get(ConfigReader.get("base.url"));
        // Check if store persists (confirmation indicator still visible)
        home.clickFindAStore();
        locator.clickSelectMyStore();
        Assert.assertTrue(results.isConfirmationIndicatorDisplayed(), "Store selection did not persist");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
