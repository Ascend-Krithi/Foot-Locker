package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.StoreLocatorPage;
import pages.StoreResultsPage;

public class TS005_TC001_V2_Test extends BaseTest {
    @Test
    public void testStoreCardsHaveAddresses_V2() {
        HomePage home = new HomePage(driver);
        home.clickFindAStore();
        StoreLocatorPage locator = new StoreLocatorPage(driver);
        locator.enterSearch("Phoenix");
        StoreResultsPage results = new StoreResultsPage(driver);
        Assert.assertTrue(results.getStoreAddresses().size() > 0, "Each store card should have an address");
    }
}
