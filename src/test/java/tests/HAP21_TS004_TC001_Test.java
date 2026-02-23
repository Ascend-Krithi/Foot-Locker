package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.StoreLocatorPage;
import pages.StoreResultsPage;

public class HAP21_TS004_TC001_Test extends BaseTest {
    @Test
    public void testFindStoreHeaderNavigatesToLocator_HAP21_4() {
        HomePage home = new HomePage(driver);
        home.clickFindAStore();
        StoreLocatorPage locator = new StoreLocatorPage(driver);
        locator.enterSearch("Jacksonville");
        StoreResultsPage results = new StoreResultsPage(driver);
        Assert.assertTrue(results.getStoreCards().size() > 0, "Store results should be displayed");
    }
}
