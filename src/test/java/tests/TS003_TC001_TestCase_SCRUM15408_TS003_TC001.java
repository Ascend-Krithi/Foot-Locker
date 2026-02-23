/**
 * TC_ID: 2015
 * TC_Name: Test Case - SCRUM-15408 TS-003 TC-001
 * Description: Launch homepage, Open Store Locator, Search 'Boston, MA', Verify specific store address '375 Washington Street, Boston, MA 02108'. Expected: Store with exact address is visible in results
 */
package tests;

import core.DriverFactory;
import core.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.HomePage;
import pages.StoreLocatorPage;
import pages.StoreResultsPage;

public class TS003_TC001_TestCase_SCRUM15408_TS003_TC001 {
    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        DriverFactory.getDriver().get(ConfigReader.get("base.url"));
    }

    @Test
    public void testStoreAddressVisible() {
        HomePage home = new HomePage();
        StoreLocatorPage locator = new StoreLocatorPage();
        StoreResultsPage results = new StoreResultsPage();
        home.clickFindAStore();
        locator.clickSelectMyStore();
        locator.enterLocation("Boston, MA");
        locator.clickSearch();
        Assert.assertTrue(results.isStoreAddressVisible("375 Washington Street, Boston, MA 02108"), "Store address is visible in results");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
