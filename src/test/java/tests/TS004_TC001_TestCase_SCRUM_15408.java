package tests;

import core.DriverFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.StoreLocatorPage;
import pages.StoreResultsPage;

/**
 * TC_ID: 2166
 * Description: Search for 'Boston, MA', verify store with address '375 Washington Street, Boston, MA 02108' is visible
 */
public class TS004_TC001_TestCase_SCRUM_15408 {
    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
    }

    @Test
    public void testBostonStoreAddressVisible() {
        HomePage home = new HomePage();
        home.openHomePage();
        home.clickFindAStore();
        StoreLocatorPage locator = new StoreLocatorPage();
        locator.clickSelectMyStore();
        locator.enterLocation("Boston, MA");
        locator.clickSearchButton();
        StoreResultsPage results = new StoreResultsPage();
        Assert.assertTrue(results.isStoreWithAddressVisible("375 Washington Street, Boston, MA 02108"), "Store with address not found");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
