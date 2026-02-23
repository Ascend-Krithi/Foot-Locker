package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.StoreLocatorPage;
import pages.StoreResultsPage;

public class TS003_TC002_V3_Test extends BaseTest {
    @Test
    public void testSearchByZipCode_V3() {
        HomePage home = new HomePage(driver);
        home.clickFindAStore();
        StoreLocatorPage locator = new StoreLocatorPage(driver);
        locator.enterSearch("32801");
        StoreResultsPage results = new StoreResultsPage(driver);
        Assert.assertTrue(results.getStoreCards().size() > 0, "Stores should be found for zip code");
    }
}
