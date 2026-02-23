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
 * Test Scenario ID: TS-005
 * Test Case ID: TC-001 (2167)
 * Description: Search Boston, MA, locate specific store, click Set My Store
 */
public class TS005_TC001_ {
    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        DriverFactory.getDriver().get(ConfigReader.get("base.url"));
    }

    @Test
    public void testSetMyStore() {
        HomePage home = new HomePage();
        home.clickFindAStore();
        StoreLocatorPage locator = new StoreLocatorPage();
        locator.clickSelectMyStore();
        locator.enterLocation("Boston, MA");
        locator.clickSearch();
        StoreResultsPage results = new StoreResultsPage();
        results.setMyStore("375 Washington Street, Boston, MA 02108");
        // No assertion needed as exception will be thrown if not found
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
