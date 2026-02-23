package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.StoreLocatorPage;
import pages.StoreResultsPage;

public class TS004_TC002_Test extends BaseTest {
    @Test
    public void testNoResultsForRandomText() {
        HomePage home = new HomePage(driver);
        home.clickFindAStore();
        StoreLocatorPage locator = new StoreLocatorPage(driver);
        locator.enterSearch("asdkjhasd");
        StoreResultsPage results = new StoreResultsPage(driver);
        Assert.assertTrue(results.isEmptyResultsMessageDisplayed(), "No results message should be displayed");
    }
}
