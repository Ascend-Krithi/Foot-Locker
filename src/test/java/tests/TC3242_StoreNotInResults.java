package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.StoreLocatorPage;
import pages.StoreResultsPage;

public class TC3242_StoreNotInResults extends BaseTest {
    @Test
    public void testStoreNotInResults() {
        HomePage home = new HomePage(driver);
        home.clickFindAStore();
        home.clickSelectMyStore();
        StoreLocatorPage locator = new StoreLocatorPage(driver);
        locator.enterLocation("Boston, MA");
        locator.clickSearch();
        StoreResultsPage results = new StoreResultsPage(driver);
        Assert.assertFalse(results.isStorePresent("123 Fake Street, Boston, MA 02108"), "Store not in results should not be present");
    }
}
