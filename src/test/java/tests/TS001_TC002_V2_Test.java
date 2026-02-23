package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.StoreLocatorPage;
import pages.StoreResultsPage;

public class TS001_TC002_V2_Test extends BaseTest {
    @Test
    public void testSearchWithInvalidZipShowsNoResults_V2() {
        HomePage home = new HomePage(driver);
        home.clickFindAStore();
        StoreLocatorPage locator = new StoreLocatorPage(driver);
        locator.enterSearch("99999");
        StoreResultsPage results = new StoreResultsPage(driver);
        Assert.assertTrue(results.isEmptyResultsMessageDisplayed(), "No results message should be displayed");
    }
}
