package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.StoreLocatorPage;
import pages.StoreResultsPage;

public class TC3240_NoTyposStoreAddress extends BaseTest {
    @Test
    public void testNoTyposStoreAddress() {
        HomePage home = new HomePage(driver);
        home.clickFindAStore();
        home.clickSelectMyStore();
        StoreLocatorPage locator = new StoreLocatorPage(driver);
        locator.enterLocation("Boston, MA");
        locator.clickSearch();
        StoreResultsPage results = new StoreResultsPage(driver);
        Assert.assertTrue(results.isStorePresent("375 Washington Street, Boston, MA 02108"), "Store address should have no typos");
    }
}
