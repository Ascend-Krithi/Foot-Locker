package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.StoreLocatorPage;
import pages.StoreResultsPage;

public class TS002_TC001_Test extends BaseTest {
    @Test
    public void testStoreAddressesAreDisplayed() {
        HomePage home = new HomePage(driver);
        home.clickFindAStore();
        StoreLocatorPage locator = new StoreLocatorPage(driver);
        locator.enterSearch("Los Angeles");
        StoreResultsPage results = new StoreResultsPage(driver);
        Assert.assertTrue(results.getStoreAddresses().size() > 0, "Store addresses should be displayed");
    }
}
